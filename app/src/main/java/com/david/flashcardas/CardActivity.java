package com.david.flashcardas;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class CardActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle2;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;
    private Random randomGenerator;
    protected String subject;
    protected String flashcardText;
    private Flashcard flashcard;
    private TextView flashcardSideA; //= (TextView) findViewById(R.id.flashcardSideA);
    private ArrayList<Flashcard> flashcardDeck;


    protected void addDrawerItems() {
        String[] osArray = {"Change Subject", "Stats", "Add New", "Advanced Options"};
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        mDrawerList = (ListView) findViewById(R.id.navList_card);
        addDrawerItems();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CardActivity.this, "Non-Op", Toast.LENGTH_SHORT).show();
            }
        });

        FlashcardApplication application = (FlashcardApplication) getApplication();

        subject = getIntent().getStringExtra(SubjectActivity.SUBJECT_NAME);
        //TextView cardSubject = (TextView) findViewById(R.id.cardSubject);
        //cardSubject.setText(subject);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_card);
        mActivityTitle = getTitle().toString();
        setupDrawer();

        getSupportActionBar().setTitle(subject);

        //Query DB for deck comprised of single subject
        flashcardDeck = application.singleSubjectFlashcards(subject);
        //return random Flashcard based on subject selected
        flashcard = pickACard(flashcardDeck);

        flashcardSideA = (TextView) findViewById(R.id.flashcardSideA);
       // final TextView
        flashcardSideA.setText(flashcard.getSideA());



    }

    private Flashcard pickACard(ArrayList<Flashcard> flashcardDeck) {
        Flashcard result;
        randomGenerator = new Random();
        int index = randomGenerator.nextInt(flashcardDeck.size());
        result = flashcardDeck.get(index);
        return result;
    }

    protected void setupDrawer() {
        mDrawerToggle2 = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //TODO Set window title when drawer opens
                getSupportActionBar().setTitle("Navigation");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle2.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (mDrawerToggle2.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void closeCardActivity(View view) {
        finish();
    }

    public void showOtherSide(View view) {

        if(flashcardSideA.getText().equals(flashcard.getSideA())){
            flashcardSideA.setText(flashcard.getSideB());
        } else {
            flashcardSideA.setText(flashcard.getSideA());
        }
    }

    public void nextRandomCardSameSubject(View view) {
        flashcard = pickACard(flashcardDeck);
        flashcardSideA.setText(flashcard.getSideA());
    }

    //TODO reconfig button layout

    //TODO LATER VERSION if number of cards is one, addt'l buttons to add cards or merge deck
}