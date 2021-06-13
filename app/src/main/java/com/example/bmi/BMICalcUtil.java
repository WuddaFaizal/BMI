package com.example.bmi;

public class BMICalcUtil {
    public static final BMICalcUtil instance = new BMICalcUtil();

    private static final int CENTIMETERS_IN_METER = 100;
    private static final int INCHES_IN_FOOT = 12;
    private static final int BMI_IMPERIAL_WEIGHT_SCALAR = 703;

    public static final String BMI_CATEGORY_UNDERWEIGHT = "Underweight, Malnutrition Risk";
    public static final String BMI_CATEGORY_HEALTHY = "Normal Weight, Low Risk";
    public static final String BMI_CATEGORY_OVERWEIGHT = "Overweight, Enhanced Risk";
    public static final String BMI_CATEGORY_MOBESE = "Moderately Obese, Medium Risk";
    public static final String BMI_CATEGORY_SOBESE = "Severely Obese, High Risk";
    public static final String BMI_CATEGORY_VSOBESE = "Very Severely Obese, Very High Risk";

    public static BMICalcUtil getInstance() {
        return instance;
    }

    public double calculateBMIMetric(double heightCm, double weightKg) {
        return (weightKg / ((heightCm / CENTIMETERS_IN_METER) * (heightCm / CENTIMETERS_IN_METER)));
    }


    public String classifyBMI(double bmi) {
        if (bmi < 18.4) {
            return BMI_CATEGORY_UNDERWEIGHT;
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return BMI_CATEGORY_HEALTHY;
        } else if (bmi >= 25 && bmi < 29.9){
            return BMI_CATEGORY_OVERWEIGHT;
        } else if (bmi >= 30 && bmi < 34.9){
            return BMI_CATEGORY_MOBESE;
        } else if (bmi >= 35 && bmi < 39.9){
            return BMI_CATEGORY_SOBESE;
        } else {
            return BMI_CATEGORY_VSOBESE;
        }
    }
}