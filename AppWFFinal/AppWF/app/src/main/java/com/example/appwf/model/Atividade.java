package com.example.appwf.model;

public class Atividade {

    private int id;
    private String titulo, enunciado;

    public Atividade() {
    }

    public Atividade(String enunciado) {
        this.enunciado = enunciado;
    }

    public Atividade(int id, String titulo, String enunciado) {
        this.id = id;
        this.titulo = titulo;
        this.enunciado = enunciado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
