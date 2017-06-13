package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Classe que representa um Canil
 */

public class Canil {
        public long id;
        public String nome;
        public String cidade;
        public String estado;
        public String endereco;
        public String complemento;
        public String ponto_ref;
        public Integer cep;
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
                "email			TEXT		NULL" +
            ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Canil";


    // Construtor
    public Canil()
    {
        // Setando id=0 inicialmente, para caso o usuário não sete o id.
        this.id = 0;
    }

    public void save(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.nome != null) valores.put("nome", this.nome);
        if (this.estado != null) valores.put("estado", this.estado);
        if (this.cidade != null) valores.put("cidade", this.cidade);
        if (this.endereco != null)valores.put("endereco", this.endereco);
        if (this.complemento != null)valores.put("complemento", this.complemento);
        if (this.ponto_ref != null)valores.put("ponto_ref", this.ponto_ref);
        if (this.cep != null) valores.put("cep", this.cep);
        if (this.data_fundacao != null) valores.put("data_fundacao", this.data_fundacao);
        if (this.cnpj != null) valores.put("cnpj", this.cnpj);
        if (this.razao_social != null) valores.put("razao_social", this.razao_social);
        if (this.fone1 != null) valores.put("fone1", this.fone1);
        if (this.fone2 != null) valores.put("fone2", this.fone2);
        if (this.email != null) valores.put("email", this.email);

        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long id = db.insertWithOnConflict("canil", null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (id == -1) {
            db.update("canil", valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = id;
        }
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
