#include <detpic32.h>
void send2displays(unsigned char value);
void delay(int ms);
unsigned char toBcd(unsigned char value);
int main(void)

{
    TRISB = (TRISB & 0x80FF) | 0x0001; // TRISB[8] A TRISB[14] SAO SAIDAS TRISB[0] ENTRADA
    TRISD = TRISD & 0xFF9F;            // displays high e low sao saidas
    TRISE = TRISE & 0xFF00;            // TRISE[7] A TRISE[0] SAO SAIDAS

    LATDbits.LATD5 = 0; // display high desligado
    LATDbits.LATD6 = 0; // display low desligado
    int counter = 0;
    int displayCounter = 0;
    while (1)
    {
        // se ascendente

        if (PORTBbits.RB0 == 1)
        {
            if (counter % 20 == 0 && counter != 0)
            {
                displayCounter++;
                if (displayCounter >= 60)
                {
                    displayCounter = 0;
                }
            }
        }
        else // se descendente
        {
            if (counter % 50 == 0 && counter != 0)
            {
                displayCounter--;
                if (displayCounter < 0)
                {
                    displayCounter = 59;
                }
            }
        }
        send2displays(displayCounter);
        LATE = (LATE & 0xFF00) | toBcd(displayCounter);
        counter++;
        delay(10);
    }
    return 0;
}
void send2displays(unsigned char value)
{
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    static char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if (displayFlag == 0)
    {
        LATDbits.LATD6 = 1;
        LATDbits.LATD5 = 0;
        LATB = (LATB & 0x80FF) | ((unsigned int)(dh)) << 8; // Clean the display and set the right value
    }
    else
    {
        LATDbits.LATD6 = 0;
        LATDbits.LATD5 = 1;
        LATB = (LATB & 0x80FF) | ((unsigned int)(dl)) << 8; // Clean the display and set the right value
    }
    displayFlag = !displayFlag;
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

unsigned char toBcd(unsigned char value)
{
    return ((value / 10) << 4) + (value % 10);
}
