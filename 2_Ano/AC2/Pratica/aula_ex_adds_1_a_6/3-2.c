#include <detpic32.h>
void delay(int ms);
int main(void) {
    TRISE = TRISE & 0xFFF0;
    char c;
    LATE = LATE & 0XFFF0;
    while (1) {
        c = getChar();
        switch (c) {
            case '0':
                LATE = (LATE & 0XFFF0) | 0X0001;
                break;
            case '1':
                LATE = (LATE & 0XFFF0) | 0X0002;
                break;
            case '2':
                LATE = (LATE & 0XFFF0) | 0X0004;
                break;
            case '3':
                LATE = (LATE & 0XFFF0) | 0X0008;
                break;
            default:
                LATE = LATE | 0X000F;
                delay(1000);
                LATE = LATE & 0XFFF0;
                break;
        }
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
