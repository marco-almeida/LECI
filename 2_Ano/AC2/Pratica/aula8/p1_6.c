#include <detpic32.h>

int main(void) {
    TRISE = TRISE & 0xFFF5; // RE1 E RE3 COMO SAIDAS 0101
    T1CONbits.TCKPS = 2;  // 1:64 prescaler
    PR1 = 62499;          // 5hz
    TMR1 = 0;             // Reset timer T1 count register
    T1CONbits.TON = 1;    // Enable timer T1

    IPC1bits.T1IP = 2;  // Interrupt priority 2
    IEC0bits.T1IE = 1;  // Enable timer T3 interrupts
    IFS0bits.T1IF = 0;  // Reset timer T3 interrupt flag

    T3CONbits.TCKPS = 4;  // 1:16 prescaler
    PR3 = 49999;          // 25hz
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3

    IPC3bits.T3IP = 2;  // Interrupt priority 2
    IEC0bits.T3IE = 1;  // Enable timer T3 interrupts
    IFS0bits.T3IF = 0;  // Reset timer T3 interrupt flag

    EnableInterrupts();

    while (1);

    return 0;
}

void _int_(12) isr_T3(void) {
    LATEbits.LATE3 = !LATEbits.LATE3; // toggle
    IFS0bits.T3IF = 0;  // Reset timer T3 interrupt flag
}

void _int_(4) isr_T1(void) {
    LATEbits.LATE1 = !LATEbits.LATE1; // toggle
    IFS0bits.T1IF = 0;  // Reset timer T1 interrupt flag
}
