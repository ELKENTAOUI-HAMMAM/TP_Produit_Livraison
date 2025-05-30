package com.example.logitrack.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.logitrack.data.local.entity.ProduitEntity;

import java.util.List;

@Dao
public interface ProduitDao {
    @Insert
    void insert(ProduitEntity p);

    @Update
    void update(ProduitEntity p);

    @Delete
    void delete(ProduitEntity p);

    @Query("SELECT * FROM produit ORDER BY nom")
    LiveData<List<ProduitEntity>> getAll();
} 