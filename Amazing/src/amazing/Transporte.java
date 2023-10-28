package amazing;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transporte {

    private String patente;
    private int volumenMaximo;
    private int volumenActual = 0;
    private double valorQueCobra;
    protected List<Paquete> paquetesCargados;

    public Transporte(String patente, int volumenMaximo, double valorQueCobra) {
        this.patente = patente;
        this.volumenMaximo = volumenMaximo;
        this.valorQueCobra = valorQueCobra;

    }

    public String getPatente() {
        return patente;
    }

    public int getVolumenMaximo() {
        return volumenMaximo;
    }

    public double getValorQueCobra() {
        return valorQueCobra;
    }

    public List<Paquete> getPaquetesCargados() {
        return paquetesCargados;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public void setVolumenMaximo(int volumenMaximo) {
        if (volumenMaximo < 0) {
            throw new RuntimeException("Error, tiene que ser mayor que 0.");
        }
        this.volumenMaximo = volumenMaximo;
    }

    public int getVolumenActual() {
        return volumenActual;
    }

    public void setVolumenActual(int volumenActual) {
        if (volumenActual > getVolumenMaximo()) {
            throw new RuntimeException("Error, el volumen no puede superar " + getVolumenMaximo());
        }
        this.volumenActual = volumenActual;
    }

    public void aumentarVolumen(int volumenPaquete) {
        setVolumenActual(volumenActual + volumenPaquete);
    }

    public void setValorQueCobra(double valorQueCobra) {
        this.valorQueCobra = valorQueCobra;
    }

    public List<String> cargarPedido(Pedido pedido) {
        List<String> listaPaquetesCargados = new ArrayList<>();
        transporteEstaLleno();
        HashMap<Integer, Paquete> carrito = pedido.getCarrito();
        for (Paquete paquete : carrito.values()) {
            if (paquete instanceof PaqueteOrdinario || paquete instanceof PaqueteEspecial) {
                cargarPaquete(paquete,pedido);
                listaPaquetesCargados.add(cargarPaquete(paquete, pedido));
            }
        }
        return listaPaquetesCargados;
    }

    public String cargarPaquete(Paquete paquete, Pedido pedido) {
        paquetesCargados.add(paquete);
        aumentarVolumen(paquete.getVolumen());
        StringBuilder sb = new StringBuilder();
        sb.append("+ [");
        sb.append(pedido.getNroPedido());
        sb.append(" - ");
        sb.append(paquete.getIdUnico());
        sb.append(" ] ");
        sb.append(pedido.getDireccion());
        String paqueteCargado = sb.toString();
        pedido.getCarrito().remove(paquete.getIdUnico());
        return paqueteCargado;
    }

    public List<Paquete> listadoPaquetesAEntregar() {
        return paquetesCargados;
    }

    public double obtenerCostoViaje() {
        return getValorQueCobra();
    }

    public boolean superaLimite() {
        if (paquetesCargados.size() > getVolumenMaximo()) {
            return true;
        }
        return false;
    }

    public boolean transporteLleno() {
        return getVolumenActual() == getVolumenMaximo();
    }

    public void transporteEstaLleno() {
        if (transporteLleno()) {
            throw new RuntimeException("Error, el transporte esta lleno.");
        }
    }

}