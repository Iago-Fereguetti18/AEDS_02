import java.util.*;

public class Isrecur {
    static boolean vogal(String input, int i) {
        input = input.toLowerCase();
        if (input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o'
                && input.charAt(i) != 'u') {
            return false;
        }
        if (i >= input.length()-1) {
            return true;
        }
        return vogal(input, (i + 1));
    }

    static boolean consoante(String input, int i) {
        input = input.toLowerCase();
        if ((input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o'
                && input.charAt(i) != 'u') || input.charAt(i) < 'a' || input.charAt(i) > 'z') {
            return false;
        }
        if (i >= input.length()-1) {
            return true;
        }
        return consoante(input, (i + 1));
    }

    static boolean inteiro(String input, int i) {
        if (input.charAt(i) < 48 || input.charAt(i) > 57) {
            return false;
        }
        if (i >= input.length()-1) {
            return true;
        }
        return inteiro(input, (i + 1));
    }

    static boolean real(String input, int i, int contador) {

        if (contador > 1) {
            return false;
        }
        if (input.charAt(i) == 46 || input.charAt(i) == 44) {
            contador++;
        } else {
            if (input.charAt(i) < 48 || input.charAt(i) > 57) {
                return false;
            }
        }
        if (i >= input.length()-1) {
            return true;
        }
        return real(input, (i + 1), contador);
    }

    public static void main(String[] args) {
        // definir dados
        String input;
        boolean x1 = false;// vogal
        boolean x2 = false;// consoante
        boolean x3 = false;// inteiro
        boolean x4 = false;// real
        // ler entradas e testa-las
        input = MyIO.readLine();
        while (!input.equals("FIM")) {
            x1 = vogal(input, 0);
            x2 = consoante(input, 0);
            x3 = inteiro(input, 0);
            x4 = real(input, 0, 0);
            if (x1) {
                MyIO.print("SIM ");

            } else {
                MyIO.print("NAO ");
            }
            if (x2) {
                MyIO.print("SIM ");

            } else {
                MyIO.print("NAO ");
            }
            if (x3) {
                MyIO.print("SIM ");

            } else {
                MyIO.print("NAO ");
            }
            if (x4) {
                MyIO.println("SIM");

            } else {
                MyIO.println("NAO");
            }

            input = MyIO.readLine();
        }
    }
}
