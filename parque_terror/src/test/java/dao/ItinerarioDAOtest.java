package dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jdbc.ConnectionProvider;
import model.Atraccion;
import model.Promocion;
import model.PromocionAbsoluta;
import model.Usuario;

public class ItinerarioDAOtest {

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
	public void insertarAtraccionesTest() throws SQLException {
		Usuario usuario2 = UsuarioDAO.buscaUsuarioPorID(1);
		Atraccion atraccion1 = Atraccion_promocionDAO.buscaAtraccionPorID(3);

		ItinerarioDAO.insertAtracciones(usuario2, atraccion1);
		int cantidad = ItinerarioDAO.contadorAtraccionesItinerarioPorUsuario(1);
		int cantidadEsperada = 1;

		assertEquals(cantidadEsperada, cantidad);

	}

	@Test
	public void insertarPromociones() throws SQLException {
		Usuario usuario2 = UsuarioDAO.buscaUsuarioPorID(1);
		Atraccion atracciones[] = { new Atraccion(1, "Castillo de Drácula", 25, 50, 100, "adrenalina"),
				new Atraccion(2, "Nido de Dragones", 35, 40, 12, "adrenalina"),
				new Atraccion(3, "Laberinto", 50, 30, 30, "adrenalina") };
		Promocion promocion = new PromocionAbsoluta(1, "Pack Adrenalina", "Absoluta", "adrenalina", atracciones,
				90);

		ItinerarioDAO.insertPromociones(usuario2, promocion);
		int cantidad = ItinerarioDAO.contadorPromocionesItinerarioPorUsuario(1);
		int cantidadEsperada = 1;

		assertEquals(cantidadEsperada, cantidad);

	}
}
