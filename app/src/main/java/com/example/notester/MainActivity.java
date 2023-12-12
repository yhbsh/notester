package com.example.notester;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

        ListView listView = findViewById(R.id.listView);

        final DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this);
        final ArrayList<Note> notes = databaseHelper.getNotes();

        final ArrayList<String> titles = new ArrayList<>();

        for (Note note : notes) {
            titles.add(note.getTitle());
        }

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, titles);
        listView.setAdapter(arrayAdapter);
    }
}