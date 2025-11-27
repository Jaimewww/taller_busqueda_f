package sorting;

import java.util.Arrays;

/**
 * Implementacion de Selecction Sort (Ordenamiento por seleccion) para arreglos de int
 * @author Jaime Landazuri, Alejandro Padilla
 */

public class SelectionSort {
    public static void sort(int [] array) {
        System.out.println("Estado inicial: " + Arrays.toString(array));
        int length = array.length;
        int minIndex; //Indice del numero menor en el arrgelo
        int swapCount = 0; // Contador de swaps (intercambios)

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

            System.out.println("Min: " + array[minIndex]); // Numero mas pequeño en iteracion
            System.out.println("Swap with min: " + array[i]); // Con que numero se va a intercambiar

            SortUtils.swap(minIndex, i, array);

            swapCount++; // Se aumenta el conteo de intercambios

            System.out.println("  -> Arreglo actual: " + Arrays.toString(array));
        }

        System.out.println("Arreglo ordenado: " + Arrays.toString(array));

        System.out.println("Total de swaps realizados: " + swapCount);
    }
}