#include <detpic32.h>
#include </home/marco/Desktop/AC2/aula11/buffer.h>
void putstrInt(char *s);

volatile t_buf txbuf;
int main(void) {
    // Configure UART2: 115200, N, 8, 1
    U2BRG = 10;               // U2BRG = (20Mhz / (16*115200))-1 ~ 10
    U2MODEbits.PDSEL = 0b00;  // 00 = 8-bit data, no parity
    U2MODEbits.STSEL = 0;     // only 1 stop bit
    U2MODEbits.BRGH = 0;      // divide by 16

    U2STAbits.URXEN = 1;       // Enable Receiver
    U2STAbits.UTXEN = 1;       // Enable Transmitter
    U2STAbits.UTXISEL = 0b00;  // Select interrupt only for receiver
    IPC8bits.U2IP = 1;         // UART2 Priority
    IEC1bits.U2RXIE = 0;       // U2RX Interrupt Enable
    IEC1bits.U2TXIE = 0;       // U2TX Interrupt Disable
    IFS1bits.U2RXIF = 0;       // U2RX Interrupt Flag
    TRISCbits.TRISC14 = 0;     // saida
    U2MODEbits.ON = 1;         // Enable UART2
    // Enable global Interrupts
    txbuf.nchar = 0;
    EnableInterrupts();
    while (1) {
        putstrInt(
            "Test string which can be as long as you like as long as it is no "
            "longer than 100 characters\n");
    }
    return 0;
}

void putstrInt(char *s) {
    while (txbuf.nchar > 0);
    // Wait while the buffer is not empty
    // Copy all characters of the string "s" to the buffer
    int i;
    for (i = 0; s[i] != '\0'; i++) {
        txbuf.mem[i] = s[i];
        txbuf.nchar++;
    }
    // Initialize "posrd" variable with 0
    txbuf.posrd = 0;
    // Enable UART2 Tx interrupts
    IEC1bits.U2TXIE = 1;  // U2TX Interrupt Enable
}

void _int_(32) isr_uart2(void) {
    if (IFS1bits.U2TXIF == 1) {
        if (txbuf.nchar > 0) {  // At least one character to be transmitted
            // U2TXREG = ... // Read 1 character from the buffer and
            U2TXREG = txbuf.mem[txbuf.posrd];
            // Update buffer "posrd" and "nchar" variables
            txbuf.posrd++;
            txbuf.nchar--;
        } else {
            // Disable UART2 Tx interrupts
            IEC1bits.U2TXIE = 0;
        }
        // Clear UART2 Tx interrupt flag
        IFS1bits.U2TXIF = 0;  // U2TX Interrupt Flag
    }
}
