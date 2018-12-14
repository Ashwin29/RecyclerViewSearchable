package com.winision.sampleapp.Adapters;


import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.winision.sampleapp.Modals.Modal;
import com.winision.sampleapp.R;
import com.winision.sampleapp.TabbedActivity;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder>
implements Filterable {

    private List<Modal> users;
    private List<Modal> usersFiltered;
    private Context context;
    private int originalHeight = 0;
    private boolean isCardExpanded = false;

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.DataViewHolder dataViewHolder, int i) {
        final Modal modal = usersFiltered.get(i);
        dataViewHolder.nameTxt.setText(modal.getName());

        if (!isCardExpanded) {

            dataViewHolder.callBrief.setVisibility(View.GONE);
            dataViewHolder.briefTxt.setVisibility(View.GONE);
            dataViewHolder.callLogs.setVisibility(View.GONE);
            dataViewHolder.timeStamps.setVisibility(View.GONE);
            dataViewHolder.sharedItems.setVisibility(View.GONE);

            dataViewHolder.infoIcon.setVisibility(View.GONE);
            dataViewHolder.callsIcon.setVisibility(View.GONE);
            dataViewHolder.shareIcon.setVisibility(View.GONE);
            dataViewHolder.attachments.setVisibility(View.GONE);

        }

        dataViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (originalHeight == 0) {
                    originalHeight = view.getHeight();
                }

                if (!isCardExpanded) {

                    dataViewHolder.callBrief.setVisibility(View.VISIBLE);
                    dataViewHolder.briefTxt.setVisibility(View.VISIBLE);
                    dataViewHolder.callLogs.setVisibility(View.VISIBLE);
                    dataViewHolder.timeStamps.setVisibility(View.VISIBLE);
                    dataViewHolder.sharedItems.setVisibility(View.VISIBLE);

                    dataViewHolder.infoIcon.setVisibility(View.VISIBLE);
                    dataViewHolder.callsIcon.setVisibility(View.VISIBLE);
                    dataViewHolder.shareIcon.setVisibility(View.VISIBLE);
                    dataViewHolder.attachments.setVisibility(View.VISIBLE);
                    isCardExpanded = true;

                } else {
                    isCardExpanded = false;

                    dataViewHolder.callBrief.setVisibility(View.GONE);
                    dataViewHolder.briefTxt.setVisibility(View.GONE);
                    dataViewHolder.callLogs.setVisibility(View.GONE);
                    dataViewHolder.timeStamps.setVisibility(View.GONE);
                    dataViewHolder.sharedItems.setVisibility(View.GONE);

                    dataViewHolder.infoIcon.setVisibility(View.GONE);
                    dataViewHolder.callsIcon.setVisibility(View.GONE);
                    dataViewHolder.shareIcon.setVisibility(View.GONE);
                    dataViewHolder.attachments.setVisibility(View.GONE);

                }

            }
        });

    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt;
        TextView callBrief;
        TextView briefTxt;
        TextView callLogs;
        TextView timeStamps;
        TextView sharedItems;

        ImageView infoIcon;
        ImageView callsIcon;
        ImageView shareIcon;
        ImageView attachments;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            callBrief = itemView.findViewById(R.id.callBrief);
            briefTxt = itemView.findViewById(R.id.briefTxt);
            callLogs = itemView.findViewById(R.id.callLogs);
            timeStamps = itemView.findViewById(R.id.timeStamps);
            sharedItems = itemView.findViewById(R.id.sharedItems);

            infoIcon = itemView.findViewById(R.id.infoIcon);
            callsIcon = itemView.findViewById(R.id.callsIcon);
            shareIcon = itemView.findViewById(R.id.shareIcon);
            attachments = itemView.findViewById(R.id.attachments);
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
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardholder, viewGroup, false);
        return new DataViewHolder(view);
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
                                || row.getName().toLowerCase().contains(searchString.toLowerCase())) {
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

    @Override
    public int getItemCount() {
        return usersFiltered.size();
    }

    public void addData(List<Modal> data) {
        users.addAll(data);
        this.usersFiltered = users;
        notifyDataSetChanged();
    }
}
