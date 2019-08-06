package com.example.farida.note.DataBase.Model.DAOs;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.farida.note.DataBase.Model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    @Insert
     void insertNote(Note note);

    @Update
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);



    @Query ( "delete from Note where title =:title" )
    void deleteNoteByTitle(String  title);

    @Query ( "select * from Note" )
    public List<Note> getNoteList();









}
