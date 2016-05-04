package com.datacode.avon_ots_ws.model;

public class LiquidacionRepartoDTO {
	
	Integer idSalidaReparto;
	String estatusCorreo;
	String estatusCorreoDejadasPUP;
	String estatusCorreoRecolectadasPUP;

	public Integer getIdSalidaReparto() {
		return idSalidaReparto;
	}
	public void setIdSalidaReparto(Integer idSalidaReparto) {
		this.idSalidaReparto = idSalidaReparto;
	}
	public String getEstatusCorreo() {
		return estatusCorreo;
	}
	public void setEstatusCorreo(String estatusCorreo) {
		this.estatusCorreo = estatusCorreo;
	}
	public String getEstatusCorreoDejadasPUP() {
		return estatusCorreoDejadasPUP;
	}
	public void setEstatusCorreoDejadasPUP(String estatusCorreoDejadasPUP) {
		this.estatusCorreoDejadasPUP = estatusCorreoDejadasPUP;
	}
	public String getEstatusCorreoRecolectadasPUP() {
		return estatusCorreoRecolectadasPUP;
	}
	public void setEstatusCorreoRecolectadasPUP(String estatusCorreoRecolectadasPUP) {
		this.estatusCorreoRecolectadasPUP = estatusCorreoRecolectadasPUP;
	}
}
