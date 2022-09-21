#include <detpic32.h>
void delay(int ms);
void putc1(char byte2send);
int main(void) {
    // Configure UART2
    U2BRG = 10;               // U2BRG = (20Mhz / (16*115200))-1 ~ 10
    U2MODEbits.PDSEL = 0b00;  // 00 = 8-bit data, no parity
    U2MODEbits.STSEL = 0;     // only 1 stop bit
    U2MODEbits.BRGH = 0;      // divide by 16
    U2STAbits.URXEN = 1;      // Enable Receiver
    U2STAbits.UTXEN = 1;      // Enable Transmitter
    U2MODEbits.ON = 1;        // Enable UART2

    while (1) {
        putc1(0x5A);
        delay(10); // ver osciloscopio OC4
    }
    return 0;
}

void delay(int ms) {
    for (; ms > 0; ms--) {
        resetCoreTimer();
        while (readCoreTimer() < 20000)
            ;
    }
}

void putc1(char byte2send) {
    while (U1STAbits.UTXBF == 1)
        ;  // Wait until the Transmister Buffer is Full
    U1TXREG = byte2send;
}
