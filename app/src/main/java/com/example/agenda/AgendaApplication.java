package com.example.agenda;

import android.app.Application;

import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.model.Pessoa;

public class AgendaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        criaPessoasDeTeste();
    }

    private void criaPessoasDeTeste() {
        PessoaDAO dao = new PessoaDAO();
        dao.salva(new Pessoa("Pessoa 01", "(xx) xxxx-xxxx", "pessoa01@gmail.com"));

    }
}
