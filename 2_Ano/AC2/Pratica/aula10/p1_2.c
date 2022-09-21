#include <detpic32.h>
void delay(int ms);
void putc(char byte2send);
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
        putc('+');
        delay(1000);
    }
    return 0;
}

void putc(char byte2send) { 
    while(U2STAbits.UTXBF == 1);
    U2TXREG = byte2send;
} 

void delay(int ms) {
    resetCoreTimer();
    int timer = readCoreTimer();
    while (timer < ms * 20000) {
        timer = readCoreTimer();
    }
}
