package br.edu.fateczl.financialcontrol;
/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.financialcontrol.controller.DespesaController;
import br.edu.fateczl.financialcontrol.controller.ReceitaController;
import br.edu.fateczl.financialcontrol.model.Receita;
import br.edu.fateczl.financialcontrol.persistence.DespesaDao;
import br.edu.fateczl.financialcontrol.persistence.GenericDao;
import br.edu.fateczl.financialcontrol.persistence.ReceitaDao;

public class ReceitaFragment extends Fragment {

    private EditText etIdR, etFonteR, etValorR, etDataR, etCategoriaR;

    private Button btnBuscarR, btnInserirR, btnEditarR, btnListarR, btnExcluirR;

    private TextView tvListarR;

    private View view;

    private ReceitaController receitaController;

    public ReceitaFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_receita, container, false);

        etIdR = view.findViewById(R.id.etIdR);
        etFonteR = view.findViewById(R.id.etFonteR);
        etValorR = view.findViewById(R.id.etValorR);
        etDataR = view.findViewById(R.id.etDataR);
        etCategoriaR = view.findViewById(R.id.etCategoriaR);
        btnBuscarR = view.findViewById(R.id.btnBuscarR);
        btnInserirR = view.findViewById(R.id.btnInserirR);
        btnEditarR = view.findViewById(R.id.btnEditarR);
        btnListarR = view.findViewById(R.id.btnListarR);
        btnExcluirR = view.findViewById(R.id.btnExcluirR);
        tvListarR = view.findViewById(R.id.tvListarR);
        tvListarR.setMovementMethod(new ScrollingMovementMethod());

        GenericDao gDao = new GenericDao(getContext());
        ReceitaDao receitaDao = new ReceitaDao(gDao.getWritableDatabase());
        receitaController = new ReceitaController(receitaDao);

        btnInserirR.setOnClickListener(op -> inserirReceita());
        btnEditarR.setOnClickListener(op -> editarReceita());
        btnExcluirR.setOnClickListener(op -> excluirReceita());
        btnBuscarR.setOnClickListener(op -> buscarReceita());
        btnListarR.setOnClickListener(op -> listarReceitas());
        return view;
    }

    private void inserirReceita() {
        int id = Integer.parseInt(etIdR.getText().toString());
        String fonte = etFonteR.getText().toString();
        Float valor = Float.valueOf(etValorR.getText().toString());
        String data = etDataR.getText().toString();
        String categoria = etCategoriaR.getText().toString();

        Receita receita = new Receita();
        receita.setId(id);
        receita.setFonteR(fonte);
        receita.setValor(valor);
        receita.setData(data);
        receita.setCategoria(categoria);

        try{
            receitaController.inserir(receita);
            limparCampos();
            listarReceitas();
            Toast.makeText(getContext(), "Receita inserida com sucesso!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getContext(), "Erro ao inserir receita: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private void editarReceita() {
        int id = Integer.parseInt(etIdR.getText().toString());
        String fonte = etFonteR.getText().toString();
        Float valor = Float.valueOf(etValorR.getText().toString());
        String data = etDataR.getText().toString();
        String categoria = etCategoriaR.getText().toString();

        Receita receita = new Receita();
        receita.setId(id);
        receita.setFonteR(fonte);
        receita.setValor(valor);
        receita.setData(data);
        receita.setCategoria(categoria);

        try{
            receitaController.editar(receita);
            limparCampos();
            listarReceitas();
            Toast.makeText(getContext(), "Receita editada com sucesso!", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(getContext(), "Erro ao editar receita: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void excluirReceita() {
        int id = Integer.parseInt(etIdR.getText().toString());
        try{
            Receita receita = new Receita();
            receita.setId(id);
            receitaController.excluir(receita);
            limparCampos();
            listarReceitas();
            Toast.makeText(getContext(), "Receita excluída com sucesso!", Toast.LENGTH_SHORT).show();

        }catch(Exception e){
            Toast.makeText(getContext(), "Erro ao excluir receita: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void buscarReceita() {

        int id = Integer.parseInt(etIdR.getText().toString());
        try{
            Receita receita = receitaController.buscar(id);
            if(receita != null){
                etIdR.setText(String.valueOf(receita.getId()));
                etFonteR.setText(receita.getFonteR());
                etValorR.setText(String.valueOf(receita.getValor()));
                etDataR.setText(receita.getData());
                etCategoriaR.setText(receita.getData());
            }else{
                Toast.makeText(getContext(), "Receita não encontrada!", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e ){
            Toast.makeText(getContext(), "Erro ao buscar receita: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void listarReceitas() {
        try{
            List<Receita> receitas = receitaController.listar();
            StringBuilder sb = new StringBuilder();
            for( Receita receita: receitas){
                sb.append(receita.toString()).append("\n");
            }
            tvListarR.setText(sb.toString());
        }catch(Exception e){
            Toast.makeText(getContext(), "Erro ao listar receitas: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    private void limparCampos() {
        etIdR.setText("");
        etFonteR.setText("");
        etValorR.setText("");
        etDataR.setText("");
        etCategoriaR.setText("");
    }


}