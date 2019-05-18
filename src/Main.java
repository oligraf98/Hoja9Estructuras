//Codigo adaptado de https://rosettacode.org/wiki/Floyd-Warshall_algorithm#Java

import static java.lang.String.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Boolean on = false;
        ArrayList<int[]> a = new ArrayList<>();
        int numVertices = 0;
        do {
            System.out.println("Ingrese la direccion del archivo como se muestra en el ejemplo: C:\\\\Users\\\\ejemplo\\\\Desktop\\\\test.txt");
            String direccion = input.nextLine();
            try {
                System.out.println("1");
                File f = new File(direccion);
                System.out.println("2");
                BufferedReader b = new BufferedReader(new FileReader(f));
                System.out.println("3");
                String readLine;

                ArrayList<String> temp = new ArrayList<>();
                while ((readLine = b.readLine()) != null) {
                    String[] entradas = readLine.split(" ");
                    int codigo = 0;
                    int conexion = 0;
                    int peso = 0;
                    if(!temp.contains(entradas[0])){
                        temp.add(entradas[0]);
                        numVertices++;
                    }

                    codigo = Integer.parseInt(entradas[0]);
                    conexion = Integer.parseInt(entradas[1]);
                    peso = Integer.parseInt(entradas[2]);
                    int[] ey = {codigo, conexion, peso};
                    a.add(ey);
                    /*System.out.println(nombre);
                    System.out.println(tipo);*/

                }
                System.out.println("Se ha creado exitosamente la coleccion.");
                on = true;

            } catch (IOException e) {
                System.out.println("No fue posible crear el grafo con la direccion que especifico. Intentelo de nuevo...");
            }
        }while(on == false);

        System.out.println("Las ciudades son las siguientes\n\t1. Guatemala\n\t2. Xela\n\t3. Escuintla\n\t4. Solola\n\t5. Jutiapa");
        int[][] weights = a.toArray(new int[3][a.size()]);


        floydWarshall(weights, numVertices);
    }

    static void floydWarshall(int[][] weights, int numVertices) {

        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);

        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];

        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }

        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }

        printResult(dist, next);
    }

    static void printResult(double[][] dist, int[][] next) {
        System.out.println("pair     dist    path");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    String path = format("%d -> %d    %2d     %s", u, v,
                            (int) dist[i][j], u);
                    do {
                        u = next[u - 1][v - 1];
                        path += " -> " + u;
                    } while (u != v);
                    System.out.println(path);
                }
            }
        }
    }
}