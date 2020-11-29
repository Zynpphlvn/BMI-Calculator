package com.example.a150116504hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a150116504hw1.R;

public class ResultActivity extends AppCompatActivity {

    TextView text_bmi, text_result;
    Button btn_calculate_again;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        text_bmi = findViewById(R.id.text_bmi);
        text_result = findViewById(R.id.text_interpretation);
        btn_calculate_again = findViewById(R.id.button_calculate_again);

        double bmi = getIntent().getDoubleExtra("bmi_result", 5);

        if (bmi < 18.5) {
            result = "underweight";
        } else if (bmi < 25) {
            result = "normal";
        } else if (bmi < 30) {
            result = "overweight";
        } else if (bmi >= 30) {
            result = "obese";
        }

        text_bmi.setText("Your BMI is " + bmi);
        text_result.setText("This is " + result + ".");

        btn_calculate_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}