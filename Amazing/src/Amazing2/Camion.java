package Amazing.Amazing;

import java.util.Iterator;
import java.util.List;

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

    @Override
    public List<String> cargarPaquetes2(Pedido pedido){
        List<String> listaPaquetesCargados = new List<String>();
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if((paquete instanceof PaqueteEspecial) && (paquete.getVolumen() > 2000)){
                cargarPaquete(paquete);
                String paqueteCargado = "+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion();    
                listaPaquetesCargados.add(paqueteCargado);
            }
        }
        return listaPaquetesCargados;
    }
    
    public void costoViaje(){
        double costo = getValorQueCobra() + (paquetesCargados.size() * getValorAdicional());
        setValorQueCobra(costo);
    }

}