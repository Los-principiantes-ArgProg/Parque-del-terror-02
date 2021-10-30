package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import jdbc.ConnectionProvider;
import model.Promocion;
import model.PromocionAbsoluta;
import model.PromocionAxB;
import model.PromocionPorcentual;

public class PromocionDAO {

	public static int contadorTotalpromociones() throws SQLException {
		String sql = "SELECT count(id) AS 'total' FROM promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

	public static ArrayList<Promocion> recuperaPromociones() throws SQLException {
		String sql = "SELECT * FROM promociones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);

		ResultSet resultados = statement.executeQuery();

		int c = contadorTotalpromociones();

		ArrayList<Promocion> arregloPromociones = new ArrayList<Promocion>();
		for (int i = 0; i < c; i++) {
			if (resultados.next()) {
				if (resultados.getString("tipo_de_promocion").equalsIgnoreCase("AxB"))
					arregloPromociones.add(promocionAxB(resultados));

				if (resultados.getString("tipo_de_promocion").equalsIgnoreCase("porcentual"))
					arregloPromociones.add(promocionPorcentual(resultados));

				if (resultados.getString("tipo_de_promocion").equalsIgnoreCase("Absoluta"))
					arregloPromociones.add(promocionAbsoluta(resultados));
			}
		}

		return arregloPromociones;

	}

	// NO ENTIENDO PORQUE TRAE EL MAX
	public static int recuperaTipoAtraccionPorIDPromocion(int id) throws SQLException {
		String sql = "SELECT MAX(fk_tipo_de_atraccion) AS 'tipo'\n" + "FROM promociones\n" + "WHERE id=?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int c = resultados.getInt("tipo");
		return c;
	}

	private static PromocionAbsoluta promocionAbsoluta(ResultSet resultados) throws SQLException {
		return new PromocionAbsoluta(resultados.getInt("id"), resultados.getString("nombre"),
				resultados.getString("tipo_de_promocion"),
				TipoAtraccionDAO.buscarTipoAtraccionPorId(resultados.getInt("fk_tipo_de_atraccion")),
				Atraccion_promocionDAO.atraccionesPorPromocion(resultados.getInt("fk_tipo_de_atraccion")),
				resultados.getInt("descuento"));
	}

	private static PromocionPorcentual promocionPorcentual(ResultSet resultados) throws SQLException {
		return new PromocionPorcentual(resultados.getInt("id"), resultados.getString("nombre"),
				resultados.getString("tipo_de_promocion"),
				TipoAtraccionDAO.buscarTipoAtraccionPorId(resultados.getInt("fk_tipo_de_atraccion")),
				Atraccion_promocionDAO.atraccionesPorPromocion(resultados.getInt("fk_tipo_de_atraccion")),
				resultados.getInt("descuento"));
	}

	private static PromocionAxB promocionAxB(ResultSet resultados) throws SQLException {
		return new PromocionAxB(resultados.getInt("id"), resultados.getString("nombre"),
				resultados.getString("tipo_de_promocion"),
				TipoAtraccionDAO.buscarTipoAtraccionPorId(resultados.getInt("fk_tipo_de_atraccion")),
				Atraccion_promocionDAO.atraccionesPorPromocion(resultados.getInt("fk_tipo_de_atraccion")));
	}

}