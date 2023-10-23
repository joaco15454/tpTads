package Amazing;

import java.util.List;

public class Transporte{
    
    private String patente;
    private int volumenMaximo;
    private double valorQueCobra;
    private List<Paquete> paquetsCargados;
    
    public Transporte(String patente, int volumenMaximo, double valorQueCobra) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;
    }

    public String getPatente() {
        return patente;
    }

    public int getVolumenMaximo() {
        return volumenMaximo;
    }

    public double getValorQueCobra() {
        return valorQueCobra;
    }

    public List<Paquete> getPaquetsCargados() {
        return paquetsCargados;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setVolumenMaximo(int volumenMaximo) {
        this.volumenMaximo = volumenMaximo;
    }

    public void setValorQueCobra(double valorQueCobra) {
        this.valorQueCobra = valorQueCobra;
    }

    public void setPaquetsCargados(List<Paquete> paquetsCargados) {
        this.paquetsCargados = paquetsCargados;
    }

    
    
}