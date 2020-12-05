package l00162025.assign2;

/**
 * //Works as a FIFO Queue backed by GenericArrayList
 * @param <T>
 */
public class GenericQueue<T> implements IQueue { //Works as a FIFO Queue
    //Underlying ArrayList will throw out of bound errors if user attempts to dequeue or first an empty queue
    private GenericArrayList<T> queueData;

    /**
     * default constructor
     */
    public GenericQueue() {
        this.queueData = new GenericArrayList<T>();
    }

    /**
     * Adds an element to the back for the queue (the back of the GenericArrayList)
     * @param element the element argument.
     */
    @Override
    public void enque(Object element) { //accepting that the spelling of enque is by design from CA2 req sheet, just in case testing is built based on this command
        queueData.add(element); //adds element to the end of queue
    }

    /**
     * returns then deletes the first object placed in the queue as per FIFO
     * @return the first element of the queue (GenericArrayList[0]
     * @throws IndexOutOfBounds if there is no data in queue
     */
    @Override
    public Object dequeue() throws IndexOutOfBounds{
        if(!queueData.isEmpty()) {
            //Clever little workaround to avoid having to place element in a holding variable
            try {
                return queueData.get(0); //returns first element in Queue, as per fifo.
            } finally {
                queueData.remove(0); //deletes first element in queue after it is returned.
            }
        }else return null;
    }


    /**
     * looks the the fist data in the queue without removing it
     * @return first element in queue
     * @throws IndexOutOfBounds
     */
    @Override
    public Object first() throws IndexOutOfBounds {
        return queueData.get(0); //returns first element in Queue, as per fifo. does not delete
    }

    /**
     * is the queue empty
     * @return true if there is no data in queue
     */
    @Override
    public boolean empty() {
        return queueData.isEmpty(); //return if it is empty
    }
}
