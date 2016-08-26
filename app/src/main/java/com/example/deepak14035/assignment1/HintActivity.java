package com.example.deepak14035.assignment1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class HintActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    private int randomNumber;
    public static final String STORE_NUMBER="com.example.assignment1.number";
    public static final String STORE_HINT_PRESSED="com.example.assignment1.hint";
    public static final String CLASS_NAME="class";
    private TextView HintWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        Intent intent = getIntent();
        String str=intent.getStringExtra(STORE_NUMBER);
        randomNumber = Integer.parseInt(str);
        Log.d("create", "number is " + str);
        if(savedInstanceState != null)          //check for any previous values
        {
            randomNumber = savedInstanceState.getInt(STORE_NUMBER);
        }

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void backButton(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra(STORE_NUMBER, randomNumber + "");
        mainIntent.putExtra(STORE_HINT_PRESSED, "Yes");
        mainIntent.putExtra(CLASS_NAME, "HintActivity");
        startActivity(mainIntent);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Hint Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.deepak14035.assignment1/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Hint Page", // TODO: Define a title for the content shown.
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

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //if device is rotated, we save the current random number to the ID storenumber
        savedInstanceState.putInt(STORE_NUMBER, randomNumber);
        super.onSaveInstanceState(savedInstanceState);
    }

}
