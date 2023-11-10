package amazing;
public class PaqueteEspecial extends Paquete{
    private double porcentajeAdicional;
    private int valorAdicional;
    public PaqueteEspecial(String direccion, int volumen, double precio,
            double porcentajeAdicional, int valorAdicional) {
        super( volumen, precio);
        this.porcentajeAdicional = porcentajeAdicional;
        this.valorAdicional = valorAdicional;
    }
    public double obtenerPorcentajeAdicional() {
        return porcentajeAdicional/100;
    }

    public int obtenerValorAdicional() {
        return valorAdicional;
    }

    /* OPERACIONES */
    protected int adicionalAPagar(){
    	int valorAdicionalAPagar = 0;
        if(obtenerVolumen() >= 3000 && obtenerVolumen() < 5000) {
        	valorAdicionalAPagar=(obtenerValorAdicional());
        }
        else if (obtenerVolumen() > 5000) {
        	valorAdicionalAPagar=(obtenerValorAdicional()  * 2);
        }
        return valorAdicionalAPagar;
    }
    @Override
    public double costoFinal(){
    	double precioBaseConPorcentaje = obtenerPrecio() + (obtenerPrecio() * (obtenerPorcentajeAdicional()));
    	precioBaseConPorcentaje += adicionalAPagar();
    	return precioBaseConPorcentaje;    	    	
    }
}
