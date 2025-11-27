package sorting;

/**
 * Utilidades para los metodos de ordenamiento
 * @author Jaime Landazuri, Alejandro Padilla
 */

public class SortUtils {

    /**
     * Este metodo guarda una variable temporal para realizar un intercambio en un arreglo
     * @param x
     * @param y
     * @param array
     */
    public static void swap(int x, int y, int[] array) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
