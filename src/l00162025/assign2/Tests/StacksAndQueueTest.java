package l00162025.assign2.Tests;

import l00162025.assign2.GenericQueue;
import l00162025.assign2.GenericStack;

public class StacksAndQueueTest {

    public static void main(String[] args) throws Throwable {

        GenericStack<String> GStack = new GenericStack<>();
        GenericQueue<String> GQueue = new GenericQueue<>();

        System.out.println("Begin to enqueue Queue and load stack with 'First'");
        GQueue.enque("First");
        GStack.push("First");
        System.out.println("///////////////////////");
        System.out.println("Peek at the top of each: expect 'First'");
        System.out.println("Queue: " + GQueue.first());
        System.out.println("Stack: " + GStack.peek());
        System.out.println("///////////////////////");
        System.out.println();
        System.out.println("Enqueue Queue and load stack with 'Second'");
        GQueue.enque("Second");
        GStack.push("Second");
        System.out.println("///////////////////////");
        System.out.println("Enqueue Queue and load stack with 'Third'");
        GQueue.enque("Third");
        GStack.push("Third");
        System.out.println("///////////////////////");

        System.out.println("Unload Queue with dequeue, expect 123 (FIFO)");
        System.out.println("1 Queue: " + GQueue.dequeue());
        System.out.println("2 Queue: " + GQueue.dequeue());
        System.out.println("3 Queue: " + GQueue.dequeue());
        System.out.println("is Queue empty: " + GQueue.empty());
        System.out.println("***********************************************");

        System.out.println("Unload Stack with pop, expect 321 (LIFO)");
        System.out.println("1 Stack: " + GStack.pop());
        System.out.println("2 Stack: " + GStack.pop());
        System.out.println("3 Stack: " + GStack.pop());
        System.out.println("is Stack empty: " + GStack.empty());
        System.out.println("***********************************************");

    }

}
