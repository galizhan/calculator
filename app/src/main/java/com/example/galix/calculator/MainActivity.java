package com.example.galix.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn;
    TextView tv;
    int result;
    ArrayList<Integer> nums = new ArrayList<Integer>();
    ArrayList<Character> signs = new ArrayList<Character>();
    ArrayList<String> par = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn1);
        et = (EditText) findViewById(R.id.et);
        tv = (TextView) findViewById(R.id.txt);
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s;
                s = et.getText().toString();
                divide(s);
                calculate(nums,signs);
            }
        };

        btn.setOnClickListener(click);


    }

    public void divide(String s){
        String temp="";

        for (int i = 0; i < s.length(); i++) {
            char c;
            c = s.charAt(i);

            if (Character.isDigit(c)) {
                temp += c;
            } else {

                nums.add(Integer.parseInt(temp));
                signs.add(c);
                Log.d("nums", temp);
                Log.d("sings", "" + c);
                temp = "";

            }
            if (i == s.length() - 1) nums.add(Integer.parseInt(temp));
        }
    }
    public void calculate(ArrayList<Integer> arrnums, ArrayList<Character> arrsigns) {
        if (arrsigns.size() == 0) {
            nums = arrnums;
        } else {
            Character[] ch = {'/', '*',};
            int sum = 0;
            for (int i = 0; i < arrsigns.size(); i++) {
                if (arrsigns.get(i) == '/') {
                    sum = arrnums.get(i) / arrnums.get(i + 1);
                    arrnums.remove(i);
                    arrnums.remove(i);
                    arrnums.add(sum);
                    sum = 0;
                    arrsigns.add(arrsigns.get(i - 1));
                    arrsigns.remove(i);
                    arrsigns.remove(i - 1);
                    Log.d("signjaskdl", "" + arrnums);
                } if (arrsigns.get(i) == '*') {
                    sum = arrnums.get(i) * arrnums.get(i + 1);
                    arrnums.remove(i);
                    arrnums.remove(i);
                    arrnums.add(sum);
                    sum = 0;
                    arrsigns.add(arrsigns.get(i - 1));
                    arrsigns.remove(i);
                    arrsigns.remove(i - 1);
                    Log.d("signjaskdl", "" + arrnums);

                }
            }
            for (int j = 0; j < arrsigns.size(); j++) {
                switch (arrsigns.get(j)) {
                    case '+':
                        sum = arrnums.get(j) + arrnums.get(j + 1);
                        arrnums.remove(j);
                        arrnums.remove(j);
                        arrnums.add(sum);
                        sum = 0;
                        arrsigns.remove(j);
                        break;
                    case '-':
                        sum = arrnums.get(j) - arrnums.get(j + 1);
                        arrnums.remove(j);
                        arrnums.remove(j);
                        arrnums.add(sum);
                        sum = 0;
                        arrsigns.remove(j);
                        break;
                }
            }

            if(arrsigns.size()!=0){
                calculate(arrnums,arrsigns);

            }
            else {
                Log.d("numss",""+arrnums);
                result = arrnums.get(0);
                tv.setText(""+result);
                nums.clear();
                signs.clear();
            }

        }
    }
}
