package renata.kenndroid.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.listas.ListaAnimal;
import renata.kenndroid.listas.ListaCliente;
import renata.kenndroid.persistencia.Animal;
import renata.kenndroid.persistencia.Cliente;
import renata.kenndroid.persistencia.Venda;

import static renata.kenndroid.cadastro.CadAnimal.RES_PAI;

public class CadVendas extends AppCompatActivity {

    public static final int RES_ANIMAL = 1;
    public static final int RES_CLIENTE = 2;

    private Long idEditando = null;
    private Long idAnimal = null;
    private String txtAnimal = null;
    private Long idCliente = null;
    private String txtCliente = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {

            Venda item = new Venda();
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
            Venda.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };

    private View.OnClickListener AnimalListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadVendas.this, ListaAnimal.class);
            intent.putExtra("comando", ListaAnimal.CMD_SELECIONAR);
            startActivityForResult(intent, RES_ANIMAL);
        }
    };

    private View.OnClickListener ClienteListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadVendas.this, ListaCliente.class);
            intent.putExtra("comando", ListaCliente.CMD_SELECIONAR);
            startActivityForResult(intent, RES_CLIENTE);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case RES_ANIMAL: // Retorno da Seleção de Pai
                if (resultCode == Activity.RESULT_OK) {
                    this.idAnimal = data.getLongExtra("id", 0);
                    this.txtAnimal = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_CLIENTE: // Retorno da Seleção de Mãe
                if (resultCode == Activity.RESULT_OK) {
                    this.idCliente = data.getLongExtra("id", 0);
                    this.txtCliente = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;

        }
    }

    public void atualizarCamposSelecionaveis() {
        Button btn;
        btn = (Button) findViewById(R.id.btnAnimal);
        if (this.idAnimal != null) {
            btn.setText("Animal: " + this.txtAnimal);
        } else {
            btn.setText("Animal");
        }

        btn = (Button) findViewById(R.id.btnCliente);
        if (this.idCliente != null) {
            btn.setText("Cliente: " + this.txtCliente);
        } else {
            btn.setText("Cliente");
        }
    }

    public void colocarDadosDaPersistenciaNaTela(Venda item) {
        EditText edt;
        TextView txt;
        Spinner spn;
        CheckBox chk;
        Button btn;

        if (item.animal != null) {
            this.idAnimal = item.animal.id;
            this.txtAnimal = item.animal.nome;
            btn = (Button) findViewById(R.id.btnAnimal);
            btn.setText("Animal: " + this.txtAnimal);
        }

        if (item.cliente != null) {
            this.idCliente = item.cliente.id;
            this.txtCliente = item.cliente.nome;
            btn = (Button) findViewById(R.id.btnCliente);
            btn.setText("Cliente: " + this.txtCliente);
        }

        if (item.forma_pagamento != null) {
            spn = (Spinner) findViewById(R.id.spnPagamento);
            spn.setSelection(item.forma_pagamento);
        }

        if (item.nota_fiscal != null) {
            chk = (CheckBox) findViewById(R.id.edt_nota_fiscal);
            chk.setChecked(item.nota_fiscal == 1);
        }

        if (item.data != null) {
            edt = (EditText) findViewById(R.id.edt_dt_venda);
            edt.setText(item.data);
        }

        if (item.data_entrega_pedigree != null) {
            edt = (EditText) findViewById(R.id.dt_pedigree);
            edt.setText(item.data_entrega_pedigree);
        }

        if (item.valor != null) {
            edt = (EditText) findViewById(R.id.valor_total);
            edt.setText(String.format("%.2f", (item.valor / 100.0f)));
        }

        if (item.valor_recebido != null) {
            edt = (EditText) findViewById(R.id.valor_recebido);
            edt.setText(String.format("%.2f", (item.valor_recebido / 100.0f)));
        }

        if (item.data_ult_pagamento != null) {
            edt = (EditText) findViewById(R.id.dt_ult_pgto);
            edt.setText(item.data_ult_pagamento);
        }

        if (item.data_entrega_pedigree != null) {
            edt = (EditText) findViewById(R.id.dt_pedigree);
            edt.setText(item.data_entrega_pedigree);
        }

        if (item.observacoes != null) {
            edt = (EditText) findViewById(R.id.edt_obs);
            edt.setText(item.observacoes);
        }
    }

    public void colocarDadosDaTelaNaPersistencia(Venda item) {
        EditText edt;
        Spinner spn;
        CheckBox chk;

        KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
        SQLiteDatabase db = openHelper.getReadableDatabase();

        if (this.idAnimal != null) {
            item.animal = Animal.carregar(db, this.idAnimal, 0);
        }

        if (this.idCliente != null) {
            item.cliente = Cliente.carregar(db, this.idCliente);
        }

        db.close();

        edt = (EditText) findViewById(R.id.edt_dt_venda);
        item.data = edt.getText().toString();

        Float valor;
        edt = (EditText) findViewById(R.id.valor_total);
        try {
            valor = Float.parseFloat(edt.getText().toString().replace(',','.'));
        } catch (Exception ex) {
            valor = 0.0f;
        }
        item.valor = Math.round(valor*100);

        edt = (EditText) findViewById(R.id.valor_recebido);
        try {
            valor = Float.parseFloat(edt.getText().toString().replace(',','.'));
        } catch (Exception ex) {
            valor = 0.0f;
        }
        item.valor_recebido = Math.round(valor*100);

        edt = (EditText) findViewById(R.id.dt_ult_pgto);
        item.data_ult_pagamento = edt.getText().toString();

        edt = (EditText) findViewById(R.id.dt_pedigree);
        item.data_entrega_pedigree = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_obs);
        item.observacoes = edt.getText().toString();

        spn = (Spinner) findViewById(R.id.spnPagamento);
        item.forma_pagamento = Venda.getIdFormaPagamento(spn.getSelectedItem().toString());

        chk = (CheckBox) findViewById(R.id.edt_nota_fiscal);
        item.nota_fiscal = chk.isChecked() ? 1 : 0;

        if (this.idEditando != null) {
            item.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_vendas);

        // Setar o tipo do item de spinner
        String[] formasPagto =  getResources().getStringArray(R.array.formas_pgto);
        Spinner spnPagamento = (Spinner) findViewById(R.id.spnPagamento);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_spinner, formasPagto);
        spnPagamento.setAdapter(adapter);

        // Setar Listener de Click do botão Salvar
        Button btnSalvar = (Button)findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(SalvarListener);

        // Setar Listener de Click do botão Apagar
        Button btnApagar = (Button)findViewById(R.id.apagar);
        btnApagar.setOnClickListener(ApagarListener);

        // Setar Listener de Click do Botão de selecionar Animal
        Button btnAnimal = (Button)findViewById(R.id.btnAnimal);
        btnAnimal.setOnClickListener(AnimalListener);

        // Setar Listener de Click do Botão de selecionar Cliente
        Button btnCliente = (Button)findViewById(R.id.btnCliente);
        btnCliente.setOnClickListener(ClienteListener);

        Intent intent = getIntent();
        String comando = intent.getStringExtra("comando");
        if (comando.equals("editar"))
        {
            this.idEditando = intent.getLongExtra("id", 0);
            if (this.idEditando != 0) {
                SQLiteDatabase db = KenndroidDb.getInstance(this).getReadableDatabase();
                Venda item = Venda.carregar(db, this.idEditando);
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
