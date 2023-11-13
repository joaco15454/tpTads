package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Transporte {

    protected String patente;
    protected int volumenMaximo;
    protected int volumenActual;
    protected double valorQueCobra;
    protected List<Paquete> paquetesCargados;

    public Transporte(String patente, int volumenMaximo, double valorQueCobra) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;
        this.volumenActual = 0;
        this.paquetesCargados = new ArrayList<>();
    }

    public void modificarVolumenActual(int volumenActual) {
        if (volumenActual > volumenMaximo) {
            throw new RuntimeException("Error, el volumen no puede superar " + volumenMaximo);
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
        return valorQueCobra;
    }

    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario || p instanceof PaqueteEspecial) && !paquetesCargados.contains(p) && !transporteLleno();
    }

    public void cargarPaquete(Paquete paquete) {
        if ((paquete.obtenerVolumen() + volumenActual) < volumenMaximo) {
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
        return paquetesCargados.size() > volumenMaximo;
    }

    public boolean transporteLleno() {
        return volumenActual == volumenMaximo;
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

    public boolean distintaPatente(Transporte t) {
    	return !patente.equals(t.patente);
    }

    public boolean mismaClase(Transporte t) {
    	return getClass().equals(t.getClass());
    }
    public boolean distintaCantidadPaquetes(Transporte t) {
    	return paquetesCargados.size()!= t.paquetesCargados.size();
    }

	public boolean cargaIdentica(Transporte t) {
		boolean paquetesIguales = true;
		if(distintaCantidadPaquetes(t)) {
			return false;
		}
		for(Paquete p1 : paquetesCargados) {
			paquetesIguales &= existePaqueteIgual(p1,t.paquetesCargados);
		}
		return paquetesIguales;
	}
	private boolean existePaqueteIgual(Paquete p, List<Paquete> cargaPaquetes) {
		for(Paquete p2 : cargaPaquetes) {
			if(p.mismoPrecio(p2) && p.mismoVolumen(p2)) {
				return true;
			}
		}
		return false;
	}
    
    public abstract void actualizarCostoEntrega();

    public String formatoEntrega(Pedido pedido, Paquete paquete) {
        String formato = " + [ %d - %d ] %s\n";
        return String.format(formato, pedido.obtenerNroPedido(), paquete.obtenerIdUnico(), pedido.obtenerDireccion());
    }

    @Override
    public String toString() {
        StringBuilder paquetesCargadosStr = new StringBuilder("[");

        if (!paquetesCargados.isEmpty()) {
        	for(Paquete p : paquetesCargados) {
        		paquetesCargadosStr.append(p.toString());
        	}
        }
        paquetesCargadosStr.append("]");
        return "Transporte: Patente=" + patente +
                ", VolumenMaximo=" + volumenMaximo +
                ", CostoDeEntrega=" + valorQueCobra +
                ", PaquetesCargados:" + paquetesCargadosStr.toString();
    }
}
