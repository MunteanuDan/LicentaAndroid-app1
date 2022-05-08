package eu.ase.firebaseauthlicenta.order.util;

import java.io.Serializable;

public class Comanda implements Serializable {

    private String id;
    private String denumire;
    private String cantitate;
    private String pret;

    public Comanda() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getCantitate() {
        return cantitate;
    }

    public void setCantitate(String cantitate) {
        this.cantitate = cantitate;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Comanda{" +
                "id='" + id + '\'' +
                ", denumire='" + denumire + '\'' +
                ", cantitate='" + cantitate + '\'' +
                ", pret='" + pret + '\'' +
                '}';
    }

}

