package com.example.agenda.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.agenda.R;
import com.example.agenda.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class ListaPessoasAdapter extends BaseAdapter {

    private final List<Pessoa> pessoas = new ArrayList<>();
    private final Context context;

    public ListaPessoasAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Pessoa getItem(int posicao) {
        return pessoas.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return pessoas.get(posicao).getId();
    }

    @Override
    public View getView(int posicao, View view, ViewGroup viewGroup) {
        View viewCriada = criaView(viewGroup);
        Pessoa pessoaDevolvida = pessoas.get(posicao);
        vincula(viewCriada, pessoaDevolvida);
        return viewCriada;
    }

    private void vincula(View view, Pessoa pessoa) {
        TextView nome = view.findViewById(R.id.item_pessoa_nome);
        nome.setText(pessoa.getNome());
        TextView telefone = view.findViewById(R.id.item_pessoa_telefone);
        telefone.setText(pessoa.getTelefone());
    }

    private View criaView(ViewGroup viewGroup) {
        View viewCriada = LayoutInflater
                .from(context)
                .inflate(R.layout.item_pessoa, viewGroup, false);
        return viewCriada;
    }

    public void atualiza (List<Pessoa> pessoas) {
        this.pessoas.clear();
        this.pessoas.addAll(pessoas);

    }

    public void remove(Pessoa pessoa) {

        pessoas.remove(pessoa);
        notifyDataSetChanged();
    }
}
