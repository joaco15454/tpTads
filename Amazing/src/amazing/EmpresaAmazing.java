package amazing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaAmazing implements IEmpresa {
	private String Cuit;
	private double facturacionTotalPedidosCerrados = 0;
	private HashMap<Integer, Pedido> pedidos = new HashMap<>();
	private HashMap<String, Transporte> transportes = new HashMap<>();

	public EmpresaAmazing(String cuit) {
		Cuit = cuit;
	}

	public String obtenerCuit() {
		return Cuit;
	}

	public double obtenerFacturacionTotalPedidosCerrados() {
		return facturacionTotalPedidosCerrados;
	}

	public void actualizarFacturacionTotalPedidosCerrados(double facturacionTotalPedidosCerrados) {
		this.facturacionTotalPedidosCerrados = facturacionTotalPedidosCerrados;
	}

	public HashMap<Integer, Pedido> getPedidos() {
		return pedidos;
	}

	public HashMap<String, Transporte> obtenerTransportes() {
		return transportes;
	}

	// ** PARTE OPERACIONES INTERFAZ */
//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/* REGISTRAR TRANSPORTE AUTOMOVIL */
	/**
	 * Registra un nuevo transporte tipo Automovil en el sistema con los siguientes
	 * datos correspondiente a todo transporte:
	 * - patente,
	 * - volumen maximo de carga
	 * - valor del viaje (que cobrará a la empresa)
	 * 
	 * Además por ser Automovil se proporciona el dato:
	 * - cantidad maxima de paquetes que transporta
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	
	public void registrarAutomovil(String patente, int volMax, int valorViaje, int maxPaq) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Comun(patente, volMax, valorViaje, maxPaq);
		transportes.put(patente, t);
	}
	/* FIN REGISTRAR TRANSPORTE AUTOMOVIL */
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* REGISTRAR TRANSPORTE UTILITARIO */
	/**
	 * Registra un nuevo transporte tipo Utilitario en el sistema con los
	 * datos correspondientes a toda clase de transporte y ademas:
	 * 
	 * - un valor extra que cobra a la empresa si superan los 10 paquetes.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	public void registrarUtilitario(String patente, int volMax, int valorViaje, int valorExtra) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Utilitario(patente, volMax, valorViaje, valorExtra);
		transportes.put(patente, t);
	}
	/* FIN REGISTRAR TRANSPORTE UTILITARIO */
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/* REGISTRAR TRANSPORTE CAMION */
	/**
	 * Registra un nuevo transporte tipo Camion en el sistema con los
	 * datos correspondiente a todo transporte y ademas:
	 * 
	 * - un valor adicional que se multiplica por la cantidad de paquetes que le
	 * carguen.
	 * 
	 * Si esa patente ya esta en el sistema se debe generar una excepcion.
	 */
	public void registrarCamion(String patente, int volMax, int valorViaje, int adicXPaq) {
		if (existeTransporte(patente)) {
			throw new RuntimeException("Error, ya hay un transporte registrado con la patente " + patente);
		}
		Transporte t = new Camion(patente, volMax, valorViaje, adicXPaq);
		transportes.put(patente, t);
	}
	/* FIN REGISTRAR TRANSPORTE CAMION */
	
//////////////////////////////////////////////////////////////////////////////////////////////

	
	/* REGISTRAR PEDIDO*/
	/**
	 * Se registra un nuevo pedido en el sistema proporcionando los siguientes
	 * datos:
	 * - el nombre del cliente que lo solicita
	 * - su dirección
	 * - su dni
	 * 
	 * El sistema le asigna un numero de pedido unico y crea un carrito de ventas
	 * vacio.
	 * Devuelve el numero de pedido asignado.
	 * 
	 */
	public int registrarPedido(String cliente, String direccion, int dni) {
		Pedido p = new Pedido(dni, direccion, cliente, dni, false);
		pedidos.put(p.obtenerNroPedido(), p);
		return p.obtenerNroPedido();
	}
	
	
	
	/* FIN REGISTRAR PEDIDO*/
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* AGREGAR PAQUETE ORDINARIO */
	/**
	 * Se registra la compra de un producto, el cual se agregara al carrito del
	 * pedido dado
	 * como un paquete de tipo ordinario.
	 * 
	 * Se ingresan los datos necesario para agregarlo:
	 * - pedido al que corresponde agregarlo
	 * - volumen del paquete a agregar
	 * - precio del producto que contiene el paquete.
	 * 
	 * Ademas por ser un paquete de tipo ordinario:
	 * - costo del envio
	 * 
	 * Si ese pedido no esta registrado en el sistema o ya está finalizado
	 * se debe generar una excepcion.
	 * 
	 * devuelve el codigo de paquete unico.
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int costoEnvio) {
		Pedido p = pedidos.get(codPedido);
		if (p == null || p.isEstaCerrado()) {
			throw new RuntimeException("pedido no encontrado o cerrado");
		}
		return p.agregarProductoCarrito(volumen, precio, costoEnvio);
	}
	/* FIN AGREGAR PAQUETE ORDINARIO */
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* AGREGAR PAQUETE ESPECIAL */
	/**
	 * Se registra la compra de un producto que se agregara al carrito del pedido
	 * dado
	 * como paquete de tipo especial.
	 * 
	 * Se ingresan los datos necesario para agregarlo:
	 * - pedido al que corresponde agregarlo
	 * - volumen del paquete a agregar
	 * - precio del producto que contiene el paquete.
	 * 
	 * Ademas por ser un paquete de tipo especial:
	 * - porcentaje adicional (que se calcula y suma a su precio)
	 * - adicional (se suma si el paquete tiene volumen>3000)
	 * 
	 * Si ese pedido no esta registrado en el sistema o ya está finalizado
	 * se debe generar una excepcion.
	 * 
	 * devuelve el codigo de paquete unico.
	 * 
	 */
	public int agregarPaquete(int codPedido, int volumen, int precio, int porcentaje, int adicional) {
		Pedido p = pedidos.get(codPedido);
		if (p == null || p.isEstaCerrado()) {
			throw new RuntimeException("pedido no encontrado o cerrado");
		}
		return p.agregarProductoCarrito(volumen, precio, porcentaje, adicional);
	}
	/* FIN AGREGAR PAQUETE ESPECIAL */
	
//////////////////////////////////////////////////////////////////////////////////////////////

	
	/*QUITAR PAQUETE*/
	/**
	 * quita un paquete del pedido dado su codigo unico de paquete.
	 * 
	 * Devuelve true si pudo quitar el paquete.
	 * si no lo encontró o el pedido ya esta finalizado, devuelve false.
	 * 
	 * Demostrar la complejidad en terminos de O grande en el informe.
	 */
	

	public boolean quitarPaquete(int codPaquete) {
		for (Pedido p : pedidos.values()) {
			if (p.paqueteEnCarrito(codPaquete)) {
				return p.eliminarProductoCarrito(codPaquete);
			}
		}
		throw new RuntimeException("Error, no hay ningun paquete registrado con el codigo: " + codPaquete);
	}
	/* FIN QUITAR PAQUETE*/
	
	
//////////////////////////////////////////////////////////////////////////////////////////////
	/* CERRAR PEDIDO */

	/**
	 * Se registra la finalizacion de un pedido registrado en la empresa,
	 * dado su codigo.
	 * Devuelve el total a pagar por el pedido.
	 * 
	 * Si ese codigo no esta en el sistema o ya fue finalizado se debe
	 * generar una excepcion.
	 *
	 */
	public double cerrarPedido(int codPedido) {

		if(!existePedido(codPedido)) {
			throw new RuntimeException("Error, no hay un pedido registrado con el codigo: " + codPedido);
		}
		else {
			Pedido p = buscarPedido(codPedido);
			if(p.isEstaCerrado()) {
				throw new RuntimeException("Error, el pedido ya esta cerrado.");
			}else {
				p.finalizarPedido();
				actualizarFacturacionTotal(p.calcularValorAPagar());
				return p.calcularValorAPagar();
			}
		}
	}
	
	/* FIN CERRAR PEDIDOS*/
	


//////////////////////////////////////////////////////////////////////////////////////////////

	
	/*CARGA TRANSPORTE*/
	/**
	 * Se solicita la carga de un transporte registrado en la empresa, dada su
	 * patente.
	 * 
	 * Devuelve un String con forma de listado donde cada renglón representa un
	 * paquete cargado.
	 * Cada renglón debe respetar el siguiente formato:
	 * " + [ NroPedido - codPaquete ] direccion"
	 * por ejemplo:
	 * " + [ 1002 - 101 ] Gutierrez 1147"
	 *
	 * Los paquetes que se cargan deben pertenecer a pedidos finaizados.
	 * Si no se encontró ningún paquete para cargar, se debe devolver un string
	 * vacio.
	 * 
	 * Si esa patente no esta en el sistema se debe generar una excepcion.
	 * 
	 */
	public String cargarTransporte(String patente) {
		if (!existeTransporte(patente)) {
			throw new RuntimeException("Error, no existe un transporte registrado con la patente " + patente);
		}
		StringBuilder sb = new StringBuilder();
		Transporte t = transportes.get(patente);
		for (Pedido p : pedidos.values()) {
			if (p.isEstaCerrado()) {
				List<String> listaPaquetesCargados = t.cargarPedido(p);
				for (String j : listaPaquetesCargados) {
					 sb.append(String.join("\n", j));
				}
			}
		}
		String listadoPaquetesCargados = sb.toString();
		return listadoPaquetesCargados;
	}
	/*FIN CARGA TRANSPORTE*/
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	/* COSTO DE ENTREGA*/

	/**
	 * Se registra el costo del viaje de un transporte dado su patente
	 * Este costo es el que cobra el transporte (a la empresa) por entregar
	 * la carga una vez que fue cargado con los paquetes.
	 * 
	 * Una vez cargado, aunque no se haya podido completar, si no hay más paquetes
	 * para agregarle, el transporte reparte los paquetes cargados.
	 * 
	 * Se devuelve el valor del viaje segun lo indicado en cada clase de transporte.
	 * Cada clase de transporte tiene su forma de calcular el costo del viaje.
	 * 
	 * Si esa patente no esta en el sistema se debe generar una excepcion.
	 * Si el transporte no esta cargado genera un excepcion.
	 * 
	 * Se debe resolver en O(1)
	 */
	public double costoEntrega(String patente) {
		if (!existeTransporte(patente)) {
			throw new RuntimeException("Error, no hay un transport registrado con la patente " + patente);
		}
		Transporte t = transportes.get(patente);
		if (t.transporteVacio()) {
			throw new RuntimeException("Error, el transporte esta vacio.");
		}
		return t.costoEntrega();
	} 

	/* FIN COSTO DE ENTREGA*/
	
	
	
	/* FACTURACION TOTAL */
	/**
	 * Devuelve la suma del precio facturado de todos los pedidos cerrados.
	 * 
	 * Se debe realizar esta operacion en O(1).
	 */
	public double facturacionTotalPedidosCerrados() {

		return obtenerFacturacionTotalPedidosCerrados();
	}
	/*FIN FACTURACION TOTAL*/
	
	
//////////////////////////////////////////////////////////////////////////////////////////////	
	
	/*PEDIDOS NO ENTREGADOS (QUE SON CERRADOS)*/
	
	/**
	 * Devuelve los pedidos cerrados y que no fueron entregados en su totalidad.
	 * O sea, que tienen paquetes sin entregar.
	 * 
	 * Devuelve un diccionario cuya clave es el codigo del pedido y el valor es el
	 * nombre del
	 * cliente que lo pidio.
	 * 
	 */
	
	public Map<Integer, String> pedidosNoEntregados() {
		Map<Integer, String> pedidosNoEntregados = new HashMap<>();
		for (Pedido p : pedidos.values()) {
			if (p.isEstaCerrado() && p.entregaPendiente()&& !pedidosNoEntregados.containsKey(p.obtenerNroPedido())) {
				pedidosNoEntregados.put(p.obtenerNroPedido(), p.obtenerNombreDeCliente());
			}
		}
		return pedidosNoEntregados;
	}
	
	/*FIN PEDIDOS NO ENTREGADOS (QUE SON CERRADOS)*/
	
	
//////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*HAY TRANSPORTES IDENTICOS*/
	/**
	 * Se consideran transportes identicos a 2 transportes cargados con:
	 * - distinta patente,
	 * - misma clase y
	 * - la misma carga.
	 * Se considera misma carga al tener la misma cantidad de paquetes con las
	 * mismas caracteristicas:
	 * - mismo volumen,
	 * - misma clase y
	 * - mismo precio.
	 * VER EJEMPLO EN ENUNCIADO
	 */
	public boolean hayTransportesIdenticos() {
		for (Transporte t1 : transportes.values()) {
			for (Transporte t2 : transportes.values()) {
				boolean distintaPatenteMismaClase = (distintaPatente(t1,t2) && mismaClaseTransporte(t1,t2));
				boolean transportesNoVacios = (!t1.transporteVacio() && !t2.transporteVacio());
				boolean mismaCarga = (cargaIdentica(t1,t2));
				if (transportesNoVacios && distintaPatenteMismaClase && mismaCarga) {
					return true;
				}
			}
		}
		return false;
	}

	
	/*FIN HAY TRANSPORTES IDENTICOS*/
//////////////////////////////////////////////////////////////////////////////////////////////
	/* METODOS AUXILIARES */
	
	
	private boolean cargaIdentica(Transporte t1, Transporte t2) {
		List<Paquete> primeraCarga = t1.obtenerPaquetesCargados();
		List<Paquete> segundaCarga = t2.obtenerPaquetesCargados();
		if (primeraCarga.size() != segundaCarga.size()) {
			return false;
		}
		for (Paquete p1 : primeraCarga) {
			if (!existePaqueteIgual(p1, segundaCarga)) {
				return false;
			}
		}
		return true;
	}

	private boolean existePaqueteIgual(Paquete p1, List<Paquete> cargaPaquetes) {
		for (Paquete p2 : cargaPaquetes) {
			if  (p1.mismoPrecio(p2) && p1.mismoVolumen(p2)) {
				return true;
			}
		}
		return false;
	}
	private boolean distintaPatente(Transporte t1, Transporte t2) {
		return !t1.obtenerPatente().equals(t2.obtenerPatente());
	}
	private boolean mismaClaseTransporte(Transporte t1, Transporte t2) {
		return t1.getClass().equals(t2.getClass());
	}
    @Override
    public String toString() {
        StringBuilder pedidosStr = new StringBuilder();
        pedidosStr.append("Listado de Pedidos:\n");
        
        for (Pedido p : pedidos.values()) {
            pedidosStr.append(p.toString()).append("\n");
        }
        
        StringBuilder transportesStr = new StringBuilder();
        transportesStr.append("Listado de Transportes:\n");
        
        for (Transporte t : transportes.values()) {
            transportesStr.append(t.toString()).append("\n");
        }
        
        String separador = "============================================================================================================================\n";

        return separador +"Empresa Amazing - [" + Cuit + "]\n" +
                separador +
                pedidosStr.toString() +
                separador +
                transportesStr.toString()+
                separador;
    }
	
	
	private void actualizarFacturacionTotal(double valorPedido) {
		double valor = obtenerFacturacionTotalPedidosCerrados(); // Obtiene el valor actual
		valor += valorPedido; // Acumula el valor del pedido cerrado
		actualizarFacturacionTotalPedidosCerrados(valor); // Actualiza la variable
	} 
	
	private Pedido buscarPedido(int codPedido) {
		if(existePedido(codPedido)) {
			return pedidos.get((codPedido));
		}
		throw new RuntimeException("Error, no hay un pedido registrado con el codigo: " + codPedido);
	}
	
	
	private boolean existePedido(int codPaquete) {
		return pedidos.get(codPaquete) != null;
	}
	
	private boolean existeTransporte(String patente) {
		return transportes.get(patente) != null;
	}

	
	
	/* FIN METODOS AUXILIARES */
}
