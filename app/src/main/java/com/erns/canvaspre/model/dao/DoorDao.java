package com.erns.canvaspre.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.erns.canvaspre.model.ent.DoorEntity;

import java.util.List;

@Dao
public interface DoorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DoorEntity> doorEntityList);

    @Query("select * from doors")
    List<DoorEntity> getAll();


}
