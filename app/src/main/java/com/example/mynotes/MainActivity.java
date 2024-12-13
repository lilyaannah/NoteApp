package com.example.mynotes;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.mynotes.adapter.NoteAdapter;
import com.example.mynotes.models.Note;
import com.example.mynotes.repository.RoomDB;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    NoteAdapter noteAdapter;
    RoomDB database;
    List<Note> notesList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recycler_home);
        floatingActionButton=findViewById(R.id.fab_add);
        database=RoomDB.getInstance(this);
        notesList=database.mainDao().getAll();
        updateRecyclerView(notesList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoteTakeActivity.class);
                startActivityForResult(intent, 101);
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 ){
            if(requestCode == Activity.RESULT_OK){
                Note newNote = (Note) data.getSerializableExtra("note");
                database.mainDao().insert(newNote);
                notesList.clear();
                notesList.addAll(database.mainDao().getAll());
                noteAdapter.notifyDataSetChanged();
            }
        }
    }

    private void updateRecyclerView(List<Note> notesList) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        noteAdapter = new NoteAdapter(MainActivity.this, notesList, notesClickListener);
        recyclerView.setAdapter(noteAdapter);
    }
    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Note note) {

        }

        @Override
        public void onLongClick(Note note, CardView cardView) {

        }
    };
}