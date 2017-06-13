package renata.kenndroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Renata on 12/06/2017.
 */

public class KenndroidDb extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Kenndroid.db";
    public static KenndroidDb instance = null;

    private KenndroidDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized KenndroidDb getInstance(Context context) {
        if (instance == null) {
            instance = new KenndroidDb(context);
        }
        return instance;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(Animal.SQL_CREATE_TABLE);
        db.execSQL(Cliente.SQL_CREATE_TABLE);
        db.execSQL(AnimalEvento.SQL_CREATE_TABLE);
        db.execSQL(Canil.SQL_CREATE_TABLE);
        db.execSQL(Clinica.SQL_CREATE_TABLE);
        db.execSQL(Cruzamento.SQL_CREATE_TABLE);
        db.execSQL(Vacina.SQL_CREATE_TABLE);
        db.execSQL(Vacinacao.SQL_CREATE_TABLE);
        db.execSQL(Venda.SQL_CREATE_TABLE);
        db.execSQL(Veterinario.SQL_CREATE_TABLE);
        db.execSQL(renata.kenndroid.Config.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Posso definir aqui COMO o banco de dados será atualizado de uma versão velha para uma versão nova.
        // Essa é a primeira versão então não há nada para atualizar.
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

}
