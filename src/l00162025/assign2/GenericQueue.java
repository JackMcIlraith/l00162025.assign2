package l00162025.assign2;

public class GenericQueue<T> implements IQueue { //Works as a FIFO Queue
    //Underlying ArrayList will throw out of bound errors if user attempts to dequeue or first an empty queue
    private GenericArrayList<T> queueData;

    public GenericQueue() {
        this.queueData = new GenericArrayList<T>();
    }

    @Override
    public void enque(Object element) { //accepting that the spelling of enque is by design from CA2 req sheet, just in case testing is built based on this command
        queueData.add(element); //adds element to the end of queue
    }

    @Override
    public Object dequeue() throws IndexOutOfBounds{
        if(!queueData.isEmpty()) {
            //Clever little workaround to avoid having to place element in a holding variable
            try {
                return queueData.get(1); //returns first element in Queue, as per fifo.
            } finally {
                queueData.remove(1); //deletes first element in queue after it is returned.
            }
        }else return null;
    }

    @Override
    public Object first() throws IndexOutOfBounds {
        return queueData.get(1); //returns first element in Queue, as per fifo. does not delete
    }

    @Override
    public boolean empty() {
        return queueData.isEmpty(); //return if it is empty
    }
}
