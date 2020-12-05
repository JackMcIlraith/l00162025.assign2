package l00162025.assign2.Tests;

import l00162025.assign2.InfixCalculator;
import java.util.Scanner;

public class InfixCalculatorTester {

    public static void main(String[] args) throws Throwable {
        Scanner userExpression = new Scanner(System.in);  // Create a Scanner object to hold the expression
        System.out.println("Enter expression to evaluate");
        String expression = userExpression.nextLine();  // Read user input
        System.out.println("User expression: " + expression);
        System.out.println("Result: " + InfixCalculator.evaluate(expression));

    }
}
