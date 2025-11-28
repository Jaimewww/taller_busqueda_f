package util;

import sorting.SelectionSort;
import structures.Node;
import structures.SimpleList;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SearchDemo {

    private static final Scanner sc = new Scanner(System.in);

    private static final String[] FILES = {
            "estudiantes_notas.csv",
            "inventario_500_inverso.csv",
            "pacientes_500.csv",
            "productos_inventario.csv"
    };

    private static final String[] COLUMNS = {
            "edad",
            "stock",
            "prioridad",
            "stock"
    };

    public static void iniciar() {

        while (true) {
            System.out.println("\n-----------------------------------------------------");
            System.out.println("                DEMO DE BÚSQUEDAS - SLL & ARRAY      ");
            System.out.println("-----------------------------------------------------");
            System.out.println("Seleccione un archivo para cargar:");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. estudiantes_notas.csv");
            System.out.println("2. inventario_500_inverso.csv");
            System.out.println("3. pacientes_500.csv");
            System.out.println("4. productos_inventario.csv");
            System.out.println("0. Salir");
            System.out.println("-----------------------------------------------------");
            System.out.print("Opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            if (option == 0) {
                System.out.println("Saliendo...");
                return;
            }

            if (option < 1 || option > 4) {
                System.out.println("Opción inválida");
                continue;
            }

            String fileName = FILES[option - 1];
            String columnName = COLUMNS[option - 1];

            System.out.println("\nCargando archivo: " + fileName);

            try {
                procesarArchivo(fileName, columnName);
                System.out.println("-----------------------------------------------------\n");

            } catch (Exception error) {
                System.out.println("Error: " + error.getMessage());
            }
        }
    }

    /**
     * Carga el CSV y ejecuta las búsquedas en arreglo y SLL.
     */
    private static void procesarArchivo(String fileName, String columnName) throws Exception {

        Integer[] datos = loadIntegerColumn(fileName, columnName);

        if (datos.length == 0) {
            System.out.println("Error: archivo vacío o columna incorrecta.");
            return;
        }

        System.out.println("Archivo cargado correctamente: " + datos.length + " registros.");

        System.out.println("\n-----------------------------------------------------");
        System.out.println("                  ¿QUÉ DESEA PROBAR?                 ");
        System.out.println("-----------------------------------------------------");
        System.out.println("1. Búsquedas en ARRAY");
        System.out.println("2. Búsquedas en LISTA ENLAZADA SIMPLE (SLL)");
        System.out.println("-----------------------------------------------------");
        System.out.print("Opción: ");

        int op = sc.nextInt();
        sc.nextLine();

        if (op == 1) {
            runArrayDemo(datos);
        } else if (op == 2) {
            SimpleList<Integer> list = toSimpleList(datos);
            runLinkedListDemo(list);
        } else {
            System.out.println("Opción inválida");
        }
    }

    // ===========================================================
    //   CARGA DE CSV
    // ===========================================================
    public static Integer[] loadIntegerColumn(String file, String col) throws IOException {
        String[] paths = {"src/main/resources/", "src/main/java/resources/"};
        for (String p : paths) {
            try {
                return CsvReader.readIntegerColumn(p + file, col);
            } catch (IOException ignore) {}
        }
        throw new IOException("Archivo no encontrado en " + Arrays.toString(paths));
    }

    // ===========================================================
    //   ARRAY DEMO – versión EXACTA de la práctica
    // ===========================================================
    public static void runArrayDemo(Integer[] data) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("                DEMO CON ARREGLOS                    ");
        System.out.println("-----------------------------------------------------");

        int[] keys = {7, 5, 2, 42};

        for (int key : keys) {
            System.out.println("\nBuscando clave: " + key + "");
            System.out.println("-----------------------------------------------------");

            // ----------- Primera coincidencia -----------
            try {
                int value = ArrayUtils.firstCoincidence(data, key);
                int idx = firstIndexOf(data, key);
                System.out.println("Primera coincidencia: valor = " + value + ", índice = " + idx);
            } catch (NoSuchElementException ex) {
                System.out.println("Primera coincidencia no encontrada");
            }

            // ----------- Última coincidencia -----------
            try {
                int value = ArrayUtils.lastCoincidence(data, key);
                int idx = lastIndexOf(data, key);
                System.out.println("Última coincidencia: valor = " + value + ", índice = " + idx);
            } catch (NoSuchElementException ex) {
                System.out.println("Última coincidencia no encontrada");
            }

            // ----------- findAll índices -----------
            try {
                SimpleList<SearchResult> results = ArrayUtils.findAll(data, key);
                System.out.print("findAll: índices = ");
                printSearchResultIndices(results);
            } catch (NoSuchElementException ex) {
                System.out.println("findAll vacío");
            }

            // ----------- Secuencial con centinela -----------
            try {
                Integer found = ArrayUtils.findWithCentinel(Arrays.copyOf(data, data.length), key, key);
                System.out.println("Secuencial con centinela: encontrado = " + found);
            } catch (NoSuchElementException ex) {
                System.out.println("Secuencial con centinela no encontrado");
            }

            // ----------- Búsqueda binaria -----------
            try {
                Integer[] copy = Arrays.copyOf(data, data.length);
                SelectionSort.sort(copy);
                int idx = ArrayUtils.binarySearch(copy, key);
                System.out.println("Búsqueda binaria: índice = " + idx);
            } catch (NoSuchElementException ex) {
                System.out.println("Búsqueda binaria no encontrado");
            }
        }
    }

    public static int firstIndexOf(Integer[] a, int key) {
        for (int i = 0; i < a.length; i++)
            if (a[i] == key) return i;
        throw new NoSuchElementException();
    }

    public static int lastIndexOf(Integer[] a, int key) {
        for (int i = a.length - 1; i >= 0; i--)
            if (a[i] == key) return i;
        throw new NoSuchElementException();
    }

    public static void printSearchResultIndices(SimpleList<SearchResult> list) {
        if (list.isEmpty()) {
            System.out.println("[]");
            return;
        }
        List<Node<SearchResult>> nodes = list.findAll(n -> true);
        System.out.print("[ ");
        for (Node<SearchResult> n : nodes) {
            System.out.print(n.getValue().getIndex() + " ");
        }
        System.out.println("]");
    }

    public static SimpleList<Integer> toSimpleList(Integer[] data) {
        SimpleList<Integer> list = new SimpleList<>();
        for (Integer v : data) list.pushBack(v);
        return list;
    }

    public static void runLinkedListDemo(SimpleList<Integer> list) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("            DEMO CON LISTA ENLAZADA SIMPLE           ");
        System.out.println("-----------------------------------------------------");
        int key = 3;

        // ----------- Primero que coincide -----------
        try {
            Node<Integer> first = list.findFirst(key);
            System.out.print("Primera coincidencia de " + key + ": ");
            printNode(first);
        } catch (NoSuchElementException ex) {
            System.out.println("Primera coincidencia no encontrada");
        }

        // ----------- Último que coincide -----------
        try {
            Node<Integer> last = list.findLast(key);
            System.out.print("Última coincidencia de " + key + ": ");
            printNode(last);
        } catch (NoSuchElementException ex) {
            System.out.println("Última coincidencia no encontrada");
        }

        // ----------- findAll con predicado (value < 3) -----------
        System.out.println("findAll (value < 3): ");
        List<Node<Integer>> nodes = list.findAll(node -> node.getValue() < 3);
        if (nodes.isEmpty()) System.out.println("(vacío)");
        else for (Node<Integer> n : nodes) printNode(n);
    }

    public static void printNode(Node<?> n) {
        if (n == null) {
            System.out.println("null");
            return;
        }
        System.out.println("Node {value = " + n.getValue() + "}");
    }

    public static void main(String[] args) {
        iniciar();
    }
}
