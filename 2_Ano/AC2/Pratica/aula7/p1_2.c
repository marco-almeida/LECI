#include <detpic32.h>
unsigned char toBcd(unsigned char value);
void send2displays(unsigned char value);
void delay(int ms);
volatile unsigned char voltage = 0;  // Global variable

int main(void) {
    unsigned int cnt = 0;
    // Configure all (digital I/O, analog input, A/D module, interrupts)
    TRISB = TRISB & 0x80FF;  // TRISB[8] A TRISB[14] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;  // displays high e low sao saidas

    TRISBbits.TRISB4 = 1;   // RB4 digital output disconnected
    AD1PCFGbits.PCFG4 = 0;  // RB4 configured as analog input (AN4)
    AD1CON1bits.SSRC = 7;   // Conversion trigger constant
    AD1CON1bits.CLRASAM =
        1;  // Stop conversion when the 1st A/D converter intetupr is generated.
            // At the same time, hardware clears ASAM bit
    AD1CON3bits.SAMC = 16;  // Sample time is 16 TAD (TAD = 100ns)
    AD1CON2bits.SMPI = 7;   // Interrupt is generated after 1 sample
    AD1CHSbits.CH0SA = 4;   // analog channel input 4
    AD1CON1bits.ON = 1;     // Enable the A/d configuration sequence

    // Enable interrupts ADC
    IPC6bits.AD1IP = 2;  // configure priority of A/D interrupts
    IFS1bits.AD1IF = 0;  // clear A/D interrupt flag
    IEC1bits.AD1IE = 1;  // enable A/D interrupts

    EnableInterrupts();  // Global Interrupt Enable

    while (1) {
        if (cnt % 20 == 0 && cnt != 0) {
            AD1CON1bits.ASAM = 1;  // Start conversion
        }
        // Send "voltage" value to displays
        send2displays(toBcd(voltage));
        delay(10);
        cnt++;
    }
    return 0;
}

// Interrupt handler
void _int_(27) isr_adc(void) {
    int i, average = 0;
    // Calculate buffer average (8 samples)
    int *p = (int *)(&ADC1BUF0);
    for (i = 0; i < 8; i++) {
        average += (p[i * 4] * 33 + 511) / 1023;
    }
    // Calculate voltage amplitude
    voltage = average / 8;
    // Convert voltage amplitude to decimal and store the result in the global
    // variable "voltage"
    IFS1bits.AD1IF = 0;
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
