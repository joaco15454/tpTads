 package Amazing.Amazing;

import java.util.Iterator;
import java.util.List;

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

    @Override
    public List<String> cargarPaquetes2(Pedido pedido){
        List<String> listaPaquetesCargados = new List<String>();
        transporteEstaLleno();
        Iterator <Integer> it = pedido.getCarrito().keySet().iterator();
        while(it.hasNext()){
            int id = it.next();
            Paquete paquete = pedido.obtenerPaquete(id);
            if((paquete instanceof PaqueteOrdinario) && (paquete.getVolumen() < 2000)){
                cargarPaquete(paquete);
                String paqueteCargado = "+ [" + pedido.getNroPedido() + " - " + paquete.getIdUnico() + " ] " + pedido.getDireccion();
                listaPaquetesCargados.add(paqueteCargado);
            }
        }
        return listaPaquetesCargados;
    }
 }