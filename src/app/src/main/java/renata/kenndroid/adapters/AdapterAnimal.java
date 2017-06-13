package renata.kenndroid.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import renata.kenndroid.R;
import renata.kenndroid.persistencia.Animal;

/**
 * Created by Renata on 13/06/2017.
 */

public class AdapterAnimal extends BaseAdapter {

    // Lista de animais para usar
    private final List<Animal> animais;

    // Activity (janela) que vai usar a lista
    private final Activity activity;

    /**
     * Constructor do adapter.
     * @param animais Lista de Animals que será usada
     * @param act Activity que usará o adapter.
     */
    public AdapterAnimal(List<Animal> animais, Activity act) {
        this.animais = animais;
        this.activity = act;
    }

    /**
     * Tem de retornar o número de itens na lista.
     * @return número de itens na lista
     */
    @Override
    public int getCount() {
        // retornar o tamanho da lista de Animals
        return animais.size();
    }

    /**
     * Obtém um item da lista e retorna.
     * @param position posição do item na lista (índice).
     * @return retorna o item de indice especificado na lista.
     */
    @Override
    public Object getItem(int position) {
        // usa a função get da lista pra pegar o item na posição.
        return animais.get(position);
    }

    /**
     * Obtém o ID do item na posição (índice) especificado.
     * @param position índice do item.
     * @return id do item no índice especificado.
     */
    @Override
    public long getItemId(int position) {
        // retorna o id da Animal na posição
        return animais.get(position).id;
    }

    /**
     * Essa função cria uma View para colocar na lista.
     * @param position posição do item para criar a view. e somo chama la no xml ? ou e direto n
     * @param convertView
     * @param parent O layout ao qual será adicionado o item da lista.
     * @return View do item para mostrar na tela.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // inflar o layout do "item da lista"
        View view = this.activity.getLayoutInflater().inflate(R.layout.item_animal, parent, false);
        // Pegar a Animal para colocar nesse item da lista.
        Animal animal = this.animais.get(position);

        // Agora encontrar os campos do layout item_Animal
        TextView nome = (TextView) view.findViewById(R.id.txt_nome);
        TextView dataNasc = (TextView) view.findViewById(R.id.txt_data_nasc);

        // Preencher os campos do layout com a informação da Animal
        nome.setText(animal.nome);
        descricao.setText(animal.data_nasc);
        return view;
    }
}
