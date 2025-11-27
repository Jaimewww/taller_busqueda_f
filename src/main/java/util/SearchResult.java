package util;

/**
 * Clase para almacenar el resultado de una busqueda en un arreglo
 * @author Jaime Landazuri
 */

public class SearchResult {
    private int index;
    private int value;

    public SearchResult(int index, int value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{Index: " + index + ", Value: " + value + "}";
    }
}