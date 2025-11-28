package util;

import structures.SimpleList;

import java.util.NoSuchElementException;

/**
 * Clase con metodos utilitarios para arreglos, como busquedas lineales y binarias
 * @author Jaime Landazuri
 */

public class ArrayUtils {
    public static SimpleList<SearchResult> findAll(Integer[] array, Integer key) throws NoSuchElementException {
        SimpleList<SearchResult> results = new SimpleList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(key)) {
                SearchResult result = new SearchResult(i, array[i]);
                results.pushBack(result);
            }
        }
        if (results.isEmpty()) {
            throw new NoSuchElementException("Elemento no encontrado: " + key);
        }
        return results;
    }

    // Retorna la primera coincidencia del elemento key en el arreglo
    public static Integer firstCoincidence(Integer[] array, Integer key) throws NoSuchElementException {
        for (Integer integer : array) {
            if (integer.equals(key)) {
                return integer;
            }
        }
        throw new NoSuchElementException("Elemento no encontrado: " + key);
    }

    // Retorna la ultima coincidencia del elemento key en el arreglo
    public static Integer lastCoincidence(Integer[] array, Integer key) throws NoSuchElementException {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i].equals(key)) {
                return array[i];
            }
        }
        throw new NoSuchElementException("Elemento no encontrado: " + key);
    }

    // Retorna la primera coincidencia del elemento key en el arreglo usando centinela
    public static Integer findWithCentinel(Integer[] array, Integer key, Integer centinel) throws NoSuchElementException {
        Integer lastElement = array[array.length - 1];
        array[array.length - 1] = centinel;

        int i = 0;
        while (!array[i].equals(key)) {
            i++;
        }

        array[array.length - 1] = lastElement;

        if (i < array.length - 1 || array[array.length - 1].equals(key)) {
            return array[i];
        } else {
            throw new NoSuchElementException("Elemento no encontrado: " + key);
        }
    }

    // Retorna el indice de la aparicion del elemento key en el arreglo usando busqueda binaria
    public static int binarySearch(Integer[] array, Integer key) throws NoSuchElementException {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid].equals(key)) {
                return mid; // ahora retorna índice
            }

            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        throw new NoSuchElementException("Elemento no encontrado: " + key);
    }

    // Retorna el indice de la primera aparicion del elemento key en el arreglo usando busqueda binaria
    public static int lowerBound(Integer[] array, Integer key) throws NoSuchElementException {
        int left = 0;
        int right = array.length - 1;
        int result = -1; // Almacenamos el indice candidato

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid].equals(key)) {
                result = mid;     // Encontramos una coincidencia
                right = mid - 1;  // Sigue buscando en la mitad izquierda
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (result != -1) {
            return result; // Retornamos el indice de la primera coincidencia
        } else {
            throw new NoSuchElementException("Elemento no encontrado: " + key);
        }
    }

    // Retorna el indice de la ultima aparicion del elemento key en el arreglo usando busqueda binaria
    public static int upperBound(Integer[] array, Integer key) throws NoSuchElementException {
        int left = 0;
        int right = array.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid].equals(key)) {
                result = mid;    // Encontramos una coincidencia
                left = mid + 1;  // Sigue buscando en la mitad derecha
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (result != -1) {
            return result; // Retornamos el indice de la ultima coincidencia
        } else {
            throw new NoSuchElementException("Elemento no encontrado: " + key);
        }
    }


}
