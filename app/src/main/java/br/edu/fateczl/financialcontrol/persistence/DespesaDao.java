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
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.financialcontrol.model.Despesa;

public class DespesaDao implements ICRUDDao<Despesa> {
    
    private final SQLiteDatabase database;

    public DespesaDao(SQLiteDatabase database) {
        this.database = database;
    }


    @Override
    public void insert(Despesa despesa) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("id", despesa.getId());
        values.put("categoria", despesa.getCategoria());
        values.put("valor", despesa.getValor());
        values.put("dataPagamento", despesa.getData());
        values.put("formaPagamento", despesa.getFormaPag());
        
        database.insert("despesa", null, values);
    }

    @Override
    public int edit(Despesa despesa) throws SQLException {
        ContentValues values = new ContentValues();
        values.put("id", despesa.getId());
        values.put("categoria", despesa.getCategoria());
        values.put("valor", despesa.getValor());
        values.put("dataPagamento", despesa.getData());
        values.put("formaPagamento", despesa.getFormaPag());
        
        return database.update("despesa", values, "id = ?", new String[]{String.valueOf(despesa.getId())});
        
    }

    @Override
    public void delete(Despesa despesa) throws SQLException {
        database.delete("despesa", "id = ?", new String[]{String.valueOf(despesa.getId())});

    }
    
    @SuppressLint("Range")
    @Override
    public Despesa findOne(int id) throws SQLException {
        Cursor cursor = database.query("despesa", null, "id = ?", new String[]{String.valueOf(id)}, null, null, null);
        
        if(cursor!= null && cursor.moveToFirst()){
            Despesa despesa = new Despesa();
            despesa.setId(cursor.getInt(cursor.getColumnIndex("id")));
            despesa.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
            despesa.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));
            despesa.setData(cursor.getString(cursor.getColumnIndex("dataPagamento")));
            despesa.setFormaPag(cursor.getString(cursor.getColumnIndex("formaPagamento")));
            cursor.close();
            return despesa;
        }
        return null;
    }
    @SuppressLint("Range")
    @Override
    public List<Despesa> findAll() throws SQLException {
        List<Despesa> despesas = new ArrayList<>();
        Cursor cursor = database.query("despesa", null, null, null, null,null,null);
        
        if(cursor != null && cursor.moveToFirst()){
            do{
                Despesa despesa = new Despesa();
                despesa.setId(cursor.getInt(cursor.getColumnIndex("id")));
                despesa.setCategoria(cursor.getString(cursor.getColumnIndex("categoria")));
                despesa.setValor(cursor.getFloat(cursor.getColumnIndex("valor")));
                despesa.setData(cursor.getString(cursor.getColumnIndex("dataPagamento")));
                despesa.setFormaPag(cursor.getString(cursor.getColumnIndex("formaPagamento")));
                despesas.add(despesa);
            }while(cursor.moveToNext());
            cursor.close();
        }
        return despesas;
    }
}