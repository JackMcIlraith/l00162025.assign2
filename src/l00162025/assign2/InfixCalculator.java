package l00162025.assign2;

import java.util.Arrays;
import java.util.StringTokenizer;

public class InfixCalculator {

    /**
     * Core function to evaluate user input string.
     * does not work great if there are no ) brackets or if the number of operators exceed the number of ).
     * Will attempt to address time permitting.
     * @param expression user input to be evaluated using infix/postfix conversion
     * @return postfix result of user input in double form. not extra formatting provided
     * @throws IndexOutOfBounds
     */
    public static double evaluate(String expression) throws IndexOutOfBounds {
        GenericStack<String> operators = new GenericStack<String>();
        GenericStack<Double> operands = new GenericStack<Double>();
        String[] cleanExpression; // String array to hold parsed input
        //parse user input to remove spaces
        expression = parser(expression);
        //System.out.println(expression); //quick test
        cleanExpression = breakdown(expression);
        //begin to load and unload stacks:
        for (int i = 0; i < cleanExpression.length; i++) {
            switch (cleanExpression[i]) {

                case ")": //this is the magic symbol that executes the pops!
                    double rightPart = (double) operands.pop(); //need to have right pop first, else it wont work for subtraction or div eg (12 - 34 instead of 34 -12)
                    double leftPart =  (double) operands.pop();
                    String operationToDo = (String) operators.pop();
                    double result = 0;
                    switch (operationToDo) {
                        case "+":
                            result = rightPart + leftPart;
                            break;
                        case "-":
                            result = leftPart - rightPart;
                            break;
                        case "*":
                            result = rightPart * leftPart;
                            break;
                        case "/":
                            result = leftPart / rightPart;
                            break;
                        case "^":
                            result = Math.pow(leftPart, rightPart);
                            break;
                        case "%":
                            result = leftPart % rightPart;
                            break;
                        default:
                            break;
                    }
                    operands.push(result);
                    break;

                case "+":
                case "*":
                case "-":
                case "/":
                case "%":
                case "^":
                    operators.push(cleanExpression[i]);
                    break;
                case "(":
                case "":
                case " ":
                    break;
                default:
                    operands.push(Double.parseDouble(cleanExpression[i]));
                    break;
            }

        }
        return (double)operands.pop();
    }

    /**
     * Sanitation of user input.
     * will remove all spaces and re-insert them around opperators as delimiters, then remove double spaces
     * eg: ((2   +8)-    7)
     * ->((2+8)-7)
     * -> (  ( 2 + 8 )  - 7 )
     * -> ( ( 2 + 8 ) - 7 )
     * @param exprssion is the user input or expression to be evaluated
     * @return parsed and formatted expression
     */
    private static String parser(String exprssion) {
        exprssion = exprssion.replaceAll(" ", "");
        exprssion = exprssion.replaceAll("\\+", " + ");
        exprssion = exprssion.replaceAll("\\-", " - ");
        exprssion = exprssion.replaceAll("\\*", " * ");
        exprssion = exprssion.replaceAll("\\%", " % ");
        exprssion = exprssion.replaceAll("\\/", " / ");
        exprssion = exprssion.replaceAll("\\)", " ) ");
        exprssion = exprssion.replaceAll("\\(", " ( ");
        exprssion = exprssion.replaceAll("\\^", " ^ ");
        exprssion = exprssion.replaceAll("  ", " ");
        exprssion = exprssion.replaceAll("   ", " ");
        return exprssion;
    }

    /**
     * Break String into String array using " " s a delimiter - ensure String has been parsed by parser() before hand
     * @param expression user input, but should be sanitized forst using parser()
     * @return
     */
    private static String[] breakdown(String expression) {
        String[] expressionArray = new String[expression.length()];
        expressionArray = expression.split(" ");
        return expressionArray;
    }

}
