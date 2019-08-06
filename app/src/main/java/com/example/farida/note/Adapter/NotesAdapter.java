package com.example.farida.note.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.farida.note.DataBase.Model.Note;
import com.example.farida.note.DataBase.Model.NoteDataBase;
import com.example.farida.note.R;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    List<Note>notes;





    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
         View view = LayoutInflater.from ( viewGroup.getContext () )
                 .inflate ( R.layout.item_view_note,viewGroup,false );
         return new ViewHolder ( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {
     final Note note = notes.get ( pos );
    viewHolder.title.setText ( note.getTitle () );
    viewHolder.date.setText ( note.getDate () );


    if (onItemClickListener !=null){
        viewHolder.itemView.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick (pos,note);




            }
        } );
    }


        switch(note.getPriority ()){
            case 1:
            {
                viewHolder.priority.setText ( R.string.high );
                viewHolder.priority.setBackgroundResource ( R.drawable.high_padge);
                break;
            }
            case 2:
            {
                viewHolder.priority.setText ( R.string.normal );
                viewHolder.priority.setBackgroundResource ( R.drawable.normal_padge);
                break;
            }
            case 3 :
            {
                viewHolder.priority.setText ( R.string.low);
                viewHolder.priority.setBackgroundResource ( R.drawable.low_padge);
                break;
            }



        }

    }
    public void changeData(List<Note>notes)
    {

        this.notes=notes;
        notifyDataSetChanged ();


    }




    @Override
    public int getItemCount() {
        if (notes==null)return 0;
        return notes.size ();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

   OnItemClickListener onItemClickListener;







    public interface OnItemClickListener{

        void onItemClick(int pos,Note notecontent);

    }

    class  ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView date;
        TextView priority;

        public ViewHolder(@NonNull View itemView) {
            super ( itemView );
            title = itemView.findViewById ( R.id.title );
            date = itemView.findViewById ( R.id.date );
            priority = itemView.findViewById ( R.id.priority );


        }
    }


    public void removeItem(int position) {

        notes.remove(position);

        notifyItemRemoved(position);

    }



    public void restoreItem( Note note, int pos) {

        notes.add(note);

        notifyItemInserted(pos);

    }



    public List<Note> getData() {

        return notes;

    }


}
