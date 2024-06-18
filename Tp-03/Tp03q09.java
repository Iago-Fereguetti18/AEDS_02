import java.util.*;
import java.util.Scanner;

class Cell {
    public Cell ant;
    public Cell prox;
    public Cell sup;
    public Cell inf;
    public int elemento;

    public Cell() {
        this.elemento = 1;
        this.ant = null;
        this.prox = null;
        this.sup = null;
        this.inf = null;
    }

    public Cell(int x) {
        this.elemento = x;
        this.ant = null;
        this.prox = null;
        this.sup = null;
        this.inf = null;
    }
}

class Matriz {
    private Cell inicio;
    int linha, coluna;

    public Matriz(int l, int c) {
        this.linha = l;
        this.coluna = c;
        this.inicio = new Cell();

        Cell tmp = inicio;
        Cell i = inicio;
        Cell j = inicio;
        int count = 2;

        for (int v = 0; v < linha; v++) {
            for (int k = 0; k < coluna - 1; k++) {
                i.prox = new Cell(count);
                count++;
                i.prox.ant = i;
                i = i.prox;
                if (v > 0) {
                    tmp.inf = i;
                    i.sup = tmp;
                    tmp = tmp.prox;
                }
            }
            if (v < linha - 1) {
                j.inf = new Cell(count);
                count++;
                j.inf.sup = j;
                tmp = j.prox;
                j = j.inf;
                i = j;
            }
        }
    }

    public void inserir(int x, int i, int j) {
        Cell tmp = inicio;
        for (int k = 0; k < i; k++) {
            tmp = tmp.inf;
        }
        for (int k = 0; k < j; k++) {
            tmp = tmp.prox;
        }
        tmp.elemento = x;
    }

    public void mostar() {
        for (Cell i = inicio; i != null; i = i.inf) {
            for (Cell j = i; j != null; j = j.prox) {
                System.out.print(j.elemento + " ");
            }
            System.out.println();
        }
    }

    public void mostrarDiagonalPri() {
        Cell i = inicio;
        while (i != null) {
            System.out.print(i.elemento + " ");
            i = (i.inf != null) ? i.inf.prox : null;
        }
        System.out.println();
    }

    public void mostrarDiagonalSec() {
        Cell i;
        for (i = inicio; i.prox != null; i = i.prox)
            ;
        while (i != null) {
            System.out.print(i.elemento + " ");
            i = (i.inf != null) ? i.inf.ant : null;
        }
        System.out.println();
    }

    public Matriz soma(Matriz m2) {
        Matriz resp = new Matriz(m2.linha, m2.coluna);
        // setar linha
        Cell indexLinha = resp.inicio;
        Cell linha1 = inicio;
        Cell linha2 = m2.inicio;

        while (linha1 != null && linha2 != null) {
            // setar coluna
            Cell indexCol = indexLinha;
            Cell col1 = linha1;
            Cell col2 = linha2;
            while (col1 != null && col2 != null) {
                // somar
                indexCol.elemento = col1.elemento + col2.elemento;
                // pra direita
                col1 = col1.prox;
                col2 = col2.prox;
                indexCol = indexCol.prox;
            }
            // pra baixo
            linha1 = linha1.inf;
            linha2 = linha2.inf;
            indexLinha = indexLinha.inf;
        }

        return resp;
    }

    public Matriz mulMatriz(Matriz m2) {
        Matriz resp = new Matriz(this.linha, m2.coluna);
        if (this.linha == m2.linha && this.coluna == m2.coluna) {
            Cell resp_index = resp.inicio;
            Cell resp_index_AUX = resp.inicio;
            Cell index_I = this.inicio;// referencia para a primeiro celula da matriz
            Cell index_J = m2.inicio; // referencio para a primeira celula da matriz2
            Cell index_AUX_I = this.inicio;// auxiliar i
            Cell index_AUX_J = m2.inicio;// auxiliar j
            resp_index.elemento = 0;
            while (resp_index != null && index_I != null) {
                while (index_J != null) {
                    resp_index.elemento = 0;
                    while (index_J != null) {
                        resp_index.elemento += (index_I.elemento * index_J.elemento);
                        index_I = index_I.prox;
                        index_J = index_J.inf;
                    }
                    // avancar para direita
                    resp_index = resp_index.prox;
                    index_AUX_J = index_AUX_J.prox;
                    index_J = index_AUX_J;
                    index_I = index_AUX_I;
                }
                // avancar para baixo
                resp_index_AUX = resp_index_AUX.inf;
                resp_index = resp_index_AUX;
                index_AUX_I = index_AUX_I.inf;
                index_AUX_J = m2.inicio;
                index_J = index_AUX_J;
                index_I = index_AUX_I;
            }
        }
        return resp;
    }
}

public class Tp03q09 {

    /*
     * A entrada padrão é composta por vários casos de teste sendo que
     * o número de casos é um inteiro contido na primeira linha da entrada.
     * 
     * Em seguida, temos cada um dos casos de teste.
     * Cada caso é composto por duas matrizes.
     * Para cada caso de teste,
     * 
     * temos que suas duas primeiras linhas contêm
     * um número inteiro cada representando os números de linhas e de colunas da
     * primeira matriz, respectivamente.
     * Em seguida, temos os elementos da primeira matriz que estão representados nas
     * próximas
     * l linhas onde l é o número de linhas dessa matriz.
     * Cada uma dessas linhas têm c colunas onde c é o número de colunas dessa
     * matriz.
     * 
     * Nas duas linhas seguintes, temos os números de linhas e colunas da segunda
     * matriz do caso de teste.
     * As l2 linhas seguintes têm c2 colunas contendo os elementos da segunda
     * matriz.
     * l2 e c2 correspondem aos números de linhas e colunas da segunda matriz do
     * caso de teste, respectivamente.
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        Matriz matriz;
        Matriz matriz2;
        int linha, coluna;
        for (int i = 0; i < n; i++) {
            // primeiro
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
            matriz = new Matriz(linha, coluna);
            for (int j = 0; j < matriz.linha; j++) {
                for (int j2 = 0; j2 < matriz.coluna; j2++) {
                    matriz.inserir(scanner.nextInt(), j, j2);
                }
            }

            // segundo
            linha = scanner.nextInt();
            coluna = scanner.nextInt();
            matriz2 = new Matriz(linha, coluna);
            for (int j = 0; j < matriz2.linha; j++) {
                for (int j2 = 0; j2 < matriz2.coluna; j2++) {
                    matriz2.inserir(scanner.nextInt(), j, j2);
                }
            }

            // mostrar as matrizes
            Matriz result = new Matriz(linha, coluna);
            matriz.mostrarDiagonalPri();
            matriz.mostrarDiagonalSec();
            result = matriz.soma(matriz2);
            result.mostar();
            result = matriz.mulMatriz(matriz2);
            result.mostar();
        }

        scanner.close();
    }
}
