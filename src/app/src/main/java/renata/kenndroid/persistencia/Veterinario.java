package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Veterinario.
 */

public class Veterinario {
    public long id;
    public String cpf;
    public String nome;
    public String especialidade;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public Integer cep;
    public String fone1;
    public String fone2;
    public String email;

    public static final String TABLE_NAME = "Veterinario";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Veterinario (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "cpf			TEXT		NULL," +
                "nome			TEXT		NOT NULL," +
                "especialidade	TEXT		NULL," +
                "cidade			TEXT		NULL," +
                "estado			TEXT		NULL," +
                "endereco		TEXT		NULL," +
                "complemento	TEXT		NULL," +
                "ponto_ref		TEXT		NULL," +
                "cep			INTEGER		NULL," +
                "fone1			TEXT		NULL," +
                "fone2			TEXT		NULL," +
                "email			TEXT		NULL" +
                ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Veterinario";

    public Veterinario()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.cpf != null) valores.put("cpf", this.cpf);
        if (this.nome != null) valores.put("nome", this.nome);
        if (this.especialidade != null) valores.put("especialidade", this.especialidade);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.endereco != null) valores.put("endereco", this.endereco);
        if (this.complemento != null) valores.put("complemento", this.complemento);
        if (this.ponto_ref != null) valores.put("ponto_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
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

    public static Veterinario lerItem(Cursor resp)
    {
        Veterinario item = new Veterinario();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.cpf = resp.getString(resp.getColumnIndex("cpf"));
        item.nome = resp.getString(resp.getColumnIndex("nome"));
        item.especialidade = resp.getString(resp.getColumnIndex("especialidade"));
        item.cidade = resp.getString(resp.getColumnIndex("cidade"));
        item.estado = resp.getString(resp.getColumnIndex("estado"));
        item.endereco = resp.getString(resp.getColumnIndex("endereco"));
        item.complemento = resp.getString(resp.getColumnIndex("complemento"));
        item.ponto_ref = resp.getString(resp.getColumnIndex("ponto_ref"));
        item.cep = resp.getInt(resp.getColumnIndex("cep"));
        item.fone1 = resp.getString(resp.getColumnIndex("fone1"));
        item.fone2 = resp.getString(resp.getColumnIndex("fone2"));
        item.email = resp.getString(resp.getColumnIndex("email"));
        return item;
    }

    public static void deletar(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME,                       // Nome da tabela
            "id=?",                                 // Condições do WHERE para apagar (apenas id)
            new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Veterinario carregar(SQLiteDatabase db, long id)
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
        Veterinario item = lerItem(resposta);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Veterinario> lista)
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
            Veterinario item = lerItem(resposta);
            lista.add(item);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Veterinario> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


