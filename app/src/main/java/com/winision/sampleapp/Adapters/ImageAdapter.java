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
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.winision.sampleapp.Modals.ImagesModal;
import com.winision.sampleapp.Modals.Modal;
import com.winision.sampleapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> implements
        Filterable {

    private List<ImagesModal> images;
    private List<ImagesModal> imagesFiltered;
    private Context context;

    public ImageAdapter(Context context) {
        this.images = new ArrayList<>();
        this.imagesFiltered = new ArrayList<>();
        this.context = context;
    }

    public void addImages(List<ImagesModal> data) {
        images.addAll(data);
        this.imagesFiltered = images;
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
        return imagesFiltered.size();
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

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String searchString = charSequence.toString();
                if (searchString.isEmpty()) {
                    imagesFiltered = images;
                } else {
                    List<ImagesModal> filteredList = new ArrayList<>();
                    for (ImagesModal row : images) {
                        if (row.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    imagesFiltered = filteredList;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = imagesFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                imagesFiltered = (List<ImagesModal>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
