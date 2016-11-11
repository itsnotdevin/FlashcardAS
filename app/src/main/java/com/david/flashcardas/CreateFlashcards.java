package com.david.flashcardas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//TODO This class should be a dialog and not an activity!

public class CreateFlashcards extends AppCompatActivity {

    private Button addButton;
    private Button cancelButton;
    private EditText subjectText;
    private EditText sideAText;
    private EditText sideBText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flashcards);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();
    }

    public void addFlashcardClickHandler(View view) {
        String userInput;
        userInput = subjectText.getText().toString();
        String subject = userInput;
        userInput = sideAText.getText().toString();
        String sideA = userInput;
        userInput = sideBText.getText().toString();
        String sideB = userInput;

        Log.d("CreateFlashcards", "I hear the ADD button!");

        if( isValid(subject) && isValid(sideA) && isValid(sideB)){

            Flashcard flashcard = new Flashcard(subject, sideA, sideB);
            FlashcardApplication app = (FlashcardApplication) getApplication();

            try {
                app.addFlashcard(flashcard);
            } catch (NullPointerException npe) {
                //npe.printStackTrace();
                Toast.makeText(CreateFlashcards.this, "This feature is not yet enabled", Toast.LENGTH_SHORT).show();
                //TODO only let valid flashcards into database!
            }

            Log.d("CreateFlashcards", "Flashcard added");

            //TODO clear input boxes

        } else {
            //TODO cancel/try again buttons
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Invalid Flashcard")
                    .setMessage("error")
                    .setCancelable(false)
                    .setPositiveButton("ok",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
        }

    }
    private boolean isValid(String userInputText){
        //TODO what's valid? Spelling, English, numbers, decimals
        boolean result = true;
        //should anything be outlawed?
        if(userInputText.equals("")){
            result = false;
        }
        return result;
    }

    private void setupViews(){
        addButton = (Button) findViewById(R.id.addButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);
        subjectText = (EditText) findViewById(R.id.subjectText);
        sideAText = (EditText) findViewById(R.id.sideAText);
        sideBText = (EditText) findViewById(R.id.sideBText);
    }
    //TODO Check validity Progress bar?

    public void closeActivity(View view){
        finish();
    }
}
