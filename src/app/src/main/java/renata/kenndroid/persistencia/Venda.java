package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Canil.
 */

public class Venda {
    public long id;
    public int id_Animal;
    public int id_Cliente;
    public String data;
    public Integer valor;
    public Integer valor_recebido;
    public String data_ult_pagamento;
    public String data_entrega_pedigree;
    public String forma_pagamento;
    public Integer nota_fiscal;
    public String observacoes;


    public static final String TABLE_NAME ="Venda";

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
                "forma_pagamento			TEXT		NULL," +
                "nota_fiscal				INTEGER		NULL," +
                "observacoes				TEXT		NULL" +
                ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Venda";

    public Venda()
    {
        this.id = 0;
    }

    // TODO: fazer as listagem de telas para obter ids extrageiros
    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.data != null) valores.put("data", this.data);
        if (this.valor != null) valores.put("valor", this.valor);
        if (this.valor_recebido != null) valores.put("valor recebido", this.valor_recebido);
        if (this.data_ult_pagamento != null)valores.put("Data do ultimo pagamento", this.data_ult_pagamento);
        if (this.data_entrega_pedigree != null)valores.put("Entrega do Pedigree", this.data_entrega_pedigree);
        if (this.forma_pagamento != null)valores.put("forma de pagamento", this.forma_pagamento);
        if (this.nota_fiscal != null) valores.put("Nota Fiscal", this.nota_fiscal);
        if (this.observacoes != null) valores.put("Observações", this.observacoes);


        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long id = db.insertWithOnConflict("venda", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (id == -1) {
            db.update("venda", valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = id;
        }
    }

    public static Venda carregar(SQLiteDatabase db, long id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void tudo(SQLiteDatabase db, List<Venda> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Venda> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }


}
