package com.example.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Pessoa implements Serializable {

    private int id = 0;
    private  String nome;
    private  String telefone;
    private  String email;

    public Pessoa(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public Pessoa() {

    }

    public void setNome(String nome) {

        this.nome = nome;
    }

    public String getNome() {

        return nome;
    }

    public void setTelefone(String telefone) {

        this.telefone = telefone;
    }

    public String getTelefone() {

        return telefone;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public String getEmail() {

        return email;
    }

    @NonNull
    @Override
    public String toString() {

        return nome + " - " + telefone;
    }

    public void setId(int id) {

        this.id = id;
    }

    public int getId() {

        return id;
    }

    public boolean temIdValido() {

        return id > 0;
    }
}
