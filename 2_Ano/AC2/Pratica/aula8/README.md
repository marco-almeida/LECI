**Timers**

quero um timer de 10hz. logo fout = 10

primeiro calculo o valor do prescaler (é o valor que inicialmente vai dividir o pbclk)

Kprescaler = PBCLK / ((65535 + 1) * fout)

substituindo fout por 10, vai dar 30.51; isto quer dizer que o valor para usar nos prescalers vai ter que ser no minimo 30.51; logo pode se usar 32,64 ou 256. vamos usar 32.

Kprescaler = 32;

depois calcula-se o valor de PRx(o próprio valor armazenado no timer(16 bits)):

PRx = (PBCLK / Kprescaler / fout) - 1

com pbclk = 20e6, Kprescaler = 32, fout = 10, vai dar 62499.

Metemos o timer a contar 1 vez, a cada 32 clocks da pbclk, até 62499. isto produz uma frequencia de 10hz