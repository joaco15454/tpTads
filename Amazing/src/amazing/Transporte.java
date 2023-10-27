package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class Transporte{
    
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
        if(volumenMaximo < 0){
            throw new RuntimeException("Error, tiene que ser mayor que 0.");
        }
        this.volumenMaximo = volumenMaximo;
    }

    public int getVolumenActual(){
        return volumenActual;
    }
    public void setVolumenActual(int volumenActual){
        if(volumenActual > getVolumenMaximo()){
            throw new RuntimeException("Error, el volumen no puede superar " + getVolumenMaximo());
        }
        this.volumenActual = volumenActual;
    }
    public void aumentarVolumen(int volumenPaquete){
        setVolumenActual(volumenActual + volumenPaquete);
    }

    public void setValorQueCobra(double valorQueCobra) {
        this.valorQueCobra = valorQueCobra;
    }

    public void cargarPedido(Pedido pedido){
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            cargarPaquete(paquete);
            System.out.println("+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
        }
    }
    public List<String> cargarPedido2(Pedido pedido){
        List<String> listaPaquetesCargados = new ArrayList<>();
        transporteEstaLleno();
        HashMap<Integer,Paquete> carrito = pedido.getCarrito();
        for(Paquete paquete : carrito.values()){
            if(paquete instanceof PaqueteOrdinario || paquete instanceof PaqueteEspecial){
                cargarPaquete(paquete);
                StringBuilder sb = new StringBuilder();
                sb.append("+ [");
                sb.append(pedido.getNroPedido());
                sb.append(" - ");
                sb.append(paquete.getIdUnico());
                sb.append(" ] ");
                sb.append(pedido.getDireccion());
                String paqueteCargado = sb.toString();
                listaPaquetesCargados.add(paqueteCargado);
                //carrito.remove(paquete.getIdUnico());
            }
        }
        return listaPaquetesCargados;
    }
    public void cargarPaquete(Paquete paquete){
        paquetesCargados.add(paquete);
        aumentarVolumen(paquete.getVolumen());
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