package Amazing;

public class Camion extends Transporte{
    
    private double valorAdicional;
    public Camion(String patente, int volumenMaximo, double valorQueCobra, double valorAdicional) {
        super(patente, volumenMaximo, valorQueCobra);
        this.valorAdicional = valorAdicional;
    }
    public double getValorAdicional() {
        return valorAdicional;
    }
    public void setValorAdicional(double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    
    
}