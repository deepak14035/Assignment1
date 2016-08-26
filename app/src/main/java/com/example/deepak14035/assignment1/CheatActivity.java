package com.example.deepak14035.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String STORE_NUMBER="com.example.assignment1.number";
    public static final String STORE_CHEAT_PRESSED="com.example.assignment1.cheat";
    public static final String CLASS_NAME="class";
    public static final String CHEATED_OR_NOT="cheat";

    private int randomNumber;
    private TextView cheatAnswer;
    private int isCheated=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        Intent intent = getIntent();
        String str=intent.getStringExtra(STORE_NUMBER);
        randomNumber = Integer.parseInt(str);
        Log.d("create", "number is " + str);
        if(savedInstanceState != null)          //check for any previous values
        {
            randomNumber = savedInstanceState.getInt(STORE_NUMBER);
        }
        cheatAnswer = (TextView)findViewById(R.id.cheatTextID);

        if(savedInstanceState != null) {          //check for any previous values
            randomNumber = savedInstanceState.getInt(STORE_NUMBER);
            isCheated=savedInstanceState.getInt(CHEATED_OR_NOT);
            if(isCheated==1){
                checkPrime();
            }
        }

    }

    public void cheatNo(View v) {
        if(isCheated!=1){
            isCheated=0;
        }
    }

    public void cheatYes(View v) {
        checkPrime();
    }

    public void checkPrime(){
        int i;
        boolean flag=true;
        for(i=2;i<=randomNumber/2;i++){         //check if the random number is prime or not
            if(randomNumber%i==0){
                //System.out.println("Number is not prime");
                flag=false;
                break;
            }
        }
        String displayAnswer;
        if(flag){
            displayAnswer=" "+randomNumber+"is a prime number";
        }
        else{
            displayAnswer=" "+randomNumber+"is not a prime number";
        }
        cheatAnswer.setText(displayAnswer);
        isCheated=1;
    }

    public void backButton(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(STORE_NUMBER, randomNumber + "");
        mainIntent.putExtra(STORE_CHEAT_PRESSED, "Yes");
        mainIntent.putExtra(CLASS_NAME, "CheatActivity");
        if(isCheated==1){
            mainIntent.putExtra(CHEATED_OR_NOT, "yes");
        }
        else{
            mainIntent.putExtra(CHEATED_OR_NOT, "no");
        }

        startActivity(mainIntent);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        //if device is rotated, we save the current random number to the ID storenumber
        savedInstanceState.putInt(STORE_NUMBER, randomNumber);
        savedInstanceState.putInt(CHEATED_OR_NOT, isCheated);
        super.onSaveInstanceState(savedInstanceState);
    }
}
