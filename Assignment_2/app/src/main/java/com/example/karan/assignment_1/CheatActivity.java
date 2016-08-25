package com.example.karan.assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by KARAN on 24-08-2016.
 */
public class CheatActivity extends AppCompatActivity {

    private TextView mCheat;
    private Bundle extras;
    private int isShown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Button mShowCheat;

        mShowCheat = (Button) findViewById(R.id.showCheatButton);
        mCheat = (TextView) findViewById(R.id.textCheat);

        extras = getIntent().getExtras();

        if(savedInstanceState != null) {
            if(savedInstanceState.getInt("isShown") == 1) {
                String primeText = "Answer: " + extras.getInt("Number") + " is a Prime Number!";
                String notPrimeText = "Answer: " + extras.getInt("Number") + " is not a Prime Number!";
                if (extras.getInt("IsPrime") == 1)
                    mCheat.setText(primeText);
                else
                    mCheat.setText(notPrimeText);
                isShown = 1;
            }
            else {
                isShown = 0;
                mCheat.setText("");
            }
        }
        else {
            isShown = 0;
            mCheat.setText("");
        }

        mShowCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String primeText = "Answer: " + extras.getInt("Number") + " is a Prime Number!";
                String notPrimeText = "Answer: " + extras.getInt("Number") + " is not a Prime Number!";
                if (extras.getInt("IsPrime") == 1)
                    mCheat.setText(primeText);
                else
                    mCheat.setText(notPrimeText);
                isShown = 1;
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        if(isShown == 1)
            intent.putExtra("result","Cheat was taken!");
        else
            intent.putExtra("result","Cheat was not taken!");
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("isShown", isShown);
    }
}
