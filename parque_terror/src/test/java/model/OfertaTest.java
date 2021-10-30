package model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import org.junit.Test;
import paquete.Oferta;

public class OfertaTest {

	@Test
	public void restaPresupuestoYTiempoVisitantePromoTest() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "adrenalina");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "Mansion Embrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		Oferta.restaPresupuestoYTiempoVisitantePromo(usuario1, promocion1);

		int presupuestoEsperado = 100;
		int tiempoEsperado = 150;
		assertEquals(presupuestoEsperado, usuario1.getPresupuesto());
		assertEquals(tiempoEsperado, usuario1.getTiempoDisponible());

	}

	@Test
	public void restaPresupuestoYTiempoVisitanteAtraccionesTest() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "adrenalina");

		Atraccion atraccionesLocal2 = new Atraccion(1, "calaveras", 50, 40, 10, "paseo");

		Oferta.restaPresupuestoYTiempoVisitanteAtraccion(usuario1, atraccionesLocal2);

		int presupuestoEsperado = 120;
		int tiempoEsperado = 210;
		assertEquals(presupuestoEsperado, usuario1.getPresupuesto());
		assertEquals(tiempoEsperado, usuario1.getTiempoDisponible());

	}

	@Test
	public void tieneDineroYTiempoAtraccionesTest() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "adrenalina");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertTrue(Oferta.tieneDineroYTiempoAtraccionesPromos(promocion1, usuario1));

	}

	@Test
	public void tieneDineroYTiempoAtraccionesTestFalse() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 50, 250, "adrenalina");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertFalse(Oferta.tieneDineroYTiempoAtraccionesPromos(promocion1, usuario1));

	}

	@Test
	public void controlaPromocionPrefeTest() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "adrenalina");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertTrue(Oferta.controlaPromocionPrefe(promocion1, usuario1));

	}

	@Test
	public void controlaPromocionPrefeTestFalse() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "paseo");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		PromocionAxB promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertFalse(Oferta.controlaPromocionPrefe(promocion1, usuario1));

	}

	@Test
	public void controlaPromocionNoPrefeTest() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "paseo");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertTrue(Oferta.controlaPromocionNoPrefe(promocion1, usuario1));

	}

	@Test
	public void controlaPromocionNoPrefeTestFalse() throws IOException, SQLException {

		Usuario usuario1 = new Usuario(1, "Martina", 170, 250, "adrenalina");

		Atraccion atraccioneslocal[] = { new Atraccion(1, "MansionEmbrujada", 70, 60, 10, "adrenalina"),
				new Atraccion(2, "tren fantasma", 150, 40, 15, "adrenalina") };

		Promocion promocion1 = new PromocionAxB(1, "pack adrenalina", "AxB", "adrenalina", atraccioneslocal);

		assertFalse(Oferta.controlaPromocionNoPrefe(promocion1, usuario1));

	}
}
