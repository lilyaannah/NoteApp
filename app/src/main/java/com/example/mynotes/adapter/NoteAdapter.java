package com.example.mynotes.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynotes.NotesClickListener;
import com.example.mynotes.R;
import com.example.mynotes.models.Note;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoteAdapter extends RecyclerView.Adapter<NotesViewHolder>{
    Context context;
    List<Note> notesList;
    NotesClickListener listener;

    public NoteAdapter(Context context, List<Note> notesList, NotesClickListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.textView_title.setText(notesList.get(position).getTitle());
        holder.textView_title.setSelected(true);

        holder.textView_notes.setText(notesList.get(position).getNote());

        holder.textView_date.setText(notesList.get(position).getDate());
        holder.textView_date.setSelected(true);

        if(notesList.get(position).isPinnedStatus()){
            holder.imageView_pin.setImageResource(R.drawable.pin);
        } else {
            holder.imageView_pin.setImageResource(0);
        }
        int colorCode=getRandomColor();
        holder.notes_container.setCardBackgroundColor(holder.itemView.getResources().getColor(colorCode, null));

        holder.notes_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(notesList.get(holder.getAdapterPosition()));
            }
        });
        holder.notes_container.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onLongClick(notesList.get(holder.getAdapterPosition()), holder.notes_container);
                return true;
            }
        });
    }

    private int getRandomColor(){
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.colo2);
        colorCode.add(R.color.colo3);
        colorCode.add(R.color.colo4);
        colorCode.add(R.color.colo5);
        Random random=new Random();
        int randomColor = random.nextInt(colorCode.size());
        return colorCode.get(randomColor);
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }
}
 class NotesViewHolder extends RecyclerView.ViewHolder {
    CardView notes_container;
    TextView textView_title, textView_notes, textView_date;
    ImageView imageView_pin;

     public NotesViewHolder(@NonNull View itemView) {
         super(itemView);
         notes_container=itemView.findViewById(R.id.notes_container);
         textView_title=itemView.findViewById(R.id.textView_title);
         textView_notes=itemView.findViewById(R.id.textView_notes);
         textView_date=itemView.findViewById(R.id.textView_date);
         imageView_pin=itemView.findViewById(R.id.imageView_pin);
     }
 }
