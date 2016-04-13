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

import com.datacode.avon_ots_ws.model.EntregaVentanillaMasiva;

public class EntregaVentanillaMasivaController {

	private Connection _connection;
	private DecimalFormat _decimalFormat = null;
	
	/**
	 * Método que nos devuelve las ultimas 5 campanias
	 * @author: Andres Alvarez
	 * @date: 05/05/2014
	 * @return: Devuelve un arreglo con las campanias
	 */
	public List<String> ObtenerCampania() {
		_connection = AccesoBD.AbrirConexionOTS();
		List<String> vLista = new ArrayList<String>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaObtenerCampanias()}");
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {					
					vLista.add(vResultSet.getString("Campania"));
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}			
		}
		return vLista;
	}
	 
	/**
	 * Método que nos devuelve las ordenes con las que cuenta la representante
	 * @author: Andres Alvarez
	 * @date: 05/05/2014 
	 * @param pRegistro: Obtiene el registro que ingreso el usuario
	 * @param pIdUsuario: Obtiene el id del usuario logeado
	 * @param pCampania: Obtiene la campania seleccionada
	 * @return: Devuelve un arreglo con los datos de representantes
	 */
	public EntregaVentanillaMasiva[] ObtenerOrdenesRepresentante(String pRegistro, String pCampania, int pIdUsuario){
		_connection = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanillaMasiva> vLista = new ArrayList<EntregaVentanillaMasiva>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaObtenerRepresentante(?,?)}");
				vCallStatement.setObject("p_Registro", pRegistro, Types.VARCHAR);				
				vCallStatement.setObject("p_Campania",  pCampania, Types.VARCHAR);
				DecimalFormatSymbols vSymbol = new DecimalFormatSymbols();
				vSymbol.setDecimalSeparator('.');
				_decimalFormat = new DecimalFormat("#0.00", vSymbol);
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {
					EntregaVentanillaMasiva vRes = new EntregaVentanillaMasiva();					
					vRes.setRegistro(vResultSet.getString("Registro"));
					vRes.setNombre(vResultSet.getString("Nombre"));
					vRes.setDireccion(vResultSet.getString("Domicilio"));
					vRes.setCajas(vResultSet.getString("Cajas"));
					vRes.setUnitarios(vResultSet.getString("Unitarios"));
					vRes.setPremios(vResultSet.getString("Premios"));
					vRes.setMontoCobrar(_decimalFormat.format(Double.parseDouble(vResultSet.getString("MontoACobrar"))));
					vRes.setIdOrden(vResultSet.getString("IdOrden"));
					vRes.setIdZona(vResultSet.getString("IdZona"));
					vRes.setNumeroOrden(vResultSet.getString("CLAVE_ORDEN"));
					vRes.setZona(vResultSet.getString("Zona"));
					vLista.add(vRes);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M15", "Error al cargar ordenes de representante", ex.getMessage(), pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}
		}
		return (EntregaVentanillaMasiva[]) vLista.toArray(new EntregaVentanillaMasiva[vLista.size()]);
	}
	
	/**
	 * Método que devuelve los items pertenecientes a la orden
	 * @author: Andres Alvarez
	 * @date: 05/05/2014 
	 * @param pIdOrden: Obtiene el id de la orden seleccionada
	 * @param pIdUsuario: Obtiene el id del usuario logeado
	 * @return: Devuelve los item relacionados a la orden
	 */
	public EntregaVentanillaMasiva[] ObtenerDetalleOrden(String pIdOrden, int pIdUsuario){
		_connection = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanillaMasiva> vLista = new ArrayList<EntregaVentanillaMasiva>();
		CallableStatement vCallStatement = null;

		if (_connection != null) {
			try {
				vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaObtenerDetallesOrden(?)}");
				vCallStatement.setObject("p_IdOrden", pIdOrden, Types.VARCHAR);
				ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
				while (vResultSet.next()) {
					EntregaVentanillaMasiva vRes = new EntregaVentanillaMasiva();
					vRes.setIdItem(vResultSet.getString("IdItem"));
					vRes.setRegistro(vResultSet.getString("Registro"));
					vRes.setNombre(vResultSet.getString("Nombre"));
					vRes.setCodigo(vResultSet.getString("Codigo"));
					vRes.setCodigoFSC(vResultSet.getString("CodigoFSC"));
					vRes.setCodigoEAN13(vResultSet.getString("CodigoEAN13"));
					vRes.setTipo(vResultSet.getString("Tipo"));
					vRes.setDescripcion(vResultSet.getString("Descripcion"));
					vRes.setEstatus(vResultSet.getString("Estatus"));
					vRes.setCantidad(vResultSet.getString("Cantidad"));
					vRes.setEscaneado(vResultSet.getString("Escaneado"));
					vLista.add(vRes);
				}
				vResultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M16", "Error al cargar detalle de la orden", ex.getMessage(),pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}
		}
		return (EntregaVentanillaMasiva[]) vLista.toArray(new EntregaVentanillaMasiva[vLista.size()]);
	}

	@SuppressWarnings("unused")
	public String ActualizaEntregaVentanilla(EntregaVentanillaMasiva[] pCajas, EntregaVentanillaMasiva[] pUnitarios, /*EntregaVentanillaMasiva[] pPremios,*/ String pIdUsuario, String pIdOrden){
		_connection = AccesoBD.AbrirConexionOTS();
		String vMensaje = "";
		CallableStatement vCallStatement = null;
		if(_connection != null){
			try{				
				if(pCajas.length > 0){
					for (EntregaVentanillaMasiva entregaVentanillaMasiva : pCajas) {
						vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaActualiza(?,?,?,?,?,?)}");
						vCallStatement.setObject("p_IdItem", entregaVentanillaMasiva.getIdItem(), Types.VARCHAR);
						vCallStatement.setObject("p_CodigoBarras", entregaVentanillaMasiva.getCodigo(), Types.VARCHAR);
						vCallStatement.setObject("p_Cantidad", entregaVentanillaMasiva.getEscaneado(), Types.VARCHAR);
						vCallStatement.setObject("p_IdOrden", entregaVentanillaMasiva.getIdOrden(), Types.VARCHAR);				
						vCallStatement.setObject("p_IdUsuario", pIdUsuario, Types.VARCHAR);
						vCallStatement.setObject("p_Registro", entregaVentanillaMasiva.getRegistro(), Types.VARCHAR);
						//vCallStatement.execute();
						ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
						AccesoBD.CerrarStatement(vCallStatement);
					}
				}
				if(pUnitarios.length > 0){
					for (EntregaVentanillaMasiva entregaVentanillaMasiva : pUnitarios) {
						vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaActualiza(?,?,?,?,?,?)}");
						vCallStatement.setObject("p_IdItem", entregaVentanillaMasiva.getIdItem(), Types.VARCHAR);
						vCallStatement.setObject("p_CodigoBarras", entregaVentanillaMasiva.getCodigoFSC(), Types.VARCHAR);
						vCallStatement.setObject("p_Cantidad", entregaVentanillaMasiva.getEscaneado(), Types.VARCHAR);
						vCallStatement.setObject("p_IdOrden", pIdOrden, Types.VARCHAR);				
						vCallStatement.setObject("p_IdUsuario", pIdUsuario, Types.VARCHAR);
						vCallStatement.setObject("p_Registro", entregaVentanillaMasiva.getRegistro(), Types.VARCHAR);
						//vCallStatement.execute();
						ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
						AccesoBD.CerrarStatement(vCallStatement);
					}
				}
				/*if(pPremios.length > 0){
					for (EntregaVentanillaMasiva entregaVentanillaMasiva : pPremios) {
						vCallStatement = _connection.prepareCall("{call SP_EntregaVentanillaMasivaActualiza(?,?,?,?,?)}");
						vCallStatement.setObject("p_IdItem", entregaVentanillaMasiva.getIdItem(), Types.VARCHAR);
						vCallStatement.setObject("p_CodigoBarras", entregaVentanillaMasiva.getCodigo(), Types.VARCHAR);
						vCallStatement.setObject("p_Cantidad", entregaVentanillaMasiva.getEscaneado(), Types.VARCHAR);
						vCallStatement.setObject("p_IdOrden", pIdOrden, Types.VARCHAR);				
						vCallStatement.setObject("p_IdUsuario", pIdUsuario, Types.VARCHAR);
						//vCallStatement.execute();
						ResultSet vResultSet = AccesoBD.executeRetrieveResultSet(vCallStatement);
						AccesoBD.CerrarStatement(vCallStatement);
					}
				}*/
	            
				vMensaje = "Actualización Correcta";
				
				
			}catch(SQLException sqlex)
			{
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M18", "Excepción al actualizar items.", sqlex.getMessage(), Id_Usuario);
				return "Excepción; Revise log para detalles";
			}finally{
				//Cierra el objetos para liberar conexión
				AccesoBD.CerrarConexion(_connection);
			}
		}
		return vMensaje;
	}
	
	public EntregaVentanillaMasiva[] ObtenerRepresentantes(String pRegistro, String pNombre, int pIdUsuario){
		_connection = AccesoBD.AbrirConexionOTS();
		List<EntregaVentanillaMasiva> vLista = new ArrayList<EntregaVentanillaMasiva>();
		CallableStatement cs = null;

		if (_connection != null) {
			try {
				cs = _connection.prepareCall("{call SP_PW_Busca_Representante(?,?)}");
				cs.setObject("p_REGISTRO", pRegistro, Types.VARCHAR);
				cs.setObject("p_NOMBRE", pNombre, Types.VARCHAR);
				ResultSet resultSet = AccesoBD.executeRetrieveResultSet(cs);
				while (resultSet.next()) {
					EntregaVentanillaMasiva catRepre = new EntregaVentanillaMasiva();
					catRepre.setRegistro(resultSet.getString("ACCOUNT"));
					catRepre.setNombre(resultSet.getString("NOMBRE"));
					catRepre.setDireccion(resultSet.getString("DIRECCION"));
					catRepre.setIdZona(resultSet.getString("ID_ZONA"));
					catRepre.setZona(resultSet.getString("ZONA"));
					catRepre.setMontoCobrar(resultSet.getString("MONTO_A_COBRAR"));
					vLista.add(catRepre);
				}
				resultSet.close();
			} catch (SQLException ex) {
				//Utils.GuardarLogMensajeBD("CUADMIN002.02.02", "M14", "Error al cargar representantes", ex.getMessage(),pIdUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarConexion(_connection);
			}
		}
		return (EntregaVentanillaMasiva[]) vLista.toArray(new EntregaVentanillaMasiva[vLista.size()]);
	}
}
