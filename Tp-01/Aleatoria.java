import java.util.*;

public class Aleatoria {
    static String bagunca(String input, char pri, char sec) {
        // definir dados
        char[] chars = input.toCharArray();

        // bagunçar
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == pri) {
                chars[i] = sec;
            }
        }
        // final
        return new String(chars);
    }

    public static void main(String[] args) {
        String input = "";
        String resp = "";
        char pri;
        char sec;
        // gerador
        Random gerador = new Random();
        gerador.setSeed(4);
        // Scanner scanner = new Scanner(System.in);
        // input = scanner.nextLine();
        input = MyIO.readLine();
        while (!input.equals("FIM")) {
            pri = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
            sec = ((char) ('a' + (Math.abs(gerador.nextInt()) % 26)));
            resp = bagunca(input, pri, sec);
            MyIO.println(resp);
            input = MyIO.readLine();
        }
    }
}
