package edu.fsadriann.linkedlist.singly.singly;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

import edu.fsadriann.linkedlist.node.singly.LinkedNode;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;
import edu.fsadriann.model.list.AbstractList;
import edu.fsadriann.model.list.List;

public class LinkedList<E> extends AbstractList<E> {

  private transient LinkedNode<E> head;
  private transient LinkedNode<E> tail;
  private transient LinkedNode<E> inode;

  public LinkedList() {
    head = tail = null;
  }

  public LinkedList(E element) {
    this.head = new LinkedNode<>(element);
  }

  public boolean add(E element) {
    try {
      if (isEmpty()) {
        LinkedNode<E> node = new LinkedNode<>(element);
        this.head = this.tail = node;
        return true;
      } else {
        LinkedNode<E> node = new LinkedNode<>(element);
        this.tail.setNext(node);
        this.tail = node;
        return true;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return false;
    }

  }

  public boolean isEmpty() {
    return this.head == null;
  }

  @Override
  public String toString() {
    return "LinkedList [head=" + head + "]";
  }

  @Override
  public boolean add(E[] array) {
    if (array == null){
      return false;
    }
    for (E element : array) {
      if (!add(element)) {
        return false;
    }
  }
  return true;
  }

  @Override
  public boolean add(Collection<E> collection) {
    Iterator<E> iterator = collection.iterator();
    while(iterator.hasNext()){
      if (!add(iterator.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addFirst(E element) {
    try {
      LinkedNode<E> node = new LinkedNode<E>(element);
      node.setNext(head);
      head = node;
      if (tail == null) {
        tail = node;
      }
      return true;

    } catch (Exception e) {
      return false;
    }

  }

  @Override
  public boolean addFirst(E[] array) {
    if (array == null){
      return false;
    }
    for (int i = array.length - 1; i >= 0; i--) {
      if (!addFirst(array[i])) {
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean addFirst(Collection<E> collection) {
    Iterator<E> iterator = collection.iterator();
    while(iterator.hasNext()){
      if (!addFirst(iterator.next())) {
        return false;
      }
    }
    return true;
  }

  @Override
  public E peek() {
    return this.head.get();
  }

  @Override
  public E peekLast() {
    return this.tail.get();
  }

  @Override
  @SuppressWarnings("unchecked")
public E[] peekArray(int n) {
    E[] result = (E[]) new Object[n];
    LinkedNode<E> current = head;
    
    for (int i = 0; i < n; i++) {
        result[i] = current.get(); // obtiene el nodo actual
        current = current.getNext(); // avanza al siguiente
    }
    
    return result;
}
  @Override
  @SuppressWarnings("unchecked")
public E[] peekLastArray(int n) {
    E[] result = (E[]) new Object[n];
    LinkedNode<E> current = head;
    
    // 1. avanzas hasta la posición size() - n
    int start = size() - n;
    for (int i = 0; i < start; i++) {
        current = current.getNext();
    }
    
    // 2. desde ahí obtienes los n elementos
    for (int i = 0; i < n; i++) {
        result[i] = current.get();
        current = current.getNext();
    }
    
    return result;
}

@Override
public List<E> peekCollection(int n) {
    LinkedList<E> result = new LinkedList<>();
    LinkedNode<E> current = head;
    
    for (int i = 0; i < n; i++) {
        result.add(current.get());   // agrega sin eliminar
        current = current.getNext(); // avanza
    }
    
    return result;
  }
@Override
public List<E> peekLastCollection(int n) {
    LinkedList<E> result = new LinkedList<>();
    LinkedNode<E> current = head;
    
    // 1. avanzas hasta la posición size() - n
    int start = size() - n;
    for (int i = 0; i < start; i++) {
        current = current.getNext();
    }
    
    // 2. desde ahí obtienes los n elementos
    for (int i = 0; i < n; i++) {
        result.add(current.get());
        current = current.getNext();
    }
    
    return result;
}

  @Override
public E poll() {
    if (isEmpty()) {
        return null;
    }
    
    E element = head.get();  // guardas el elemento
    head = head.getNext();   // mueves el head al siguiente
    
    if (head == null) {      // si la lista quedó vacía
        tail = null;         // tail también es null
    }
    
    return element;          // retornas el elemento eliminado
}

  @Override
public E pollLast() {
    
    // 1. si la lista está vacía no hay nada que eliminar
    if (isEmpty()) {
        return null;
    }
    
    // 2. guardas el elemento del tail antes de eliminarlo
    E element = tail.get();
    
    // 3. caso especial: si solo hay un nodo
    // head y tail apuntan al mismo nodo
    if (head == tail) {
        head = null;  // la lista queda vacía
        tail = null;
        return element;
    }
    
    // 4. recorres hasta encontrar el nodo ANTES del tail
    // paras cuando el siguiente de current es el tail
    LinkedNode<E> current = head;
    while (current.getNext() != tail) {
        current = current.getNext();
    }
    
    // 5. aquí current es el nodo anterior al tail
    // ejemplo: [10] → [20] → [30]
    //                  ↑        ↑
    //               current    tail
    
    current.setNext(null);  // [20] ya no apunta a [30]
    tail = current;         // [20] se convierte en el nuevo tail
    
    // 6. retornas el elemento eliminado
    return element;
}
  @Override
@SuppressWarnings("unchecked")
public E[] pollArray(int n) {
    E[] result = (E[]) new Object[n]; // creas un array de tamaño n
    
    for (int i = 0; i < n; i++) {
        result[i] = poll(); // obtiene y elimina el primero en cada iteración
    }
    
    return result;
}

  @Override
  @SuppressWarnings("unchecked")
  public E[] pollLastArray(int n) {
    E[] result = (E[]) new Object[n];
    for (int i = 0; i < n; i++){
      result[i] = pollLast(); //elimina al ultimo 


    }
    return result;
  }

  @Override
public List<E> pollCollection(int n) {
    LinkedList<E> result = new LinkedList<>();
    
    for (int i = 0; i < n; i++) {
        result.add(poll()); // agrega al final de la nueva lista
    }
    
    return result;
}

  @Override
  public List<E> pollLastCollection(int n) {
      LinkedList<E> result = new LinkedList<>();
    
      for (int i = 0; i < n; i++) {
        result.add(pollLast());
      }
    
      return result;
}
  

  @Override
  public boolean remove(E element) {
    LinkedNode<E> previous = null;
    LinkedNode<E> current = head;
    
    while (current != null) {
        if (current.get().equals(element)) {
            if (current == head) {
                head = head.getNext();
                return true;
            } else if (current == tail) {
                tail = previous;
                previous.setNext(null);
                return true;
            } else {
                previous.setNext(current.getNext());
                return true; 
            }
        }
        previous = current;
        current = current.getNext();
    }
    return false;
}


  @Override
public boolean remove(E[] array) {
    
    if (array == null){
      return false;
    }
    for (E element : array) {
        if (!remove(element)) {
            return false;
        }
    }
    
    // 4. si eliminó todos los elementos del array exitosamente
    return true;
}
  @Override
public boolean remove(Collection<E> collection) {
    
    Iterator<E> iterator = collection.iterator();
    
    // 2. mientras haya elementos en la colección
    while (iterator.hasNext()) {
        
        // 3. obtienes el siguiente elemento y lo intentas eliminar
        // reutilizas el remove(E element)
        if (!remove(iterator.next())) {
            
            // 4. si algún elemento no existía en la lista
            // retorna false inmediatamente
            return false;
        }
    }
    
    // 5. si eliminó todos los elementos exitosamente
    return true;
}
  @Override
  public boolean remove(Predicate<E> filter) {
      if (isEmpty() || filter == null) {
          return false;
      }
      
      boolean anyRemoved = false;
      LinkedNode<E> current = head;
      
      // Recorrer toda la lista y eliminar todos los elementos que cumplan
      while (current != null) {
          LinkedNode<E> next = current.getNext();  // Guardar el siguiente ANTES de eliminar
          
          if (filter.test(current.get())) {
              remove(current.get());  // Eliminar el elemento actual
              anyRemoved = true;
          }
          
          current = next;  // Avanzar al siguiente nodo replace
      }
      
      if (anyRemoved) {
          return true;
      }
      return false;
    }

  @Override
public boolean replace(E element, E newElement, Predicate<E> comparator) {
    LinkedNode<E> current = head;
    
    while (current != null) {
     if (comparator.test(current.get())) {  // evalúa el elemento actual del nodo
    current.set(newElement);            // reemplaza con el nuevo valor
    return true;
    } 
        current = current.getNext();
    }
    return false;
}

@Override
public boolean replace(E[] array, E[] newArray, Predicate<E> comparator) {
    for (int i = 0; i < array.length; i++) {
        if (!replace(array[i],newArray[i] , comparator)) {
            return false;
        }
    }
    return true;
}

@Override
public boolean replace(Collection<E> collection, Collection<E> newCollection, Predicate<E> comparator) {
    Iterator<E> iteratorElements = collection.iterator();
    Iterator<E> iteratorNew = newCollection.iterator();

    boolean anyReplaced = false;
    
    while (iteratorElements.hasNext()) {
        E element = iteratorElements.next();
        E newElement = iteratorNew.next();

        // Intenta reemplazar, si lo hace marca como true
        if (replace(element, newElement, comparator)) {
            anyReplaced = true;
        }
        // Si no lo reemplaza, continúa con el siguiente
    }
    
    if (anyReplaced) {
        return true;
    }
    return false;
}
@Override
public boolean retain(E[] array) {
    LinkedNode<E> current = head;
    
    while (current != null) {
        E element = current.get();
        current = current.getNext(); // avanzas ANTES de eliminar
        
        if (!contains(element)) {  
            remove(element); //elimina el elemento
        }
    }
    return true;
}
  @Override
public boolean retain(Collection<E> collection) {
    LinkedNode<E> current = head; // empiezas desde el head

    while (current != null) { // recorres toda la lista
        E element = current.get(); // guardas el elemento actual
        current = current.getNext(); // avanzas ANTES de eliminar

        if (!collection.contains(element)) { // si el elemento NO está en la colección
            remove(element); // lo elimina de la lista
        }
    }
    return true;
}

  @Override
  public boolean set(E index, E element) {
    LinkedNode<E> current = head;
    while (current != null){
      if (current.get().equals(index)){
        current.set(element);
        return true;
      }
      current = current.getNext();
    }
    return false;
  }

  @Override
  public boolean sort(ToIntFunction<E> toInt) {
    if (isEmpty() || head == tail){
      return true; // No hay nada que ordenar si la lista está vacía o tiene un solo nodo
    }
    boolean swapped = true; // controla si se hicieron cambios en la pasada
    while (swapped) {
      swapped = false;
      LinkedNode<E> current = head;
      while (current.getNext() != null) {
        if(toInt.applyAsInt(current.get()) > toInt.applyAsInt(current.getNext().get())){
          E temp = current.get();
          current.set(current.getNext().get());
          current.getNext().set(temp);
          swapped = true; // se hizo un cambio, hay que seguir ordenando
        }
        current = current.getNext();
      }
    }
    return true;
  }

  @Override
  public List<E> subList(E from, E to) {
    LinkedList<E> result = new LinkedList<>();
    LinkedNode<E> current = head;
    
    boolean foundFrom = false;
    while(current != null){
      if(current.get().equals(from)){
        foundFrom = true;
      }
      if (foundFrom){
        result.add(current.get());
      
        if(current.get().equals(to)){
          break;
        }
      }
      current = current.getNext();
    }
    return result;
  }

  @Override
  public E[] toArray() {
    @SuppressWarnings("unchecked")
    E[] result = (E[]) new Object[size()];
    LinkedNode<E> current = head;
    int index = 0;
    while (current != null){
      result[index++] = current.get();
      current = current.getNext();
    }
    return result;
  }

  @Override
    public boolean clear() {
      head = null;
      tail = null;
      return true;
    }

  @Override
  public boolean contains(E element) {
    LinkedNode<E> current = head;
    while (current != null){
      if(current.get().equals(element)){
        return true;
      }
      current = current.getNext();

    }
    return false;
  }

  @Override
  public boolean contains(E[] array) {
    for(E element: array){
      if(!contains(element)){
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean contains(Collection<E> collection) {
    Iterator<E> iterator = collection.iterator();
    while(iterator.hasNext()){
      if(!contains(iterator.next())){
        return false;
      }
    }
    return true;
  }

  @Override
  public boolean reverse() {
    if (isEmpty() || head == tail) {
        return true; // No hay nada que invertir si hay un solo nodo
    }
    LinkedNode<E> previous = null;
    LinkedNode<E> current = head;
    LinkedNode<E> next = null;

    while(current != null){
      next = current.getNext();
      current.setNext(previous);
      previous = current;
      current = next;
    }
    LinkedNode<E> temp = head;
    head = previous;
    tail = temp;
    return true;
  }

  @Override
  public int size() {
    int contador = 0;
    LinkedNode<E> current = head;
    while (current != null){
      contador++;
      current = current.getNext();
    }
    return contador;
  }

  @Override
  public void forEach(Function<E, Void> action) {
    LinkedNode<E> current = head;
    while (current != null){
      action.apply(current.get());
      current = current.getNext();
    }
  }

  @Override
  public Iterator<E> iterator() {
    inode = head;

    return new Iterator<E>() {

      public boolean hasNext() {
        return inode != null;
      }

      public E next() {
        if (!hasNext()) {
          throw new IllegalStateException("No more elements in the iterator");
        }

        E element = inode.get();
        inode = inode.getNext();
        return element;
      }
    };
  }

    public void setInode(LinkedNode<E> inode) {
        this.inode = inode;
    }

}