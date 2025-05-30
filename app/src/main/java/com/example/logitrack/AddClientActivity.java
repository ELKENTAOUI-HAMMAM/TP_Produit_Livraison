package com.example.logitrack;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.logitrack.data.local.entity.ClientEntity;

public class AddClientActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);

        EditText nomEdit = findViewById(R.id.nomEdit);
        EditText adresseEdit = findViewById(R.id.adresseEdit);
        Button addButton = findViewById(R.id.addButton);

        LivraisonViewModel viewModel = new ViewModelProvider(this).get(LivraisonViewModel.class);

        addButton.setOnClickListener(v -> {
            String nom = nomEdit.getText().toString().trim();
            String adresse = adresseEdit.getText().toString().trim();
            if (nom.isEmpty() || adresse.isEmpty()) {
                Toast.makeText(this, "Nom et adresse obligatoires", Toast.LENGTH_SHORT).show();
                return;
            }
            ClientEntity client = new ClientEntity();
            client.setNom(nom);
            client.setAdresse(adresse);
            // Insertion sur thread séparé
            new Thread(() -> {
                com.example.logitrack.data.local.AppDatabase.get(getApplication())
                        .clientDao().insert(client);
                runOnUiThread(this::finish);
            }).start();
        });
    }
}