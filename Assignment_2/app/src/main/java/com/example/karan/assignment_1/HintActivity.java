package com.example.karan.assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HintActivity extends AppCompatActivity {

    private TextView mHint;
    private int isShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Button mShowHint;

        mShowHint = (Button) findViewById(R.id.showHintButton);
        mHint = (TextView) findViewById(R.id.textHint);

        if(savedInstanceState != null) {
            if(savedInstanceState.getInt("isShown") == 1) {
                String primeDef = "Hint: A prime number is a natural number greater than 1 that has no positive divisor other than 1";
                mHint.setText(primeDef);
                isShown = 1;
            }
            else {
                mHint.setText("");
                isShown = 0;
            }
        }
        else {
            isShown = 0;
            mHint.setText("");
        }

        mShowHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primeDef = "Hint: A prime number is a natural number greater than 1 that has no positive divisor other than 1";
                mHint.setText(primeDef);
                isShown = 1;
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if(isShown == 1)
            intent.putExtra("result","Hint was taken!");
        else
            intent.putExtra("result","Hint was not taken!");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("isShown", isShown);
    }
}