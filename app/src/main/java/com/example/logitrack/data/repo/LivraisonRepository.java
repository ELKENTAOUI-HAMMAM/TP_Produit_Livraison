package com.example.logitrack.data.repo;

import androidx.lifecycle.LiveData;

import com.example.logitrack.data.local.dao.LivraisonDao;
import com.example.logitrack.data.local.entity.LivraisonEntity;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LivraisonRepository {
    private final LivraisonDao dao;
    private final Executor io = Executors.newSingleThreadExecutor();

    public LivraisonRepository(LivraisonDao d) {
        dao = d;
    }

    public LiveData<List<LivraisonEntity>> getLocal() {
        return dao.getAll();
    }

    public void add(LivraisonEntity l) {
        io.execute(() -> {
            if (dao.exists(l.getDateLivraison(), l.getProduitId(), l.getClientId()) == 0) {
                dao.insert(l);
            }
        });
    }

    public void updateStatut(int id, String statut) {
        io.execute(() -> dao.updateStatut(id, statut));
    }

    public void deleteById(int id) {
        io.execute(() -> dao.deleteById(id));
    }

}