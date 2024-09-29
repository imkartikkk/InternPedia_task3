package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    double firstNum = 0;
    String operations = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Buttons for numbers
        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);

        // Buttons for operators
        Button plus = findViewById(R.id.plus);
        Button on = findViewById(R.id.on);
        Button off = findViewById(R.id.off);
        Button ac = findViewById(R.id.ac);
        Button del = findViewById(R.id.del);
        Button div = findViewById(R.id.div);
        Button times = findViewById(R.id.times);
        Button min = findViewById(R.id.min);
        Button equal = findViewById(R.id.equal);
        Button point = findViewById(R.id.point);

        TextView screen = findViewById(R.id.screen);

        // Reset the calculator
        ac.setOnClickListener(view -> {
            firstNum = 0;
            operations = "";
            screen.setText("0");
        });

        // Hide or show screen
        off.setOnClickListener(view -> screen.setVisibility(View.GONE));
        on.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");
        });

        // Number buttons functionality
        ArrayList<Button> nums = new ArrayList<>();
        nums.add(num0);
        nums.add(num1);
        nums.add(num2);
        nums.add(num3);
        nums.add(num4);
        nums.add(num5);
        nums.add(num6);
        nums.add(num7);
        nums.add(num8);
        nums.add(num9);
        for (Button b : nums) {
            b.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")) {
                    screen.setText(screen.getText().toString() + b.getText().toString());
                } else {
                    screen.setText(b.getText().toString());
                }
            });
        }

        // Operator buttons functionality
        ArrayList<Button> opers = new ArrayList<>();
        opers.add(div);
        opers.add(times);
        opers.add(plus);
        opers.add(min);
        for (Button b : opers) {
            b.setOnClickListener(view -> {
                double secondNum = Double.parseDouble(screen.getText().toString());
                if (!operations.isEmpty()) {
                    switch (operations) {
                        case "/":
                            firstNum = firstNum / secondNum;
                            break;
                        case "X":
                            firstNum = firstNum * secondNum;
                            break;
                        case "+":
                            firstNum = firstNum + secondNum;
                            break;
                        case "-":
                            firstNum = firstNum - secondNum;
                            break;
                    }
                } else {
                    firstNum = secondNum;
                }
                operations = b.getText().toString(); // Update the operator
                screen.setText("0"); // Clear the screen for the next input
            });
        }

        // Delete button
        del.setOnClickListener(view -> {
            String num = screen.getText().toString();
            if (num.length() > 1) {
                screen.setText(num.substring(0, num.length() - 1));
            } else if (num.length() == 1 && !num.equals("0")) {
                screen.setText("0");
            }
        });

        // Point button for decimals
        point.setOnClickListener(view -> {
            if (!screen.getText().toString().contains(".")) {
                screen.setText(screen.getText().toString() + ".");
            }
        });

        // Equal button for final result
        equal.setOnClickListener(view -> {
            double secondNum = Double.parseDouble(screen.getText().toString());
            double result;
            switch (operations) {
                case "/":
                    result = firstNum / secondNum;
                    break;
                case "X":
                    result = firstNum * secondNum;
                    break;
                case "+":
                    result = firstNum + secondNum;
                    break;
                case "-":
                    result = firstNum - secondNum;
                    break;
                default:
                    result = firstNum;
            }
            screen.setText(String.valueOf(result));
            firstNum = result;
            operations = ""; // Clear the operator after calculation
        });
    }
}
