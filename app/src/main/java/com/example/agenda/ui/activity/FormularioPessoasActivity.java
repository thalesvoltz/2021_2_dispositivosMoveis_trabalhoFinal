package com.example.agenda.ui.activity;

import static com.example.agenda.ui.activity.ConstantesActivities.CHAVE_PESSOA;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.agenda.R;
import com.example.agenda.dao.PessoaDAO;
import com.example.agenda.model.Pessoa;

public class FormularioPessoasActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVA_PESSOA = "Adicionar Contato";
    private static final String TITULO_APPBAR_EDITA_PESSOA = "Alterar Contato";


    private EditText campoNome;
    private EditText campoTelefone;
    private EditText campoEmail;
    final PessoaDAO dao = new PessoaDAO();
    private Pessoa pessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_pessoa);
        inicializacaoDosCampos();
        carregaPessoa();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_pessoa_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_formulario_pessoa_menu_salvar){
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaPessoa() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PESSOA)){
            setTitle(TITULO_APPBAR_EDITA_PESSOA);
            pessoa = (Pessoa) dados.getSerializableExtra(CHAVE_PESSOA);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVA_PESSOA);
            pessoa = new Pessoa();
        }
    }

    private void preencheCampos() {
        campoNome.setText(pessoa.getNome());
        campoTelefone.setText(pessoa.getTelefone());
        campoEmail.setText(pessoa.getEmail());
    }

    private void finalizaFormulario() {
        preenchePessoa();
        if(pessoa.temIdValido()){
            dao.edita(pessoa);
        } else {
           dao.salva(pessoa);
        }
        finish();
    }

    private void inicializacaoDosCampos() {
        campoNome = findViewById(R.id.activity_formulario_pessoa_nome);
        campoTelefone = findViewById(R.id.activity_formulario_pessoa_telefone);
        campoEmail = findViewById(R.id.activity_formulario_pessoa_email);
    }

    private void preenchePessoa() {
        String nome = campoNome.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String email = campoEmail.getText().toString();

        pessoa.setNome(nome);
        pessoa.setTelefone(telefone);
        pessoa.setEmail(email);
    }
}