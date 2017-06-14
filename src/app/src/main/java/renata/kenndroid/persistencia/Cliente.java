package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Cliente.
 */

public class Cliente {
        public long id;
        public String nome;
        public Integer dia_nasc;
        public Integer mes_nasc;
        public Integer ano_nasc;
        public String cidade;
        public String estado;
        public String endereco;
        public String complemento;
        public String ponto_ref;
        public Integer cep;
        public String cpf;
        public String fone1;
        public String fone2;
        public String email;

    public static final String TABLE_NAME = "cliente";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Cliente (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "nome			TEXT		NOT NULL," +
            "dia_nasc		INTEGER		NULL," +
            "mes_nasc		INTEGER		NULL," +
            "ano_nasc		INTEGER		NULL," +
            "cidade			TEXT		NULL," +
            "estado			TEXT		NULL," +
            "endereco		TEXT		NULL," +
            "complemento	TEXT		NULL," +
            "ponto_ref		TEXT		NULL," +
            "cep			INTEGER		NULL," +
            "cpf			TEXT		NULL," +
            "fone1			TEXT		NULL," +
            "fone2			TEXT		NULL," +
            "email			TEXT		NULL" +
            ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Canil";

    public Cliente()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.nome != null) valores.put("nome", this.nome);
        if (this.dia_nasc != null) valores.put("dia_nasc", this.dia_nasc);
        if (this.mes_nasc != null) valores.put("mes_nasc", this.mes_nasc);
        if (this.ano_nasc != null) valores.put("ano_nasc", this.ano_nasc);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.endereco != null) valores.put("endereco", this.endereco);
        if (this.complemento != null) valores.put("complemento", this.complemento);
        if (this.ponto_ref != null) valores.put("ponto_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
        if (this.cpf != null) valores.put("cpf", this.cpf);
        if (this.fone1 != null) valores.put("fone1", this.fone1);
        if (this.fone2 != null) valores.put("fone2", this.fone2);
        if (this.email != null) valores.put("email", this.email);

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

    public static Cliente lerItem(Cursor resp)
    {
        Cliente item = new Cliente();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.nome = resp.getString(resp.getColumnIndex("nome"));
        item.dia_nasc = resp.getInt(resp.getColumnIndex("dia_nasc"));
        item.mes_nasc = resp.getInt(resp.getColumnIndex("mes_nasc"));
        item.ano_nasc = resp.getInt(resp.getColumnIndex("ano_nasc"));
        item.cidade = resp.getString(resp.getColumnIndex("cidade"));
        item.estado = resp.getString(resp.getColumnIndex("estado"));
        item.endereco = resp.getString(resp.getColumnIndex("endereco"));
        item.complemento = resp.getString(resp.getColumnIndex("complemento"));
        item.ponto_ref = resp.getString(resp.getColumnIndex("ponto_ref"));
        item.cep = resp.getInt(resp.getColumnIndex("cep"));
        item.cpf = resp.getString(resp.getColumnIndex("cpf"));
        item.fone1 = resp.getString(resp.getColumnIndex("fone1"));
        item.fone2 = resp.getString(resp.getColumnIndex("fone2"));
        item.email = resp.getString(resp.getColumnIndex("email"));
        return item;
    }

    public static void deletar(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME,                // Nome da tabela
            "id=?",                                 // Condições do WHERE para apagar (apenas id)
            new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Cliente carregar(SQLiteDatabase db, long id)
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
        Cliente item = lerItem(resposta);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Cliente> lista)
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
            Cliente item = lerItem(resposta);
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
