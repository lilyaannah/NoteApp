package com.example.mynotes.repository;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mynotes.models.Note;
import java.util.List;

@Dao
public interface MainDao {
    @Insert(onConflict = REPLACE)
     void insert(Note note);

    @Query("SELECT * FROM notes ORDER BY id DESC")
    //@Query("select * from notes")
    List<Note> getAll();
    @Query("UPDATE notes SET title=:title, note=:note where id=:id")
    void update (Long id, String title, String note);

    @Delete
    void delete(Note note);
}
