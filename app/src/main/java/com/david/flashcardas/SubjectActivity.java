package com.david.flashcardas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class SubjectActivity extends ListActivity {

    protected ArrayList<String> subjectList;
    public static final String SUBJECT_NAME = "subject";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subjects_layout);

        FlashcardApplication application = (FlashcardApplication) getApplication();

        subjectList = application.getAllSubjects();

        Log.d("SubjectList", subjectList.toString());

        ArrayAdapter<String> subjectArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, subjectList);
        ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(subjectArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String subject = subjectList.get(position);
                displayDetail(subject);
            }
        });
    }

    private void displayDetail(String subject) {
        Log.d("SubjectActivity", "Displaying subject " + subject);

        if(subject.equals("Create New Subject")) {
            Intent createIntent = new Intent(this, CreateFlashcards.class);
            startActivity(createIntent);
            finish();

        } else {
            Intent intent = new Intent(this, CardActivity.class);
            intent.putExtra(SUBJECT_NAME, subject);
            startActivity(intent);
            finish();
        }
    }
}

//TODO redo design
