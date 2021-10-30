package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import model.Promocion;

public class PromocionDAOtest {

	@Test
	public void contadorTotalPromocionesTest() throws SQLException {

		int totalPromociones = PromocionDAO.contadorTotalpromociones();
		int totalPromocionesEsperadas = 4;

		assertEquals(totalPromocionesEsperadas, totalPromociones);
	}

	@Test
	public void recuperaPromocionesTest() throws SQLException {

		ArrayList<Promocion> promocion1 = PromocionDAO.recuperaPromociones();

		promocion1.toString();

		int size = promocion1.size();

		assertEquals(4, size);

	}

}
