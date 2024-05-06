public class Ciframentorecur {
    public static String cesar(char[] chars, int n, int i) {

        chars[i] = (char) (chars[i] + 3);

        new String(chars);
        if (i >= n - 1) {
            return new String(chars);
        }
        return cesar(chars, n, i + 1);
    }

    public static void main(String[] args) {
        while (true) {
            String antes = MyIO.readLine();
            char[] chars = antes.toCharArray();
            int n = chars.length;
            if (antes.equals("FIM")) {
                break;
            }

            String depois = cesar(chars, n, 0);
            MyIO.println(depois);
        }
    }
}
