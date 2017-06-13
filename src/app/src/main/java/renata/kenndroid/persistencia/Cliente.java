package renata.kenndroid.persistencia;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa um Cliente.
 */

public class Cliente {
        public int id;
        public String nome;
        public String dia_nasc;
        public String mes_nasc;
        public String ano_nasc;
        public String cidade;
        public String estado;
        public String endereco;
        public String complemento;
        public String ponto_ref;
        public int cep;
        public String cpf;
        public String fone1;
        public String fone2;
        public String email;

    public static final String TABLE_NAME ="Cliente";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Cliente (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
               "nome			TEXT		NOT NULL," +
               " dia_nasc		TEXT		NULL," +
               "mes_nasc		TEXT		NULL," +
               "ano_nasc		TEXT		NULL," +
               "cidade			TEXT		NULL," +
               "estado			TEXT		NULL," +
               "endereco		TEXT		NULL," +
               "complemento		TEXT		NULL," +
               "ponto_ref		TEXT		NULL," +
               "cep				INTEGER		NULL," +
               "cpf				TEXT		NULL," +
               "fone1			TEXT		NULL," +
               "fone2			TEXT		NULL," +
               "email			TEXT		NULL" +
                    ")";


    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Canil";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Cliente load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static void all(SQLiteDatabase db, List<Cliente> lista)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Cliente> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}
