package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionDAO {

	public static int contadorTotalAtracciones() throws SQLException {
		String sql = "SELECT count(*) AS 'total' FROM Atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

	public static Atraccion[] findAll() throws SQLException {
		String sql = "SELECT * FROM atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		int c = contadorTotalAtracciones();
		Atraccion[] paseos = new Atraccion[c];
		for (int i = 0; i < c; i++) {
			if (resultados.next()) {
				paseos[i] = atracciones(resultados);
			}

		}

		return paseos;

	}

	public static int updateCupo(Atraccion atraccion) throws SQLException {
		String sql = "UPDATE atracciones SET cupo = ? WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);

		statement.setInt(1, atraccion.getCupoMaximoDiario());
		statement.setInt(2, atraccion.getId());
		int rows = statement.executeUpdate();

		return rows;
	}

	private static Atraccion atracciones(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt("id"), resultados.getString("nombre"), resultados.getInt("costo"),
				resultados.getInt("tiempo"), resultados.getInt("cupo"),
				TipoAtraccionDAO.buscarTipoAtraccionPorId(resultados.getInt("fk_tipo_de_atraccion")));
	}
}