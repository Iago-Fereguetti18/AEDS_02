import java.util.ArrayList;

public class Algebrabool {

    static ArrayList<Integer> lista = new ArrayList<>();
    static String texto;
    static String buffer;
    static int bufferpos = 0;
    static Boolean retorno;

    // buffer para receber vaiaveis
    static boolean BufferReader() {
        if (buffer.charAt(bufferpos) == '\n')
            return true;
        texto = "";
        // verificar ( )
        char t = buffer.charAt(bufferpos);
        bufferpos++;
        while (t != '(' && t != ')') {
            if (t == '\n')
                return true;
            // verificar ,
            if (t != ',') {
                // tirar ' '
                if (t != ' ') {
                    texto += t;
                }
            } else
                return false;
            t = buffer.charAt(bufferpos);
            bufferpos++;
        }
        return true;
    }

    //metodo para intentificar 'or','and' e 'not'
    static boolean read() {
        do {
            retorno = BufferReader();
        } while (texto.length() == 0);
        switch (texto) {
            case "and":
                boolean a = read();
                while (!retorno)
                    a = (read() && a);
                retorno = BufferReader();
                return a;

            case "or":
                boolean o = read();
                while (!retorno)
                    o = (read() || o);
                retorno = BufferReader();
                return o;

            case "not":
                boolean n = !read();
                retorno = BufferReader();
                return n;

            default:
                int t = texto.charAt(0);
                t = lista.get(t - 65);
                return (t == 1);
        }
    }

    public static void main(String[] args) {
        int numero = MyIO.readInt();
        //rodar até o valor ser 0
        while (numero > 0) {
            lista.clear();
            for (int i = 0; i < numero; i++) {
                lista.add(MyIO.readInt());
            }
            buffer = MyIO.readLine();
            buffer += '\n';
            bufferpos = 0;
            System.out.println((read() ? 1 : 0));
            numero = MyIO.readInt();
        }
    }
}
