package l00162025.assign2;

import java.util.Scanner;

public class InfixTester {

    public InfixTester() throws IndexOutOfBounds {
        this.tester();
    }

    private void tester() throws IndexOutOfBounds {
        Scanner userExpression = new Scanner(System.in);  // Create a Scanner object to hold the expression
        System.out.println("Enter expression to evaluate");
        String expression = userExpression.nextLine();  // Read user input
        //System.out.println("User expression: " + expression);
        System.out.println("Result: " + InfixCalculator.evaluate(expression));

    }
}
