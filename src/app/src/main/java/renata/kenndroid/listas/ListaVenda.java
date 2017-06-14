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
import renata.kenndroid.adapters.AdapterVenda;
import renata.kenndroid.cadastro.CadVendas;
import renata.kenndroid.persistencia.Venda;

public class ListaVenda extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    List<Venda> vendas;

    private View.OnClickListener AddListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ListaVenda.this, CadVendas.class);
            intent.putExtra("comando", "criar");
            startActivityForResult(intent, RES_CADASTRO);
        }
    };

    private AdapterView.OnItemClickListener ItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ListaVenda.this, CadVendas.class);
            intent.putExtra("comando", "editar");
            intent.putExtra("id", id);
            startActivityForResult(intent, RES_EDICAO);
        }
    };

    public void carregar()
    {
        this.vendas.clear();
        SQLiteDatabase db = KenndroidDb.getInstance(this).getWritableDatabase();
        Venda.tudo(db, this.vendas);
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
            case RES_CADASTRO: // Tratar Retorno do Cadastro de Venda
            case RES_EDICAO:   // Tratar Retorno da Edição de Venda igual ao cadastro
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

        // Criar lista para armazenar as Vendas em memoria.
        this.vendas = new ArrayList<Venda>();

        // Setar o Listener do Botão de Adicionar
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(AddListener);

        // Carregar a lista de Vendas usando o método tudo da classe de persistência.
        carregar();

        // Encontrar o controle ListView da tela.
        ListView lista = (ListView) findViewById(R.id.lista);

        // Setar o ItemClickListener da lista para receber os clicks em itens.
        lista.setOnItemClickListener(ItemClickListener);

        // Criar o adapter com a lista de Vendas definida na classe, usando essa própria activity.
        AdapterVenda adapter = new AdapterVenda(this.vendas, this);

        // Setar o adapter da ListView da tela
        lista.setAdapter(adapter);

        setTitle("Vendas");
    }
}
