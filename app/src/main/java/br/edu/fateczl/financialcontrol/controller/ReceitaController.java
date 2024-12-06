package br.edu.fateczl.financialcontrol.controller;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import br.edu.fateczl.financialcontrol.model.Receita;
import br.edu.fateczl.financialcontrol.persistence.ReceitaDao;

public class ReceitaController implements IController<Receita>{

    private ReceitaDao receitaDao;

    public ReceitaController(ReceitaDao receitaDao){
        this.receitaDao = receitaDao;
    }


    @Override
    public void inserir(Receita receita) throws SQLException {
        receitaDao.insert(receita);
        System.out.println("Receita inserida: " + receita);

    }

    @Override
    public void editar(Receita receita) throws SQLException {
        receitaDao.edit(receita);
        System.out.println("Receita editada: " + receita);

    }

    @Override
    public void excluir(Receita receita) throws SQLException {
        receitaDao.delete(receita);
        System.out.println("Receita excluída: " + receita);
    }

    @Override
    public Receita buscar(int id) throws SQLException {
       return receitaDao.findOne(id);
    }

    @Override
    public List<Receita> listar() throws SQLException {
        return receitaDao.findAll();
    }
}
