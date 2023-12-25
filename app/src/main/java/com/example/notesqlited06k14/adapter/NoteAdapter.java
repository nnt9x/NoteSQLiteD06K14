package com.example.notesqlited06k14.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notesqlited06k14.R;
import com.example.notesqlited06k14.model.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private List<Note> dataSource;
    private Context context;
    private LayoutInflater layoutInflater;

    public NoteAdapter(Context context, List<Note> dataSource) {
        this.dataSource = dataSource;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return dataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.item_note, parent, false);
        // Bind id
        TextView tvNoteId = convertView.findViewById(R.id.tv_note_id);
        TextView tvNoteTitle = convertView.findViewById(R.id.tv_note_title);
        // Do du lieu vao view
        Note note = dataSource.get(position);
        tvNoteTitle.setText(note.title);
        tvNoteId.setText("" + note.id);

        return convertView;
    }
}
