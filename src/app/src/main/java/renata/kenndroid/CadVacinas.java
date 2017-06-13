package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadVacinas extends AppCompatActivity {

    private View.OnClickListener CadastrarListener = new View.OnClickListener() {
        public void onClick(View v) {

            Vacina vacina = new Vacina();
            colocarDadosDaTelaNaPersistencia(vacina);

            KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
            SQLiteDatabase db = openHelper.getWritableDatabase();
            vacina.save(db);
            db.close();
            Toast.makeText(getApplicationContext(), "Salvo.", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_vacinas);

        Button btnCadastrar = (Button)findViewById(R.id.button);
        btnCadastrar.setOnClickListener(CadastrarListener);
    }
    public void colocarDadosDaTelaNaPersistencia(Vacina vac) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome_vac);
        vac.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_descricao);
        vac.descricao = edt.getText().toString();
    }
}
