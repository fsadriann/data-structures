package edu.fsadriann.app.linkedlist.singly.circular;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import edu.fsadriann.app.linkedlist.node.singly.circular.LinkedNode;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.list.AbstractList;
import edu.fsadriann.model.list.List;

/**
 * Lista enlazada simple circular
 * El ultimo nodo apunta al primero formando un circulo
 */
public class LinkedList<E> extends AbstractList<E> {

    // numero de elementos en la lista
    private int size;
    // referencia al primer nodo
    private LinkedNode<E> head;
    // referencia al ultimo nodo
    private LinkedNode<E> flag;

    // constructor que crea una lista vacia
    public LinkedList() {
        this.size = 0;
        this.head = null;
        this.flag = null;
    }

    // constructor que crea una lista con un elemento inicial
    public LinkedList(E element) {
        LinkedNode<E> nuevoNodo = new LinkedNode<>(element);
        this.head = nuevoNodo;
        this.flag = nuevoNodo;
        this.size = 1;
    }

    // convierte la lista en texto para mostrarla
    @Override
    public String toString() {
        // si la lista esta vacia retorna mensaje
        if (head == null) {
            return "LinkedList: Empty";
        }

        // recorre la lista y construye el string
        String resultado = "LinkedList:";
        LinkedNode<E> actual = head;
        int contador = 0;
        
        while (contador < size) {
            resultado += " Next: " + actual.toString();
            actual = actual.getNext();
            contador++;
        }
        
        return resultado;
    }

    // verifica si la lista no tiene elementos
    @Override
    public boolean isEmpty() {
        return head == null;
    }

    // retorna cuantos elementos tiene la lista
    @Override
    public int size() {
        return this.size;
    }

    // elimina todos los elementos de la lista
    @Override
    public boolean clear() {
        try {
            head = null;
            flag = null;
            size = 0;
            return true;
        } catch (Exception error) {
            return false;
        }
    }

