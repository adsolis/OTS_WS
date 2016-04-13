/**
 * @author jorge.torner
 * @since 03/02/2012
 */
package com.datacode.avon_ots_ws;

import static com.datacode.avon_ots_ws.Utils.agregaComillas;
import static com.datacode.avon_ots_ws.Utils.agregaComillasFecha;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.datacode.avon_ots_ws.model.PrimeraSegundaLiquidacion;

/**
 * @author jorge.torner
 * @since 03/02/2012
 */
public class PrimeraSegundaLiquidacionController {
	
	Connection conOTS;
	
	private List<PrimeraSegundaLiquidacion> obtenerDatosLiquidacion(short p_idLiquidacion, int p_idUsuario){
		List<PrimeraSegundaLiquidacion> listaLiquidacion = new ArrayList<PrimeraSegundaLiquidacion>();
		PrimeraSegundaLiquidacion datosLiquidacion = null;
		
		Connection con = AccesoBD.AbrirConexionOTS();
        CallableStatement cs = null;
		if(con != null){
	        try{
	        	cs = con.prepareCall("{call SP_PWA_ObtenerPrimeraSegundaLiquidacion(?)}");
	        	cs.setObject("P_ID_TIPO_LIQUIDACION", (p_idLiquidacion == 0 ? null : p_idLiquidacion), Types.SMALLINT);

	        	ResultSet rs = AccesoBD.executeRetrieveResultSet(cs);
	        	while(rs.next())
	        	{
	        		datosLiquidacion = new PrimeraSegundaLiquidacion();
	        		
	        		datosLiquidacion.setACCEPT_FLAG(rs.getString("ACCEPT_FLAG"));
	        		datosLiquidacion.setACCOUNT(rs.getLong("ACCOUNT"));
	        		datosLiquidacion.setBANK_CODE(rs.getString("BANK_CODE"));
	        		datosLiquidacion.setCAMP_YEAR(rs.getInt("CAMP_YEAR"));
	        		datosLiquidacion.setCAMPAIGN(rs.getShort("CAMPAIGN"));
	        		datosLiquidacion.setCHANGE_FLAG(rs.getString("CHANGE_FLAG"));
	        		datosLiquidacion.setCOMMENT(rs.getString("COMMENT"));
	        		datosLiquidacion.setDELIVERY1ST(rs.getString("DELIVERY1ST"));
	        		datosLiquidacion.setDEVOL_RSN(rs.getString("DEVOL_RSN"));
	        		datosLiquidacion.setDLV_TYP(rs.getString("DLV_TYP"));
//	        		datosLiquidacion.setHJK_TYP(rs.getString("HJK_TYP"));
	        		datosLiquidacion.setIN_PROCESS(rs.getString("IN_PROCESS"));
	        		datosLiquidacion.setPAYMENT_DATE(rs.getString("PAYMENT_DATE"));
	        		datosLiquidacion.setPAYMENT_VALUE(rs.getFloat("PAYMENT_VALUE"));
	        		datosLiquidacion.setPAYMENT_VALUE((rs.getString("PAYMENT_VALUE") == null ? null : rs.getFloat("PAYMENT_VALUE")));
	        		datosLiquidacion.setRETURN_VAL((rs.getString("RETURN_VAL") == null ? null : rs.getFloat("RETURN_VAL")));
	        		datosLiquidacion.setROUND_NUM(rs.getString("ROUND_NUM"));
	        		datosLiquidacion.setROUTE_SEQ(rs.getInt("ROUTE_SEQ"));
	        		datosLiquidacion.setCLAVE_ORDEN(rs.getString("CLAVE_ORDEN"));
	        		// dona.ugalde - Diciembre 2015
	        		datosLiquidacion.setHJK_TYP(rs.getString("HJK_TYP") == null || "".equals(rs.getString("HJK_TYP").trim()) ? null : rs.getString("HJK_TYP").trim());
	        		datosLiquidacion.setEFEC_SEQ(rs.getString("EFEC_SEQ") == null || "".equals(rs.getString("EFEC_SEQ").trim()) ? null : rs.getString("EFEC_SEQ").trim());
	        		try {
	        			if (datosLiquidacion.getEFEC_SEQ() != null) {
	        				if (datosLiquidacion.getEFEC_SEQ().length() > 8) {
	        					datosLiquidacion.setEFEC_SEQ(datosLiquidacion.getEFEC_SEQ().substring(0, 8));
	        				}
	        				Long.valueOf(datosLiquidacion.getEFEC_SEQ());
	        			}
	        		} catch (NumberFormatException e) {
	        			datosLiquidacion.setEFEC_SEQ(null);
	        		}
	        		listaLiquidacion.add(datosLiquidacion);
	        	}
	        	rs.close();
	        }
	        catch (SQLException ex){
	        	Utils.GuardarLogMensajeParams("PrimeraSegundaLiquidacionAdmin", "M1", ex.getMessage(), p_idUsuario, String.valueOf(p_idLiquidacion));
	        }
	        finally{
	        	AccesoBD.CerrarStatement(cs);
	        	AccesoBD.CerrarConexion(con);
	        }
		}
		
		return listaLiquidacion;
	}

