package renata.kenndroid.listas;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import renata.kenndroid.KenndroidDb;
import renata.kenndroid.R;
import renata.kenndroid.adapters.AdapterAnimal;
import renata.kenndroid.cadastro.CadAnimal;
import renata.kenndroid.persistencia.Animal;

public class ListaAnimal extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    // Comandos que guiam o funcionamento da listagem
    public static final int CMD_LISTAR = 1;     // Apenas listar
    public static final int CMD_SELECIONAR = 2; // Selecionar um item se o usuário clicar

    private List<Animal> animais;
    private int comando;        // Comando que foi recebido da janela que chamou essa

    private View.OnClickListener AddListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ListaAnimal.this, CadAnimal.class);
            intent.putExtra("comando", "criar");
            startActivityForResult(intent, RES_CADASTRO);
        }
    };

    private AdapterView.OnItemClickListener ItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // Se o comando era LISTAR
            if (ListaAnimal.this.comando == CMD_LISTAR) {
                // Iniciar activity de cadastro para editar o item clicado
                Intent intent = new Intent(ListaAnimal.this, CadAnimal.class);
                intent.putExtra("comando", "editar");
                intent.putExtra("id", id);
                startActivityForResult(intent, RES_EDICAO);
            }
            // Se o comando era SELECIONAR
            else if (ListaAnimal.this.comando == CMD_SELECIONAR) {
                // Retornar o ID do item clicado pelo usuario
                Intent result = new Intent();
                result.putExtra("id", id);
                result.putExtra("texto", animais.get(position).nome);
                ListaAnimal.this.setResult(Activity.RESULT_OK, result);
                finish();
            }
        }
    };

    public void carregar()
    {
        this.animais.clear();
        SQLiteDatabase db = KenndroidDb.getInstance(this).getWritableDatabase();
        Animal.tudo(db, this.animais);
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
            case RES_CADASTRO: // Tratar Retorno do Cadastro de Animal
            case RES_EDICAO:   // Tratar Retorno da Edição de Animal igual ao cadastro
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

        Intent intent = getIntent();
        this.comando = intent.getIntExtra("comando", CMD_LISTAR);

        // Criar lista para armazenar os Animais em memoria.
        this.animais = new ArrayList<Animal>();

        // Setar o Listener do Botão de Adicionar
        FloatingActionButton btnAdd = (FloatingActionButton) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(AddListener);

        // Carregar a lista de Animais usando o método tudo da classe de persistência.
        carregar();

        // Encontrar o controle ListView da tela.
        ListView lista = (ListView) findViewById(R.id.lista);

        // Setar o ItemClickListener da lista para receber os clicks em itens.
        lista.setOnItemClickListener(ItemClickListener);

        // Criar o adapter com a lista de animais definida na classe, usando essa própria activity.
        AdapterAnimal adapter = new AdapterAnimal(this.animais, this);

        // Setar o adapter da ListView da tela
        lista.setAdapter(adapter);

        setTitle("Animais");
    }
}
