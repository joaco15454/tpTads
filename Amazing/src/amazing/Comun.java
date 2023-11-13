package amazing;

public class Comun extends Transporte {

    protected int limitePaquetes;

    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }

    @Override
    public boolean transporteLleno() {
        return ((super.paquetesCargados.size() == limitePaquetes) || (super.volumenActual == volumenMaximo));
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
    	if(p == null) {
    		throw new RuntimeException("Error, el paquete no existe.");
    	}
        return (p instanceof PaqueteOrdinario) && !transporteLleno() && !super.paquetesCargados.contains(p) && p.obtenerVolumen() < 2000;
    }

	@Override
	public void actualizarCostoEntrega() {}

}
