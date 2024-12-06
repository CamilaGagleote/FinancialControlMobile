package br.edu.fateczl.financialcontrol.persistence;
/*
 *@author: <Camila>
 * @ra:<1110482312050>
 */

import java.sql.SQLException;
import java.util.List;

public interface ICRUDDao<T> {

    public void insert(T t) throws SQLException;
    public  int edit(T t) throws SQLException;
    public void delete (T t) throws SQLException;
    public T findOne (int id) throws SQLException;
    public List<T> findAll() throws SQLException;

}
