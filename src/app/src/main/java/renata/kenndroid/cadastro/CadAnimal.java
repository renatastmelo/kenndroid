package renata.kenndroid.cadastro;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.listas.ListaAnimal;
import renata.kenndroid.listas.ListaCanil;
import renata.kenndroid.listas.ListaCliente;
import renata.kenndroid.persistencia.Animal;
import renata.kenndroid.persistencia.Canil;
import renata.kenndroid.persistencia.Cliente;

public class CadAnimal extends AppCompatActivity {

    public static final int RES_PAI = 1;
    public static final int RES_MAE = 2;
    public static final int RES_CANIL = 3;
    public static final int RES_COMPRADOR = 4;
    public static final int RES_FOTO = 5;

    private Long idEditando = null;
    private Long idPai = null;
    private String txtPai = null;
    private Long idMae = null;
    private String txtMae = null;
    private Long idCanil = null;
    private String txtCanil = null;
    private Long idComprador = null;
    private String txtComprador = null;
    private Bitmap foto = null;

    private View.OnClickListener SalvarListener = new View.OnClickListener() {
        public void onClick(View v) {
            Animal item = new Animal();
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
            Animal.deletar(db, idEditando);
            db.close();
            setResult(Activity.RESULT_OK);
            finish();
        }
    };


