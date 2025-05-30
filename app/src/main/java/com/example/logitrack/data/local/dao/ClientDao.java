package com.example.logitrack.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.logitrack.data.local.entity.ClientEntity;

import java.util.List;

@Dao
public interface ClientDao {
    @Insert
    void insert(ClientEntity c);

    @Update
    void update(ClientEntity c);

    @Delete
    void delete(ClientEntity c);

    @Query("SELECT * FROM client ORDER BY nom")
    LiveData<List<ClientEntity>> getAll();
} 