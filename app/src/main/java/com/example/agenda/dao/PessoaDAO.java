package com.example.agenda.dao;


import androidx.annotation.Nullable;

import com.example.agenda.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private final static List<Pessoa> pessoas = new ArrayList<>();
    private static int contadorDeIds = 1;


    public void salva(Pessoa pessoa) {
        pessoa.setId(contadorDeIds);
        pessoas.add(pessoa);
        atualizaIds();
    }

    private void atualizaIds() {
        contadorDeIds++;
    }

    public void edita(Pessoa pessoa) {
        Pessoa pessoaEncontrada = buscaPessoaPeloId(pessoa);
        if (pessoaEncontrada != null) {
            int posicaoDaPessoa = pessoas.indexOf((pessoaEncontrada));
            pessoas.set(posicaoDaPessoa, pessoa);
        }
    }

    @Nullable
    private Pessoa buscaPessoaPeloId(Pessoa pessoa) {
        Pessoa pessoaEncontrada = null;
        for (Pessoa a :
                pessoas) {
            if (a.getId() == pessoa.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Pessoa> todos() {
        return new ArrayList<>(pessoas);
    }

    public void remove(Pessoa pessoa) {
        Pessoa pessoaDevolvida = buscaPessoaPeloId(pessoa);
        if(pessoaDevolvida != null) {
            pessoas.remove(pessoaDevolvida);
        }

    }
}
