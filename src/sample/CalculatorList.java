package sample;
import java.util.ArrayList;

public class CalculatorList {

    private String _buttonTitle;

    public String getButtonTitle() {
        return _buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this._buttonTitle = buttonTitle;
    }

    // Create array list of Calculator.
    ArrayList<Calculator> equationList = new ArrayList<Calculator>();

    public void Equation(String number1, String operator, String number2) {
        double num2;
        double num1 = Double.parseDouble(number1);

        // If number2 contains % and operator is x divide by 100 and assign
        // result to num2.
        if (number2.contains("%") && operator.contains("×")) {
            // Remove % sign.
            number2 = number2.replaceFirst("%", "");
            num2 = Double.parseDouble(number2) / 100;
        }
        // If number2 contains % and any other operator is passed divide
        // number2 by 100 and multiply by number1. Assign result to num2
        else if (number2.contains("%")) {
            // Remove % sign.
            number2 = number2.replaceFirst("%", "");
            num2 = Double.parseDouble(number2) / 100 * Double.parseDouble(number1);
        }
        else {
            num2 = Double.parseDouble(number2);
        }

        equationList.add(new Calculator(num1, operator, num2));
    }


    public double EquationCalc() {
        // Switch statement to calculate numbers and return result.
        double total = 0.0;

        for (int i = 0; i < equationList.size(); i++) {
            Calculator calc = equationList.get(i);
            double number1 = calc.getNumber1();
            String operator = calc.getOperation();
            double number2 = calc.getNumber2();

            switch (operator) {
            case "+":
                total = number1 + number2;
                break;
            case "-":
                total = number1 - number2;
                break;
            case "×":
                total = number1 * number2;
                break;
            case "÷":
                total = number1 / number2;
                break;
            case "x²":
                total = Math.pow(number1, 2);
                break;
            case "√":
                total = Math.sqrt(number1);
                break;
            }
        }
        return total;
    }

}