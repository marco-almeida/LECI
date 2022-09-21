#include <detpic32.h>
unsigned char toBcd(unsigned char value);
void send2displays(unsigned char value);
void delay(int ms);
void configureAll();
void putc(char byte2send);
void putstr(char *str);
void sendUpdate(char *str, int minMax);
volatile int voltage = 0;  // Global variable
int voltMin = 34;
int voltMax = 0;
int main(void) {
    configureAll();
    IFS0bits.T1IF = 0;    // Reset timer T1 interrupt flag
    IFS0bits.T3IF = 0;    // Reset timer T3 interrupt flag
    IFS1bits.AD1IF = 0;   // Reset AD1IF
    IFS1bits.U2RXIF = 0;  // RESET U2RX Interrupt Flag

    EnableInterrupts();
    while (1);
    return 0;
}

void _int_(32) isr_uart2(void) {
    if (IFS1bits.U2RXIF == 1) {
        char byte = U2RXREG;  // Read character from FIFO (U2RXREG)
        if (byte == 'M') {
            sendUpdate("VMax=", voltMax);
        } else if (byte == 'm') {
            sendUpdate("VMin=", voltMin);
        }
        IFS1bits.U2RXIF = 0;  // Clear UART2 Rx interrupt flag
    }
}

void _int_(27) isr_adc(void) {
    int i;
    int *p = (int *)(&ADC1BUF0);
    voltage = 0;
    for (i = 0; i < 8; i++) {  // Get the values for the 8 samples
        voltage += (p[i * 4] * 33 + 511) / 1023;
    }
    voltage = voltage / 8;
    if (voltage > voltMax) {
        voltMax = voltage;
    }
    if (voltage < voltMin) {
        voltMin = voltage;
    }
    voltage = toBcd(voltage);

    IFS1bits.AD1IF = 0;
}

void _int_(12) isr_T3(void) {  // 100 hz / 10 ms
    send2displays(voltage);
    IFS0bits.T3IF = 0;  // Reset timer T3 interrupt flag
}

void _int_(4) isr_T1(void) {  // 5 hz / 200 ms
    AD1CON1bits.ASAM = 1;     // Start conversion
    IFS0bits.T1IF = 0;        // Reset timer T1 interrupt flag
}

void putstr(char *str) {
    int i = 0;
    while (str[i] != '\0') {
        putc(str[i++]);
    }
}

void configureAll() {
    /////// ad module
    TRISBbits.TRISB4 = 1;     // RBx digital output disconnected
    AD1PCFGbits.PCFG4 = 0;    // RBx configured as analog input
    AD1CON1bits.SSRC = 7;     // Conversion trigger selection bits: in this
                              //  mode an internal counter ends sampling and
                              //  starts conversion
    AD1CON1bits.CLRASAM = 1;  // Stop conversions when the 1st A/D converter
                              //  interrupt is generated. At the same time,
                              //  hardware clears the ASAM bit
    AD1CON3bits.SAMC = 16;    // Sample time is 16 TAD (TAD = 100 ns)
    AD1CON2bits.SMPI = 7;     // Interrupt is generated after XX samples
                              //  (replace XX by the desired number of
                              //  consecutive samples)
    AD1CHSbits.CH0SA = 4;     // replace x by the desired input
                              //  analog channel (0 to 15)
    AD1CON1bits.ON = 1;       // Enable A/D converter
                              //  This must the last command of the A/D
                              //  configuration sequence
    ////////// display
    TRISB = TRISB & 0x80FF;  // TRISB[8] A TRISB[14] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;  // displays high e low sao saidas
    ///////// timers and interrupts
    T1CONbits.TCKPS = 2;  // 1:64 prescaler
    PR1 = 62499;          // 5hz
    TMR1 = 0;             // Reset timer T1 count register
    T1CONbits.TON = 1;    // Enable timer T1

    T3CONbits.TCKPS = 2;  // 1:4 prescaler
    PR3 = 49999;          // 100hz
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3

    IPC1bits.T1IP = 2;  // Interrupt priority 2
    IEC0bits.T1IE = 1;  // Enable timer T1 interrupts

    IPC3bits.T3IP = 2;  // Interrupt priority 2
    IEC0bits.T3IE = 1;  // Enable timer T3 interrupts

    IPC6bits.AD1IP = 2;  // configure priority of A/D interrupts
    IEC1bits.AD1IE = 1;  // enable A/D interrupts

    // Configure UART2: 115200, N, 8, 1
    U2BRG = 10;               // U2BRG = (20Mhz / (16*115200))-1 ~ 10
    U2MODEbits.PDSEL = 0b00;  // 00 = 8-bit data, no parity
    U2MODEbits.STSEL = 0;     // only 1 stop bit
    U2MODEbits.BRGH = 0;      // divide by 16

    U2STAbits.URXEN = 1;       // Enable Receiver
    U2STAbits.UTXEN = 1;       // Enable Transmitter
    U2STAbits.URXISEL = 0b00;  // Select interrupt only for receiver
    IPC8bits.U2IP = 1;         // UART2 Priority
    IEC1bits.U2RXIE = 1;       // U2RX Interrupt Enable
    IEC1bits.U2TXIE = 0;       // U2TX Interrupt Disable

    U2MODEbits.ON = 1;  // Enable UART2
}

void send2displays(unsigned char value) {
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    static char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if (displayFlag == 0) {
        LATDbits.LATD6 = 1;
        LATDbits.LATD5 = 0;
        LATB = (LATB & 0x80FF) |
               ((unsigned int)(dh))
                   << 8;  // Clean the display and set the right value
    } else {
        LATDbits.LATD6 = 0;
        LATDbits.LATD5 = 1;
        LATB = (LATB & 0x80FF) |
               ((unsigned int)(dl))
                   << 8;  // Clean the display and set the right value
    }
    displayFlag = !displayFlag;
}

unsigned char toBcd(unsigned char value) {
    return ((value / 10) << 4) + (value % 10);
}

void delay(int ms) {
    resetCoreTimer();
    int timer = readCoreTimer();
    while (timer < ms * 20000) {
        timer = readCoreTimer();
    }
}

void putc(char byte2send) {
    while (U2STAbits.UTXBF == 1)
        ;
    U2TXREG = byte2send;
}

void sendUpdate(char *str, int minMax) {
    unsigned char val;
    val = toBcd(minMax);
    putstr(str);
    putc((val >> 4) + '0');
    putc('.');
    putc((val & 0x0F) + '0');
    putc('V');
    putc('\n');
}
