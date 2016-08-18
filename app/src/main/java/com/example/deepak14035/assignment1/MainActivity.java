package com.example.deepak14035.assignment1;

import android.content.Context;
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
    private int randomNumber=2;
    private Random randomFunction;
    private TextView questionID;
    private String questionText;
    private String storeNumber="-1";
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
        if(savedInstanceState != null)          //check for any previous values
            randomNumber=savedInstanceState.getInt(storeNumber);
        if(Integer.parseInt(storeNumber)==-1){
            rotateFlag=1;
        }
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



    public void falseButton(View v) {
        checkAnswer(false);

    }
    public void trueButton(View v) {
        checkAnswer(true);

    }
    public void nextButton(View v) {        //generate a random number and put it in textView
        randomNumber=randomFunction.nextInt(1000);
        questionText = "Is "+randomNumber+" a prime number?";
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
        savedInstanceState.putInt(storeNumber, randomNumber);
        super.onSaveInstanceState(savedInstanceState);
    }
    @Override
    public void onStop() {
        super.onStop();

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
