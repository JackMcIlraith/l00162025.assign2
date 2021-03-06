package l00162025.assign2.Tests;

import l00162025.assign2.GenericArrayList;
import l00162025.assign2.GenericCollections;
import l00162025.assign2.IndexOutOfBounds;
import l00162025.assign2.Person;

public class GenericCollectionsTest {

    public static void main(String[] args) throws Throwable {
        GenericArrayList<Person> myList = new GenericArrayList<>();
        myList.add(new Person(20, "Hegarty", "Dermot"));
        myList.add(new Person(23,"Minchin", "Tim"));
        myList.add(new Person(19, "DiCamillo", "Kate"));
        myList.add(new Person(28, "Hardinge", "Frances"));
        myList.add(new Person(24, "Bryson", "Bill"));
        myList.add(new Person(26, "Ness", "Patrick"));
        GenericCollections.printList("Original List:", myList);
        //This method uses compareTo() to do its job
        GenericCollections.sort(myList);
        GenericCollections.printList("\nSorted List based on natural ordering (age):", myList);
        GenericCollections.rotate(myList, 2);
        GenericCollections.printList("\nList rotated left by two(2) (as per Dermot's spec):", myList);
        GenericCollections.rotate2(myList, -2);
        GenericCollections.printList("\nList rotated right using IList rotate (-2) to correct:", myList);
        GenericCollections.rotate2(myList, 4);
        GenericCollections.printList("\nList rotated left using IList rotate (4) to test:", myList);
        System.out.println("\nThe oldest person is " + GenericCollections.max(myList));
    }
}
