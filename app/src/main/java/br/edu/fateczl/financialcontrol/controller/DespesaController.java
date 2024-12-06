package br.edu.fateczl.financialcontrol.controller;
/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.financialcontrol.model.Despesa;
import br.edu.fateczl.financialcontrol.persistence.DespesaDao;


public class DespesaController implements IController<Despesa>{

    private DespesaDao despesaDao;

    public DespesaController(DespesaDao despesaDao){
        this.despesaDao = despesaDao;
    }

    @Override
    public void inserir(Despesa despesa) throws SQLException {
        despesaDao.insert(despesa);
        System.out.println("Despesa inserida: " + despesa);
    }

    @Override
    public void editar(Despesa despesa) throws SQLException {
        despesaDao.edit(despesa);
        System.out.println("Despesa editada: " + despesa);
    }

    @Override
    public void excluir(Despesa despesa) throws SQLException {
        despesaDao.delete(despesa);
        System.out.println("Despesa excluída: " + despesa);
    }

    @Override
    public Despesa buscar(int id) throws SQLException {
        return despesaDao.findOne(id);
    }

    @Override
    public List<Despesa> listar() throws SQLException {
        return despesaDao.findAll();
    }
}