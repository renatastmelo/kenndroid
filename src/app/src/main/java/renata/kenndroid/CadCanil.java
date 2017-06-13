package renata.kenndroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import renata.kenndroid.persistencia.Canil;

public class CadCanil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_canil);
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
        can.cep = Integer.parseInt(edt.getText().toString());

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

    }
}
