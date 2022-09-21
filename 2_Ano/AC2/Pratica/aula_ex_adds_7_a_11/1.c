#include <detpic32.h>
void send2displays(unsigned char value);
unsigned char digitos;
void delay(int ms);
int main(void) {
    TRISB = TRISB & 0x80FF;
    TRISD = TRISD & 0xFF9F;
    TRISE = TRISE & 0xFFF0;

    T2CONbits.TCKPS = 2;  // 1:4 prescaler 100hz
    PR2 = 49999;          //
    TMR2 = 0;             // Reset timer T2 count register
    T2CONbits.TON = 1;    // Enable timer T2

    IPC2bits.T2IP = 2;  // Interrupt priority 2
    IEC0bits.T2IE = 0;  // Enable timer T2 interrupts
    IFS0bits.T2IF = 0;  // Reset timer T2 interrupt flag
    LATE = LATE & 0xFFF0;
    LATDbits.LATD5 = 0;
    LATDbits.LATD6 = 0;
    EnableInterrupts();
    while (1) {
        char c = inkey();
        if (c == 0) {
            continue;
        }
        switch (c) {
            case '0':     
                LATE = (LATE & 0xFFF0) | 0x0001;
                digitos = 0;
                IEC0bits.T2IE = 1;
                break;
            case '1':
                LATE = (LATE & 0xFFF0) | 0x0002;
                digitos = 1;
                IEC0bits.T2IE = 1;
                break;
            case '2':
                LATE = (LATE & 0xFFF0) | 0x0004;
                digitos = 2;
                IEC0bits.T2IE = 1;
                break;
            case '3':
                LATE = (LATE & 0xFFF0) | 0x0008;
                digitos = 3;
                IEC0bits.T2IE = 1;
                break;
            default:
                LATE = (LATE & 0xFFF0) | 0x000F;
                IEC0bits.T2IE = 1;
                digitos = 0xFF;
                delay(1000);
                LATE = LATE & 0xFFF0;
                LATDbits.LATD5 = 0;
                LATDbits.LATD6 = 0;
                IEC0bits.T2IE = 0;
                break;
        }
    }
    return 0;
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

void _int_(8) isr_T2(void) {  // 0,01 sec
    send2displays(digitos);
    IFS0bits.T2IF = 0;  // reset flag
}

void delay(int ms) {
    resetCoreTimer();
    int timer = readCoreTimer();
    while (timer < ms * 20000) {
        timer = readCoreTimer();
    }
}
