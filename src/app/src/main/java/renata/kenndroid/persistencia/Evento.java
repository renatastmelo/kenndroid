package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Evento.
 */

public class Evento {
    public long id;
    public Integer tipo;
    public String nome;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public Integer cep;
    public String fone;
    public String email;
    public String organizacao;
    public String data;

    public static final String TABLE_NAME = "evento";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE evento (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "tipo			INTEGER		NULL," +
            "nome			TEXT		NOT NULL," +
            "cidade			TEXT		NULL," +
            "estado			TEXT		NULL," +
            "endereco		TEXT		NULL," +
            "complemento	TEXT		NULL," +
            "ponto_ref		TEXT		NULL," +
            "cep			INTEGER		NULL," +
            "fone			TEXT		NULL," +
            "email			TEXT		NULL," +
            "organizacao	TEXT		NULL," +
            "data 			TEXT		NULL" +
            ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS evento";


    public Evento()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.tipo != null) valores.put("tipo", this.tipo);
        if (this.nome != null) valores.put("nome", this.nome);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.endereco != null) valores.put("endereco", this.endereco);
        if (this.complemento != null) valores.put("complemento", this.complemento);
        if (this.ponto_ref != null) valores.put("ponto_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
        if (this.fone != null) valores.put("fone", this.cep);
        if (this.email != null) valores.put("email", this.email);
        if (this.organizacao != null) valores.put("organizacao", this.organizacao);
        if (this.data != null) valores.put("data", this.data);

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

    public static Evento lerItem(Cursor resp)
    {
        Evento item = new Evento();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.tipo = resp.getInt(resp.getColumnIndex("tipo"));
        item.nome = resp.getString(resp.getColumnIndex("nome"));
        item.cidade = resp.getString(resp.getColumnIndex("cidade"));
        item.estado = resp.getString(resp.getColumnIndex("estado"));
        item.endereco = resp.getString(resp.getColumnIndex("endereco"));
        item.complemento = resp.getString(resp.getColumnIndex("complemento"));
        item.ponto_ref = resp.getString(resp.getColumnIndex("ponto_ref"));
        item.cep = resp.getInt(resp.getColumnIndex("cep"));
        item.fone = resp.getString(resp.getColumnIndex("fone"));
        item.organizacao = resp.getString(resp.getColumnIndex("organizacao"));
        item.email = resp.getString(resp.getColumnIndex("email"));
        item.data = resp.getString(resp.getColumnIndex("data"));
        return item;
    }

    public static void deletar(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME,                // Nome da tabela
                "id=?",                                 // Condições do WHERE para apagar (apenas id)
                new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Evento carregar(SQLiteDatabase db, long id)
    {
        Cursor resposta = db.query(TABLE_NAME,      // Nome da tabela
                null,                               // Colunas pra retornar (null=todas)
                "id=?",                             // Colunas de condição (apenas id)
                new String[] {String.valueOf(id)},  // Valores de condição (id)
                null,                               // Colunas para Agrupar (não utilizado)
                null,                               // Condição de valor Agrupado (não utilizado)
                null);

        // Se não obtive resposta retorne null
        if (resposta.getCount() <= 0)
        {
            resposta.close();
            return null;
        }

        // Ler primeiro e único item
        resposta.moveToFirst();
        Evento item = lerItem(resposta);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Evento> lista)
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
            Evento item = lerItem(resposta);
            lista.add(item);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Cliente> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


