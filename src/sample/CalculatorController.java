package sample;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalculatorController {
    @FXML
    private Button btnOperator;
    @FXML
    private Button btnNumber;
    @FXML
    private Button btnEqual;
    @FXML
    private Button btnPlusMinus;
    @FXML
    private Button btnPercent;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnDataTrans;
    @FXML
    private TextField displayTxt;
    @FXML
    private TextField displaySumTxt;

    static CalculatorList calc = new CalculatorList();
    private HistoryList history = new HistoryList();

    private String number1 = "";
    private String operator = "";
    private String number2 = "";
    private String equation = "";
    private boolean resultReturned = false;
    private String result = "";


    @FXML
    private void initialize() {
        btnEqual.setOnAction(e -> onEqualClicked());
        btnPlusMinus.setOnAction(e -> onPlusMinusClicked());
        btnPercent.setOnAction(e -> onPercentClicked());
        btnClear.setOnAction(e -> onClearClicked());
        btnDelete.setOnAction(e -> onDeleteClicked());
        btnDataTrans.setOnAction(e -> onDataTransClicked());
        // On startup load history from a file.
        history.CalculatorListLoad();
    }

    @FXML
    private void onNumberClicked(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button btnNum = (Button) event.getSource();
            // Clear screen after result shown if an number button is
            // clicked.

            if (resultReturned == true) {
                // Clear main text display.
                displayTxt.clear();
                // Reset number2 and result to empty string.
                number2 = "";
                result = "";
                // Reset boolean to false
                resultReturned = false;
            }
            if (displayTxt.getText().equals("" + calc.EquationCalc())) {
                displayTxt.clear();
                displayTxt.setText(displayTxt.getText() + btnNum.getText());
            }
            else {
                displayTxt.setText(displayTxt.getText() + btnNum.getText());
            }
            if (operator == "") {
                number1 = displayTxt.getText();
            }

            else {
                // Set number variable to number value clicked.
                number2 = displayTxt.getText();
            }
        }
    }

    @FXML
    private void onOperationClicked(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button btnOp = (Button) event.getSource();

            double num1;
            double num2;
            // Set symbol to math symbol clicked.
            operator = btnOp.getText();
            // Clear main text field.
            displayTxt.clear();
            if (btnOp.getText().equals("x²") || btnOp.getText().equals("√")) {
                // If operator is x² or √ and number 1 is empty string set
                // result to 0.
                if (number1 == "") {
                    result = "0";
                }
                else {
                    // If not pass number and operator to array list.
                    num1 = Double.parseDouble(number1);
                    calc.equationList.add(new Calculator(num1, operator));
                    result = "" + calc.EquationCalc();
                    equation = equation + number1 + " " + btnOp.getText();
                    // Display result in main test field.
                    displayTxt.setText("" + result);
                    number1 = result;

                    // Set result returned to boolean true.
                    resultReturned = true;
                }
            }

            else if (number2 != "") {
                num1 = Double.parseDouble(number1);
                num2 = Double.parseDouble(number2);
                calc.equationList.add(new Calculator(num1, operator, num2));
                result = "" + calc.EquationCalc();

                displayTxt.setText("" + result);
                number1 = result;
                equation = equation + " " + number2 + " " + btnOp.getText();

                // Set result returned to boolean true.
                resultReturned = true;

            }
            else {
                equation = number1 + " " + operator;
            }
            displaySumTxt.setText(equation);
        }

    }

    private void onEqualClicked() {
        // If equal is clicked without entering a two numbers and an operator or
        // if an operator is entered with no numbers display 0 in text field.
        if (number1 == "" || operator == "" || number2 == "") {
            result = "0";
            operator = "";
        }
        else {
            // Pass numbers and operator to Equation().
            calc.Equation(number1, operator, number2);
            result = "" + calc.EquationCalc();
            displayTxt.setText("" + result);
            number1 = result;

            displaySumTxt.setText(equation + " " + number2);

            // Set result returned to boolean true.
            resultReturned = true;
        }
    }


    @FXML
    private void onPlusMinusClicked() {
        // On +- click check if number2 empty string.
        if (number2 == "") {
            // If so check if number1 is minus.
            if (number1.contains("-")) {
                // If true remove minus sign.
                number1 = number1.replaceFirst("-", "");
                displayTxt.setText(number1);
            }
            else {
                // If false add minus sign.
                number1 = "-" + number1;
                displayTxt.setText(number1);
            }
        }
        // On +- click check if number2 is minus.
        else {
            if (number2.contains("-")) {
                // If true remove minus sign.
                number2 = number2.replaceFirst("-", "");
                displayTxt.setText(number2);
            }
            else {
                // If false add minus sign.
                number2 = "-" + number2;
                displayTxt.setText(number2);
            }
        }

    }

    private void onPercentClicked() {
        // Appends the number with the % symbol.
        if (number2 != "") {
            number2 = number2 + "%";
            displayTxt.setText(number2);
        }
    }

    // When CLR is clicked.
    private void onClearClicked() {
        // Clear main text display and sub display.
        displayTxt.clear();
        displaySumTxt.clear();
        // Reset field variables to empty string.
        equation = "";
        operator = "";
        number1 = "";
        number2 = "";
        result = "";
    }

    private void onDeleteClicked() {
        // Check if displayTxt String length is greater than 0 and reduce by 1
        // when delete is clicked.
        if (displayTxt.getText().length() > 0) {
            displayTxt.setText(displayTxt.getText(0, displayTxt.getText().length() - 1));

        }
    }

    private void onDataTransClicked() {
        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Transformation.fxml"));
            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
            Stage secondStage = new Stage();
            // Sets the stage icon
            secondStage.getIcons().add(new Image(getClass().getResourceAsStream("ico/calculator.png")));
            // Set the stage title
            secondStage.setTitle("Calculator - Data Transformation");
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    @FXML
    // On click call History Controller.
    protected void onHistoryClicked(ActionEvent event) throws IOException {
        // Pass return button text to HistoryController.
        calc.setButtonTitle("Return to Calculator");

        try {
            AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("History.fxml"));
            Scene scene = new Scene(root, 500, 600);
            scene.getStylesheets().add(getClass().getResource("Calculator.css").toExternalForm());
            Stage secondStage = new Stage();
            // Sets the stage icon
            secondStage.getIcons().add(new Image(getClass().getResourceAsStream("ico/calculator.png")));
            // Set the stage title
            secondStage.setTitle("Calculator - History");
            secondStage.setScene(scene);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.showAndWait();
        }
        catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

}
