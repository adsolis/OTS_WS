/**
 * Mapeo de las propiedades del Objeto de la BD - // VIEW_ORDEN //
 * @author brenda.estrada
 * @since  18-01-2012
 */
package com.datacode.avon_ots_ws.model;

/**
 * @author brenda.estrada
 * @since 20-12-2011
 * @category Map
 */
public class Orden {
	//Definición de atributos del objeto
		private Integer idOrden = 0;
		private String  cveOrden = "",
						noCajas = "", lastupd_ts = "", fecha_actualizado = "", usuario_actualiza = "",
						anio = "";
		private String cveRuta = ""; //No usa IDRUTA
		//Dependencias
		private Integer idRepresentante = 0;	private String descRepresentante = "";	 //Registro	
		private Integer idLDC = 0;				private String descLDC = ""; 			//Clave
		private Integer idPrimeraOrden = 0;		private String descPrimeraOrden = ""; //0-1
		private Integer idCampania = 0;			private String descCampania = "";     //Campnia
		private Integer idEstatus = 0;			private String descEstatus = ""; //0-1



		/**
		 * @return the idOrden
		 */
		public Integer getIdOrden() {
			return idOrden;
		}



		/**
		 * @param idOrden the idOrden to set
		 */
		public void setIdOrden(Integer idOrden) {
			this.idOrden = idOrden;
		}



		/**
		 * @return the cveOrden
		 */
		public String getCveOrden() {
			return cveOrden;
		}



		/**
		 * @param cveOrden the cveOrden to set
		 */
		public void setCveOrden(String cveOrden) {
			this.cveOrden = cveOrden;
		}



		/**
		 * @return the noCajas
		 */
		public String getNoCajas() {
			return noCajas;
		}



		/**
		 * @param noCajas the noCajas to set
		 */
		public void setNoCajas(String noCajas) {
			this.noCajas = noCajas;
		}



		/**
		 * @return the lastupd_ts
		 */
		public String getLastupd_ts() {
			return lastupd_ts;
		}



		/**
		 * @param lastupd_ts the lastupd_ts to set
		 */
		public void setLastupd_ts(String lastupd_ts) {
			this.lastupd_ts = lastupd_ts;
		}



		/**
		 * @return the fecha_actualizado
		 */
		public String getFecha_actualizado() {
			return fecha_actualizado;
		}



		/**
		 * @param fecha_actualizado the fecha_actualizado to set
		 */
		public void setFecha_actualizado(String fecha_actualizado) {
			this.fecha_actualizado = fecha_actualizado;
		}



		/**
		 * @return the usuario_actualiza
		 */
		public String getUsuario_actualiza() {
			return usuario_actualiza;
		}



		/**
		 * @param usuario_actualiza the usuario_actualiza to set
		 */
		public void setUsuario_actualiza(String usuario_actualiza) {
			this.usuario_actualiza = usuario_actualiza;
		}



		/**
		 * @return the anio
		 */
		public String getAnio() {
			return anio;
		}



		/**
		 * @param anio the anio to set
		 */
		public void setAnio(String anio) {
			this.anio = anio;
		}



		/**
		 * @return the cveRuta
		 */
		public String getCveRuta() {
			return cveRuta;
		}



		/**
		 * @param cveRuta the cveRuta to set
		 */
		public void setCveRuta(String cveRuta) {
			this.cveRuta = cveRuta;
		}



		/**
		 * @return the idRepresentante
		 */
		public Integer getIdRepresentante() {
			return idRepresentante;
		}



		/**
		 * @param idRepresentante the idRepresentante to set
		 */
		public void setIdRepresentante(Integer idRepresentante) {
			this.idRepresentante = idRepresentante;
		}



		/**
		 * @return the descRepresentante
		 */
		public String getDescRepresentante() {
			return descRepresentante;
		}



		/**
		 * @param descRepresentante the descRepresentante to set
		 */
		public void setDescRepresentante(String descRepresentante) {
			this.descRepresentante = descRepresentante;
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
		 * @return the idPrimeraOrden
		 */
		public Integer getIdPrimeraOrden() {
			return idPrimeraOrden;
		}



		/**
		 * @param idPrimeraOrden the idPrimeraOrden to set
		 */
		public void setIdPrimeraOrden(Integer idPrimeraOrden) {
			this.idPrimeraOrden = idPrimeraOrden;
		}



		/**
		 * @return the descPrimeraOrden
		 */
		public String getDescPrimeraOrden() {
			return descPrimeraOrden;
		}



		/**
		 * @param descPrimeraOrden the descPrimeraOrden to set
		 */
		public void setDescPrimeraOrden(String descPrimeraOrden) {
			this.descPrimeraOrden = descPrimeraOrden;
		}



		/**
		 * @return the idCampania
		 */
		public Integer getIdCampania() {
			return idCampania;
		}



		/**
		 * @param idCampania the idCampania to set
		 */
		public void setIdCampania(Integer idCampania) {
			this.idCampania = idCampania;
		}



		/**
		 * @return the descCampania
		 */
		public String getDescCampania() {
			return descCampania;
		}



		/**
		 * @param descCampania the descCampania to set
		 */
		public void setDescCampania(String descCampania) {
			this.descCampania = descCampania;
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
	

	
}
