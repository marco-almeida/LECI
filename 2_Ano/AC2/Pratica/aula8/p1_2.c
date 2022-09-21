#include <detpic32.h>
int main(void) {
    T3CONbits.TCKPS = 7;  // 1:256 prescaler Fout_presc = 78125;
    PR3 = 39062;          // Fout = 20Mhz / (256 * (39062 + 1)) = 1,9999Hz
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
    putChar('.');
    IFS0bits.T3IF = 0;
}
