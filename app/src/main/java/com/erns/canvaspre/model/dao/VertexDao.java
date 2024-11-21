package com.erns.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.erns.canvaspre.model.ent.VertexEntity;

import java.util.List;

@Dao
public interface VertexDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<VertexEntity> vertexEntityList);

    @Query("select * from vertex")
    List<VertexEntity> getAll();

}
