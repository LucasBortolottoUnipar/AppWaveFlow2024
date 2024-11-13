package com.example.appwf.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appwf.R;
import com.example.appwf.dao.AtividadeDAO;
import com.example.appwf.model.Atividade;

public class cadastroEx extends AppCompatActivity {

    EditText etEnunciado, etTitulo; // Adicione um EditText para o título
    Button btEnviar, btVerAtividades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_ex);

        etEnunciado = findViewById(R.id.etEnunciado);
        etTitulo = findViewById(R.id.etTitulo); // Certifique-se de ter este campo no layout
        btEnviar = findViewById(R.id.btEnviar);
        btVerAtividades = findViewById(R.id.btVerAtividades);

        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enunciado = etEnunciado.getText().toString().trim();
                String titulo = etTitulo.getText().toString().trim();
                int tamanhoEnunciado = enunciado.length();

                if(tamanhoEnunciado < 5 || tamanhoEnunciado > 200){
                    Toast.makeText(cadastroEx.this, "O enunciado deve ter entre 5 e 200 caracteres.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Atividade atividade = new Atividade();
                atividade.setTitulo(titulo.isEmpty() ? "Sem título" : titulo);
                atividade.setEnunciado(enunciado);

                AtividadeDAO dao = new AtividadeDAO(cadastroEx.this);
                long idInserido = dao.insert(atividade);

                if (idInserido != -1) {
                    Toast.makeText(cadastroEx.this, "Atividade cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                    etEnunciado.setText("");
                    etTitulo.setText("");
                } else {
                    Toast.makeText(cadastroEx.this, "Erro ao cadastrar atividade.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btVerAtividades.setOnClickListener(v -> {
            Intent intent = new Intent(cadastroEx.this, ListaExActivity.class);
            startActivity(intent);
        });
    }
}