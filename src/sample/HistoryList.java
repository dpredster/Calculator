package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HistoryList {

    // Reference to CalculatorList.
    private CalculatorList calc = CalculatorController.calc;

    // Save array list to file when called.
    public void CalculatorListSave() {
        PrintWriter writer = null;
        try {
            File fileDescriptor = new File("History.txt");
            writer = new PrintWriter(fileDescriptor);

            // Loop to save all objects in array list.
            for (int i = 0; i < calc.equationList.size(); i++) {
                Calculator list = calc.equationList.get(i);
                writer.println(list.getNumber1() + " " + list.getOperation() + " " + list.getNumber2());
            }
        }
        catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.ERROR, "File not found. Please try again.");
            alert.show();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    public void CalculatorListLoad() {
        // Don't load if the file is empty.
        File fileDescriptor = new File("History.txt");

        if (fileDescriptor.length() == 0) {
            return;
        }
        Scanner reader = null;
        try {
            reader = new Scanner(fileDescriptor);
            // Clear array list before loading/
            calc.equationList.clear();
            while (reader.hasNext()) {
                // Read the next line from the file
                String record = reader.nextLine();

                // Now split the line into separate fields
                String[] field = record.split(" ");

                double firstNumber = Double.parseDouble(field[0]);
                String operator = field[1];
                double secondNumber = Double.parseDouble(field[2]);

                // Create a player object and add it to the list
                Calculator equation = new Calculator(firstNumber, operator, secondNumber);
                calc.equationList.add(equation);
            }
        }
        catch (FileNotFoundException e) {
            Alert alert = new Alert(AlertType.ERROR, "File not found. Please try again.");
            alert.show();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    public void calculatorClear() {
        calc.equationList.clear();
    }
}