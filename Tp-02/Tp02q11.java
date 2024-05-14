
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
import javax.swing.text.TabExpander;
import java.text.Collator;

//import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//arquivo log
import java.io.FileWriter;

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
        setHogwartsStaff(bina(separado[7]));// criar metodo para verificar
        if (separado[8].equalsIgnoreCase("VERDADEIRO")) {
            setHogwartsStudent("true");
        } else {
            setHogwartsStudent("false");
        }
        setActorName(separado[9]);
        setAlive(bina(separado[10]));// criar metodo para verificar

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
        System.out.print("[" + this.id + " ## " + this.name + " ## " + "{");
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

}// end_Personagem

// -----------------------------------------------------------------------------//
// ------------------------------Classe_Do_Arquivo------------------------------//

public class Tp02q11 {
    private static List<Personagem> personagens = new ArrayList<>();

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

    public static void ler() { // tem que pular a primeira linha ainda
        try (BufferedReader leitor = new BufferedReader(new FileReader("/tmp/characters.csv"))) {// /tmp/
            // FileReader file = new FileReader(nomeArquivo);
            // BufferedReader buffer = new BufferedReader(file);
            String line = "";
            leitor.readLine();// descartar a primeira linha
            while ((line = leitor.readLine()) != null) {
                // MyIO.println(line);
                // line = leitor.readLine();
                Personagem p = new Personagem();
                // String[] separado = editor(';', line);
                // separado = editor(';', line);
                // p.divisor(separado);
                p.divisor(line);
                personagens.add(p);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ENDERECO ERRADO BURRO");
        } catch (IOException e) {
            System.out.println("NAO ACHOU");
        }
    }

    public static void buscarid(String id) {
        // boolean achou = false;
        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                // achou = true;
                p.imprimir();
                // break;
            }
        }
    }
    // ----------------------------------------------------------------------------------------//
    // ------------------------------Questão03_Pesquisa_Sequencial----------------------------//

    // pegar ids selecionadas e procurar nomes
    public static int sequencial(String[] ids, String nome) {
        int comparacoes = 0;
        for (Personagem p : personagens) {
            for (int i = 0; i < ids.length; i++) {
                comparacoes++;
                if (p.getId().equals(ids[i]) && p.getName().equals(nome)) {
                    System.out.println("SIM");
                    return comparacoes;
                }
            }
        }
        System.out.println("NAO");
        return comparacoes;
    }

    public static void log(long tempoExecucao, int numComparacoes) {
        try {
            FileWriter escrivao = new FileWriter("815991_countingsort.txt", true); // true para anexar ao arquivo em
                                                                                   // vez de sobrescrevê-lo
            escrivao.write("815991\n");
            escrivao.write("Tempo de execução: " + tempoExecucao + "ms\n"); // registrar o tempo de execução
            escrivao.write("Número de comparações: " + numComparacoes + "\n"); // registrar o número de comparações
            escrivao.close(); // fechar o FileWriter quando terminar
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void TP02Q03() {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[50];
        int i = 0;
        long startTime = System.currentTimeMillis(); // iniciar o cronômetro
        int totalComparacoes = 0;
        // pegar os ids
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            arrayDeIds[i] = input;
            i++;
        }
        i = 0;
        // pegar os nomes
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            totalComparacoes += sequencial(arrayDeIds, input);
            i++;
        }
        long endTime = System.currentTimeMillis(); // parar o cronômetro
        long tempoExecucao = endTime - startTime;
        log(tempoExecucao, totalComparacoes); // registrar o tempo de execução e o número de comparações
        scanner.close();
    }

    // ----------------------------------------------------------------------------------------//
    // ---------------------------------Questao05_Ordenação_por_Seleção------------------------//
    static String[] nomes = new String[50];

    public static void swap(int i, int j) {
        String tmp = nomes[i];
        nomes[i] = nomes[j];
        nomes[j] = tmp;
    }

    public static void mostrar(String[] ordenado) {
        int i = 0;
        while (i != ordenado.length) {
            for (Personagem p : personagens) {
                if (p.getName().equals(ordenado[i])) {
                    // System.out.println("ERA PRA MOSTRAR");
                    p.imprimir();
                    break;
                }
            }
            i++;
        }
    }

    public static int ordenacao(String[] ids) {
        int comp = 0;
        int tam = ids.length;
        int i = 0;
        String nome = "";
        for (int j = 0; j < tam; j++) {
            for (Personagem p : personagens) {
                if (p.getId().equals(ids[i])) {
                    // System.out.println("TO AQUI");
                    nome = p.getName();
                    nomes[i] = nome;
                    i++;
                }
            }
            j++;
        }

        for (i = 0; nomes[i] != null; i++) {
            int menor = i;
            for (int j = (i + 1); nomes[j] != null; j++) {
                comp++;
                if (nomes[menor].compareTo(nomes[j]) > 0) {
                    menor = j;
                }
            }
            swap(menor, i);
        }

        mostrar(nomes);
        return comp;
    }

    public static void TP02Q05() {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[50];
        int i = 0;
        long startTime = System.currentTimeMillis(); // iniciar o cronômetro
        int totalComparacoes = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            arrayDeIds[i] = input;
            i++;
        }
        totalComparacoes += ordenacao(arrayDeIds);
        long endTime = System.currentTimeMillis(); // parar o cronômetro
        long tempoExecucao = endTime - startTime;
        log(tempoExecucao, totalComparacoes); // registrar o tempo de execução e o número de comparações
        scanner.close();
    }

    // ----------------------------------------------------------------------------------------//
    // ---------------------------------Questao07_Ordenação_por_Inserção-----------------------//
    public static void mostrar05(String[] ids) {
        String input;
        for (int i = 0; i < ids.length; i++) {
            input = ids[i];
            buscarid(input);
        }
    }

    /**
     * função para pegar data usando ids como referencia
     * 
     * @param id
     * @return data
     */
    public static LocalDate pegaData(String id) {
        LocalDate data = null;
        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                return (p.getDateOfBirth());
            }
        }
        return data;
    }

    public static int pegaNome(String id, String id2) {
        String nome1 = "";
        String nome2 = "";
        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                nome1 = p.getName();
            }
            if (p.getId().equals(id2)) {
                nome2 = p.getName();
            }
        }
        return nome1.compareTo(nome2);
    }

    public static int insercao(String[] ids) {
        int comp = 0;
        for (int i = 1; i < ids.length; i++) {
            LocalDate tmp = pegaData(ids[i]);
            String idTemp = ids[i];
            int j = i - 1;
            while ((j >= 0) && (tmp != null && pegaData(ids[j]) != null && (tmp.isBefore(pegaData(ids[j]))
                    || (tmp.isEqual(pegaData(ids[j])) && pegaNome(ids[i], ids[j]) < 0)))) {
                ids[j + 1] = ids[j];
                j--;
            }
            ids[j + 1] = idTemp;
        }
        mostrar05(ids);
        return comp;
    }

    public static void TP02Q07() {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[50];
        int i = 0;
        long startTime = System.currentTimeMillis(); // iniciar o cronômetro
        int totalComparacoes = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            arrayDeIds[i] = input;
            i++;
        }
        totalComparacoes += insercao(arrayDeIds);
        long endTime = System.currentTimeMillis(); // parar o cronômetro
        long tempoExecucao = endTime - startTime;
        log(tempoExecucao, totalComparacoes); // registrar o tempo de execução e o número de comparações
        scanner.close();
    }

    // ----------------------------------------------------------------------------------------//
    // ---------------------------------Questao09_Ordenação_por_Heapsort-----------------------//
    static int tamHeap = 0;// numero de strings colocadas
    static String[] vetHeap = new String[50];
    static String[] idsHeap = new String[50];

    public static int heapsort() {
        int count = 0;
        int copia = tamHeap;// copia para alteracoes
        // tamHeap = idsHeap.length;
        // construir heap
        constroiHeapsort();

        for (int i = tamHeap - 1; i > 0; i--) {
            troca(0, i);
            tamHeap -= 1;
            ordHeap(0);
            count++;
        }
        /*
         * for(int i = 0; i < copia; i++){
         * System.out.println(idsHeap[i]);
         * }
         */
        for (int i = 0; i < copia; i++) {
            for (Personagem p : personagens) {
                // System.out.println("PQ????");
                if (p.getId().equals(idsHeap[i])) {
                    // System.out.println("cade?????");
                    p.imprimir();
                }
            }
        }

        return count;
    }

    /**
     * função para moldar o heap
     */
    public static void constroiHeapsort() {
        for (int i = tamHeap / 2 - 1; i >= 0; i--) {
            ordHeap(i);
        }
    }

    private static void troca(int i, int j) {
        String aux;
        aux = idsHeap[i];
        idsHeap[i] = idsHeap[j];
        idsHeap[j] = aux;
        /*--------------------------------*/
        /*
         * aux = vetHeap[i];
         * vetHeap[i] = vetHeap[j];
         * vetHeap[j] = aux;
         */
    }

    public static int pegaCor(String id, String id2) {
        String nome1 = "";
        String nome2 = "";
        int resp = 0;
        for (Personagem p : personagens) {
            if (p.getId().equals(id)) {
                nome1 = p.getHairColour();
            }
            if (p.getId().equals(id2)) {
                nome2 = p.getHairColour();
            }
        }
        // Collator collator = Collator.getInstance(new Locale("pt", "BR"));
        // collator.setStrength(Collator.PRIMARY);
        // return collator.compare(nome1, nome2);
        if (!nome1.equalsIgnoreCase(nome2)) {
            resp = nome1.compareTo(nome2);
        } else {
            resp = pegaNome(id, id2);
        }
        return resp;
    }

    public static void ordHeap(int pai) {
        int maior = pai; // o maior é o pai até provar o contrario
        int esquerda = 2 * pai + 1; // filho a esquerda
        int direita = 2 * pai + 2;// filho a direita

        if (esquerda < tamHeap && pegaCor(idsHeap[esquerda], idsHeap[maior]) > 0) {
            maior = esquerda;
        }
        if (direita < tamHeap && pegaCor(idsHeap[direita], idsHeap[maior]) > 0) {
            maior = direita;
        }
        if (maior != pai) {
            troca(pai, maior);
            ordHeap(maior);
        }
    }

    public static void TP02Q09() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        long startTime = System.currentTimeMillis(); // iniciar o cronômetro
        int totalComparacoes = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("FIM")) {
                break;
            }
            idsHeap[i] = input;
            i++;
        }
        tamHeap = i;
        totalComparacoes += heapsort();
        long endTime = System.currentTimeMillis(); // parar o cronômetro
        long tempoExecucao = endTime - startTime;
        log(tempoExecucao, totalComparacoes); // registrar o tempo de execução e o número de comparações
        scanner.close();
    }

    // ----------------------------------------------------------------------------------------//
    // ---------------------------------Questao11_Counting-Sort--------------------------------//
    public static Personagem getElementById(String id) {
        Personagem p = null;

        for (Personagem p2 : personagens) {
            if (p2.getId().equals(id)) {
                return p2;
            }
        }

        return p;
    }

    public static int getMaior(String idarr[], int n) {
        int maior = 0;
        for (int i = 0; i < n; i++) {
            for (Personagem p : personagens) {
                if (p.getId().equals(idarr[i])) {
                    if (maior < p.getYearOfBirth()) {
                        maior = p.getYearOfBirth();
                    }
                }
            }
        }
        return maior;
    }

    public static void sort(String idarr[], int n) {
        // Array para contar o numero de ocorrencias de cada elemento
        int[] count = new int[getMaior(idarr, n) + 1];
        Personagem[] ordenado = new Personagem[n];

        // Inicializar cada posicao do array de contagem
        for (int i = 0; i < count.length; count[i] = 0, i++)
            ;

        // Agora, o count[i] contem o numero de elemento iguais a i
        for (int i = 0; i < n; i++) {
            if (getElementById(idarr[i]) != null) {
                int data = getElementById(idarr[i]).getYearOfBirth();
                count[data]++;
            }
        }

        // Agora, o count[i] contem o numero de elemento menores ou iguais a i
        for (int i = 1; i < count.length; count[i] += count[i - 1], i++)
            ;

        // Ordenando
        for (int i = n - 1; i >= 0; i--) {
            if (getElementById(idarr[i]) != null) {
            int data = getElementById(idarr[i]).getYearOfBirth();
            ordenado[count[data] - 1] = getElementById(idarr[i]);
            count[data]--;
            }
        }

        // desenpate por nome
        for (int i = 1; i < ordenado.length; i++) {
            int j = i - 1;
            while (ordenado[j] != null && j >= 0 && (ordenado[j].getYearOfBirth() == ordenado[j + 1].getYearOfBirth() &&
                    ordenado[j].getName().compareTo(ordenado[j + 1].getName()) > 0)) {
                Personagem tmp = ordenado[j];
                ordenado[j] = ordenado[j + 1];
                ordenado[j + 1] = tmp;
                j--;
            }
        }

        // Copiando para o array original
        for (int i = 0; i < n; i++) {
            if(ordenado[i] != null){
            idarr[i] = ordenado[i].getId();
        }
        }
    }

    public static void TP02Q11() {
        Scanner scanner = new Scanner(System.in);
        String[] arrayDeIds = new String[50];
        int i = 0;
        long startTime = System.currentTimeMillis(); // iniciar o cronômetro
        int totalComparacoes = 0;
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("FIM")) {
                break;
            }
            arrayDeIds[i] = input;
            i++;
        }
        // totalComparacoes += insercao(arrayDeIds);
        sort(arrayDeIds, i);

        for (int j = 0; j < i; j++) {
            for (Personagem p : personagens) {
                // System.out.println("PQ????");
                if (p.getId().equals(arrayDeIds[j])) {
                    // System.out.println("cade?????");
                    p.imprimir();
                }
            }
        }

        long endTime = System.currentTimeMillis(); // parar o cronômetro
        long tempoExecucao = endTime - startTime;
        log(tempoExecucao, totalComparacoes); // registrar o tempo de execução e o número de comparações
        scanner.close();
    }

    // ----------------------------------------------------------------------------------------//
    public static void main(String[] args) {
        // ler arquivo
        ler();
        TP02Q11();
    }
}
