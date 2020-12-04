package l00162025.assign2;

public class GenericCollections {
    private static IndexOutOfBounds NoSuchElementException;


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

    public static <T extends Comparable<T>> void rotate(IList<T> list, int distance) throws IndexOutOfBounds {
        distance = (distance % list.size()); //no need to over iterate through the list to rotate, eg: size - 6, distance = 8 is th same as distance =2, or 8%6
        if(distance == 0){
            return;
        }
        else if(distance > 0){
            for(int i =0; i < distance; i++){
                list.add(list.get(0));
                list.remove(0);
            }
            return;
        }
        else if (distance < 0){
            distance*=-1;
            for(int i =0; i < distance; i++) {
                list.add(list.get(list.size() - 1));
                for (int j = list.size() - 1; j > 0; j--) {
                    list.set(j, list.get(j - 1));
                }
                list.set(0, list.get(list.size() - 1));
                list.remove(list.size() - 1);
            }
            return;
        }
    }






}
