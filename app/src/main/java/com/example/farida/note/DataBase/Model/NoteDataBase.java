package com.example.farida.note.DataBase.Model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.farida.note.DataBase.Model.DAOs.NoteDAO;

@Database ( entities = Note.class ,version = 1,exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    private final static String DATABASE_NAME="Route_NoteDataBase";

    private static NoteDataBase dataBase;
    public abstract NoteDAO noteDAO();

    public static NoteDataBase getInstance(Context context){

        if(dataBase == null){
            //initialize
            dataBase = Room.databaseBuilder (context,NoteDataBase.class,DATABASE_NAME )
                    .fallbackToDestructiveMigrationFrom (  )
                    .allowMainThreadQueries ()
                    .build ();
        }
        return dataBase;
    }

}
