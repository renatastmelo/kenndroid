package renata.kenndroid.persistencia;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Clinica.
 */

public class Clinica {
    public int id;
    public String cnpj;
    public String nome;
    public String razao_social;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public int cep;
    public String fone1;
    public String fone2;
    public String email;

    public static final String TABLE_NAME ="Clinica";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Clinica (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "cnpj			TEXT		NULL," +
            "nome			TEXT		NOT NULL," +
            "razao_social	TEXT		NULL," +
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
            "DROP TABLE IF EXISTS Clinica";


    public Clinica()
    {
        this.id = 0;
    }

    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Clinica load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void all(SQLiteDatabase db, List<Clinica> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Clinica> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


