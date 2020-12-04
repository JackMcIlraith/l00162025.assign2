package l00162025.assign2;

public class GenericCollections {
    private static IndexOutOfBounds NoSuchElementException;

    public static void main(String[] args) throws IndexOutOfBounds {
        GenericArrayList<Person> myList = new GenericArrayList<>();
        myList.add(new Person(20, "Hegarty", "2Dermot"));
        myList.add(new Person(23,"Minchin", "3Tim"));
        myList.add(new Person(19, "DiCamillo", "1Kate"));
        myList.add(new Person(28, "Hardinge", "6Frances"));
        myList.add(new Person(24, "Bryson", "4Bill"));
        myList.add(new Person(26, "Ness", "5Patrick"));
        printList("Original List:", myList);
        //This method uses compareTo() to do its job
        GenericCollections.sort(myList);
        printList("\nSorted List based on natural ordering (age):", myList);
       // GenericCollections.rotate(myList, 2);
       // printList("\nList rotated by two:", myList);
        System.out.println("\nThe oldest person is " + GenericCollections.max(myList));
    }
    public static void printList(String message, GenericArrayList<Person> listToPrint) throws IndexOutOfBounds {
        System.out.println(message);
        for (int i = 0; i < listToPrint.size(); i++)
        {
            Object temp = listToPrint.get(i);
            System.out.println(temp.toString());
        }
    }

    /**
     * Sort based on natural ordering (as defined by class's compareTo method)
     * @param listToSort
     */
    public static <T extends Comparable<T>> void sort(IList<T> listToSort) throws IndexOutOfBounds {
        {
            int test = listToSort.size();
            for (int i = 0; i < listToSort.size(); i++) //number of passes
            {
                //keeps track of positions per pass
                for (int j = 0; j < (listToSort.size() - 1 - i); j++)
                {
                    //if left value is greater than right value
                    if (listToSort.get(j).compareTo(listToSort.get(j+1)) > 0)
                    {
                        //swap values
                        T temp = listToSort.get(j);
                        listToSort.set(j,listToSort.get(j+1));
                        listToSort.set(j+1, temp);
                    }
                }
            }
        }
    }

    public static <T extends Comparable<T>> T max(IList<T> list) throws IndexOutOfBounds {
        IList<T> newList = new GenericArrayList<>();
        newList = list;
        if(list.isEmpty()){
            throw NoSuchElementException;
        }
        sort(newList);
        return newList.get(newList.size()-1);
    }

}
