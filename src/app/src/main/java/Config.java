package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Classe que representa a configuração.
 */

public class Config {
    public int id;
    public int id_Canil;
    public String senha;

    public static final String TABLE_NAME ="Vacina";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Config (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "id_Canil		INTEGER		NOT NULL," +
                "senha			TEXT		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Config";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Config load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Config> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Config> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


