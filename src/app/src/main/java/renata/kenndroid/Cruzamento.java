package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Classe que representa um Acasalamento entre animais.
 */

public class Cruzamento {
    public int id;
    public int macho;
    public int femea;
    public String data;
    public String nome_local;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public int cep;
    public String sucesso;

    public static final String TABLE_NAME ="Cruzamento";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Cruzamento (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "macho			INTEGER		NOT NULL," +
                "femea			INTEGER		NOT NULL," +
                "data			TEXT		NULL," +
                "nome_local		TEXT		NULL," +
                "cidade			TEXT		NULL," +
                "estado			TEXT		NULL," +
                "endereco		TEXT		NULL," +
                "complemento	TEXT		NULL," +
                "ponto_ref		TEXT		NULL," +
                "cep			INTEGER		NULL," +
                "sucesso		INTEGER		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Cruzamento";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Cruzamento load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Cruzamento> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Cruzamento> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


