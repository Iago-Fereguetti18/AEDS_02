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
    arquivo_log = fopen("815991_quicksort.txt", "a"); // "a" para adicionar ao arquivo existente
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
    printf("%s ## %s ## {", personagem.id, personagem.name);

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

/*------------------------------------------------------------------------*/

typedef struct Celula
{
    Personagem personagem;
    struct Celula *prox;
    struct Celula *ant;
} Celula;

Celula *newCelula(Personagem p)
{
    Celula *new = (Celula *)malloc(sizeof(Celula));
    new->personagem = p;
    new->prox = new->ant = NULL;
    return new;
}

typedef struct ListaDupla
{
    Celula *primeiro;
    Celula *ultimo;
} ListaDupla;

ListaDupla *newListaDupla()
{
    ListaDupla *lista = (ListaDupla *)malloc(sizeof(ListaDupla));
    Personagem p;
    lista->primeiro = newCelula(p);
    lista->ultimo = lista->primeiro;
    return lista;
}

void inseririnicio(Personagem p, ListaDupla *lista)
{
    Celula *tmp = newCelula(p);

    tmp->prox = lista->primeiro->prox;
    tmp->ant = lista->primeiro;
    lista->primeiro->prox = tmp;

    if (lista->primeiro == lista->ultimo)
    {
        lista->ultimo = tmp;
    }
    else
    {
        lista->primeiro->prox->ant = tmp;
    }

    tmp = NULL;
}

void inserirFim(Personagem p, ListaDupla *lista)
{
    lista->ultimo->prox = newCelula(p);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
}

Personagem removerinicio(ListaDupla *lista)
{
    if (lista->primeiro == lista->ultimo)
    {
        printf("Erro - removerinicio");
    }
    Personagem resp = lista->primeiro->personagem;
    Celula *tmp = lista->primeiro;
    lista->primeiro = lista->primeiro->prox;
    tmp->prox = lista->primeiro->ant = NULL;
    free(tmp);
    tmp = NULL;
    return resp;
}

Personagem removerfim(ListaDupla *lista)
{
    if (lista->primeiro == lista->ultimo)
    {
        printf("ERRO - removerfim");
    }
    Personagem resp = lista->ultimo->personagem;
    lista->ultimo = lista->ultimo->ant;
    lista->ultimo->prox->ant = NULL;
    return resp;
}

int tamanho(ListaDupla *lista)
{
    int tam = 0;
    Celula *i;
    for (i = lista->primeiro; i != lista->ultimo; i = i->prox)
    {
        tam++;
    }
    return tam;
}

void inserir(Personagem p, ListaDupla *lista, int pos)
{
    int tam = tamanho(lista);
    if (pos < 0 || pos > tam)
    {
        printf("ERRO - inserir %d", pos);
    }
    else if (pos == 0)
    {
        inseririnicio(p, lista);
    }
    else if (pos == tam)
    {
        inserirFim(p, lista);
    }
    else
    {
        Celula *i = lista->primeiro;
        int j;
        for (j = 0; j < pos; j++, i = i->prox)
            ;
        Celula *tmp = newCelula(p);
        tmp->prox = i->prox;
        tmp->ant = i;
        i->prox->ant = tmp;
        i->prox = tmp;

        i = tmp = NULL;
    }
}

Personagem remover(ListaDupla *lista, int pos)
{
    int tam = tamanho(lista);
    Personagem resp;
    if (pos < 0 || pos > tam)
    {
        printf("ERRO - remover %d", pos);
    }
    else if (pos == 0)
    {
        resp = removerinicio(lista);
    }
    else if (pos == (tam - 1))
    {
        resp = removerfim(lista);
    }
    else
    {
        Celula *i = lista->primeiro;
        int j;
        for (j = 0; j < pos; j++, i = i->prox)
            ;
        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        resp = i->personagem;
        i->prox = i->ant = NULL;
        free(i);
        i = NULL;
    }
    return resp;
}

char *getPivo(ListaDupla *lista, int esq, int dir)
{
    int j;
    int meio = (esq + dir) / 2;
    Celula *i = lista->primeiro->prox;
    for (j = 0; j < meio; j++, i = i->prox)
        ;
    return i->personagem.id;
}

char *getid(ListaDupla *lista, int pos)
{
    Celula *i = lista->primeiro->prox;
    int j;
    for (j = 0; j < pos; j++, i = i->prox)
        ;
    return i->personagem.id;
}

void swap(ListaDupla *lista, int i_pos, int j_pos)
{
    Celula *i = lista->primeiro->prox;
    int k;
    for (k = 0; k < i_pos; k++)
    {
        i = i->prox;
    }
    Celula *j = lista->primeiro->prox;
    for (k = 0; k < j_pos; k++)
    {
        j = j->prox;
    }
        Personagem tmp = i->personagem;
        i->personagem = j->personagem;
        j->personagem = tmp;
}

void quicksort(ListaDupla *lista, int esq, int dir)
{
    if (esq < dir)
    {
        int i = esq;
        int j = dir;
        Personagem pePivo;
        Personagem p;
        pePivo = getElementById(getPivo(lista, i, j));

        while (i <= j)
        {
            p = getElementById(getid(lista, i));
            while (strcmp(pePivo.house, p.house) > 0 ||
                   (strcmp(pePivo.house, p.house) == 0 && strcmp(pePivo.name, p.name) > 0))
            {
                atualizarComparacoes(3);
                i++;
                p = getElementById(getid(lista, i));
            }

            p = getElementById(getid(lista, j));
            while (strcmp(pePivo.house, p.house) < 0 ||
                   (strcmp(pePivo.house, p.house) == 0 && strcmp(pePivo.name, p.name) < 0))
            {
                atualizarComparacoes(3);
                j--;
                p = getElementById(getid(lista, j));
            }
            if (i <= j)
            {
                swap(lista, i, j);
                atualizarMovimentacoes(1);
                i++;
                j--;
            }
        }
        if (esq < j)
        {
            quicksort(lista, esq, j);
        }
        if (i < dir)
        {
            quicksort(lista, i, dir);
        }
    }
}

int main()
{
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

    char input[3000];
    scanf("%99s", input);
    ListaDupla *lista = newListaDupla();

    int x = 0;
    while (strcmp(input, "FIM") != 0)
    {
        if (strcmp(input, "FIM") != 0)
        {
            inserirFim(getElementById(input), lista);
            x++;
        }
        scanf("%s", input);
    }

    // quicksort
    quicksort(lista, 0, x - 1);

    int count = 0;
    Celula *i;
    for (i = lista->primeiro->prox; i != NULL; i = i->prox)
    {
        printf("[");
        imprimir(getElementById(getid(lista, count)));
        count++;
    }

    clock_t fim = clock();
    double tempo_total = (double)(fim - inicio)/CLOCKS_PER_SEC;

    atualizarTempo(tempo_total);
    registrarLog();

    free(lista);

    return 0;
}