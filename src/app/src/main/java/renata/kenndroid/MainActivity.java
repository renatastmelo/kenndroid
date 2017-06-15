package renata.kenndroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import renata.kenndroid.cadastro.CadCruzamento;
import renata.kenndroid.listas.ListaAnimal;
import renata.kenndroid.listas.ListaCanil;
import renata.kenndroid.listas.ListaCliente;
import renata.kenndroid.listas.ListaClinica;
import renata.kenndroid.listas.ListaCruzamento;
import renata.kenndroid.listas.ListaEvento;
import renata.kenndroid.listas.ListaVacina;
import renata.kenndroid.listas.ListaVenda;
import renata.kenndroid.listas.ListaVeterinario;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Ativa os icones
        if(menu instanceof MenuBuilder){
           ((MenuBuilder) menu).setOptionalIconsVisible(true);
       }

        // Cria o menu com base no XML
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = null;

        // Switch que verifica o item do menu selecionado e cria uma intent para abrir a janela
        // correspondente.
        switch (id)
        {
            case R.id.action_canil:
                intent = new Intent(this, ListaCanil.class);
                break;

            case R.id.action_animal:
            intent = new Intent(this, ListaAnimal.class);
            break;

            case R.id.action_clientes:
                intent = new Intent(this, ListaCliente.class);
                break;

            case R.id.action_eventos:
                intent = new Intent(this, ListaEvento.class);
                break;

            case R.id.action_vacinas:
                intent = new Intent(this, ListaVacina.class);
                break;

            case R.id.action_veterinarios:
                intent = new Intent(this, ListaVeterinario.class);
                break;

            case R.id.action_clinicas:
                intent = new Intent(this, ListaClinica.class);
                break;

            case R.id.action_vendas:
                intent = new Intent(this, ListaVenda.class);
                break;

            case R.id.action_cruzamento:
                intent = new Intent(this, ListaCruzamento.class);
                break;
        }
        startActivity(intent);
        return true;

        //return super.onOptionsItemSelected(item);
    }

}
