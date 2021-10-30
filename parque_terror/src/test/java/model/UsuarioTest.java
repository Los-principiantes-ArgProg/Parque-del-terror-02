package model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.UsuarioDAO;
import jdbc.ConnectionProvider;

public class UsuarioTest {

	@Before
	public void setUp() throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		conn.setAutoCommit(false);
	}
	
	@After
	public void tearDown() throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		conn.rollback();
		conn.setAutoCommit(true);
	}

	@Test
	public void queUsuarioNoSeaNuloTest() throws SQLException {
		Usuario usuario1 = UsuarioDAO.buscaUsuarioPorID(1);

		assertNotNull(usuario1);
	}

	@Test
	public void getPresupuestoTest() throws SQLException {
		Usuario usuario2 = UsuarioDAO.buscaUsuarioPorID(2);
		;

		assertEquals(400, usuario2.getPresupuesto());

	}

	@Test
	public void getIdTest() throws SQLException {
		Usuario usuario2 = UsuarioDAO.buscaUsuarioPorID(2);
		;

		assertEquals(2, usuario2.getId());

	}

	@Test
	public void getTiempoTest() throws SQLException {
		Usuario usuario3 = UsuarioDAO.buscaUsuarioPorID(3);

		assertEquals(520, usuario3.getTiempoDisponible());
	}

	@Test
	public void AtraccionPreferidaTest() throws SQLException {
		Usuario usuario4 = UsuarioDAO.buscaUsuarioPorID(4);
		assertEquals("degustacion", usuario4.getAtraccionPreferida());
	}

}
