package edu.fsadriann.app.priorityqueue;

import edu.fsadriann.model.priorityqueue.AbstractPriorityQueue;
import edu.fsadriann.app.array.Array;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.collection.Collection;

import java.util.function.Function;

// cola de prioridades que usa un array por dentro
// el elemento en la posicion 0 tiene la maxima prioridad
public class PriorityQueue<E> extends AbstractPriorityQueue<E> {

    // aqui guardamos los elementos
    private Array<E> array;

    // constructor vacio, crea una cola sin elementos
    public PriorityQueue() {
        this.array = new Array<>();
    }

    // constructor con un elemento inicial
    public PriorityQueue(E element) {
        this.array = new Array<>();
        this.array.add(element);
    }

    // ve cual es el siguiente elemento sin sacarlo de la cola
    // retorna el primero o null si esta vacia
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array.get(0);
    }

    // saca el elemento con más prioridad y lo elimina de la cola
    // retorna el primero o null si esta vacia
    @Override
    public E extract() {
        if (isEmpty()) {
            return null;
        }
        E element = array.get(0);
        array.remove(0);
        return element;
    }

    // agrega un elemento al final de la cola
    // tiene la menor prioridad
    @Override
    public boolean insert(E element) {
        if (element == null) {
            return false;
        }
        return array.add(element);
    }

    // inserta un elemento en una posicion especifica
    // mientras mas chico el indice mas prioridad tiene
    @Override
    public boolean insert(E element, int index) {
        if (element == null || index < 0 || index > size()) {
            return false;
        }
        // si el indice es igual al tamanio agregamos al final
        if (index == size()) {
            return array.add(element);
        }
        // creamos un array temporal con el nuevo elemento
        @SuppressWarnings("unchecked")
        E[] tempArray = (E[]) new Object[1];
        tempArray[0] = element;
        return array.add(index, tempArray);
    }

    // vacia la cola y borra todos los elementos
    @Override
    public boolean clear() {
        return array.clear();
    }

    // busca si un elemento esta en la cola
    @Override
    public boolean contains(E element) {
        if (element == null) {
            return false;
        }
        Iterator<E> iterator = array.iterator();
        while (iterator.hasNext()) {
            E current = iterator.next();
            if (current != null && current.equals(element)) {
                return true;
            }
        }
        return false;
    }

    // verifica si todos los elementos de un array estan en la cola
    @Override
    public boolean contains(E[] elements) {
        if (elements == null) {
            return false;
        }
        for (E element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    // verifica si todos los elementos de una coleccion estan en la cola
    @Override
    public boolean contains(Collection<E> collection) {
        if (collection == null) {
            return false;
        }
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    // checa si la cola tiene elementos o no
    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    // da vuelta la cola, el ultimo pasa a ser el primero
    // cambia las prioridades al revés
    @Override
    public boolean reverse() {
        return array.reverse();
    }

    // dice cuantos elementos hay en la cola
    @Override
    public int size() {
        return array.size();
    }

    // aplica una funcion a cada elemento de la cola
    @Override
    public void forEach(Function<E, Void> action) {
        if (action == null) {
            return;
        }
        array.forEach(action);
    }

    // retorna un iterador para recorrer la cola
    @Override
    public Iterator<E> iterator() {
        return array.iterator();
    }

    // convierte la cola a texto para poder imprimirla
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriorityQueue[");
        Iterator<E> iterator = array.iterator();
        boolean first = true;
        while (iterator.hasNext()) {
            if (!first) {
                sb.append(", ");
            }
            sb.append(iterator.next());
            first = false;
        }
        sb.append("]");
        return sb.toString();
    }
}