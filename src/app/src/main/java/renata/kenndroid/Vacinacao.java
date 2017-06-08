package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


/**
 * Classe que representa uma Vacinacao.
 */

public class Vacinacao {
    public int id;
    public int id_Animal;
    public int id_Vacina;
    public int id_Veterinario;
    public int id_Clinica;
    public String data;
    public String marca;
    public String validade;
    public String lote;
    public String reacoes_adversas;
    public int valor;
    public String observacoes;

    public static final String TABLE_NAME ="Vacinacao";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Vacinacao" +
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
                    "observacoes		TEXT		NULL," +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Clinica";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Vacinacao load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Vacinacao> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Vacinacao> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}


