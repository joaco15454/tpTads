package amazing;

import java.util.ArrayList;
import java.util.List;

public class Transporte {

    private String patente;
    private int volumenMaximo;
    private int volumenActual;
    private double valorQueCobra;
    protected List<Paquete> paquetesCargados;

    public Transporte(String patente, int volumenMaximo, double valorQueCobra) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;
        this.volumenActual = 0;
        this.paquetesCargados = new ArrayList<>(); 
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
    	if (volumenPaquete < 0) {
            throw new RuntimeException("Error, debe ser un numero positivo");
        }
        setVolumenActual(volumenActual + volumenPaquete);
    }

    public void setValorQueCobra(double valorQueCobra) {
    	if(valorQueCobra < 0) {
    		throw new RuntimeException("Error, el valor que cobra el transporte debe ser positivo.");
    	}
        this.valorQueCobra = valorQueCobra;
    }

    public double costoEntrega() {
        return getValorQueCobra();
    }

    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario || p instanceof PaqueteEspecial) && !getPaquetesCargados().contains(p) &&!transporteLleno();
    }

    public void cargarPaquete(Paquete paquete) {
        if ((paquete.getVolumen() + getVolumenActual()) < getVolumenMaximo()) {
            paquetesCargados.add(paquete);
            aumentarVolumen(paquete.getVolumen());
        }

    }
    public boolean transporteVacio(){
        return paquetesCargados.isEmpty();
    }

    public List<Paquete> listadoPaquetesAEntregar() {
        return paquetesCargados;
    }


    public boolean superaLimite() {
        return paquetesCargados.size() > getVolumenMaximo();
    }
 
    public boolean transporteLleno() {
        return getVolumenActual() == getVolumenMaximo();
    }

    public void transporteEstaLleno() {
        if (transporteLleno()) {
            throw new RuntimeException("Error, el transporte esta lleno.");
        }
    }
    @Override
    public String toString() {
        StringBuilder paquetesCargadosStr = new StringBuilder("[");
        
        List<Paquete> paquetes = getPaquetesCargados();
        if (!paquetes.isEmpty()) {
            for (int i = 0; i < paquetes.size() - 1; i++) {
                paquetesCargadosStr.append(paquetes.get(i).getIdUnico()).append(", ");
            }
            paquetesCargadosStr.append(paquetes.get(paquetes.size() - 1).getIdUnico());
        }
        
        paquetesCargadosStr.append("]");

        return "Transporte: Patente=" + getPatente() +
                ", VolumenMaximo=" + getVolumenMaximo() +
                ", CostoDeEntrega=" + getValorQueCobra() +
                ", PaquetesCargados:" + paquetesCargadosStr.toString();
    }

}