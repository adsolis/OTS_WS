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
import com.datacode.avon_ots_ws.model.LiquidacionRepartoDTO;
import com.datacode.avon_ots_ws.model.PUPDTO;
import com.datacode.avon_ots_ws.model.PremioUnitarioOrdenDejadaRecolectadaPUPDTO;

public class OrdenesDejadasRecolectadas {
	
	private ResultSet resultSet;
	private CallableStatement callableStatement;
	private Connection connection;
	
	public OrdenesDejadasRecolectadas() {}
	
	/*public List<LiquidacionRepartoDTO> obtenerListaLiquidacionesMail() {
		connection = AccesoBD.AbrirConexionOTS();
		List<LiquidacionRepartoDTO> listaLiq = new ArrayList<LiquidacionRepartoDTO>();
		if (connection != null) {
			try {
				callableStatement = connection
						.prepareCall("{call SP_Obtener_Lista_Liquidaciones_Mail}");
				resultSet = AccesoBD
						.executeRetrieveResultSet(callableStatement);
				while (resultSet.next()) {
					LiquidacionRepartoDTO lr = new LiquidacionRepartoDTO();
					lr.setIdSalidaReparto(resultSet.getInt("ID_SALIDA_REPARTO"));
					lr.setEstatusCorreo(resultSet.getString("ESTATUS_CORREO"));
					lr.setEstatusCorreoDejadasPUP(resultSet.getString("ESTATUS_CORREO_DEJADAS_PUP"));
					lr.setEstatusCorreoRecolectadasPUP(resultSet.getString("ESTATUS_CORREO_RECOLECTADAS_PUP"));
					listaLiq.add(lr);
				}
				resultSet.close();
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al obtener la lista de liquidaciones a enviar : obtenerListaLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return listaLiq;
	}*/

	/*public String actualizarStatusLiquidacionesMail(String statusNuevo,
			int idSalidaReparto, String tipoLiquidacion) {
		connection = AccesoBD.AbrirConexionOTS();
		String error = "";
		if (connection != null) {
			try {
				callableStatement = connection
					.prepareCall("{call SP_Actualizar_Status_Liquidaciones_Mail(?,?)}");
				if (tipoLiquidacion.equals("subBodega")) {
					callableStatement.setObject("P_STATUS_NUEVO", statusNuevo,
							Types.VARCHAR);
				} else if (tipoLiquidacion.equals("ordenesDejadas")) {
					callableStatement.setObject("ESTATUS_CORREO_DEJADAS_PUP", statusNuevo,
							Types.VARCHAR);
				} else if (tipoLiquidacion.equals("ordenesRecolectadas")) {
					callableStatement.setObject("ESTATUS_CORREO_RECOLECTADAS_PUP ", statusNuevo,
							Types.VARCHAR);
				}
				callableStatement.setObject("P_ID_SALIDA_REPARTO",
						idSalidaReparto, Types.INTEGER);
			} catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						" ",
						"M1",
						"Surgió un error al actualizar el status de la lista de liquidaciones a enviar : actualizarStatusLiquidacionesMail",
						ex.getMessage(), 1);
				System.out.println(ex.getMessage());
				error = ex.getStackTrace().toString();
			} finally {
				AccesoBD.CerrarStatement(callableStatement);
				AccesoBD.CerrarConexion(connection);
			}
		}
		return error;
	}*/

	
	public List<PUPDTO> obtenerPUPOrdenesDejadasRecolectadas(String tipoLiquidacion, long idSalidaReparto,int idEstatus, int idUsuario) {
		System.out.println("si entra aqui");
		connection = AccesoBD.AbrirConexionOTS();
		List<PUPDTO> resultado = new ArrayList<PUPDTO>();
		PUPDTO res = null;
		System.out.println("Llego aqui");
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

					resultado.add(res);
					System.out.println(res.getCorreo());
				}
				resultSet.close();
			} /*catch (SQLException ex) {
				Utils.GuardarLogMensajeBD(
						"ReportesAdmin",
						"M4",
						"Surgió un error al obtener las ordenes dejadas/recolectadas en PUP. Método: obtenerPUPOrdenesDejadasRecolectadas",
						ex.getMessage(), idUsuario);
				System.out.println(ex.getMessage());
			}*/ catch(Exception e) {
				System.out.println("pues esta ezepzion: " + e.getMessage());
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
