package com.winision.sampleapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.winision.sampleapp.Modals.VideoModal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder>
        implements Filterable {

    private VideoAdapter videoAdapter;
    private Context context;
    private List<VideoModal> videos;
    private List<VideoModal> videosFiltered;

    public VideoAdapter(Context context) {
        this.videos = new ArrayList<>();
        this.videosFiltered = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videos_card, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        final VideoModal videoModal = videosFiltered.get(i);
        String thumbnailUrl = videoModal.getThumbnailUrl();
        Picasso.get()
                .load(thumbnailUrl)
                .resize(150, 150)
                .centerCrop()
                .into(videoViewHolder.videoThumbnail);
    }

    @Override
    public int getItemCount() {
        return videosFiltered.size();
    }

    public void addVideodata(List<VideoModal> data) {
        videos.addAll(data);
        this.videosFiltered = videos;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchString = charSequence.toString();
                if (searchString.isEmpty()) {
                    videosFiltered = videos;
                } else {
                    List<VideoModal> filteredList = new ArrayList<>();
                    for (VideoModal row : videos) {
                        if (row.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    videosFiltered = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = videosFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                videosFiltered = (List<VideoModal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        // TextView videoName;
        ImageView videoThumbnail;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            // videoName = itemView.findViewById(R.id.videoName);
        }
    }

}
