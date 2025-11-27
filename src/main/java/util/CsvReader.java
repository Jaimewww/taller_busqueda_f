package util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidad para leer archivos CSV y extraer columnas como arreglos de Double.
 * Soporta valores numericos y fechas en formato ISO 8601.
 * Se usa la libreria Apache Commons CSV para el parsing del CSV.
 * @author Jaime Landazuri, Alejandro Padilla
 */

public class CsvReader {

    // Lee una columna especifica de un archivo CSV y la convierte en un arreglo de Double.
    public static Double[] readDoubleColumn(String filePath, String columnName) throws IOException {
        // Configuracion del formato CSV
        CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreSurroundingSpaces(true)
                .setDelimiter(';')
                .build();

        // Lista para almacenar los valores Double
        List<Double> doubleList = new ArrayList<>();

        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, csvFormat)) {

            for (CSVRecord csvRecord : csvParser) {
                String value = csvRecord.get(columnName);

                // Reemplazar comas por puntos y eliminar espacios
                String cleanedValue = value.replace(',', '.').trim();

                if (!cleanedValue.isEmpty()) {
                    try {
                        Double doubleValue = Double.parseDouble(cleanedValue);
                        doubleList.add(doubleValue);
                    } catch (NumberFormatException e) {
                        // Intentar parsear como fecha en formato ISO 8601
                        try {
                            LocalDateTime date = LocalDateTime.parse(value);
                            double timestamp = (double) date.toEpochSecond(ZoneOffset.UTC);
                            doubleList.add(timestamp);
                        } catch (DateTimeParseException ex) {
                            System.err.println("Ignorando valor inválido: '" + value + "'");
                        }
                    }
                }
            }
        }
        return doubleList.toArray(new Double[0]);
    }
}
