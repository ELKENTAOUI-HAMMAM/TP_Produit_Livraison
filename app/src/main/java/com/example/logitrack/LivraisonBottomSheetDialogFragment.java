package com.example.logitrack;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.logitrack.data.local.entity.LivraisonEntity;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class LivraisonBottomSheetDialogFragment extends BottomSheetDialogFragment {
    private static final String ARG_ID = "livraison_id";
    private LivraisonViewModel viewModel;
    private int livraisonId;

    public static LivraisonBottomSheetDialogFragment newInstance(int livraisonId) {
        LivraisonBottomSheetDialogFragment frag = new LivraisonBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, livraisonId);
        frag.setArguments(args);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livraison_bottom_sheet, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(LivraisonViewModel.class);
        livraisonId = getArguments().getInt(ARG_ID);

        RadioGroup statutGroup = view.findViewById(R.id.statutRadioGroup);
        Button updateButton = view.findViewById(R.id.updateButton);

        // (Optionnel) Afficher les infos de la livraison
        TextView detailsText = view.findViewById(R.id.livraisonDetails); // Ajoute ce TextView dans ton layout XML

        // Observer la liste des livraisons et trouver celle avec l'ID voulu
        viewModel.livraisons.observe(getViewLifecycleOwner(), livraisons -> {
            for (LivraisonEntity l : livraisons) {
                if (l.getId() == livraisonId) {
                    // Affiche les infos (optionnel)
                    if (detailsText != null) {
                        detailsText.setText(
                                "Produit ID: " + l.getProduitId() +
                                        "\nClient ID: " + l.getClientId() +
                                        "\nDate: " + l.getDateLivraison() +
                                        "\nStatut: " + l.getStatut()
                        );
                    }
                    // Sélectionne le bon bouton radio selon le statut
                    if (l.getStatut().equals("En attente")) {
                        statutGroup.check(R.id.radioEnAttente);
                    } else {
                        statutGroup.check(R.id.radioLivre);
                    }
                    break;
                }
            }
        });

        updateButton.setOnClickListener(v -> {
            // Récupérer le nouveau statut
            String nouveauStatut = statutGroup.getCheckedRadioButtonId() == R.id.radioEnAttente
                    ? "En attente" : "Livré";
            // Appeler la méthode de mise à jour du statut
            viewModel.updateStatut(livraisonId, nouveauStatut);
            dismiss();
        });

        return view;
    }
}