    // Listeners dos botões de selecionar Pai, Mãe, Canil e Comprador
    private View.OnClickListener PaiListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadAnimal.this, ListaAnimal.class);
            intent.putExtra("comando", ListaAnimal.CMD_SELECIONAR);
            startActivityForResult(intent, RES_PAI);
        }
    };

    private View.OnClickListener MaeListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadAnimal.this, ListaAnimal.class);
            intent.putExtra("comando", ListaAnimal.CMD_SELECIONAR);
            startActivityForResult(intent, RES_MAE);
        }
    };

    private View.OnClickListener CanilListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadAnimal.this, ListaCanil.class);
            intent.putExtra("comando", ListaCanil.CMD_SELECIONAR);
            startActivityForResult(intent, RES_CANIL);
        }
    };

    private View.OnClickListener CompradorListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(CadAnimal.this, ListaCliente.class);
            intent.putExtra("comando", ListaCliente.CMD_SELECIONAR);
            startActivityForResult(intent, RES_COMPRADOR);
        }
    };

    private View.OnClickListener FotoListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(intent, RES_FOTO);
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case RES_PAI: // Retorno da Seleção de Pai
                if (resultCode == Activity.RESULT_OK) {
                    this.idPai = data.getLongExtra("id", 0);
                    this.txtPai = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_MAE: // Retorno da Seleção de Mãe
                if (resultCode == Activity.RESULT_OK) {
                    this.idMae = data.getLongExtra("id", 0);
                    this.txtMae = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_CANIL: // Retorno da Seleção de Canil
                if (resultCode == Activity.RESULT_OK) {
                    this.idCanil = data.getLongExtra("id", 0);
                    this.txtCanil = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_COMPRADOR: // Retorno da Seleção de Comprador
                if (resultCode == Activity.RESULT_OK) {
                    this.idComprador = data.getLongExtra("id", 0);
                    this.txtComprador = data.getStringExtra("texto");
                }
                atualizarCamposSelecionaveis();
                break;
            case RES_FOTO: // Retorno da Foto
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    this.foto = (Bitmap) extras.get("data");
                    int menorDimensao = Math.min(this.foto.getWidth(), this.foto.getHeight());
                    this.foto = Bitmap.createBitmap(this.foto, 0, 0, menorDimensao, menorDimensao);
                    atualizarCamposSelecionaveis();
                } else {
                    this.foto = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_4444);
                    atualizarCamposSelecionaveis();
                }
                break;
        }
    }

    public void atualizarCamposSelecionaveis()
    {
        TextView txt;
        ImageView img;
        if (this.idPai != null) {
            txt = (TextView) findViewById(R.id.txt_pai);
            txt.setText(this.txtPai);
        }

        if (this.idMae != null) {
            txt = (TextView) findViewById(R.id.txt_mae);
            txt.setText(this.txtMae);
        }

        if (this.idCanil != null) {
            txt = (TextView) findViewById(R.id.txt_canil);
            txt.setText(this.txtCanil);
        }

        if (this.idComprador != null) {
            txt = (TextView) findViewById(R.id.txt_comprador);
            txt.setText(this.txtComprador);
        }

        if (this.foto != null) {
            img = (ImageView) findViewById(R.id.foto);
            img.setImageBitmap(this.foto);
        }
    }

    public void colocarDadosDaPersistenciaNaTela(Animal item) {
        EditText edt;
        TextView txt;
        ImageView img;

        if (item.nome != null) {
            edt = (EditText) findViewById(R.id.edt_nome);
            edt.setText(item.nome);
        }

        if (item.data_nasc != null) {
            edt = (EditText) findViewById(R.id.edt_datanasc);
            edt.setText(item.data_nasc);
        }

        if (item.data_venda != null) {
            edt = (EditText) findViewById(R.id.edt_datavenda);
            edt.setText(item.data_venda);
        }

        if (item.pai != null) {
            this.idPai = item.pai.id;
            this.txtPai = item.pai.nome;
            txt = (TextView) findViewById(R.id.txt_pai);
            txt.setText(this.txtPai);
        }

        if (item.mae != null) {
            this.idMae = item.mae.id;
            this.txtMae = item.mae.nome;
            txt = (TextView) findViewById(R.id.txt_mae);
            txt.setText(this.txtMae);
        }

        if (item.canil != null) {
            this.idCanil = item.canil.id;
            this.txtCanil = item.canil.nome;
            txt = (TextView) findViewById(R.id.txt_canil);
            txt.setText(this.txtCanil);
        }

        if (item.comprador != null) {
            this.idComprador = item.comprador.id;
            this.txtComprador = item.comprador.nome;
            txt = (TextView) findViewById(R.id.txt_comprador);
            txt.setText(this.txtComprador);
        }

        if (item.foto != null) {
            this.foto = item.foto;
            img = (ImageView) findViewById(R.id.foto);
            img.setImageBitmap(this.foto);
        }
    }

    public void colocarDadosDaTelaNaPersistencia(Animal item) {
        EditText edt;

        edt = (EditText) findViewById(R.id.edt_nome);
        item.nome = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_datanasc);
        item.data_nasc = edt.getText().toString();

        edt = (EditText) findViewById(R.id.edt_datavenda);
        item.data_venda = edt.getText().toString();

        KenndroidDb openHelper = KenndroidDb.getInstance(getApplicationContext());
        SQLiteDatabase db = openHelper.getWritableDatabase();

        if (this.idPai != null) {
            item.pai = Animal.carregar(db, this.idPai, 0);
        }
        if (this.idMae != null) {
            item.mae = Animal.carregar(db, this.idMae, 0);
        }
        if (this.idCanil != null) {
            item.canil = Canil.carregar(db, this.idCanil);
        }
        if (this.idComprador != null) {
            item.comprador = Cliente.carregar(db, this.idComprador);
        }
        if (this.foto != null) {
            item.foto = this.foto;
        }
        if (this.idEditando != null) {
            item.id = this.idEditando;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cad_animal);

        Button btnSalvar = (Button)findViewById(R.id.salvar);
        btnSalvar.setOnClickListener(SalvarListener);
        Button btnApagar = (Button)findViewById(R.id.apagar);
        btnApagar.setOnClickListener(ApagarListener);

        // Setar o Listener do Botão de setar Pai
        Button btnPai = (Button) findViewById(R.id.btnPai);
        btnPai.setOnClickListener(PaiListener);

        // Setar o Listener do Botão de setar Mãe
        Button btnMae = (Button) findViewById(R.id.btnMae);
        btnMae.setOnClickListener(MaeListener);

        // Setar Listener do Botão de setar Canil
        Button btnCanil = (Button) findViewById(R.id.btnCanil);
        btnCanil.setOnClickListener(CanilListener);

        // Setar Listener do Botão de setar Comprador
        Button btnComprador = (Button) findViewById(R.id.btnComprador);
        btnComprador.setOnClickListener(CompradorListener);

        // Setar Listener do Botão de Fotografar
        Button btnFoto = (Button) findViewById(R.id.btnFoto);
        btnFoto.setOnClickListener(FotoListener);

        Intent intent = getIntent();
        String comando = intent.getStringExtra("comando");
        if (comando.equals("editar"))
        {
            this.idEditando = intent.getLongExtra("id", 0);
            if (this.idEditando != 0) {
                SQLiteDatabase db = KenndroidDb.getInstance(this).getReadableDatabase();
                Animal item = Animal.carregar(db, this.idEditando, 1);
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
