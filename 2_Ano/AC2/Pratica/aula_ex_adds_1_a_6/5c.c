#include <detpic32.h>
void delay(int ms);
unsigned char toBcd(unsigned char value);
void send2displays(unsigned char value);

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
    unsigned char lastSeen = 0;

    while (1) {
        send2displays(toBcd(lastSeen));
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
            printStr("Hz ");
            lastSeen = counter;
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

void send2displays(unsigned char value) {
    static const char display7Scodes[] = {0x3F, 0x06, 0x5B, 0x4F, 0x66, 0x6D,
                                          0x7D, 0x07, 0x7F, 0x6F, 0x77, 0x7C,
                                          0x39, 0x5E, 0x79, 0x71};
    static char displayFlag = 0;
    unsigned char dh = display7Scodes[value >> 4];
    unsigned char dl = display7Scodes[value & 0x0F];

    if (displayFlag == 0) {
        LATDbits.LATD6 = 1;
        LATDbits.LATD5 = 0;
        LATB = (LATB & 0x80FF) |
               ((unsigned int)(dh))
                   << 8;  // Clean the display and set the right value
    } else {
        LATDbits.LATD6 = 0;
        LATDbits.LATD5 = 1;
        LATB = (LATB & 0x80FF) |
               ((unsigned int)(dl))
                   << 8;  // Clean the display and set the right value
    }
    displayFlag = !displayFlag;
}

unsigned char toBcd(unsigned char value) {
    return ((value / 10) << 4) + (value % 10);
}
