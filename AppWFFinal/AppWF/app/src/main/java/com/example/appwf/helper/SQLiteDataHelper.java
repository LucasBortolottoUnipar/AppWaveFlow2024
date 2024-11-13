package com.example.appwf.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "appwf.db";
    private static final int DATABASE_VERSION = 2; // Versão incrementada

    // SQL para criar a tabela de usuários
    private static final String CREATE_TABLE_USUARIO = "CREATE TABLE usuario (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "username TEXT UNIQUE, " +
            "email TEXT UNIQUE, " +
            "senha TEXT)";

    // SQL para criar a tabela de atividades
    private static final String CREATE_TABLE_ATIVIDADE = "CREATE TABLE atividade (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "titulo TEXT, " +
            "enunciado TEXT NOT NULL)";

    public SQLiteDataHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Criação das tabelas
        sqLiteDatabase.execSQL(CREATE_TABLE_USUARIO);
        sqLiteDatabase.execSQL(CREATE_TABLE_ATIVIDADE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Criar a tabela atividade se a versão anterior for menor que 2
            sqLiteDatabase.execSQL(CREATE_TABLE_ATIVIDADE);
        }
        // Adicione mais condições se houver futuras atualizações de versão
    }
}
