import java.util.*;
public class Palindromo {

    static boolean istrue(String str) {
        int size = str.length() - 1;
        int j = size;
        for (int i = 0; i <= size; i++) {
            if (str.charAt(i) != str.charAt(j)) {
                return false;
            }
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "";
        String resp = "";
        boolean resposta = false;
        Scanner scanner = new Scanner(System.in);
        str = scanner.nextLine();
        while (!str.equals("FIM")) {

            resposta = istrue(str);
            if (resposta) {
                MyIO.println("SIM");
            } else {
                MyIO.println("NAO");
            }
            str = scanner.nextLine();
        }
    }
}
