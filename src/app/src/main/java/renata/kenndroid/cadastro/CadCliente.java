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
import renata.kenndroid.persistencia.Cliente;

public class CadCliente extends AppCompatActivity {

    private Long idEditando = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {

            Cliente item = new Cliente();
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
            Cliente.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    public void colocarDadosDaPersistenciaNaTela(Cliente item) {
        EditText edt;

        edt = (EditText) findViewById(R.id.dia);
        edt.setText(item.dia_nasc);

        edt = (EditText) findViewById(R.id.mes);
        edt.setText(item.mes_nasc);

        edt = (EditText) findViewById(R.id.ano);
        edt.setText(item.ano_nasc);

        edt = (EditText) findViewById(R.id.edt_nome);
        edt.setText(item.nome);

        edt = (EditText) findViewById(R.id.edt_cpf);
        edt.setText(item.cpf);

        edt = (EditText) findViewById(R.id.edt_cidade);
        edt.setText(item.cidade);

        edt = (EditText) findViewById(R.id.edt_estado);
        edt.setText(item.estado);

        edt = (EditText) findViewById(R.id.edt_end);
        edt.setText(item.endereco);

        edt = (EditText) findViewById(R.id.edt_compl);
        edt.setText(item.complemento);

        edt = (EditText) findViewById(R.id.edt_pt_ref);
        edt.setText(item.ponto_ref);

        edt = (EditText) findViewById(R.id.edt_cep);
        edt.setText(item.cep);

        edt = (EditText) findViewById(R.id.edt_fone1);
        edt.setText(item.fone1);

        edt = (EditText) findViewById(R.id.edt_fone2);
        edt.setText(item.fone2);

        edt = (EditText) findViewById(R.id.edt_email);
        edt.setText(item.email);
    }

    public void colocarDadosDaTelaNaPersistencia(Cliente item) {
        EditText edt;

        edt = (EditText) findViewById(R.id.dia);
        item.dia_nasc = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.mes);
        item.mes_nasc = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.ano);
        item.ano_nasc = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.edt_nome);
        item.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_cpf);
        item.cpf = edt.getText().toString();

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
        item.cep = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.edt_fone1);
        item.fone1 = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_fone2);
        item.fone2 = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_email);
        item.email = edt.getText().toString();

        if (this.idEditando != null) {
            item.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_vacinas);

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
                Cliente item = Cliente.carregar(db, this.idEditando);
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
