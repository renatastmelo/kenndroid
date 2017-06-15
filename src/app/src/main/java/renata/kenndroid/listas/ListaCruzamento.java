package renata.kenndroid.listas;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.adapters.AdapterCruzamento;
import renata.kenndroid.cadastro.CadCruzamento;
import renata.kenndroid.persistencia.Cruzamento;

public class ListaCruzamento extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    // Comandos que guiam o funcionamento da listagem
    public static final int CMD_LISTAR = 1;     // Apenas listar
    public static final int CMD_SELECIONAR = 2; // Selecionar um item se o usuário clicar

    List<Cruzamento> cruzamentos;
    private int comando;

    private View.OnClickListener AddListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ListaCruzamento.this, CadCruzamento.class);
            intent.putExtra("comando", "criar");
            startActivityForResult(intent, RES_CADASTRO);
        }
    };

    private AdapterView.OnItemClickListener ItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Se o comando era LISTAR
            if (ListaCruzamento.this.comando == CMD_LISTAR) {
                Intent intent = new Intent(ListaCruzamento.this, CadCruzamento.class);
                intent.putExtra("comando", "editar");
                intent.putExtra("id", id);
                startActivityForResult(intent, RES_EDICAO);
            }
            // Se o comando era SELECIONAR
            else if (ListaCruzamento.this.comando == CMD_SELECIONAR) {
                // Retornar o ID do item clicado pelo usuario
                Intent result = new Intent();
                result.putExtra("id", id);
                result.putExtra("texto", String.format("(M)%s x %s(F)",
                        cruzamentos.get(position).macho.nome,
                        cruzamentos.get(position).femea.nome));
                ListaCruzamento.this.setResult(Activity.RESULT_OK, result);
                finish();
            }
        }
    };

    public void carregar()
    {
        this.cruzamentos.clear();
        SQLiteDatabase db = KenndroidDb.getInstance(this).getWritableDatabase();
        Cruzamento.tudo(db, this.cruzamentos);
        db.close();
    }

    /**
     * Trata o retorno/resultado quando o usuáio sai da tela de Cadastro.
     * @param requestCode Código da Solicitação
     *   - RES_CADASTRO - quando foi clicando no botão (+) de cadastrar
     *   - RES_EDICAO - quando foi clicando em um item na lista para editar.
     * @param resultCode Código de resultado, se foi RESULT_OK ou se foi cancelado.
     * @param data Dados retornados (atualmente nenhum).
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode)
        {
            case RES_CADASTRO: // Tratar Retorno do Cadastro de Canil
            case RES_EDICAO:   // Tratar Retorno da Edição de Canil igual ao cadastro
                if (resultCode == Activity.RESULT_OK) {
                    carregar();
                    ListView lista = (ListView) findViewById(R.id.lista);
                    lista.invalidateViews();
                }
                break;
        }
    }

    /**
     * Executado quando a janela de listagem é criada.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        // Ler comando enviado para essa tela
        Intent intent = getIntent();
        this.comando = intent.getIntExtra("comando", CMD_LISTAR);

        // Criar lista para armazenar os canis em memoria.
        this.cruzamentos = new ArrayList<Cruzamento>();

        // Setar o Listener do Botão de Adicionar
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(AddListener);

        // Carregar a lista de canis usando o método loadAll da classe de persistência.
        carregar();

        // Encontrar o controle ListView da tela.
        ListView lista = (ListView) findViewById(R.id.lista);

        // Setar o ItemClickListener da lista para receber os clicks em itens.
        lista.setOnItemClickListener(ItemClickListener);

        // Criar o adapter com a lista de canis definida na classe, usando essa própria activity.
        AdapterCruzamento adapter = new AdapterCruzamento(this.cruzamentos, this);

        // Setar o adapter da ListView da tela
        lista.setAdapter(adapter);

        setTitle("Cruzamentos");
    }
}
