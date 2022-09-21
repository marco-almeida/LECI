#include <detpic32.h>
void delay(int ms);
int main(void) {
    TRISB = TRISB | 0x000F;
    TRISBbits.TRISB4 = 1;     // RBx digital output disconnected
    AD1PCFGbits.PCFG4 = 0;    // RBx configured as analog input
    AD1CON1bits.SSRC = 7;     // Conversion trigger selection bits: in this
                              //  mode an internal counter ends sampling and
                              //  starts conversion
    AD1CON1bits.CLRASAM = 1;  // Stop conversions when the 1st A/D converter
                              //  interrupt is generated. At the same time,
                              //  hardware clears the ASAM bit
    AD1CON3bits.SAMC = 16;    // Sample time is 16 TAD (TAD = 100 ns)
    AD1CON2bits.SMPI = 0;     // Interrupt is generated after XX samples
                              //  (replace XX by the desired number of
                              //  consecutive samples)
    AD1CHSbits.CH0SA = 4;     // replace x by the desired input
                              //  analog channel (0 to 15)
    AD1CON1bits.ON = 1;       // Enable A/D converter
                              //  This must the last command of the A/D
                              //  configuration sequence
    while (1) {
        AD1CON1bits.ASAM = 1;  // Start conversion
        while (IFS1bits.AD1IF == 0);                // Wait while conversion not done
        IFS1bits.AD1IF = 0;  // Reset AD1IF
        printStr("DS3=");
        putChar(PORTBbits.RB3 + '0');
        printStr(", ");
        printStr("DS2=");
        putChar(PORTBbits.RB2 + '0');
        printStr(", ");
        printStr("DS1=");
        putChar(PORTBbits.RB1 + '0');
        printStr(", ");
        printStr("DS0=");
        putChar(PORTBbits.RB0 + '0');
        putChar('.');
        putChar('\r');
        delay(1223 - ADC1BUF0);
    }
    return 0;
}

void delay(int ms) {
    resetCoreTimer();
    int timer = readCoreTimer();
    while (timer < ms * 20000) {
        timer = readCoreTimer();
    }
}
