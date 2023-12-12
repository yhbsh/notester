package com.example.notester;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    private static DatabaseHelper instance;

    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }

        return instance;
    }

    public ArrayList<Note> getNotes() {
        final ArrayList<Note> notes = new ArrayList<>();

        final SQLiteDatabase db = getReadableDatabase();

        final String GET_NOTES = "SELECT * FROM notes;";

        final Cursor cursor = db.rawQuery(GET_NOTES, null);

        if (cursor.moveToFirst()) {
            do {
                final Integer id = cursor.getInt(0);
                final String title = cursor.getString(1);

                final Note note = new Note();
                note.setId(id);
                note.setTitle(title);

                notes.add(note);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return notes;
    }

    public void createNote(String title) {
        final SQLiteDatabase db = getWritableDatabase();

        final String CREATE_NOTE = "INSERT INTO notes (title) VALUES (?);";

        db.execSQL(CREATE_NOTE, new String[]{title});
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS notes (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title TEXT" +
                ");";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
