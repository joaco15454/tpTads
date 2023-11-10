package amazing;

public class Comun extends Transporte {

    protected int limitePaquetes;

    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }

    @Override
    public boolean transporteLleno() {
        return ((paquetesCargados.size() == limitePaquetes) || (obtenerVolumenActual() == volumenMaximo));
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario) && !transporteLleno() && !obtenerPaquetesCargados().contains(p) && p.obtenerVolumen() < 2000;
    }

	@Override
	public void actualizarCostoEntrega() {}


}
