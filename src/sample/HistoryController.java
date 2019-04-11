package sample;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class HistoryController {
    @FXML
    private Button btnClear;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReturn;
    @FXML
    private ListView<Calculator> listArea;

    private CalculatorList calc = CalculatorController.calc;
    private HistoryList history = new HistoryList();

    @FXML
    private void initialize() {
        btnReturn.setOnAction(e -> onReturnClick());
        btnClear.setOnAction(e -> onClearClick());
        btnSave.setOnAction(e -> onSaveClick());
        // Set return button text for Calculator.
        btnReturn.setText(calc.getButtonTitle());

        // Display content of Calculator ArrayList
        for (int index = 0; index < calc.equationList.size(); index++) {
            listArea.getItems().add(calc.equationList.get(index));
        }
    }

    @FXML
    private void onReturnClick() {
        // Get a reference to the stage
        Stage stage = (Stage) btnReturn.getScene().getWindow();
        // Close the window
        stage.close();
    }

    private void onClearClick() {
        // Clear ArrayList.
        calc.equationList.clear();
        // Clear ListView.
        listArea.getItems().clear();
    }

    private void onSaveClick() {
        // Save history to file.
        history.CalculatorListSave();
    }

}
