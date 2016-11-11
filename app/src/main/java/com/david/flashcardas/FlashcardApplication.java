package com.david.flashcardas;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import static com.david.flashcardas.MyDBHandler.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FlashcardApplication extends Application {

    private ArrayList<Flashcard> allFlashcards;
    private ArrayList<String> allSubjects;
    ArrayList<String> randomSubject = new ArrayList<>();
    ArrayList<String> randomSideAorB = new ArrayList<>();
  //  private ArrayList<Flashcard> defaultDB = new ArrayList<>();
    private SQLiteDatabase flashcardDB;
    private Random randomGenerator;

    @Override
    public void onCreate(){
        super.onCreate();

        MyDBHandler databaseHandler = new MyDBHandler(this);
        flashcardDB = databaseHandler.getWritableDatabase();
        readFlashcardsFromDB();
    }

    private ArrayList<String> readFlashCardSubjectsFromDB(){
        allSubjects = new ArrayList<>();

        for(Flashcard flashcard : allFlashcards){
            if(allSubjects.contains(flashcard)){
                //do nothing
            } else {
                allSubjects.add(flashcard.getSubject());
            }
       }

        Collections.sort(allSubjects);
        allSubjects.add(0, "Create New Subject");
        return allSubjects;
    }

    private void readFlashcardsFromDB() {
        allFlashcards = new ArrayList<>();

        Cursor flashcardsCursor;

        flashcardsCursor = flashcardDB.query(FLASHCARD_TABLE,
                new String[]{ RECORD_ID, COLUMN_SUBJECT, SIDE_A, SIDE_B },
                null, null, null, null, COLUMN_SUBJECT);

        flashcardsCursor.moveToFirst();
        Flashcard tempFlashCard;

        if(!flashcardsCursor.isAfterLast()){
            do{
                long id = flashcardsCursor.getLong(0);
                String subject = flashcardsCursor.getString(1);
                String sideA = flashcardsCursor.getString(2);
                String sideB = flashcardsCursor.getString(3);

                tempFlashCard = new Flashcard(id, subject, sideA, sideB);

                allFlashcards.add(tempFlashCard);

            }while (flashcardsCursor.moveToNext());

        }
        flashcardsCursor.close();

        if (allFlashcards.isEmpty()){
            addDefaultDatabase();
        }
        //Collections.sort(allFlashcards);
    }

    public void addFlashcard(Flashcard flashcard){
        assert flashcard != null;

        ContentValues cv = new ContentValues();
        // cv.put(MyDBHandler.RECORD_ID, flashcard.getCardNumber());
        cv.put(MyDBHandler.COLUMN_SUBJECT, flashcard.getSubject());
        cv.put(MyDBHandler.SIDE_A, flashcard.getSideA());
        cv.put(MyDBHandler.SIDE_B, flashcard.getSideB());

        long idpassedback = flashcardDB.insert(MyDBHandler.FLASHCARD_TABLE, null, cv);
        flashcard.setCardNumber(idpassedback);

        Log.d("Flashcard Database", "After adding a flashcard" + flashcard);

        allFlashcards.add(flashcard);

    }

    public void addDefaultDatabase(){
        assert flashcardDB != null;

        for(int i = 0; i < 200; i++) {
            Flashcard defaultFlashcard = new Flashcard(getRandomSubject(), getRandomSideAorB(), getRandomSideAorB());

            ContentValues cv = new ContentValues();
            cv.put(MyDBHandler.COLUMN_SUBJECT, defaultFlashcard.getSubject());
            cv.put(MyDBHandler.SIDE_A, defaultFlashcard.getSideA());
            cv.put(MyDBHandler.SIDE_B, defaultFlashcard.getSideB());

            long idpassedback = flashcardDB.insert(MyDBHandler.FLASHCARD_TABLE, null, cv);
            defaultFlashcard.setCardNumber(idpassedback);
        }
    }

    private String getRandomSideAorB() {
        String result;
        randomSideAorB.add("Android OS");
        randomSideAorB.add("iOS");
        randomSideAorB.add("Blackberry");
        randomSideAorB.add("RIM");
        randomSideAorB.add("Apple Watch");
        randomSideAorB.add("Silicon Valley");
        randomSideAorB.add("Inheritance");
        randomSideAorB.add("Interface");
        randomSideAorB.add("compareTo");
        randomSideAorB.add("boolean");
        randomSideAorB.add("equals method");
        randomSideAorB.add("ListView");
        randomSideAorB.add("Android Dialogs");
        randomSideAorB.add("C.R.U.D");
        randomGenerator = new Random();

        int index = randomGenerator.nextInt(randomSideAorB.size());
        result = randomSideAorB.get(index);
        return result;
    }

    private String getRandomSubject(){
        String result;
        randomSubject.add("Java");
        randomSubject.add("Database");
        randomSubject.add("Mobile OS");
        randomSubject.add(".Net");
        randomSubject.add("Object Oriented");
        randomSubject.add("C#");
        randomSubject.add("Networking");
        randomSubject.add("Software Dev");
        randomSubject.add("Hacking");
        randomSubject.add("Project Management");
        randomGenerator = new Random();

        int index = randomGenerator.nextInt(randomSubject.size());
        result = randomSubject.get(index);

        return result;
    }

    public ArrayList<Flashcard> singleSubjectFlashcards(String subject){

        ArrayList<Flashcard> singleSubject = new ArrayList<>();

        for(Flashcard flashcard : allFlashcards){
            if(subject.equals(flashcard.getSubject())){
                singleSubject.add(flashcard);
            }
        }
        return singleSubject;

    }

    public ArrayList<Flashcard> getAllFlashcards() {
        if(allFlashcards.size() == 1){
            addDefaultDatabase();
        }
        return allFlashcards;
    }

    public ArrayList<String> getAllSubjects() {
        return readFlashCardSubjectsFromDB();
    }

    private void setAllFlashcards(ArrayList<Flashcard> allFlashcards) {
        this.allFlashcards = allFlashcards;
    }


}
