#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <errno.h>
#include <ctype.h>
#include <time.h>

// Variáveis globais para registro de comparações, movimentações e tempo de execução
unsigned long long int num_comparacoes = 0;
unsigned long long int num_movimentacoes = 0;
double tempo_execucao = 0.0;

// Função para atualizar o registro de comparações
void atualizarComparacoes(unsigned long long int comparacoes)
{
    num_comparacoes += comparacoes;
}

// Função para atualizar o registro de movimentações
void atualizarMovimentacoes(unsigned long long int movimentacoes)
{
    num_movimentacoes += movimentacoes;
}

// Função para atualizar o registro de tempo de execução
void atualizarTempo(double tempo)
{
    tempo_execucao += tempo;
}

// Função para registrar os valores de comparações e tempo em um arquivo de log
void registrarLog()
{
    FILE *arquivo_log;
    arquivo_log = fopen("815991_heapsort.txt", "a"); // "a" para adicionar ao arquivo existente
    if (arquivo_log == NULL)
    {
        printf("Erro ao abrir o arquivo de log.\n");
        return;
    }
    fprintf(arquivo_log, "815991 ");
    fprintf(arquivo_log, "Número de comparações: %llu ", num_comparacoes);
    fprintf(arquivo_log, "Número de movimentações: %llu ", num_movimentacoes);
    fprintf(arquivo_log, "Tempo de execução: %.6f segundos ", tempo_execucao);
    fclose(arquivo_log);
}

//------------------------------------------------------------------------------//

// Função para limpar o registro de comparações e tempo de execução
void limparRegistros()
{
    num_comparacoes = 0;
    tempo_execucao = 0.0;
}

// define para melhorar uso de string
typedef char *String;

typedef struct Lista
{
    String *elemento;
    int tamanho;
} Lista;

void iniciar(Lista *lista, size_t tamanho)
{
    if (tamanho > 0)
    {
        lista->elemento = (String *)malloc(tamanho * sizeof(String));
        for (size_t i = 0; i < tamanho; i++)
        {
            lista->elemento[i] = (String)malloc(500 * sizeof(char));
        }
    }
    lista->tamanho = 0;
}

void inserirfim(String elemento, Lista *lista, int tamanho)
{
    if (lista->tamanho >= tamanho)
    {
        printf("ERRO NO IMSERIR");
        exit(1);
    }
    strcpy(lista->elemento[lista->tamanho], elemento);
    lista->tamanho++;
}

void mostrar(Lista *lista)
{
    for (int i = 0; i < lista->tamanho; i++)
    {
        printf("%s", lista->elemento[i]);
    }
}

void freelist(Lista *lista)
{
    free(lista->elemento);
    lista->tamanho = 0;
}

// struct de personagem
typedef struct
{
    char id[40];                // 0
    char name[80];              // 1
    Lista *alternate_names;     // 2
    char house[50];             // 3
    char ancestry[50];          // 4
    char species[50];           // 5
    char patronus[50];          // 6
    bool hogwartsStaff;         // 7
    char hogwartsStudent[50];   // 8
    char actorname[80];         // 9
    bool alive;                 // 10
    Lista *alternatives_actors; // 11
    char dateOfBirth[15];       // 12
    char yearOfBirth[15];       // 13 era pra ser int
    char eyeColour[15];         // 14
    char gender[15];            // 15
    char hairColour[15];        // 16
    bool wirzad;                // 17 era string

} Personagem;

void reset(Personagem *personagem)
{
    personagem->hogwartsStaff = false;
    personagem->alive = false;
    // personagem->yearOfBirth = 0;
    personagem->wirzad = false;
    personagem->alternate_names = NULL;
    personagem->alternatives_actors = NULL;

    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 50);
    personagem->alternatives_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternatives_actors, 50);
}

//--------------------------------------------------------------------//
//--------------------------------Celula------------------------------//

typedef struct Cell
{
    Personagem personagem;
    struct Cell *prox;
} Cell;

Cell *newCell(Personagem personagem)
{
    Cell *new = (Cell *)malloc(sizeof(Cell));
    new->personagem = personagem;
    new->prox = NULL;
    return new;
}

Cell *primeiro;
Cell *ultimo;

void start(Personagem personagem)
{
    primeiro = newCell(personagem);
    ultimo = primeiro;
}

