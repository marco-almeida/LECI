#include <detpic32.h>
void delay(int ms);

int main(void)
{
    int i;
    LATB = LATB & 0x80FF;            // LATE[8] A LATE[14] reset 0111.1111.0000.0000
    LATD = (LATD | 0x0040) & 0xFFDF; // RD[6] = 1 RD[5] = 0    .7654.3210
    TRISB = TRISB & 0x80FF;          // TRISE[0] A TRISE[3] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;          // LATD[5] A LATD[6] SAO SAIDAS

    unsigned char segment;
    while (1)
    {
        segment = 1;
        for (i = 0; i < 7; i++)
        {
            LATB = (LATB & 0x80FF) | ((unsigned int)(segment)) << 8;
            delay(500);
            segment = segment << 1;
        }

        LATD = LATD ^ 0x0060;
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
