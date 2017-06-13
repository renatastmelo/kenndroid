package renata.kenndroid.persistencia;

import android.database.sqlite.SQLiteDatabase;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * classe que representa um animal
 */

public class Animal {
    public long id;
    public String nome;
    public String data_nasc;
    public int pai;
    public int mae;
    public int canil;
    public int comprador;
    public String data_venda;
    public ByteBuffer foto;

    public static final String TABLE_NAME = "animal";

    public static final String SQL_CREATE_TABLE =
        "CREATE TABLE Animal (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "nome			TEXT		NOT NULL," +
            "data_nasc		TEXT		NULL,"+
            "pai			INTEGER		NOT NULL,"+
            "mae			INTEGER		NOT NULL,"+
            "cad_canil			INTEGER		NOT NULL,"+
            "comprador		INTEGER		NULL,"+
            "data_venda		TEXT		NULL,"+
            "foto			BLOB		NULL"+
        ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Animal";

    public Animal()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Animal carregar(SQLiteDatabase db, long id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void tudo(SQLiteDatabase db, List<Animal> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Animal> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }

}