	public String enviarPrimeraSegundaLiquidacion(short p_idLiquidacion, int p_idUsuario){
		String resultado = "";
		String sqlQuery = "";
		//Obtenemos los datos de liquidación en SOS
		List<PrimeraSegundaLiquidacion> listaLiq = obtenerDatosLiquidacion(p_idLiquidacion, p_idUsuario);
		
		for(PrimeraSegundaLiquidacion liq : listaLiq){
			Connection conSOS = AccesoBD.AbrirConexionSOS();
			conOTS = AccesoBD.AbrirConexionOTS();
	        Statement st = null;
			if(conSOS != null && conOTS != null){
		        try{
		        	//=======================PAYMENTT=====================//
		        	//Verificamos la existencia del registro en SOS
		        	sqlQuery = "SELECT ACCOUNT FROM PAYMENTT WHERE ACCOUNT= " + String.valueOf(liq.getACCOUNT()) + " AND CAMP_YEAR= " + String.valueOf(liq.getCAMP_YEAR()) + " AND CAMPAIGN=" + String.valueOf(liq.getCAMPAIGN());
		        	st = conSOS.createStatement();

		        	ResultSet rs = st.executeQuery(sqlQuery);
		        	//Si se encuentra el registro, se actualiza, si no, se inserta en SOS
		        	//if(rs.next() || p_idLiquidacion == 2) //Para segunda liquidación siempre es Update
		        	if(rs.next()) //Se quita validación porque ahora una orden puede pasar a segunda liquidación sin mandarse primera (Cierre, Ventanilla, Rutas Especiales). ja.torner 26Feb2015.
		        	{
		        		sqlQuery = "UPDATE PAYMENTT SET PAYMENT_VALUE=" + liq.getPAYMENT_VALUE()
		        				+", BANK_CODE=" +  agregaComillas(liq.getBANK_CODE()) + ", PAYMENT_DATE=" + agregaComillasFecha(liq.getPAYMENT_DATE()) + ", ROUND_NUM=" + agregaComillas(liq.getROUND_NUM())
		        				+", DEVOL_RSN=" + agregaComillas(liq.getDEVOL_RSN()) + ", COMMENT=" + agregaComillas(liq.getCOMMENT())
		        				+", HJK_TYP=" + agregaComillas(liq.getHJK_TYP()) + ", DLV_TYP=" + agregaComillas(liq.getDLV_TYP()) + ", DELIVERY1ST=" + agregaComillas(liq.getDELIVERY1ST()) + ", CHANGE_FLAG=" + agregaComillas(liq.getCHANGE_FLAG())
		        				+", TRAN_DATE=GETDATE(), IN_PROCESS=" + agregaComillas(liq.getIN_PROCESS()) + ", ACCEPT_FLAG=" + agregaComillas(liq.getACCEPT_FLAG())
		        				+", PROC_TEXT=NULL, RETURN_VAL=" + liq.getRETURN_VAL() + ", PROC_DATE=NULL"
		        				+", EFEC_SEQ=" + liq.getEFEC_SEQ() // dona.ugalde - Diciembre 2015
		        				+" WHERE ACCOUNT= " + liq.getACCOUNT() + " AND CAMP_YEAR= " + liq.getCAMP_YEAR() + " AND CAMPAIGN=" + liq.getCAMPAIGN();
		        	}
		        	else{
		        		sqlQuery = "INSERT INTO PAYMENTT (ACCOUNT, CAMP_YEAR, CAMPAIGN,  PAYMENT_VALUE"
		        				+", BANK_CODE, PAYMENT_DATE, ROUND_NUM, DEVOL_RSN"
		        				+", COMMENT, HJK_TYP, DLV_TYP"
		        				+", DELIVERY1ST, CHANGE_FLAG, IN_PROCESS, ACCEPT_FLAG, EFEC_SEQ)" 
		        				+" VALUES (" + liq.getACCOUNT() + ", " + liq.getCAMP_YEAR() + ", " + liq.getCAMPAIGN() +", " + liq.getPAYMENT_VALUE() 
		        				+ ", " + agregaComillas(liq.getBANK_CODE()) + ", " + agregaComillasFecha(liq.getPAYMENT_DATE()) + ", " + agregaComillas(liq.getROUND_NUM()) + ", " + agregaComillas(liq.getDEVOL_RSN())
		        				+", " + agregaComillas(liq.getCOMMENT()) + ", " + agregaComillas(liq.getHJK_TYP()) + ", " + agregaComillas(liq.getDLV_TYP()) 
		        				+ ", " + agregaComillas(liq.getDELIVERY1ST()) + ", " + agregaComillas(liq.getCHANGE_FLAG()) + ", " + agregaComillas(liq.getIN_PROCESS()) + ", " + agregaComillas(liq.getACCEPT_FLAG())
		        				+ ", " + liq.getEFEC_SEQ() + ")"; // dona.ugalde - Diciembre 2015
		        	}
		        	rs.close();
		        	st.close();
		        	
		        	st = conSOS.createStatement();
		        	st.executeUpdate(sqlQuery);
		        	st.close();
		        	//=============Insertamos log de trazabilidad============//
		        	insertaLogLiquidacion(liq.getACCOUNT(), liq.getCAMP_YEAR(), liq.getCAMPAIGN(), liq.getCLAVE_ORDEN(), sqlQuery, p_idUsuario);
		        	
		        	
		        	//=======================REPRESENTATIVEST=====================//
		        	//Verificamos la existencia del registro de Representantes en SOS
		        	sqlQuery = "SELECT ACCOUNT FROM REPRESENTATIVEST WHERE ACCOUNT=" + String.valueOf(liq.getACCOUNT());
		        	st = conSOS.createStatement();

		        	rs = st.executeQuery(sqlQuery);
		        	//Si se encuentra el registro, se actualiza, si no, se inserta en SOS
		        	//if(rs.next() || p_idLiquidacion == 2)  //Para segunda liquidación siempre es Update
		        	if(rs.next()) //Se quita validación porque ahora una orden puede pasar a segunda liquidación sin mandarse primera (Cierre, Ventanilla, Rutas Especiales). ja.torner 26Feb2015.
		        	{
		        		sqlQuery = "UPDATE REPRESENTATIVEST " 
		        				+"SET ROUTE_SEQ=" + liq.getROUTE_SEQ()
		        				+", CHANGE_FLAG='T', ACCEPT_FLAG='F', PROC_TEXT=NULL, TRAN_DATE=GETDATE(), PROC_DATE=NULL "
		        				+"WHERE ACCOUNT=" + liq.getACCOUNT();
		        	}
		        	else{
		        		sqlQuery = "INSERT INTO REPRESENTATIVEST (ACCOUNT, ROUTE_SEQ, CHANGE_FLAG, ACCEPT_FLAG) "
		        				+"VALUES (" + liq.getACCOUNT() + ", " + liq.getROUTE_SEQ() + ", 'T', 'F')";
		        	}
		        	rs.close();
		        	st.close();
		        	
		        	st = conSOS.createStatement();
		        	st.executeUpdate(sqlQuery);
		        	
		        	st.close();
		        	
		        	//=============Insertamos log de trazabilidad============//
		        	insertaLogLiquidacion(liq.getACCOUNT(), liq.getCAMP_YEAR(), liq.getCAMPAIGN(), liq.getCLAVE_ORDEN(), sqlQuery, p_idUsuario);
		        	
		        	//=============REGISTRA LIQUIDACION OTS============//
		        	//Actualiza la orden como enviada a Primer/Segunda Liquidación en OTS
		        	CallableStatement cs = conOTS.prepareCall("{call SP_PWA_RegistraEnvioLiquidacion(?,?,?)}");
		        	cs.setObject("P_CLAVE_ORDEN", liq.getCLAVE_ORDEN(), Types.VARCHAR);
		        	cs.setObject("P_ID_USUARIO", p_idUsuario, Types.INTEGER);
		        	cs.setObject("P_ID_TIPO_LIQUIDACION", p_idLiquidacion, Types.SMALLINT);
		        	
		        	cs.execute();
		        	AccesoBD.CerrarStatement(cs);
		        }
		        catch (SQLException ex){
		        	resultado += Utils.GuardarLogMensajeParams("PrimeraSegundaLiquidacionAdmin", "M2", ex.getMessage() + " SQLQUERY:" + sqlQuery, p_idUsuario, String.valueOf(p_idLiquidacion) +"&"+ String.valueOf(liq.getACCOUNT()));
		        }
			}
			
	        AccesoBD.CerrarConexion(conSOS);
	        AccesoBD.CerrarConexion(conOTS);
		}
		
		
		return resultado;
	}
	
