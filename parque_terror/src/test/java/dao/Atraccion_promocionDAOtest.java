package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.Atraccion;

public class Atraccion_promocionDAOtest {

	@Test
	public void buscaAtraccionPorID() throws SQLException {

		Atraccion atraccion1 = Atraccion_promocionDAO.buscaAtraccionPorID(1);

		String nombre = atraccion1.getNombre();
		assertEquals("Bosque Encantado", nombre);

	}

	@Test
	public void contarTodasAtraccionesPromocionesTest() throws SQLException {

		int AtraccionesPorPromocion = 3;
		int AtraccionesPorPromocionEsperada = Atraccion_promocionDAO.contarTodasAtraccionesPromociones(1);

		assertEquals(AtraccionesPorPromocionEsperada, AtraccionesPorPromocion);
	}

	@Test
	public void atraccionesPorPromocionTest() throws SQLException {

		Atraccion[] atracciones1 = Atraccion_promocionDAO.atraccionesPorPromocion(1);

		int length = atracciones1.length;

		assertEquals(3, length);

	}

}
