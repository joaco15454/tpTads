package amazing;

public class Comun extends Transporte {

    private int limitePaquetes;

    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }

    public void setLimitePaquetes(int limitePaquetes) {
    	if(limitePaquetes < 0) {
    		throw new RuntimeException("Error, el limite de paquetes debe ser positivo.");
    	}
        this.limitePaquetes = limitePaquetes;
    }

    public int getLimitePaquetes() {
        return limitePaquetes;
    }

    @Override
    public boolean transporteLleno() {
        return ((paquetesCargados.size() == getLimitePaquetes()) || (getVolumenActual() == getVolumenMaximo()));
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteOrdinario) && !transporteLleno() && !getPaquetesCargados().contains(p) && p.getVolumen() < 2000;
    }
}