package com.example.farida.note;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.farida.note.DataBase.Model.Note;
import com.example.farida.note.DataBase.Model.NoteDataBase;

import java.util.Calendar;
import java.util.List;

public class AddNoteActivity extends AppCompatActivity implements OnClickListener {

    EditText title;
    TextView date;
    EditText desc;
    Button add;
    Spinner spinner;
    public Note note;
    public NoteDataBase noteDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_add_note );
        title = findViewById ( R.id.title );
        date = findViewById ( R.id.date );
         desc = findViewById ( R.id.desc );
        add = findViewById ( R.id.add );
        spinner = findViewById ( R.id.spinner );
        date.setOnClickListener ( this);
        add.setOnClickListener ( AddNoteActivity.this);


    }


    @Override
    public void onClick(View v) {

        view ();

        if (v.getId () == R.id.add) {
            //Todo : validation
            String sTitle = title.getText ().toString ();
            String sDate = date.getText ().toString ();
            String sDetails = desc.getText ().toString ();
            int priority = spinner.getSelectedItemPosition () + 1;

            Note note = new Note ( sTitle, sDetails, sDate, priority );
            NoteDataBase.getInstance ( this )
                    .noteDAO ()
                    .insertNote ( note );


            setAlarmOfNote(hour,min);


            finish ();

         }
        else if (v.getId ()==R.id.date){
            openTimePickerDialog();
        }


    }




    public void view( ) {


        Intent i = getIntent ();
        i.getIntExtra ( "position", 0 );


        String sTitle= title.getText ().toString ();
        String sDetails  = desc.getText ().toString ();
        String sDate= date.getText ().toString ();
        int priority = spinner.getSelectedItemPosition () + 1;

        Note note1 = new Note ( sTitle, sDetails, sDate,priority);

        NoteDataBase.getInstance ( this ).noteDAO ().getNoteList ( );



        title.setText ( sTitle );
        desc.setText (sDetails );
        date.setText ( sDate );



//        finish ();




    }




            int hour,min;
    private void openTimePickerDialog() {
        Calendar calendar = Calendar.getInstance ();
        TimePickerDialog timePickerDialog =new TimePickerDialog ( this, new TimePickerDialog.OnTimeSetListener () {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                date.setText ( hourOfDay+":"+minute );
                hour = hourOfDay;
                min=minute;

            }
        },calendar.get ( Calendar.HOUR_OF_DAY),calendar.get ( Calendar.MINUTE ),false );
        timePickerDialog.show ();
    }

    private void setAlarmOfNote(int hourOfDay, int minute) {

        Calendar calendar = Calendar.getInstance ();
        Intent intent = new Intent ( this,NoteAlarmReciever.class );
        intent.putExtra ( "title",title.getText ().toString () );
        intent.putExtra ( "desc",desc.getText ().toString () );
        PendingIntent pendingIntent = PendingIntent.getBroadcast
                ( this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT );
        calendar.set ( Calendar.HOUR_OF_DAY,hourOfDay );
        calendar.set ( Calendar.MINUTE,minute );
        AlarmManager alarmManager =(AlarmManager) getSystemService ( ALARM_SERVICE );
        alarmManager.set ( AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis (),pendingIntent );

    }


}
