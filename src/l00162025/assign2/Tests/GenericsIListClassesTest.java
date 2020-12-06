package l00162025.assign2.Tests;

import l00162025.assign2.GenericArrayList;
import l00162025.assign2.GenericLinkList;
import l00162025.assign2.Person;

public class GenericsIListClassesTest {
    public static void main(String[] args) throws Throwable {
        //Test Objects:
        Person person1 = new Person(20, "Hegarty", "Dermot");
        Person person2 = new Person(23,"Minchin", "Tim");
        Person person3 = new Person(19, "DiCamillo", "Kate");
        Person person4 = new Person(28, "Hardinge", "Frances");
        Person person5 = new Person(24, "Bryson", "Bill");
        Person person6 = new Person(26, "Ness", "Patrick");
        //Test Lists
        GenericLinkList<Person> testLinkedList = new GenericLinkList<>();
        GenericArrayList<Person> testArrayList = new GenericArrayList<>();


        System.out.println("//////////////////////////////////////////////////////////");
        System.out.println("Test isEmpty - expect true");
        System.out.println("ArrayList: " + testArrayList.isEmpty());
        System.out.println("LinkedList: " + testLinkedList.isEmpty());
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person1)");
        testArrayList.add(person1);
        testLinkedList.add(person1);
        testArrayList.printList();
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test isEmpty - expect false");
        System.out.println("ArrayList: " + testArrayList.isEmpty());
        System.out.println("LinkedList: " + testLinkedList.isEmpty());
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test size() - expect 1");
        System.out.println("ArrayList: " + testArrayList.size());
        System.out.println("LinkedList: " + testLinkedList.size());
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person2)");
        testArrayList.add(person2);
        testLinkedList.add(person2);
        testArrayList.printList();
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test size() - expect 2");
        System.out.println("ArrayList: " + testArrayList.size());
        System.out.println("LinkedList: " + testLinkedList.size());
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person3) at index 0");
        testArrayList.add(0,person3);
        testLinkedList.add(0,person3);
        testArrayList.printList();
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person4) at index 3 ie add to end, but by index");
        testArrayList.add(3,person4);
        testLinkedList.add(3,person4);
        testArrayList.printList();
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person5) at index 2" );
        testArrayList.add(2,person5);
        testLinkedList.add(2,person5);
        testArrayList.printList();
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test add (person6) at index 4 - this is to ensure that ArrayList resizes correctly" );
        testArrayList.add(4,person6);
        testLinkedList.add(4,person6);
        testArrayList.printList();
        System.out.println("*****");
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test remove (index 2) Bill" );
        testArrayList.remove(2);
        testLinkedList.remove(2);
        testArrayList.printList();
        System.out.println("*****");
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");


        System.out.println("Test remove (person6 ie Patrick)" );
        testArrayList.remove(person6);
        testLinkedList.remove(person6);
        testArrayList.printList();
        System.out.println("*****");
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test remove (person3 ie Kate)" );
        testArrayList.remove(person3);
        testLinkedList.remove(person3);
        testArrayList.printList();
        System.out.println("*****");
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");

        System.out.println("Test set() all 3 to person5 ie Bill" );
        testArrayList.set(0,person5);
        testArrayList.set(1,person5);
        testArrayList.set(2,person5);
        testLinkedList.set(0,person5);
        testLinkedList.set(1,person5);
        testLinkedList.set(2,person5);
        testArrayList.printList();
        System.out.println("*****");
        testLinkedList.printList();
        System.out.println("//////////////////////////////////////////////////////////");



    }
}
