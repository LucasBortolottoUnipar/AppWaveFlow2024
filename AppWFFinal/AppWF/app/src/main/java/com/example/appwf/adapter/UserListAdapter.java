package com.example.appwf.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appwf.R;
import com.example.appwf.model.Usuario;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private ArrayList<Usuario> listaUsuarios;
    private Context context;

    public UserListAdapter(ArrayList<Usuario> listaUsuarios, Context context) {
        this.listaUsuarios = listaUsuarios;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemList = inflater.inflate(R.layout.activity_register_scr, parent, false);

        return new ViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.rgUsuario.setText(usuario.getUsername());
        holder.rgSenha.setText(usuario.getSenha());
        holder.rgEmail.setText(usuario.getEmail());
    }

    /**
     * Retorna o número de itens na lista
     */
    @Override
    public int getItemCount() {
        return this.listaUsuarios.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView rgUsuario;
        public TextView rgSenha;
        public TextView rgEmail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rgUsuario = itemView.findViewById(R.id.rgUsuario);
            rgSenha = itemView.findViewById(R.id.rgSenha);
            rgEmail = itemView.findViewById(R.id.rgEmail);
        }
    }
}