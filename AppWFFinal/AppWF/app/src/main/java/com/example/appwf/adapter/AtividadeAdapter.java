package com.example.appwf.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appwf.R;
import com.example.appwf.model.Atividade;

import java.util.ArrayList;

public class AtividadeAdapter extends RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder> {

    private ArrayList<Atividade> listaAtividades;

    public AtividadeAdapter(ArrayList<Atividade> listaAtividades) {
        this.listaAtividades = listaAtividades;
    }

    @NonNull
    @Override
    public AtividadeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_atividade, parent, false);
        return new AtividadeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AtividadeViewHolder holder, int position) {
        Atividade atividade = listaAtividades.get(position);
        holder.tvTitulo.setText(atividade.getTitulo());
        holder.tvEnunciado.setText(atividade.getEnunciado());
    }

    @Override
    public int getItemCount() {
        return listaAtividades.size();
    }

    static class AtividadeViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitulo, tvEnunciado;

        public AtividadeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tvTitulo);
            tvEnunciado = itemView.findViewById(R.id.tvEnunciado);
        }
    }
}