    // obtiene el primer elemento sin quitarlo
    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return head.get();
    }

    // obtiene el ultimo elemento sin quitarlo
    @Override
    public E peekLast() {
        if (isEmpty()) {
            return null;
        }
        return flag.get();
    }

    // agrega un elemento al final de la lista
    @Override
    public boolean add(E element) {
        // no permite elementos nulos
        if (element == null) {
            return false;
        }

        LinkedNode<E> nuevoNodo = new LinkedNode<>(element);

        // caso cuando la lista esta vacia
        if (isEmpty()) {
            head = nuevoNodo;
            flag = nuevoNodo;
            flag.setNext(head); // hace el circulo
        } else {
            // agrega al final y actualiza el circulo
            flag.setNext(nuevoNodo);
            flag = nuevoNodo;
            flag.setNext(head);
        }

        size++;
        return true;
    }

    // agrega varios elementos desde un arreglo
    @Override
    public boolean add(E[] array) {
        if (array == null) {
            return false;
        }

        // agrega cada elemento del arreglo
        for (E elemento : array) {
            add(elemento);
        }
        
        return true;
    }

    // agrega elementos desde otra coleccion
    @Override
    public boolean add(Collection<E> collection) {
        if (collection == null) {
            return false;
        }

        // usa el iterador para agregar cada elemento
        Iterator<E> iterador = collection.iterator();
        while (iterador.hasNext()) {
            add(iterador.next());
        }
        
        return true;
    }

    // agrega un elemento al inicio de la lista
    @Override
    public boolean addFirst(E element) {
        if (element == null) {
            return false;
        }

        LinkedNode<E> nuevoNodo = new LinkedNode<>(element);

        // caso lista vacia
        if (isEmpty()) {
            nuevoNodo.setNext(nuevoNodo);
            head = nuevoNodo;
            flag = nuevoNodo;
        } else {
            // inserta al inicio y actualiza referencias
            nuevoNodo.setNext(head);
            head = nuevoNodo;
            flag.setNext(head);
        }

        size++;
        return true;
    }

    // agrega varios elementos al inicio desde un arreglo
    @Override
    public boolean addFirst(E[] array) {
        if (array == null) {
            return false;
        }

        // agrega cada elemento al inicio
        for (E elemento : array) {
            addFirst(elemento);
        }
        
        return true;
    }

    // agrega elementos al inicio desde una coleccion
    @Override
    public boolean addFirst(Collection<E> collection) {
        if (collection == null) {
            return false;
        }

        // agrega cada elemento al inicio
        Iterator<E> iterador = collection.iterator();
        while (iterador.hasNext()) {
            addFirst(iterador.next());
        }
        
        return true;
    }

    // busca si un elemento existe en la lista
    @Override
    public boolean contains(E element) {
        // validaciones iniciales
        if (isEmpty() || element == null) {
            return false;
        }

        // recorre la lista buscando el elemento
        LinkedNode<E> actual = head;
        do {
            if (actual.get().equals(element)) {
                return true;
            }
            actual = actual.getNext();
        } while (actual != head);

        return false;
    }

    // verifica si todos los elementos del arreglo estan en la lista
    @Override
    public boolean contains(E[] array) {
        if (array == null || isEmpty()) {
            return false;
        }

        // arreglo vacio siempre retorna true
        if (array.length == 0) {
            return true;
        }

        // busca cada elemento del arreglo
        for (E elemento : array) {
            if (!contains(elemento)) {
                return false;
            }
        }
        
        return true;
    }

    // verifica si todos los elementos de la coleccion estan en la lista
    @Override
    public boolean contains(Collection<E> collection) {
        if (collection == null || isEmpty()) {
            return false;
        }

        // coleccion vacia siempre retorna true
        if (collection.size() == 0) {
            return true;
        }

        // busca cada elemento de la coleccion
        Iterator<E> iterador = collection.iterator();
        while (iterador.hasNext()) {
            if (!contains(iterador.next())) {
                return false;
            }
        }
        
        return true;
    }

    // retorna los primeros N elementos en un arreglo sin eliminarlos
    @SuppressWarnings("unchecked")
    @Override
    public E[] peekArray(int cuantity) {
        Object[] arreglo = new Object[cuantity];

        // validaciones
        if (isEmpty() || cuantity <= 0) {
            return (E[]) arreglo;
        }
        
        if (size < cuantity) {
            return (E[]) arreglo;
        }

        // recorre y guarda los elementos
        LinkedNode<E> actual = head;
        for (int i = 0; i < arreglo.length; i++) {
            arreglo[i] = actual.get();
            actual = actual.getNext();
        }
        
        return (E[]) arreglo;
    }

    // retorna los ultimos N elementos en un arreglo sin eliminarlos
    @SuppressWarnings("unchecked")
    @Override
    public E[] peekLastArray(int cuantity) {
        Object[] arreglo = new Object[cuantity];

        // validaciones
        if (isEmpty() || cuantity <= 0) {
            return (E[]) arreglo;
        }
        
        if (size < cuantity) {
            return (E[]) arreglo;
        }

        // avanza hasta el inicio de los ultimos N elementos
        LinkedNode<E> actual = head;
        for (int i = 0; i < size - cuantity; i++) {
            actual = actual.getNext();
        }

        // guarda los ultimos N elementos
        for (int i = 0; i < cuantity; i++) {
            arreglo[i] = actual.get();
            actual = actual.getNext();
        }
        
        return (E[]) arreglo;
    }

    // retorna los primeros N elementos en una coleccion sin eliminarlos
    @Override
    public List<E> peekCollection(int cuantity) {
        List<E> lista = new LinkedList<>();

        // validaciones
        if (isEmpty() || cuantity <= 0) {
            return lista;
        }

        if (size < cuantity) {
            return lista;
        }

        // agrega los primeros N elementos a la nueva lista
        LinkedNode<E> actual = head;
        for (int i = 0; i < cuantity; i++) {
            lista.add(actual.get());
            actual = actual.getNext();
        }
        
        return lista;
    }

    // retorna los ultimos N elementos en una coleccion sin eliminarlos
    @Override
    public List<E> peekLastCollection(int cuantity) {
        List<E> lista = new LinkedList<>();

        // validaciones
        if (isEmpty() || cuantity <= 0) {
            return lista;
        }
        
        if (size < cuantity) {
            return lista;
        }

        // avanza hasta el inicio de los ultimos N elementos
        LinkedNode<E> actual = head;
        for (int i = 0; i < size - cuantity; i++) {
            actual = actual.getNext();
        }

        // agrega los ultimos N elementos
        for (int i = 0; i < cuantity; i++) {
            lista.add(actual.get());
            actual = actual.getNext();
        }
        
        return lista;
    }

    // elimina la primera aparicion de un elemento
    @Override
    public boolean remove(E element) {
        if (isEmpty() || element == null) {
            return false;
        }

        // caso especial: un solo elemento
        if (size == 1) {
            if (head.get().equals(element)) {
                clear();
                return true;
            }
            return false;
        }

        // busca y elimina el elemento
        LinkedNode<E> actual = head;
        LinkedNode<E> anterior = flag;

        do {
            // eliminar el head
            if (actual.get().equals(element) && actual == head) {
                head = head.getNext();
                flag.setNext(head);
                size--;
                return true;
            }

            // eliminar nodo intermedio
            if (actual.get().equals(element) && actual != flag) {
                anterior.setNext(actual.getNext());
                size--;
                return true;
            }

            // eliminar el flag
            if (actual.get().equals(element) && actual == flag) {
                anterior.setNext(head);
                flag = anterior;
                size--;
                return true;
            }

            anterior = actual;
            actual = actual.getNext();
        } while (actual != head);

        return false;
    }

    // elimina todas las apariciones de los elementos del arreglo
    @Override
    public boolean remove(E[] array) {
        if (isEmpty()) {
            return false;
        }
        
        if (size < array.length) {
            return false;
        }

        // elimina todas las apariciones de cada elemento
        for (E elemento : array) {
            while (remove(elemento)) {
                // continua eliminando mientras exista
            }
        }
        
        return true;
    }

    // elimina todas las apariciones de los elementos de la coleccion
    @Override
    public boolean remove(Collection<E> collection) {
        if (isEmpty()) {
            return false;
        }
        
        if (size < collection.size()) {
            return false;
        }

        // elimina todas las apariciones de cada elemento
        Iterator<E> iterador = collection.iterator();
        while (iterador.hasNext()) {
            E valor = iterador.next();
            while (remove(valor)) {
                // continua eliminando mientras exista
            }
        }
        
        return true;
    }

    // elimina elementos que cumplan con la condicion
    @Override
    public boolean remove(Predicate<E> filter) {
        if (isEmpty() || filter == null) {
            return false;
        }

        int total = size;
        LinkedNode<E> actual = head;

        // recorre la lista evaluando cada elemento
        for (int i = 0; i < total; i++) {
            LinkedNode<E> siguiente = actual.getNext();
            
            if (filter.test(actual.get())) {
                remove(actual.get());
            }
            
            actual = siguiente;
            
            if (isEmpty()) {
                break;
            }
        }
        
        return true;
    }

    // retorna y elimina el primer elemento
    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        E elemento = head.get();

        // caso especial: un solo elemento
        if (size == 1) {
            clear();
            return elemento;
        }

        // actualiza head y mantiene circularidad
        head = head.getNext();
        flag.setNext(head);
        size--;
        
        return elemento;
    }

    // retorna y elimina el ultimo elemento
    @Override
    public E pollLast() {
        if (isEmpty()) {
            return null;
        }

        E elemento = flag.get();

        // caso especial: un solo elemento
        if (size == 1) {
            clear();
            return elemento;
        }

        // busca el penultimo nodo
        LinkedNode<E> actual = head;
        while (actual.getNext() != flag) {
            actual = actual.getNext();
        }

        // actualiza flag y mantiene circularidad
        actual.setNext(head);
        flag = actual;
        size--;
        
        return elemento;
    }

    // retorna y elimina los primeros N elementos en un arreglo
    @Override
    public E[] pollArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        
        if (size < cuantity) {
            return null;
        }

        // primero obtiene los elementos
        E[] arreglo = peekArray(cuantity);
        
        // luego los elimina
        for (int i = 0; i < cuantity; i++) {
            poll();
        }
        
        return arreglo;
    }

    // retorna y elimina los ultimos N elementos en un arreglo
    @Override
    public E[] pollLastArray(int cuantity) {
        if (isEmpty() || cuantity <= 0) {
            return null;
        }
        
        if (size < cuantity) {
            return null;
        }

        // primero obtiene los elementos
        E[] arreglo = peekLastArray(cuantity);
        
        // luego los elimina
        for (int i = 0; i < cuantity; i++) {
            pollLast();
        }
        
        return arreglo;
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

        // primero obtiene los elementos
        List<E> coleccion = peekCollection(cuantity);
        
        // luego los elimina
        for (int i = 0; i < cuantity; i++) {
            poll();
        }
        
        return coleccion;
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

        // primero obtiene los elementos
        List<E> coleccion = peekLastCollection(cuantity);
        
        // luego los elimina
        for (int i = 0; i < cuantity; i++) {
            pollLast();
        }
        
        return coleccion;
    }

    // reemplaza un elemento por otro valor
    @Override
    public boolean set(E index, E element) {
        if (isEmpty() || element == null || index == null) {
            return false;
        }

        // busca el elemento y lo reemplaza
        LinkedNode<E> actual = head;
        do {
            if (actual.get().equals(index)) {
                actual.set(element);
                return true;
            }
            actual = actual.getNext();
        } while (actual != head);

        return false;
    }

    // reemplaza un elemento si cumple la condicion
    @Override
    public boolean replace(E element, E newElement, Predicate<E> comparator) {
        if (comparator == null || element == null || newElement == null) {
            return false;
        }
        
        if (isEmpty()) {
            return false;
        }

        // busca el elemento y verifica la condicion
        LinkedNode<E> actual = head;
        do {
            if (actual.get().equals(element)) {
                if (comparator.test(actual.get())) {
                    actual.set(newElement);
                    break;
                }
            }
            actual = actual.getNext();
        } while (actual != head);

        return true;
    }

    // reemplaza multiples elementos si cumplen la condicion
    @Override
    public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
        // validaciones
        if (isEmpty() || comparator == null) {
            return false;
        }
        
        if (array.length != newArray.length) {
            return false;
        }
        
        if (size < array.length) {
            return false;
        }

        // reemplaza cada par de elementos
        for (int i = 0; i < array.length; i++) {
            replace(array[i], newArray[i], comparator);
        }
        
        return true;
    }

    // reemplaza elementos de colecciones si cumplen la condicion
    @Override
    public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
        // validaciones
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

        // reemplaza cada par de elementos
        Iterator<E> iterador1 = collection.iterator();
        Iterator<E> iterador2 = newCollection.iterator();
        
        while (iterador1.hasNext()) {
            replace(iterador1.next(), iterador2.next(), comparator);
        }
        
        return true;
    }

    // mantiene solo los elementos que estan en el arreglo
    @Override
    public boolean retain(E[] array) {
        if (isEmpty() || array == null) {
            return false;
        }

        LinkedNode<E> actual = head;
        
        // recorre la lista completa
        do {
            boolean encontrado = false;
            LinkedNode<E> siguiente = actual.getNext();

            // busca si el elemento esta en el arreglo
            for (E elemento : array) {
                if (actual.get().equals(elemento)) {
                    encontrado = true;
                    break;
                }
            }

            // si no esta en el arreglo lo elimina
            if (!encontrado) {
                remove(actual.get());
            }

            actual = siguiente;
        } while (actual != head);

        return true;
    }

    // mantiene solo los elementos que estan en la coleccion
    @Override
    public boolean retain(Collection<E> collection) {
        if (isEmpty() || collection == null) {
            return false;
        }

        LinkedNode<E> actual = head;
        
        // recorre la lista completa
        do {
            boolean encontrado = false;
            Iterator<E> iterador = collection.iterator();
            LinkedNode<E> siguiente = actual.getNext();

            // busca si el elemento esta en la coleccion
            while (iterador.hasNext()) {
                if (actual.get().equals(iterador.next())) {
                    encontrado = true;
                    break;
                }
            }

            // si no esta en la coleccion lo elimina
            if (!encontrado) {
                remove(actual.get());
            }

            actual = siguiente;
        } while (actual != head);

        return true;
    }

    // obtiene una sublista desde un elemento hasta otro
    @Override
    public List<E> subList(E from, E to) {
        List<E> lista = new LinkedList<>();

        if (from == null || to == null || isEmpty()) {
            return lista;
        }

        boolean iniciar = false;
        LinkedNode<E> actual = head;

        // recorre buscando el rango
        do {
            // si encuentra el final antes del inicio retorna vacia
            if (actual.get().equals(to) && !iniciar) {
                return lista;
            }

            // marca el inicio del rango
            if (actual.get().equals(from)) {
                iniciar = true;
            }

            // agrega elementos dentro del rango
            if (iniciar) {
                lista.add(actual.get());
            }

            // si encuentra el final termina
            if (actual.get().equals(to)) {
                break;
            }

            actual = actual.getNext();
        } while (actual != head);

        return lista;
    }

    // convierte la lista a un arreglo
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray() {
        Object[] arreglo = new Object[size];

        if (isEmpty()) {
            return (E[]) arreglo;
        }

        // copia cada elemento al arreglo
        LinkedNode<E> actual = head;
        for (int i = 0; i < size; i++) {
            arreglo[i] = actual.get();
            actual = actual.getNext();
        }

        return (E[]) arreglo;
    }

    // ordena la lista usando bubble sort
    @SuppressWarnings("unchecked")
    @Override
    public boolean sort(ToIntFunction<E> toInt) {
        if (toInt == null || size < 2) {
            return false;
        }

        // convierte a arreglo para ordenar
        Object[] arreglo = toArray();

        // aplica bubble sort
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                int valorA = toInt.applyAsInt((E) arreglo[j]);
                int valorB = toInt.applyAsInt((E) arreglo[j + 1]);

                // intercambia si estan en orden incorrecto
                if (valorA > valorB) {
                    Object temporal = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temporal;
                }
            }
        }

        // actualiza los valores en la lista
        LinkedNode<E> actual = head;
        int indice = 0;
        do {
            actual.set((E) arreglo[indice++]);
            actual = actual.getNext();
        } while (actual != head);

        return true;
    }

    // invierte el orden de los elementos en la lista
    @Override
    public boolean reverse() {
        if (isEmpty() || size < 2) {
            return false;
        }

        LinkedNode<E> actual = head;
        LinkedNode<E> anterior = flag;

        // invierte las referencias de cada nodo
        do {
            LinkedNode<E> siguiente = actual.getNext();
            actual.setNext(anterior);
            anterior = actual;
            actual = siguiente;
        } while (actual != head);

        // intercambia head y flag
        LinkedNode<E> temporal = head;
        head = flag;
        flag = temporal;

        return true;
    }

    // crea un iterador para recorrer la lista
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int contador = 0;
            private LinkedNode<E> actual = head;

            // verifica si hay mas elementos
            @Override
            public boolean hasNext() {
                return contador < size;
            }

            // obtiene el siguiente elemento
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new IllegalStateException("No hay elementos");
                }

                E elemento = actual.get();
                actual = actual.getNext();
                contador++;
                
                return elemento;
            }
        };
    }

    // ejecuta una accion sobre cada elemento de la lista
    @Override
    public void forEach(Function<E, Void> action) {
        if (isEmpty() || action == null) {
            return;
        }

        LinkedNode<E> actual = head;
        
        // aplica la accion a cada elemento
        do {
            action.apply(actual.get());
            actual = actual.getNext();
        } while (actual != head);
    }
}