#include <detpic32.h>

int main(void)
{
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    LATB = LATB & 0x80FF; // LATB[8] A LATB[14] reset
    LATDbits.LATD5 = 0;
    LATDbits.LATD6 = 1;
    TRISB = (TRISB & 0x80FF) | 0x000F; // TRISB[8] A TRISB[14] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;            // TRISD[5] A TRISD[6] SAO SAIDAS

    while (1)
    {
        LATB = (LATB & 0x80FF) | (display7Scodes[PORTB & 0x000F] << 8);
    }
    return 0;
}
