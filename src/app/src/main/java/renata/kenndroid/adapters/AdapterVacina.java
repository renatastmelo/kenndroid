package renata.kenndroid.adapters;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import renata.kenndroid.R;
import renata.kenndroid.persistencia.Vacina;

/**
 * Created by Renata on 13/06/2017.
 */

public class AdapterVacina extends BaseAdapter {

    // Lista de vacinas para usar
    private final List<Vacina> vacinas;

    // Activity (janela) que vai usar a lista
    private final Activity activity;

    /**
     * Constructor do adapter.
     * @param vacinas Lista de vacinas que será usada
     * @param act Activity que usará o adapter.
     */
    public AdapterVacina(List<Vacina> vacinas, Activity act) {
        this.vacinas = vacinas;
        this.activity = act;
    }

    /**
     * Tem de retornar o número de itens na lista.
     * @return número de itens na lista
     */
    @Override
    public int getCount() {
        // retornar o tamanho da lista de vacinas
        return vacinas.size();
    }

    /**
     * Obtém um item da lista e retorna.
     * @param position posição do item na lista (índice).
     * @return retorna o item de indice especificado na lista.
     */
    @Override
    public Object getItem(int position) {
        // usa a função get da lista pra pegar o item na posição.
        return vacinas.get(position);
    }

    /**
     * Obtém o ID do item na posição (índice) especificado.
     * @param position índice do item.
     * @return id do item no índice especificado.
     */
    @Override
    public long getItemId(int position) {
        // retorna o id da vacina na posição
        return vacinas.get(position).id;
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
        View view = this.activity.getLayoutInflater().inflate(R.layout.item_vacina, parent, false);
        // Pegar a vacina para colocar nesse item da lista.
        Vacina vacina = this.vacinas.get(position);

        // Agora encontrar os campos do layout item_vacina
        TextView nome = (TextView) view.findViewById(R.id.txt_nome);
        TextView descricao = (TextView) view.findViewById(R.id.txt_descricao);

        // Preencher os campos do layout com a informação da vacina
        nome.setText(vacina.nome);
        descricao.setText(vacina.descricao);

        return view;
    }
}
