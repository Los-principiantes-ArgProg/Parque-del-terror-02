package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.Atraccion;

public class TipoAtraccionDAOtest {

	@Test
	public void buscarTipoAtraccionPorIdTest() throws SQLException {

		Atraccion atraccion1 = Atraccion_promocionDAO.buscaAtraccionPorID(1);

		String tipoDeAtraccion = atraccion1.getTipoAtraccion();
		String tipoDeAtraccionEsperada = "paseo";

		assertEquals(tipoDeAtraccionEsperada, tipoDeAtraccion);

	}

	@Test
	public void contarTipoAtracciones() throws SQLException {

		int totaltipoAtracciones = TipoAtraccionDAO.countAll();
		int totalTipoPromocionesEsperadas = 4;

		assertEquals(totalTipoPromocionesEsperadas, totaltipoAtracciones);
	}

}
