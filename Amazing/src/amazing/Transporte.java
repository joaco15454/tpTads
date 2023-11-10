package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Transporte {

    private String patente;
    private int volumenMaximo;
    private int volumenActual;
    protected double valorQueCobra;
    protected List<Paquete> paquetesCargados;

    public Transporte(String patente, int volumenMaximo, double valorQueCobra) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;
        this.volumenActual = 0;
        this.paquetesCargados = new ArrayList<>();
    }

    public String obtenerPatente() {
        return patente;
    }

    public int obtenerVolumenMaximo() {
        return volumenMaximo;
    }

    public double obtenerValorQueCobra() {
        return valorQueCobra;
    }

    public List<Paquete> obtenerPaquetesCargados() {
        return paquetesCargados;
    }

    public int obtenerVolumenActual() {
        return volumenActual;
    }

    public void modificarVolumenActual(int volumenActual) {
        if (volumenActual > obtenerVolumenMaximo()) {
            throw new RuntimeException("Error, el volumen no puede superar " + obtenerVolumenMaximo());
        }
        this.volumenActual = volumenActual;
    }

    public void aumentarVolumen(int volumenPaquete) {
        if (volumenPaquete < 0) {
            throw new RuntimeException("Error, debe ser un numero positivo");
        }
        modificarVolumenActual(volumenActual + volumenPaquete);
    }

    public double costoEntrega() {
        return obtenerValorQueCobra();
    }

    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario || p instanceof PaqueteEspecial) && !obtenerPaquetesCargados().contains(p) && !transporteLleno();
    }

    public void cargarPaquete(Paquete paquete) {
        if ((paquete.obtenerVolumen() + obtenerVolumenActual()) < obtenerVolumenMaximo()) {
            paquetesCargados.add(paquete);
            aumentarVolumen(paquete.obtenerVolumen());
        }

    }

    public boolean transporteVacio() {
        return paquetesCargados.isEmpty();
    }

    public List<Paquete> listadoPaquetesAEntregar() {
        return paquetesCargados;
    }

    public boolean superaLimite() {
        return paquetesCargados.size() > obtenerVolumenMaximo();
    }

    public boolean transporteLleno() {
        return obtenerVolumenActual() == obtenerVolumenMaximo();
    }

    public void transporteEstaLleno() {
        if (transporteLleno()) {
            throw new RuntimeException("Error, el transporte esta lleno.");
        }
    }

    public  List<String> cargarPedido(Pedido pedido){
    		transporteEstaLleno();
    	    List<String> listaPaquetesCargados = new ArrayList<>();
    	    List<Paquete> paquetesEspeciales = new ArrayList<>();
    	    List<Paquete> paquetesOrdinarios = new ArrayList<>();
    	    HashMap<Integer, Paquete> carrito = pedido.obtenerCarrito();
    	    
    	    for (Paquete paquete : carrito.values()) {
    	        if (seCumplenCondiciones(paquete)) {
    	            if (paquete instanceof PaqueteEspecial) {
    	                paquetesEspeciales.add(paquete);
    	            } else if (paquete instanceof PaqueteOrdinario){
    	                paquetesOrdinarios.add(paquete);
    	            }
    	        }
    	    }
    	    for (Paquete paquete : paquetesEspeciales) {
    	        cargarPaquete(paquete);
    	        String datosEntrega = formatoEntrega(pedido, paquete);
    	        listaPaquetesCargados.add(datosEntrega);
    	        carrito.remove(paquete.obtenerIdUnico()); // Elimina el paquete del carrito
    	    }
    	    for (Paquete paquete : paquetesOrdinarios) {
    	        cargarPaquete(paquete);
    	        String datosEntrega = formatoEntrega(pedido, paquete);
    	        listaPaquetesCargados.add(datosEntrega);
    	        carrito.remove(paquete.obtenerIdUnico()); // Elimina el paquete del carrito
    	    }
    	    actualizarCostoEntrega();
    	    return listaPaquetesCargados;
    	}

    public abstract void actualizarCostoEntrega();

    public String formatoEntrega(Pedido pedido, Paquete paquete) {
        String formato = " + [ %d - %d ] %s\n";
        return String.format(formato, pedido.obtenerNroPedido(), paquete.obtenerIdUnico(), pedido.obtenerDireccion());
    }

    @Override
    public String toString() {
        StringBuilder paquetesCargadosStr = new StringBuilder("[");

        List<Paquete> paquetes = obtenerPaquetesCargados();
        if (!paquetes.isEmpty()) {
        	for(Paquete p : paquetesCargados) {
        		paquetesCargadosStr.append(p.toString());
        	}
        }
        paquetesCargadosStr.append("]");
        return "Transporte: Patente=" + obtenerPatente() +
                ", VolumenMaximo=" + obtenerVolumenMaximo() +
                ", CostoDeEntrega=" + obtenerValorQueCobra() +
                ", PaquetesCargados:" + paquetesCargadosStr.toString();
    }
}
