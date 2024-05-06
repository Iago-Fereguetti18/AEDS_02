import java.util.ArrayList;

public class Algebraboolrecur {

    static ArrayList<Integer> lista = new ArrayList<>();

    static String resolve(String test, int pos0) {
        int posF = pos0 + 2;
        boolean r;
        if (test.charAt(pos0) == 't') {
            r = (test.charAt(posF) == 'F');
            posF++;
        } else {
            r = (test.charAt(posF) == 'T');
            posF++;
            while (test.charAt(posF) != ')') {
                if (test.charAt(pos0) == 'a')
                    r = (r && test.charAt(posF) == 'T');
                if (test.charAt(pos0) == 'r')
                    r = (r || test.charAt(posF) == 'T');
                posF++;
            }
        }
        int i = 0;
        String ret = "";
        while (test.charAt(i) != '\n') {
            if (i == pos0) {
                ret += (r ? "T" : "F");
                i = posF;
            } else
                ret += test.charAt(i);
            i++;
        }
        ret += "\n";
        return ret;
    }

    static String filter(String test) {

        int n = 0;
        test += "\n";

        String result = "";

        while (test.charAt(n) != '\n') {
            if (test.charAt(n) == 'a' || test.charAt(n) == 'r' || test.charAt(n) == 't' || test.charAt(n) == '('
                    || test.charAt(n) == ')')
                result += test.charAt(n);
            int m = test.charAt(n);
            if (m >= 65 && m <= 90) {
                m = lista.get(m - 65);
                result += ((m == 1) ? "T" : "F");
            }
            n++;
        }
        result += "\n";

        return result;
    }

    static int bufferedSimplify(String test) {
        int pos0 = 0;
        int n = 0;

        while (test.charAt(n) != '\n') {
            if (test.charAt(n) == 'a' || test.charAt(n) == 'r' || test.charAt(n) == 't')
                pos0 = n;
            if (test.charAt(n) == ')') {
                test = resolve(test, pos0);
                n = -1;
            }
            n++;
        }
        return ((test.charAt(0) == 'T') ? 1 : 0);
    }

    public static void main(String[] args) {
        int number = MyIO.readInt();
        while (number > 0) {
            // create and clear an array list with starting variables
            lista.clear();
            for (int i = 0; i < number; i++) {
                lista.add(MyIO.readInt());
            }
            String read = MyIO.readLine();

            System.out.println(bufferedSimplify(filter(read)));
            number = MyIO.readInt();
        }
    }

}
