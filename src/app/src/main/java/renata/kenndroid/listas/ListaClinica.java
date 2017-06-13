package renata.kenndroid.listas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import renata.kenndroid.cadastro.CadClinicas;
import renata.kenndroid.persistencia.Clinica;

public class ListaClinica extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    List<Clinica> clinicas;

    private View.OnClickListener AddListener = new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ListaClinica.this, CadClinicas.class);
            intent.putExtra("comando", "criar");
            startActivityForResult(intent, RES_CADASTRO);
        }
    };

    private AdapterView.OnItemClickListener ItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(ListaClinica.this, CadClinicas.class);
            intent.putExtra("comando", "editar");
            intent.putExtra("id", id);
            startActivityForResult(intent, RES_EDICAO);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
