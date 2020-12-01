package l00162025.assign2;

public class IndexOutOfBounds extends Exception {
    public IndexOutOfBounds(Throwable cause) {
        super(cause);
        System.out.println("Invalid input. please try again");
    }

    public IndexOutOfBounds() {
        System.out.println("Invalid input. please try again and reduce the index");
    }
}
