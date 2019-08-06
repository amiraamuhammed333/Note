package com.example.farida.note;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.farida.note.DataBase.Model.Note;
import com.example.farida.note.DataBase.Model.NoteDataBase;

public class NoteContentActivity extends AppCompatActivity implements View.OnClickListener {


    static Note note;
    protected int id;
    protected EditText titleEdit;
    protected TextView dateEdit;
    protected EditText descriptionNote;
    protected Spinner spinner;
    protected Button updateBtn;



    public static void setNote(Note note) {

        NoteContentActivity.note = note;

    }
    String intentNoteContent;

    int intentNotePosition;

    protected TextView content;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        super.setContentView(R.layout.note_content);
        if (getIntent().getExtras() != null) {

            descriptionNote = (EditText) findViewById(R.id.description_note);
            descriptionNote.setText(getIntent().getExtras().getString("desc"));
            titleEdit = (EditText) findViewById(R.id.titleEdit);
            titleEdit.setText(getIntent().getExtras().getString("title"));
            dateEdit = (TextView) findViewById(R.id.dateEdit);
            dateEdit.setText(getIntent().getExtras().getString("date"));
            id = getIntent().getExtras().getInt("id");

        }
        initView ();
    }
    @Override

    public void onClick(View view) {

        if (view.getId() == R.id.updateBtn) {

            String title = titleEdit.getText().toString();
            String date = dateEdit.getText().toString();
            String desc = descriptionNote.getText().toString();
            int priority= spinner.getSelectedItemPosition()+1;
            Note note = new Note (title,date,desc,priority);
            note.setId(id); /* خطوة ال id  مهمة جدا جدا عشان يعمل ال update معايا لازم احددله بالظبط انهي note  وده عن طريق ال update*/
            NoteDataBase.getInstance(this)

                    .noteDAO().updateNote(note);
            finish();





        }}

        private void initView() {

            titleEdit = (EditText) findViewById(R.id.titleEdit);
            dateEdit = (TextView) findViewById(R.id.dateEdit);
            descriptionNote = (EditText) findViewById(R.id.description_note);
            spinner = (Spinner) findViewById(R.id.spinner);
            updateBtn = (Button) findViewById(R.id.updateBtn);
            updateBtn.setOnClickListener(NoteContentActivity.this);

        }

    }





