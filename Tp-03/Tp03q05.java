
//verificar
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.time.LocalDate;
import javax.swing.text.DateFormatter;
//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Crie uma classe Personagem seguindo todas as regras apresentadas no slide unidade01g_conceitosBasicos_introducaoOO.pdf.
 * Sua classe terá os atributos privados id (String),
 *  name (String),
 *  alternate_names (Lista),
 *  house (String),
 *  ancestry (String),
 *  species (String),
 *  patronus (String),
 *  hogwartsStaff (Boolean),
 *  hogwartsStudent (String),
 *  actorName (String),
 *  alive (Boolean),
 *  dateOfBirth (DateTime),
 *  yearOfBirth (int),
 *  eyeColour (String),
 *  gender (String),
 *  hairColour (String),
 *  wizard (Boolean).
 *  Sua classe também terá pelo menos dois construtores, e os métodos gets, sets, clone, imprimir e ler.
O método imprimir mostra os atributos do registro (ver cada linha da saída padrão) e o ler lê os atributos de um registro.
 Atenção para o arquivo de entrada, pois em alguns registros faltam valores e esse foi substituído pelo valor 0 (zero) ou vazio.
A entrada padrão é composta por várias linhas e cada uma contém uma string indicando o id do Personagem a ser lido.
 A última linha da entrada contém a palavra FIM. A saída padrão também contém várias linhas, uma para cada registro contido em uma linha da entrada padrão.
 */

// Classe para armazenar uma lista de strings
class Lista {
    private String[] elementos;
    private int tamanho;

    // Construtor da classe Lista
    public Lista(int capacidade) {
        this.elementos = new String[capacidade];
        this.tamanho = 0;
    } // end Constructor

    // Método para adicionar um elemento à lista
    public void adicionar(String elemento) {
        this.elementos[this.tamanho] = elemento;
        this.tamanho++;
    } // end adicionar

    // Método para obter um elemento da lista pelo índice
    public String obter(int indice) {
        if (!(indice >= 0 && indice < this.tamanho)) {
            throw new IllegalArgumentException("LISTAAAAAAA");
        }
        return this.elementos[indice];
    } // end obter

    // Método para obter o tamanho atual da lista
    public int tamanho() {
        return this.tamanho;
    } // end tamanho

} // end Lista

// ----------------------------------Nova-Classe-Lista------------------------------------//
class ListaPersonagem {
    private Personagem[] personagems;
    private int tam;

    // construtor
    public ListaPersonagem(int capacidade) {
        this.personagems = new Personagem[capacidade];
        this.tam = 0;
    }

    // Metodo para obter o tamanho
    public int tamanho() {
        return this.tam;
    }

    /*
     * Os métodos de inserir e remover devem operar conforme descrito a seguir,
     * respeitando parâmetros e retornos.
     * Primeiro, o void inserirInicio(Personagem personagem) insere um registro na
     * primeira posição da Lista e remaneja os demais.
     * Segundo, o void inserir(Personagem personagem, int posição) insere um
     * registro na posição p da Lista, onde p < n e n é o número de registros
     * cadastrados.
     * Em seguida, esse método remaneja os demais registros. O void
     * inserirFim(Personagem personagem) insere um registro na última posição da
     * Lista.
     * O Personagem removerInicio() remove e retorna o primeiro registro cadastrado
     * na Lista e remaneja os demais.
     * O Personagem remover(int posição) remove e retorna o registro cadastrado na
     * p-ésima posição da Lista e remaneja os demais.
     * O Personagem removerFim() remove e retorna o último registro cadastrado na
     * lista.
     */

    // Metodo para inserir no inicio
    void inseririnicio(Personagem personagem) throws Exception {
        if (tam >= personagems.length) {
            throw new Exception("ERRO - inseririnicio");
        }
        for (int i = tam; i > 0; i--) {
            personagems[i] = personagems[i - 1];
        }
        personagems[0] = personagem;
        tam++;
    }

    // Metodo para inserir no fim
    void inserirfim(Personagem personagem) throws Exception {
        if (tam >= personagems.length) {
            throw new Exception("ERRO - inserirfim");
        }
        personagems[tam] = personagem;
        tam++;
    }

