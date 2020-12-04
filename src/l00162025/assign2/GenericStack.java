package l00162025.assign2;

public class GenericStack<T> implements IStack {
    private GenericLinkList<T> stackData; //Generic stack to be backed by a LinkedList


    public GenericStack() {
        this.stackData = new GenericLinkList<T>(); //initiate generic stack
    }

    @Override
    public void push(Object element) {
        stackData.add(element); //add element to end of the stacks LinkedList
    }

    @Override
    public Object pop() throws IndexOutOfBounds {
        if(!stackData.isEmpty()) {
            try { //clever little try/finally statement to return the last element of the linked list, then delete the last element without having to place the element in a holder variable

                return stackData.get(stackData.size());
            }//return last in list (Last in- first out)

            finally {
                stackData.remove(stackData.get(stackData.size())); //delete last entry in the LinkedList
            }
        } else return null;
    }

    @Override
    public Object peek() throws IndexOutOfBounds {
        return stackData.get(stackData.size()); // return the last node in the linked list
    }

    @Override
    public boolean empty() {
        return stackData.isEmpty(); //return if it is empty
    }
}
