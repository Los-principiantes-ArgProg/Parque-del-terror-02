package model;

public class PromocionPorcentual extends Promocion {

	private int descuento;

	public PromocionPorcentual(int id, String nombre, String tipo, String tipoAtraccion, Atraccion[] atracciones,
			int descuento) {
		super(id, nombre, tipo, tipoAtraccion, atracciones);
		this.descuento = descuento;
	}

	public int calculoPromocion() {
		int devolucionCalculo = 0;
		for (int c = 0; c < atracciones.length; c++) {
			devolucionCalculo += atracciones[c].getCostoVisita();
		}

		int calculoDescuento = (devolucionCalculo * descuento) / 100;

		return devolucionCalculo - calculoDescuento;

	}

}