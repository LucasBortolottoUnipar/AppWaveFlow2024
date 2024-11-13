package com.example.appwf.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appwf.R;
import com.example.appwf.model.Usuario;
import com.example.appwf.controller.UserController;

public class LoginScr extends AppCompatActivity {

    private EditText usuarioEditText, senhaEditText;
    private Button loginButton;
    private ImageButton signUpButton;
    private UserController userController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_scr);

        // Inicializando as views
        usuarioEditText = findViewById(R.id.usuario);
        senhaEditText = findViewById(R.id.senha);
        loginButton = findViewById(R.id.btLogin);

        // Inicializando o controlador de usuário
        userController = new UserController(this);

        // Listener para o botão de login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = usuarioEditText.getText().toString();
                String senha = senhaEditText.getText().toString();

                if (usuario.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(LoginScr.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
                } else {
                    // Verificando o login
                    Usuario user = userController.login(usuario, senha);
                    if (user != null) {
                        Toast.makeText(LoginScr.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginScr.this, generalScr.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginScr.this, "Usuário ou senha incorretos.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
