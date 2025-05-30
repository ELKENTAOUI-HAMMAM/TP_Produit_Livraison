package com.example.logitrack;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.logitrack.data.local.AppDatabase;
import com.example.logitrack.data.local.entity.ClientEntity;
import com.example.logitrack.data.local.entity.LivraisonEntity;
import com.example.logitrack.data.local.entity.ProduitEntity;
import com.example.logitrack.data.repo.LivraisonRepository;
// import com.example.logitrack.data.remote.NetworkModule; // À décommenter quand NetworkModule sera créé

import java.util.List;

public class LivraisonViewModel extends AndroidViewModel {
    private final LivraisonRepository repo;
    public final LiveData<List<LivraisonEntity>> livraisons;

    public LivraisonViewModel(@NonNull Application app) {
        super(app);
        AppDatabase db = AppDatabase.get(app);
        // repo = new LivraisonRepository(db.livraisonDao(), NetworkModule.api()); // Avec API
        repo = new LivraisonRepository(db.livraisonDao()); // Sans API pour l'instant
        livraisons = repo.getLocal();
    }

    public void refresh() {
        // repo.sync(); // À activer quand la méthode sync sera implémentée
    }

    public void add(LivraisonEntity l) {
        repo.add(l);
    }

    // Dans LivraisonViewModel
    public LiveData<List<ProduitEntity>> getProduits() {
        return AppDatabase.get(getApplication()).produitDao().getAll();
    }

    public LiveData<List<ClientEntity>> getClients() {
        return AppDatabase.get(getApplication()).clientDao().getAll();
    }
    public void updateStatut(int id, String statut) {
        repo.updateStatut(id, statut);
    }

}