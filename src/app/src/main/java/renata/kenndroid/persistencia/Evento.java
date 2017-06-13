package renata.kenndroid.persistencia;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Evento.
 */

public class Evento {
    public int id;
    public int tipo;
    public String nome;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public int cep;
    public String fone;
    public String email;
    public String organizacao;
    public String data;

    public static final String TABLE_NAME ="Evento";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Evento (" +
                    "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                    "tipo			INTEGER		NULL," +
                    "nome			TEXT		NOT NULL," +
                    "cidade			TEXT		NULL," +
                    "estado			TEXT		NULL," +
                    "endereco		TEXT		NULL," +
                    "complemento	TEXT		NULL," +
                    "ponto_ref		TEXT		NULL," +
                    "cep			INTEGER		NULL," +
                    "fone			TEXT		NULL," +
                    "email			TEXT		NULL," +
                    "organizacao	TEXT		NULL," +
                    "data 			TEXT		NULL" +
                        ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Evento";


    public Evento()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Evento carregar(SQLiteDatabase db, long id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void all(SQLiteDatabase db, List<Evento> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Evento> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


