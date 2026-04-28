package edu.fsadriann.app.array;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import edu.fsadriann.model.array.AbstractArray;
import edu.fsadriann.model.collection.Collection;
import edu.fsadriann.model.iterator.Iterator;

public class Array<E> extends AbstractArray<E>{

    private E[] array;
    private int capacity;
    private int actual;
    public int head;
    public int tail;

    @SuppressWarnings("unchecked")
    public Array(int size){
        array = (E[]) new Object[size];
        this.capacity = size;
        this.actual=0;
    }

    @Override
    public boolean isEmpty(){
        return actual==0;
    }

    @Override
    public int size(){
        return actual;
    }

    @Override
    public boolean contains(Collection<E> collection){
        if(collection==null){
            return true;
        }
        if(isEmpty()){
            return false;
        }

        Iterator<E> iterator=collection.iterator();
        while(iterator.hasNext()){
            if(!contains(iterator.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean clear(){
        for(int i=0;i<capacity;i++){
            array[i]=null;
        }
        actual=0;
        head=0;
        tail=-1;
        return true;
    }

    @Override
    public boolean reverse(){
        if(actual==1||isEmpty()){
            return true;
        }
        for(int i=0;i<actual/2;i++){

            int a=(head+i)%capacity;
            int b=(head+actual-1-i)%capacity;

            E tem=array[a];
            array[a]=array[b];
            array[b]=tem;
        }
        return true;
    }

    @Override
    public boolean add(E element){
        if(actual==capacity||element==null){
            return false;
        }

        if(actual==0){
            head=0;
            tail=0;
        }else{
            tail=(tail+1)%capacity;
        }

        array[tail]=element;
        actual++;
        return true;
    }

    @Override
    public boolean add(int index,E[] array){
        if(array==null||index>actual||index<0||(actual+array.length)>capacity){
            return false;
        }


        for(int i=actual-1;i>=index;i--){
            int from=(head+i)%capacity;
            int to=(head+i+array.length)%capacity;
            this.array[to]=this.array[from];
        }

        for(int i=0;i<array.length;i++){
            int pos=(head+index+i)%capacity;
            this.array[pos]=array[i];
        }
        tail=(head+actual+array.length-1)%capacity;

        actual+=array.length;
        return true;
    }

    @Override
    public boolean add(int index, Collection<E> collection){
        if(collection==null||index>actual||index<0||(actual+collection.size())>capacity){
            return false;
        }
        int n=collection.size();
        for(int i=actual-1;i>=index;i--){
            int from=(head+i)%capacity;
            int to=(head+i+n)%capacity;
            this.array[to]=this.array[from];
        }
        int i=0;
        Iterator<E> iterator=collection.iterator();
        while(iterator.hasNext()){
            int pos=(head+index+i)%capacity;
            array[pos]=iterator.next();
            i++;
        }

        tail=(head+actual+n-1)%capacity;
        actual+=n;
        return true;
    }

    @Override
    public E get(int index){
        if(index<0||index>=actual||isEmpty()){
            return null;
        }
        int indexReal=(head+index)%capacity;

        return array[indexReal];
    }
    @Override
    public int indexOf(E element){
        if(element==null||isEmpty()){
            return -1;
        }
        for(int i=0;i<actual;i++){
            int index=(head+i)%capacity;
            if(element.equals(array[index])){
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(E element){
        if(element==null||isEmpty()){
            return -1;
        }
        for(int i=actual-1;i>=0;i--){
            int index=(head+i)%capacity;
            if(element.equals(array[index])){
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean remove(int index){
        if(index<0||index>=actual||isEmpty()){
            return false;
        }

        for(int i=index;i<actual-1;i++){
            int from=(head+i+1)%capacity;
            int to=(head+i)%capacity;
            array[to]=array[from];
        }

        if(actual==1){
            return clear();
        }else{
            int tailIndex=(head+actual-1)%capacity;
            array[tailIndex]=null;
            tail=(head+actual-2+capacity)%capacity;
            actual--;
            return true;
        }
    }
    @Override
    public boolean remove(int from, int to){
        if(from<0 || to<0 || isEmpty()||to>actual||from>=to){
            return false;
        }
        int count=to-from;
        for(int i=from;i<actual-count;i++){
            int fromIndex=(head+i+count)%capacity;
            int toIndex=(head+i)%capacity;
            array[toIndex]=array[fromIndex];
        }

        for(int i=actual-count;i<actual;i++){
            int index=(head+i)%capacity;
            array[index]=null;
        }
        actual-=count;
        if(actual==0){
            head=0;
            tail=-1;
        }else{
            tail=(head+actual-1)%capacity;
        }
        return true;


    }

    @Override
    public boolean set(int index, E element){
        if(element==null||index<0||index>=actual){
            return false;
        }

        int indexReal=(head+index)%capacity;

        array[indexReal]=element;
        return true;
    }

    @Override
    public Iterator<E> iterator(){
        return new Iterator<>(){
            private int index=0;

            @Override
            public boolean hasNext(){
                return index<actual;
            }
            @Override
            public E next(){
                if(!hasNext()){
                    throw new NoSuchElementException("No more elements");
                }

                int index2=(head+index)%capacity;
                E element=array[index2];
                index++;
                return element;
            }
        };
    }

    @Override
    public void forEach(Function<E, Void> action){
        if(action==null||isEmpty()){
            return;
        }
        Iterator<E> iterator=iterator();
        while(iterator.hasNext()){
            action.apply(iterator.next());
        }

    }

    @Override
    public boolean remove(Predicate<E> filter){
        if(filter==null||isEmpty()){
            return false;
        }

        int i=0;
        boolean removed=false;
        while(i<actual){
            int realIndex=(head+i)%capacity;
            E value=array[realIndex];

            if(filter.test(value)){
                remove(i);
                removed=true;
            }else{
                i++;
            }

        }
        return removed;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void defragment(){
        if(actual==0){
            head=0;
            tail=-1;
            return;
        }

        E[] nuevo=(E[]) new Object[capacity];

        for(int i=0;i<actual;i++){
            nuevo[i]=array[(head+i)%capacity];
        }
        array=nuevo;
        head=0;
        tail=actual-1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean dimension(int dimension){
        if(dimension<actual||dimension<=0){
            return false;
        }
        E[] nuevo=(E[]) new Object[dimension];

        for(int i=0;i<actual;i++){
            nuevo[i]=array[((head+i)%capacity)];
        }

        array=nuevo;
        capacity=dimension;
        head=0;
        tail=actual-1;
        return true;
    }
}