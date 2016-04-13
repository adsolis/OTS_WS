/**
 * @author jorge.torner
 * @since 07/02/2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * Clase modelo para el envío de primera liquidación 
 * @author jorge.torner
 * @since 07/02/2012
 */
public class PrimeraSegundaLiquidacion {
	private long ACCOUNT;
	private int CAMP_YEAR;
	private short CAMPAIGN;
	private String COMMENT;
	private Float PAYMENT_VALUE;
	private String BANK_CODE;
	private String PAYMENT_DATE;
	private String ROUND_NUM;
	private String TRAN_DATE;
	private String HJK_TYP;
	private String CHANGE_FLAG;
	private String IN_PROCESS;
	private String ACCEPT_FLAG;
	private String DELIVERY1ST;
	private String DLV_TYP;
	private String DEVOL_RSN;
	private String EFEC_SEQ;
	private String PROC_TEXT;
	private Float RETURN_VAL;
	private String PROC_DATE;
	private int ROUTE_SEQ;
	private String CLAVE_ORDEN;
	private int ROBO_CAJAS;

	private String idBitacoraEstatusOrdenReversar;
	private String accountReversar;
	private String campYearReversar;
	private String campaignReversar;

	private long enrutadoIdBitacoraEstatusOrden;
	private long enrutadoIdOrden;
	private int enrutadoRutaSecuencia;
	private String enrutadoRegistro;
	
