package br.edu.fateczl.financialcontrol.model;

/*
    *@author: <Camila>
    * @ra:<1110482312050>
*/

public class Despesa extends Pagamento{
        private String categoria;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "id = " + getId() +
                " categoria = " + categoria +
                " valor = R$" + getValor() +
                " data pagamento = " + getData() +
                " forma do pagamento = " + getFormaPag() + '\'';

    }
}
