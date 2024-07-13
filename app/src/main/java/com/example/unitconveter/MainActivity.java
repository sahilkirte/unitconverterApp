package com.example.unitconveter;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;
    private TextView textViewResult;
    private Button buttonConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextInput = findViewById(R.id.editTextInput);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        textViewResult = findViewById(R.id.textViewResult);
        buttonConvert = findViewById(R.id.buttonConvert);

        // Define unit options
        String[] units = {"Meters", "Kilometers", "Centimeters", "Inches", "Feet"};

        // Set up Spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);


        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertUnits();
            }
        });
    }

    private void convertUnits() {
        String inputString = editTextInput.getText().toString();
        if (inputString.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }

        double inputValue = Double.parseDouble(inputString);
        String fromUnit = spinnerFromUnit.getSelectedItem().toString();
        String toUnit = spinnerToUnit.getSelectedItem().toString();

        double result = convert(inputValue, fromUnit, toUnit);
        textViewResult.setText("Result: " + result);
    }

    private double convert(double value, String fromUnit, String toUnit) {
        // Conversion logic here
        double baseValue = 0;

        // Convert from the input unit to a base unit (e.g., meters)
        switch (fromUnit) {
            case "Meters":
                baseValue = value;
                break;
            case "Kilometers":
                baseValue = value * 1000;
                break;
            case "Centimeters":
                baseValue = value / 100;
                break;
            case "Inches":
                baseValue = value * 0.0254;
                break;
            case "Feet":
                baseValue = value * 0.3048;
                break;
        }

        // Convert from the base unit to the output unit
        switch (toUnit) {
            case "Meters":
                return baseValue;
            case "Kilometers":
                return baseValue / 1000;
            case "Centimeters":
                return baseValue * 100;
            case "Inches":
                return baseValue / 0.0254;
            case "Feet":
                return baseValue / 0.3048;
        }

        return 0;
    }
}
