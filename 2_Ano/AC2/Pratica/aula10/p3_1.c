#include <detpic32.h>
void putc(char byte2send);
void puts(char *str);
void delay(int ms);
int main(void) {
    // Configure UART2 (115200, N, 8, 1)
    U2BRG = 10;               // U2BRG = (20Mhz / (16*115200))-1 ~ 10
    U2MODEbits.PDSEL = 0b00;  // 00 = 8-bit data, no parity
    U2MODEbits.STSEL = 0;     // only 1 stop bit
    U2MODEbits.BRGH = 0;      // divide by 16
    U2STAbits.URXEN = 1;      // Enable Receiver
    U2STAbits.UTXEN = 1;      // Enable Transmitter
    U2MODEbits.ON = 1;        // Enable UART2
    // config RD11 as output
    TRISDbits.TRISD11 = 0;
    int time;
    while (1) {
        while (U2STAbits.TRMT == 0)
            ;
        resetCoreTimer();
        LATDbits.LATD11 = 1;
        puts("123456789AB\n");
        time = 50 * readCoreTimer();
        printInt10(time);
        putChar('\n');
        delay(500);
        LATDbits.LATD11 = 0;
    }
    return 0;
}

void puts(char *str) {
    while (*str != '\0') {
        putc(*str);
        str++;
    }
}

void putc(char byte2send) {
    while (U2STAbits.UTXBF == 1)
        ;
    U2TXREG = byte2send;
}

void delay(int ms) {
    for (; ms > 0; ms--) {
        resetCoreTimer();
        while (readCoreTimer() < 20000)
            ;
    }
}
