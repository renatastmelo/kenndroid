package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe que representa um Canil.
 */

public class Venda {
    public int id;
    public int id_Animal;
    public int id_Cliente;
    public String data;
    public int valor;
    public int valor_recebido;
    public String data_ult_pagamento;
    public String data_entrega_pedigree;
    public int forma_pagamento;
    public int nota_fiscal;
    public String observacoes;


    public static final String TABLE_NAME ="Venda";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Venda (" +
                "id						    INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "id_Animal				    INTEGER		NOT NULL," +
                " id_Cliente				INTEGER		NOT NULL," +
                "data					    TEXT		NULL," +
                "valor					    INTEGER		NULL," +
                "valor_recebido			    INTEGER		NULL," +
                "data_ult_pagamento		    TEXT		NULL," +
                " data_entrega_pedigree	    TEXT		NULL," +
                "forma_pagamento			INTEGER		NULL," +
                "nota_fiscal				INTEGER		NULL," +
                "observacoes				TEXT		NULL," +
                    ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Venda";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Venda load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Venda> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Venda> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }


}
