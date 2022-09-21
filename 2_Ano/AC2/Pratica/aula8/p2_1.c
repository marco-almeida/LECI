#include <detpic32.h>
void delay(int ms);

int main(void) {
    TRISEbits.TRISE0 = 0;
    TRISDbits.TRISD8 = 1;

    while (1) {
        while (PORTDbits.RD8 == 1)
            ;
        LATEbits.LATE0 = 1; 
        delay(3000);
        LATEbits.LATE0 = 0; 
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