void addend(Personagem personagem)
{
    ultimo->prox = newCell(personagem);
    ultimo = ultimo->prox;
}

int length()
{
    int length = 0;
    Cell *i;
    for (i = primeiro; i != ultimo; i = i->prox, length++)
        ;
    return length;
}

void printCell()
{
    printf("[ ");
    Cell *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        printf("%s ", i->personagem.id);
    }
    printf("] \n");
}

Personagem getElementById(String id)
{
    Cell *i;
    for (i = primeiro->prox; i != NULL; i = i->prox)
    {
        if (strcmp(i->personagem.id, id) == 0)
        {
            return i->personagem;
        }
    }
    return i->personagem;
}

void freeCell()
{
    Cell *aux = primeiro;
    Cell *prox = NULL;
    while (aux != NULL)
    {
        primeiro = aux->prox;
        free(aux);
        aux = prox;
    }
    primeiro = NULL;
    ultimo = NULL;
}

size_t numtam(char regex, String str)
{
    size_t x = 0, y = 0;
    size_t size = strlen(str);
    for (x = 0; x < size; x++)
    {
        if (str[x] == regex)
        {
            y++;
        }
    }
    return y + 1;
}

//--------------------------------------------------------------------//

String *split(char corta, String str)
{
    size_t size = numtam(corta, str);
    String *output = (String *)malloc(size * sizeof(String));
    for (size_t i = 0; i < size; i++)
    {
        output[i] = (String)malloc(200 * sizeof(char));
    }
    int x = 0, y = 0, z = 0;

    while (str[x] != '\0')
    {
        if (str[x] == corta)
        {
            output[y][z] = '\0';

            y++;
            z = 0;
            if ((str[x + 1] == corta) || (str[x + 1] == '\0') || (str[x + 1] == '\n'))
            {
                x++;
                y++;
            }
        }
        else
        {
            if (str[x] != '\n')
            {
                output[y][z] = str[x];
                z++;
            }
        }
        x++;
    }
    return output;
}
//--------------------------------------------------------------------------------//
//------------------------------------Replace-All---------------------------------//
char *replaceAll(const char *oldString, const char *newString, const char *str)
{
    size_t oldLen = strlen(oldString);
    size_t newLen = strlen(newString);
    size_t strLen = strlen(str);
    size_t count = 0;

    const char *tmp = str;
    while ((tmp = strstr(tmp, oldString)))
    {
        count++;
        tmp += oldLen;
    }

    size_t resultlen = strLen + count * (newLen - oldLen) + 1;
    char *result = (char *)malloc(resultlen);

    char *out = result;
    const char *in = str;
    while (*in)
    {
        // trocar oldString por newString em str
        if (strncmp(in, oldString, oldLen) == 0)
        {
            strcpy(out, newString);
            in += oldLen;
            out += newLen;
        }
        else
        {
            *out++ = *in++;
        }
    }
    *out = '\0';
    return result;
}

//--------------------------------------------------------------------------------//
//------------------------------Tirar-Espaços-da-String---------------------------//

char *trimWhiteSpace(char *str)
{
    char *end;
    while (isspace((unsigned char)*str))
    {
        str++;
    }
    if (*str == 0)
    {
        return str;
    }

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end))
    {
        end--;
    }
    end[1] = '\0';

    return str;
}

//--------------------------------------------------------------------------------//
void arrumadata(char *data)
{
    int dia, mes, ano;
    sscanf(data, "%d-%d-%d", &dia, &mes, &ano);
    sprintf(data, "%02d-%02d-%d", dia, mes, ano);
}

