package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.ConnectionProvider;

public class TipoAtraccionDAO {

	public static String buscarTipoAtraccionPorId(int id) throws SQLException {
		String sql = "SELECT nombre FROM tipo_de_atraccion WHERE id=?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		String tipo = null;

		if (resultados.next()) {
			tipo = resultados.getString("nombre");
		}

		return tipo;
	}

	public static int countAll() throws SQLException {
		String sql = "SELECT count(*) AS 'total' FROM tipo_de_atraccion";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

}