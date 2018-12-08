package com.winision.sampleapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private VideoAdapter videoAdapter;
    private List<VideoModal> videos;

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.videos_card, viewGroup, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder videoViewHolder, int i) {
        videoViewHolder.videoName.setText(videos.get(i).getTitle());
        String thumbnailUrl = videos.get(i).getThumbnailUrl();
        Picasso.get()
                .load(thumbnailUrl)
                .resize(150, 150)
                .centerCrop()
                .into(videoViewHolder.videoThumbnail);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public void addVideodata(List<VideoModal> data) {
        videos.addAll(data);
        notifyDataSetChanged();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        TextView videoName;
        ImageView videoThumbnail;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoThumbnail = itemView.findViewById(R.id.videoThumbnail);
            videoName = itemView.findViewById(R.id.videoName);
        }
    }

}
