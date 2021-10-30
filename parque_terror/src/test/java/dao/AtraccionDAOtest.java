package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.Atraccion;

public class AtraccionDAOtest {

	@Test
	public void encontrarAtraccionesTest() throws SQLException {

		Atraccion[] atracciones2 = AtraccionDAO.findAll();

		int length = atracciones2.length;

		assertEquals(12, length);

	}

	@Test
	public void contadorTotalAtraccionesTest() throws SQLException {

		int cantidadAtracciones = AtraccionDAO.contadorTotalAtracciones();
		int cantidadAtraccionesEsperadas = 12;

		assertEquals(cantidadAtraccionesEsperadas, cantidadAtracciones);

	}

}
