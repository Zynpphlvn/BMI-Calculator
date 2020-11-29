package com.example.a150116504hw1;

import  androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a150116504hw1.R;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText edit_height, edit_weight;
    Button btn_calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_height = findViewById(R.id.edit_height);
        edit_weight = findViewById(R.id.edit_weight);
        btn_calculate = findViewById(R.id.button_calculate);

        btn_calculate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(!isValidHeight(edit_height)){
                    Context context = getApplicationContext();
                    CharSequence text = "Value should be double!";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else if (ControlInputValidity(edit_weight) && ControlInputValidity(edit_height)) {
                    double bmi = calculateBmi(DoubleToInt(edit_height), DoubleToInt(edit_weight));
                    openResultActivity(bmi);
                } else {
                    Context context = getApplicationContext();
                    CharSequence text = "Value should be double";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }
        });
    }

    private double DoubleToInt(EditText editText) {
        String input = editText.getText().toString().replace(',', '.');
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private boolean ControlInputValidity(EditText editText) {
        return DoubleToInt(editText) > 0;
    }

    private boolean isValidHeight(EditText editText){
        return editText.getText().toString().contains(".");
    }

    private double calculateBmi(double height, double weight){
        double bmi = weight / (height * height);
        return Double.parseDouble(new DecimalFormat("##.#").format(bmi));
    }

    private void openResultActivity(double bmi){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("bmi_result", bmi);
        startActivity(intent);
    }

}