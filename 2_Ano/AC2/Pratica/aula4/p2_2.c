#include <detpic32.h>
void delay(int ms);

int main(void)
{
    int i;
    LATB = LATB & 0x80FF; // LATE[8] A LATE[14] reset 0111.1111.0000.0000
    LATDbits.LATD5 = 1;
    LATDbits.LATD6 = 0;
    TRISB = TRISB & 0x80FF; // TRISE[0] A TRISE[3] SAO SAIDAS
    TRISD = TRISD & 0xFF9F; // LATD[5] A LATD[6] SAO SAIDAS

    unsigned char segment;
    while (1)
    {
        segment = 1;
        for (i = 0; i < 7; i++)
        {
            LATB = (LATB & 0x80FF) | ((unsigned int)(segment)) << 8;
            delay(10);
            segment = segment << 1;
        }

        LATDbits.LATD5 = !LATDbits.LATD5;
        LATDbits.LATD6 = !LATDbits.LATD6;
    }
    return 0;
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
