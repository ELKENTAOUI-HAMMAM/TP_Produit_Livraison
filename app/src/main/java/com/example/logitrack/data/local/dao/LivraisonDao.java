package com.example.logitrack.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.logitrack.data.local.entity.LivraisonEntity;

import java.util.Date;
import java.util.List;

@Dao
public interface LivraisonDao {
    @Insert
    void insert(LivraisonEntity l);

    @Query("DELETE FROM livraison WHERE id=:id")
    void deleteById(int id);

    @Query("UPDATE livraison SET statut=:statut WHERE id=:id")
    void updateStatut(int id, String statut);

    @Query("SELECT * FROM livraison ORDER BY dateLivraison DESC")
    LiveData<List<LivraisonEntity>> getAll();

    @Query("SELECT COUNT(*) FROM livraison WHERE dateLivraison=:d AND produitId=:p AND clientId=:c")
    int exists(Date d, int p, int c);
} 