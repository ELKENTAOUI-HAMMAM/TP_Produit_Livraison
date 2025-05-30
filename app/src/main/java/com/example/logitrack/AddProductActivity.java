package com.example.logitrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.logitrack.data.local.entity.ProduitEntity;

public class AddProductActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        EditText nomEdit = findViewById(R.id.nomEdit);
        EditText prixEdit = findViewById(R.id.prixEdit);
        Button addButton = findViewById(R.id.addButton);

        LivraisonViewModel viewModel = new ViewModelProvider(this).get(LivraisonViewModel.class);

        addButton.setOnClickListener(v -> {
            String nom = nomEdit.getText().toString().trim();
            String prixStr = prixEdit.getText().toString().trim();
            if (nom.isEmpty() || prixStr.isEmpty()) {
                Toast.makeText(this, "Nom et prix obligatoires", Toast.LENGTH_SHORT).show();
                return;
            }
            double prix = Double.parseDouble(prixStr);
            ProduitEntity produit = new ProduitEntity();
            produit.setNom(nom);
            produit.setPrix(prix);
            // Insertion sur thread séparé
            new Thread(() -> {
                viewModel.getApplication().getApplicationContext();
                com.example.logitrack.data.local.AppDatabase.get(getApplication())
                        .produitDao().insert(produit);
                runOnUiThread(this::finish);
            }).start();
        });
    }
}