// função para atribuir valores
void ler(Personagem *personagem, String line)
{
    String *separado = split(';', line);

    strcpy(personagem->id, separado[0]);
    strcpy(personagem->name, separado[1]);
    // alternative names separado[2]
    personagem->alternate_names = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternate_names, 15);
    String tmp = replaceAll("[", "", separado[2]);
    tmp = replaceAll("]", "", tmp);
    size_t sizeAlterNames = numtam(',', tmp);
    String *alternateNamesArray = split(',', tmp);
    for (size_t i = 0; i < sizeAlterNames; i++)
    {
        String tmpAlt = trimWhiteSpace(alternateNamesArray[i]);
        tmpAlt = replaceAll("'", "", tmpAlt);
        if (tmpAlt != NULL)
        {
            inserirfim(tmpAlt, personagem->alternate_names, sizeAlterNames);
        }
        free(tmpAlt);
    }

    strcpy(personagem->house, separado[3]);
    strcpy(personagem->ancestry, separado[4]);
    strcpy(personagem->species, separado[5]);
    strcpy(personagem->patronus, separado[6]);
    personagem->hogwartsStaff = (separado[7][0] == 'F') ? false : true;
    if (strcmp(separado[8], "FALSO") == 0)
    {
        strcpy(personagem->hogwartsStudent, "false");
    }
    else
    {
        strcpy(personagem->hogwartsStudent, "true");
    }
    strcpy(personagem->actorname, separado[9]);
    personagem->alive = (separado[10][0] == 'F') ? false : true;
    // alternative actors separado[11]
    personagem->alternatives_actors = (Lista *)malloc(sizeof(Lista));
    iniciar(personagem->alternatives_actors, 15);
    String tmp2 = replaceAll("[", "", separado[11]);
    tmp2 = replaceAll("]", "", tmp2);
    size_t sizeAlterActor = numtam(',', tmp2);
    String *alternateActorArray = split(',', tmp2);

    for (size_t i = 0; i < sizeAlterActor; i++)
    {
        String tmp2Alt = (String)malloc(300 * sizeof(char));
        alternateActorArray[i] = trimWhiteSpace(alternateActorArray[i]);
        strcpy(tmp2Alt, alternateActorArray[i]);
        tmp2Alt = replaceAll("'", "", tmp2Alt);
        if (tmp2Alt != NULL)
        {
            inserirfim(tmp2Alt, personagem->alternatives_actors, sizeAlterActor);
        }
        free(tmp2Alt);
    }

    strcpy(personagem->dateOfBirth, separado[12]);
    arrumadata(personagem->dateOfBirth);
    strcpy(personagem->yearOfBirth, separado[13]);
    strcpy(personagem->eyeColour, separado[14]);
    strcpy(personagem->gender, separado[15]);
    strcpy(personagem->hairColour, separado[16]);
    personagem->wirzad = (separado[17][0] == 'F') ? false : true;

    free(tmp);
    free(tmp2);
}
// função imprimir
void imprimir(Personagem personagem)
{
    printf("[%s ## %s ## {", personagem.id, personagem.name);

    // mostrar lista alternative_names
    for (int i = 0; i < personagem.alternate_names->tamanho; i++)
    {
        if (i == (personagem.alternate_names->tamanho - 1))
        {
            printf("%s", personagem.alternate_names->elemento[i]);
        }
        else
        {
            printf("%s, ", personagem.alternate_names->elemento[i]);
        }
    }

    printf("} ## %s ## ", personagem.house);
    printf("%s ## ", personagem.ancestry);
    printf("%s ## ", personagem.species);
    printf("%s ## ", personagem.patronus);
    if (personagem.hogwartsStaff)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    printf("%s ## ", personagem.hogwartsStudent); // hogwartsStudent
    printf("%s ## ", personagem.actorname);
    if (personagem.alive)
    {
        printf("true ## ");
    }
    else
    {
        printf("false ## ");
    }
    // lista alternative_actors
    printf("%s ## ", personagem.dateOfBirth);
    printf("%s ## ", personagem.yearOfBirth);
    printf("%s ## ", personagem.eyeColour);
    printf("%s ## ", personagem.gender);
    printf("%s ## ", personagem.hairColour);
    if (personagem.wirzad)
    {
        printf("true]\n");
    }
    else
    {
        printf("false]\n");
    }
}

// ----------------------------------------------------------------------------------------//
// ----------------------Questão 06_Ordenação por Seleção Recursiva------------------------//
void mostrar06(String *idarr, int n)
{
    for (int i = 0; i < n - 1; i++)
    {
        Personagem p = getElementById(idarr[i]);
        imprimir(p);
    }
}

