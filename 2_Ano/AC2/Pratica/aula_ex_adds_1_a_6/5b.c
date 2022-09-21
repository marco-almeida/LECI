#include <detpic32.h>
void delay(int ms);

int main(void) {
    unsigned char counter = 0;
    unsigned char tempo = 0;
    double periodo = 500;
    char c;
    char ia = 0;
    TRISB = TRISB & 0x80FF;  // TRISB[8] A TRISB[14] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;  // displays high e low sao saidas
    LATDbits.LATD5 = 0;      // display high desligado
    LATDbits.LATD6 = 0;      // display low desligado

    while (1) {
        delay(20);
        tempo++;
        if (tempo * 20 >= periodo) {
            counter++;
            tempo = 0;
        }
        c = inkey();
        if (counter == 100) {
            counter = 0;
        }
        printInt(counter, 10 | 2 << 16);

        if (c >= '0' && c <= '4') {
            ia = c - '0';
            periodo = 1000 / (2 * (1 + ia));
        } else if (c == 10) {
            printStr(", ");
            printf("%3.1f", (double)1 / periodo * 1000);
            printStr(" Hz ");
        }
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
