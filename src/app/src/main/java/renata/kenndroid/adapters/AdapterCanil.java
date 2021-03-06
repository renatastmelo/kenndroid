package renata.kenndroid.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import renata.kenndroid.R;
import renata.kenndroid.persistencia.Canil;

/**
 * Created by Renata on 13/06/2017.
 */

public class AdapterCanil extends BaseAdapter {

    // Lista de itens para usar
    private final List<Canil> itens;

    // Activity (janela) que vai usar a lista
    private final Activity activity;

    /**
     * Constructor do adapter.
     * @param itens Lista de itens que será usada
     * @param act Activity que usará o adapter.
     */
    public AdapterCanil(List<Canil> itens, Activity act) {
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
        // retorna o id do Canil na posição
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
        View view = this.activity.getLayoutInflater().inflate(R.layout.item_canil, parent, false);
        // Pegar o canil para colocar nesse item da lista.
        Canil Canil = this.itens.get(position);

        // Agora encontrar os campos do layout item_Canil
        TextView nome = (TextView) view.findViewById(R.id.txt_nome);
        TextView cidade = (TextView) view.findViewById(R.id.txt_cidade);
        TextView estado = (TextView) view.findViewById(R.id.txt_estado);

        // Preencher os campos do layout com a informação da Canil
        nome.setText(Canil.nome);
        cidade.setText(Canil.cidade);
        estado.setText(Canil.estado);
        return view;
    }
}
