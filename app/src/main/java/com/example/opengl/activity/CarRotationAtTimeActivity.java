package com.example.opengl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opengl.R;

public class CarRotationAtTimeActivity extends AppCompatActivity {
    private EditText editTextAxisW1, editTextAxisX1, editTextAxisY1, editTextAxisZ1, editTextAxisW2, editTextAxisX2, editTextAxisY2, editTextAxisZ2;
    private Button buttonSubmitRotation, buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotate_car_at_time);

        // Map the views from XML
        editTextAxisW1 = findViewById(R.id.editTextAxisW1);
        editTextAxisX1 = findViewById(R.id.editTextAxisX1);
        editTextAxisY1 = findViewById(R.id.editTextAxisY1);
        editTextAxisZ1 = findViewById(R.id.editTextAxisZ1);
        editTextAxisW2 = findViewById(R.id.editTextAxisW2);
        editTextAxisX2 = findViewById(R.id.editTextAxisX2);
        editTextAxisY2 = findViewById(R.id.editTextAxisY2);
        editTextAxisZ2 = findViewById(R.id.editTextAxisZ2);
        buttonSubmitRotation = findViewById(R.id.buttonSubmitRotation);
        buttonReturn = findViewById(R.id.buttonReturnRotation);

        // Set up button click listener for Submit
        buttonSubmitRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String axisW1Str = editTextAxisW1.getText().toString();
                String axisX1Str = editTextAxisX1.getText().toString();
                String axisY1Str = editTextAxisY1.getText().toString();
                String axisZ1Str = editTextAxisZ1.getText().toString();
                String axisW2Str = editTextAxisW2.getText().toString();
                String axisX2Str = editTextAxisX2.getText().toString();
                String axisY2Str = editTextAxisY2.getText().toString();
                String axisZ2Str = editTextAxisZ2.getText().toString();

                float axisW1, axisX1, axisY1, axisZ1, axisW2, axisX2, axisY2, axisZ2;
                try {
                    axisW1 = Float.parseFloat(axisW1Str);
                    axisX1 = Float.parseFloat(axisX1Str);
                    axisY1 = Float.parseFloat(axisY1Str);
                    axisZ1 = Float.parseFloat(axisZ1Str);
                    axisW2 = Float.parseFloat(axisW2Str);
                    axisX2 = Float.parseFloat(axisX2Str);
                    axisY2 = Float.parseFloat(axisY2Str);
                    axisZ2 = Float.parseFloat(axisZ2Str);
                } catch (NumberFormatException e) {
                    Toast.makeText(CarRotationAtTimeActivity.this, "Invalid input format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Proceed to the next activity
                Intent intent = new Intent(CarRotationAtTimeActivity.this, CarRotationAtTimeResultActivity.class);
                intent.putExtra("axisW1", axisW1);
                intent.putExtra("axisX1", axisX1);
                intent.putExtra("axisY1", axisY1);
                intent.putExtra("axisZ1", axisZ1);
                intent.putExtra("axisW2", axisW2);
                intent.putExtra("axisX2", axisX2);
                intent.putExtra("axisY2", axisY2);
                intent.putExtra("axisZ2", axisZ2);
                startActivity(intent);
            }
        });

        // Set up button click listener for Return
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save state in Bundle
        outState.putString("axisW", editTextAxisW1.getText().toString());
        outState.putString("axisX1", editTextAxisX1.getText().toString());
        outState.putString("axisY1", editTextAxisY1.getText().toString());
        outState.putString("axisZ1", editTextAxisZ1.getText().toString());
        outState.putString("axisW2", editTextAxisW2.getText().toString());
        outState.putString("axisX2", editTextAxisX2.getText().toString());
        outState.putString("axisY2", editTextAxisY2.getText().toString());
        outState.putString("axisZ2", editTextAxisZ2.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state from Bundle
        editTextAxisW1.setText(savedInstanceState.getString("axisW1"));
        editTextAxisX1.setText(savedInstanceState.getString("axisX1"));
        editTextAxisY1.setText(savedInstanceState.getString("axisY1"));
        editTextAxisZ1.setText(savedInstanceState.getString("axisZ1"));
        editTextAxisW2.setText(savedInstanceState.getString("axisW2"));
        editTextAxisX2.setText(savedInstanceState.getString("axisX2"));
        editTextAxisY2.setText(savedInstanceState.getString("axisY2"));
        editTextAxisZ2.setText(savedInstanceState.getString("axisZ2"));
    }
}
