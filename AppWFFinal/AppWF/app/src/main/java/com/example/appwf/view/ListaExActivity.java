package com.example.appwf.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appwf.R;
import com.example.appwf.adapter.AtividadeAdapter;
import com.example.appwf.dao.AtividadeDAO;
import com.example.appwf.model.Atividade;

import java.util.ArrayList;

public class ListaExActivity extends AppCompatActivity {

    RecyclerView recyclerViewAtividades;
    AtividadeAdapter adapter;
    AtividadeDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_ex);

        recyclerViewAtividades = findViewById(R.id.recyclerViewAtividades);
        recyclerViewAtividades.setLayoutManager(new LinearLayoutManager(this));

        dao = new AtividadeDAO(this);
        ArrayList<Atividade> listaAtividades = dao.getAll();

        adapter = new AtividadeAdapter(listaAtividades);
        recyclerViewAtividades.setAdapter(adapter);

    }
}
