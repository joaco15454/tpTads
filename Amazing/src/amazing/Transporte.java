package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transporte {

    private String patente;
    private int volumenMaximo;
    private int volumenActual = 0;
    private double valorQueCobra;
    protected List<Paquete> paquetesCargados;

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

    public List<Paquete> getPaquetesCargados() {
        return paquetesCargados;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setVolumenMaximo(int volumenMaximo) {
        if (volumenMaximo < 0) {
            throw new RuntimeException("Error, tiene que ser mayor que 0.");
        }
        this.volumenMaximo = volumenMaximo;
    }

    public int getVolumenActual() {
        return volumenActual;
    }

    public void setVolumenActual(int volumenActual) {
        if (volumenActual > getVolumenMaximo()) {
            throw new RuntimeException("Error, el volumen no puede superar " + getVolumenMaximo());
        }
        this.volumenActual = volumenActual;
    }

    public void aumentarVolumen(int volumenPaquete) {
        setVolumenActual(volumenActual + volumenPaquete);
    }

    public void setValorQueCobra(double valorQueCobra) {
        this.valorQueCobra = valorQueCobra;
    }

    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario || p instanceof PaqueteEspecial) && !transporteLleno();
    }

    public void cargarPaquete(Paquete paquete, Pedido pedido) {
        paquetesCargados.add(paquete);
        aumentarVolumen(paquete.getVolumen());
        pedido.getCarrito().remove(paquete.getIdUnico());
    }

    public List<Paquete> listadoPaquetesAEntregar() {
        return paquetesCargados;
    }

    public double obtenerCostoViaje() {
        return getValorQueCobra();
    }

    public boolean superaLimite() {
        if (paquetesCargados.size() > getVolumenMaximo()) {
            return true;
        }
        return false;
    }

    public boolean transporteLleno() {
        return getVolumenActual() == getVolumenMaximo();
    }

    public void transporteEstaLleno() {
        if (transporteLleno()) {
            throw new RuntimeException("Error, el transporte esta lleno.");
        }
    }

}