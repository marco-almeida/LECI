#include <detpic32.h>
void delay(int ms);
int main(void) {
    TRISB = TRISB | 0x000F;
    while (1) {
        printStr("DS3=");

        putChar(PORTBbits.RB3 + '0');
        printStr(", ");
        printStr("DS2=");
        putChar(PORTBbits.RB2 + '0');
        printStr(", ");
        printStr("DS1=");
        putChar(PORTBbits.RB1 + '0');
        printStr(", ");
        printStr("DS0=");
        putChar(PORTBbits.RB0 + '0');
        putChar('.');
        putChar('\r');
        delay(1000);
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
