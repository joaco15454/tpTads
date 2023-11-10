package amazing;

public class Paquete{
    protected static int contadorPedidos = 100;
    protected int idUnico;
    protected int volumen;
    protected double precio;
 
    private Boolean entregado;

    /* CONSTRUCTOR, SETTERS AND GETTERS */
    public Paquete(int volumen, double precio) {
        this.idUnico = contadorPedidos++;
        this.volumen = volumen;
        this.precio = precio;
        
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

    

    public Boolean paqueteFueEntregado() {
        return entregado;
    }

    
    /* FIN CONSTRUCTOR, SETTERS AND GETTERS */
    /* OPERACIONES */
    
    protected void paqueteEntregado() {
        entregado	 = true;
    }
    protected boolean fueEntregado() {
        return paqueteFueEntregado();
    }
    
	protected double costoFinal() {
		return obtenerPrecio();
	}
    @Override 
    public String toString() {
    	return " [" + obtenerIdUnico()+ "] ";
    }
    public boolean mismoPrecio ( Paquete p2) {
    	return (obtenerPrecio() == p2.obtenerPrecio()) ;
    		
    }
    public boolean mismoVolumen ( Paquete p2) {
    	return (obtenerVolumen() == p2.obtenerVolumen()); 		
    	
    }
}