package sorting;

import java.util.Arrays;

/**
 * Implementacion de Selecction Sort (Ordenamiento por seleccion) para arreglos de int
 * @author Jaime Landazuri
 */

public class SelectionSort {
    public static void sort(Integer[] array) {
        int length = array.length;
        int minIndex; //Indice del numero menor en el arrgelo

        for(int i = 0;  i < length; i++ ) { // Bucle principal, recorre todo el arreglo para hacer los intercambios
            minIndex = i;
            for (int j = i + 1; j < length; j++) { // Bucle interno en el que se busca el numero menor para hacer el
                                                    // intercambio con el numero del indice i
                if(array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if(minIndex == i) { // Si el numero minimo ya esta en su posicion, no se hace swap
                continue;
            }

            SortUtils.swap(minIndex, i, array);
        }
    }
}