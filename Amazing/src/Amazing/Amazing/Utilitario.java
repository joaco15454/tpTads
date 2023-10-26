package Amazing;

public class Utilitario extends Transporte {
    private double valorExtra;

    public Utilitario(String patente, int volumenMaximo, double valorQueCobra, double valorExtra, int volumenActual) {
        super(patente, volumenMaximo, valorQueCobra, volumenActual);
        this.valorExtra = valorExtra;
    }

    public double getValorExtra() {
        return valorExtra;
    }

    public void setValorExtra(double valorExtra) {
        this.valorExtra = valorExtra;
    }
    
    public void calcularCostoViaje(){
        //return paquetesCargados.size() > 10 ? (getValorQueCobra() + getValorExtra()) : getValorQueCobra();
        double costo = paquetesCargados.size() > 10 ? (getValorQueCobra() + getValorExtra()) : getValorQueCobra();
        setValorQueCobra(costo);
    }
    
}
