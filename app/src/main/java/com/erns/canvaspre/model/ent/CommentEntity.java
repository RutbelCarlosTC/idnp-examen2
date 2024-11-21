package com.erns.canvaspre.model.ent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "comments")
public class CommentEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "pictureId")
    private int pictureId; // Relación con las imágenes

    @ColumnInfo(name = "commentText")
    private String commentText;

    @ColumnInfo(name = "userName")
    private String userName;

    @ColumnInfo(name = "rating")
    private int rating; // Puntuación del comentario

    // Constructor
    public CommentEntity(int pictureId, String commentText, String userName, int rating) {
        this.pictureId = pictureId;
        this.commentText = commentText;
        this.userName = userName;
        this.rating = rating; // Asegúrate de pasar el rating en el constructor
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPictureId() {
        return pictureId;
    }

    public void setPictureId(int pictureId) {
        this.pictureId = pictureId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
