#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define MAX_ALTERNATIVE_NAMES 10
#define MAX_ALTERNATIVE_ACTORS 10
#define MAX_LINE_LENGTH 1024
#define MAX_NAME_LENGTH 50
#define MAX_SEPARADO 20
#define MAX_PERSONAGEM 1000

typedef struct
{
    char elementos[MAX_ALTERNATIVE_NAMES][MAX_NAME_LENGTH];
    int tamanho;
} Lista;
// struct de personagem
typedef struct
{
    char id[MAX_NAME_LENGTH];          // 0
    char name[MAX_NAME_LENGTH];        // 1
    Lista alternate_names;             // 2
    char house[MAX_NAME_LENGTH];       // 3
    char ancestry[MAX_NAME_LENGTH];    // 4
    char species[MAX_NAME_LENGTH];     // 5
    char patronus[MAX_NAME_LENGTH];    // 6
    bool hogwartsStaff;                // 7
    bool hogwartsStudent;              // era pra ser string //8
    char actorname[MAX_NAME_LENGTH];   // 9
    bool alive;                        // 10
    Lista alternatives_actors;         // 11
    char dateOfBirth[MAX_NAME_LENGTH]; // 12
    char yearOfBirth[MAX_NAME_LENGTH]; // era pra ser int //13
    char eyeColour[MAX_NAME_LENGTH];   // 14
    char gender[MAX_NAME_LENGTH];      // 15
    char hairColour[MAX_NAME_LENGTH];  // 16
    char wirzad[MAX_NAME_LENGTH];      // 17

} Personagem;

void iniciarLista(Lista *lista)
{
    lista->tamanho = 0;
}

void adicionarElemento(Lista *lista, const char *elemento)
{
    if (lista->tamanho < MAX_ALTERNATIVE_NAMES)
    {
        strncpy(lista->elementos[lista->tamanho], elemento, MAX_NAME_LENGTH - 1);
        lista->elementos[lista->tamanho][MAX_NAME_LENGTH - 1] = '\0'; // Garante que a string seja terminada
        lista->tamanho++;
    }
}

void liberarLista(Lista *lista)
{
    lista->tamanho = 0;
}

int split(char *str, char delim, char *tokens[])
{
    int count = 0;
    char *token = str;

    while (*str != '\0')
    {
        if (*str == delim)
        {
            *str = '\0';
            tokens[count++] = token;
            token = str + 1;
        }
        str++;
    }

    tokens[count++] = token;
    return count;
}

void arrumadata(char *data)
{
    int dia, mes, ano;
    sscanf(data, "%d-%d-%d", &dia, &mes, &ano);
    sprintf(data, "%02d-%02d-%d", dia, mes, ano);
}

