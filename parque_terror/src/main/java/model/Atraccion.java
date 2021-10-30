package model;

public class Atraccion {

	private int id;
	private String nombre = "";
	private int costoVisita = 0;
	private int tiempoPromedio = 0;
	private int cupoMaximoDiario = 0;
	private String atraccion;

	public Atraccion(int id, String nombre, int costoVisita, int tiempoPromedio, int cupoMaximoDiario, String buscarTipoAtraccionPorId) {
		this.id = id;
		this.nombre = nombre;
		this.costoVisita = costoVisita;
		this.tiempoPromedio = tiempoPromedio;
		this.cupoMaximoDiario = cupoMaximoDiario;
		this.atraccion = buscarTipoAtraccionPorId;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getTipoAtraccion() {
		return atraccion;
	}

	public int getCostoVisita() {
		return costoVisita;
	}

	public int getTiempoPromedio() {
		return tiempoPromedio;
	}

	public int getCupoMaximoDiario() {
		return cupoMaximoDiario;
	}

	@Override
	public String toString() {
		return "Id: " + id + ", Atraccion: " + nombre + ", tipo de atraccion: " + atraccion + ", costo: " + costoVisita
				+ " monedas, tiempo promedio: " + tiempoPromedio + " minutos.\n";
	}

	public void setCupoMaximoDiario(int i) {
		cupoMaximoDiario = i;

	}

}