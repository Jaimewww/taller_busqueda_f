
package structures;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class SimpleList<T> {
     Node<T> head;

    public SimpleList() {
        this.head = null;
    }

    // Inserta nodo al inicio de la lista
    public void pushFront(T newData){
        Node<T> newNode = new Node<>(newData);
        newNode.next = head;
        head = newNode;
    }

    // Inserta nodo al final de la lista
    public void pushBack(T newData){
        Node<T> newNode = new Node<>(newData);
        if(head == null){
            head = newNode;
            return;
        }
        Node<T> last = head;
        while(last.next != null){
            last = last.next;
        }
        last.next = newNode;
    }

    // Busca un nodo por su valor y devuelve el valor si lo encuentra, -1 si no
    public T find(T key){
        Node<T> current = head;
        while(current != null){
            if(current.value == key){
                return current.value;
            }
            current = current.next;
        }
        throw new NoSuchElementException();
    }

    // Elimina un nodo por su valor y devuelve el valor si lo encuentra, -1 si no
    public T remove(T key){
        Node<T> current = head;
        Node<T> prev = null;

        // Si el nodo a eliminar es el head
        if(current != null && current.value == key){
            head = current.next;
            return current.value;
        }

        while(current != null && current.value != key){
            prev = current;
            current = current.next;
        }

        if(current == null){
            throw new NoSuchElementException();
        }

        prev.next = current.next;
        return current.value;
    }

    // Devuelve el tamaño de la lista haciendo un recorrido y conteo
    public int size(){
        int count = 0;
        Node<T> current = head;
        while(current != null){
            count++;
            current = current.next;
        }
        return count;
    }

    // Verifica si la lista está vacía
    public boolean isEmpty(){
        return head == null;
    }

    // Vacía la lista haciendola apuntar a null
    public void clear(){
        head = null;
    }

    public static <T> Node<T> firstCoincidence(Node<T> head, Predicate<T> p) throws NoSuchElementException {
        Node<T> current = head;

        while (current != null) {
            if (p.test(current.value)) {
                return current; // Retorna inmediatamente al encontrar el primero
            }
            current = current.next;
        }

        throw new NoSuchElementException("Elemento no encontrado"+p.toString()); // No se encontró ninguna coincidencia
    }
    public static <T> Node<T> lastCoincidence(Node<T> head, Predicate<T> p) {
        Node<T> current = head;
        Node<T> lastMatch = null; // Guarda la última coincidencia encontrada

        while (current != null) {
            if (p.test(current.value)) {
                lastMatch = current; // Actualiza cada vez que encuentra una coincidencia
            }
            current = current.next;
        }

        if (lastMatch == null) {
            throw new NoSuchElementException("Elemento no encontrado"+p.toString());
        }
        return lastMatch; // Retorna el último encontrado (o null si no hubo coincidencias)
    }

    public static <T> List<Node<T>> findAll(Node<T> head, Predicate<Node<T>> p) {
        List<Node<T>> results = new ArrayList<>();
        Node<T> current = head;

        while (current != null) {
            if (p.test(current)) {
                results.add(current); // Agrega el nodo completo a la lista
            }
            current = current.next;
        }

        return results; // Retorna lista vacía si no hay coincidencias
    }


    public Node<T> firstCoincidence(Predicate<T> p) {
        return firstCoincidence(this.head, p);
    }


    public Node<T> lastCoincidence(Predicate<T> p) {
        return lastCoincidence(this.head, p);
    }

    /**
     * Metodo de instancia para encontrar todas las coincidencias en esta lista.
     */
    public List<Node<T>> findAll(Predicate<Node<T>> p) {
        return findAll(this.head, p);
    }
}