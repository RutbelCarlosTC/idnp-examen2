package com.erns.canvaspre.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erns.canvaspre.R;
import com.erns.canvaspre.model.ent.CommentEntity;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentViewHolder> {
    private final Context context;
    private final List<CommentEntity> comments;

    public CommentsAdapter(Context context, List<CommentEntity> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentEntity comment = comments.get(position);
        holder.userName.setText(comment.getUserName());
        holder.commentText.setText(comment.getCommentText());
        holder.ratingBar.setRating(comment.getRating());

        // Puedes agregar más lógica, como configurar imágenes de perfil aquí.
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        TextView userName;
        TextView commentText;
        RatingBar ratingBar;
        ImageView userIcon; // Placeholder para ícono de usuario, si decides usarlo.

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.txtUserName);
            commentText = itemView.findViewById(R.id.txtCommentText);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            userIcon = itemView.findViewById(R.id.imgUserIcon);
        }
    }
}
