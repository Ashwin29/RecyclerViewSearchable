package com.winision.sampleapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.winision.sampleapp.Modals.NotesModal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private List<NotesModal> notes;
    private Context context;


    public NotesAdapter(Context context) {
        this.notes = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_card, viewGroup, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder notesViewHolder, int i) {
        notesViewHolder.notesTitle.setText(notes.get(i).getTitle());
        notesViewHolder.notesBody.setText(notes.get(i).getBody());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void addNotesData(List<NotesModal> data) {
        notes.addAll(data);
        notifyDataSetChanged();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {

        TextView notesTitle;
        TextView notesBody;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            notesTitle = itemView.findViewById(R.id.notesTitle);
            notesBody = itemView.findViewById(R.id.notesBody);
        }
    }

}
