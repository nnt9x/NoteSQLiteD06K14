package com.example.notesqlited06k14.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.notesqlited06k14.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDAO {
    private final MyDB dbHelper;

    public NoteDAO(MyDB dbHelper) {
        this.dbHelper = dbHelper;
    }

    // Tao
    public Note insert(Note note) {
        // tra ve note da co id
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Chuan bi du lieu
        ContentValues values = new ContentValues();
        values.put("title", note.title);
        // Insert vao db
        long newId = db.insert("notes", null, values);
        note.id = newId;
        return note;
    }

    // Xem - READ
    public List<Note> getAll() {
        // SELECT * FROM ...
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Lay du lieu
        Cursor cursor = db.query("notes", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            // Lay ra tung ban ghi
            Note note = new Note(cursor.getLong(0), cursor.getString(1));
            notes.add(note);
        }
        cursor.close();
        return notes;
    }

    // update: du lieu can update + update cho ban ghi nao bang id
    public void update(Note note, long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", note.title);
        // Ko cap nhat id nhe
        db.update("notes", values, "id = " + id, null);
    }


    // Xoa
    public void deleteById(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // Xoa DELETE FROM notes WHERE "id = 2"
        db.delete("notes", "id = " + id, null);
    }
}
