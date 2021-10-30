package model;

public abstract class Promocion {

	private int id;
	private String nombre;
	private String tipo;
	private String tipoAtraccion;
	protected Atraccion[] atracciones;

	public Promocion(int id, String nombre, String tipo, String tipoAtraccion, Atraccion[] atracciones) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.tipoAtraccion = tipoAtraccion;
		this.atracciones = atracciones;
	}

	public String getTipoAtraccion() {
		return tipoAtraccion;
	}

	public String getTipo() {
		return tipo;
	}

	public int getTiempoPromedio() {
		int devolucionTiempo = 0;

		for (int c = 0; c < atracciones.length; c++) {
			devolucionTiempo += atracciones[c].getTiempoPromedio();
		}

		return devolucionTiempo;
	}

	public abstract int calculoPromocion();

	@Override
	public String toString() {
		String nombreAtracciones = "";

		for (int i = 0; i < atracciones.length; i++) {
			nombreAtracciones += atracciones[i].getNombre();

			if (!(i == atracciones.length - 1)) {
				nombreAtracciones += ", ";
			} else {
				nombreAtracciones += ".";
			}
		}
		return "Id: " + id + ", Nombre de Promocion: " + nombre + ", de tipo: " + tipo + ", atracciones: "
				+ nombreAtracciones + "\n\t\ttiempo promedio: " + getTiempoPromedio() + " minutos, costo: "
				+ calculoPromocion() + " monedas.\n";
	}

	public String getNombre() {
		return nombre;
	}

	public Atraccion getAtraccionElemento(int i) {
		return atracciones[i];
	}

	public Atraccion[] getAtraccion() {
		return atracciones;
	}

	public int getId() {
		return id;
	}

}