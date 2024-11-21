package com.erns.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.erns.canvaspre.model.ent.CommentEntity;

import java.util.List;

@Dao
public interface CommentDao {
    @Insert
    void insert(CommentEntity comment);

    @Insert
    void insertAll(List<CommentEntity> comments);

    @Query("SELECT * FROM comments WHERE pictureId = :pictureId")
    List<CommentEntity> getCommentsByPictureId(int pictureId);

    @Query("DELETE FROM comments WHERE pictureId = :pictureId")
    void deleteCommentsByPictureId(int pictureId);
}
