package Amazing.Amazing;

import java.util.Iterator;
import java.util.List;

public class Transporte{
    
    private String patente;
    private int volumenMaximo;
    private int volumenActual;
    private double valorQueCobra;
    protected List<Paquete> paquetesCargados;
    
    public Transporte(String patente, int volumenMaximo, double valorQueCobra, int volumenActual) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;
        this.volumenActual = 0;
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
        this.volumenMaximo = volumenMaximo;
    }

    public int getVolumenActual(){
        return volumenActual;
    }
    public void setVolumenActual(int volumenActual){
        this.volumenActual = volumenActual;
    }
    public void aumentarVolumen(int volumenActual, int volumenPaquete){
        volumenActual += volumenPaquete;
    }

    public void setValorQueCobra(double valorQueCobra) {
        this.valorQueCobra = valorQueCobra;
    }

    public List<String> cargarPedido(Pedido pedido){
        List<String> listaPaquetesCargados = new List<String>();
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(paquete instanceof PaqueteOrdinario || paquete instanceof PaqueteEspecial){
                cargarPaquete(paquete);
                String paqueteCargado = "+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion(); 
                listaPaquetesCargados.add(paqueteCargado);
            }

        }
        return listaPaquetesCargados;
    }
    public void cargarPaquete(Paquete paquete){
        paquetesCargados.add(paquete);
        aumentarVolumen(getVolumenActual(), paquete.getVolumen());
    }

    public List<Paquete> listadoPaquetesAEntregar(){
        return paquetesCargados;
    }

    public double obtenerCostoViaje(){
        return getValorQueCobra();
    }

    public boolean superaLimite(){
        if(paquetesCargados.size() > getVolumenMaximo()){
            return true;
        }
        return false;
    }

    public boolean transporteLleno(){
        if(getVolumenActual() == getVolumenMaximo()){
            return true;
        }
        return false;
    }
    public void transporteEstaLleno(){
        if(transporteLleno()){
            throw new RuntimeException("Error, el transporte esta lleno.");
        }
    }
    
}