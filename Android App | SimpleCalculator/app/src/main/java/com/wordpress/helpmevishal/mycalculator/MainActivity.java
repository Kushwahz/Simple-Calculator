package com.wordpress.helpmevishal.mycalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9;
    Button bDecimal, bEquals, bPlus, bMinus, bMultiply, bDivide, bCE, bFactorial, bPower;
    ImageButton bBack;
    EditText window;
    double operand_1 = 0,operand_2 = 0, result=1;
    Character operation ='0';
    Boolean clear = false, equalClear= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        window = (EditText) findViewById(R.id.editWindow);
        window.setEnabled(false);

        b0 = (Button) findViewById(R.id.b_0);
        b0.setOnClickListener(this);
        b1 = (Button) findViewById(R.id.b_1);
        b1.setOnClickListener(this);
        b2 = (Button) findViewById(R.id.b_2);
        b2.setOnClickListener(this);
        b3 = (Button) findViewById(R.id.b_3);
        b3.setOnClickListener(this);
        b4 = (Button) findViewById(R.id.b_4);
        b4.setOnClickListener(this);
        b5 = (Button) findViewById(R.id.b_5);
        b5.setOnClickListener(this);
        b6 = (Button) findViewById(R.id.b_6);
        b6.setOnClickListener(this);
        b7 = (Button) findViewById(R.id.b_7);
        b7.setOnClickListener(this);
        b8 = (Button) findViewById(R.id.b_8);
        b8.setOnClickListener(this);
        b9 = (Button) findViewById(R.id.b_9);
        b9.setOnClickListener(this);

        bDecimal= (Button) findViewById(R.id.b_decimal);
        bDecimal.setOnClickListener(this);
        bEquals = (Button) findViewById(R.id.b_equals);
        bEquals.setOnClickListener(this);
        bPlus = (Button) findViewById(R.id.b_plus);
        bPlus.setOnClickListener(this);
        bMinus = (Button) findViewById(R.id.b_minus);
        bMinus.setOnClickListener(this);
        bMultiply = (Button) findViewById(R.id.b_multiply);
        bMultiply.setOnClickListener(this);
        bDivide = (Button) findViewById(R.id.b_divide);
        bDivide.setOnClickListener(this);
        bFactorial = (Button) findViewById(R.id.b_factorial);
        bFactorial.setOnClickListener(this);
        bPower = (Button) findViewById(R.id.b_power);
        bPower.setOnClickListener(this);
        bBack = (ImageButton) findViewById(R.id.b_back);
        bBack.setOnClickListener(this);
        bCE = (Button) findViewById(R.id.b_CE);
        bCE.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.b_0:
                if(window.getText().toString().equals("0")){
                    window.setText("");
                } else onSetValues(0,false);
                break;
            case R.id.b_1: onSetValues(1,false);
                break;
            case R.id.b_2: onSetValues(2,false);
                break;
            case R.id.b_3: onSetValues(3,false);
                break;
            case R.id.b_4: onSetValues(4,false);
                break;
            case R.id.b_5: onSetValues(5,false);
                break;
            case R.id.b_6: onSetValues(6,false);
                break;
            case R.id.b_7: onSetValues(7,false);
                break;
            case R.id.b_8: onSetValues(8,false);
                break;
            case R.id.b_9: onSetValues(9,false);
                break;

            case R.id.b_plus: onOperate('+',true);
                Toast.makeText(this, "Enter second operand...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_minus: onOperate('-',true);
                Toast.makeText(this, "Enter second operand...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_multiply: onOperate('*',true);
                Toast.makeText(this, "Enter second operand...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_divide: onOperate('/',true);
                Toast.makeText(this, "Enter second operand...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_equals:
                if(!window.getText().toString().equals("")){
                    if(window.getText().toString().length()<=15){
                        try{
                            onCalculate(operation);
                        } catch (Exception e){
                            Toast.makeText(this, "Result out of Bound...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        window.setText("");
                        Toast.makeText(this, "Input out of Bound...", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.b_factorial:
                if(!window.getText().toString().equals("")){
                    clear = true;
                    equalClear = true;
                    operand_1 = Double.valueOf(window.getText().toString());
                    int factorial = 1;
                    for(int i = 1 ; i <= operand_1; i++){
                        factorial = factorial*i;
                    }
                    onSetText(window, factorial);

                }
                break;
            case R.id.b_power: onOperate('^',true);
                Toast.makeText(this, "Raised to power...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.b_decimal:
                if(!window.getText().toString().contains("."))
                    window.setText(window.getText().toString()+".");
                break;
            case R.id.b_back:
                if(equalClear !=true){
                    int index = window.getText().length();
                    if(index != 0){
                        String editedInput = window.getText().toString().substring(0,index-1);
                        window.setText(editedInput);
                    }
                }
                else {
                    window.setText("");
                    equalClear = false;
                }
                break;
            case R.id.b_CE: onResetValues(false);
                window.setText("");
                break;
        }
    }

    double onCalculate(Character operator){
        operand_2 = Double.valueOf(window.getText().toString());
        switch(operator){
            case '+': onSetText(window,(operand_1+operand_2));
                break;
            case '-': onSetText(window,(operand_1-operand_2));
                break;
            case '*': onSetText(window,(operand_1*operand_2));
                break;
            case '/': if(window.getText().toString().equals("0")){
                clear = true;
                Toast.makeText(getApplicationContext(),"Invalid Operation", Toast.LENGTH_SHORT).show();
                }
                else{
                    onSetText(window,(operand_1/operand_2));
                }
                break;
            case '!':                 break;
            case '^': double result = operand_1;
                for (int i = 1; i < operand_2;i++){
                    result = operand_1 * result;
                }
                onSetText(window,result);
                break;
            case '=':
                window.setText("");
                break;
        }
        onResetValues(true);
        equalClear = true;
        return Double.parseDouble(window.getText().toString());
    }

    void onClearWindow(){
        if(clear) window.setText("");
        else return;
    }

    void onResetValues(Boolean reset){
        operand_1 = 0;
        operand_2 = 0;
        operation ='0';
        clear = reset;
    }

    void onSetValues(Integer input, Boolean reset){
        onClearWindow();
        clear = reset;
        window.setText(window.getText().toString()+ input);
    }

    void onOperate(Character Operation, Boolean reset){
        if(!window.getText().toString().equals("")) {
            clear = reset;
            operation = Operation;
            operand_1 = Double.valueOf(window.getText().toString());
        }
    }

    void onSetText(EditText editWindow, double result){
        String[] Number = String.valueOf(result).split("\\.");
        if (Number[1].equals("0")){
            editWindow.setText(Number[0]);
        }
        else editWindow.setText(String.valueOf(result));
    }
}
