#include <detpic32.h>
void delay(int ms);
int main(void) {
    int counter = 0;
    while (1) {
        if (counter == 100) {
            counter = 0;
        }
        printInt(counter++, 10 | 2 << 16);
        delay(100);
        putChar('\r');
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
