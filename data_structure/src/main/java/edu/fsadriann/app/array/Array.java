package edu.fsadriann.app.array;

import edu.fsadriann.model.array.AbstractArray;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.collection.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Un array genérico para guardar elementos del mismo tipo.
 * Se puede acceder a los elementos por su posición.
 */
public class Array<E> extends AbstractArray<E> {

    private E[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 1000;

    // Crea un nuevo array vacío
    @SuppressWarnings("unchecked")
    public Array() {
        this.array = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // --- Información básica del array ---

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        return true;
    }

    @Override
    public boolean reverse() {
        for (int i = 0; i < size / 2; i++) {
            E temp = array[i];
            array[i] = array[size - 1 - i];
            array[size - 1 - i] = temp;
        }
        return true;
    }

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

    @Override
    public void forEach(Function<E, Void> action) {
        if (action == null) {
            return;
        }
        Iterator<E> iterator = iterator();
        while (iterator.hasNext()) {
            action.apply(iterator.next());
        }
    }

    // --- Recorrer los elementos ---

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return array[index++];
            }
        };
    }

    // --- Agregar elementos ---

    @Override
    public boolean add(E element) {
        if (size == array.length) {
            return false;
        }
        array[size++] = element;
        return true;
    }

    @Override
    public boolean add(int index, E[] elements) {
        if (elements == null || index < 0 || index > size) {
            return false;
        }
        if ((size + elements.length) > array.length) {
            return false;
        }
        shiftRight(index, elements.length);
        for (int i = 0; i < elements.length; i++) {
            array[index + i] = elements[i];
        }
        size += elements.length;
        return true;
    }

    @Override
    public boolean add(int index, Collection<E> collection) {
        if (collection == null || index < 0 || index > size) {
            return false;
        }
        if ((collection.size() + size) > array.length) {
            return false;
        }
        shiftRight(index, collection.size());
        Iterator<E> iterator = collection.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            array[index + i++] = iterator.next();
        }
        size += collection.size();
        return true;
    }

    // --- Obtener elementos ---

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }

    @Override
    public int indexOf(E element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    // --- Quitar elementos ---

    @Override
    public boolean remove(int index) {
        return remove(index, index);
    }

    @Override
    public boolean remove(int from, int to) {
        if (from < 0 || to >= size || from > to) {
            return false;
        }
        int count = to - from + 1;
        shiftLeft(from, count);
        size -= count;
        return true;
    }

    @Override
    public boolean remove(Predicate<E> filter) {
        if (filter == null) {
            return false;
        }
        int i = 0;
        while (i < size) {
            if (filter.test(array[i])) {
                remove(i);
            } else {
                i++;
            }
        }
        return true;
    }

    // --- Cambiar elementos ---

    @Override
    public boolean set(int index, E element) {
        if (index < 0 || index >= size) {
            return false;
        }
        array[index] = element;
        return true;
    }

    // --- Gestión del espacio ---

    @Override
    public void defragment() {
        // El array se mantiene organizado automáticamente
    }

    @Override
    public boolean dimension(int newDimension) {
        // Para aumentar el tamaño del array en el futuro
        return true;
    }

    // --- Funciones internas de ayuda ---

    // Corre los elementos hacia la derecha para hacer espacio
    private void shiftRight(int startIndex, int count) {
        for (int i = size - 1; i >= startIndex; i--) {
            array[i + count] = array[i];
        }
    }

    // Corre los elementos hacia la izquierda para cerrar espacios vacíos
    private void shiftLeft(int startIndex, int count) {
        for (int i = startIndex; i < size - count; i++) {
            array[i] = array[i + count];
        }
    }
}
