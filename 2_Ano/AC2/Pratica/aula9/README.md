queremos um sinal com uma frequência de 10 HZ e um duty-cycle de 20%

fazemos a cena da ultima aula dos timers para 10hz, ficamos com Kprescaler = 32(no minimo), logo foutprescaler = 20e6/32 = 625k e PR2 = 62499

agora calculamos o valor da constante a colocar no OC1RS

tON = 20% * (1/fout), ou 0,2 * 1/10 = 0,02 ou 20 ms

como foutprescaler = 625k, Tourprescaler = 1/625k = 1.6 us

entao OC1RS vai ser configurado assim: 20ms / 1.6 us = 12500

alternativamente podemos só multiplicar PR2+1 pelo duty cycle, (62499+1) * 0,2 = 12500

para calcular a resoluçao é log2(foutprescaler/fout) ou log2(625k/10) = 15,93 ~ 15 bits
