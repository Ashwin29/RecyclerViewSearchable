package com.winision.sampleapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private List<ImagesModal> images;
    private Context context;

    public ImageAdapter(Context context) {
        this.images = new ArrayList<>();
        this.context = context;
    }

    public void addImages(List<ImagesModal> data) {
        images.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_images, viewGroup, false);
        return new ImageAdapter.ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
        imageViewHolder.imgTxt.setText(images.get(i).getTitle());
        String imageUrl = images.get(i).getUrl();
        Picasso.get()
                .load(imageUrl)
                .resize(200, 200)
                .centerCrop()
                .into(imageViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        TextView imgTxt;
        ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTxt = itemView.findViewById(R.id.imgTxt);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
