package nulp;

public class Main {

    static int NUM_OF_VERTEX = ReadFile.vertex();

    public static void main(String[] args) {
        int[][] array = ReadFile.matrix();
        int[][] connectivityMatrix = new int[NUM_OF_VERTEX][NUM_OF_VERTEX];

        int[] c = new int[NUM_OF_VERTEX];
        int[] path = new int[NUM_OF_VERTEX];

        System.out.println("Number of vertex: " + NUM_OF_VERTEX);
        System.out.println("Matrix of weight: ");
        ReadFile.printMatrix(array);
        connectivityMatrix(connectivityMatrix, array);
        ifGamilton(connectivityMatrix, c, path);
        printWay(array, path);
    }

    static void connectivityMatrix(int[][] connectivityMatrix, int[][] array) {
        System.out.println("Connectivity matrix: ");
        for (int i = 0; i < NUM_OF_VERTEX; i++) {
            for (int j = 0; j < NUM_OF_VERTEX; j++) {
                if (array[i][j] > 0) {
                    connectivityMatrix [i][j] = 1;
                }
                else {
                    connectivityMatrix [i][j] = array[i][j];
                }
                System.out.print(connectivityMatrix [i][j] + "\t");
            }
            System.out.println();
        }
    }

    static boolean gamilton(int k, int[][] conMatr, int[] c, int[] path, int v0)
    {
        int k1;
        boolean q1 = false;
        for (int v = 0; v < NUM_OF_VERTEX && !q1; v++)
        {
            if (!(conMatr[v][path[k - 1]]==0) || !(conMatr[path[k - 1]][v]==0))
            {
                if (k == NUM_OF_VERTEX && v == v0) {
                    q1 = true;
                }
                else if (c[v] == -1)
                {
                    c[v] = k; path[k] = v;
                    k1 = k + 1;
                    q1 = gamilton(k1, conMatr, c, path, v0);
                    if (!q1) c[v] = -1;
                }
            }
        }
        return q1;
    }

    static void ifGamilton(int[][] conMatr, int[] c, int[] path) {
        int v0 = 0;
        for (int j = 0; j < NUM_OF_VERTEX; j++) {
            c[j] = -1;
        }
        path[0] = v0;
        c[v0] = v0;

        gamilton(1, conMatr, c, path, v0);
    }

    static void printWay(int[][] array, int[] path) {
        int weight = 0;
        System.out.println(" Edge : Weight");
        for (int i = 0; i < NUM_OF_VERTEX; i++) {
            if (i == NUM_OF_VERTEX - 1) {
                System.out.println(" " + path[i] +  " - " + path[0] + " : " + array[path[i]][path[0]]);
                weight += array[path[i]][path[0]];
            }
            else {
                System.out.println(" " + path[i] + " - " + path[i + 1] + " : " + array[path[i]][path[i + 1]]);
                weight += array[path[i]][path[i + 1]];
            }
        }
        System.out.println("\n Total path weight: " + weight);
    }
}
