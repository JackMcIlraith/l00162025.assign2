package l00162025.assign2;

import java.util.Iterator;
import java.util.function.Consumer;

public class GenericArrayList <T> implements IList{
    private final int defaultSize = 5; //default arraylist size for this generic
    private int arrayCurrentMaxSize;
    private Object[] ourArray = (Object[]) new Object[arrayCurrentMaxSize];
    private int currentFilled = 0;

    public GenericArrayList() {
        this.arrayCurrentMaxSize = defaultSize;
        this.ourArray = (T[]) new Object[arrayCurrentMaxSize];
    }

    public void printArray(){
        System.out.println(ourArray[0]);
        GenericArrayListIterator iteratoror = new GenericArrayListIterator();
        while (iteratoror.hasNext()) {
            System.out.println(iteratoror.next()); //will not return last
        }

    }

    @Override
    public int size() {
        return this.arrayCurrentMaxSize;
    }

    @Override
    public boolean isEmpty() {
        return (size() == 0);
    }

    @Override
    public Iterator iterator() {
        return new GenericArrayListIterator();
    }

    class GenericArrayListIterator implements Iterator<T> {
        private int cursor = 0; //index of where we have iterated to
        @Override
        public boolean hasNext() {
            if(cursor+1 >= currentFilled){
                return false;
            } else return true;
        }

        @Override
        public T next() {
            if(!hasNext()){
                try {
                    throw new IndexOutOfBounds();
                } catch (IndexOutOfBounds indexOutOfBounds) {
                    indexOutOfBounds.printStackTrace();
                }
                return null; //should throw exception IndexOutOfBounds before reaching here
            }
            else{
                cursor++;
                return (T) ourArray[cursor];
            }
        }

        public T current(){
            return (T) ourArray[cursor];
        }
    }

    private void resize(){
        if(currentFilled >= arrayCurrentMaxSize){
            int resizeCopyIndex = 1;
            T[] resizedArray = (T[]) new Object[arrayCurrentMaxSize*2];
            GenericArrayListIterator iteratoror = new GenericArrayListIterator();
            resizedArray[0] = iteratoror.current();
            while (iteratoror.hasNext()) {
                resizedArray[resizeCopyIndex] = iteratoror.next(); //will not return last
                resizeCopyIndex++;
            }
            resizedArray[resizeCopyIndex] = iteratoror.current();
            ourArray = resizedArray;
        }
    }

    @Override
    public void add(Object elem) {
        resize();
        ourArray[currentFilled] = elem;
        currentFilled++;
    }

    @Override
    public Object get(int index) throws IndexOutOfBounds {
        if(index > currentFilled ){
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        return ourArray[index-1];
    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBounds {
        if(index > currentFilled ){
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        Object hold = ourArray[index-1];
        ourArray[index-1] = element;
        return hold;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///ToDo - gittest
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void add(int index, Object element) throws IndexOutOfBounds {

    }

    @Override
    public Object remove(int index) throws IndexOutOfBounds {
        return null;
    }

    @Override
    public boolean remove(Object elem) {
        return false;
    }

    @Override
    public boolean contains(Object element) {
        return false;
    }


}
