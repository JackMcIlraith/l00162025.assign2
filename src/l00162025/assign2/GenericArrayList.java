package l00162025.assign2;

import java.util.Iterator;
import java.util.function.Consumer;

public class GenericArrayList <T> implements IList{
    private final int defaultSize = 5; //default arraylist size for this generic
    private int arrayCurrentMaxSize;
    private Object[] ourArray = (Object[]) new Object[arrayCurrentMaxSize];
    private int currentFilled = 0;

//Constructor:
    public GenericArrayList() {
        this.arrayCurrentMaxSize = defaultSize;
        this.ourArray = (T[]) new Object[arrayCurrentMaxSize];
    }



//Inserters, Adders and Setter:

    @Override
    public void add(Object elem) { //add to end of array
        resize(); //check and make resize if required
        ourArray[currentFilled] = elem; //add to end of array, off by 1 error already avoided as array[end+1] = currentFilled
        currentFilled++; //increment the size of array
    }

    @Override
    public void add(int index, Object element) throws IndexOutOfBounds { //inserts element ino array at index location - make sure there is space left, and increase size of current capacity
        if(index-1 > currentFilled ){ //check and make sure we are not trying to insert into unmannaged section of array
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
        }
        else {
            resize(); //check if the array is at Capacity and resize if necessary
            currentFilled++; //increase the size of current array
            int mover = currentFilled; // use an alternate helper cursor to ensure that the currentFilled value is not impacted by the function
            while(mover != index-1){ // move all elements to the right of insertion location one space to the right
                ourArray[mover]= ourArray[mover-1];
                mover--;
            }
            ourArray[index-1] = element; // insert element into target location
        }

    }

    @Override
    public Object set(int index, Object element) throws IndexOutOfBounds {
        if(index > currentFilled ){ //ensure index is within used array bounds
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        Object hold = ourArray[index-1]; //hold array element before deletion, as needed as return
        ourArray[index-1] = element; // replace old element with new
        return hold; //return old element
    }


//Getters:

    @Override
    public Object get(int index) throws IndexOutOfBounds {
        if(index > currentFilled ){ //ensure index is within used array space
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        return ourArray[index-1]; // return index -1 to avoid off by 1 error
    }

//Removers:
    @Override
    public boolean remove(Object elem) throws IndexOutOfBounds { //inefficient function, as if we use a index object from contains(element) we could remove with the remove(index) function very quickly
        if(!contains(elem)){ //ensure element is in arraylist
            return false;
        }//once here we know that the element is present, once located we we can remove it with the remove(index) function
        if (ourArray[0] == elem){ //check if element 0 is target, if so, then remove
            remove(1);
        }
        else { //else use iterator to find element location
            int targetLocation = 1; //where we can find the target element for the rovove function
            GenericArrayListIterator iteratoror = new GenericArrayListIterator();
            while (iteratoror.hasNext()) { //iterate through arraylist to find element
                if(iteratoror.next() == elem){//evaluate
                    remove(targetLocation+1); //have to be careful of the ol' 1 off error
                    return true;
                }
                targetLocation++;
            }
        }
        //failsafe here, hopefully it will never be seen, but useful to keep track
        System.out.println("Element was found, but failed to remove");//if function fails to execute properly, we send a console message detailing the failing
        return false;
    }

    @Override
    public Object remove(int index) throws IndexOutOfBounds {
        if(index > currentFilled ){ //throw exception if index out of bounds.
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        //begin to copy array to new location.
        //while System.arraycopy will do this, it was decided that learning to do it had its own better value
        Object holder = ourArray[index-1]; // begin overriding elements starting fom index location on array
        for(int i = index-1; i <currentFilled;i++){
            ourArray[i] = ourArray[i+1];
        }
        currentFilled--; //decrease current array size *****VIP*****
        return holder;
    }

//Helpers:

    private void resize(){
        if(currentFilled >= arrayCurrentMaxSize){ //check if we have reached the limit of array - at the moment we only worry if we are at the limit, in reality it may be better to have a tolerance such as 2/3 capacity to ensure that there is memory available for the move, but as the scope is small at the moment we can get away with it
            int resizeCopyIndex = 1; //helper to copy array
            T[] resizedArray = (T[]) new Object[arrayCurrentMaxSize*2];
            //again, System.arraycopy can be used to do this, but as we're building a class, we may as well do this manually too
            GenericArrayListIterator iteratoror = new GenericArrayListIterator();
            resizedArray[0] = iteratoror.current(); //assign array[0] as this will be missed by the iterator
            while (iteratoror.hasNext()) { //iterate and copy array to newer, bigger, better and faster array. UPGRADES PEOPLE!!!
                resizedArray[resizeCopyIndex] = iteratoror.next(); //will not return last
                resizeCopyIndex++;
            }
            resizedArray[resizeCopyIndex] = iteratoror.current(); //add the last element of array, could have used a do-while loop, but wanted the extra safety of having iterator.next to throw ts own exception then iterating.
            ourArray = resizedArray; //override old array
            arrayCurrentMaxSize = arrayCurrentMaxSize*2; //log new max size increase we need to resize again, it should now expand exponential if required.
        }
    }

    public void printArray(){ //used to test during development, I'm leaving it here in case there are issues or if wanted for future function
        System.out.println(ourArray[0]);
        GenericArrayListIterator iteratoror = new GenericArrayListIterator();
        while (iteratoror.hasNext()) {
            System.out.println(iteratoror.next());
        }

    }

    @Override
    public int size() { //returns current size of used portion of array
        return this.currentFilled;
    }

    @Override
    public boolean isEmpty() { //returns boolean true if used array space is null
        return (size() == 0);
    }

    @Override
    public boolean contains(Object element) { //returns true if array contains element
        if(ourArray[0] == element){ //check if 0 is true,
            return true;
        } //else use iterator to search array
        GenericArrayListIterator iteratoror = new GenericArrayListIterator();
        while (iteratoror.hasNext()) { //ensure that we do not evaluate null space or index out of bounds
            if(iteratoror.next() == element){ //boolean evaluation if element is the same. may be replaced by equals() once further tested.
                return true;
            }
        }
        return false;
    }

//Iterators:

    @Override
    public Iterator iterator() {
        return new GenericArrayListIterator();
    }

    class GenericArrayListIterator implements Iterator<T> {

        private int cursor = 0; //index of where we have iterated to


        @Override
        public boolean hasNext() {
            if(cursor+1 >= currentFilled){ //see if there is any more elements in array
                return false;
            } else return true;
        }

        @Override
        public T next() {
            if(!hasNext()){ // added security by having the next function check if available. not normally present, but useful to avoid errors. requires the use of a current element function to get last element safely
                try {
                    throw new IndexOutOfBounds();
                } catch (IndexOutOfBounds indexOutOfBounds) {
                    indexOutOfBounds.printStackTrace();
                }
                return null; //should throw exception IndexOutOfBounds before reaching here
            }
            else{
                cursor++; //iterate cursor
                return (T) ourArray[cursor]; //return element, has to be type cast to ensure generic is handled.
            }
        }

        public T current(){
            return (T) ourArray[cursor]; //returns current element in cursor, usefull hen resizing or looking for the last element and avoiding the out of index throw
        }
    }



    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///ToDo - check that == works when evaluating Persons class, if needed change to equals()
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
























}
