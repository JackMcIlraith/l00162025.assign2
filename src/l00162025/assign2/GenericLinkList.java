package l00162025.assign2;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class GenericLinkList <T> implements IList{
    Node headNode;
    Node tailNode;

    public void printList(){
        Node current = headNode;
        while (current != null){
            System.out.println(current.data);
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


//Helper function to enable adding to the very start of List for the first element.
    private void addToStart(Object elem) {
        Node newNode = new Node(elem);
        newNode.nextNode = headNode;
        headNode = newNode;

    }

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
     }
 }

    @Override
    public boolean isEmpty() {
        if(this.size() == 0){ //if size = zero, then it is empty by deff. or if head is missing.... but that's a bigger problem
            return true;
        } else return false;
    }

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


    @Override
    public Iterator<Node> iterator() {
        return new GenericLinkListIterator();
    }

    @Override
    public void rotate(int distance) throws Throwable {

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

    @Override
    public Object get(int index) throws IndexOutOfBounds {
        if(index > size()){
            throw new IndexOutOfBounds();
        }
        GenericLinkListIterator iteratoror = new GenericLinkListIterator();
        for(int i = 1; i < index; i++){
            iteratoror.next();
        }
        return iteratoror.cursor.data;

    }

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

    @Override
    public boolean contains(Object element) {
        int index = size();
        GenericLinkListIterator iteratoror = new GenericLinkListIterator();
        for(int i = 0; i < index; i++){
            if(iteratoror.cursor.data == element){
                return true;
            }
            iteratoror.next();
        }
        return false;
    }

    @Override
    public boolean remove(Object elem) {
        if(!contains(elem)) {
            return false;
        }
        else if(headNode.data == elem){
            headNode = headNode.nextNode;
            return true;
        }
        else {
            Node current = headNode;
            for(int i = 0; i< size(); i++){
                if(current.nextNode.data == elem){
                    current.nextNode = current.nextNode.nextNode;
                    return true;
                }
                current = current.nextNode;
            }
            return false;
        }

    }
    @Override
    public void add(int index, Object element) throws IndexOutOfBounds {
        Node newNode = new Node(element);
        if(index > size()+1){
            throw new IndexOutOfBounds();
        }
        if(index == 1){
            newNode.nextNode = headNode;
            headNode = newNode;
        }

        else if((index -1) == size()){
            add(element);
        }
        else {
            Node current = headNode;
            for(int i = 1; i < index-1; i++){
                current = current.nextNode;
            }
            if(current.nextNode != null) {
                newNode.nextNode = current.nextNode;
            }
            current.nextNode = newNode;
        }
    }


    @Override
    public Object remove(int index) throws IndexOutOfBounds {
        Node toRemove = new Node(get(index));
        Node current = headNode;
        if(index > size()){
            throw new IndexOutOfBounds();
        }
        else if(index == 1){
            headNode = headNode.nextNode;
            return toRemove;
        }
        for(int i = 1; i < index-1; i++){
            current = current.nextNode;
        }
        current.nextNode = current.nextNode.nextNode;
        return toRemove;

    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///ToDo
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////








}
