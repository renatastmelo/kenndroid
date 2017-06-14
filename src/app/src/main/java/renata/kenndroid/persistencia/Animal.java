package renata.kenndroid.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * classe que representa um animal
 */

public class Animal {
    public long id;
    public String nome;
    public String data_nasc;
    public Animal pai;
    public Animal mae;
    public Canil canil;
    public Cliente comprador;
    public String data_venda;
    public Bitmap foto;

    public static final String TABLE_NAME = "animal";

    public static final String SQL_CREATE_TABLE =
        "CREATE TABLE Animal (" +
            "id				INTEGER		PRIMARY KEY		AUTOINCREMENT," +
            "nome			TEXT		NOT NULL," +
            "data_nasc		TEXT		NULL,"+
            "pai			INTEGER		NULL,"+
            "mae			INTEGER		NULL,"+
            "canil		    INTEGER		NULL,"+
            "comprador		INTEGER		NULL,"+
            "data_venda		TEXT		NULL,"+
            "foto			BLOB		NULL"+
        ")";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS Animal";

    public Animal()
    {
        this.id = 0;
    }

    public void salvar(SQLiteDatabase db)
    {
        ContentValues valores = new ContentValues();

        if (this.nome != null) valores.put("nome", this.nome);
        if (this.data_nasc != null) valores.put("data_nasc", this.data_nasc);
        if (this.pai != null) valores.put("pai", this.pai.id);
        if (this.mae != null) valores.put("mae", this.mae.id);
        if (this.canil != null) valores.put("canil", this.canil.id);
        if (this.comprador != null) valores.put("comprador", this.comprador.id);
        if (this.data_venda != null) valores.put("data_venda", this.data_venda);
        if (this.foto != null) {
            // Converter foto para byte array no formato JPEG
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            foto.compress(Bitmap.CompressFormat.PNG, 100, stream);
            valores.put("foto", stream.toByteArray());
        }

        if (this.id != 0) {
            valores.put("id", this.id);
        }

        long idnovo = db.insertWithOnConflict(TABLE_NAME, null, valores, SQLiteDatabase.CONFLICT_IGNORE);

        if (idnovo == -1) {
            db.update(TABLE_NAME, valores, "id=?", new String[] { Long.toString(this.id) });
        }else {
            this.id = idnovo;
        }
    }

    public static Animal lerItem(SQLiteDatabase db, Cursor resp, int profundidade)
    {
        Animal item = new Animal();
        item.id = resp.getLong(resp.getColumnIndex("id"));
        item.nome = resp.getString(resp.getColumnIndex("nome"));
        item.data_nasc = resp.getString(resp.getColumnIndex("data_nasc"));
        if (profundidade > 0) {
            if (!resp.isNull(resp.getColumnIndex("pai"))) {
                Long idpai = resp.getLong(resp.getColumnIndex("pai"));
                item.pai = Animal.carregar(db, idpai, profundidade - 1);
            }
            if (!resp.isNull(resp.getColumnIndex("mae"))) {
                Long idmae = resp.getLong(resp.getColumnIndex("mae"));
                item.mae = Animal.carregar(db, idmae, profundidade - 1);
            }
        }
        if (!resp.isNull(resp.getColumnIndex("canil"))) {
            Long idcanil = resp.getLong(resp.getColumnIndex("canil"));
            item.canil = Canil.carregar(db, idcanil);
        }

        if (!resp.isNull(resp.getColumnIndex("comprador"))) {
            Long idcomprador = resp.getLong(resp.getColumnIndex("comprador"));
            item.comprador = Cliente.carregar(db, idcomprador);
        }
        item.data_venda = resp.getString(resp.getColumnIndex("data_venda"));
        if (!resp.isNull(resp.getColumnIndex("foto"))) {
            byte[] fotoPng = resp.getBlob(resp.getColumnIndex("foto"));
            item.foto = BitmapFactory.decodeByteArray(fotoPng, 0, fotoPng.length);
        }
        return item;
    }

    public static void deletar(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME,                // Nome da tabela
                "id=?",                                 // Condições do WHERE para apagar (apenas id)
                new String[] { String.valueOf(id) });   // Valor das condições acima (apenas id)
    }

    public static Animal carregar(SQLiteDatabase db, long id, int profundidade)
    {
        Cursor resposta = db.query(TABLE_NAME,      // Nome da tabela
                null,                               // Colunas pra retornar (null=todas)
                "id=?",                             // Colunas de condição (apenas id)
                new String[] {String.valueOf(id)},  // Valores de condição (id)
                null,                               // Colunas para Agrupar (não utilizado)
                null,                               // Condição de valor Agrupado (não utilizado)
                null);

        // Se não obtive resposta retorne null
        if (resposta.getCount() <= 0)
        {
            resposta.close();
            return null;
        }

        // Ler primeiro e único item
        resposta.moveToFirst();
        Animal item = lerItem(db, resposta, profundidade);
        resposta.close();
        return item;
    }

    public static void tudo(SQLiteDatabase db, List<Animal> lista)
    {
        Cursor resposta = db.query(TABLE_NAME,   // Nome da tabela
                null,                            // Colunas pra retornar (null=todas)
                null,                            // Colunas de condição (não utilizado)
                null,                            // Valores de condição (não utilizado)
                null,                            // Colunas para Agrupar (não utilizado)
                null,                            // Condição de valor Agrupado (não utilizado)
                null);                           // Ordenação

        // Ir para o começo da resposta (primeira linha)
        if (resposta.moveToFirst() == false) {
            // Se não tiver primeiro item (nao tem nenhum item)
            // Retornar a lista vazia
            resposta.close();
            return;
        }

        // Ler linha por linha
        while (!resposta.isAfterLast())
        {
            Animal item = lerItem(db, resposta, 1);
            lista.add(item);
            resposta.moveToNext();
        }
        resposta.close();
    }

    //TODO: conferir no requisito quais as pesqueisas especificadas
    public static ArrayList<Animal> searchByName(SQLiteDatabase db, String name)
    {
        // TODO: esse método vai pesquisar todos os itens cujo nome possui o texto indicado
        return null;
    }

}

