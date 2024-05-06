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

int isPalindrome(char str[])
{
    int tam = strlen(str) - 1;
    for (int i = 0; i <= tam; i++)
    {
        if (str[i] != str[tam])
        {
            return 0;
        }
        tam--;
    }
    return 1;
}

int main()
{
    // definir dados
    //char str[1000];
    char *str = (char *)malloc(1000 * sizeof(char));
    int resp = 0;
    int result = 0;
    // receber primeiro input
    scanf("%*[^\n]%*c", str);
    resp = final(str);
    // rodar enquanto não for 'FIM'
    while (resp != 1)
    {
        result = isPalindrome(str);
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
        resp = final(str);
    }
    free(str);
    return 0;
}