package amazing;
public class PaqueteEspecial extends Paquete{
    private int porcentajeAdicional;
    private int valorAdicional;
    public PaqueteEspecial(String direccion, int volumen, double precio,
            int porcentajeAdicional, int valorAdicional) {
        super(direccion, volumen, precio);
        this.porcentajeAdicional = porcentajeAdicional;
        this.valorAdicional = valorAdicional;
    }
    public int getPorcentajeAdicional() {
        return porcentajeAdicional;
    }
    public void setPorcentajeAdicional(int porcentajeAdicional) {
    	if (porcentajeAdicional < 0) {
    		throw new RuntimeException("Error porcentaje negativo");
    	}
        this.porcentajeAdicional = porcentajeAdicional/100;
    }
    public int getValorAdicional() {
        return valorAdicional;
    }
    public void setValorAdicional(int valorAdicional) {
    	if (valorAdicional < 0) {
    		throw new RuntimeException("Error valor negativo negativo");
    	}
        this.valorAdicional = valorAdicional;
    }
    /* OPERACIONES */
    public int seSuperaElVolumen(){
    	int valorAdicionalAPagar = 0;
        if(getVolumen() > 3000 && getVolumen() < 5000) {
        	valorAdicionalAPagar=(getValorAdicional());
        }
        else if (getVolumen() > 5000) {
        	valorAdicionalAPagar=(getValorAdicional()  * 2);
        }
        return valorAdicionalAPagar;
    }
    @Override
    public double costoFinal(){
    	double precioBaseConPorcentaje = getPrecio() + (getPrecio() * getPorcentajeAdicional());
    	precioBaseConPorcentaje += seSuperaElVolumen();
    	return precioBaseConPorcentaje;
    	
    	
    	
    }
}
