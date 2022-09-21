#include <detpic32.h>

void delay(int ms);

int main(void)
{

    LATCbits.LATC14 = 0;   // Output value as 0
    TRISCbits.TRISC14 = 0; // Configure port as output

    while (1)
    {
        delay(500);                         // delay
        LATCbits.LATC14 = !LATCbits.LATC14; // Toggle RC14 port value
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
