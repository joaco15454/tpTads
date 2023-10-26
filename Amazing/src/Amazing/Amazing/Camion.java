package Amazing.Amazing;

import java.util.Iterator;

public class Camion extends Transporte{
    
    private double valorAdicional;
    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional, int volumenActual) {
        super(patente, volumenMaximo, valorQueCobra, volumenActual);
        this.valorAdicional = valorAdicional;
    }
    public double getValorAdicional() {
        return valorAdicional;
    }
    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    // @Override
    // public void cargarPaquetes(Paquete paquete){
    //     if(!transporteLleno()){
    //         if(paquete.getVolumen() > 2000){
    //             paquetesCargados.add(paquete);
    //             System.out.println("+ [ " + paquete.nroPedido + "- " + paquete.getIdUnico() + " ] " + paquete.direccion);
    //         }else{
    //             throw new RuntimeException("Error, el volumen del paquete debe ser mayor a 2000.");            }
    //     }else{
    //         mensajeErrorTransporteLleno();
    //     }
    // }
    // cargarPaquetes2
    @Override
    public void cargarPaquetes(Pedido pedido){
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(!transporteLleno()){
                if(paquete.getVolumen() > 2000){ 
                    cargarPaquete(paquete);
                    System.out.println("+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
                }else{
                    throw new RuntimeException("Error, el volumen del paquete debe ser mayor a 2000.");
                }
            }else{
                transporteEstaLleno();
            }
        }
    }

    @Override
    public void cargarPaquetes2(Pedido pedido){
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(paquete.getVolumen() > 2000){
                cargarPaquete(paquete);
                System.out.println("+ [" + pedido.getNroPedido() + "  - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
            }else{
                throw new RuntimeException("Error, el volumen del paquete debe ser mayor a 2000.");
            }
        }
    }
    
    public void costoViaje(){
        double costo = getValorQueCobra() + (paquetesCargados.size() * getValorAdicional());
        setValorQueCobra(costo);
    }

}