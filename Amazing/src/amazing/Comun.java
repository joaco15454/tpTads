package amazing;

public class Comun extends Transporte {

    private int limitePaquetes;

    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }

    public int obtenerLimitePaquetes() {
        return limitePaquetes;
    }

    @Override
    public boolean transporteLleno() {
        return ((paquetesCargados.size() == obtenerLimitePaquetes()) || (obtenerVolumenActual() == obtenerVolumenMaximo()));
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario) && !transporteLleno() && !obtenerPaquetesCargados().contains(p) && p.obtenerVolumen() < 2000;
    }

	@Override
	public void actualizarCostoEntrega() {}


}
