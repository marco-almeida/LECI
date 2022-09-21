#include <detpic32.h>
int main(void) {
    // Configure Timer T3 (2 Hz with interrupts disabled)
    T3CONbits.TCKPS = 7;  // 1:256 prescaler Fout_presc = 78125;
    PR3 = 39063;          // Fout = 20Mhz / (256 * (39063 + 1)) = 1,999Hz
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3

    while (1) {
        while (IFS0bits.T3IF != 1);
        IFS0bits.T3IF = 0;
        putChar('.');
    }
    return 0;
}
