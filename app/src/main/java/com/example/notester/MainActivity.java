package com.example.notester;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList<String> notesTitles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = DatabaseHelper.getInstance(this);

        ListView listView = findViewById(R.id.listView);

        notesTitles = loadNotesTitles();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notesTitles);
        listView.setAdapter(arrayAdapter);

        final EditText editTextTitle = findViewById(R.id.editTextTitle);
        editTextTitle.setOnEditorActionListener((v, actionId, event) -> {
            final String title = editTextTitle.getText().toString();
            databaseHelper.createNote(title);
            editTextTitle.setText("");

            notesTitles.add(0, title);
            arrayAdapter.notifyDataSetChanged();

            return false;
        });
    }

    private ArrayList<String> loadNotesTitles() {
        final ArrayList<Note> notes = databaseHelper.getNotes();
        final ArrayList<String> titles = new ArrayList<>();
        for (Note note : notes) {
            titles.add(note.getTitle());
        }

        return titles;
    }
}