package com.example.appwf.controller;

import android.content.Context;
import android.text.TextUtils;

import com.example.appwf.dao.UserDao;
import com.example.appwf.model.Usuario;

import java.util.ArrayList;

public class UserController {

    private final Context context;
    private final UserDao userDao;

    public UserController(Context context) {
        this.context = context;
        this.userDao = new UserDao(context);
    }

    // Método para salvar um usuário
    public String salvarUsuario(String username, String email, String senha) {
        try {
            // Validação dos campos
            if (TextUtils.isEmpty(username)) {
                return "Informe o Usuário.";
            }
            if (TextUtils.isEmpty(email)) {
                return "Informe o Email.";
            }
            if (TextUtils.isEmpty(senha)) {
                return "Informe a Senha.";
            }

            // Verificar se o usuário já existe
            Usuario usuarioExistente = userDao.getByUsername(username);
            if (usuarioExistente != null) {
                return "O Username (" + username + ") já está cadastrado.";
            }

            // Criar novo usuário
            Usuario novoUsuario = new Usuario();
            novoUsuario.setUsername(username);
            novoUsuario.setEmail(email);
            novoUsuario.setSenha(senha);

            // Inserir no banco de dados
            long id = userDao.insert(novoUsuario);
            if (id > 0) {
                return "Usuário cadastrado com sucesso.";
            } else {
                return "Erro ao cadastrar usuário.";
            }
        } catch (Exception e) {
            return "Erro ao cadastrar usuário: " + e.getMessage();
        } finally {
            // Fechar a conexão com o banco de dados
            userDao.close();
        }
    }

    // Método para retornar todos os usuários cadastrados
    public ArrayList<Usuario> retornarTodosUsuarios() {
        try {
            return userDao.getAll();
        } catch (Exception e) {
            return new ArrayList<>();
        } finally {
            userDao.close();
        }
    }

    // Método para login
    public Usuario login(String username, String senha) {
        try {
            Usuario usuario = userDao.getByUsername(username);
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return usuario;
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        } finally {
            userDao.close();
        }
    }
}