	public long getACCOUNT() {
		return ACCOUNT;
	}
	public void setACCOUNT(long aCCOUNT) {
		ACCOUNT = aCCOUNT;
	}
	public int getCAMP_YEAR() {
		return CAMP_YEAR;
	}
	public void setCAMP_YEAR(int cAMP_YEAR) {
		CAMP_YEAR = cAMP_YEAR;
	}
	public short getCAMPAIGN() {
		return CAMPAIGN;
	}
	public void setCAMPAIGN(short cAMPAIGN) {
		CAMPAIGN = cAMPAIGN;
	}
	public String getCOMMENT() {
		return COMMENT;
	}
	public void setCOMMENT(String cOMMENT) {
		COMMENT = cOMMENT;
	}
	public Float getPAYMENT_VALUE() {
		return PAYMENT_VALUE;
	}
	public void setPAYMENT_VALUE(Float pAYMENT_VALUE) {
		PAYMENT_VALUE = pAYMENT_VALUE;
	}
	public String getBANK_CODE() {
		return BANK_CODE;
	}
	public void setBANK_CODE(String bANK_CODE) {
		BANK_CODE = bANK_CODE;
	}
	public String getPAYMENT_DATE() {
		return PAYMENT_DATE;
	}
	public void setPAYMENT_DATE(String pAYMENT_DATE) {
		PAYMENT_DATE = pAYMENT_DATE;
	}
	public String getROUND_NUM() {
		return ROUND_NUM;
	}
	public void setROUND_NUM(String rOUND_NUM) {
		ROUND_NUM = rOUND_NUM;
	}
	public String getTRAN_DATE() {
		return TRAN_DATE;
	}
	public void setTRAN_DATE(String tRAN_DATE) {
		TRAN_DATE = tRAN_DATE;
	}
	public String getHJK_TYP() {
		return HJK_TYP;
	}
	public void setHJK_TYP(String hJK_TYP) {
		HJK_TYP = hJK_TYP;
	}
	public String getCHANGE_FLAG() {
		return CHANGE_FLAG;
	}
	public void setCHANGE_FLAG(String cHANGE_FLAG) {
		CHANGE_FLAG = cHANGE_FLAG;
	}
	public String getIN_PROCESS() {
		return IN_PROCESS;
	}
	public void setIN_PROCESS(String iN_PROCESS) {
		IN_PROCESS = iN_PROCESS;
	}
	public String getACCEPT_FLAG() {
		return ACCEPT_FLAG;
	}
	public void setACCEPT_FLAG(String aCCEPT_FLAG) {
		ACCEPT_FLAG = aCCEPT_FLAG;
	}
	public String getDELIVERY1ST() {
		return DELIVERY1ST;
	}
	public void setDELIVERY1ST(String dELIVERY1ST) {
		DELIVERY1ST = dELIVERY1ST;
	}
	public String getDLV_TYP() {
		return DLV_TYP;
	}
	public void setDLV_TYP(String dLV_TYP) {
		DLV_TYP = dLV_TYP;
	}
	public String getDEVOL_RSN() {
		return DEVOL_RSN;
	}
	public void setDEVOL_RSN(String dEVOL_RSN) {
		DEVOL_RSN = dEVOL_RSN;
	}
	public String getEFEC_SEQ() {
		return EFEC_SEQ;
	}
	public void setEFEC_SEQ(String eFEC_SEQ) {
		EFEC_SEQ = eFEC_SEQ;
	}
	public String getPROC_TEXT() {
		return PROC_TEXT;
	}
	public void setPROC_TEXT(String pROC_TEXT) {
		PROC_TEXT = pROC_TEXT;
	}
	public Float getRETURN_VAL() {
		return RETURN_VAL;
	}
	public void setRETURN_VAL(Float rETURN_VAL) {
		RETURN_VAL = rETURN_VAL;
	}
	public String getPROC_DATE() {
		return PROC_DATE;
	}
	public void setPROC_DATE(String pROC_DATE) {
		PROC_DATE = pROC_DATE;
	}
	public int getROUTE_SEQ() {
		return ROUTE_SEQ;
	}
	public void setROUTE_SEQ(int rOUTE_SEQ) {
		ROUTE_SEQ = rOUTE_SEQ;
	}
	public String getCLAVE_ORDEN() {
		return CLAVE_ORDEN;
	}
	public void setCLAVE_ORDEN(String cLAVE_ORDEN) {
		CLAVE_ORDEN = cLAVE_ORDEN;
	}
	public int getROBO_CAJAS() {
		return ROBO_CAJAS;
	}
	public void setROBO_CAJAS(int rOBO_CAJAS) {
		ROBO_CAJAS = rOBO_CAJAS;
	}
	public String getIdBitacoraEstatusOrdenReversar() {
		return idBitacoraEstatusOrdenReversar;
	}
	public void setIdBitacoraEstatusOrdenReversar(
			String idBitacoraEstatusOrdenReversar) {
		this.idBitacoraEstatusOrdenReversar = idBitacoraEstatusOrdenReversar;
	}
	public String getAccountReversar() {
		return accountReversar;
	}
	public void setAccountReversar(String accountReversar) {
		this.accountReversar = accountReversar;
	}
	public String getCampYearReversar() {
		return campYearReversar;
	}
	public void setCampYearReversar(String campYearReversar) {
		this.campYearReversar = campYearReversar;
	}
	public String getCampaignReversar() {
		return campaignReversar;
	}
	public void setCampaignReversar(String campaignReversar) {
		this.campaignReversar = campaignReversar;
	}
	public long getEnrutadoIdBitacoraEstatusOrden() {
		return enrutadoIdBitacoraEstatusOrden;
	}
	public void setEnrutadoIdBitacoraEstatusOrden(
			long enrutadoIdBitacoraEstatusOrden) {
		this.enrutadoIdBitacoraEstatusOrden = enrutadoIdBitacoraEstatusOrden;
	}
	public long getEnrutadoIdOrden() {
		return enrutadoIdOrden;
	}
	public void setEnrutadoIdOrden(long enrutadoIdOrden) {
		this.enrutadoIdOrden = enrutadoIdOrden;
	}
	public int getEnrutadoRutaSecuencia() {
		return enrutadoRutaSecuencia;
	}
	public void setEnrutadoRutaSecuencia(int enrutadoRutaSecuencia) {
		this.enrutadoRutaSecuencia = enrutadoRutaSecuencia;
	}
	public String getEnrutadoRegistro() {
		return enrutadoRegistro;
	}
	public void setEnrutadoRegistro(String enrutadoRegistro) {
		this.enrutadoRegistro = enrutadoRegistro;
	}

}