    // Metodo para inserir em qualquer posicao
    void inserir(Personagem personagem, int pos) throws Exception {
        if (tam >= personagems.length || pos < 0 || pos > tam) {
            throw new Exception("ERRO - inserir");
        }
        for (int i = tam; i > pos; i--) {
            personagems[i] = personagems[i - 1];
        }
        personagems[pos] = personagem;
        tam++;
    }

    // Metodo para remover no inicio
    Personagem removerinicio() throws Exception {
        if (tam == 0) {
            throw new Exception("ERRO - Lista Vazia");
        }

        Personagem resp = personagems[0];
        tam--;

        for (int i = 0; i < tam; i++) {
            personagems[i] = personagems[i + 1];
        }
        return resp;
    }

    // Metodo para remover em qualquer posicao
    Personagem remover(int pos) throws Exception {
        if (pos < 0 || pos > tam) {
            throw new Exception("ERRO - Posicao invalida");
        }

        Personagem resp = personagems[pos];
        tam--;

        for (int i = pos; i < tam; i++) {
            personagems[i] = personagems[i + 1];
        }
        return resp;
    }

    // Metodo para remover no fim
    Personagem removerfim() throws Exception {
        if (tam == 0) {
            throw new Exception("ERRO - Lista Vazia");
        }
        return personagems[--tam];
    }

    public Personagem get(int index) {
        return personagems[index];
    }
}


class Pilha {
    private int topo;
    private Personagem[] elementos;

    public Pilha(int tamanho) {
        this.topo = -1;
        this.elementos = new Personagem[tamanho];
    }

    public void empilhar(Personagem x) throws Exception {
        if (topo == elementos.length - 1) {
            throw new Exception("ERRO: A pilha está cheia!");
        }
        elementos[++topo] = x;
    }

    public Personagem desempilhar() throws Exception {
        if (topo == -1) {
            throw new Exception("ERRO: A pilha está vazia!");
        }
        return elementos[topo--];
    }

    public boolean estaVazia() {
        return topo == -1;
    }

    public void mostrar() {
        int count = 0;
        if (topo == -1) {
            System.out.println("Pilha vazia!");
        } else {
            System.out.println("[ Top ]");
            for (int i = topo; i >= 0; i--) {
                System.out.printf("[%d ## ", count);
                elementos[i].imprimir();
                count++;
            }
            System.out.println("[ Bottom ]");
        }
    }
}
/*--------------------------------------------------------------------------------- */
//lista flexivel
class Cell {
    public Cell prox;
    public Personagem elemento;

    public Cell() {
        this.elemento = new Personagem();
        this.prox = null;
    }

    public Cell(Personagem x) {
        this.elemento = x;
        this.prox = null;
    }
}

class ListaFlexivel {
    private Cell pri;
    private Cell ult;

    public ListaFlexivel() {
        pri = null; // Inicialmente a lista está vazia
        ult = null;
    }

    public void inseririnicio(Personagem p) {
        Cell tmp = new Cell(p);
        tmp.prox = pri;
        pri = tmp;
        if (ult == null) {
            ult = pri;
        }
    }

    public void inserirfim(Personagem p) {
        Cell tmp = new Cell(p);
        if (ult == null) {
            pri = tmp;
        } else {
            ult.prox = tmp;
        }
        ult = tmp;
    }

    public int tamanho() {
        int resp = 0; // Inicializa com zero
        for (Cell i = pri; i != null; i = i.prox) {
            resp++;
        }
        return resp;
    }

    public void inserir(Personagem p, int pos) {
        int tam = tamanho();
        if (pos > tam || pos < 0) {
            System.out.println("ERRO: Posição inválida");
        } else if (pos == 0) {
            inseririnicio(p);
        } else if (pos == tam) {
            inserirfim(p);
        } else {
            Cell i = pri;
            for (int j = 0; j < pos - 1; j++) {
                i = i.prox;
            }
            Cell tmp = new Cell(p);
            tmp.prox = i.prox;
            i.prox = tmp;
        }
    }

    public Personagem removerinicio() {
        if (pri == null) {
            System.out.println("ERRO: Lista vazia!");
            return null;
        }
        Cell tmp = pri;
        pri = pri.prox;
        if (pri == null) {
            ult = null;
        }
        Personagem p = tmp.elemento;
        tmp.prox = null;
        return p;
    }