/*
int numReject(char *str, char regex)
{
    int x = 0, y = 0;
    while (str[x] != '\0')
    {
        if (str[x] == regex)
            y++;
        x++;
    }
    return y + 1;
}

// criar um split
char **split(char *line, char some)
{
    int tam = numReject(line, some);
    char **editado = (char **)malloc(tam * sizeof(char *));

    for (int i = 0; i < tam; i++)
    {
        editado[i] = (char *)malloc(200 * sizeof(char)); // talvez tenha que alterar o tamanho
    }
    int j = 0;
    int k = 0;
    int l = 0;
    while (line[j] != '\0')
    {
        if (line[j] == some)
        {
            editado[k][l] = '\0';
            k++;
            l = 0;
        }
        else
        {
            if (line[j] != '\n')
            {
                editado[k][l] = line[j];
                l++;
            }
        }
        j++;
    }
    return editado;
}
*/
// função para atribuir valores
void ler(Personagem *personagem, char *line)
{
    char *separado[MAX_SEPARADO];
    // separado = split(line,';');
    int num_separados = split(line, ';', separado);

    strcpy(personagem->id, separado[0]);
    strcpy(personagem->name, separado[1]);

    // organizar lista alternate_names
    iniciarLista(&(personagem->alternate_names));
    int i = 0;
    char *alternativeNames_separado = strtok(separado[2], ",");
    while (alternativeNames_separado != NULL && i < MAX_ALTERNATIVE_NAMES)
    {
        // remover [] e ''
        int tam = strlen(alternativeNames_separado);
        for (int j = 0; j < tam; j++)
        {
            if (alternativeNames_separado[j] == '[' || alternativeNames_separado[j] == ']')
            {
                memmove(&alternativeNames_separado[j], &alternativeNames_separado[j + 1], tam - j);
                tam--;
                j--;
            }
            else if (alternativeNames_separado[j] == '\'')
            {
                memmove(&alternativeNames_separado[j], &alternativeNames_separado[j + 1], tam - j);
                tam--;
                j--;
            }
        }
        adicionarElemento(&(personagem->alternate_names), alternativeNames_separado);
        alternativeNames_separado = strtok(NULL, ",");
        i++;
    }

    strcpy(personagem->house, separado[3]);
    strcpy(personagem->ancestry, separado[4]);
    strcpy(personagem->species, separado[5]);
    strcpy(personagem->patronus, separado[6]);
    personagem->hogwartsStaff = false;   // strcpy(personagem->hogwartsStaff, separado[7]);
    personagem->hogwartsStudent = false; // strcpy(personagem->hogwartsStudent, separado[8]);
    strcpy(personagem->actorname, separado[9]);
    personagem->alive = false; // 10

    // organizar lista alternate_actors//11
    iniciarLista(&(personagem->alternatives_actors));
    i = 0;
    char *alternativeActors_token = strtok(separado[10], ",");
    while (alternativeActors_token != NULL && i < MAX_ALTERNATIVE_ACTORS)
    {
        // Remover colchetes e todas as aspas simples
        int length = strlen(alternativeActors_token);
        for (int j = 0; j < length; j++)
        {
            if (alternativeActors_token[j] == '[' || alternativeActors_token[j] == ']')
            {
                memmove(&alternativeActors_token[j], &alternativeActors_token[j + 1], length - j);
                length--;
                j--;
            }
            else if (alternativeActors_token[j] == '\'')
            {
                memmove(&alternativeActors_token[j], &alternativeActors_token[j + 1], length - j);
                length--;
                j--;
            }
        }
        adicionarElemento(&(personagem->alternatives_actors), alternativeActors_token);
        alternativeActors_token = strtok(NULL, ",");
        i++;
    }
    strcpy(personagem->dateOfBirth, separado[12]);
    arrumadata(personagem->dateOfBirth);
    strcpy(personagem->yearOfBirth, separado[13]);
    strcpy(personagem->eyeColour, separado[14]);
    strcpy(personagem->gender, separado[15]);
    strcpy(personagem->hairColour, separado[16]);
    if (separado[17][0] == 'V')
    {
        strcpy(personagem->wirzad, "true");//personagem->wirzad = "true";//15
    }
    else
    {
        strcpy(personagem->wirzad, "false");//personagem->wirzad = "false";
    }
    // free(separado);
}
// função imprimir
void imprimir(Personagem *personagem)
{
    printf("[%s ## %s ## {", personagem->id, personagem->name);

    // mostrar lista alternative_names
    for (int i = 0; i < personagem->alternate_names.tamanho - 1; i++)
    {
        printf("%s,", personagem->alternate_names.elementos[i]);
    }
    printf("%s} ## ", personagem->alternate_names.elementos[personagem->alternate_names.tamanho - 1]); // vaga do ultimo a lista
    printf("%s ## ", personagem->house);
    printf("%s ## ", personagem->ancestry);
    printf("%s ## ", personagem->species);
    printf("%s ## ", personagem->patronus);
    printf("false ## "); // hogwartsStaff
    printf("false ## "); // hogwartsStudent
    printf("%s ## ", personagem->actorname);
    printf("false ## "); // alive
    // lista alternative_actors
    printf("%s ## ", personagem->dateOfBirth);
    printf("%s ## ", personagem->yearOfBirth);
    printf("%s ## ", personagem->eyeColour);
    printf("%s ## ", personagem->gender);
    printf("%s ## ", personagem->hairColour);
    printf("%s]\n", personagem->wirzad);
}

// ler arquivo no main
int main()
{
    FILE *arquivo;
    arquivo = fopen("/tmp/characters.csv", "r");
    if (arquivo == NULL)
    {
        printf("Erro na abertura do arquivo");
        return -1;
    }
    char line[MAX_LINE_LENGTH];
    fgets(line, sizeof(line), arquivo); // ignorar primeira linha
    Personagem personagens[MAX_PERSONAGEM];
    int elenco = 0; // numero de personagens

    while (fgets(line, sizeof(line), arquivo))
    {
        if (elenco >= MAX_PERSONAGEM)
        {
            printf("PERSONA\n");
            break;
        }
        ler(&personagens[elenco], line);
        elenco++;
    }

    // terminou de usar o arquivo
    fclose(arquivo);

    //-------------------------------------------------//
    // ler inputs
    // função para buscar id
    char input[MAX_NAME_LENGTH];
    scanf("%s", input);
    while (true)
    {
        if (strcmp(input, "FIM") == 0)
        {
            break;
        }

        bool achou = false;
        for (int i = 0; i < elenco; i++)
        {
            if (strcmp(personagens[i].id, input) == 0)
            {
                imprimir(&personagens[i]);
                achou = true;
                i = elenco;
            }
        }
        if (!achou)
        {
            printf("NAO_ACHOU_ID\n");
        }
        scanf("%s", input);
    }

    // liberar lista
    for (int i = 0; i < elenco; i++)
    {
        liberarLista(&(personagens[i].alternate_names));
        liberarLista(&(personagens[i].alternatives_actors));
    }

    return 0;
}