package br.edu.fateczl.financialcontrol.controller;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import java.sql.SQLException;
import java.util.List;

public interface IController<T>{
    public void inserir(T t) throws SQLException;
    public void editar (T t) throws SQLException;
    public void excluir (T t) throws SQLException;
    public T buscar(int id) throws SQLException;
    public List<T> listar() throws SQLException;
}
