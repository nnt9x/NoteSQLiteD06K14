package com.example.notesqlited06k14;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.notesqlited06k14.adapter.NoteAdapter;
import com.example.notesqlited06k14.db.MyDB;
import com.example.notesqlited06k14.db.NoteDAO;
import com.example.notesqlited06k14.model.Note;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView lvNotes;
    private List<Note> dataSource;
    private NoteAdapter noteAdapter;

    private MyDB dbHelper;
    private NoteDAO noteDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNotes = findViewById(R.id.lv_notes);
        // init db
        dbHelper = new MyDB(this);
        noteDAO = new NoteDAO(dbHelper);

        // Can co du lieu (Fake du lieu)
        // Thu them 1 so ban ghi co san
        dataSource = noteDAO.getAll();

        // Tao adapter
        noteAdapter = new NoteAdapter(this, dataSource);
        // SetAdapter cho listview
        lvNotes.setAdapter(noteAdapter);
        // Nhan giu de xoa
        lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // xoa trong db
                noteDAO.deleteById(dataSource.get(position).id);

                // lay du lieu
                dataSource.clear();
                dataSource.addAll(noteDAO.getAll());
                // render listview
                noteAdapter.notifyDataSetChanged();
                // Toast
                Toast.makeText(MainActivity.this, "Xoá thành công", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void createNewNote(View view) {
        // Khi click button -> ham nay dc chay
    }
}