 package amazing;

import java.util.Iterator;

public class Comun extends Transporte{
    
    private int limitePaquetes;
    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }
    public void setLimitePaquetes(int limitePaquetes) {
        this.limitePaquetes = limitePaquetes;
    }
    public int getLimitePaquetes() {
        return limitePaquetes;
    }

    @Override
    public void cargarPedido(Pedido pedido){
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(paquete.getVolumen() < 2000){
                cargarPaquete(paquete);
                System.out.println("+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
            }
        }
    }
    @Override
    public boolean transporteLleno(){
        return ((paquetesCargados.size() == getLimitePaquetes()) && (getVolumenActual() == getVolumenMaximo()));
    }
 }