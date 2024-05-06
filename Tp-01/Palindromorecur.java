import java.util.*;

public class Palindromorecur {

    static boolean istrue(String str, int size, int i) {
        if (i >= size) {
            return true;
        }
        if (str.charAt(i) != str.charAt(size)) {
            return false;
        }
        return istrue(str, (size - 1), (i + 1));
    }

    public static void main(String[] args) {
        // definir dados
        String str = "";
        String resp = "";
        boolean resposta = false;
        // scanner
        Scanner scanner = new Scanner(System.in);
        // receber dados
        str = scanner.nextLine();
        // definir primeiro tamanho
        int size = str.length() - 1;
        // pegar inputs até "FIM"
        while (!str.equals("FIM")) {

            resposta = istrue(str, size, 0);
            if (resposta) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
            str = scanner.nextLine();
            // alterar tamanho
            size = str.length() - 1;
        }
    }
}