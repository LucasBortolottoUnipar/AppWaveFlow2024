package com.example.appwf.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.appwf.helper.SQLiteDataHelper;
import com.example.appwf.model.Atividade;

import java.util.ArrayList;

public class AtividadeDAO implements IGenericDao <Atividade> {

    private SQLiteDataHelper dbHelper;
    private SQLiteDatabase db;

    public AtividadeDAO(Context context) {
        dbHelper = new SQLiteDataHelper(context);
        db = dbHelper.getWritableDatabase();
    }



    @Override
    public long insert(Atividade obj) {
        ContentValues values = new ContentValues();
        //values.put("titulo", obj.getTitulo());
        values.put("enunciado", obj.getEnunciado());

        // Inserir e retornar o ID do registro inserido
        return db.insert("atividade", null, values);
    }


    @Override
    public long update(Atividade obj) {

        ContentValues values = new ContentValues();
        values.put("titulo", obj.getTitulo());
        values.put("enunciado", obj.getEnunciado());


        return db.update("atividade",values,"id = ?",new String[]{String.valueOf(obj.getId())} );
        //UPDATE atividade SET titulo = ?, enunciado = ? WHERE id = ?
    }

    @Override
    public long delete(Atividade obj) {
        return db.delete("atividade","id = ?", new String[]{String.valueOf(obj.getId())});
    }

    @Override
    public Atividade getById(int id) {
        Cursor cursor = db.query("atividade", new String[]{"id","titulo","enunciado"},"id = ?",
                new String[]{String.valueOf(id)},null,null,null);

        if (cursor != null && cursor.moveToFirst()){
            Atividade atividade = new Atividade();
            atividade.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
            atividade.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
            atividade.setEnunciado(cursor.getString(cursor.getColumnIndexOrThrow("enunciado")));
            cursor.close();
            return atividade;
        }
        return null;
    }

    @Override
    public ArrayList<Atividade> getAll() {
        Cursor cursor = db.query("atividade", new String[]{"id","titulo","enunciado"},"",
                new String[]{},null,null,null);

        ArrayList<Atividade> lista = new ArrayList();

        if (cursor != null && cursor.moveToFirst()){
            do {
                Atividade atividade = new Atividade();
                atividade.setId(cursor.getInt(cursor.getColumnIndexOrThrow("id")));
                atividade.setTitulo(cursor.getString(cursor.getColumnIndexOrThrow("titulo")));
                atividade.setEnunciado(cursor.getString(cursor.getColumnIndexOrThrow("enunciado")));
                lista.add(atividade);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return lista;
    }
}
