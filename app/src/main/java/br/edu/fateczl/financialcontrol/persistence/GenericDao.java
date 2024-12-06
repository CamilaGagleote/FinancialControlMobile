package br.edu.fateczl.financialcontrol.persistence;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GenericDao extends SQLiteOpenHelper {

    private static final String DATABASE = "FINANCE.DB";

    private static final int DATABASE_VER = 1;

    private static final String CREATE_TABLE_RECEITA =
            "CREATE TABLE receita( " +
                    "id INT NOT NULL PRIMARY KEY," +
                    "fonte VARCHAR(20) NOT NULL, "+
                    "valor DECIMAL(8,2) NOT NULL, " +
                    "dataRecebimento DATE NOT NULL, " +
                    "categoria VARCHAR(20) NOT NULL);";
    private static final String CREATE_TABLE_DESPESA =
            "CREATE TABLE despesa( " +
                    "id INT NOT NULL PRIMARY KEY," +
                    "categoria VARCHAR(20) NOT NULL, "+
                    "valor DECIMAL(8,2) NOT NULL, " +
                    "dataPagamento DATE NOT NULL, " +
                    "formaPagamento VARCHAR(20) NOT NULL);";


    public GenericDao(Context context) {
        super(context, DATABASE, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_RECEITA);
        sqLiteDatabase.execSQL(CREATE_TABLE_DESPESA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {
        if(novaVersao > antigaVersao){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS receita");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS despesa");
            onCreate(sqLiteDatabase);
        }

    }
}
