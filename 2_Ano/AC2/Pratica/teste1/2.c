#include <detpic32.h>

int main(void) {
    TRISBbits.TRISB4 = 1;     // RBx digital output disconnected
    AD1PCFGbits.PCFG4 = 0;    // RBx configured as analog input
    AD1CON1bits.SSRC = 7;     // Conversion trigger selection bits: in this
                              //  mode an internal counter ends sampling and
                              //  starts conversion
    AD1CON1bits.CLRASAM = 1;  // Stop conversions when the 1st A/D converter
                              //  interrupt is generated. At the same time,
                              //  hardware clears the ASAM bit
    AD1CON3bits.SAMC = 16;    // Sample time is 16 TAD (TAD = 100 ns)
    AD1CON2bits.SMPI = 1;     // Interrupt is generated after XX samples
                              //  (replace XX by the desired number of
                              //  consecutive samples)
    AD1CHSbits.CH0SA = 4;     // replace x by the desired input
                              //  analog channel (0 to 15)
    AD1CON1bits.ON = 1;       // Enable A/D converter
                              //  This must the last command of the A/D
                              //  configuration sequence
    TRISD = TRISD & 0xFF9F;
    TRISB = TRISB & 0x80FF;
    TRISEbits.TRISE1 = 0;
    LATEbits.LATE1 = 0; 
    LATB = LATB & 0x80FF;
    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;

    T3CONbits.TCKPS = 7;  // 1:256 prescaler Fout_presc = 78125;
    PR3 = 15624;          // Fout = 20Mhz / (256 * (39063 + 1)) = 1,999Hz
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3

    IPC3bits.T3IP = 2;  // Interrupt priority 2
    IEC0bits.T3IE = 1;  // Enable timer T3 interrupts
    IFS0bits.T3IF = 0;  // Reset timer T3 interrupt flag

    EnableInterrupts();
    while (1) {
    }

    return 0;
}

void _int_(12) isr_T3(void) {
    AD1CON1bits.ASAM = 1;  // Start conversion
    while (IFS1bits.AD1IF == 0)
        ;  // Wait while conversion not done
    LATEbits.LATE1 = !LATEbits.LATE1;
    int *p = (int *)(&ADC1BUF0);
    int i, valor = 0;
    for (i = 0; i < 2; i++) {
        valor += p[i * 4];
    }
    valor = valor / 2;
    printInt(valor, 16 | 3 << 16);
    putChar('\n');
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    LATB = (LATB & 0x80FF) | display7Scodes[(valor * 9 + 511)/ 1023] << 8;
    IFS0bits.T3IF = 0;  // reset flag
}
