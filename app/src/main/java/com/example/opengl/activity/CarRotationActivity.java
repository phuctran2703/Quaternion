package com.example.opengl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opengl.R;

public class CarRotationActivity extends AppCompatActivity {
    private EditText editTextAxisX, editTextAxisY, editTextAxisZ, editTextRotationAngle;
    private Button buttonSubmitRotation, buttonReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotate_car);

        // Map the views from XML
        editTextAxisX = findViewById(R.id.editTextAxisX);
        editTextAxisY = findViewById(R.id.editTextAxisY);
        editTextAxisZ = findViewById(R.id.editTextAxisZ);
        editTextRotationAngle = findViewById(R.id.editTextRotationAngle);
        buttonSubmitRotation = findViewById(R.id.buttonSubmitRotation);
        buttonReturn = findViewById(R.id.buttonReturnRotation);

        // Set up button click listener for Submit
        buttonSubmitRotation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String axisXStr = editTextAxisX.getText().toString();
                String axisYStr = editTextAxisY.getText().toString();
                String axisZStr = editTextAxisZ.getText().toString();
                String rotationAngleStr = editTextRotationAngle.getText().toString();

                if (axisXStr.isEmpty() || axisYStr.isEmpty() || axisZStr.isEmpty() || rotationAngleStr.isEmpty()) {
                    Toast.makeText(CarRotationActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                float axisX, axisY, axisZ, rotationAngle;
                try {
                    axisX = Float.parseFloat(axisXStr);
                    axisY = Float.parseFloat(axisYStr);
                    axisZ = Float.parseFloat(axisZStr);
                    rotationAngle = Float.parseFloat(rotationAngleStr);
                } catch (NumberFormatException e) {
                    Toast.makeText(CarRotationActivity.this, "Invalid input format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Proceed to the next activity
                Intent intent = new Intent(CarRotationActivity.this, CarRotationResultActivity.class);
                intent.putExtra("axisX", axisX);
                intent.putExtra("axisY", axisY);
                intent.putExtra("axisZ", axisZ);
                intent.putExtra("rotationAngle", rotationAngle);
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
        outState.putString("axisX", editTextAxisX.getText().toString());
        outState.putString("axisY", editTextAxisY.getText().toString());
        outState.putString("axisZ", editTextAxisZ.getText().toString());
        outState.putString("rotationAngle", editTextRotationAngle.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore state from Bundle
        editTextAxisX.setText(savedInstanceState.getString("axisX"));
        editTextAxisY.setText(savedInstanceState.getString("axisY"));
        editTextAxisZ.setText(savedInstanceState.getString("axisZ"));
        editTextRotationAngle.setText(savedInstanceState.getString("rotationAngle"));
    }
}
