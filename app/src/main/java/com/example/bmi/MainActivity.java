package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_HEALTHY;
import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_MOBESE;
import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_OVERWEIGHT;
import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_SOBESE;
import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_UNDERWEIGHT;
import static com.example.bmi.BMICalcUtil.BMI_CATEGORY_VSOBESE;


public class MainActivity extends AppCompatActivity {
        private EditText weightKgEditText, heightCmEditText;
        private Button calculateButton;
        private TextView bmiTextView, categoryTextView;
        private CardView bmiResultCardView;

        private boolean inMetricUnits;
        SharedPreferences sharedPref;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            weightKgEditText = findViewById(R.id.activity_main_weightkgs);
            heightCmEditText = findViewById(R.id.activity_main_heightcm);



            calculateButton = findViewById(R.id.activity_main_calculate);

            bmiTextView = findViewById(R.id.activity_main_bmi);
            categoryTextView = findViewById(R.id.activity_main_category);
            bmiResultCardView = findViewById(R.id.activity_main_resultcard);

            sharedPref = this.getSharedPreferences("height", Context.MODE_PRIVATE);
            sharedPref = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
            float hg = sharedPref.getFloat("height", 0);
            float wg = sharedPref.getFloat("weight", 0);
            heightCmEditText.setText((String.valueOf(hg)));
            weightKgEditText.setText((String.valueOf(wg)));

            inMetricUnits = true;
            updateInputsVisibility();
            bmiResultCardView.setVisibility(View.GONE);

            calculateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try{
                                float heightInCms = Float.parseFloat(heightCmEditText.getText().toString());
                                float weightInKgs = Float.parseFloat(weightKgEditText.getText().toString());
                                    SharedPreferences.Editor editor = sharedPref.edit();
                                    editor.putFloat("height", heightInCms);
                                    editor.putFloat("weight", weightInKgs);
                                    editor.apply();
                                double bmi = BMICalcUtil.getInstance().calculateBMIMetric(heightInCms, weightInKgs);
                                displayBMI(bmi);

                    } catch (Exception exception) {
                        Toast.makeText(MainActivity.this, "Please enter your height or weight", Toast.LENGTH_SHORT).show();                 }

                }
            });


        }

        private void updateInputsVisibility() {
            if (inMetricUnits) {
                heightCmEditText.setVisibility(View.VISIBLE);
                weightKgEditText.setVisibility(View.VISIBLE);
            } else {
                heightCmEditText.setVisibility(View.GONE);
                weightKgEditText.setVisibility(View.GONE);
            }
        }

        private void displayBMI(double bmi) {
            bmiResultCardView.setVisibility(View.VISIBLE);

            bmiTextView.setText(String.format("%.2f", bmi));

            String bmiCategory = BMICalcUtil.getInstance().classifyBMI(bmi);
            categoryTextView.setText(bmiCategory);

            switch (bmiCategory) {
                case BMI_CATEGORY_UNDERWEIGHT:
                    bmiResultCardView.setCardBackgroundColor(Color.YELLOW);
                    break;
                case BMI_CATEGORY_HEALTHY:
                    bmiResultCardView.setCardBackgroundColor(Color.GREEN);
                    break;
                case BMI_CATEGORY_OVERWEIGHT:
                    bmiResultCardView.setCardBackgroundColor(Color.YELLOW);
                    break;
                case BMI_CATEGORY_MOBESE:
                    bmiResultCardView.setCardBackgroundColor(Color.RED);
                    break;
                case BMI_CATEGORY_SOBESE:
                    bmiResultCardView.setCardBackgroundColor(Color.RED);
                    break;
                case BMI_CATEGORY_VSOBESE:
                    bmiResultCardView.setCardBackgroundColor(Color.RED);
                    break;
            }
        }
    }

