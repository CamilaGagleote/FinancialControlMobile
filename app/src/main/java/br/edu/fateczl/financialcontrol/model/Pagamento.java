package br.edu.fateczl.financialcontrol.model;

/*
 *@author: <Camila>
 * @ra:<1110482312050>
*/

import java.util.Date;

public abstract class Pagamento {
    private int id;
    private float valor;
    private String data;
    private String formaPag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFormaPag() {
        return formaPag;
    }

    public void setFormaPag(String formaPag) {
        this.formaPag = formaPag;
    }

    @Override
    public String toString() {
        return "Pagamento: " +
                "id = " + id +
                ", valor = " + valor +
                ", data = " + data +
                ", formaPag = '" + formaPag + '\'';
    }
}