	private void insertaLogLiquidacion(Long p_registro, int p_anioCampania, int p_campania, String p_claveOrden, String p_consulta, int p_idUsuario){
		if(conOTS != null){
			try{
				//=============Guarda liquidación SOS============//
	        	//Se inserta en tabla de historial la liquidación enviada a SOS
				CallableStatement cs = conOTS.prepareCall("{call SP_PWA_InsertaLiquidacionSOS(?,?,?,?,?,?)}");
				cs.setObject("P_REGISTRO", String.valueOf(p_registro), Types.VARCHAR);
				cs.setObject("P_ANIO_CAMPANIA", p_anioCampania, Types.SMALLINT);
				cs.setObject("P_CAMPANIA", p_campania, Types.TINYINT);
		    	cs.setObject("P_CLAVE_ORDEN", p_claveOrden, Types.VARCHAR);
		    	cs.setObject("P_CONSULTA", p_consulta, Types.VARCHAR);
		    	cs.setObject("P_USUARIO_ACTUALIZA", String.valueOf(p_idUsuario), Types.VARCHAR);
		    	cs.execute();
		    	AccesoBD.CerrarStatement(cs);
		    }
		    catch (SQLException ex){
		    	Utils.GuardarLogMensajeBD("PrimeraSegundaLiquidacionAdmin", "Clave", "Error al guardar registro de liquidación", ex.getMessage(), p_idUsuario);
		    }
		}
	}

