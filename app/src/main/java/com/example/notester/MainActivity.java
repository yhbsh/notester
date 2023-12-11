package com.example.notester;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);

        String[] items = new String[100];

        for (int i = 0; i < 100; i++) {
            items[i] = "Hello World";
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_item, items);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Code to execute on item click

            // Example: display a Toast with the clicked item's text
            String selectedItem = (String) parent.getItemAtPosition(position);
            Toast.makeText(MainActivity.this, "Clicked: " + selectedItem + " " + position, Toast.LENGTH_SHORT).show();
        });

    }
}