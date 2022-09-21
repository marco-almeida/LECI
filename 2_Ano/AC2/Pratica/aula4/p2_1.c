#include <detpic32.h>

int main(void)
{

    LATB = LATB & 0x80FF; // LATE[8] A LATE[14] reset 0111.1111.0000.0000
    LATDbits.LATD5 = 0;
    LATDbits.LATD6 = 1;
    TRISB = TRISB & 0x80FF; // TRISE[0] A TRISE[3] SAO SAIDAS
    TRISD = TRISD & 0xFF9F; // LATD[5] A LATD[6] SAO SAIDAS

    char c;
    while (1)
    {
        c = getChar();
        if (c >= 'a' && c <= 'g')
        {
            c = c - 0x0020;
        }
        if (c >= 'A' && c <= 'G')
        {
            c -= 'A'; // Get the index of the leter A->0, G->6
            int val = (0x100 << c);
            LATB = (LATB & 0x80FF) | val;
        }
        else
        {
            LATB = LATB & 0x80FF;
        }
    }
    return 0;
}
