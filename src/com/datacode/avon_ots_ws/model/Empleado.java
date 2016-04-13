/**
 * Mapeo de las propiedades del Objeto de la BD - PW_EMPLEADO
 * @author brenda.estrada
 * @since 10/01/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 10/01/2012
 * @category Map
 */
public class Empleado {
	//Atributos de Mapeo del Objeto
	private Integer idEmpleado = 0;
	private String nombre = "";
	private String apPaterno = "";
	private String apMaterno ="";
	private String domicilio ="";
	private String feNacimiento ="";
	private Integer idSexo = 0;
	private String sexo ="";
	private Integer idEdoCivil = 0;
	private String edoCivil ="";
	private String rfc ="";
	private String feIngreso ="";
	private String telefono ="";
	private String mail ="";
	//Dependencias
	private Integer idEstatus = 0;
	private String descEstatus = "";
	private Integer idPuesto = 0;
	private String descPuesto = "";
	private Integer idLDC = 0;
	private String descLDC = "";

	
	/**
	 * @return the idEmpleado
	 */
	public Integer getIdEmpleado() {
		return idEmpleado;
	}

	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apPaterno
	 */
	public String getApPaterno() {
		return apPaterno;
	}

	/**
	 * @param apPaterno the apPaterno to set
	 */
	public void setApPaterno(String apPaterno) {
		this.apPaterno = apPaterno;
	}

	/**
	 * @return the apMaterno
	 */
	public String getApMaterno() {
		return apMaterno;
	}

	/**
	 * @param apMaterno the apMaterno to set
	 */
	public void setApMaterno(String apMaterno) {
		this.apMaterno = apMaterno;
	}

	/**
	 * @return the domicilio
	 */
	public String getDomicilio() {
		return domicilio;
	}

	/**
	 * @param domicilio the domicilio to set
	 */
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	/**
	 * @return the feNacimiento
	 */
	public String getFeNacimiento() {
		return feNacimiento;
	}

	/**
	 * @param feNacimiento the feNacimiento to set
	 */
	public void setFeNacimiento(String feNacimiento) {
		this.feNacimiento = feNacimiento;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	/**
	 * @param sexo the sexo to set
	 */
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the edoCivil
	 */
	public String getEdoCivil() {
		return edoCivil;
	}

	/**
	 * @param edoCivil the edoCivil to set
	 */
	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}

	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the feIngreso
	 */
	public String getFeIngreso() {
		return feIngreso;
	}

	/**
	 * @param feIngreso the feIngreso to set
	 */
	public void setFeIngreso(String feIngreso) {
		this.feIngreso = feIngreso;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return the idEstatus
	 */
	public Integer getIdEstatus() {
		return idEstatus;
	}

	/**
	 * @param idEstatus the idEstatus to set
	 */
	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	/**
	 * @return the descEstatus
	 */
	public String getDescEstatus() {
		return descEstatus;
	}

	/**
	 * @param descEstatus the descEstatus to set
	 */
	public void setDescEstatus(String descEstatus) {
		this.descEstatus = descEstatus;
	}

	/**
	 * @return the idPuesto
	 */
	public Integer getIdPuesto() {
		return idPuesto;
	}

	/**
	 * @param idPuesto the idPuesto to set
	 */
	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	/**
	 * @return the descPuesto
	 */
	public String getDescPuesto() {
		return descPuesto;
	}

	/**
	 * @param descPuesto the descPuesto to set
	 */
	public void setDescPuesto(String descPuesto) {
		this.descPuesto = descPuesto;
	}

	/**
	 * @return the idLDC
	 */
	public Integer getIdLDC() {
		return idLDC;
	}

	/**
	 * @param idLDC the idLDC to set
	 */
	public void setIdLDC(Integer idLDC) {
		this.idLDC = idLDC;
	}

	/**
	 * @return the descLDC
	 */
	public String getDescLDC() {
		return descLDC;
	}

	/**
	 * @param descLDC the descLDC to set
	 */
	public void setDescLDC(String descLDC) {
		this.descLDC = descLDC;
	}

	/**
	 * @return the idSexo
	 */
	public Integer getIdSexo() {
		return idSexo;
	}

	/**
	 * @param idSexo the idSexo to set
	 */
	public void setIdSexo(Integer idSexo) {
		this.idSexo = idSexo;
	}

	/**
	 * @return the idEdoCivil
	 */
	public Integer getIdEdoCivil() {
		return idEdoCivil;
	}

	/**
	 * @param idEdoCivil the idEdoCivil to set
	 */
	public void setIdEdoCivil(Integer idEdoCivil) {
		this.idEdoCivil = idEdoCivil;
	}

}
