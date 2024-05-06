#include <stdlib.h>
#include <stdio.h>

int main(){
    //definir variaveis
    int n = 0;
    double m = 0;
    //abrir arquivo para escrever
    FILE *arquivo;
    arquivo = fopen("arquivo.txt", "w");
    //receber input
    scanf("%d", &n);
    //receber n inputs
    
    for (int i = 0; i < n; i++)
    {
        scanf("%lf", &m);
        fprintf(arquivo, "%lf\n", m);
    }
    fclose(arquivo);
    //abrir arquivo para ler
    FILE *ler;
    ler = fopen("arquivo.txt", "r");
    long int floatsize = sizeof(float);
    //pegar o final
    fseek(ler, -floatsize, SEEK_END);
    long pos = ftell(ler);
    while (n > 1)
    {
        while(pos)
        {
            fseek(ler, --pos, SEEK_SET);
            if(fgetc(ler) == '\n')
            {
                if(pos != ftell(ler))
                {
                    break;
                }
            }
        }
        fscanf(ler, "%lf\n", &m);
        printf("%g\n", m);
        n--;
    }
    
    fclose(ler);

    return 0;
}