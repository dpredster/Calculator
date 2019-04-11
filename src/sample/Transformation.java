package sample;


public class Transformation {
    private double num; //value witch been entered by user for calculations


    /**
     * Constructor
     */

    public Transformation(double num) {
        this.num = num;
    }


    /**
     * Calculate Celsius to Fahrenheit using basic formula with possibility calculate up to 16 decimal places
     */
    public double Celsius_to_Fahrenheit(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num * 1.8 + 32) * 10) / 10))));
    }

    /**
     * Calculate Fahrenheit to Celsius using basic formula with possibility calculate up to 16 decimal places
     */
    public double Fahrenheit_to_Celsius(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num - 32) / 1.8) * 10) / 10)));
    }

    /**
     * Calculate Feet to Meters using basic formula with possibility calculate up to 16 decimal places
     */
    public double Feet_to_Meters(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num * 0.3048) * 10) / 10))));
    }

    /**
     * Calculate Meters to Feet using basic formula with possibility calculate up to 16 decimal places
     */
    public double Meters_to_Feet(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num / 0.3048) * 10) / 10))));
    }

    /**
     * Calculate Inches to Centimeters using basic formula with possibility calculate up to 16 decimal places
     */
    public double Inches_to_Centimeters(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num * 2.54) * 10) / 10))));
    }

    /**
     * Calculate Centimeters to Inches using basic formula with possibility calculate up to 16 decimal places
     */
    public double Centimeters_to_Inches(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num / 2.54) * 10) / 10))));
    }

    /**
     * Calculate Pounds to Kilograms using basic formula with possibility calculate up to 16 decimal places
     */
    public double Pounds_to_Kilograms(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num / 2.205) * 10) / 10))));
    }

    /**
     * Calculate Kilograms to Pounds using basic formula with possibility calculate up to 16 decimal places
     */
    public double Kilograms_to_Pounds(double num, int quantity) {
        String digits = "%." + String.valueOf(quantity) + "f";
        return Double.parseDouble(String.format(digits, ((((num * 2.2046) * 10) / 10))));
    }


}
