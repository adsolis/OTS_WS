package com.datacode.avon_ots_ws.model;

public class DatosCorreo {

	private String cuenta;
	private int puerto;
	private String usuario;
	private String password;
	private boolean habilitaSSL;
	private String servidor;
	private String razonSocial;
	private String clavePorteo;

	public String getClavePorteo() {
		return clavePorteo;
	}

	public void setClavePorteo(String clavePorteo) {
		this.clavePorteo = clavePorteo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isHabilitaSSL() {
		return habilitaSSL;
	}

	public void setHabilitaSSL(boolean habilitaSSL) {
		this.habilitaSSL = habilitaSSL;
	}

	public String getServidor() {
		return servidor;
	}

	public void setServidor(String servidor) {
		this.servidor = servidor;
	}

}
