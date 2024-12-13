package com.example.mynotes.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "note")

    private String note;
    @ColumnInfo(name = "date")

    private String date;
    @ColumnInfo(name = "pinnedStatus")

    private boolean pinnedStatus = false ;

    public Note(Long id, String title, String note, String date, boolean pinnedStatus) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.date = date;
        this.pinnedStatus = pinnedStatus;
    }

    public Note() {

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNote() {
        return note;
    }

    public String getDate() {
        return date;
    }

    public boolean isPinnedStatus() {
        return pinnedStatus;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPinnedStatus(boolean pinnedStatus) {
        this.pinnedStatus = pinnedStatus;
    }
}
