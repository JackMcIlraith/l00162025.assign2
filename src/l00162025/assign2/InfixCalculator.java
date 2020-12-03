package l00162025.assign2;

import java.util.Arrays;
import java.util.StringTokenizer;

public class InfixCalculator {


    public static double evaluate(String expression) throws IndexOutOfBounds {
        GenericStack<String> operators = new GenericStack<String>();
        GenericStack<Double> operands = new GenericStack<Double>();
        String[] cleanExpression;
        //parse user input to remove spaces
        expression = parser(expression);
        //System.out.println(expression); //quick test
        cleanExpression = breakdown(expression);
        //begin to load stacks:
        for (int i = 0; i < cleanExpression.length; i++) {
            switch (cleanExpression[i]) {

                case ")":
                    double rightPart = (double) operands.pop(); //need to have right pop first, else it wont work for subtraction or div
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
                        default:
                            break;
                    }
                    operands.push(result);
                    break;

                case "+":
                case "*":
                case "-":
                case "/":
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

    private static String parser(String exprssion) {
        exprssion = exprssion.replaceAll(" ", "");
        exprssion = exprssion.replaceAll("\\+", " + ");
        exprssion = exprssion.replaceAll("\\-", " - ");
        exprssion = exprssion.replaceAll("\\*", " * ");
        exprssion = exprssion.replaceAll("\\/", " / ");
        exprssion = exprssion.replaceAll("\\)", " ) ");
        exprssion = exprssion.replaceAll("\\(", " ( ");
        exprssion = exprssion.replaceAll("  ", " ");
        exprssion = exprssion.replaceAll("   ", " ");
        return exprssion;
    }

    private static String[] breakdown(String expression) {
        String[] expressionArray = new String[expression.length()];
        expressionArray = expression.split(" ");
        return expressionArray;
    }

}
