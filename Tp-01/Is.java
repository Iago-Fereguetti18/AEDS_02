import java.util.*;

public class Is {
    static boolean vogal(String input) {
        String output;
        output = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (output.charAt(i) != 'a' && output.charAt(i) != 'e' && output.charAt(i) != 'i' && output.charAt(i) != 'o'
                    && output.charAt(i) != 'u') {
                return false;
            }
        }
        return true;
    }

    static boolean consoante(String input) {
        String output;
        output = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            if (vogal(Character.toString(output.charAt(i))) || output.charAt(i) < 'a' || output.charAt(i) > 'z') {
                return false;
            }
        }
        return true;
    }

    static boolean inteiro(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) < 48 || input.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    static boolean real(String input) {
        // input.charAt(i) == '.' || input.charAt(i) == ','
        int contador = 0;
        for (int i = 0; i < input.length(); i++) {
            if(contador > 1){
                return false;
            }
                if (input.charAt(i) == 46 || input.charAt(i) == 44) {
                    contador++;
                }else{
                    if (input.charAt(i) < 48 || input.charAt(i) > 57) {
                        return false;
                    }
                }
            }
        return true;
    }

    public static void main(String[] args) {
        // definir dados
        String input = "";
        boolean x1 = false;// vogal
        boolean x2 = false;// consoante
        boolean x3 = false;// inteiro
        boolean x4 = false;// real
        // ler entradas e testa-las
        input = MyIO.readLine();
        while (!input.equals("FIM")) {
            x1 = vogal(input);
            x2 = consoante(input);
            x3 = inteiro(input);
            x4 = real(input);
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
