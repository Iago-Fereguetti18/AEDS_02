import java.util.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class Arquivo {
    public static void main(String[] args) {
        // definir dados
        int n = 0;
        double m = 0;
        try {
            // abrir arquivo
            RandomAccessFile arquivo = new RandomAccessFile("arquivo.txt", "rw");

            // receber input
            n = MyIO.readInt();
            for (int i = 0; i < n; i++) {
                arquivo.writeDouble(MyIO.readDouble());
            }
            arquivo.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // DecimalFormat form = new DecimalFormat();
            // form.format(float);
            RandomAccessFile ler = new RandomAccessFile("arquivo.txt", "r");
            long i = ler.length() - 8;
            while (i >= 0) {
                ler.seek(i);
                m = ler.readDouble();
                // MyIO.println(form.format(ler.readFloat()));
                if (m % 1 == 0) {
                    MyIO.println((int)m);
                } else {
                    MyIO.println(m);
                }
                i -= 8;
            }

            ler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
