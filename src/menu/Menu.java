/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author ivan
 */
public class Menu {

    /**
     * @param args the command line arguments
     */


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
        System.out.println("En construcción");

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

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Dividir y Vencer");
            System.out.println("2. Programación Dinámica");
            System.out.println("3. Opcion 3");
            System.out.println("4. Vuelta Atrás");

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
