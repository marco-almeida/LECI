#include <detpic32.h>

int main(void) {
    TRISB = TRISB | 0x000F;
    TRISE = TRISE & 0xFF00;
    while (1) {
        LATEbits.LATE4 = PORTBbits.RB3;
        LATEbits.LATE5 = PORTBbits.RB2;
        LATEbits.LATE6 = PORTBbits.RB1;
        LATEbits.LATE7 = PORTBbits.RB0;
        // LATEbits.LATE3 = ~PORTBbits.RB3;
        // LATEbits.LATE2 = ~PORTBbits.RB2;
        // LATEbits.LATE1 = ~PORTBbits.RB1;
        // LATEbits.LATE0 = ~PORTBbits.RB0;
        LATE = ~PORTB;
    }
    return 0;
}
