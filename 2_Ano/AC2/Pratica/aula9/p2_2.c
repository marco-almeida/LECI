#include <detpic32.h>
void setPWM(unsigned int dutyCycle);
int main(void) {
    T3CONbits.TCKPS = 2;  // 1:4 prescaler (i.e Fout_presc = 5 MHz)
    PR3 = 49999;          //
    TMR3 = 0;             // Reset timer T3 count register
    T3CONbits.TON = 1;    // Enable timer T3 (must be the last command of the
                          //  timer configuration sequence)
    IPC3bits.T3IP = 3;    // Interrupt priority 2
    IEC0bits.T3IE = 1;    // Enable timer T3 interrupts
    IFS0bits.T3IF = 0;    // Reset timer T3 interrupt flag

    OC1CONbits.OCM = 6;     // PWM mode on OCx; fault pin disabled
    OC1CONbits.OCTSEL = 1;  // Use timer T3 as the time base for PWM generation
    OC1RS = 12500;          // Ton constant (PR3+1) * 0,25 do duty cycle
    OC1CONbits.ON = 1;      // Enable OC1 module

    EnableInterrupts();

    while (1)
        ;
    return 0;
}

void _int_(12) isr_T3(void) { IFS0bits.T3IF = 0; }

void setPWM(unsigned int dutyCycle) {
    // duty_cycle must be in the range [0, 100]
    if (dutyCycle >= 0 && dutyCycle <= 100) {
        OC1RS = (PR3 + 1) * dutyCycle / 100;
    }   
}