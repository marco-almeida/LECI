#include <detpic32.h>
void send2displays(unsigned char value);
unsigned char toBcd(unsigned char value);
int counterValue;
int main(void) {
    TRISB = TRISB & 0x80FF;
    TRISD = TRISD & 0xFF9F;
    T1CONbits.TCKPS = 2;  // 1:64 prescaler 10hz
    PR1 = 31249;          //
    TMR1 = 0;             // Reset timer T1 count register
    T1CONbits.TON = 1;    // Enable timer T1

    IPC1bits.T1IP = 2;  // Interrupt priority 2
    IEC0bits.T1IE = 1;  // Enable timer T1 interrupts
    IFS0bits.T1IF = 0;  // Reset timer T1 interrupt flag

    T2CONbits.TCKPS = 3;  // 1:4 prescaler 100hz
    PR2 = 49999;          //
    TMR2 = 0;             // Reset timer T2 count register
    T2CONbits.TON = 1;    // Enable timer T2

    IPC2bits.T2IP = 2;  // Interrupt priority 2
    IEC0bits.T2IE = 1;  // Enable timer T2 interrupts
    IFS0bits.T2IF = 0;  // Reset timer T2 interrupt flag

    EnableInterrupts();
    while(1);
    return 0;
}

void _int_(8) isr_T2(void) {  // 0,02 sec
    send2displays(toBcd(counterValue));
    IFS0bits.T2IF = 0;  // reset flag
}

void _int_(4) isr_T1(void) {  // 0,1 sec
    counterValue++;
    if (counterValue == 99) {
        counterValue = 0;
    }
    printInt(counterValue, 16 | 2 << 16); 
    IFS0bits.T1IF = 0;  // reset flag
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
