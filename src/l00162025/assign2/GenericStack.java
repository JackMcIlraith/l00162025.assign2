package l00162025.assign2;

/**
 * GenericStack Class backed by GenericLinkedList
 * Stack woks as a LIFO
 * @param <T> generic object
 */
public class GenericStack<T> implements IStack {
    private GenericLinkList<T> stackData; //Generic stack to be backed by a LinkedList

    /**
     * default constructor
     */
    public GenericStack() {
        this.stackData = new GenericLinkList<T>(); //initiate generic stack
    }

    /**
     * push element onto the top of the stack (or the tail of LinkedList)
     * @param element the element argument.
     */
    @Override
    public void push(Object element) {
        stackData.add(element); //add element to end of the stacks LinkedList
    }

    /**
     * Returns the most recent entered element of the stack
     * Removes it after the return with a clever little statement of finally! no need for a placeholder!
     * @return
     * @throws IndexOutOfBounds
     */
    @Override
    public Object pop() throws IndexOutOfBounds {
        if(!stackData.isEmpty()) {
            try { //clever little try/finally statement to return the last element of the linked list, then delete the last element without having to place the element in a holder variable

                return stackData.get(stackData.size()-1);
            }//return last in list (Last in- first out)

            finally {
                stackData.remove((stackData.size()-1)); //delete last entry in the LinkedList
            }
        } else return null;
    }

    /**
     * can evaluate the objct on the top of the stack.
     * will return null if stack is empty by design
     * @return top data from stack
     * @throws IndexOutOfBounds
     */
    @Override
    public Object peek() throws IndexOutOfBounds {
        return stackData.get(stackData.size()-1); // return the last node in the linked list
    }

    /**
     *
     * @return true if there is no data on the stack
     */
    @Override
    public boolean empty() {
        return stackData.isEmpty(); //return true if it is empty
    }
}
