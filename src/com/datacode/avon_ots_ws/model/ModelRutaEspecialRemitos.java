package com.datacode.avon_ots_ws.model;

public class ModelRutaEspecialRemitos {
	
	/**Propiedad para el registro del remito*/
	private String registro;
	
	/**Propiedad para el nombre del registro del remito*/
	private String nombre;
	
	/**Propiedad para la cantidad a recolectar*/
	private int cantidadRecolectar;
	
	/**Propiedad para definir si esta escaneado el remito*/
	private boolean escaneada;
	
	/**Propiedad para el id del remito*/
	private long idRemito;
	
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCantidadRecolectar() {
		return cantidadRecolectar;
	}
	public void setCantidadRecolectar(int cantidadRecolectar) {
		this.cantidadRecolectar = cantidadRecolectar;
	}
	public boolean getEscaneada() {
		return escaneada;
	}
	public void setEscaneada(boolean escaneada) {
		this.escaneada = escaneada;
	}
	
	public long getIdRemito() {
		return this.idRemito;
	}
	
	public void setIdRemito(long idRemito) {
		this.idRemito = idRemito;
	}

	
	
}
