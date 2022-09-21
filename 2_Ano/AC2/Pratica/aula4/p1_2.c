#include <detpic32.h>

void delay(int ms);

int main(void)
{

    LATE = LATE & 0xFF00;   // LATE[0] A LATE[3] reset
    TRISE = TRISE & 0xFF00; // TRISE[0] A TRISE[3] SAO SAIDAS
    int count = 0;

    while (1)
    {
        delay(100); // delay
        LATE = (LATE & 0xFF00) | count;

        if (count < 255)
        {
            count++;
        }
        else
        {
            count = 0;
        }
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
