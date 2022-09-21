#include <detpic32.h>
unsigned char pattern = 1;
int main(void) {
    TRISE = TRISE & 0xFFC0;  // RE SAIDAS
    TRISBbits.TRISB2 = 1;    // RB2 ENTRADA
    LATE = LATE & 0XFFC0;
    T3CONbits.TCKPS = 7;  // 1:256 prescaler Fout_presc = 78125;
    PR3 = 26040;          // Fout = 20Mhz / (256 * (39063 + 1)) = 1,999Hz
    // 11159
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3

    IPC3bits.T3IP = 2;  // Interrupt priority 2
    IEC0bits.T3IE = 1;  // Enable timer T3 interrupts
    IFS0bits.T3IF = 0;  // Reset timer T3 interrupt flag

    EnableInterrupts();
    while(1) {
        if (PORTBbits.RB2 == 1){
            PR3 = 11159;
        } else {
            PR3 = 26040;
        }
    }
    return 0;
}

void _int_(12) isr_T3(void) {
    LATE = (LATE & 0xFFC0) | pattern;
    pattern = pattern << 1;
    if (pattern == 64){
        pattern = 1;
    }
    IFS0bits.T3IF = 0; // reset flag
}
