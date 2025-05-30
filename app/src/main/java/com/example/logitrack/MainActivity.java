package com.example.logitrack;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private LivraisonViewModel viewModel;
    private LivraisonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel = new ViewModelProvider(this).get(LivraisonViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new LivraisonAdapter(livraison -> {
            LivraisonBottomSheetDialogFragment.newInstance(livraison.getId())
                    .show(getSupportFragmentManager(), "bottomSheet");
        });
        recyclerView.setAdapter(adapter);

        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            swipeRefreshLayout.setRefreshing(false);
        });

        viewModel.livraisons.observe(this, livraisons -> adapter.submitList(livraisons));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            startActivity(new Intent(this, AddDeliveryActivity.class));
        });

        findViewById(R.id.addProductButton).setOnClickListener(v ->
                startActivity(new Intent(this, AddProductActivity.class)));
        findViewById(R.id.addClientButton).setOnClickListener(v ->
                startActivity(new Intent(this, AddClientActivity.class)));
    }
}