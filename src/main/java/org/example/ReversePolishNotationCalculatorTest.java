package org.example;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateWithManyBlank() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        Assertions.assertEquals(1, calculator.calculatePolishNotation("0  1  +"));
    }

    @Test
    public void shouldCalculateWithNegativeDigits() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        Assertions.assertEquals(0, calculator.calculatePolishNotation("-1 -1 -"));
    }

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        Assertions.assertEquals(5, calculator.calculatePolishNotation("3 2 +"));
    }

    @Test
    public void shouldCalculateSubtraction() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        Assertions.assertEquals(1, calculator.calculatePolishNotation("3 2 -"));
    }

    @Test
    public void shouldCalculateMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        Assertions.assertEquals(6, calculator.calculatePolishNotation("3 2 *"));
    }
}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}
