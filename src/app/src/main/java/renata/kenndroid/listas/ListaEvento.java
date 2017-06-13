package renata.kenndroid.listas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import renata.kenndroid.R;
import renata.kenndroid.persistencia.Evento;

public class ListaEvento extends AppCompatActivity {
    // Código de Solicitação de Cadastro para a tela de Cadastro
    public static final int RES_CADASTRO = 1;
    // Código de Solicitação de Edição para a tela de Cadastro
    public static final int RES_EDICAO = 2;

    List<Evento> eventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
    }
}
