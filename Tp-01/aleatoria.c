#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>

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

char *bagunca(char *chars, char pri, char sec, int tam, int i)
{
    // verificar
    if (i == tam)
    {
        return chars;
    }
    // bagunçar
    if (chars[i] == pri)
    {
        chars[i] = sec;
    }
    // final
    return bagunca(chars, pri, sec, tam, (i + 1));
}

int main()
{
    // definir dados
    char *input = (char *)malloc(1001 * sizeof(char));
    char *resp = (char *)malloc(1001 * sizeof(char));
    char pri;
    char sec;
    int fim = 0;
    // gerador
    srand(time(NULL));
    // ler primeiro input
    scanf("%[^\n]%*c", input);
    int tam = strlen(input) - 1;
    fim = final(input);
    // rodar enquanto nao for 'FIM'
    while (fim == 0)
    {
        pri = ((char)('a' + rand() % 26));
        sec = ((char)('a' + rand() % 26));
        resp = bagunca(input, pri, sec, tam, 0);
        printf("%s\n", resp);
        scanf("%[^\n]%*c", input);
        tam = strlen(input) - 1;
        fim = final(input);
    }
    return 0;
}