package l00162025.assign2;

import java.util.LinkedList;

public class Main {



    public static void main(String[] args) throws IndexOutOfBounds {


    	/*
	LinkList testing




	GenericLinkList<String> TestList = new GenericLinkList<>();
    //    System.out.println(TestList.isEmpty());
	TestList.add("First in");
	//TestList.printList();
		TestList.add("Second in");
		TestList.add("Third in");
		TestList.add("The end?");
		TestList.printList();
       System.out.println(TestList.size());
   //     System.out.println(TestList.isEmpty());
		GenericLinkList<String> TestList2 = new GenericLinkList<>();
		TestList2.add(TestList.get(3));
		System.out.println("Testing get function by new list having element 3 of old; expect 'third but 1' ");
		TestList2.printList();
		System.out.println("Trying to reset 'Third in' with 3333");
		TestList.set(3,"3333");
		TestList.printList();
		TestList.printList();
		System.out.println(TestList.contains("The end?"));
		TestList.remove("3333");
		TestList.printList();
		TestList.remove("The end?");
		TestList.printList();
		System.out.println("////////////");
		TestList.remove("First in");
		TestList.printList();
		System.out.println("/////////// add to start of list with index 1");
		TestList.add(1, "added to First");
		TestList.printList();
		System.out.println("/////////// add to start of list with index 3");
		TestList.add(4, "inserted to number 4");
		TestList.printList();
		System.out.println("********************************");
		TestList.printList();
		TestList.get(1);
		TestList.get(2);
		TestList.get(3);
		TestList.get(4);
		TestList.remove(4);
		TestList.printList();

		*/

		GenericArrayList<String> myTest = new GenericArrayList<>();
		myTest.add("one");
		myTest.add("two");
		myTest.add("three");
		myTest.add("four");
		myTest.add("five");
		myTest.printArray();
		myTest.add("six");
		myTest.printArray();
		System.out.println("//////////////");
		System.out.println(myTest.get(1));
		System.out.println(myTest.get(2));
		System.out.println(myTest.get(3));
		System.out.println(myTest.get(4));
		System.out.println(myTest.get(5));
		System.out.println(myTest.get(6));
		myTest.set(3,"Aaaarg");
		System.out.println("XXXXXXXXXXXXXXXXX");
		myTest.printArray();

    }
}