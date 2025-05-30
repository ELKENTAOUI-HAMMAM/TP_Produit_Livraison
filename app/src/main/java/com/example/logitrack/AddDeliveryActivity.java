package com.example.logitrack;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.logitrack.data.local.entity.ClientEntity;
import com.example.logitrack.data.local.entity.LivraisonEntity;
import com.example.logitrack.data.local.entity.ProduitEntity;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.Date;
import java.util.List;

public class AddDeliveryActivity extends AppCompatActivity {
    private LivraisonViewModel viewModel;
    private ProduitEntity selectedProduit;
    private ClientEntity selectedClient;
    private long selectedDate = System.currentTimeMillis();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_delivery);

        viewModel = new ViewModelProvider(this).get(LivraisonViewModel.class);

        Spinner produitSpinner = findViewById(R.id.produitSpinner);
        Spinner clientSpinner = findViewById(R.id.clientSpinner);
        Button dateButton = findViewById(R.id.dateButton);
        RadioGroup statutGroup = findViewById(R.id.statutRadioGroup);
        Button addButton = findViewById(R.id.addButton);

        viewModel.getProduits().observe(this, produits -> {
            ArrayAdapter<ProduitEntity> produitAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, produits);
            produitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            produitSpinner.setAdapter(produitAdapter);
        });

        viewModel.getClients().observe(this, clients -> {
            ArrayAdapter<ClientEntity> clientAdapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_spinner_item, clients);
            clientAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            clientSpinner.setAdapter(clientAdapter);
        });

        // DatePicker
        dateButton.setOnClickListener(v -> {
            MaterialDatePicker<Long> picker = MaterialDatePicker.Builder.datePicker().build();
            picker.addOnPositiveButtonClickListener(selection -> {
                selectedDate = selection;
                dateButton.setText(android.text.format.DateFormat.format("yyyy-MM-dd", selection));
            });
            picker.show(getSupportFragmentManager(), "datePicker");
        });

        // Ajouter la livraison
        addButton.setOnClickListener(v -> {
            ProduitEntity selectedProduit = (ProduitEntity) produitSpinner.getSelectedItem();
            ClientEntity selectedClient = (ClientEntity) clientSpinner.getSelectedItem();
            if (selectedProduit == null || selectedClient == null) {
                Toast.makeText(this, "Veuillez sélectionner un produit et un client", Toast.LENGTH_SHORT).show();
                return;
            }
            String statut = statutGroup.getCheckedRadioButtonId() == R.id.radioEnAttente ? "En attente" : "Livré";
            LivraisonEntity livraison = new LivraisonEntity();
            livraison.setProduitId(selectedProduit.getId());
            livraison.setClientId(selectedClient.getId());
            livraison.setDateLivraison(new Date(selectedDate));
            livraison.setStatut(statut);
            viewModel.add(livraison);
            finish();
        });
    }
}