package edu.fsadriann.model.array;

import edu.fsadriann.model.collection.AbstractCollection;
import edu.fsadriann.model.iterator.Iterator;

public abstract class AbstractArray<E> extends AbstractCollection<E> implements BufferArray<E> {

    @Override
    public boolean contains(E element){
        
        Iterator<E> iterator=iterator();
        while(iterator.hasNext()){
            if(iterator.next().equals(element)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(E[] array){

        for(E element: array){
            if(!contains(element)){
                return false;
            }
        }
        return true;
    }


}