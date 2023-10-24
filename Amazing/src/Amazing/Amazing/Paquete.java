package Amazing;

public class Paquete{
    
    private int idUnico;
    private int volumen;
    private double precio;
    private String direccion;
    private Boolean entregado = false;


    public Paquete(int idUnico, int volumen, double precio, String direccion, Boolean entregado) {
        this.idUnico = idUnico;
        this.volumen = volumen;
        this.precio = precio;
        this.direccion = direccion;
        this.entregado = entregado;
    }


    public int getIdUnico() {
        return idUnico;
    }


    public int getVolumen() {
        return volumen;
    }


    public double getPrecio() {
        return precio;
    }


    public String getDireccion() {
        return direccion;
    }


    public Boolean getEntregado() {
        return entregado;
    }


    public void setIdUnico(int idUnico) {
        this.idUnico = idUnico;
    }


    public void setVolumen(int volumen) {
        this.volumen = volumen;
    }


    public void setPrecio(double precio) {
        this.precio = precio;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
    
}