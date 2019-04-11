package sample;
import java.util.ArrayList;

public class CalculatorList {

    private String _buttonTitle; // Main window to second window

    public String getButtonTitle() {
        return _buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this._buttonTitle = buttonTitle;
    }

    ArrayList<Calculator> equationList = new ArrayList<Calculator>();

    public void Equation(String equat) {
        double num1;
        String oper;
        double num2;

        // Split string array and pass result to Calculator arrayList
        String[] calcArr = equat.split(" ");

        // If second character is square root or square pass number and operator
        // to ArrayList.
        if (calcArr[1].equals("x²") || calcArr[1].equals("√")) {
            String firstNumber = calcArr[0];
            num1 = Double.parseDouble(firstNumber);
            oper = calcArr[1];
            equationList.add(new Calculator(num1, oper));
        }

        else {
            String firstNumber = calcArr[0];
            num1 = Double.parseDouble(firstNumber);
            oper = calcArr[1];
            // If third character is % remove % and divide second number by 100
            // then pass to ArrayList.
            if (calcArr[2].contains("%")) {
                calcArr[2] = calcArr[2].replaceFirst("%", "");
                String SecondNumber = calcArr[2];
                num2 = Double.parseDouble(SecondNumber) / 100;
            }
            else {
                // If third character is a number only pass equation to
                // ArrayList.
                String SecondNumber = calcArr[2];
                num2 = Double.parseDouble(SecondNumber);
            }
            equationList.add(new Calculator(num1, oper, num2));
        }

    }

    public double EquList() {
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