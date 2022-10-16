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
