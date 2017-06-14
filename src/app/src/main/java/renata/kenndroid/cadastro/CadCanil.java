package renata.kenndroid.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.persistencia.Canil;

public class CadCanil extends AppCompatActivity {

    private Long idEditando = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {
            Canil item = new Canil();
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
            Canil.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    public void colocarDadosDaPersistenciaNaTela(Canil item) {
        EditText edt;

        if (item.nome != null) {
            edt = (EditText) findViewById(R.id.edt_nome);
            edt.setText(item.nome);
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

        if (item.data_fundacao != null) {
            edt = (EditText) findViewById(R.id.edt_dataFund);
            edt.setText(item.data_fundacao);
        }

        if (item.cnpj != null) {
            edt = (EditText) findViewById(R.id.edt_cnpj);
            edt.setText(item.cnpj);
        }

        if (item.razao_social != null) {
            edt = (EditText) findViewById(R.id.edt_razao_social);
            edt.setText(item.razao_social);
        }

        if (item.fone1 != null) {
            edt = (EditText) findViewById(R.id.edt_fone1);
            edt.setText(item.fone1);
        }

        if (item.fone2 != null) {
            edt = (EditText) findViewById(R.id.edt_fone2);
            edt.setText(item.fone2);
        }

        if (item.email != null) {
            edt = (EditText) findViewById(R.id.edt_email);
            edt.setText(item.email);
        }
    }

    public void colocarDadosDaTelaNaPersistencia(Canil can) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome);
        can.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cidade);
        can.cidade = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_estado);
        can.estado = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_end);
        can.endereco = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_compl);
        can.complemento = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_pt_ref);
        can.ponto_ref = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cep);
        try {
            can.cep = Integer.parseInt(edt.getText().toString());
        } catch (Exception ex) {
            can.cep = null;
        }

        edt = (EditText) findViewById(R.id.edt_dataFund);
        can.data_fundacao = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cnpj);
        can.cnpj = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_razao_social);
        can.razao_social = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_fone1);
        can.fone1 = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_fone2);
        can.fone2 = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_email);
        can.email = edt.getText().toString();

        if (this.idEditando != null) {
            can.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_canil);

        Button btnSalvar = (Button)findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(SalvarListener);
        Button btnApagar = (Button)findViewById(R.id.apagar);
        btnApagar.setOnClickListener(ApagarListener);

        Intent intent = getIntent();
        String comando = intent.getStringExtra("comando");
        if (comando.equals("editar"))
        {
            this.idEditando = intent.getLongExtra("id", 0);
            if (this.idEditando != 0) {
                SQLiteDatabase db = KenndroidDb.getInstance(this).getReadableDatabase();
                Canil item = Canil.carregar(db, this.idEditando);
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