	public boolean actualizarRouteSeq(int p_idUsuario) {
		Connection connOTS = null;
		CallableStatement csOTS = null;
		connOTS = AccesoBD.AbrirConexionOTS();
		if(connOTS != null) {
			try {
				// Obtenemos ordenes
				csOTS = connOTS.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerRouteSeq(?,?)}");
				csOTS.setObject("P_ACCION", "OBTENER", Types.VARCHAR);
				csOTS.setObject("P_ID_BITACORA_ESTATUS_ORDEN", null, Types.BIGINT);
				ResultSet rsOTS = AccesoBD.executeRetrieveResultSet(csOTS);
				List<PrimeraSegundaLiquidacion> listaOrdenes = new ArrayList<PrimeraSegundaLiquidacion>();
	        	while (rsOTS.next()) {
	        		PrimeraSegundaLiquidacion orden = new PrimeraSegundaLiquidacion();
	        		orden.setEnrutadoIdBitacoraEstatusOrden(rsOTS.getLong("ID_BITACORA_ESTATUS_ORDEN"));
	        		orden.setEnrutadoIdOrden(rsOTS.getLong("ID_ORDEN"));
	        		orden.setEnrutadoRutaSecuencia(rsOTS.getInt("RUTA_SECUENCIA"));
	        		orden.setEnrutadoRegistro(rsOTS.getString("REGISTRO"));
	        		listaOrdenes.add(orden);
	        	}
	        	rsOTS.close();
	        	csOTS.close();
	        	connOTS.close();
	        	for (PrimeraSegundaLiquidacion iter : listaOrdenes) {
	        		Connection connSOS = AccesoBD.AbrirConexionSOS();
        			Statement stSOS = null;
	        		StringBuilder query = new StringBuilder()
	        			.append("SELECT ROUTE_SEQ FROM REPRESENTATIVEST WHERE ACCOUNT = '")
	        			.append(iter.getEnrutadoRegistro().trim().toUpperCase())
	        			.append("'");
	        		System.out.println(query.toString());
	        		stSOS = connSOS.createStatement();
	        		ResultSet rs = stSOS.executeQuery(query.toString());
	        		boolean existe = false;
	        		if (rs.next()) {
	        			existe = (rs.getString("ROUTE_SEQ") != null);
	        		}
	        		stSOS.close();
	        		connSOS.close();
	        		query.delete(0, query.length());
	        		if (existe) {
	        			query.append("UPDATE REPRESENTATIVEST SET ROUTE_SEQ = ")
	        				.append(iter.getEnrutadoRutaSecuencia())
	        				.append(", CHANGE_FLAG = 'F', ACCEPT_FLAG = 'F', PROC_TEXT = NULL, TRAN_DATE = GETDATE(), PROC_DATE = NULL ")
	        				.append("WHERE ACCOUNT = '")
	        				.append(iter.getEnrutadoRegistro().trim().toUpperCase())
	        				.append("'");
	        		} else {
	        			query.append("INSERT INTO REPRESENTATIVEST (ACCOUNT, ROUTE_SEQ, CHANGE_FLAG, ACCEPT_FLAG) VALUES ('")
	        				.append(iter.getEnrutadoRegistro().trim().toUpperCase())
	        				.append("', ")
	        				.append(iter.getEnrutadoRutaSecuencia())
	        				.append(", 'T', 'F')");
	        		}
	        		System.out.println(query.toString());
	        		connSOS = AccesoBD.AbrirConexionSOS();
	        		stSOS = connSOS.createStatement();
	        		stSOS.executeUpdate(query.toString());
	        		stSOS.close();
	        		connSOS.close();
	        		// Actualizamos
	        		connOTS = AccesoBD.AbrirConexionOTS();
	        		csOTS = connOTS.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerRouteSeq(?,?)}");
					csOTS.setObject("P_ACCION", "ACTUALIZAR", Types.VARCHAR);
					csOTS.setObject("P_ID_BITACORA_ESTATUS_ORDEN", iter.getEnrutadoIdBitacoraEstatusOrden(), Types.BIGINT);
					AccesoBD.executeRetrieveResultSet(csOTS);
	        	}
        		return true;
			} catch (SQLException e) {
				System.err.println("SQLException en actualizarRouteSeq con idUsuario " + p_idUsuario + " a las " + new Date());
    			System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				System.err.println("Exception en actualizarRouteSeq con idUsuario " + p_idUsuario + " a las " + new Date());
    			System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

	public void ejecutarReversionOrdenes(int p_idUsuario) {
		Connection connOTS = AccesoBD.AbrirConexionOTS();
		CallableStatement csOTS = null;
		if(connOTS != null) {
			try {
				csOTS = connOTS.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerOrdenesReversar(?,?)}");
				csOTS.setObject("P_ACCION", 1, Types.INTEGER);
				csOTS.setObject("P_ID_BITACORA_ESTATUS_ORDEN", null, Types.BIGINT);
				ResultSet rsOTS = AccesoBD.executeRetrieveResultSet(csOTS);
				List<PrimeraSegundaLiquidacion> listaOrdenes = new ArrayList<PrimeraSegundaLiquidacion>();
	        	while(rsOTS.next()) {
	        		PrimeraSegundaLiquidacion orden = new PrimeraSegundaLiquidacion();
	        		orden.setIdBitacoraEstatusOrdenReversar(rsOTS.getString("ID_BITACORA_ESTATUS_ORDEN") != null ? rsOTS.getString("ID_BITACORA_ESTATUS_ORDEN") : "");
	        		orden.setAccountReversar(rsOTS.getString("ACCOUNT") != null ? rsOTS.getString("ACCOUNT") : "");
	        		orden.setCampYearReversar(rsOTS.getString("CAMP_YEAR") != null ? rsOTS.getString("CAMP_YEAR") : "");
	        		orden.setCampaignReversar(rsOTS.getString("CAMPAIGN") != null ? rsOTS.getString("CAMPAIGN") : "");
	        		listaOrdenes.add(orden);
	        		String mensaje = "***** Orden a reversar - ID_BITACORA_ESTATUS_ORDEN: >" + orden.getIdBitacoraEstatusOrdenReversar() + "< ";
	        		mensaje += "ACCOUNT: >" + orden.getAccountReversar() + "< ";
	        		mensaje += "CAMP_YEAR: >" + orden.getCampYearReversar() + "< ";
	        		mensaje += "CAMPAIGN: >" + orden.getCampaignReversar() + "< ";
	        		System.out.println(mensaje);
	        	}
	        	rsOTS.close();
	        	csOTS.close();
	        	connOTS.close();
	        	for (PrimeraSegundaLiquidacion iter : listaOrdenes) {
	        		try {
	        			Connection connSOS = AccesoBD.AbrirConexionSOS();
	        			Statement stSOS = null;
		        		StringBuilder insertHFDEL = new StringBuilder()
		        			.append("INSERT INTO HFDEL (LASTUPD_TS, DEL_TABLE, DEL_WHERE) VALUES(GETDATE(), 'PAYMENTT', 'T.ACCOUNT=")
		        			.append(iter.getAccountReversar())
		        			.append(" AND T.CAMP_YEAR=")
		        			.append(iter.getCampYearReversar())
		        			.append(" AND T.CAMPAIGN=")
		        			.append(iter.getCampaignReversar())
		        			.append("')");
		        		StringBuilder deletePAYMENTT = new StringBuilder()
	        				.append("DELETE FROM PAYMENTT WHERE ACCOUNT = ")
	        				.append(iter.getAccountReversar())
	        				.append(" AND CAMP_YEAR = ")
	        				.append(iter.getCampYearReversar())
	        				.append(" AND CAMPAIGN = ")
	        				.append(iter.getCampaignReversar());
		        		// Ejecutamos el insert
		        		System.out.println(insertHFDEL.toString());
		        		stSOS = connSOS.createStatement();
			        	stSOS.executeUpdate(insertHFDEL.toString());
			        	stSOS.close();
			        	// Ejecutamos el update
			        	System.out.println(deletePAYMENTT.toString());
			        	stSOS = connSOS.createStatement();
			        	stSOS.executeUpdate(deletePAYMENTT.toString());
			        	stSOS.close();
			        	connSOS.close();
			        	// Actualizamos en OTS
			        	connOTS = AccesoBD.AbrirConexionOTS();
			        	csOTS = connOTS.prepareCall("{call SP_LiquidacionRepartoWeb_ObtenerOrdenesReversar(?,?)}");
			        	csOTS.setObject("P_ACCION", 2, Types.INTEGER);
			        	csOTS.setObject("P_ID_BITACORA_ESTATUS_ORDEN", Long.valueOf(iter.getIdBitacoraEstatusOrdenReversar()), Types.BIGINT);
						AccesoBD.executeRetrieveResultSet(csOTS);
						csOTS.close();
						connOTS.close();
	        		} catch (SQLException e) {
	        			System.err.println("SQLException en ejecutarReversionOrdenes con idUsuario " + p_idUsuario + " a las " + new Date());
	        			System.err.println(e.getMessage());
	    				e.printStackTrace();
	        		} catch (NumberFormatException e) {
	        			System.err.println("NumberFormatException en ejecutarReversionOrdenes con idUsuario " + p_idUsuario + " a las " + new Date());
	        			System.err.println(e.getMessage());
	    				e.printStackTrace();
	        		} catch (Exception e) {
	        			System.err.println("Exception en ejecutarReversionOrdenes con idUsuario " + p_idUsuario + " a las " + new Date());
	        			System.err.println(e.getMessage());
	    				e.printStackTrace();
	        		}
	        	}
			} catch (SQLException e2) {
				System.err.println("SQLException e2 en ejecutarReversionOrdenes con idUsuario " + p_idUsuario + " a las " + new Date());
    			System.err.println(e2.getMessage());
				e2.printStackTrace();
			}
		}
	}

}
