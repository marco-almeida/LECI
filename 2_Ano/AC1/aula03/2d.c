#include <stdlib.h>
#include <stdio.h>

int main(int argc, char* argv[]) 
{
    int idade;
    char nome[30];
    printf("Escreva o seu nome:");
    scanf("%[^\n]", &nome);
    printf("Insira a sua idade:");
    scanf("%d", idade);
    setvbuf (stdout, NULL, _IONBF, 0);
    printf("O seu nome e: %s e tem: %d anos\n", nome, idade);
    system("pause");
    return 0;
    
}   