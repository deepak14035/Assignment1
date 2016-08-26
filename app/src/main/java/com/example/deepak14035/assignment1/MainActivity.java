package com.example.deepak14035.assignment1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private int randomNumber=2, hintPressedFlag=0;
    private Random randomFunction;
    private TextView questionID;
    private String questionText;
    public static final String STORE_NUMBER="com.example.assignment1.number";
    public static final String STORE_HINT_PRESSED="com.example.assignment1.hint";
    public static final String STORE_CHEAT_PRESSED="com.example.assignment1.cheat";
    public static final String CHEATED_OR_NOT="cheat";
    public static final String CLASS_NAME="class";
    private int rotateFlag=0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String numberReturned=intent.getStringExtra(STORE_NUMBER);
        String isHintPressed=intent.getStringExtra(STORE_HINT_PRESSED);
        String isCheatPressed=intent.getStringExtra(STORE_CHEAT_PRESSED);
        String className=intent.getStringExtra(CLASS_NAME);
        String cheatedOrNot=intent.getStringExtra(CHEATED_OR_NOT);
        Log.d("create", "coming from "+className);
        Log.d("create", "cheat   "+isCheatPressed);
        Log.d("create", "hint   "+isHintPressed);
        Log.d("create", "number "+numberReturned);

        if(isHintPressed!=null){
            //if(isHintPressed.equals("yes")){
                Toast hintToast;
                hintToast=Toast.makeText(this, "you pressed the hint button!", Toast.LENGTH_SHORT);
                hintToast.show();
            //}
        }
        if(isCheatPressed!=null){
            //if(isHintPressed.equals("yes")){
            Toast hintToast;
            if(cheatedOrNot.equals("yes")) {
                hintToast = Toast.makeText(this, "you cheated!", Toast.LENGTH_SHORT);
            }else{
                hintToast = Toast.makeText(this, "you didn't cheat!", Toast.LENGTH_SHORT);
            }
            hintToast.show();
            //}
        }
        if(numberReturned!=null){
            randomNumber=Integer.parseInt(numberReturned);
            Log.d("create", "number is "+numberReturned);
        }


        else if(savedInstanceState != null)          //check for any previous values
            randomNumber=savedInstanceState.getInt(STORE_NUMBER);

        rotateFlag=1;

        setTitle("prime numbers");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.deepak14035.assignment1/http/host/path")
        );
        questionID = (TextView)findViewById(R.id.questionID);
        randomFunction=new Random();

        if(rotateFlag==1){      //if device is rotated, we load the previous number
            rotateFlag=0;
            Log.d("create", "starting");
        }else{
            randomNumber=randomFunction.nextInt(1000);  //generate random number
        }
        questionText="Is "+randomNumber+" a prime number?";
        questionID.setText(questionText);
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    public void hintButton(View v) {
        Intent hintIntent = new Intent(this, HintActivity.class);
        hintIntent.putExtra(STORE_NUMBER, randomNumber + "");

        startActivity(hintIntent);
    }

    public void cheatButton(View v) {
        Intent cheatIntent = new Intent(this, CheatActivity.class);
        cheatIntent.putExtra(STORE_NUMBER, randomNumber + "");

        startActivity(cheatIntent);
    }

    public void falseButton(View v) {
        checkAnswer(false);

    }
    public void trueButton(View v) {
        checkAnswer(true);

    }
    public void nextButton(View v) {        //generate a random number and put it in textView
        randomNumber=randomFunction.nextInt(1000);
        questionText = "Is " + randomNumber+" a prime number?";
        questionID.setText(questionText);

    }

    private void checkAnswer(boolean answer){
        int i;
        boolean flag=true;
        for(i=2;i<=randomNumber/2;i++){         //check if the random number is prime or not
            if(randomNumber%i==0){
                //System.out.println("Number is not prime");
                flag=false;
                break;
            }
        }
        boolean check;
        //check if the answer selected by user is correct
        if(answer==flag){
            check= true;
        }
        else{
            check=false;
        }
        Toast displayAnswer;
        String correct="correct answer", incorrect="incorrect answer";
        //diplay a toast
        if(check){
            displayAnswer=Toast.makeText(this, correct, Toast.LENGTH_SHORT);
        }
        else{
            displayAnswer=Toast.makeText(this, incorrect, Toast.LENGTH_SHORT);
        }
        displayAnswer.show();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //if device is rotated, we save the current random number to the ID storenumber
        savedInstanceState.putInt(STORE_NUMBER, randomNumber);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onStop() {
        super.onStop();
        //Log.d(this, "Stop called");
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.deepak14035.assignment1/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
