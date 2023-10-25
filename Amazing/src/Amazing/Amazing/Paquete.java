package Amazing;

public class Paquete{
    
    private int idUnico;
    private int volumen;
    private double precio;
    private String direccion;
    private Boolean entregado;

    /* CONSTRUCTOR, SETTERS AND GETTERS */
    public Paquete(int idUnico, int volumen, double precio, String direccion, Boolean entregado) {
        this.idUnico = idUnico;
        this.volumen = volumen;
        this.precio = precio;
        this.direccion = direccion;
        this.entregado = false;
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
        //falta comprobar que no se repita
        this.idUnico = idUnico;
    }


    public void setVolumen(int volumen) {
        if (volumen < 0 || volumen > 999999) {
            throw new RuntimeException("Error, el numero tiene que ser mayor a 0 y menor a 999999");
        }
        this.volumen = volumen;
    }


    public void setPrecio(double precio) {
        if (precio < 0 ) {
            throw new RuntimeException("Error,el precio tiene que ser mayor a 0");
        }
        this.precio = precio;
    }


    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public void setEntregado(Boolean entregado) {
        this.entregado = entregado;
    }
    /* FIN CONSTRUCTOR, SETTERS AND GETTERS */
    /* OPERACIONES */
    public void precioTotal(){

    }
    public void paqueteEntregado() {
        setEntregado(true);
    }
    public boolean fueEntregado() {
        return getEntregado();
    }
}