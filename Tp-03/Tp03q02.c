#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

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
typedef struct ListaPersonagem
{
    Personagem *elementos;
    int tamanho;
    int personagemTam;
} ListaPersonagem;

ListaPersonagem *newLista(int tamanho)
{
    ListaPersonagem *new = (ListaPersonagem *)malloc(sizeof(ListaPersonagem));
    new->elementos = (Personagem *)malloc(tamanho * sizeof(Personagem));
    new->personagemTam = tamanho;
    new->tamanho = 0;
    return new;
}

/**
 * funcao para inserir no inicio da lista
 */
void inserirInicio(Personagem p, ListaPersonagem *lista)
{
    if (lista->tamanho >= lista->personagemTam)
    {
        printf("ERRO - inserirInicio\n");
        exit(-1);
    }

    for (int i = lista->tamanho; i > 0; i--)
    {
        lista->elementos[i] = lista->elementos[i - 1];
    }

    lista->elementos[0] = p;
    lista->tamanho++;
}

/**
 * funcao para inserir no fim da lista
 */
void inserirFim(Personagem p, ListaPersonagem *lista)
{
    if (lista->tamanho >= lista->personagemTam)
    {
        printf("ERRO - inserirFim\n");
        exit(-1);
    }

    lista->elementos[lista->tamanho] = p;
    lista->tamanho++;
}

/**
 * funcao para inserir em qualquer posicao da lista
 */
void inserir(Personagem p, ListaPersonagem *lista, int pos)
{
    if (pos < 0 || pos > lista->tamanho || lista->tamanho >= lista->personagemTam)
    {
        printf("ERRO - inserir\n");
        exit(-1);
    }

    for (int i = lista->tamanho; i > pos; i--)
    {
        lista->elementos[i] = lista->elementos[i - 1];
    }

    lista->elementos[pos] = p;
    lista->tamanho++;
}

/**
 * funcao para remover no inicio da lista
 * @return personagem removido no inicio
 */
Personagem removerInicio(ListaPersonagem *lista)
{
    Personagem removido;

    if (lista->tamanho == 0)
    {
        printf("ERRO - removerInicio\n");
        exit(-1);
    }
    removido = lista->elementos[0];
    lista->tamanho--;

    for (int i = 0; i < lista->tamanho; i++)
    {
        lista->elementos[i] = lista->elementos[i + 1];
    }

    return removido;
}

/**
 * funcao para remover no fim da lista
 * @return personagem removido no fim
 */
Personagem removerFim(ListaPersonagem *lista)
{
    Personagem removido;

    if (lista->tamanho == 0)
    {
        printf("ERRO - removerFim\n");
        exit(-1);
    }
    removido = lista->elementos[--lista->tamanho];

    return removido;
}

/**
 * funcao para remover em qualquer posicao da lista
 * @return personagem removido
 */
Personagem remover(ListaPersonagem *lista, int pos)
{
    Personagem removido;

    if (pos < 0 || pos > lista->tamanho || lista->tamanho >= lista->personagemTam)
    {
        printf("ERRO - remover pos: %d\n", pos);
        exit(-1);
    }

    removido = lista->elementos[pos];
    lista->tamanho--;

    for (int i = pos; i < lista->tamanho; i++)
    {
        lista->elementos[i] = lista->elementos[i + 1];
    }
    return removido;
}

String getId(ListaPersonagem* lista, int pos){
    return lista->elementos[pos].id;
}




int main()
{
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
    //---------------TP03Q02-Lista-com-Alocação-Sequencial----------------//

    Personagem personagens2;
    ListaPersonagem *lista = newLista(100);
    char input[100];
    scanf("%99s", input);
    while (strcmp(input, "FIM") != 0)
    {
        if (strcmp(input, "FIM") != 0)
        {
            inserirFim(getElementById(input), lista);
        }
        scanf("%s", input);
    }

    int n = 0;
    scanf("%d", &n);
    int j = 0;
    for (int i = 0; i < n; i++)
    {
        char comando[10];
        scanf("%s", comando);
        if (strcasecmp(comando, "II") == 0)
        {
            char id[50];
            scanf("%s", id);
            inserirInicio(getElementById(id), lista);
        }
        if (strcasecmp(comando, "IF") == 0)
        {
            char id[50];
            scanf("%s", id);
            inserirFim(getElementById(id), lista);
        }
        if (strcasecmp(comando, "I*") == 0)
        {
            int pos = 0;
            char id[50];
            scanf("%d", &pos);
            scanf("%s", id);
            inserir(getElementById(id), lista, pos);
        }
        if (strcasecmp(comando, "RI") == 0)
        {
            Personagem removido;
            removido = removerInicio(lista);
            printf("(R) %s\n", removido.name);
        }
        if(strcasecmp(comando, "RF") == 0){
            Personagem removido;
            removido = removerFim(lista);
            printf("(R) %s\n", removido.name);
        }
        if (strcasecmp(comando, "R*") == 0){
            int pos = 0;
            scanf("%d", &pos);
            Personagem removido;
            removido = remover(lista, pos);
            printf("(R) %s\n", removido.name);
        }
    }

    int count = 0;
    for (int i = 0; i < lista->tamanho; i++)
    {
        printf("[%d ## ", count);
        imprimir(getElementById(getId(lista, i)));
        count++;
    }

    free(lista);

    return 0;
}