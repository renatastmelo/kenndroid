package renata.kenndroid.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import renata.kenndroid.R;
import renata.kenndroid.persistencia.Clinica;

/**
 * Created by Renata on 13/06/2017.
 */

public class AdapterClinica extends BaseAdapter {

    // Lista de itens para usar
    private final List<Clinica> itens;

    // Activity (janela) que vai usar a lista
    private final Activity activity;

    /**
     * Constructor do adapter.
     * @param itens Lista de itens que será usada
     * @param act Activity que usará o adapter.
     */
    public AdapterClinica(List<Clinica> itens, Activity act) {
        this.itens = itens;
        this.activity = act;
    }

    /**
     * Tem de retornar o número de itens na lista.
     * @return número de itens na lista
     */
    @Override
    public int getCount() {
        // retornar o tamanho da lista de itens
        return itens.size();
    }

    /**
     * Obtém um item da lista e retorna.
     * @param position posição do item na lista (índice).
     * @return retorna o item de indice especificado na lista.
     */
    @Override
    public Object getItem(int position) {
        // usa a função get da lista pra pegar o item na posição.
        return itens.get(position);
    }

    /**
     * Obtém o ID do item na posição (índice) especificado.
     * @param position índice do item.
     * @return id do item no índice especificado.
     */
    @Override
    public long getItemId(int position) {
        // retorna o id da Clinica na posição
        return itens.get(position).id;
    }

    /**
     * Essa função cria uma View para colocar na lista.
     * @param position posição do item para criar a view.
     * @param convertView
     * @param parent O layout ao qual será adicionado o item da lista.
     * @return View do item para mostrar na tela.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflar o layout do "item da lista"
        View view = this.activity.getLayoutInflater().inflate(R.layout.item_clinica, parent, false);
        // Pegar a Clinica para colocar nesse item da lista.
        Clinica clinica = this.itens.get(position);

        // Agora encontrar os campos do layout item_Clinica
        TextView nome = (TextView) view.findViewById(R.id.txt_nome);
        TextView telefone = (TextView) view.findViewById(R.id.txt_telefone);
        TextView email = (TextView) view.findViewById(R.id.txt_email);

        // Preencher os campos do layout com a informação da Clinica
        nome.setText(clinica.nome);
        String fone;
        if (clinica.fone1 != null && clinica.fone1.length() > 0)
        {
            fone = clinica.fone1;
        } else if (clinica.fone2 != null && clinica.fone2.length() > 0)
        {
            fone = clinica.fone2;
        } else {
            fone = "(  ) _____.____";
        }
        telefone.setText(fone);
        email.setText(clinica.email);

        return view;
    }
}
