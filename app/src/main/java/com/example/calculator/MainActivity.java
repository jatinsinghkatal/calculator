package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bcl, bplus, bminus, bmulti, bdiv, beql;
    EditText ET_Result;
    Float v1, v2;
    Boolean add, sub, multi, div;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons and EditText
        initializeViews();

        // Set OnClickListeners
        setButtonListeners();
    }

    private void initializeViews() {
        b1 = findViewById(R.id.btn_one);
        b2 = findViewById(R.id.btn_two);
        b3 = findViewById(R.id.btn_three);
        b4 = findViewById(R.id.btn_four);
        b5 = findViewById(R.id.btn_five);
        b6 = findViewById(R.id.btn_six);
        b7 = findViewById(R.id.btn_seven);
        b8 = findViewById(R.id.btn_eight);
        b9 = findViewById(R.id.btn_nine);
        b0 = findViewById(R.id.btn_zero);
        bdot = findViewById(R.id.btn_dot);
        bcl = findViewById(R.id.btn_clear);
        bplus = findViewById(R.id.btn_plus);
        bminus = findViewById(R.id.btn_minus);
        bmulti = findViewById(R.id.btn_multi);
        bdiv = findViewById(R.id.btn_divide);
        beql = findViewById(R.id.btn_eql);
        ET_Result = findViewById(R.id.ET_Result);
    }

    private void setButtonListeners() {
        // Set listeners for number buttons
        setNumberButtonListener(b1, "1");
        setNumberButtonListener(b2, "2");
        setNumberButtonListener(b3, "3");
        setNumberButtonListener(b4, "4");
        setNumberButtonListener(b5, "5");
        setNumberButtonListener(b6, "6");
        setNumberButtonListener(b7, "7");
        setNumberButtonListener(b8, "8");
        setNumberButtonListener(b9, "9");
        setNumberButtonListener(b0, "0");

        // Set listeners for operator buttons
        bdot.setOnClickListener(view -> ET_Result.setText(ET_Result.getText() + "."));
        bcl.setOnClickListener(view -> {
            ET_Result.setText("");
            v1 = null; v2 = null;
            add = sub = multi = div = false;
        });

        setOperatorButtonListener(bplus, "add");
        setOperatorButtonListener(bminus, "sub");
        setOperatorButtonListener(bmulti, "multi");
        setOperatorButtonListener(bdiv, "div");

        // Set listener for equals button
        beql.setOnClickListener(view -> {
            if (v1 != null && ET_Result.getText().length() > 0) {
                v2 = Float.parseFloat(ET_Result.getText().toString());
                if (add) {
                    ET_Result.setText(String.valueOf(v1 + v2));
                    add = false;
                } else if (sub) {
                    ET_Result.setText(String.valueOf(v1 - v2));
                    sub = false;
                } else if (multi) {
                    ET_Result.setText(String.valueOf(v1 * v2));
                    multi = false;
                } else if (div) {
                    if (v2 != 0) {
                        ET_Result.setText(String.valueOf(v1 / v2));
                    } else {
                        ET_Result.setText("Error");
                    }
                    div = false;
                }
            }
        });
    }

    // Helper method for setting number buttons
    private void setNumberButtonListener(Button button, String value) {
        button.setOnClickListener(view -> ET_Result.setText(ET_Result.getText().append(value)));
    }

    // Helper method for setting operator buttons
    private void setOperatorButtonListener(Button button, String operation) {
        button.setOnClickListener(view -> {
            if (ET_Result.getText().length() > 0) {
                v1 = Float.parseFloat(ET_Result.getText().toString());
                if (operation.equals("add")) add = true;
                if (operation.equals("sub")) sub = true;
                if (operation.equals("multi")) multi = true;
                if (operation.equals("div")) div = true;
                ET_Result.setText("");
            }
        });
    }
}
