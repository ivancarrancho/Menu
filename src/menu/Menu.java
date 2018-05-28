/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;
/**
 *
 * @author ivan
 */
public class Menu {

    /**
     * @param args the command line arguments
     */

    final static char C = '1', X = '0', S = 's', E = 'e', V = '.';

    final static int START_I = 0, START_J = 0;
    final static int END_I = 9, END_J = 9;
    String auxa;
    public static char[][] laberinto = laberinto_completo();

    public static void p_1() {
        Scanner reader = new Scanner(System.in);
        String puntos;
        double datos;
        System.out.println("Ingrese la cantidad de puntos a validar\n ");
        puntos = reader.nextLine();
        int filas = Integer.parseInt(puntos);
        int columnas = 2;
        double[][] matrix = new double[filas][columnas];
        double[][] result = new double[2][2];
        double[][] final_result = new double[3][2];
        final_result[2][0] = 1000000.002;
        String coordenada;
        double fil2;

        for (int fil = 0; fil < filas; fil++) {
            for (int col = 0; col < columnas; col++) {
                try {
                    if (col == 0) {
                        coordenada = "x";
                    }else {
                        coordenada = "y";
                    }
                    fil2 = fil + 1;
                    System.out.println("Ingrese la coordenada "+ coordenada + " del punto " + fil2);
                    matrix[fil][col] = Integer.parseInt(reader.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Debes insertar un número\n Volvamos a empezar....\n ");
                    p_1();
                }

            }
        }

        System.out.println("\n");

        System.out.println("****Puntos ingresados *****");

        for (int fil = 0; fil < filas; fil++) {
            fil2 = fil + 1;
            System.out.print("Punto "+ fil2 +" [");
            for (int col = 0; col < columnas; col++) {
                if (col == 0) {
                    coordenada = "x";
                }else {
                    coordenada = "y";
                }
                System.out.print(coordenada + ": " + matrix[fil][col] + " ");
            }
            System.out.println("]");
        }


        for (int fil = 0; fil < filas; fil++) {
            for (int fil_aux = 0; fil_aux < filas; fil_aux++) {
                result = calc_less_distance(matrix[fil][0], matrix[fil][1], matrix[fil_aux][0], matrix[fil_aux][1]);

                if (
                    (
                        result[0][0] !=
                        result[1][0] ||
                        result[0][1] !=
                        result[1][1]
                    ) && result[2][0] < final_result[2][0]
                ) {
                    // System.out.println("\n \n");
                    // System.out.println("x-1 " + result[0][0] + " x-2 " + result[0][1] + " y-1 " + result[1][0] + " y-2 " + result[1][1] + " result " + result[2][0]);
                    // System.out.println("\n \n");
                    final_result[0][0] = result[0][0];
                    final_result[0][1] = result[0][1];
                    final_result[1][0] = result[1][0];
                    final_result[1][1] = result[1][1];

                    final_result[2][0] = result[2][0];
                }

                //System.out.println("\n \n");
                //System.out.println("x-1 " + final_result[0][0] + " x-2 " + final_result[0][1] + " y-1 " + final_result[1][0] + " y-2 " + final_result[1][1] + " result " + final_result[2][0]);
                //System.out.println("\n \n");
            }
        }
        System.out.println("\n \n");
        System.out.print("Coordenada X: [" + final_result[0][0] + ", ");
        System.out.println(final_result[0][1] + "] ");
        System.out.print("Coordenada Y: [" + final_result[1][0] + ", ");
        System.out.println(final_result[1][1] + "] \n");
        System.out.println("Distancia entre ambos puntos " + final_result[2][0] + " <-----");

        System.out.println("\n \n");
    }

    public static void p_2() {
        Menu maze = new Menu();
        maze.imprimir();

        System.out.println("\n\nEncuentra una ruta recursiva: ");
        maze.resolverRecursion();
    }

    public int tamLaberinto() {
        return laberinto.length;
    }

    public static char[][] laberinto_completo() {
        char[][]  complete_lab = new char[10][10];
        char aux;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 && j == 0) {
                    complete_lab[i][j] = S;
                } else if (i == 9 && j == 9){
                    complete_lab[i][j] = E;
                } else {
                    Random r = new Random();
                    String alphabet = "101";
                    aux = alphabet.charAt(r.nextInt(alphabet.length()));
                    if (aux == '0') {
                        complete_lab[i][j] = X;
                    } else if (aux == '1') {
                        complete_lab[i][j] = C;
                    }
                }
            }
        }

        return complete_lab;

    }


    public void imprimir() {
        for (int i = 0; i < tamLaberinto(); i++) {
            for (int j = 0; j < tamLaberinto(); j++) {
                System.out.print(laberinto[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public char marcar(int i, int j, char valor) {
        assert (isInMaze(i, j));
        char tmp = laberinto[i][j];
        laberinto[i][j] = valor;
        return tmp;
    }

    public char marcar(MazePos pos, char value) {
        return marcar(pos.i(), pos.j(), value);
    }

    public boolean isMarked(int i, int j) {
        assert (isInMaze(i, j));
        return (laberinto[i][j] == V);
    }

    public boolean isMarked(MazePos pos) {
        return isMarked(pos.i(), pos.j());
    }

    public boolean isClear(int i, int j) {
        assert (isInMaze(i, j));
        return (laberinto[i][j] != X && laberinto[i][j] != V);
    }

    public boolean isClear(MazePos pos) {
        return isClear(pos.i(), pos.j());
    }

    //true if cell is within maze 
    public boolean isInMaze(int i, int j) {
        if (i >= 0 && i < tamLaberinto() && j >= 0 && j < tamLaberinto()) {
            return true;
        } else {
            return false;
        }
    }

    //true if cell is within maze 
    public boolean isInMaze(MazePos pos) {
        return isInMaze(pos.i(), pos.j());
    }

    public boolean isFinal(int i, int j) {
        return (i == Menu.END_I && j == Menu.END_J);
    }

    public boolean isFinal(MazePos pos) {
        return isFinal(pos.i(), pos.j());
    }

    @Override
    public char[][] clone() {

        char[][] mazeCopy = new char[tamLaberinto()][tamLaberinto()];
        for (int i = 0; i < tamLaberinto(); i++) {
            System.arraycopy(laberinto[i], 0, mazeCopy[i], 0, tamLaberinto());
        }
        return mazeCopy;
    }

    public void restaurar(char[][] savedMaze) {
        for (int i = 0; i < tamLaberinto(); i++) {
            for (int j = 0; j < tamLaberinto(); j++) {
                laberinto[i][j] = savedMaze[i][j];
            }
        }
    }

    public void resolverRecursion() {

        if (solve(new MazePos(START_I, START_J))) {
            System.out.println("lo tengo: ");
        } else {
            System.out.println("Estás atrapado en el laberinto.");
        }
        imprimir();

    }

    public boolean solve(MazePos pos) {

        //base case
        if (!isInMaze(pos)) {
            return false;
        }
        if (isFinal(pos)) {
            return true;
        }
        if (!isClear(pos)) {
            return false;
        }

        //posición actual debe ser clara
        assert (isClear(pos));


        marcar(pos, V);

        if (solve(pos.irSur())) {

            return true;
        }

        if (solve(pos.IrOeste())) {

            return true;
        }

        if (solve(pos.irNorte())) {
            return true;
        }

        if (solve(pos.IrEste())) {
            return true;
        }

        marcar(pos, C);

        return false;
    }

    public static void p_3() {
        System.out.println("En construcción");

    }

    public static double[][] calc_less_distance(double x_1, double y_1, double x_2, double y_2) {
        double menor_distancia = 1000000;
        double aux;
        double menor_p1;
        double menor_p2;
        double[][] menor_puntos = new double[3][2];

        double base_1 = x_2 - x_1;
        double base_2 = y_2 - y_1;

        menor_p1 = (double)Math.pow(base_1, 2);
        menor_p2 = (double)Math.pow(base_2, 2);

        aux = (double) Math.sqrt(menor_p1 + menor_p2);


        menor_distancia = (double) Math.sqrt(menor_p1 + menor_p2);
        menor_puntos[0][0] = x_1;
        menor_puntos[0][1] = y_1;
        menor_puntos[1][0] = x_2;
        menor_puntos[1][1] = y_2;
        menor_puntos[2][0] = menor_distancia;
        menor_puntos[2][1] = 0;
        //System.out.println("x-1 " + x_1 + " x-2 " + y_1 + " y-1 " + x_2 + " y-2 " + y_2 + " result " + menor_distancia);
        return menor_puntos;

    }

    class MazePos {

        int i, j;

        public MazePos(int i, int j) {
            this.i = i;
            this.j = j;
        }

        ;
        public int i() {
            return i;
        }

        public int j() {
            return j;
        }

        public void print() {
            System.out.println("(" + i + "," + j + ")");
        }

        public MazePos irNorte() {
            return new MazePos(i - 1, j);
        }

        public MazePos irSur() {
            return new MazePos(i + 1, j);
        }

        public MazePos IrEste() {
            return new MazePos(i, j + 1);
        }

        public MazePos IrOeste() {
            return new MazePos(i, j - 1);
        }

    };
    
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Dividir y Vencer");
            System.out.println("2. Programación Dinámica");
            System.out.println("3. Vuelta Atrás");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        p_1();
                        break;
                    case 2:
                        p_2();
                        break;
                    case 3:
                        p_3();
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
}
