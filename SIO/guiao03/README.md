# Symmetric Cryptography

## File encryption

### **AES**

É uma **cifra simetrica por blocos**. **Necessita de padding de 128, 192, ou 256 bits na key e 128 bit nos dados**.
É preciso meter padding na key (depois de ser) encoded em binario e padding nos dados (tambem depois de encoded).
É preciso utilizar um **Initializing Vector de 16 bytes**
Assim que dermos decrypt, vao aparecer os dados iniciais (encoded em binario), mas se nao forem multiplos de 128-bit, tambem ira aparecer padding.
É possivel tirar o padding enquanto ainda estao os dados encoded utilizando o `unpadder()`

### **ChaCha20**

É uma **cifra simetrica continua(stream)**. **Necessita de padding 256 bits na key**.
É preciso meter padding na key (depois de ser) encoded em binario.
É preciso utilizar um **nonce de 12 bytes**
Assim que dermos decrypt, vao aparecer os dados iniciais (encoded em binario).

## File decryption

Para dar decrypt é preciso a key e é preciso o iv/nonce acho eu

## Cipher Modes

### Initializing Vector

falta fazer a partir deste como deve ser, mais vale fazer logo todas as ciphers de uma vez em vez de tar a pedir, e pa manter o iv, comenta-se o gerador no encrypt.py

- What length should the IV be?

        16B
- For each cipher mode, what is the impact of repeating the IV, while changing the Key?
- For each cipher mode, what is the impact of repeating the Key, while changing the IV?

### Patterns

da mt trabalho

- What do you conclude from the experiment?

        Different cipher modes have different results

- Can we have an insecure AES cryptogram?

        acho que sim, ecb deixa padroes nas imagens e cbc já é mais complexo/random

### Cryptogram corruption

                Depende mt do cipher mode. Uma, se corromper um bloco, todos os blocos para a frente ficam corrompidos uma vez que dependem da decifraçao do bloco anterior. Outro, se corromper um bloco, fica so esse bloco corrompido e os outros blocos sao decifrados bem, desde que sao independentes.

### Padding

- Is padding required for all ciphers modes?

        CTR, OFB, CFB, GCM nao precisam. ECB e CBC precisam
- What is the impact of padding when selecting the cryptographic primitive?

        num sei
