package com.datacode.avon_ots_ws;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import com.datacode.avon_ots_ws.model.Bancos;
import com.datacode.avon_ots_ws.model.LiquidarOrdenesVentanillaMasiva;
import com.datacode.avon_ots_ws.model.PersonaRecibe;
import com.datacode.avon_ots_ws.model.TipoPago;

public class LiquidacionVentanillaMasivaController {

	private Connection _connection;
	private DecimalFormat _decimalFormat = null;
	
	public TipoPago[] ObtenerTipoPagos() {
		_connection = AccesoBD.AbrirConexionOTS();
		List<TipoPago> vLista = new ArrayList<TipoPago>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_LiquidacionVentanillaMasivaTipoPago()}");
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {					
					TipoPago vTipoPago = new TipoPago();
					vTipoPago.setIdTipoPuesto(vResultSet.getInt("ID_TIPO_PAGO"));
					vTipoPago.setDescripcion(vResultSet.getString("DESCRIPCION"));
					vLista.add(vTipoPago);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}			
		}
		return (TipoPago[]) vLista.toArray(new TipoPago[vLista.size()]);
	}
	
	public Bancos[] ObtenerBancos() {
		_connection = AccesoBD.AbrirConexionOTS();
		List<Bancos> vLista = new ArrayList<Bancos>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_LiquidacionVentanillaMasivaBancos()}");
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {
					Bancos vBanco = new Bancos();
					vBanco.setIdBanco(vResultSet.getInt("ID_BANCO"));
					vBanco.setClave(vResultSet.getString("CLAVE"));
					vBanco.setNombre(vResultSet.getString("NOMBRE"));
					vLista.add(vBanco);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}			
		}
		return (Bancos[]) vLista.toArray(new Bancos[vLista.size()]);
	}
	
	public PersonaRecibe[] ObtenerQuienRecibe() {
		_connection = AccesoBD.AbrirConexionOTS();
		List<PersonaRecibe> vLista = new ArrayList<PersonaRecibe>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_LiquidacionVentanillaMasivaPersonaRecibe()}");
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {					
					PersonaRecibe vQuienRecibe = new PersonaRecibe();
					vQuienRecibe.setIdPersonaRecibe(vResultSet.getInt("ID_PERSONA_RECIBE"));
					vQuienRecibe.setDescripcion(vResultSet.getString("DESCRIPCION"));
					vLista.add(vQuienRecibe);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}			
		}
		return (PersonaRecibe[]) vLista.toArray(new PersonaRecibe[vLista.size()]);
	}

	public LiquidarOrdenesVentanillaMasiva[] ObtenerOrdenes(){
		_connection = AccesoBD.AbrirConexionOTS();
		List<LiquidarOrdenesVentanillaMasiva> vLista = new ArrayList<LiquidarOrdenesVentanillaMasiva>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_LiquidacionVentanillaMasivaObtenerOrdenes()}");
				DecimalFormatSymbols vSymbol = new DecimalFormatSymbols();
				vSymbol.setDecimalSeparator('.');
				_decimalFormat = new DecimalFormat("#0.00", vSymbol);	
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {					
					LiquidarOrdenesVentanillaMasiva vLiquidarOrdenesVentanillaMasiva = new LiquidarOrdenesVentanillaMasiva();
					vLiquidarOrdenesVentanillaMasiva.setIdOrdenEntVentanilla(vResultSet.getString("ID_ORDEN_ENT_VENTANILLA"));
					vLiquidarOrdenesVentanillaMasiva.setIdOrden(vResultSet.getString("ID_ORDEN"));
					vLiquidarOrdenesVentanillaMasiva.setClaveOrden(vResultSet.getString("CLAVE_ORDEN"));
					vLiquidarOrdenesVentanillaMasiva.setRegistro(vResultSet.getString("REGISTRO"));
					vLiquidarOrdenesVentanillaMasiva.setNombre(vResultSet.getString("NOMBRE"));
					vLiquidarOrdenesVentanillaMasiva.setMontoCobrar(_decimalFormat.format(Double.parseDouble(vResultSet.getString("MONTO_A_COBRAR"))));
					vLiquidarOrdenesVentanillaMasiva.setIdCampania(vResultSet.getString("ID_CAMPANIA"));
					vLiquidarOrdenesVentanillaMasiva.setCampania(vResultSet.getString("CAMPANIA"));
					vLiquidarOrdenesVentanillaMasiva.setIdZona(vResultSet.getString("ID_ZONA"));
					vLiquidarOrdenesVentanillaMasiva.setZona(vResultSet.getString("ZONA"));
					vLista.add(vLiquidarOrdenesVentanillaMasiva);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}			
		}
		return (LiquidarOrdenesVentanillaMasiva[]) vLista.toArray(new LiquidarOrdenesVentanillaMasiva[vLista.size()]);
	}

	public String ObtenerPagos(LiquidarOrdenesVentanillaMasiva[] pPagos, String pUsuario, String pOtros, String pComentarios, String pIdPersonaRecibe){
		String vRes = "";
		_connection = AccesoBD.AbrirConexionOTS();
		CallableStatement vCallStatement = null;
		if(_connection != null){
			try{	
				if(pPagos.length > 0){
					vCallStatement = _connection.prepareCall("{call SP_LiquidacionVentanillaMasivaPago(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
					for (LiquidarOrdenesVentanillaMasiva liquidarOrdenesVentanillaMasiva : pPagos) {
						vCallStatement.setObject("p_IdOrden", liquidarOrdenesVentanillaMasiva.getIdOrden(), Types.VARCHAR);
						vCallStatement.setObject("p_IdOrdenVentanilla", liquidarOrdenesVentanillaMasiva.getIdOrdenEntVentanilla(), Types.VARCHAR);
						vCallStatement.setObject("p_IdTipoPago", liquidarOrdenesVentanillaMasiva.getIdTipoPago(), Types.VARCHAR);
						vCallStatement.setObject("p_IdBanco", liquidarOrdenesVentanillaMasiva.getIdBanco(), Types.VARCHAR);
						vCallStatement.setObject("p_Monto", liquidarOrdenesVentanillaMasiva.getMontoCobrar(), Types.VARCHAR);
						vCallStatement.setObject("p_Folios", liquidarOrdenesVentanillaMasiva.getFolio(), Types.VARCHAR);
						vCallStatement.setObject("p_Fecha", liquidarOrdenesVentanillaMasiva.getFecha(), Types.VARCHAR);
						vCallStatement.setObject("p_IdUsuario", pUsuario, Types.VARCHAR);
						vCallStatement.setObject("p_IdPersonaRecibe", pIdPersonaRecibe, Types.VARCHAR);
						vCallStatement.setObject("p_OtrosRecibe", pOtros, Types.VARCHAR);
						vCallStatement.setObject("p_Comentarios", pComentarios, Types.VARCHAR);
						vCallStatement.execute();
						vCallStatement.clearParameters();
						vRes = "OK";
						//ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
						//AccesoBD.CerrarStatement(vCallStatement);
					}
				}
			}catch(Exception ex){
				vRes ="Error";
			}
		}

		return vRes;
	}

}