void selecao(String *idarr, int n, int i)
{
    char tmp[50];
    if (i < n)
    {
        int menor = i;
        for (int j = i + 1; j < 28 - 1; j++)
        {
            Personagem personagem1 = getElementById(idarr[menor]); // pegar personagem
            Personagem personagem2 = getElementById(idarr[j]);     // pegar personagem 2
            if (strcmp(personagem1.name, personagem2.name) > 0)
            {
                menor = j;
            }
        }
        // swap
        strcpy(tmp, idarr[i]);
        strcpy(idarr[i], idarr[menor]);
        strcpy(idarr[menor], tmp);
        selecao(idarr, n, i + 1);
    }
    else
    {
        mostrar06(idarr, n);
    }
}

void TP02Q06()
{
    char input[300];
    scanf("%99s", input);
    // array de id
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }

    selecao(idarr, x + 1, 0);

    // desalocar memoria
    for (size_t i = 0; i < 28; i++)
    {
        free(idarr[i]);
    }
    free(idarr);
}

// ----------------------------------------------------------------------------------------//
// -------------------------------Questão_04_Pesquisa_Binária------------------------------//

bool pesquisaBinaria(String *idarr, int n, char procura[50])
{
    int dir = n - 1, esq = 0;
    // bool resp = false;
    while (esq <= dir)
    {
        int meio = (esq + dir) / 2;
        Personagem personagem = getElementById(idarr[meio]);
        if (strcmp(procura, personagem.name) == 0)
        {
            return true;
            // esq = n;
        }
        else if (strcmp(procura, personagem.name) > 0)
        {
            esq = meio + 1;
        }
        else
        {
            dir = meio - 1;
        }
    }
    return false;
}

// alterado para questão 10
void quicksort(String *idarr, int n, int esq, int dir)
{
    int i = esq, j = dir;
    int pivo = (dir + esq) / 2;
    char tmp[50];
    Personagem personagem1 = getElementById(idarr[pivo]);
    while (i <= j)
    {
        while (strcmp(getElementById(idarr[i]).house, personagem1.house) < 0 ||
               (strcmp(getElementById(idarr[i]).house, personagem1.house) == 0 && strcmp(getElementById(idarr[i]).name, personagem1.name) < 0))
        {
            i++;
        }
        while (strcmp(getElementById(idarr[j]).house, personagem1.house) > 0 ||
               (strcmp(getElementById(idarr[j]).house, personagem1.house) == 0 && strcmp(getElementById(idarr[j]).name, personagem1.name) > 0))
        {
            j--;
        }
        if (i <= j)
        {
            strcpy(tmp, idarr[i]);
            strcpy(idarr[i], idarr[j]);
            strcpy(idarr[j], tmp);
            i++;
            j--;
        }
    }
    if (esq < j)
    {
        quicksort(idarr, n, esq, j);
    }
    if (i < dir)
    {
        quicksort(idarr, n, i, dir);
    }
}

void TP02Q04()
{
    char input[300];
    scanf("%99s", input);
    // array de id
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }
    // ordenar array
    quicksort(idarr, x, 0, x - 1);

    bool achou = false;
    scanf(" %[^\r\n]%*c", input);
    while (strcmp(input, "FIM") != 0)
    {
        achou = pesquisaBinaria(idarr, x, input);
        if (achou)
        {
            printf("SIM\n");
        }
        else
        {
            printf("NAO\n");
        }
        scanf(" %[^\r\n]%*c", input);
    }

    // desalocar memoria
    for (size_t i = 0; i < 28; i++)
    {
        free(idarr[i]);
    }
    free(idarr);
}

// ----------------------------------------------------------------------------------------//
// -----------------------------------Questão_10_Quicksort---------------------------------//

void TP02Q10()
{
    char input[300];
    scanf("%99s", input);
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }

    quicksort(idarr, x, 0, x - 1);

    for (int i = 0; i < x; i++)
    {
        Personagem p = getElementById(idarr[i]);
        imprimir(p);
    }
}

// ----------------------------------------------------------------------------------------//
// ------------------------------------Questão_12_Bolha------------------------------------//
void bolha(String *idarr, int n)
{
    int i, j;
    char tmp[500];
    Personagem p;
    for (i = 0; i < (n - 1); i++)
    {
        for (j = 0; j < n - 1; j++)
        {
            p = getElementById(idarr[j + 1]);
            if (strcmp(getElementById(idarr[j]).hairColour, p.hairColour) > 0 ||
                strcmp(getElementById(idarr[j]).hairColour, p.hairColour) == 0 &&
                    strcmp(getElementById(idarr[j]).name, p.name) > 0)
            {
                strcpy(tmp, idarr[j]);
                strcpy(idarr[j], p.id);
                strcpy(idarr[j + 1], tmp);
            }
        }
    }
}

