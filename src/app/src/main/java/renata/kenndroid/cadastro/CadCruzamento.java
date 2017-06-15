package renata.kenndroid.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.listas.ListaAnimal;
import renata.kenndroid.persistencia.Animal;
import renata.kenndroid.persistencia.Cruzamento;

public class CadCruzamento extends AppCompatActivity {
    private static final int RES_MACHO = 1;
    private static final int RES_FEMEA = 2;

    private Long idEditando = null;
    private Long idMacho = null;
    private String txtMacho = null;
    private Long idFemea = null;
    private String txtFemea = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {
            Cruzamento item = new Cruzamento();
            colocarDadosDaTelaNaPersistencia(item);

            KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
            SQLiteDatabase db = openHelper.getWritableDatabase();
            item.salvar(db);
            db.close();
            Toast.makeText(getApplicationContext(), "Salvo.", Toast.LENGTH_LONG).show();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    private View.OnClickListener ApagarListener = new View.OnClickListener() {
        public void onClick(View v) {
            KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
            SQLiteDatabase db = openHelper.getWritableDatabase();
            Cruzamento.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    // Listener do Botão de setar Macho
    private View.OnClickListener MachoListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadCruzamento.this, ListaAnimal.class);
            intent.putExtra("comando", ListaAnimal.CMD_SELECIONAR);
            startActivityForResult(intent, RES_MACHO);
        }
    };

    // Listener do Botão de setar Femea
    private View.OnClickListener FemeaListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadCruzamento.this, ListaAnimal.class);
            intent.putExtra("comando", ListaAnimal.CMD_SELECIONAR);
            startActivityForResult(intent, RES_FEMEA);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case RES_MACHO: // Retorno da Seleção de Pai
                if (resultCode == Activity.RESULT_OK) {
                    this.idMacho = data.getLongExtra("id", 0);
                    this.txtMacho = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_FEMEA: // Retorno da Seleção de Mãe
                if (resultCode == Activity.RESULT_OK) {
                    this.idFemea = data.getLongExtra("id", 0);
                    this.txtFemea = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
        }
    }

    public void atualizarCamposSelecionaveis() {
        Button btn;
        if (this.idMacho != null) {
            btn = (Button) findViewById(R.id.btnMacho);
            btn.setText("Macho: " + this.txtMacho);
        }
        if (this.idFemea != null) {
            btn = (Button) findViewById(R.id.btnFemea);
            btn.setText("Fêmea: " + this.txtFemea);
        }
    }

    public void colocarDadosDaPersistenciaNaTela(Cruzamento item) {
        EditText edt;
        Button btn;
        CheckBox chk;

        if (item.macho != null) {
            this.idMacho = item.macho.id;
            this.txtMacho = item.macho.nome;
            btn = (Button) findViewById(R.id.btnMacho);
            btn.setText("Macho: " + this.txtMacho);
        }

        if (item.femea != null) {
            this.idFemea = item.femea.id;
            this.txtFemea = item.femea.nome;
            btn = (Button) findViewById(R.id.btnFemea);
            btn.setText("Femea: " + this.txtFemea);
        }

        if (item.data != null) {
            edt = (EditText) findViewById(R.id.data);
            edt.setText(item.data);
        }

        if (item.nome_local != null) {
            edt = (EditText) findViewById(R.id.edt_nome_local);
            edt.setText(item.nome_local);
        }

        if (item.cidade != null) {
            edt = (EditText) findViewById(R.id.edt_cidade);
            edt.setText(item.cidade);
        }

        if (item.estado != null) {
            edt = (EditText) findViewById(R.id.edt_estado);
            edt.setText(item.estado);
        }

        if (item.endereco != null) {
            edt = (EditText) findViewById(R.id.edt_end);
            edt.setText(item.endereco);
        }

        if (item.complemento != null) {
            edt = (EditText) findViewById(R.id.edt_compl);
            edt.setText(item.complemento);
        }

        if (item.ponto_ref != null) {
            edt = (EditText) findViewById(R.id.edt_pt_ref);
            edt.setText(item.ponto_ref);
        }

        if (item.cep != null) {
            edt = (EditText) findViewById(R.id.edt_cep);
            edt.setText(item.cep.toString());
        }

        if (item.sucesso != null) {
            chk = (CheckBox) findViewById(R.id.edt_sucesso);
            chk.setChecked(item.sucesso);
        }
    }

    public void colocarDadosDaTelaNaPersistencia(Cruzamento item) {
        EditText edt;
        CheckBox chk;

        KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();

        if (this.idMacho != null) {
            item.macho = Animal.carregar(db, this.idMacho, 0);
        }

        if (this.idFemea != null) {
            item.femea = Animal.carregar(db, this.idFemea, 0);
        }

        db.close();

        edt = (EditText) findViewById(R.id.data);
        item.data = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_nome_local);
        item.nome_local = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cidade);
        item.cidade = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_estado);
        item.estado = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_end);
        item.endereco = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_compl);
        item.complemento = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_pt_ref);
        item.ponto_ref = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cep);
        try {
            item.cep = Integer.parseInt(edt.getText().toString());
        } catch (Exception ex) {
            item.cep = null;
        }

        chk = (CheckBox) findViewById(R.id.edt_sucesso);
        item.sucesso = chk.isChecked();

        if (this.idEditando != null) {
            item.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_cruzamento);

        // Setar o Listener do Botão de Salvar
        Button btnSalvar = (Button)findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(SalvarListener);

        // Setar o Listener do Botão de Apagar
        Button btnApagar = (Button)findViewById(R.id.apagar);
        btnApagar.setOnClickListener(ApagarListener);

        // Setar o Listener do Botão de setar Macho
        Button btnMacho = (Button) findViewById(R.id.btnMacho);
        btnMacho.setOnClickListener(MachoListener);

        // Setar o Listener do Botão de setar Femea
        Button btnFemea = (Button) findViewById(R.id.btnFemea);
        btnFemea.setOnClickListener(FemeaListener);

        Intent intent = getIntent();
        String comando = intent.getStringExtra("comando");
        if (comando.equals("editar"))
        {
            this.idEditando = intent.getLongExtra("id", 0);
            if (this.idEditando != 0) {
                SQLiteDatabase db = KenndroidDb.getInstance(this).getReadableDatabase();
                Cruzamento item = Cruzamento.carregar(db, this.idEditando);
                db.close();
                colocarDadosDaPersistenciaNaTela(item);
            }
            btnSalvar.setText("Atualizar");
            btnApagar.setVisibility(View.VISIBLE);

        }
        else if (comando.equals("criar"))
        {
            this.idEditando = null;
            btnSalvar.setText("Cadastrar");
            btnApagar.setVisibility(View.GONE);
        }
    }
}
