package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import structures.Node;
import structures.SimpleList;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    // Lee una columna especifica de un archivo CSV y la convierte en un arreglo de Integer.
    public static Integer[] readIntegerColumn(String filePath, String columnName) throws IOException {
        // Configuracion del formato CSV
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        // Lista para almacenar los valores Integer
        List<Integer> integerList = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);

                // Reemplazar comas por puntos y eliminar espacios
                String cleanedValue = value.replace(',', '.').trim();

                if (!cleanedValue.isEmpty()) {
                    try {
                        Integer integerValue = Integer.parseInt(cleanedValue);
                        integerList.add(integerValue);
                    } catch (NumberFormatException e) {
                        System.err.println("Ignorando valor inválido: '" + value + "'");
                    }
                }
            }
        }
        return integerList.toArray(new Integer[0]);
    }

    public static SimpleList<Integer> readIntegerColumnList(String filePath, String columnName) throws IOException {
        // Configuracion del formato CSV (mismo formato que el metodo original)
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();
        SimpleList<Integer> integerList = new SimpleList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);

                // Reemplazar comas por puntos y eliminar espacios
                String cleanedValue = value.replace(',', '.').trim();

                if (!cleanedValue.isEmpty()) {
                    try {
                        Integer integerValue = Integer.parseInt(cleanedValue);
                        integerList.pushBack(integerValue);

                    } catch (NumberFormatException e) {
                        System.err.println("Ignorando valor inválido: '" + value + "'");
                    }
                }
            }
        }

        return integerList; // Retorna la lista enlazada simple.
    }
}