package amazing;

public class Camion extends Transporte {

    private double valorAdicional;

    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }

    public double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(double valorAdicional) {
    	if(valorAdicional < 0) {
    		throw new RuntimeException("Error, el valor adicional debe ser positivo.");
    	}
        this.valorAdicional = valorAdicional;
    }

    public void calcularCostoViaje() {
        double costo = getValorQueCobra() + (paquetesCargados.size() * getValorAdicional());
        setValorQueCobra(costo);
    }

    @Override
    public boolean seCumplenCondiciones(Paquete p) {
        return (p instanceof PaqueteEspecial) && !transporteLleno() && p.getVolumen() > 2000;
    }

}