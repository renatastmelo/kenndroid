package renata.kenndroid;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import renata.kenndroid.persistencia.Vacina;

public class CadVacinas extends AppCompatActivity {

    public Long idEditando = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {

            Vacina vacina = new Vacina();
            colocarDadosDaTelaNaPersistencia(vacina);

            KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
            SQLiteDatabase db = openHelper.getWritableDatabase();
            vacina.save(db);
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
            Vacina.del(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

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
                Vacina vac = Vacina.load(db, this.idEditando);
                db.close();
                colocarDadosDaPersistenciaNaTela(vac);
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

    public void colocarDadosDaPersistenciaNaTela(Vacina vac) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome_vac);
        edt.setText(vac.nome);

        edt = (EditText) findViewById(R.id.edt_descricao);
        edt.setText(vac.descricao);
    }

    public void colocarDadosDaTelaNaPersistencia(Vacina vac) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome_vac);
        vac.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_descricao);
        vac.descricao = edt.getText().toString();

        if (this.idEditando != null) {
            vac.id = this.idEditando;
        }
    }
}
