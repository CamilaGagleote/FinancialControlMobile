package br.edu.fateczl.financialcontrol.persistence;
/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.financialcontrol.model.Receita;

public class ReceitaDao implements ICRUDDao<Receita>{

    private SQLiteDatabase database;

    public ReceitaDao(SQLiteDatabase database){
        this.database = database;
    }

    @Override
    public void insert(Receita receita) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", receita.getId());
        contentValues.put("fonte", receita.getFonteR());
        contentValues.put("valor", receita.getValor());
        contentValues.put("dataRecebimento", String.valueOf(receita.getData()));
        contentValues.put("categoria", receita.getCategoria());

        database.insert("receita", null, contentValues);
    }

    @Override
    public int edit(Receita receita) throws SQLException {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", receita.getId());
        contentValues.put("fonte", receita.getFonteR());
        contentValues.put("valor", receita.getValor());
        contentValues.put("dataRecebimento", String.valueOf(receita.getData()));
        contentValues.put("categoria", receita.getCategoria());

        return database.update("receita", contentValues, "id = ?", new String[]{String.valueOf(receita.getId())});
    }

    @Override
    public void delete(Receita receita) throws SQLException {
        database.delete("receita","id = ?", new String[]{String.valueOf(receita.getId())});
    }

    @SuppressLint("Range")
    @Override
    public Receita findOne(int id) throws SQLException {
        Cursor cursor = database.query("receita", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()){
            Receita receita = new Receita();
            receita.setId(cursor.getInt(cursor.getColumnIndex("id")));
            receita.setFonteR(cursor.getString(cursor.getColumnIndex("fonte")));
            receita.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));
            receita.setData(cursor.getString(cursor.getColumnIndex("dataRecebimento")));
            receita.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
            cursor.close();
            return receita;

        }
        return null;

    }

    @SuppressLint("Range")
    @Override
    public List<Receita> findAll() throws SQLException {
        List<Receita> receitas = new ArrayList<>();
        Cursor cursor = database.query("receita", null, null,null, null, null, null, null);

        if(cursor != null && cursor.moveToFirst()){
            do{
                Receita receita = new Receita();
                receita.setId(cursor.getInt(cursor.getColumnIndex("id")));
                receita.setFonteR(cursor.getString(cursor.getColumnIndex("fonte")));
                receita.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));
                receita.setData(cursor.getString(cursor.getColumnIndex("dataRecebimento")));
                receita.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));

                receitas.add(receita);
            }while(cursor.moveToNext());

            cursor.close();
        }

        return receitas;
    }
}
