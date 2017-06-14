package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Acasalamento entre animais.
 */

public class Cruzamento {
    public long id;
    public Animal macho;
    public Animal femea;
    public String data;
    public String nome_local;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public Integer cep;
    public Boolean sucesso;

    public static final String TABLE_NAME = "cruzamento";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Cruzamento (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "macho			INTEGER		NOT NULL," +
                "femea			INTEGER		NOT NULL," +
                "data			TEXT		NULL," +
                "nome_local		TEXT		NULL," +
                "cidade			TEXT		NULL," +
                "estado			TEXT		NULL," +
                "endereco		TEXT		NULL," +
                "complemento	TEXT		NULL," +
                "ponto_ref		TEXT		NULL," +
                "cep			INTEGER		NULL," +
                "sucesso		INTEGER		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Cruzamento";

    public Cruzamento()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.macho != null) valores.put("macho", this.macho.id);
        if (this.femea != null) valores.put("femea", this.femea.id);
        if (this.data != null) valores.put("data", this.data);
        if (this.nome_local != null) valores.put("nome_local", this.nome_local);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.endereco != null) valores.put("endereco", this.endereco);
        if (this.complemento != null) valores.put("complemento", this.complemento);
        if (this.ponto_ref != null) valores.put("pont_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
        if (this.sucesso != null) valores.put("sucesso", this.sucesso ? 1 : 0);

        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long idnovo = db.insertWithOnConflict(TABLE_NAME, null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (idnovo == -1) {
            db.update(TABLE_NAME, valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = idnovo;
        }
    }

    public static Cruzamento lerItem(SQLiteDatabase db, Cursor resp)
    {
        Cruzamento item = new Cruzamento();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.macho = Animal.carregar(db, resp.getLong(resp.getColumnIndex("macho")), 0);
        item.femea = Animal.carregar(db, resp.getLong(resp.getColumnIndex("femea")), 0);
        item.data = resp.getString(resp.getColumnIndex("data"));
        item.nome_local = resp.getString(resp.getColumnIndex("nome_local"));
        item.cidade = resp.getString(resp.getColumnIndex("cidade"));
        item.estado = resp.getString(resp.getColumnIndex("estado"));
        item.endereco = resp.getString(resp.getColumnIndex("endereco"));
        item.complemento = resp.getString(resp.getColumnIndex("complemento"));
        item.ponto_ref = resp.getString(resp.getColumnIndex("ponto_ref"));
        item.cep = resp.getInt(resp.getColumnIndex("cep"));
        item.sucesso = (resp.getInt(resp.getColumnIndex("sucesso")) == 1);
        return item;
    }

    public static Cruzamento carregar(SQLiteDatabase db, long id)
    {
        Cursor resposta = db.query(TABLE_NAME,   // Nome da tabela
                null,                                       // Colunas pra retornar (null=todas)
                "id=?",                                     // Colunas de condição (apenas id)
                new String[] {String.valueOf(id)},          // Valores de condição (id)
                null,                                       // Colunas para Agrupar (não utilizado)
                null,                                       // Condição de valor Agrupado (não utilizado)
                null);

        // Se não obtive resposta retorne null
        if (resposta.getCount() <= 0)
        {
            resposta.close();
            return null;
        }

        // Ler primeiro e único item
        resposta.moveToFirst();
        Cruzamento item = lerItem(db, resposta);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Cruzamento> lista)
    {
        Cursor resposta = db.query(TABLE_NAME,   // Nome da tabela
                null,                            // Colunas pra retornar (null=todas)
                null,                            // Colunas de condição (não utilizado)
                null,                            // Valores de condição (não utilizado)
                null,                            // Colunas para Agrupar (não utilizado)
                null,                            // Condição de valor Agrupado (não utilizado)
                null);                           // Ordenação

        // Ir para o começo da resposta (primeira linha)
        if (resposta.moveToFirst() == false) {
            // Se não tiver primeiro item (nao tem nenhum item)
            // Retornar a lista vazia
            resposta.close();
            return;
        }

        // Ler linha por linha
        while (!resposta.isAfterLast())
        {
            Cruzamento item = lerItem(db, resposta);
            lista.add(item);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Cruzamento> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


