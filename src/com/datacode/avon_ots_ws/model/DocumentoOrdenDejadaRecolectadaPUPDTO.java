package com.datacode.avon_ots_ws.model;

public class DocumentoOrdenDejadaRecolectadaPUPDTO {

	Long registro;
	Integer codEnviado;
	Integer codRecibido;
	Integer remitoEnviado;
	Integer remitoRecibido;

	public Long getRegistro() {
		return registro;
	}
	public void setRegistro(Long registro) {
		this.registro = registro;
	}
	public Integer getCodEnviado() {
		return codEnviado;
	}
	public void setCodEnviado(Integer codEnviado) {
		this.codEnviado = codEnviado;
	}
	public Integer getCodRecibido() {
		return codRecibido;
	}
	public void setCodRecibido(Integer codRecibido) {
		this.codRecibido = codRecibido;
	}
	public Integer getRemitoEnviado() {
		return remitoEnviado;
	}
	public void setRemitoEnviado(Integer remitoEnviado) {
		this.remitoEnviado = remitoEnviado;
	}
	public Integer getRemitoRecibido() {
		return remitoRecibido;
	}
	public void setRemitoRecibido(Integer remitoRecibido) {
		this.remitoRecibido = remitoRecibido;
	}
	
}
