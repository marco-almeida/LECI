#include <detpic32.h>
void send2displays(unsigned char value);
void delay(int ms);

int main(void)
{

    TRISB = TRISB & 0x80FF; // TRISB[8] A TRISB[14] SAO SAIDAS       1111.1111.1111.1111 <- PORTO B
    TRISD = TRISD & 0xFF9F;         // Displays high/low as output
    LATDbits.LATD5 = 0;
    LATDbits.LATD6 = 0;
    
    while (1)
    {
        send2displays(0x15);
        delay(200);
    }
    return 0;
}
void send2displays(unsigned char value)
{
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    LATDbits.LATD6 = 0;     
    LATDbits.LATD5 = 1;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];
    LATB = (LATB & 0x80FF) | ((unsigned int)(dl)) << 8; // Clean the display and set the right value
    delay(2); 
    LATDbits.LATD6 = 1;
    LATDbits.LATD5 = 0; 
    LATB = (LATB & 0x80FF) | ((unsigned int)(dh)) << 8; // Clean the display and set the right value
}

void delay(int ms)
{
    resetCoreTimer();
    int timer = readCoreTimer();
    while (timer < ms * 20000)
    {
        timer = readCoreTimer();
    }
}
