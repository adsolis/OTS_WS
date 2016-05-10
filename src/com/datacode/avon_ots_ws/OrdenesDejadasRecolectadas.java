package com.datacode.avon_ots_ws;

import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import com.datacode.avon_ots_ws.model.CajaOrdenDejadaRecolectadaPUPDTO;
import com.datacode.avon_ots_ws.model.DocumentoOrdenDejadaRecolectadaPUPDTO;
import com.datacode.avon_ots_ws.model.PUPDTO;
import com.datacode.avon_ots_ws.model.PremioUnitarioOrdenDejadaRecolectadaPUPDTO;

public class OrdenesDejadasRecolectadas {
	
	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;
	
	public OrdenesDejadasRecolectadas() {}
	
	public List<PUPDTO> obtenerPUPOrdenesDejadasRecolectadas(String tipoLiquidacion, long idSalidaReparto,int idEstatus, int idUsuario) {
		connection = AccesoBD.AbrirConexionOTS();
		List<PUPDTO> resultado = new ArrayList<PUPDTO>();
		PUPDTO res = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_CONSULTAR_ORDENES_DEJADAS_RECOLECTADAS_PUP(?,?)}");
				callableStatement.setObject("ID_SALIDA_REPARTO",
						idSalidaReparto, Types.BIGINT);
				callableStatement.setObject("ID_ESTATUS",
						idEstatus, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				System.out.println("ya ejecuto");
				while (resultSet.next()) {
					res = new PUPDTO();

					res.setIdPUP(resultSet.getInt("ID_PUP"));
					res.setCorreo(resultSet.getString("CORREO"));
					res.setNombre(resultSet.getString("NOMBRE"));

					resultado.add(res);
					System.out.println(res.getCorreo());
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener las ordenes dejadas/recolectadas en PUP. Método: obtenerPUPOrdenesDejadasRecolectadas",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			}
			finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return resultado;
	}
	
	public List<CajaOrdenDejadaRecolectadaPUPDTO> obtenerCajasOrdenDejadaRecolectada(int idEstatus,long idSalidaReparto,long idPUP, int idUsuario) {
		connection = AccesoBD.AbrirConexionOTS();
		List<CajaOrdenDejadaRecolectadaPUPDTO> listaCajas = new ArrayList<CajaOrdenDejadaRecolectadaPUPDTO>();
		CajaOrdenDejadaRecolectadaPUPDTO rodrpup = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_OBTENER_REPORTE_ORDENES_DEJADAS_RECOLECTADAS_PUP(?,?,?,?)}");
				callableStatement.setObject("DETALLE",
						1, Types.INTEGER);
				callableStatement.setObject("ID_SALIDA_REPARTO",
						idSalidaReparto, Types.BIGINT);
				callableStatement.setObject("ID_PUP",
						idPUP, Types.BIGINT);
				callableStatement.setObject("ID_ESTATUS",
						idEstatus, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					rodrpup = new CajaOrdenDejadaRecolectadaPUPDTO();
					rodrpup.setZona(resultSet.getInt("ZONA"));
					rodrpup.setCampania(resultSet.getString("CAMPANIA"));
					rodrpup.setOrden(resultSet.getLong("ORDEN"));
					rodrpup.setNombre(resultSet.getString("NOMBRE"));
					rodrpup.setRegistro(resultSet.getLong("REGISTRO"));
					rodrpup.setItem(resultSet.getString("ITEM"));
					rodrpup.setCodigoBarras(resultSet.getString("CODIGO_BARRAS"));
					
					if(idEstatus==22)
						rodrpup.setDejadoPUP(resultSet.getInt("DEJADOPUP"));
					else if(idEstatus==24)
						rodrpup.setRecolectadoPUP(resultSet.getInt("RECOLECTADOPUP"));
					
					listaCajas.add(rodrpup);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener las cajas de una orden dejada/recolectada en PUP. Método: obtenerCajasOrdenDejadaRecolectada",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaCajas;
	}
	
	public List<PremioUnitarioOrdenDejadaRecolectadaPUPDTO> obtenerPremiosUnitariosOrdenDejadaRecolectada(int idEstatus,long idSalidaReparto,long idPUP, int idUsuario) {
		connection = AccesoBD.AbrirConexionOTS();
		List<PremioUnitarioOrdenDejadaRecolectadaPUPDTO> listaPremiosUnitarios = new ArrayList<PremioUnitarioOrdenDejadaRecolectadaPUPDTO>();
		PremioUnitarioOrdenDejadaRecolectadaPUPDTO rodrpup = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_OBTENER_REPORTE_ORDENES_DEJADAS_RECOLECTADAS_PUP(?,?,?,?)}");
				callableStatement.setObject("DETALLE",
						2, Types.INTEGER);
				callableStatement.setObject("ID_SALIDA_REPARTO",
						idSalidaReparto, Types.BIGINT);
				callableStatement.setObject("ID_PUP",
						idPUP, Types.BIGINT);
				callableStatement.setObject("ID_ESTATUS",
						idEstatus, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					rodrpup = new PremioUnitarioOrdenDejadaRecolectadaPUPDTO();
					rodrpup.setFsc(resultSet.getString("FSC"));
					rodrpup.setEan13(resultSet.getString("EAN13"));
					rodrpup.setCantidad(resultSet.getInt("CANTIDAD"));
					
					if(idEstatus==22)
						rodrpup.setDejadoPUP(resultSet.getInt("DEJADOPUP"));
					else if(idEstatus==24)
						rodrpup.setRecolectadoPUP(resultSet.getInt("RECOLECTADOPUP"));
					
					listaPremiosUnitarios.add(rodrpup);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener los premios & unitarios de una orden dejada/recolectada en PUP. Método: obtenerPremiosUnitariosOrdenDejadaRecolectada",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaPremiosUnitarios;
	}
	
	public List<DocumentoOrdenDejadaRecolectadaPUPDTO> obtenerDocumentosOrdenDejadaRecolectada(int idEstatus,long idSalidaReparto,long idPUP, int idUsuario) {
		connection = AccesoBD.AbrirConexionOTS();
		List<DocumentoOrdenDejadaRecolectadaPUPDTO> listaDocumentos = new ArrayList<DocumentoOrdenDejadaRecolectadaPUPDTO>();
		DocumentoOrdenDejadaRecolectadaPUPDTO rodrpup = null;
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_OBTENER_REPORTE_ORDENES_DEJADAS_RECOLECTADAS_PUP(?,?,?,?)}");
				callableStatement.setObject("DETALLE",
						3, Types.INTEGER);
				callableStatement.setObject("ID_SALIDA_REPARTO",
						idSalidaReparto, Types.BIGINT);
				callableStatement.setObject("ID_PUP",
						idPUP, Types.BIGINT);
				callableStatement.setObject("ID_ESTATUS",
						idEstatus, Types.INTEGER);
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);

				while (resultSet.next()) {
					rodrpup = new DocumentoOrdenDejadaRecolectadaPUPDTO();
					rodrpup.setRegistro(resultSet.getLong("REGISTRO"));
					rodrpup.setCodEnviado(resultSet.getInt("CODENVIADO"));
					rodrpup.setCodRecibido(resultSet.getInt("CODRECIBIDO"));
					rodrpup.setCodRecolectado(resultSet.getInt("CODRECOLECTADO"));
					rodrpup.setRemitoEnviado(resultSet.getInt("REMITOENVIADO"));
					rodrpup.setRemitoRecibido(resultSet.getInt("REMITORECIBIDO"));
					rodrpup.setRemitoRecolectado(resultSet.getInt("REMITORECOLECTADO"));
					listaDocumentos.add(rodrpup);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener los documentos de una orden dejada/recolectada en PUP. Método: obtenerDocumentosOrdenDejadaRecolectada",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaDocumentos;
	}

}
