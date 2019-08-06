package com.example.farida.note;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.farida.note.Adapter.NotesAdapter;
import com.example.farida.note.DataBase.Model.Note;
import com.example.farida.note.DataBase.Model.NoteDataBase;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    List<Note>notes;
     RecyclerView recyclerView;
     RecyclerView.LayoutManager layoutManager;
     NotesAdapter adapter;
    public static TextView descContent ;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Toolbar toolbar = findViewById ( R.id.toolbar );
        recyclerView = findViewById ( R.id.recycler_view );
        setSupportActionBar ( toolbar );
        adapter= new NotesAdapter ( null );
        layoutManager= new LinearLayoutManager ( this );





        adapter.setOnItemClickListener ( new NotesAdapter.OnItemClickListener () {
            @Override
            public void onItemClick(int pos, Note noteContent) {

                Intent intent =new Intent (MainActivity.this ,AddNoteActivity.class );

                // intent.putExtra ( "position",pos );
                intent.putExtra("title" , noteContent.getTitle());

                intent.putExtra("date" , noteContent.getDate());

                intent.putExtra("desc" , noteContent.getDesc());

                intent.putExtra("priority" , noteContent.getPriority());

                intent.putExtra("id" , noteContent.getId());


                startActivity ( intent );
            }
        }


        );




        recyclerView.setAdapter ( adapter );
        recyclerView.setLayoutManager ( layoutManager );

        FloatingActionButton fab = (FloatingActionButton) findViewById ( R.id.fab );
        fab.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
               //Snackbar.make ( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                       // .setAction ( "Action", null ).show ();

            startActivity ( new Intent ( MainActivity.this,AddNoteActivity.class ) );
            }
        } );







    }

    
    @Override
    protected void onStart() {
        super.onStart ();
        List<Note> notes = NoteDataBase.getInstance (this)
                .noteDAO ()
                .getNoteList ();
        adapter.changeData ( notes);



    }





}
