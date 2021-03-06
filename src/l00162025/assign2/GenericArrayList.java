package l00162025.assign2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Managed array with default size of 5, just to demonstrate all aspects of arraylist
 * keeps track of the size using an internal tracker rather than dynamically finding its own size
 * @param <T>
 */
public class GenericArrayList <T> implements IList{
    private final int defaultSize = 5; //default arraylist size for this generic
    private int arrayCurrentMaxSize;
    private Object[] ourArray;
    private int currentFilled = 0;
    private Object IndexOutOfBoundsException;

//Constructor:

    /**
     * default constructor
     */
    public GenericArrayList() {
        this.arrayCurrentMaxSize = defaultSize;
        this.ourArray = (T[]) new Object[arrayCurrentMaxSize];
    }


//Inserters, Adders and Setter:

    /**
     * add element to the end of array,
     * assess if the array needs to be expanded first
     * @param elem
     */
    @Override
    public void add(Object elem) { //add to end of array
        resize(); //check and make resize if required
        ourArray[currentFilled] = elem; //add to end of array, off by 1 error already avoided as array[end+1] = currentFilled
        currentFilled++; //increment the size of array
    }

    /**
     * insert element into array at index, any other elements to the right will be moved 1, and array will be resized if required
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBounds
     */
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
            currentFilled++; //increase the size of current array
            resize(); //check if the array is at Capacity and resize if necessary
            int mover = currentFilled; // use an alternate helper cursor to ensure that the currentFilled value is not impacted by the function
            while(mover != index){ // move all elements to the right of insertion location one space to the right
                ourArray[mover]= ourArray[mover-1];
                mover--;
            }
            ourArray[index] = element; // insert element into target location
        }

    }

    /**
     * override element at index, if avalible, or add to end if target is 1 ore than current size
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return
     * @throws IndexOutOfBounds
     */
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
        Object hold = ourArray[index]; //hold array element before deletion, as needed as return
        ourArray[index] = element; // replace old element with new
        return hold; //return old element
    }


//Getters:

    /**
     *
     * @param index index of the element to return
     * @return
     * @throws IndexOutOfBounds
     */
    @Override
    public Object get(int index) throws IndexOutOfBounds {
        if(index-1 > currentFilled ){ //ensure index is within used array space
            try {
                throw new IndexOutOfBounds();
            } catch (IndexOutOfBounds indexOutOfBounds) {
                indexOutOfBounds.printStackTrace();
            }
            return null;
        }
        return ourArray[index];
    }


//Removers:

    /**
     * find and remove target element within array, move elements to close gap
     * @param elem the element to remove
     * @return
     * @throws IndexOutOfBounds
     */
    @Override
    public boolean remove(Object elem) throws IndexOutOfBounds { //inefficient function, as if we use a index object from contains(element) we could remove with the remove(index) function very quickly
        if(!contains(elem)){ //ensure element is in arraylist
            return false;
        }//once here we know that the element is present, once located we we can remove it with the remove(index) function
        if (ourArray[0] == elem){ //check if element 0 is target, if so, then remove
            remove(0);
            return true;
        }
        else if(ourArray[currentFilled-1] == elem){
            remove(currentFilled-1);
            return true;
        }
        else { //else use iterator to find element location
            int targetLocation = 1; //where we can find the target element for the rovove function
            GenericArrayListIterator iteratoror = new GenericArrayListIterator();
            while (iteratoror.hasNext()) { //iterate through arraylist to find element
                if(iteratoror.next() == elem){//evaluate
                    remove(targetLocation); //have to be careful of the ol' 1 off error
                    return true;
                }
                targetLocation++;
            }
        }
        //failsafe here, hopefully it will never be seen, but useful to keep track
        System.out.println("Element was found, but failed to remove");//if function fails to execute properly, we send a console message detailing the failing
        return false;
    }

    /**
     * remove target element at index of array, move elements to close gap
     * @param index
     * @return
     * @throws IndexOutOfBounds
     */
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
        Object holder = ourArray[index]; // begin overriding elements starting fom index location on array
        for(int i = index; i <currentFilled;i++){
            ourArray[i] = ourArray[i+1];
        }
        currentFilled--; //decrease current array size *****VIP*****
        return holder;
    }


//Helpers:

    /**
     * double array size if current size is equal to size limit. normaly would have a safety gap, such as if array is half full then expand, but for the sake of this project we make it small to demonstrate its functionality
     */
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

    public void printList(){ //used to test during development, I'm leaving it here in case there are issues or if wanted for future function
        System.out.println("ArrayList: " + ourArray[0]);
        GenericArrayListIterator iteratoror = new GenericArrayListIterator();
        while (iteratoror.hasNext()) {
            System.out.println("ArrayList: " + iteratoror.next());
        }

    }

    /**
     * returns current used size within arraylist
     * @return
     */
    @Override
    public int size() { //returns current size of used portion of array
        return this.currentFilled;
    }

    /**
     * if size is zero we assume list is empty
     * @return
     */
    @Override
    public boolean isEmpty() { //returns boolean true if used array space is null
        return (size() == 0);
    }

    /**
    Search array and return true if element is found
     */
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

    /**
     * Rotates elements of the list right or left depending on input
     *      * @param distance
     *      *      distance > 0 moves arraylist left
     *      *     distance < 0 moves arraylist right
     *      *     distance = 0 or *size() does nothing.
     * @param distance
     * @throws Throwable
     */
    @Override
    public void rotate(int distance) throws Throwable {
        if (isEmpty()) {
            throw (Throwable) IndexOutOfBoundsException;
        }
        distance = distance % ourArray.length;
        if (distance == 0) {
            return;
        } else if (distance <= 0) { //rotate right
            distance *= -1;
            for (int i = 0; i < distance; i++) {
                add(get(size() - 1));
                for (int j = size() - 1; j > 0; j--) {
                    set(j, get(j - 1));
                }
                set(0, get(size() - 1));
                remove(size() - 1);
            }
            return;
        } else if (distance >= 0) { //rotate left
            for (int i = 0; i < distance; i++) {
                add(get(0)); //very inefficient in memory if the size has exactly matched the limit of the arraylist, but if not, then its about O(n) per iteration
                remove(0);
            }
            return;
        }
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
        //    else if (cursor == 0){
        //        cursor++;
        //        return (T) ourArray[cursor-1];

        //    }
            else{
                cursor++; //iterate cursor
                return (T) ourArray[cursor]; //return element, has to be type cast to ensure generic is handled.
            }
        }

        public T current(){
            return (T) ourArray[cursor]; //returns current element in cursor, usefull hen resizing or looking for the last element and avoiding the out of index throw
        }
    }



























}
