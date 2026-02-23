package edu.fsadriann.app.linkedlist.doubly.circular;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import edu.fsadriann.app.linkedlist.node.doubly.LinkedNode;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.list.AbstractList;
import edu.fsadriann.model.list.List;

/**
 * Lista circular doblemente enlazada
 * El ultimo nodo apunta al primero y viceversa formando un circulo
 */
public class LinkedList<E> extends AbstractList<E> {

    
    // cantidad de elementos en la lista
    private int size;
    // primer nodo de la lista
    private LinkedNode<E> head;
    // ultimo nodo de la lista
    private LinkedNode<E> flag;

    
    // crea lista vacia
    public LinkedList() {
        size = 0;
        this.head = this.flag = null;
    }

    // crea lista con un elemento inicial
    public LinkedList(E element) {
        size = 1;
        LinkedNode<E> node = new LinkedNode<E>(element);
        this.head = this.flag = node;
    }

    
    // convierte la lista a string para imprimir
    @Override
    public String toString() {
        LinkedNode<E> node = head;
        String string;
        if (node == null) {
            string = "LinkedList: Vacía";  
        } else {
            string = "LinkedList:";
            for (int i = 0; i < size; i++) {
                string += " Next: " + node.toString();
                node = node.getNext();
            }
        }
        return string;
    }
    
    // verifica si la lista esta vacia
    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    // retorna la cantidad de elementos
    @Override
    public int size() {
        return size;
    }

