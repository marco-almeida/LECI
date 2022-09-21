#include <detpic32.h>
void delay(int ms);
void send2displays(unsigned char value);
int main(void) {
    TRISE = TRISE & 0xFFF0;
    LATE = LATE & 0XFFF0;
    TRISB = TRISB & 0x80FF;  // TRISB[8] A TRISB[14] SAO SAIDAS
    TRISD = TRISD & 0xFF9F;  // displays high e low sao saidas
    LATDbits.LATD5 = 0;      // display high desligado
    LATDbits.LATD6 = 0;      // display low desligado
    int i = 0;
    char c;
    unsigned char display = 0;
    while (1) {
        c = getChar();
        switch (c) {
            case '0':
                LATE = (LATE & 0XFFF0) | 0X0001;
                display = 0x00;
                break;
            case '1':
                LATE = (LATE & 0XFFF0) | 0X0002;
                display = 0x01;
                break;
            case '2':
                LATE = (LATE & 0XFFF0) | 0X0004;
                display = 0x02;
                break;
            case '3':
                LATE = (LATE & 0XFFF0) | 0X0008;
                display = 0x03;
                break;
            default:
                LATE = LATE | 0X000F;
                display = 0xFF;
                break;
        }
        delay(10);
        send2displays(display);
        send2displays(display);
        if (display == 0xFF) {
            for (i = 0; i < 100; i++) {
                delay(10);
                send2displays(0xFF);
            }
            LATDbits.LATD5 = 0;  // display high desligado
            LATDbits.LATD6 = 0;  // display low desligado
            LATE = LATE & 0XFFF0;
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