void TP02Q12()
{
    char input[300];
    scanf("%99s", input);
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }

    bolha(idarr, x);

    for (int i = 0; i < x; i++)
    {
        Personagem p = getElementById(idarr[i]);
        imprimir(p);
    }
}
// ----------------------------------------------------------------------------------------//
// ------------------------------------Questão_14_Radixsort--------------------------------//

bool getMax(String str1, String str2, Personagem p1, Personagem p2)
{
    for (size_t i = 0; i < strlen(str1) && i < strlen(str2); i++)
    {
        atualizarComparacoes(1); // Atualizar comparações
        if ((str1[i] > str2[i]))
        {
            return true;
        }
        else if (str1[i] < str2[i])
        {
            return true;
        }
    }

    return false;
}
//=============================================================================
void radcountingSort(char *idarr[], int n, size_t exp)
{
    char **tmp = NULL;
    int *count = NULL;
    tmp = (char **)malloc(n * sizeof(char *));
    count = (int *)malloc(257 * sizeof(int));

    // Inicializar cada posicao do array de contagem
    for (int i = 0; i < 257; count[i] = 0, i++)
        ;

    // o count[i] contem o numero de elemento iguais a i
    for (int i = 0; i < n; i++)
    {
        atualizarComparacoes(1); // Atualizar comparações
        count[exp < strlen(idarr[i]) ? (int)(unsigned char)idarr[i][exp] + 1 : 0]++;
    }

    // o count[i] contem o numero de elemento menores ou iguais a i
    for (int i = 1; i < 257; i++)
    {
        atualizarComparacoes(1); // Atualizar comparações
        count[i] += count[i - 1];
    }

    // Ordenando
    for (int r = n - 1; r >= 0; r--)
    {
        atualizarComparacoes(1); // Atualizar comparações
        tmp[count[exp < strlen(idarr[r]) ? (int)(unsigned char)idarr[r][exp] + 1 : 0] - 1] = idarr[r];
        count[exp < strlen(idarr[r]) ? (int)(unsigned char)idarr[r][exp] + 1 : 0]--;
    }

    // Copiando para o array original
    for (int i = 0; i < n; i++)
    {
        atualizarComparacoes(1);   // Atualizar comparações
        atualizarMovimentacoes(1); // Atualizar movimentações
        idarr[i] = tmp[i];
    }

    // deslocar
    free(tmp);
    free(count);
}

void radixsort(char **idarr, int n)
{
    // Pegar tamanho dos ids
    size_t max = strlen(idarr[0]);
    for (int exp = max; exp > 0; exp--)
    {
        atualizarComparacoes(1); // Atualizar comparações
        radcountingSort(idarr, n, exp - 1);
    }
}

void TP02Q14()
{
    char input[300];
    scanf("%99s", input);
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            atualizarMovimentacoes(1); // Atualizar movimentações
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }

    radixsort(idarr, x);

    for (int i = 0; i < x; i++)
    {
        Personagem p = getElementById(idarr[i]);
        imprimir(p);
    }
}
// ----------------------------------------------------------------------------------------//
// ------------------------------------Questão_17_Radixsort--------------------------------//

