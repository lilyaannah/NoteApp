package com.example.mynotes;

import androidx.cardview.widget.CardView;

import com.example.mynotes.models.Note;

public interface NotesClickListener {
    void onClick(Note note);
    void onLongClick(Note note, CardView cardView);
}
