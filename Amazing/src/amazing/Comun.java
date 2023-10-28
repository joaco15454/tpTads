package amazing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Comun extends Transporte {

    private int limitePaquetes;

    public Comun(String patente, int volumenMaximo, double valorQueCobra, int limitePaquetes) {
        super(patente, volumenMaximo, valorQueCobra);
        this.limitePaquetes = limitePaquetes;
    }

    public void setLimitePaquetes(int limitePaquetes) {
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
        return (p instanceof PaqueteOrdinario) && !transporteLleno() && p.getVolumen() < 2000;
    }
}