import java.util.*;
/**
 A ideia b´asica ´e um simples deslocamento
de caracteres. Assim, por exemplo, se a chave utilizada para criptografar as mensagens for 3,
todas as ocorrˆencias do caractere ’a’ s˜ao substitu´ıdas pelo caractere ’d’, as do ’b’ por ’e’, e assim
sucessivamente. Crie um m´etodo iterativo que recebe uma string como parˆametro e retorna
outra contendo a entrada de forma cifrada. Neste exerc´ıcio, suponha a chave de ciframento trˆes.
Na sa´ıda padr˜ao, para cada linha de entrada, escreva uma linha com a mensagem criptografada.
 */
public class Ciframento {
    public static String cesar(String input){
        char[] chars = input.toCharArray();
        for(int i = 0; i < chars.length; i++){
            chars[i] = (char)(chars[i] + 3);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        while (true) { 
        String antes = MyIO.readLine();
        if (antes.equals("FIM")) {
            break;
        }

        String depois = cesar(antes);
        MyIO.println(depois);
    }

    }
}