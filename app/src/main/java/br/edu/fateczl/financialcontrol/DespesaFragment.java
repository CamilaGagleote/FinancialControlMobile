package br.edu.fateczl.financialcontrol;
/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.edu.fateczl.financialcontrol.controller.DespesaController;
import br.edu.fateczl.financialcontrol.model.Despesa;
import br.edu.fateczl.financialcontrol.persistence.DespesaDao;
import br.edu.fateczl.financialcontrol.persistence.GenericDao;

public class DespesaFragment extends Fragment {

    private EditText etIdD, etCategoriaD, etValorD, etDataD, etFormaD;

    private Button btnBuscarD, btnInserirD, btnEditarD, btnListarD, btnExcluirD;

    private TextView tvListarD;

    private View view;

    private DespesaController despesaController;

    public DespesaFragment() {
       super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_despesa, container, false);
        etIdD = view.findViewById(R.id.etIdD);
        etCategoriaD = view.findViewById(R.id.etCategoriaD);
        etValorD = view.findViewById(R.id.etValorD);
        etDataD = view.findViewById(R.id.etDataD);
        etFormaD = view.findViewById(R.id.etFormaD);
        btnBuscarD = view.findViewById(R.id.btnBuscarD);
        btnInserirD = view.findViewById(R.id.btnInserirD);
        btnEditarD = view.findViewById(R.id.btnEditarD);
        btnListarD = view.findViewById(R.id.btnListarD);
        btnExcluirD = view.findViewById(R.id.btnExcluirD);
        tvListarD = view.findViewById(R.id.tvListarD);
        tvListarD.setMovementMethod(new ScrollingMovementMethod());

        GenericDao gDao = new GenericDao(getContext());
        DespesaDao despesaDao = new DespesaDao(gDao.getWritableDatabase());
        despesaController = new DespesaController(despesaDao);

        btnInserirD.setOnClickListener(op -> inserirDespesa());
        btnEditarD.setOnClickListener(op -> editarDespesa());
        btnExcluirD.setOnClickListener(op -> excluirDespesa());
        btnBuscarD.setOnClickListener(op -> buscarDespesa());
        btnListarD.setOnClickListener(op -> listarDespesas());
        return view;
    }

    private void inserirDespesa() {
        int id = Integer.parseInt(etIdD.getText().toString());
        String categoria = etCategoriaD.getText().toString();
        Float valor = Float.valueOf(etValorD.getText().toString());
        String data = etDataD.getText().toString();
        String forma = etFormaD.getText().toString();

        Despesa despesa = new Despesa();
        despesa.setId(id);
        despesa.setCategoria(categoria);
        despesa.setValor(valor);
        despesa.setData(data);
        despesa.setFormaPag(forma);
        try{
            despesaController.inserir(despesa);
            limparCampos();
            Toast.makeText(getContext(), "Despesa inserida com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao inserir despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void editarDespesa() {
        int id = Integer.parseInt(etIdD.getText().toString());
        String categoria = etCategoriaD.getText().toString();
        Float valor = Float.valueOf(etValorD.getText().toString());
        String data = etDataD.getText().toString();
        String forma = etFormaD.getText().toString();

        Despesa despesa = new Despesa();
        despesa.setId(id);
        despesa.setCategoria(categoria);
        despesa.setValor(valor);
        despesa.setData(data);
        despesa.setFormaPag(forma);

        try{
            despesaController.editar(despesa);
            limparCampos();
            Toast.makeText(getContext(), "Despesa inserida com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao inserir despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private void excluirDespesa() {
        int id = Integer.parseInt(etIdD.getText().toString());

        try{
            Despesa despesa = new Despesa();
            despesa.setId(id);
            despesaController.excluir(despesa);
            limparCampos();
            Toast.makeText(getContext(), "Despesa excluída com sucesso!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getContext(), "Erro ao excluir despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void buscarDespesa() {
        int id = Integer.parseInt(etIdD.getText().toString());

        try{
            Despesa despesa = despesaController.buscar(id);
            if(despesa != null){
                etIdD.setText(String.valueOf(despesa.getId()));
                etCategoriaD.setText(despesa.getCategoria());
                etValorD.setText(String.valueOf(despesa.getValor()));
                etDataD.setText(despesa.getData());
                etFormaD.setText(despesa.getFormaPag());
            }else{
                Toast.makeText(getContext(), "Despesa não encontrada!", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Erro ao buscar despesa: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


    }

    private void listarDespesas() {
        try{
            List<Despesa> despesas = despesaController.listar();
            StringBuilder sb = new StringBuilder();
            for( Despesa despesa : despesas){
                sb.append(despesa.toString()).append("\n");
            }
            tvListarD.setText(sb.toString());
        }catch(Exception e){
            Toast.makeText(getContext(), "Erro ao listar despesas: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void limparCampos() {
        etIdD.setText("");
        etCategoriaD.setText("");
        etValorD.setText("");
        etDataD.setText("");
        etFormaD.setText("");
    }

}
