package model;

import static org.junit.Assert.*;
import org.junit.Test;

public class PromocionTest {

	@Test
	public void PromocionAbsolutaTest() {
		Atraccion atracciones[] = { new Atraccion(1, "Castillo de Drácula", 25, 50, 100, "adrenalina"),
				new Atraccion(2, "Nido de Dragones", 35, 40, 12, "adrenalina"),
				new Atraccion(3, "Laberinto", 50, 30, 30, "adrenalina") };
		Promocion promocion = new PromocionAbsoluta(1, "Pack Adrenalina", "Absoluta", "adrenalina", atracciones, 90);

		int costoEsperado = 90;
		int tiempoEsperado = 120;

		assertEquals(costoEsperado, promocion.calculoPromocion());
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());
	}

	@Test
	public void PromocionAxBTest() {
		Atraccion atracciones[] = { new Atraccion(1, "Bosque encantado", 60, 70, 12, "paseo"),
				new Atraccion(2, "Lago del terror", 50, 50, 40, "paseo"),
				new Atraccion(3, "Tren Fantasma", 20, 4, 7, "paseo") };

		PromocionAxB promocion = new PromocionAxB(2, "Pack Paseo", "AxB", "paseo", atracciones);
		int costoEsperado = 110;
		int tiempoEsperado = 124;
		assertEquals(costoEsperado, promocion.calculoPromocion());
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());
	}

	@Test
	public void PromocionPorcentualTest() {
		Atraccion atracciones[] = { new Atraccion(1, "La Posada de Hades", 45, 80, 15, "degustacion"),
				new Atraccion(2, "Canibalismo y cervezas", 70, 75, 40, "degustacion") };
		PromocionPorcentual promocion = new PromocionPorcentual(3, "Pack Degustacion", "porcentual", "degustacion",
				atracciones, 20);
		int costoEsperado = 92;
		int tiempoEsperado = 155;
		assertEquals(costoEsperado, promocion.calculoPromocion());
		assertEquals(tiempoEsperado, promocion.getTiempoPromedio());

	}

	@Test
	public void ObtenernNombrePromocionTest() {
		Atraccion atracciones[] = { new Atraccion(1,"Castillo de Drácula", 25, 50, 100, "adrenalina"),
				new Atraccion(2,"Nido de Dragones", 35, 40, 12, "adrenalina"),
				new Atraccion(3,"Laberinto", 50, 30, 30, "adrenalina") };
		Promocion promocion = new PromocionAbsoluta (1,"Pack Adrenalina","Absoluta", "adrenalina", atracciones,90);
		
		String nombreEsperado = "Pack Adrenalina";
		String nombre = promocion.getNombre();
		
		assertEquals(nombreEsperado, nombre);
		
	}
	
	@Test
	public void getTipoPromocionTest() {
		Atraccion atracciones[] = { new Atraccion(1,"Castillo de Drácula", 25, 50, 100, "adrenalina"),
				new Atraccion(2,"Nido de Dragones", 35, 40, 12, "adrenalina"),
				new Atraccion(3,"Laberinto", 50, 30, 30, "adrenalina") };
		Promocion promocion = new PromocionAbsoluta (1,"Pack Adrenalina","Absoluta", "adrenalina", atracciones,90);
		
		String tipoEsperado = "Absoluta";
		String tipo = promocion.getTipo();
		
		assertEquals(tipoEsperado, tipo);
		
	}
	
	@Test
	public void getTipoDeAtraccionPromocionTest() {
		Atraccion atracciones[] = { new Atraccion(1,"Castillo de Drácula", 25, 50, 100, "adrenalina"),
				new Atraccion(2,"Nido de Dragones", 35, 40, 12, "adrenalina"),
				new Atraccion(3,"Laberinto", 50, 30, 30, "adrenalina") };
		Promocion promocion = new PromocionAbsoluta (1,"Pack Adrenalina","Absoluta", "adrenalina", atracciones,90);
		
		String tipoDeAtraccionEsperado = "adrenalina";
		String tipoDeAtraccion = promocion.getTipoAtraccion();
		
		assertEquals(tipoDeAtraccionEsperado, tipoDeAtraccion);
		
	}
}
