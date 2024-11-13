package com.example.appwf.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appwf.R;
import com.example.appwf.controller.UserController;

public class RegisterScr extends AppCompatActivity {

    private EditText rgUsuarioEditText, rgEmailEditText, rgSenhaEditText;
    private Button btRegister;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_scr);

        // Inicializando as Views
        rgUsuarioEditText = findViewById(R.id.rgUsuario);
        rgEmailEditText = findViewById(R.id.rgEmail);
        rgSenhaEditText = findViewById(R.id.rgSenha);
        btRegister = findViewById(R.id.btCadastro);

        // Inicializando o controlador de usuário
        userController = new UserController(this);

        // Listener para o botão de registro
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuario();
            }
        });
    }

    private void registrarUsuario() {
        // Obtendo os dados preenchidos
        String username = rgUsuarioEditText.getText().toString();
        String email = rgEmailEditText.getText().toString();
        String senha = rgSenhaEditText.getText().toString();

        // Validando os campos
        if (username.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Chamando o controlador para salvar o usuário
        String resultado = userController.salvarUsuario(username, email, senha);

        // Exibindo o resultado para o usuário
        Toast.makeText(this, resultado, Toast.LENGTH_SHORT).show();

        // Limpar campos se o registro for bem-sucedido
        if (resultado.contains("gravados com sucesso")) {
            rgUsuarioEditText.setText("");
            rgEmailEditText.setText("");
            rgSenhaEditText.setText("");
        }
    }
}