    // retorna el primer elemento sin eliminarlo
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return this.head.get();
    }

    // retorna el ultimo elemento sin eliminarlo
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return this.flag.get();
    }

    // retorna los primeros N elementos en un array sin eliminarlos
    @SuppressWarnings("unchecked")
    @Override
    public E[] peekArray(int cuantity) {
        Object[] array = new Object[cuantity];
        if (isEmpty() || cuantity <= 0) {
            return (E[]) array;
        }
        if (size < cuantity) {
            return (E[]) array;
        }

        LinkedNode<E> node = head;
        for (int i = 0; i < array.length; i++) {
            array[i] = node.get();
            node = node.getNext();
        }
        return (E[]) array;
    }

    // retorna los ultimos N elementos en un array sin eliminarlos
    @SuppressWarnings("unchecked")
    @Override
    public E[] peekLastArray(int cuantity) {
        Object[] array = new Object[cuantity];
        if (isEmpty() || cuantity <= 0) {
            return (E[]) array;
        }
        if (size < cuantity) {
            return (E[]) array;
        }
        LinkedNode<E> node = flag;
        for (int i = cuantity - 1; i >= 0; i--) {
            array[i] = node.get();
            node = node.getPrevious();
        }
        return (E[]) array;
    }

    // retorna los primeros N elementos en una coleccion sin eliminarlos
    @Override
    public List<E> peekCollection(int cuantity) {
        List<E> list = new LinkedList<>();
        if (isEmpty() || cuantity <= 0) {
            return list;
        }

        if (size < cuantity) {
            return list;
        }
        
        LinkedNode<E> node = head;
        for (int i = 0; i < cuantity; i++) {
            list.add(node.get());
            node = node.getNext();
        }
        return list;
    }

    // retorna los ultimos N elementos en una coleccion sin eliminarlos
    @Override
    public List<E> peekLastCollection(int cuantity) {
        List<E> list = new LinkedList<>();
        if (isEmpty() || cuantity <= 0) {
            return list;
        }
        if (size < cuantity) {
            return list;
        }
        LinkedNode<E> node = flag;
        for (int i = 0; i < cuantity; i++) {
            list.addFirst(node.get());
            node = node.getPrevious();
        }
        return list;
    }

    // agrega un elemento al final de la lista circular
    @Override
    public boolean add(E element) {
        if (element == null) {
            return false;
        }
        LinkedNode<E> node = new LinkedNode<>(element);
        if (isEmpty()) {
            // primer elemento apunta a si mismo
            node.setNext(node);
            node.setPrevious(node);
            this.head = this.flag = node;
        } else {
            // insertar al final y mantener circularidad
            node.setPrevious(flag);
            this.flag.setNext(node);
            flag = node;
            flag.setNext(head);
            head.setPrevious(flag);
        }
        size++;
        return true;
    }

    // agrega todos los elementos del array al final
    @Override
    public boolean add(E[] array) {
        if (array == null) {
            return false;
        }
        for (E x : array) {
            add(x);
        }
        return true;
    }

    // agrega todos los elementos de la coleccion al final
    @Override
    public boolean add(Collection<E> collection) {
        if (collection == null) {
            return false;
        }
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            add(iterator.next());
        }
        return true;
    }

    // agrega un elemento al inicio de la lista circular
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }
        LinkedNode<E> node = new LinkedNode<>(element);
        if (isEmpty()) {
            node.setNext(node);
            node.setPrevious(node);
            this.head = this.flag = node;
        } else {
            node.setNext(this.head);
            node.setPrevious(flag);
            this.head = node;
            flag.setNext(head);
        }
        size++;
        return true;
    }

    // agrega todos los elementos del array al inicio manteniendo orden
    @Override
    public boolean addFirst(E[] array) {
        if (array == null) {
            return false;
        }
        for (int i = array.length - 1; i >= 0; i--) {
            addFirst(array[i]);
        }
        return true;
    }

    // agrega todos los elementos de la coleccion al inicio manteniendo orden
    @Override
    public boolean addFirst(Collection<E> collection) {
        if (collection == null) {
            return false;
        }
        // crear lista temporal para almacenar elementos
        LinkedList<E> temp = new LinkedList<>();
        Iterator<E> iterator = collection.iterator();
        
        // guardar elementos en lista temporal
        while (iterator.hasNext()) {
            temp.add(iterator.next());
        }
        
        // agregar desde el final de la lista temporal hacia el inicio
        LinkedNode<E> node = temp.flag;
        if (node != null) {
            do {
                addFirst(node.get());
                node = node.getPrevious();
            } while (node != temp.flag);
        }
        return true;
    }

    // verifica si un elemento existe en la lista
    @Override
    public boolean contains(E element) {
        if (isEmpty()) {
            return false;
        }
        if (element == null) {
            return false;
        }
        LinkedNode<E> node = head;
        do { 
            if (node.get().equals(element)) {
                return true;
            }
            node = node.getNext();
        } while (node != head);
        return false;
    }

    // verifica si todos los elementos del array existen en la lista
    @Override
    public boolean contains(E[] array) {
        if (array == null || isEmpty()) {
            return false;
        }
        if (array.length == 0) {
            return true;
        }
        for (E x : array) {
            if (!contains(x)) {
                return false;
            }
        }
        return true;
    }

    // verifica si todos los elementos de la coleccion existen en la lista
    @Override
    public boolean contains(Collection<E> collection) {
        if (collection == null || isEmpty()) {
            return false;
        }
        if (collection.size() == 0) {
            return true;
        }
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            if (!contains(iterator.next())) {
                return false;
            }
        }
        return true;
    }

    // elimina la primera ocurrencia de un elemento
    @Override
    public boolean remove(E element) {
        if (isEmpty() || element == null) {
            return false;
        }
        LinkedNode<E> node = head;
        if (size == 1) {
            if (head.get().equals(element)) {
                clear();
                return true;
            } else {
                return false;
            }
        } else {
            do { 
                if (node.get().equals(element)) {
                    if (node == head) {
                        head = head.getNext();
                        head.setPrevious(flag);
                        flag.setNext(head);
                    } else if (node == flag) {
                        flag = node.getPrevious();
                        flag.setNext(head);
                        head.setPrevious(flag);
                    } else {
                        node.getPrevious().setNext(node.getNext());
                        node.getNext().setPrevious(node.getPrevious());
                    }
                    size--;
                    return true;
                }
                node = node.getNext();
            } while (node != head);
        }
        return false;
    }

    // elimina todas las ocurrencias de los elementos del array
    @Override
    public boolean remove(E[] array) {
        if (isEmpty() || array == null || array.length == 0) {
            return false;
        }
        boolean removed = false;
        for (E x : array) {
            while (remove(x)) {
                removed = true;
            }
        }
        return removed;
    }

    // elimina todas las ocurrencias de los elementos de la coleccion
    @Override
    public boolean remove(Collection<E> collection) {
        if (isEmpty() || collection == null || collection.size() == 0) {
            return false;
        }
        boolean removed = false;
        Iterator<E> iterator = collection.iterator();
        while (iterator.hasNext()) {
            E val = iterator.next();
            while (remove(val)) {
                removed = true;
            }
        }
        return removed;
    }

    // elimina todos los elementos que cumplan con el filtro
    @Override
    public boolean remove(Predicate<E> filter) {
        if (isEmpty() || filter == null) {
            return false;
        }
        boolean removed = false;
        int count = size;
        LinkedNode<E> node = head;
        for (int i = 0; i < count; i++) {
            if (isEmpty()) {
                break;
            }
            LinkedNode<E> next = node.getNext();
            if (filter.test(node.get())) {
                remove(node.get());
                removed = true;
            }
            node = next;
        }
        return removed;
    }

    // retorna y elimina el primer elemento
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E element = head.get();
        if (size == 1) {
            clear();
            return element;
        }
        head = head.getNext();
        head.setPrevious(flag);
        flag.setNext(head);
        size--;
        return element;
    }

    // retorna y elimina el ultimo elemento
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }
        E element = flag.get();
        if (size == 1) {
            clear();
            return element;
        }
        flag = flag.getPrevious();
        flag.setNext(head);
        head.setPrevious(flag);
        size--;
        return element;
    }

    // retorna y elimina los primeros N elementos en un array
    @Override
    public E[] pollArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        if (size < cuantity) {
            return null;
        }
        E[] array = peekArray(cuantity);
        for (int i = 0; i < cuantity; i++) {
            poll();
        }
        return array;
    }

    // retorna y elimina los ultimos N elementos en un array
    @Override
    public E[] pollLastArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        if (size < cuantity) {
            return null;
        }
        E[] array = peekLastArray(cuantity);
        for (int i = 0; i < cuantity; i++) {
            pollLast();
        }
        return array;
    }

    // retorna y elimina los primeros N elementos en una coleccion
    @Override
    public List<E> pollCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        if (size < cuantity) {
            return null;
        }
        List<E> collection = peekCollection(cuantity);
        for (int i = 0; i < cuantity; i++) {
            poll();
        }
        return collection;
    }

    // retorna y elimina los ultimos N elementos en una coleccion
    @Override
    public List<E> pollLastCollection(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        if (size < cuantity) {
            return null;
        }
        List<E> collection = peekLastCollection(cuantity);
        for (int i = 0; i < cuantity; i++) {
            pollLast();
        }
        return collection;
    }

    // elimina todos los elementos de la lista
    @Override
    public boolean clear() {
        try {
            this.head = this.flag = null;
            size = 0;
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // reemplaza el valor de un elemento por otro
    @Override
    public boolean set(E index, E element) {
        if (isEmpty()) {
            return false;
        }
        if (element == null || index == null) {
            return false;
        }
        LinkedNode<E> node = head;
        do { 
            if (node.get().equals(index)) {
                node.set(element);
                return true;
            }
            node = node.getNext();
        } while (node != head);
        return false;
    }

    // reemplaza un elemento por otro si cumple con el comparador
    @Override
    public boolean replace(E element, E newElement, Predicate<E> comparator) {
        if (comparator == null || element == null || newElement == null) {
            return false;
        }
        if (isEmpty()) {
            return false;
        }
        LinkedNode<E> node = head;
        do { 
            if (node.get().equals(element)) {
                if (comparator.test(node.get())) {
                    node.set(newElement);
                    return true;
                }
            }
            node = node.getNext();
        } while (node != head);
        return false;
    }

    // reemplaza elementos del array por otros si cumplen con el comparador
    @Override
    public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
        if (isEmpty()) {
            return false;
        }
        if (comparator == null || array == null || newArray == null) {
            return false;
        }
        if (array.length != newArray.length) {
            return false;
        }
        if (size < array.length) {
            return false;
        }

        boolean replaced = false;
        for (int i = 0; i < array.length; i++) {
            if (replace(array[i], newArray[i], comparator)) {
                replaced = true;
            }
        }
        return replaced;
    }

    // reemplaza elementos de la coleccion por otros si cumplen con el comparador
    @Override
    public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
        if (isEmpty() || collection == null || newCollection == null) {
            return false;
        }
        if (comparator == null) {
            return false;
        }
        if (collection.size() != newCollection.size()) {
            return false;
        }
        if (size < collection.size()) {
            return false;
        }
        
        boolean replaced = false;
        Iterator<E> iterator = collection.iterator();
        Iterator<E> iterator2 = newCollection.iterator();
        while (iterator.hasNext()) {
            if (replace(iterator.next(), iterator2.next(), comparator)) {
                replaced = true;
            }
        }
        return replaced;
    }

    // mantiene solo los elementos que estan en el array eliminando el resto
    @Override
    public boolean retain(E[] array) {
        if (isEmpty()) {
            return false;
        }
        if (array == null) {
            return false;
        }
        LinkedNode<E> node = head;
        int count = size;
        for (int i = 0; i < count; i++) {
            if (isEmpty()) {
                break;
            }
            boolean found = false;
            LinkedNode<E> next = node.getNext();

            for (E element : array) {
                if (node.get().equals(element)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                remove(node.get());
            }
            node = next;
        }
        return true;
    }

    // mantiene solo los elementos que estan en la coleccion eliminando el resto
    @Override
    public boolean retain(Collection<E> collection) {
        if (isEmpty()) {
            return false;
        }
        if (collection == null) {
            return false;
        }
        LinkedNode<E> node = head;
        int count = size;
        for (int i = 0; i < count; i++) {
            if (isEmpty()) {
                break;
            }
            boolean found = false;
            Iterator<E> iterator = collection.iterator();
            LinkedNode<E> next = node.getNext();
            while (iterator.hasNext()) {
                if (node.get().equals(iterator.next())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                remove(node.get());
            }
            node = next;
        }
        return true;
    }

    // retorna una sublista desde un elemento hasta otro
    @Override
    public List<E> subList(E from, E to) {
        List<E> list = new LinkedList<>();
        if (from == null || to == null) {
            return list;
        }
        if (isEmpty()) {
            return list;
        }
        boolean start = false;
        LinkedNode<E> node = head;
        int count = 0;
        do { 
            if (node.get().equals(to) && !start) {
                return list;
            }
            if (node.get().equals(from)) {
                start = true;
            }
            if (start) {
                list.add(node.get());
            }
            if (node.get().equals(to)) {
                break;
            }
            node = node.getNext();
            count++;
        } while (node != head && count < size);
        return list;
    }

    // convierte la lista a un array
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        Object[] array = new Object[size];
        if (isEmpty()) {
            return (E[]) array;
        }
        LinkedNode<E> node = head;
        for (int i = 0; i < size; i++) {
            array[i] = node.get();
            node = node.getNext();
        }
        return (E[]) array;
    }

    // ordena la lista usando bubble sort con una funcion de conversion a int
    @SuppressWarnings("unchecked")
    @Override
    public boolean sort(ToIntFunction<E> toInt) {
        if (toInt == null || size < 2) {
            return false;
        }
        Object[] array = toArray();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size - x - 1; y++) {
                int a = toInt.applyAsInt((E) array[y]);
                int b = toInt.applyAsInt((E) array[y + 1]);

                if (a > b) {
                    Object temporal = array[y];
                    array[y] = array[y + 1];
                    array[y + 1] = temporal;
                }
            }
        }

        LinkedNode<E> node = head;
        int i = 0;
        do {
            node.set((E) array[i++]);
            node = node.getNext();
        } while (node != head);
        return true;
    }

    // invierte el orden de la lista
    @Override
    public boolean reverse() {
        if (isEmpty() || size < 2) {
            return false;
        }
        LinkedNode<E> node = head;

        do { 
            LinkedNode<E> next = node.getNext();
            LinkedNode<E> temp = node.getNext();
            node.setNext(node.getPrevious());
            node.setPrevious(temp);
            node = next;
        } while (node != head);
        
        LinkedNode<E> temp = head;
        head = flag;
        flag = temp;
        return true;
    }


    // retorna un iterador que puede recorrer la lista circular
    @Override
    public Iterator<E> iterator() { 
        return new Iterator<E>() {
            int contador = 0;
            int contadorPrevious = 0;
            private LinkedNode<E> current = head;
            private LinkedNode<E> current2 = flag;

            @Override
            public boolean hasNext() {
                return contador < size;
            }

            public boolean hasPrevious() {
                return contadorPrevious < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No more elements");
                }
                E element = current.get();
                current = current.getNext();
                contador++;
                return element;
            }

            @SuppressWarnings("unused")
            public E previous() {
                if (!hasPrevious()) {
                    throw new IllegalStateException("No more elements");
                }
                E element = current2.get();
                current2 = current2.getPrevious();
                contadorPrevious++;
                return element;
            }
        };
    }

    // ejecuta una accion sobre cada elemento de la lista
    @Override
    public void forEach(Function<E, Void> action) {
        if (isEmpty()) {
            return;
        }
        if (action == null) {
            return;
        }
        LinkedNode<E> node = head;
        do { 
            action.apply(node.get());
            node = node.getNext();
        } while (node != head);
    }
}