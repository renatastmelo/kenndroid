package renata.kenndroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import renata.kenndroid.persistencia.Venda;

public class CadVendas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_vendas);
    }

    public void colocarDadosDaTelaNaPersistencia(Venda ven) {
        EditText edt;
        CheckBox chk;

        //edt = (EditText) findViewById(R.id.dropFPgto;
        //ven.forma_pagamento = Integer.parseInt(edt.getText().toString());

        chk = (CheckBox) findViewById(R.id.edt_nota_fiscal);
        ven.nota_fiscal = chk.isChecked() ? 1 : 0;

        edt = (EditText) findViewById(R.id.edt_dt_venda);
        ven.data = edt.getText().toString();

        edt = (EditText) findViewById(R.id.dt_pedigree);
        ven.data_entrega_pedigree = edt.getText().toString();

        edt = (EditText) findViewById(R.id.valor_total);
        ven.valor = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.valor_recebido);
        ven.valor_recebido = Integer.parseInt(edt.getText().toString());

        edt = (EditText) findViewById(R.id.edt_obs);
        ven.observacoes = edt.getText().toString();

        final Spinner spin = (Spinner) findViewById((R.id.dropFPgto));
        ven.forma_pagamento = String.valueOf(spin.getSelectedItem());


    }
}
