package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import renata.kenndroid.KenndroidDb;


/**
 * Classe que representa uma Vacina.
 */

public class Vacina {
    public long id;
    public String nome;
    public String descricao;

    public static final String TABLE_NAME ="Vacina";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Vacina (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "nome			TEXT		NOT NULL," +
            "descricao		TEXT		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Vacina";


    // Construtor
    public Vacina()
    {
        // Setando id=0 inicialmente, para caso o usuário não sete o id.
        this.id = 0;
    }

    public void save(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.nome != null) valores.put("nome", this.nome);
        if (this.descricao != null) valores.put("descricao", this.descricao);


        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long idnovo = db.insertWithOnConflict("vacina", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (idnovo == -1) {
            db.update("vacina", valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = idnovo;
        }
    }

    public static Vacina lerItem(Cursor resp)
    {
        Vacina vac = new Vacina();
        vac.id = resp.getLong(resp.getColumnIndex("id"));
        vac.nome = resp.getString(resp.getColumnIndex("nome"));
        vac.descricao = resp.getString(resp.getColumnIndex("descricao"));
        return vac;
    }

    public static void del(SQLiteDatabase db, long id)
    {
        db.delete(Vacina.TABLE_NAME,                // Nome da tabela
            "id=?",                                 // Condições do WHERE para apagar (apenas id)
            new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Vacina load(SQLiteDatabase db, long id)
    {
        Cursor resposta = db.query(Vacina.TABLE_NAME,   // Nome da tabela
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

        resposta.moveToFirst();
        Vacina vac = lerItem(resposta);
        resposta.close();
        return vac;
    }

    public static void all(SQLiteDatabase db, List<Vacina> vacinas)
    {
        Cursor resposta = db.query(Vacina.TABLE_NAME,   // Nome da tabela
            null,                                       // Colunas pra retornar (null=todas)
            null,                                       // Colunas de condição (não utilizado)
            null,                                       // Valores de condição (não utilizado)
            null,                                       // Colunas para Agrupar (não utilizado)
            null,                                       // Condição de valor Agrupado (não utilizado)
            null);                                      // Ordenação

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
            Vacina vac = lerItem(resposta);
            vacinas.add(vac);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static List<Vacina> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }


}


