**UART**
Tem que se configurar 4 coisas, a UxBRG, a paridade, os stop bits e o BRGH
para obter UxBRG, divide se PBCLK por BRGH(4 ou 16) e pela baudrate e depois faz se - 1... arredonda se para o inteiro mais perto
a paridade nsei em concreto
UxMODEbits.STSEL = 0 se 1 stop bit, ou = 1 se 2 stop bits
U2MODEbits.BRGH = 0 para dividir por 16 ou = 1 para 4
Depois fazem se os enables
    U2STAbits.URXEN = 1;      // Enable Receiver
    U2STAbits.UTXEN = 1;      // Enable Transmitter
    U2MODEbits.ON = 1;        // Enable UART2
nao esquecer o pterm baudrate,paridade,8,stopbits

fBRG = PBCLK / (UxBRG + 1)
depois isto é dividido por 4 ou 16 e temos a baudrate
escolhe-se se é 4 ou 16 no BRGH(UxMODE<3>) [4 = 1, 16 = 0]

UxBRG = (fPBCLK / (16 * baudrate)) – 1 
 
Ou  ainda,  e  de  modo  a  evitar  o  erro  de  truncatura  que  se  terá  ao  realizar  as  operações  com 
inteiros:  
UxBRG = ((fPBCLK + 8 * baudrate) / (16 * baudrate)) – 1

BRG  é  comum  aos  módulos  de  transmissão  e  receção,  pelo  que  a  taxa  de 
transmissão é igual à taxa de receção