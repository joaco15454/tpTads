package amazing;

import java.util.Iterator;

public class Camion extends Transporte{
    
    private double valorAdicional;
    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }
    public double getValorAdicional() {
        return valorAdicional;
    }
    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    @Override
    public void cargarPedido(Pedido pedido){
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if(paquete.getVolumen() > 2000){
                cargarPaquete(paquete);
                System.out.println("+ [" + pedido.getNroPedido() + "  - " + paquete.getIdUnico() + " ] " + pedido.getDireccion());
            }
        }
    }
    
    public void costoViaje(){
        double costo = getValorQueCobra() + (paquetesCargados.size() * getValorAdicional());
        setValorQueCobra(costo);
    }

}