#include <detpic32.h>
unsigned char count = 0;
int main(void) {
    TRISEbits.TRISE0 = 0;
    TRISDbits.TRISD8 = 1;
    LATEbits.LATE0 = 0;
    T2CONbits.TCKPS = 7;  // 1:256
    PR2 = 2366;           // 33 hz
    TMR2 = 0;             // Reset timer T2 count register
    T2CONbits.TON = 1;    // Enable timer T2

    IPC2bits.T2IP = 2;  // Interrupt priority 2
    IEC0bits.T2IE = 0;  // Enable timer T2 interrupts
    IFS0bits.T2IF = 0;  // Reset timer T2 interrupt flag

    IPC1bits.INT1IP = 26; // Interrupt priority 26
    IEC0bits.INT1IE = 1; // Enable INT1 interrupts
    IFS0bits.INT1IF = 0; // Reset timer INT1 interrupt flag

    EnableInterrupts();

    while (1)
        ;

    return 0;
}

void _int_(8) isr_T2(void) {
    count++;
    if (count >= 1 && count != 101) {  // 0,33 hz -> 3 segundos
        LATEbits.LATE0 = 1;
    } else {
        LATEbits.LATE0 = 0;
        IEC0bits.T2IE = 0;  // Disable timer T2 interrupts
    }
    IFS0bits.T2IF = 0;  // Reset timer T2 interrupt flag
}

void _int_(7) isr_INT1(void) {
    count = 0;
    IEC0bits.T2IE = 1;  // Enable timer T2 interrupts
    IFS0bits.INT1IF = 0; // Reset timer INT1 interrupt flag
}
