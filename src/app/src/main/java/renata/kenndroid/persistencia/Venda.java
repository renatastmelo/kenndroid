package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Canil.
 */

public class Venda {
    public long id;
    public Animal animal;
    public Cliente cliente;
    public String data;
    public Integer valor;
    public Integer valor_recebido;
    public String data_ult_pagamento;
    public String data_entrega_pedigree;
    public Integer forma_pagamento;
    public Integer nota_fiscal;
    public String observacoes;

    public static final String TABLE_NAME = "Venda";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Venda (" +
                "id						    INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "id_Animal				    INTEGER		NOT NULL," +
                "id_Cliente				INTEGER		NOT NULL," +
                "data					    TEXT		NULL," +
                "valor					    INTEGER		NULL," +
                "valor_recebido			    INTEGER		NULL," +
                "data_ult_pagamento		    TEXT		NULL," +
                "data_entrega_pedigree	    TEXT		NULL," +
                "forma_pagamento			INTEGER		NULL," +
                "nota_fiscal				INTEGER		NULL," +
                "observacoes				TEXT		NULL" +
                ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Venda";

    public Venda()
    {
        this.id = 0;
    }

    public static int getIdFormaPagamento(String forma)
    {
        if (forma.equals("Espécie")) {
            return 0;
        } else if (forma.equals("Débito")) {
            return 1;
        } else if (forma.equals("Crédito")) {
            return 2;
        } else if (forma.equals("Cheque")) {
            return 3;
        } else if (forma.equals("Transferência")) {
            return 4;
        } else return 5;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.animal != null) valores.put("id_Animal", this.animal.id);
        if (this.cliente != null) valores.put("id_Cliente", this.cliente.id);
        if (this.data != null) valores.put("data", this.data);
        if (this.valor != null) valores.put("valor", this.valor);
        if (this.valor_recebido != null) valores.put("valor_recebido", this.valor_recebido);
        if (this.data_ult_pagamento != null) valores.put("data_ult_pagamento", this.data_ult_pagamento);
        if (this.data_entrega_pedigree != null) valores.put("data_entrega_pedigree", this.data_entrega_pedigree);
        if (this.forma_pagamento != null) valores.put("forma_pagamento", this.forma_pagamento);
        if (this.nota_fiscal != null) valores.put("nota_fiscal", this.nota_fiscal);
        if (this.observacoes != null) valores.put("observacoes", this.observacoes);

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

    public static Venda lerItem(SQLiteDatabase db, Cursor resp)
    {
        Venda item = new Venda();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.animal = Animal.carregar(db, resp.getLong(resp.getColumnIndex("id_Animal")), 0);
        item.cliente = Cliente.carregar(db, resp.getLong(resp.getColumnIndex("id_Cliente")));
        item.data = resp.getString(resp.getColumnIndex("data"));
        item.valor = resp.getInt(resp.getColumnIndex("valor"));
        item.valor_recebido = resp.getInt(resp.getColumnIndex("valor_recebido"));
        item.data_ult_pagamento = resp.getString(resp.getColumnIndex("data_ult_pagamento"));
        item.data_entrega_pedigree = resp.getString(resp.getColumnIndex("data_entrega_pedigree"));
        item.forma_pagamento = resp.getInt(resp.getColumnIndex("forma_pagamento"));
        item.nota_fiscal = resp.getInt(resp.getColumnIndex("nota_fiscal"));
        item.observacoes = resp.getString(resp.getColumnIndex("observacoes"));
        return item;
    }

    public static void deletar(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME,                // Nome da tabela
                "id=?",                                 // Condições do WHERE para apagar (apenas id)
                new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Venda carregar(SQLiteDatabase db, long id)
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
        Venda item = lerItem(db, resposta);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Venda> lista)
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
            Venda item = lerItem(db, resposta);
            lista.add(item);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Venda> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }


}
