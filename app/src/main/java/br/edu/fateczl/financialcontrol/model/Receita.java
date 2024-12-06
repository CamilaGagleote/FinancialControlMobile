package br.edu.fateczl.financialcontrol.model;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
 */

public class Receita extends Pagamento{

    private String fonteR;
    private String categoria;

    public String getFonteR() {
        return fonteR;
    }

    public void setFonteR(String fonteR) {
        this.fonteR = fonteR;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Receita: " +
                "id = " + getId() +
                " fonte = " + fonteR +
                " valor = R$" + getValor() +
                " data recebimento = " + getData() +
                " categoria = " + categoria +
                 '\'';
    }
}