void swap(String *idArray, int i, int j)
{
    char tmp[300];
    strcpy(tmp, idArray[i]);
    strcpy(idArray[i], idArray[j]);
    strcpy(idArray[j], tmp);
}
//=============================================================================
void construir(String *array, int tamHeap)
{
    for (int i = tamHeap; i > 1; i /= 2)
    {
        int cmp = strcmp(getElementById(array[i]).hairColour, getElementById(array[i / 2]).hairColour);
        if (cmp == 0)
        {
            cmp = strcmp(getElementById(array[i]).name, getElementById(array[i / 2]).name);
        }
        if (cmp > 0)
        {
            swap(array, i, i / 2);
        }
    }
}
//=============================================================================
int getMaiorFilho(String *array, int i, int tamHeap)
{
    int filho;
    int cmp = strcmp(getElementById(array[2 * i]).hairColour, getElementById(array[2 * i + 1]).hairColour);
    if (2 * i == tamHeap || cmp > 0 || (cmp == 0 && strcmp(getElementById(array[2 * i]).name, getElementById(array[2 * i + 1]).name) > 0))
    {
        filho = 2 * i;
    }
    else
    {
        filho = 2 * i + 1;
    }
    return filho;
}
//=============================================================================
void reconstruir(String *idarr, int tamHeap)
{
    int i = 1;
    while (i <= (tamHeap / 2))
    {
        int filho = getMaiorFilho(idarr, i, tamHeap);
        int cmp = strcmp(getElementById(idarr[filho]).hairColour, getElementById(idarr[i]).hairColour);
        if (cmp == 0)
        {
            cmp = strcmp(getElementById(idarr[filho]).name, getElementById(idarr[i]).name);
        }
        if (cmp > 0)
        {
            swap(idarr, i, filho);
            i = filho;
        }
        else
        {
            i = tamHeap;
        }
    }
}
//=============================================================================
void heapsort(String *idarr, int n, int k)
{

    String *tmp = (String *)malloc(n + 1 * sizeof(String));
    for (size_t i = 0; i < n+1; i++)
    {
        tmp[i] = (char *)malloc(200 * sizeof(char));
    }

    for (size_t i = 0; i < n; i++)
    {
        strcpy(tmp[i + 1], idarr[i]);
    }

    // construir
    for (size_t tamHeap = 2; tamHeap <= n && k > 0; tamHeap++, k--)
    {
        construir(tmp, tamHeap);
    }

    // Ordenacao propriamente dita
    int tamHeap = n;
    while (tamHeap > 1)
    {
        swap(tmp, 1, tamHeap--);
        reconstruir(tmp, tamHeap);
    }

    // Alterar o vetor para voltar a posicao zero
    for (size_t i = 0; i < n; i++)
    {
        strcpy(idarr[i], tmp[i + 1]);
    }

    // desalocar tmp
    for (size_t i = 0; i < n - 1; i++)
    {
        free(tmp[i]);
    }
    free(tmp);
}

void TP02Q17()
{
    char input[300];
    scanf("%99s", input);
    String *idarr = (String *)malloc(28 * sizeof(String));
    for (size_t i = 0; i < 28; i++)
    {
        idarr[i] = (char *)malloc(300 * sizeof(char));
    }

    int x = 0;
    while (strcmp(input, "FIM") != 0 && x < 28)
    {
        if (strcmp(input, "FIM") != 0 && x < 28)
        {
            atualizarMovimentacoes(1); // Atualizar movimentações
            strcpy(idarr[x], input);
            x++;
        }
        scanf("%s", input);
    }

    heapsort(idarr, x, 10);

    for (int i = 0; i < 10; i++)
    {
        Personagem p = getElementById(idarr[i]);
        imprimir(p);
    }
}

// ----------------------------------------------------------------------------------------//
int main()
{
    // Registra o tempo no início
    clock_t inicio = clock();

    // Ler arquivo no main
    FILE *arquivo;
    arquivo = fopen("/tmp/characters.csv", "r");
    if (arquivo == NULL)
    {
        arquivo = fopen("characters.csv", "r");
        // printf("Erro na abertura do arquivo");
        // return -1;
    }
    char line[1000];
    fgets(line, sizeof(line), arquivo); // Ignorar primeira linha
    Personagem personagens;
    int elenco = 0; // Número de personagens

    reset(&personagens);

    start(personagens);

    while (fgets(line, sizeof(line), arquivo) != NULL)
    {
        reset(&personagens);     // Resetar as variáveis
        ler(&personagens, line); // Ler do arquivo e armazenar as variáveis
        addend(personagens);     // Adicionar na lista encadeada
        elenco++;
    }

    // Terminou de usar o arquivo
    fclose(arquivo);

    //--------------------------------------------------------------------//

    TP02Q17();

    //--------------------------------------------------------------------//

    // Registra o tempo no final e calcula a duração
    clock_t fim = clock();
    double tempo_total = (double)(fim - inicio) / CLOCKS_PER_SEC;

    // Atualiza o registro de tempo
    atualizarTempo(tempo_total);

    // Registra os valores de comparações e tempo em um arquivo de log
    registrarLog();

    return 0;
}