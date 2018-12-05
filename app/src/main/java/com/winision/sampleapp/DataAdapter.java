package com.winision.sampleapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>
implements Filterable {

    private List<Modal> users;
    private List<Modal> usersFiltered;
    private Context context;

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView titleTxt;
        TextView details;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.emailTxt);
            details = itemView.findViewById(R.id.nameTxt);
        }
    }

    public DataAdapter(Context context) {
        this.users = new ArrayList<>();
        this.usersFiltered = new ArrayList();
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardholder,  viewGroup, false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.DataViewHolder dataViewHolder, int i) {
        final Modal modal = usersFiltered.get(i);
        dataViewHolder.titleTxt.setText(modal.getTitle());
        dataViewHolder.details.setText(modal.getBody());
    }

    @Override
    public int getItemCount() {
        return usersFiltered.size();
    }

    public void addData(List<Modal> data) {
        users.addAll(data);
        this.usersFiltered = users;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchString = charSequence.toString();
                if(searchString.isEmpty()) {
                    usersFiltered = users;
                }else {
                    List<Modal> filteredList = new ArrayList<>();
                    for (Modal row: users) {
                        if(row.getTitle().toLowerCase().contains(searchString.toLowerCase())
                                || row.getBody().toLowerCase().contains(searchString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    usersFiltered = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = usersFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                usersFiltered = (List<Modal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
