#include <stdio.h>
int main(void)
{
    unsigned int value, bit, i;

    printf("Introduza um numero: ");
    scanf("%u", &value);
    printf("\nO valor em binario: ");

    for (i = 0; i < 32; i++)
    {
        bit = value & 0x80000000; // isola bit 31
        if((i % 4) == 0) // resto da divisão inteira
            printf(" ");
        if (bit != 0)
            printf("1");
        else
            printf("0");
        value = value << 1; // shift left de 1 bit
    }
    return 0;
}