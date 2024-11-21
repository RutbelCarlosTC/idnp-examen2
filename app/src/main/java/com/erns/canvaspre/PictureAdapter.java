package com.erns.canvaspre;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erns.canvaspre.R;
import com.erns.canvaspre.model.database.AppDatabase;
import com.erns.canvaspre.model.database.FileRepository;
import com.erns.canvaspre.model.database.GalleryRepository;
import com.erns.canvaspre.model.ent.PictureEntity;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {

    private final List<PictureEntity> pictureList;
    private GalleryRepository galleryRepository;

    public PictureAdapter(List<PictureEntity> pictureList) {
        this.pictureList = pictureList;
    }

    @NonNull
    @Override
    public PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_picture, parent, false);
        return new PictureViewHolder(view);

        //galleryRepository = new GalleryRepository(AppDatabase.getInstance(requireContext()));
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        PictureEntity picture = pictureList.get(position);
        holder.titleTextView.setText(picture.title);
        holder.authorTextView.setText(picture.author);
        holder.descriptionTextView.setText(picture.description);
        //Bitmap bitmap=fileRepository.getPicture(picture.link);
        //holder.pictureImageView.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return pictureList.size();
    }

    static class PictureViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView authorTextView;
        TextView descriptionTextView;

        ImageView pictureImageView;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            authorTextView = itemView.findViewById(R.id.author_text_view);
            descriptionTextView = itemView.findViewById(R.id.description_text_view);
            //pictureImageView = itemView.findViewById(R.id.picture_image_view);
        }
    }
}
