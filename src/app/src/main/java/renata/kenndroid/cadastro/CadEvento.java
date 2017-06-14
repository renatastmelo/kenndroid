package renata.kenndroid.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.persistencia.Evento;

public class CadEvento extends AppCompatActivity {

    private Long idEditando = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {
            Evento item = new Evento();
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
            Evento.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    public void colocarDadosDaPersistenciaNaTela(Evento item) {
        EditText edt;

        if (item.nome != null) {
            edt = (EditText) findViewById(R.id.edt_nome);
            edt.setText(item.nome);
        }

        if (item.nome != null) {
            edt = (EditText) findViewById(R.id.edt_organizacao);
            edt.setText(item.organizacao);
        }

        if (item.data != null) {
            edt = (EditText) findViewById(R.id.edt_data);
            edt.setText(item.data);
        }

        if (item.tipo != null) {
            RadioButton btn;
            switch (item.tipo) {
                case 1:
                    btn = (RadioButton) findViewById(R.id.edt_eventoTpo1);
                    btn.setChecked(true);
                    btn = (RadioButton) findViewById(R.id.edt_eventoTpo2);
                    btn.setChecked(false);
                case 2:
                    btn = (RadioButton) findViewById(R.id.edt_eventoTpo1);
                    btn.setChecked(false);
                    btn = (RadioButton) findViewById(R.id.edt_eventoTpo2);
                    btn.setChecked(true);
            }
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

        if (item.fone != null) {
            edt = (EditText) findViewById(R.id.edt_fone1);
            edt.setText(item.fone);
        }

        if (item.email != null) {
            edt = (EditText) findViewById(R.id.edt_email);
            edt.setText(item.email);
        }
    }

    public void colocarDadosDaTelaNaPersistencia(Evento can) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome);
        can.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_organizacao);
        can.organizacao = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_data);
        can.data = edt.getText().toString();

        RadioButton btn = (RadioButton) findViewById(R.id.edt_eventoTpo2);
        if (btn.isChecked()) {
            can.tipo = 2;
        } else {
            can.tipo = 1;
        }

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

        edt = (EditText) findViewById(R.id.edt_fone1);
        can.fone = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_email);
        can.email = edt.getText().toString();

        if (this.idEditando != null) {
            can.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_evento);

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
                Evento item = Evento.carregar(db, this.idEditando);
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
