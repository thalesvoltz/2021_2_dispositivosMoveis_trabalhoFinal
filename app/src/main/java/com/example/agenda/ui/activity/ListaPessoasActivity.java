package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_PESSOA;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.adapter.ListaPessoasAdapter;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.model.Pessoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaPessoasActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Agenda Telefonica";
    private final PessoaDAO dao = new PessoaDAO();
    private ListaPessoasAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pessoas);
        setTitle(TITULO_APPBAR);
        configuraFabNovoPessoa();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().
                inflate(R.menu.activity_lista_pessoas_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_pessoas_menu_remover) {
            confirmaRemocao(item);
        }

        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(this)
                .setTitle("Removendo Contato")
                .setMessage("Tem certeza que quer remover este contato?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo =
                                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Pessoa pessoaEscolhida = adapter.getItem(menuInfo.position);
                        remove(pessoaEscolhida);
                    }
                })
                .setNegativeButton("Não",null)
                .show();
    }

    private void configuraFabNovoPessoa() {
        FloatingActionButton botaoNovoPessoa = findViewById(R.id.activity_main_lista_pessoas_fab_novo_pessoa);
        botaoNovoPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInserePessoa();
            }
        });
    }

    private void abreFormularioModoInserePessoa() {
        startActivity(new Intent(this, FormularioPessoasActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaPessoas();
    }

    private void atualizaPessoas() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaDePessoas = findViewById(R.id.activity_lista_pessoas_listview);
        configuraAdapter(listaDePessoas);
        configuraListenerDeCliquePorItem(listaDePessoas);
        registerForContextMenu(listaDePessoas);
    }

    private void remove(Pessoa pessoa) {
        dao.remove(pessoa);
        adapter.remove(pessoa);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDePessoas) {
        listaDePessoas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pessoa pessoaEscolhida = (Pessoa) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaPessoa(pessoaEscolhida);
            }
        });
    }

    private void abreFormularioModoEditaPessoa(Pessoa pessoa) {
        Intent vaiParaFormularioActivity = new Intent(ListaPessoasActivity.this, FormularioPessoasActivity.class);
        vaiParaFormularioActivity.putExtra(CHAVE_PESSOA, pessoa);
        startActivity(vaiParaFormularioActivity);
    }

    private void configuraAdapter(ListView listaDePessoas) {

        adapter = new ListaPessoasAdapter(this);
        listaDePessoas.setAdapter(adapter);
    }
}
