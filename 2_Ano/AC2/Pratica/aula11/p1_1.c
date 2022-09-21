#include <detpic32.h>
void putc(char byte2send);
int main(void) {
    // Configure UART2: 115200, N, 8, 1
    U2BRG = 10;               // U2BRG = (20Mhz / (16*115200))-1 ~ 10
    U2MODEbits.PDSEL = 0b00;  // 00 = 8-bit data, no parity
    U2MODEbits.STSEL = 0;     // only 1 stop bit
    U2MODEbits.BRGH = 0;      // divide by 16

    U2STAbits.URXEN = 1;       // Enable Receiver
    U2STAbits.UTXEN = 1;       // Enable Transmitter
    U2STAbits.URXISEL = 0b00;  // Select interrupt only for receiver
    IPC8bits.U2IP = 1;         // UART2 Priority
    IEC1bits.U2RXIE = 1;       // U2RX Interrupt Enable
    IEC1bits.U2TXIE = 0;       // U2RX Interrupt Disable
    IFS1bits.U2RXIF = 0;       // U2RX Interrupt Flag

    U2MODEbits.ON = 1;  // Enable UART2
    // Enable global Interrupts
    EnableInterrupts();
    while (1)
        ;
    return 0;
}

void _int_(32) isr_uart2(void) {
    if (IFS1bits.U2RXIF == 1) {
        char byte = U2RXREG;  // Read character from FIFO (U2RXREG)
        putc(byte);           // Send the character using putc()
        IFS1bits.U2RXIF = 0;  // Clear UART2 Rx interrupt flag
    }
}

void putc(char byte2send) {
    while (U2STAbits.UTXBF == 1)
        ;
    U2TXREG = byte2send;
}