    public Personagem removerfim() {
        if (pri == null) {
            System.out.println("ERRO: Lista vazia!");
            return null;
        }
        Cell tmp = pri;
        if (pri == ult) {
            pri = null;
            ult = null;
            return tmp.elemento;
        }
        while (tmp.prox != ult) {
            tmp = tmp.prox;
        }
        Personagem p = ult.elemento;
        tmp.prox = null;
        ult = tmp;
        return p;
    }

    public Personagem remover(int pos) {
        int tam = tamanho();
        Personagem retirado = null;
        if (pos >= tam || pos < 0) {
            System.out.println("ERRO: Posição inválida");
            return null;
        } else if (pos == 0) {
            return removerinicio();
        } else if (pos == tam - 1) {
            return removerfim();
        } else {
            Cell i = pri;
            for (int j = 0; j < pos - 1; j++) {
                i = i.prox;
            }
            Cell aRemover = i.prox;
            i.prox = aRemover.prox;
            retirado = aRemover.elemento;
            aRemover.prox = null;
            return retirado;
        }
    }

    public Personagem get(int pos) {
        if (pos < 0 || pos >= tamanho()) {
            throw new IndexOutOfBoundsException("Posição inválida");
        }
        Cell i = pri;
        for (int j = 0; j < pos; j++) {
            i = i.prox;
        }
        return i.elemento;
    }
}



/*--------------------------------------------------------------------------------- */
//pilha pra questao 6

class PilhaFlexivel{
    private Cell topo;

    public PilhaFlexivel(){
        topo = null;
    }

    public void empilhar(Personagem x){
        Cell tmp = new Cell(x);
        tmp.prox = topo;
        topo = tmp;
        tmp = null;
    }

    public Personagem desempilhar() throws Exception{
        if(topo == null){
            System.out.println("NADA");
            throw new Exception("ERRO");
        }
        Personagem elemento = topo.elemento;
        Cell tmp = topo;
        topo = topo.prox;
        tmp.prox = null;
        tmp = null;
        return elemento;
    }

    public void mostrar(){
        Cell i = topo;
        int count = 0;
        System.out.println("[ Top ]");
        while(i != null) {
            Personagem p = i.elemento;
            if (p != null) {
                System.out.printf("[%d ##", count);
                count++;
                p.imprimir();
            }
            i = i.prox;
        }
        System.out.println("[ Bottom ]");
    }
}

class Personagem {
    private String id;
    private String name;
    private List<String> alternative_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private Boolean hogwartsStaff;
    private String hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private Lista alternate_actors;
    private LocalDate dateOfBirth;
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private String hairColour;
    private Boolean wizard;

    public Personagem() {
        this.id = "";
        this.name = "";
        this.alternative_names = new ArrayList<>();
        this.house = "";
        this.ancestry = "";
        this.species = "";
        this.patronus = "";
        this.hogwartsStaff = false;
        this.hogwartsStudent = "";
        this.actorName = "";
        this.alternate_actors = new Lista(100);
        this.alive = false;
        this.dateOfBirth = LocalDate.now();
        this.yearOfBirth = 0;
        this.eyeColour = "";
        this.gender = "";
        this.hairColour = "";
        this.wizard = false;
    }

    // set e get
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    /*
     * public void setAlternative_names(Lista alternative_names) {
     * this.alternative_names = alternative_names;
     * }
     * 
     * public Lista getAlternative_names() {
     * return alternative_names; //tirei o .this
     * }
     */

    // Método setter para alternative_names
    public void setAlternative_names(List<String> alternative_names) {
        this.alternative_names = alternative_names;
    }

