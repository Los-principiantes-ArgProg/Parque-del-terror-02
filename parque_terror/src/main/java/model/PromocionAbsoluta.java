package model;

public class PromocionAbsoluta extends Promocion {

	private int precioPromocion;

	public PromocionAbsoluta(int id, String nombre, String tipo, String tipoAtraccion, Atraccion[] atracciones,
			int precioPromocion) {
		super(id, nombre, tipo, tipoAtraccion, atracciones);
		this.precioPromocion = precioPromocion;
	}

	public int calculoPromocion() {
		return precioPromocion;
	}
}