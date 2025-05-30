package com.example.logitrack.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.logitrack.data.local.dao.ProduitDao;
import com.example.logitrack.data.local.dao.LivraisonDao;
import com.example.logitrack.data.local.entity.ClientEntity;
import com.example.logitrack.data.local.entity.LivraisonEntity;
import com.example.logitrack.data.local.entity.ProduitEntity;
import com.example.logitrack.data.local.entity.Converters;

@Database(entities = {ProduitEntity.class, ClientEntity.class, LivraisonEntity.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract ProduitDao produitDao();
    public abstract LivraisonDao livraisonDao();
    public abstract com.example.logitrack.data.local.dao.ClientDao clientDao();

    public static AppDatabase get(Context ctx) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),
                            AppDatabase.class, "logistique.db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
} 