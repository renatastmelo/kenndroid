package renata.kenndroid.persistencia;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Classe que representa uma relação de Animal com Evento.
 */

public class AnimalEvento {
    public int id;
    public int id_Evento;
    public String categoria;
    public String classificacao;
    public String pontos;
    public String juizes;
    public int penalizacao;
    public int percursos;

    public static final String TABLE_NAME ="AnimalEvento";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE AnimalEvento (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "id_Evento		INTEGER		NOT NULL," +
                "categoria		TEXT		NULL," +
                "classificacao	TEXT		NULL," +
                "pontos			TEXT		NULL," +
                "juizes			TEXT		NULL," +
                "penalizacao	INTEGER		NULL," +
                "percursos		INTEGER		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS AnimalEvento";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static AnimalEvento load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<AnimalEvento> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<AnimalEvento> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


