package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jdbc.ConnectionProvider;
import model.Usuario;

public class UsuarioDAO {

	public static int contadorTotalUsuarios() throws SQLException {
		String sql = "SELECT count(*) AS 'total' FROM usuarios";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultados = statement.executeQuery();

		resultados.next();
		int total = resultados.getInt("total");

		return total;
	}

	public static Usuario buscaUsuarioPorID(int id) throws SQLException {
		String sql = "SELECT * FROM usuarios WHERE id=?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet resultados = statement.executeQuery();

		Usuario visitante = null;

		// if (resultados.next()) {
		visitante = nuevoUsuario(resultados);
		// }

		return visitante;
	}

	public static int updatePresupuestoYtiempo(Usuario usuario) throws SQLException {
		String sql = "UPDATE usuarios SET presupuesto = ?, tiempo = ? WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();

		PreparedStatement statement = conn.prepareStatement(sql);
		statement.setInt(1, usuario.getPresupuesto());
		statement.setInt(2, usuario.getTiempoDisponible());
		statement.setInt(3, usuario.getId());
		int rows = statement.executeUpdate();

		return rows;
	}

	private static Usuario nuevoUsuario(ResultSet resultados) throws SQLException {

		return new Usuario(resultados.getInt("id"), resultados.getString("nombre"), resultados.getInt("presupuesto"),
				resultados.getInt("tiempo"),
				TipoAtraccionDAO.buscarTipoAtraccionPorId(resultados.getInt("fk_atraccion_favorita")));
	}

}