package l00162025.assign2;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class GenericLinkList <T> implements IList{
    Node headNode;
    Node tailNode;
    private Object IndexOutOfBoundsException;

    public void printList(){
        Node current = headNode;
        while (current != null){
            System.out.println("LinkedList: " + current.data);
            current = current.nextNode;
        }
    }


    private class Node{
        Object data;
        Node nextNode;

        public Node(Object data) {
            this.data = data;
            nextNode = null;
        }
    }


    /**
     * //Helper function to enable adding to the very start of List for the first element.
     * @param elem is inserted as headNode
     */
    private void addToStart(Object elem) {
        Node newNode = new Node(elem);
        newNode.nextNode = headNode;
        headNode = newNode;

    }

    /**
     * add element to back of list
     * @param elem to be added to the end of the list
     */
 @Override
    public void add(Object elem) {
     if (headNode == null) {       //If the head node is missing or not built yet, then this wll create a new "First" node
         addToStart(elem);
     } else {                       //actual add to end of List
         Node current = headNode;
         tailNode = headNode;
         while (current != null) {
             tailNode = current;
             current = current.nextNode;
         }
         tailNode.nextNode = new Node(elem);
         tailNode = tailNode.nextNode;
         tailNode.nextNode = null;
     }
 }

    /**
     * returns if the list is empty or headless
     * @return true if there is no data in head
     */
    @Override
    public boolean isEmpty() {
        if(this.size() == 0){ //if size = zero, then it is empty by deff. or if head is missing.... but that's a bigger problem
            return true;
        } else return false;
    }

    /**
     * size of LinkedList, uses the Iterator to walk the list
     * @return
     */
    @Override
    public int size() {
        if (headNode == null){
            return 0;
        }
        int sizeOfList = 1;
        Node current = headNode;
        GenericLinkListIterator Iteratoror = new GenericLinkListIterator();
        while (Iteratoror.hasNext()){
            sizeOfList++;
            Iteratoror.next();
        }
        return sizeOfList;
    }

    /**
     * Itterator as defined by interface
     * @return
     */
    @Override
    public Iterator<Node> iterator() {
        return new GenericLinkListIterator();
    }

    /**
     * Rotates elemnt of the list right or left depending on input
     * @param distance
     *      distance > 0 moves arraylist left
     *     distance < 0 moves arraylist right
     *     distance = 0 or *size() does nothing.
     * @throws Throwable
     */
    @Override
    public void rotate(int distance) throws Throwable {
        if (isEmpty()) {
            throw (Throwable) IndexOutOfBoundsException;
        }
        distance = distance % size();

        if(distance == 0){
            return;
        }
        else if (distance > 0){
            for(int i = 0; i < distance; i++) {
                tailNode.nextNode = headNode;
                tailNode = tailNode.nextNode;
                headNode = headNode.nextNode;
                tailNode.nextNode = null;
            }
            return;
        }
        else if(distance < 0){
            distance *=-1;
            for(int i = 0; i < distance; i++ ){
                tailNode.nextNode=headNode;
                tailNode = tailNode.nextNode;
                headNode = headNode.nextNode;
                tailNode.nextNode = null;
            }
            return;
        }
    }

    class GenericLinkListIterator implements Iterator <Node>{
        private Node cursor;
        public GenericLinkListIterator() {
            cursor = headNode;
        }



        @Override
        public boolean hasNext() {
            if(cursor.nextNode == null){
                return false;
            } else return true;
        }

        @Override
        public Node next() {
            if (!hasNext()){
                try {
                    throw new IndexOutOfBounds();
                } catch (IndexOutOfBounds indexOutOfBounds) {
                    indexOutOfBounds.printStackTrace();
                }
                return null;
            }
            else {
                cursor = cursor.nextNode;
                return cursor;
            }
        }


    }

    /**
     *
     * @param index index of the element to return
     * @return
     * @throws IndexOutOfBounds
     */
    @Override
    public Object get(int index) throws IndexOutOfBounds {
        if(index > size()){
            throw new IndexOutOfBounds();
        }
        if(index == 0){
            return headNode.data;
        }
        GenericLinkListIterator iteratoror = new GenericLinkListIterator();
        for(int i = 0; i < index; i++){
            iteratoror.next();
        }
        return iteratoror.cursor.data;

    }

    /**
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return
     * @throws IndexOutOfBounds
     */
    @Override
    public Object set(int index, Object element) throws IndexOutOfBounds {
        if(index > size()){
            throw new IndexOutOfBounds();
        }
        Node current = headNode;
        for(int i = 0; i< index; i++){
            current = current.nextNode;
        }
        Node holdElement = new Node(current.data);
        current.data = element;
        //System.out.println(holdElement.data + " has been replaced by " + current.data);
        return holdElement.data;
    }

    /**
     *
     * @param element the element to search for
     * @returnelement to search found
     */
    @Override
    public boolean contains(Object element) {
        int index = size();
        GenericLinkListIterator iteratoror = new GenericLinkListIterator();
        if(headNode.data == element){
            return true;
        }
        for(int i = 1; i < index; i++){
            if(iteratoror.cursor.data == element){
                return true;
            }
            iteratoror.next();
        }
        return false;
    }

    /**
     *
     * @param elem the element to remove
     * @return
     */
    @Override
    public boolean remove(Object elem) {
        if(!contains(elem)) { //check if element exists
            return false;
        }
        if(headNode.data == elem){ // check head
            headNode = headNode.nextNode;
            return true;
        }
        else { //iterate through and find element
            Node current = headNode;
            for(int i = 1; i< size(); i++){
                if(current.nextNode.data == elem){
                    current.nextNode = current.nextNode.nextNode;
                    return true;
                }
                current = current.nextNode; //remove by skipping the node that contains the target element
            }
            return false;
        }

    }

    /**
     * insert element into list  and shift other elements after
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws IndexOutOfBounds
     */
    @Override
    public void add(int index, Object element) throws IndexOutOfBounds {
        Node newNode = new Node(element);
        if(index > size()+1){
            throw new IndexOutOfBounds();
        }
        if(index == 0){
            newNode.nextNode = headNode;
            headNode = newNode;
        }

        else if((index) == size()){
            add(element);
        }
        else {
            Node current = headNode;
            for(int i = 1; i < index; i++){
                current = current.nextNode;
            }
            if(current.nextNode != null) {
                newNode.nextNode = current.nextNode;
            }
            current.nextNode = newNode;
        }
    }

    /**
     * @param index
     * @return if data from the node if it has been removed
     * @throws IndexOutOfBounds
     */
    @Override
    public Object remove(int index) throws IndexOutOfBounds {
        Node toRemove = new Node(get(index));
        Node current = headNode;
        if(index > size()){
            throw new IndexOutOfBounds();
        }
        else if(index == 0){
            headNode = headNode.nextNode;
            return toRemove;
        }
        for(int i = 0; i < index-1; i++){
            current = current.nextNode;
        }
        current.nextNode = current.nextNode.nextNode;
        return toRemove;

    }




}
