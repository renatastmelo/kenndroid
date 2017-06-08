package renata.kenndroid;

import android.database.sqlite.SQLiteDatabase;

import java.nio.ByteBuffer;
import java.util.ArrayList;

/**
 * Classe que representa um Canil
 */

public class Canil {
        public int id;
        public String nome;
        public String cidade;
        public String estado;
        public String endereco;
        public String complemento;
        public String ponto_ref;
        public int cep;
        public String data_fundacao;
        public String cnpj;
        public String razao_social;
        public String fone1;
        public String fone2;
        public String email;

    public static final String TABLE_NAME ="Canil";

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE Canil (" +
                "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
                "nome			TEXT		NOT NULL," +
                "estado			TEXT		NULL," +
                "cidade			TEXT		NULL," +
                "endereco		TEXT		NULL," +
                "complemento	TEXT		NULL," +
                "ponto_ref		TEXT		NULL," +
                "cep			INTEGER		NULL," +
                "data_fundacao	TEXT		NULL," +
                "cnpj			TEXT		NULL," +
                "razao_social	TEXT		NULL," +
                "fone1			TEXT		NULL," +
                "fone2			TEXT		NULL," +
                "email			TEXT		NULL," +
            ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Canil";


    public void save(SQLiteDatabase db)
    {
        // TODO: esse método vai salvar o registro no banco
    }

    public static Canil load(SQLiteDatabase db, int id)
    {
        // TODO: esse método vai ler e retornar o registro do banco
        return null;
    }

    public static ArrayList<Canil> all(SQLiteDatabase db)
    {
        // TODO: esse método vai ler e retornar TODOS os registros do banco em uma lista.
        return null;
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Canil> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }
}
