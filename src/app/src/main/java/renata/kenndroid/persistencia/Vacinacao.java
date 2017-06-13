package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe que representa uma Vacinacao.
 */

public class Vacinacao {
    public long id;
    public int id_Animal;
    public int id_Vacina;
    public int id_Veterinario;
    public int id_Clinica;
    public String data;
    public String marca;
    public String validade;
    public String lote;
    public String reacoes_adversas;
    public Integer valor;
    public String observacoes;

    public static final String TABLE_NAME ="Vacinacao";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Vacinacao (" +
                    "id					INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                    "id_Animal			INTEGER		NOT NULL," +
                    "id_Veterinario		INTEGER		NOT NULL," +
                    "id_Clinica			INTEGER		NOT NULL," +
                    "data 				TEXT		NOT NULL," +
                    "marca				TEXT		NULL," +
                    "validade			TEXT 		NULL," +
                    "lote				TEXT		NULL," +
                    "reacoes_adversas	TEXT		NULL," +
                    "valor 				INTEGER		NOT NULL," +
                    "observacoes		TEXT		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Clinica";

    public Vacinacao()
    {
        this.id = 0;
    }

    // TODO: fazer as listagem de telas para obter ids do itens selecionado pelo usuário
    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();


        if (this.data != null)valores.put("data", this.data);
        if (this.marca != null)valores.put("marca", this.marca);
        if (this.validade != null)valores.put("validade", this.validade);
        if (this.lote != null) valores.put("lote", this.lote);
        if (this.reacoes_adversas != null) valores.put("reacoes_adversas", this.reacoes_adversas);
        if (this.valor != null) valores.put("valor", this.valor);
        if (this.observacoes != null) valores.put("observacoes", this.observacoes);

        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long id = db.insertWithOnConflict("vacinacao", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (id == -1) {
            db.update("vacinacao", valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = id;
        }
    }

    public static Vacinacao carregar(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void tudo(SQLiteDatabase db, List<Vacinacao> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Vacinacao> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


