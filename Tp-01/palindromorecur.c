#include <stdio.h>
#include <string.h>
#include <ctype.h>

int final(char str[])
{
    int tam = strlen(str);
    if (tam == 3)
    {
        if (str[0] == 'F')
        {
            if (str[1] == 'I')
            {
                if (str[2] == 'M')
                {
                    return 1;
                }
            }
        }
    }
    return 0;
}
//versão recursiva
int isPalindrome(char str[], int tam, int i)
{
        if (str[i] != str[tam])
        {
            return 0;
        }
        if(i == tam)
        {
            return 1;
        }
    return isPalindrome(str, (tam-1), (i+1));
}

int main()
{
    // definir dados
    char *str = (char *)malloc(1000 * sizeof(char));
    int resp = 0;
    int result = 0;
    // receber primeiro input
    scanf("%*[^\n]%*c", str);
    //pegar primeiro tamanho
    int tam = strlen(str) - 1;
    resp = final(str);
    // rodar enquanto não for 'FIM'
    while (resp != 1)
    {
        result = isPalindrome(str, tam, 0);
        if (result == 1)
        {
            printf("SIM");
            printf("\n");
        }
        else
        {
            printf("NAO");
            printf("\n");
        }
        scanf(" %[^\r\n]%*c", str);
        int tam = strlen(str) - 1;
        resp = final(str);
    }
    free(str);
    return 0;
}