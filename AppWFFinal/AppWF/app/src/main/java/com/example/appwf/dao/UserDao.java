package com.example.appwf.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.appwf.helper.SQLiteDataHelper;
import com.example.appwf.model.Usuario;

import java.util.ArrayList;

public class UserDao implements IGenericDao<Usuario> {

    private SQLiteDataHelper dbHelper;
    private SQLiteDatabase db;

    // Construtor
    public UserDao(Context context) {
        dbHelper = new SQLiteDataHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    // Método para inserir um usuário
    public long insert(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("username", usuario.getUsername());
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());

        // Inserir e retornar o ID do registro inserido
        return db.insert("usuario", null, values);
    }

    @Override
    public long update(Usuario obj) {
        return 0;
    }

    @Override
    public long delete(Usuario obj) {
        return 0;
    }

    @Override
    public Usuario getById(int id) {
        return null;
    }

    // Método para buscar um usuário pelo username
    public Usuario getByUsername(String username) {
        Usuario usuario = null;
        Cursor cursor = db.query(
                "usuario",
                null,
                "username = ?",
                new String[]{username},
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            usuario = new Usuario();
            usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            usuario.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("username")));
            usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
            usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
            cursor.close();
        }
        return usuario;
    }

    // Método para obter todos os usuários
    public ArrayList<Usuario> getAll() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        Cursor cursor = db.query("usuario", null, null, null, null, null, "username ASC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Usuario usuario = new Usuario();
                usuario.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                usuario.setUsername(cursor.getString(cursor.getColumnIndexOrThrow("username")));
                usuario.setEmail(cursor.getString(cursor.getColumnIndexOrThrow("email")));
                usuario.setSenha(cursor.getString(cursor.getColumnIndexOrThrow("senha")));
                listaUsuarios.add(usuario);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return listaUsuarios;
    }

    // Método para fechar o banco de dados
    public void close() {
        if (db != null && db.isOpen()) {
            db.close();
        }
    }
}