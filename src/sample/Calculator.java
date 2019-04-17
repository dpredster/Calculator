package sample;

public class Calculator {

    private double _number1; // First number.
    private String _operation; // Equation operation.
    private double _number2; // Second number.

    public Calculator(double number1, String operation, double number2) {
        _number1 = number1;
        _operation = operation;
        _number2 = number2;
    }

    public Calculator(double number1, String operation) {
        _number1 = number1;
        _operation = operation;
    }

    public double getNumber1() {
        return _number1;
    }

    public String getOperation() {
        return _operation;
    }

    public double getNumber2() {
        return _number2;
    }

    public String toString() {

        if (_operation.equals("x²") || _operation.equals("√")) {
            return _operation + " " + _number1;
        }
        else {
            return _number1 + " " + _operation + " " + _number2;
        }
    }
}