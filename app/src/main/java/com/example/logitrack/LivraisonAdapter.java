package com.example.logitrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logitrack.data.local.entity.LivraisonEntity;

public class LivraisonAdapter extends ListAdapter<LivraisonEntity, LivraisonAdapter.LivraisonViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(LivraisonEntity livraison);
    }

    private final OnItemClickListener listener;

    public LivraisonAdapter(OnItemClickListener listener) {
        super(DIFF_CALLBACK);
        this.listener = listener;
    }

    @NonNull
    @Override
    public LivraisonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_livraison, parent, false);
        return new LivraisonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LivraisonViewHolder holder, int position) {
        LivraisonEntity livraison = getItem(position);
        holder.bind(livraison, listener);
    }

    static class LivraisonViewHolder extends RecyclerView.ViewHolder {
        private final TextView produitText;
        private final TextView clientText;
        private final TextView dateText;
        private final TextView statutText;

        public LivraisonViewHolder(@NonNull View itemView) {
            super(itemView);
            produitText = itemView.findViewById(R.id.produitText);
            clientText = itemView.findViewById(R.id.clientText);
            dateText = itemView.findViewById(R.id.dateText);
            statutText = itemView.findViewById(R.id.statutText);
        }

        public void bind(LivraisonEntity livraison, OnItemClickListener listener) {
            produitText.setText("Produit ID: " + livraison.getProduitId());
            clientText.setText("Client ID: " + livraison.getClientId());
            dateText.setText(livraison.getDateLivraison().toString());
            statutText.setText(livraison.getStatut());

            itemView.setOnClickListener(v -> listener.onItemClick(livraison));
        }
    }

    public static final DiffUtil.ItemCallback<LivraisonEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<LivraisonEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull LivraisonEntity oldItem, @NonNull LivraisonEntity newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull LivraisonEntity oldItem, @NonNull LivraisonEntity newItem) {
                    return oldItem.equals(newItem);
                }
            };
}