    // Método getter para alternative_names
    public List<String> getAlternative_names() {
        return alternative_names;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getHouse() {
        return this.house;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }

    public String getAncestry() {
        return this.ancestry;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public String getPatronus() {
        return this.patronus;
    }

    public void setHogwartsStaff(Boolean hogwartsStaff) {
        this.hogwartsStaff = hogwartsStaff;
    }

    public Boolean getHogwartsStaff() {
        return this.hogwartsStaff;
    }

    public void setHogwartsStudent(String hogwartsStudent) {
        this.hogwartsStudent = hogwartsStudent;
    }

    public String getHogwartsStudent() {
        return this.hogwartsStudent;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getActorName() {
        return this.actorName;
    }

    public void setAlternative_actors(Lista alternate_actors) {
        this.alternate_actors = alternate_actors;
    }

    public Lista getAlternative_actors() {
        return this.alternate_actors;
    }

    public void setAlive(Boolean alive) {
        this.alive = alive;
    }

    public Boolean getAlive() {
        return this.alive;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public int getYearOfBirth() {
        return this.yearOfBirth;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getEyeColour() {
        return this.eyeColour;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getHairColour() {
        return this.hairColour;
    }

    public void setWizard(Boolean wizard) {
        this.wizard = wizard;
    }

    public Boolean getWizard() {
        return this.wizard;
    }

    // FAZER O CLONE

    boolean bina(String separado) {
        String comp = "VERDADEIRO";
        if (separado.equalsIgnoreCase(comp)) {
            return true;
        }
        return false;
    }

    String binaString(String separado){
        if(separado.equals("VERDADEIRO")){
            separado = "true";
        }else{
            separado = "false";
        }
        return separado;
    }

    // funcao para atribuir cada string para seu objeto correto
    public void divisor(String line) {
        String[] separado = line.split(";");

        setId(separado[0]);
        setName(separado[1]);

        // Limpar a lista de nomes alternativos antes de adicionar novos
        alternative_names.clear();

        // Adicionar nomes alternativos à lista
        String[] alternativeNames = separado[2].replace("[", "").replace("]", "").split(",");
        for (String alternativeName : alternativeNames) {
            String alter = alternativeName.trim().replaceAll("^'|'$", "");
            if (!alter.isEmpty()) {
                alternative_names.add(alter);
            }
        }

        setHouse(separado[3]);
        setAncestry(separado[4]);
        setSpecies(separado[5]);
        setPatronus(separado[6]);
        setHogwartsStaff(bina(separado[7]));
        setHogwartsStudent(binaString(separado[8]));
        setActorName(separado[9]);
        setAlive(bina(separado[10]));

        // ajustar data
        if (!separado[12].isEmpty()) {
            DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-M-yyyy");
            LocalDate date = LocalDate.parse(separado[12], formater);
            setDateOfBirth(date);
        }

        setYearOfBirth(Integer.parseInt(separado[13]));
        setEyeColour(separado[14]);
        setGender(separado[15]);
        setHairColour(separado[16]);
        setWizard(bina(separado[17]));
    }

    // arrumar data
    public String formatData(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }

    public void imprimir() {
        // Imprime os detalhes do personagem encontrado
        System.out.print(" " + this.id + " ## " + this.name + " ## " + "{");
        // Imprimir nomes alternativos
        for (int i = 0; i < alternative_names.size() - 1; i++) {
            System.out.print(alternative_names.get(i) + ", ");
        }
        if (!alternative_names.isEmpty()) {
            System.out.print(alternative_names.get(alternative_names.size() - 1));
        }
        System.out.println("}" + " ## " + this.house
                + " ## " + this.ancestry + " ## "
                + this.species
                + " ## "
                + this.patronus + " ## " + this.hogwartsStaff + " ## " + this.hogwartsStudent + " ## " + this.actorName
                + " ## " + this.alive + " ## " + formatData(dateOfBirth) + " ## " + this.yearOfBirth + " ## "
                + this.eyeColour
                + " ## "
                + this.gender + " ## " + this.hairColour + " ## " + this.wizard + "]");
    }

}// end class Personagem

// ----------------------------------Clase-do-arquivo----------------------------------------//
public class Tp03q05 {
    // private static List<Personagem> personagens = new ArrayList<>();
    private static ListaPersonagem personagens = new ListaPersonagem(1000);
    private static ListaPersonagem inputs = new ListaPersonagem(1000);
    private static Pilha pilha = new Pilha(1000);
    private static ListaFlexivel lista = new ListaFlexivel();

    public static int conta(String linha, char some) {
        int j = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == some) {
                j++;
            }
        }
        return j + 1;
    }

    // editor
    public static String[] editor(char some, String linha) {
        StringBuilder modelo = new StringBuilder();
        int tam = conta(linha, some);
        String[] editado = new String[tam];
        int j = 0;
        for (int i = 0; i < tam; i++) {
            modelo = new StringBuilder();
            while (j < linha.length() && linha.charAt(j) != some) {
                modelo.append(linha.charAt(j));
                j++;
            }
            editado[i] = modelo.toString();
            j++;
        }
        return editado;
    }

    public static void ler() throws Exception { // tem que pular a primeira linha ainda
        try (BufferedReader leitor = new BufferedReader(new FileReader("/tmp/characters.csv"))) {
            // FileReader file = new FileReader(nomeArquivo);
            // BufferedReader buffer = new BufferedReader(file);
            String line = "";
            leitor.readLine();// descartar a primeira linha
            int x = 0;
            while ((line = leitor.readLine()) != null) {
                // MyIO.println(line);
                // line = leitor.readLine();
                Personagem p = new Personagem();
                // String[] separado = editor(';', line);
                // separado = editor(';', line);
                // p.divisor(separado);
                p.divisor(line);
                if (personagens.tamanho() == 0) {
                    personagens.inseririnicio(p);
                } else {
                    personagens.inserir(p, x);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch (IOException e) {
            System.out.println("NAO ACHOU");
        }
    }

    public static void buscarid(String id) {
        for (int i = 0; i < personagens.tamanho(); i++) {
            Personagem p = personagens.get(i);
            if (p.getId().equals(id)) {
                p.imprimir();
            }
        }
    }

    public static Personagem getPersonagem(String id) {
        for (int i = 0; i < personagens.tamanho(); i++) {
            Personagem p = personagens.get(i);
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }
    // ----------------------------------Tp03----------------------------------------//
    // -----------------------Lista-com-Alocação-Sequencial--------------------------//

    public static void TP03Q01() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[500];
        int i = 0;
        // receber ids
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            Personagem peguei = getPersonagem(input);
            if (inputs.tamanho() == 0) {
                inputs.inseririnicio(peguei);
            } else {
                inputs.inserir(peguei, i);
            }
            i++;
        }

        // variaveis para novos inputs
        int n = 0;
        int pos = 0;
        int count = 0;
        String comando = "";
        String id = "";
        String nome = "";
        Personagem selecionado;
        Personagem removido;
        // tipos de comandos
        /*
         * Cada uma dessas linhas tem uma palavra de comando:
         * II inserir no início,
         * I* inserir em qualquer posição,
         * IF inserir no fim,
         * RI remover no início,
         * R* remover em qualquer posição e
         * RF remover no fim.
         */
        n = scanner.nextInt();
        for (int j = 0; j < n; j++) {
            comando = scanner.next();
            if (comando.startsWith("II")) {
                id = scanner.next();
                selecionado = getPersonagem(id);
                inputs.inseririnicio(selecionado);
            } else if (comando.startsWith("I*")) {
                pos = scanner.nextInt();
                id = scanner.next();
                selecionado = getPersonagem(id);
                inputs.inserir(selecionado, pos);
            } else if (comando.startsWith("IF")) {
                id = scanner.next();
                selecionado = getPersonagem(id);
                inputs.inserirfim(selecionado);
            } else if (comando.startsWith("RI")) {
                removido = inputs.removerinicio();
                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
            } else if (comando.startsWith("R*")) {
                pos = scanner.nextInt();
                removido = inputs.remover(pos);
                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
            } else if (comando.startsWith("RF")) {
                removido = inputs.removerfim();
                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
            }
        }

        for (int k = 0; k < inputs.tamanho(); k++) {
            Personagem p = inputs.get(k);
            if (p != null) {
                System.out.printf("[%d ##", count);
                count++;
                p.imprimir();
            }
        }
        scanner.close();
    }
    // ----------------------------------Tp03----------------------------------------//
    // -----------------------Pilha-com-Alocação-Sequencial--------------------------//

    public static void TP03Q03() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[500];
        int i = 0;
        // receber ids
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            Personagem peguei = getPersonagem(input);
            pilha.empilhar(peguei);
            i++;
        }

        // variaveis para novos inputs
        int n = 0;
        int pos = 0;
        int count = 0;
        String comando = "";
        String id = "";
        String nome = "";
        Personagem selecionado;
        Personagem removido;
        // tipos de comandos
        /*
         * Cada uma dessas linhas tem uma palavra de comando:
         * I inserir(empilhar)
         * R remover(desempilhar)
         */
        n = scanner.nextInt();
        for (int j = 0; j < n; j++) {
            comando = scanner.next();
            if(comando.equals("I")){
                id = scanner.next();
                selecionado = getPersonagem(id);
                pilha.empilhar(selecionado);
            }else if(comando.equals("R")){
                removido = pilha.desempilhar();
                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
            }
        }
        
        pilha.mostrar();

        /*
        for (int k = 0; k < inputs.tamanho(); k++) {
            Personagem p = inputs.get(k);
            if (p != null) {
                System.out.printf("[%d ##", count);
                count++;
                p.imprimir();
            }
        }
        */
        scanner.close();
    }
    // ----------------------------------Tp03----------------------------------------//
    // -----------------------Lista-com-Alocação-Sequencial--------------------------//

    public static void TP03Q05() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[500];
        int i = 0;
        // receber ids
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            Personagem peguei = getPersonagem(input);
            if (lista.tamanho() == 0) {
                lista.inseririnicio(peguei);
            } else {
                lista.inserir(peguei, i);
            }
            i++;
        }

        

        // variaveis para novos inputs
        int n = 0;
        int pos = 0;
        int count = 0;
        String comando = "";
        String id = "";
        String nome = "";
        Personagem selecionado;
        Personagem removido;
        /*
        System.out.println("------------------------------------------------------------------------------");
        for (int k = 0; k < lista.tamanho(); k++) {
            Personagem p = lista.get(k);
            if (p != null) {
                System.out.printf("[%d ##", count);
                count++;
                p.imprimir();
            }
        }
        count = 0;
        System.out.println("------------------------------------------------------------------------------");
        */
        // tipos de comandos
        /*
         * Cada uma dessas linhas tem uma palavra de comando:
         * II inserir no início,
         * I* inserir em qualquer posição,
         * IF inserir no fim,
         * RI remover no início,
         * R* remover em qualquer posição e
         * RF remover no fim.
         */
        n = scanner.nextInt();
        
        for (int j = 0; j < n; j++) {
            comando = scanner.next();

            if (comando.startsWith("II")) {
                id = scanner.next();
                selecionado = getPersonagem(id);
                //System.out.println("II " + id);
                lista.inseririnicio(selecionado);
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                
            } else if (comando.startsWith("I*")) {
                pos = scanner.nextInt();
                id = scanner.next();
                //System.out.println("I* " + pos + " " + id);
                selecionado = getPersonagem(id);
                lista.inserir(selecionado, pos);
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                
            } else if (comando.startsWith("IF")) {
                id = scanner.next();
                //System.out.println("IF " + id);
                selecionado = getPersonagem(id);
                lista.inserirfim(selecionado);
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                
            } else if (comando.startsWith("RI")) {//não funciona e não retorna
                //System.out.println("RI");
                 removido = lista.removerinicio();

                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }else{
                    System.out.println("NULL");
                }
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                


            } else if (comando.startsWith("R*")) {
                pos = scanner.nextInt();
                //System.out.println("R*"+ pos);
                removido = lista.remover(pos);

                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                

            } else if (comando.startsWith("RF")) {//não funciona e retorna
                //System.out.println("RF");
                removido = lista.removerfim();

                if (removido != null) {
                    nome = removido.getName();
                    System.out.printf("(R) %s\n", nome);
                }
                
                // System.out.println("------------------------------------------------------------------------------");
                // for (int k = 0; k < lista.tamanho(); k++) {
                //     Personagem p = lista.get(k);
                //     if (p != null) {
                //         System.out.printf("[%d ##", count);
                //         count++;
                //         p.imprimir();
                //     }
                // }
                // count = 0;
                // System.out.println("------------------------------------------------------------------------------");
                
            }
        }
        
        
        for (int k = 0; k < lista.tamanho(); k++) {
            Personagem p = lista.get(k);
            if (p != null) {
                System.out.printf("[%d ##", count);
                count++;
                p.imprimir();
            }
        }
        
        scanner.close();
    }

    // ------------------------------------------------------------------------------//
    public static void main(String[] args) throws Exception {
        ler();

        TP03Q05();
    }
}
