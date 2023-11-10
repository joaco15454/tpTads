package amazing;

public class Paquete{
    private static int contadorPedidos = 100;
    private int idUnico;
    private int volumen;
    private double precio;
    private String direccion;
    private Boolean entregado;

    /* CONSTRUCTOR, SETTERS AND GETTERS */
    public Paquete(String direccion ,int volumen, double precio) {
        this.idUnico = contadorPedidos++;
        this.volumen = volumen;
        this.precio = precio;
        this.direccion = direccion; 
        this.entregado = false;
    }
    
    public int obtenerIdUnico() {
        return idUnico;
    }

    public int obtenerVolumen() {
        return volumen;
    }

    public double obtenerPrecio() {
        return precio;
    }

    public String obtenerDireccion() {
        return direccion;
    }

    public Boolean paqueteFueEntregado() {
        return entregado;
    }

    public void modificarEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
    /* FIN CONSTRUCTOR, SETTERS AND GETTERS */
    /* OPERACIONES */
    
    protected void paqueteEntregado() {
        modificarEntregado(true);
    }
    protected boolean fueEntregado() {
        return paqueteFueEntregado();
    }
    
	protected double costoFinal() {
		return obtenerPrecio();
	}
    @Override 
    public String toString() {
    	return "[Paquete: \nId="+ obtenerIdUnico()+
    			"\nVolumen="+ obtenerVolumen()+
    			"\nPrecio="+ costoFinal()+
    			"\nDireccion="+obtenerDireccion() +"]";
    }
}