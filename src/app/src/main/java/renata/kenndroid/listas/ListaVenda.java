package renata.kenndroid.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import renata.kenndroid.persistencia.Venda;

public class ListaVenda extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    List<Venda> vendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
