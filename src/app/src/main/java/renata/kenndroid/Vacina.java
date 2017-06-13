package renata.kenndroid;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Classe que representa uma Vacina.
 */

public class Vacina {
    public long id;
    public String nome;
    public String descricao;

    public static final String TABLE_NAME ="Vacina";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Vacina (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "nome			TEXT		NOT NULL," +
            "descricao		TEXT		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Vacina";


    // Construtor
    public Vacina()
    {
        // Setando id=0 inicialmente, para caso o usuário não sete o id.
        this.id = 0;
    }

    public void save(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.nome != null) valores.put("nome", this.nome);
        if (this.descricao != null) valores.put("descricao", this.descricao);


        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long idnovo = db.insertWithOnConflict("vacina", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (idnovo == -1) {
            db.update("vacina", valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = idnovo;
        }
    }


    public static Vacina load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Vacina> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Vacina> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


