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

    private String sum = "0";
    private String operator = "";
    private String equation = "";
    private boolean resultReturned = false;


    @FXML
    private void initialize() {
        btnPlusMinus.setOnAction(e -> onPlusMinusClicked());
        btnPercent.setOnAction(e -> onPercentClicked());
        btnClear.setOnAction(e -> onClearClicked());
        btnDelete.setOnAction(e -> onDeleteClicked());
        btnDataTrans.setOnAction(e -> onDataTransClicked());
        // Load history from a file.
        history.CalculatorListLoad();
    }

    @FXML
    private void onOperationClicked(ActionEvent event) {
        String result;
        if (event.getSource() instanceof Button) {
            Button btnOp = (Button) event.getSource();
            operator = btnOp.getText();

            // Clear large text from display after number is entered.
            displayTxt.clear();

            if (btnOp.getText().equals("x²") || btnOp.getText().equals("√")) {
                displaySumTxt.setText(btnOp.getText() + " " + sum);
                equation = equation.trim() + sum.trim() + " " + btnOp.getText();
                calc.Equation(equation);
                displayTxt.setText("" + calc.EquList());
                // Set to true if result is shown.
                resultReturned = true;
            }
            else if (btnOp.getText().equals("=")) {
                displaySumTxt.setText(equation + " " + sum);
                equation = equation.trim() + " " + sum.trim();
                calc.Equation(equation);
                displayTxt.setText("" + calc.EquList());
                // Set to true if result is shown.
                resultReturned = true;
            }
            else {
                // Displays the equation on the above the main text field.
                displaySumTxt.setText(equation + " " + sum + " " + btnOp.getText());
            }

            // Continues calculation with returned answer.
            result = equation + " " + sum;
            String[] equatArr = result.trim().split(" ");
            if (equatArr.length > 1) {
                displayTxt.setText(("" + calc.EquList()).trim());

                // Remove =, x² or √ sign if present.
                if (btnOp.getText().equals("=") || btnOp.getText().equals("x²") || btnOp.getText().equals("√")) {
                    equation = "" + calc.EquList();
                }
                else {
                    equation = "" + calc.EquList() + " " + btnOp.getText();
                }
                sum = ""; // Sets sum to zero.
                resultReturned = false;
            }
            else {
                equation = equation + sum + " " + btnOp.getText();
            }
        }
    }


    @FXML
    private void onNumberClicked(ActionEvent event) {
        if (event.getSource() instanceof Button) {
            Button btnTxt = (Button) event.getSource();
            // Clear screen after result shown if an number button is
            // clicked.
            if (resultReturned == true) {
                displayTxt.clear();
                equation = "";
                resultReturned = false;
            }
            if (displayTxt.getText().equals("" + calc.EquList())) {
                displayTxt.clear();
                displayTxt.setText(displayTxt.getText() + btnTxt.getText());
            }
            else {
                displayTxt.setText(displayTxt.getText() + btnTxt.getText());
            }
            // Save displayed number to sum.
            sum = displayTxt.getText();
        }
    }


    @FXML
    private void onPlusMinusClicked() {
        // On +- click check if number is minus.
        if (sum.contains("-")) {
            // If true remove minus sign.
            sum = sum.replaceFirst("-", "");
        }
        else {
            // If false add minus sign.
            sum = "-" + sum;
        }
        displayTxt.setText(sum);
    }

    private void onPercentClicked() {
        // Appends the number with the % symbol.
        sum = sum + "%";
        displayTxt.setText(sum);
    }

    // When CLR is clicked.
    private void onClearClicked() {
        // Clear main text display.
        displayTxt.clear();
        // Clean sub text display.
        displaySumTxt.clear();
        // Reset equation to empty string.
        equation = "";
        // Reset operator to empty string.
        operator = "";
        // Reset sum to zero.
        sum = "0";
    }

    private void onDeleteClicked() {
        if (displayTxt.getText().length() > 0) {
            displayTxt.setText(displayTxt.getText(0, displayTxt.getText().length() - 1));
            sum = displayTxt.getText();
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
        // Set the extra data to pass to the second controller
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
