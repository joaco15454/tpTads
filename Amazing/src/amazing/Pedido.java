package amazing;

import java.util.HashMap;
import java.util.Iterator;



public class Pedido {
    private int dni;
    private int nroPedido;
    private String direccion;
    private String nombreCliente;
    private HashMap<Integer,Paquete> carrito = new HashMap<>();
    private boolean estaCerrado;
    private static int contadorPedidos = 1000;
    

    /* CONSTRUCTOR  */ 
    public Pedido(int nroPedido, String direccion, String nombreDeCliente, int dni, boolean estaCerrado) {
        this.nroPedido = contadorPedidos++; // numero unico
        this.direccion = direccion;
        this.nombreCliente = nombreDeCliente;
    
        
        this.dni = dni;
        this.estaCerrado = estaCerrado;
    }    
    /* FIN CONSTRUCTOR */
    /* GETTERS AND SETTERS */
   
    /* FIN GETTERS AND SETTERS */
    /* OPERACIONES */

    //Agrega un producto ordinario
    protected  int agregarProductoCarrito(int volumen, double precio, int costoEnvio) {
        /*COMPLETAR */
         
        PaqueteOrdinario p = new PaqueteOrdinario(direccion,volumen,precio, costoEnvio);
        agregarProductoOrdinario(p.obtenerIdUnico(), p);
        return p.obtenerIdUnico();
    }
    protected void agregarProductoOrdinario (Integer id, PaqueteOrdinario p) {
        if (paqueteEnCarrito(id)) {
            throw new RuntimeException("Error, ya existe un paquete con esa id.");
         }else if(estaCerrado == true) {
        	 throw new RuntimeException("Error, el pedido esta cerrado por lo que no puede modificarse.");
         }
        carrito.put(id, p); 
    }



    //Agrega producto especial
    protected  int agregarProductoCarrito( int volumen, double precio, int porcentaje, int adicional) {
        /*COMPLETAR */

        PaqueteEspecial p = new PaqueteEspecial(direccion,volumen,precio, porcentaje,adicional);
        agregarProductoEspecial(p.obtenerIdUnico(), p);
        return p.obtenerIdUnico();
    }
    protected void agregarProductoEspecial (Integer id, PaqueteEspecial p) {
        if (paqueteEnCarrito(id)) {
            throw new RuntimeException("Error, ya existe un paquete con esa id o el paquete cerro.");
         }else if(estaCerrado == true) {
             throw new RuntimeException("Error, el pedido esta cerrado por lo que no puede modificarse.");        	 
         }
        carrito.put(id, p);
    }

    protected  boolean eliminarProductoCarrito (int id) {
        if (!paqueteEnCarrito(id)) {
            throw new RuntimeException("Error, el paquete que desea borrar no esta en el carrito");
        }else if(estaCerrado == true) {
            throw new RuntimeException("Error, el pedido esta cerrado por lo que no puede modificarse.");        	 
        }
        carrito.remove(id);
        return true;
    }

    
    public double calcularValorAPagar() {
        double valor = 0.0;
        Iterator <Integer> it = carrito.keySet().iterator();
        while (it.hasNext()) {
            int id = it.next();
            if (!carrito.get(id).fueEntregado()) {
            	 valor+= carrito.get(id).costoFinal();
            }
        }
        return valor;
    }
    
    /* FIN OPERACIONES */

    /* METODOS AUXILIARES  */
    public boolean paqueteEnCarrito (int id) {
        return !(obtenerPaquete(id) == null);
        
    }
    protected  boolean paqueteEntregado(int id) {
        return obtenerPaquete(id).paqueteFueEntregado();
    }
    protected  double calcularValorPaquete(int id) {
        return obtenerPaquete(id).obtenerPrecio();
    }
    public Paquete obtenerPaquete(int id) {
        return carrito.get(id);
    }

	public void finalizarPedido() {
		if (estaCerrado == true ) {
			throw new RuntimeException("Error, el pedido esta finalizado");
		}
		estaCerrado = true;
	}
	
	public boolean entregaPendiente() {
		for(Paquete p : carrito.values()) {
			if(!p.fueEntregado()) {
				return true;
			}
		}
		return false;
	}
	

    @Override
    public String toString() {
        StringBuilder carritoStr = new StringBuilder("[");
        if (!carrito.isEmpty()) {
	        for(Paquete p : carrito.values()) {
	        	carritoStr.append(p.toString());
	        }
        }
        carritoStr.append("]");
        return "Pedido: Id=" + nroPedido +
                ", Datos cliente: [Nombre=" + nombreCliente + ", Dni=" + dni  + ", Direccion=" + direccion + "]" +
                ", Carrito=" + carritoStr.toString();
    }

	public boolean noEstaEntregado() {
		return estaCerrado && entregaPendiente();
	}

	public Integer obtenerNroPedido() {
		return nroPedido;
	}

	public String obtenerNombreDeCliente() {
		return nombreCliente;
	}

	public boolean isEstaCerrado() {
		return estaCerrado;
	}

	public HashMap<Integer, Paquete> obtenerCarrito() {
		return carrito;
	}

	public String obtenerDireccion() {
		return direccion;
	}
	
}
