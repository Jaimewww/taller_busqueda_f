import sorting.SelectionSort;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            String DATASET_PATH = "src/main/java/resources/pacientes_500.csv";
            String COLUMN_NAME = "prioridad";
            Integer originalData[] = util.CsvReader.readIntegerColumn(DATASET_PATH, COLUMN_NAME);
            System.out.println(Arrays.toString(originalData));
            SelectionSort.sort(originalData);
            System.out.println("Arreglo ordenado: " + Arrays.toString(originalData));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
