 package Amazing;

import java.util.Iterator;

public class Comun extends Transporte{
    
    private int limitePaquetes;
    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes, int volumenActual) {
        super(patente, volumenMaximo, valorQueCobra, volumenActual);
        this.limitePaquetes = limitePaquetes;
    }
    public void setLimitePaquetes(int limitePaquetes) {
        this.limitePaquetes = limitePaquetes;
    }
    public int getLimitePaquetes() {
        return limitePaquetes;
    }

    // @Override
    // public void cargarPaquetes(Paquete paquete){
    //     if(!transporteLleno()){
    //         if(paquete.getVolumen() < 2000){
    //             paquetesCargados.add(paquete);
    //             aumentarVolumen(getVolumenActual(), paquete.getVolumen());
    //             System.out.println("+ [ " + paquete.nroPedido + "- " + paquete.getIdUnico() + " ] " + paquete.direccion);
    //         }else{
    //             throw new RuntimeException("Error, el volumen del paquete debe ser menor a 2000.");            }
    //     }else{
    //         mensajeErrorTransporteLleno();
    //     }
    // }

    @Override
    public void cargarPaquetes(Pedido pedido){
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(!transporteLleno()){
                if(paquete.getVolumen() < 2000){ 
                    cargarPaquete(paquete);
                    System.out.println("+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
                }else{
                    throw new RuntimeException("Error, el volumen del paquete debe ser menor a 2000.");
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
            if(paquete.getVolumen() < 2000){
                cargarPaquete(paquete);
                System.out.println("+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
            }else{
                throw new RuntimeException("Error, el volumen del paquete debe ser menor a 2000.");
            }
        }
    }
 }