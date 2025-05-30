package com.example.logitrack.data.local.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "livraison",
        foreignKeys = {
                @ForeignKey(entity = ProduitEntity.class, parentColumns = "id", childColumns = "produitId", onDelete = CASCADE),
                @ForeignKey(entity = ClientEntity.class, parentColumns = "id", childColumns = "clientId", onDelete = CASCADE)
        },
        indices = {@Index("produitId"), @Index("clientId")}
)
public class LivraisonEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private Date dateLivraison;
    @NonNull
    private String statut; // "En attente" | "Livré"
    private int produitId;
    private int clientId;

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public Date getDateLivraison() {
        return dateLivraison;
    }

    public void setDateLivraison(@NonNull Date dateLivraison) {
        this.dateLivraison = dateLivraison;
    }

    @NonNull
    public String getStatut() {
        return statut;
    }

    public void setStatut(@NonNull String statut) {
        this.statut = statut;
    }

    public int getProduitId() {
        return produitId;
    }

    public void setProduitId(int produitId) {
        this.produitId = produitId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LivraisonEntity that = (LivraisonEntity) o;
        if (id != that.id) return false;
        if (produitId != that.produitId) return false;
        if (clientId != that.clientId) return false;
        if (!dateLivraison.equals(that.dateLivraison)) return false;
        return statut.equals(that.statut);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dateLivraison.hashCode();
        result = 31 * result + statut.hashCode();
        result = 31 * result + produitId;
        result = 31 * result + clientId;
        return result;
    }
} 