package com.example.karan.assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView mRandNum;
    private final int[] prime = new  int[1001];
    private int randomNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mCorrectButton;
        Button mIncorrectButton;
        Button mNextButton;
        Button mHintButton;
        Button mCheatButton;

        mCorrectButton = (Button) findViewById(R.id.correctButton);
        mIncorrectButton = (Button) findViewById(R.id.incorrectButton);
        mNextButton = (Button) findViewById(R.id.nextButton);
        mHintButton = (Button) findViewById(R.id.hintButton);
        mCheatButton = (Button) findViewById(R.id.cheatButton);
        mRandNum = (TextView) findViewById(R.id.randNum);

        prime[1] = 0;                                                                               // Sieve of Eratosthenes
        int i, j;
        for(i = 2; i <= 1000; i++)
            prime[i] = 1;
        i = 2;
        while(i * i <= 1000)  {
            if(prime[i] == 1) {
                j = 2 * i;
                while(j <= 1000) {
                    prime[j] = 0;
                    j = j + i;
                }
            }
            i++;
        }

        if(savedInstanceState != null) {
            mRandNum.setText(savedInstanceState.getCharSequence("savedRandNum"));
            randomNumber = Integer.parseInt(mRandNum.getText().toString());
        }
        else {
            Random r = new Random();                                                                // Generate Random Numbers
            randomNumber = r.nextInt(1000) + 1;
            String randNumString = Integer.toString(randomNumber);
            mRandNum.setText(randNumString);
        }

        mCorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prime[randomNumber] == 1)
                    Toast.makeText(getApplicationContext(), "Your answer is right!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Your answer is wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        mIncorrectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(prime[randomNumber] == 1)
                    Toast.makeText(getApplicationContext(), "Your answer is wrong!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getApplicationContext(), "Your answer is right!", Toast.LENGTH_SHORT).show();
            }
        });

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                randomNumber = r.nextInt(1000) + 1;
                String randNumString = Integer.toString(randomNumber);
                mRandNum.setText(randNumString);
            }
        });

        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CheatActivity.class);
                intent.putExtra("Number", randomNumber);
                intent.putExtra("IsPrime", prime[randomNumber]);
                startActivityForResult(intent, 1);
            }
        });

        mHintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), HintActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result=data.getStringExtra("result");
                Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("savedRandNum", mRandNum.getText());
    }
}