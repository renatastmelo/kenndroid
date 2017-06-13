package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa um Acasalamento entre animais.
 */

public class Cruzamento {
    public long id;
    public Animal macho;
    public Animal femea;
    public String data;
    public String nome_local;
    public String cidade;
    public String estado;
    public String endereco;
    public String complemento;
    public String ponto_ref;
    public Integer cep;
    public Boolean sucesso;

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

    public Cruzamento()
    {
        this.id = 0;
    }

    public void save(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.macho != null) valores.put("macho", this.macho.id);
        if (this.femea != null) valores.put("femea", this.femea.id);
        if (this.data != null) valores.put("data", this.data);
        if (this.nome_local != null) valores.put("nome_local", this.nome_local);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.endereco != null) valores.put("endereco", this.endereco);
        if (this.complemento != null) valores.put("complemento", this.complemento);
        if (this.ponto_ref != null) valores.put("pont_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
        if (this.sucesso != null) valores.put("sucesso", this.sucesso ? 1 : 0);

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

    public static Cruzamento load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void all(SQLiteDatabase db, List<Cruzamento> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Cruzamento> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


