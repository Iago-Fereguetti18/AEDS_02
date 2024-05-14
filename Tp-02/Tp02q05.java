
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
        if(separado[8].equalsIgnoreCase("VERDADEIRO")){
            setHogwartsStudent("true");
        }else{
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

public class Tp02q05 {
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
            FileWriter escrivao = new FileWriter("matrícula_selecao.txt", true); // true para anexar ao arquivo em
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

    public static void swap(int i, int j){
        String tmp = nomes[i];
        nomes[i] = nomes[j];
        nomes[j] = tmp;
    }

    public static void mostrar(String[] ordenado) {
        int i = 0;
        while (i < 10) {
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
    public static void main(String[] args) {
        // ler arquivo
        ler();
        TP02Q05();
    }
}
