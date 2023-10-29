package amazing;
public class PaqueteEspecial extends Paquete{
    private double porcentajeAdicional;
    private int valorAdicional;
    public PaqueteEspecial(String direccion, int volumen, double precio,
            double porcentajeAdicional, int valorAdicional) {
        super(direccion, volumen, precio);
        this.porcentajeAdicional = porcentajeAdicional;
        this.valorAdicional = valorAdicional;
    }
    public double getPorcentajeAdicional() {
        return porcentajeAdicional/100;
    }
    public void setPorcentajeAdicional(int porcentajeAdicional) {
    	if (porcentajeAdicional < 0) {
    		throw new RuntimeException("Error porcentaje negativo");
    	}
        this.porcentajeAdicional = porcentajeAdicional;
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
    public int adicionalAPagar(){
    	int valorAdicionalAPagar = 0;
        if(getVolumen() >= 3000 && getVolumen() < 5000) {
        	valorAdicionalAPagar=(getValorAdicional());
        }
        else if (getVolumen() > 5000) {
        	valorAdicionalAPagar=(getValorAdicional()  * 2);
        }
        return valorAdicionalAPagar;
    }
    @Override
    public double costoFinal(){
    	double precioBaseConPorcentaje = getPrecio() + (getPrecio() * (getPorcentajeAdicional()));
    	precioBaseConPorcentaje += adicionalAPagar();
    	return precioBaseConPorcentaje;
    	
    	
    	
    }
}
