package renata.kenndroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import renata.kenndroid.persistencia.Vacinacao;

public class CadVacinacao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_vacinacao);
    }


    // TODO: fazer as listagem de telas para obter (animal, vacina, clinica e veterinário) para os dropboxs
    public void colocarDadosDaTelaNaPersistencia(Vacinacao vac) {
        EditText edt;

        edt = (EditText) findViewById(R.id.data);
        vac.data = edt.getText().toString();

        edt = (EditText) findViewById(R.id.vacValidade);
        vac.validade = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_lote);
        vac.lote = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_reacao_adversa);
        vac.reacoes_adversas = edt.getText().toString();

        edt = (EditText) findViewById(R.id.pgtoVac);
        vac.valor = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.vacinacao_obs);
        vac.observacoes = edt.getText().toString();

    }
}
