package model;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.Test;
import dao.Atraccion_promocionDAO;


public class AtraccionTest {

	@Test
	public void obtenerNombreTest() throws SQLException {

		Atraccion atraccion1 = Atraccion_promocionDAO.buscaAtraccionPorID(3);
		String esperado = "Castillo de Drácula";
		assertEquals(esperado, atraccion1.getNombre());
	}

	@Test
	public void obtenerCostoTest() throws SQLException {

		Atraccion atraccion2 = Atraccion_promocionDAO.buscaAtraccionPorID(4);
		int esperado = 45;
		assertEquals(esperado, atraccion2.getCostoVisita());
	}

	@Test
	public void obtenerTiempoTest() throws SQLException {

		Atraccion atraccion3 = Atraccion_promocionDAO.buscaAtraccionPorID(5);
		int esperado = 40;
		assertEquals(esperado, atraccion3.getTiempoPromedio());
	}

	@Test
	public void obtenerCupoTest() throws SQLException {

		Atraccion atraccion4 = Atraccion_promocionDAO.buscaAtraccionPorID(6);
		int esperado = 4;
		assertEquals(esperado, atraccion4.getCupoMaximoDiario());

	}

}
