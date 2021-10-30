package model;

public class Usuario {
	
	private int id;
	private String nombre;
	private int presupuesto ;
	private int tiempoDisponible;
	private String atraccionPreferida;

	public Usuario(int id, String nombre, int presupuesto, int tiempoDisponible, String atraccionPreferida) {

		this.id = id;
		this.nombre = nombre;
		this.presupuesto = presupuesto;
		this.tiempoDisponible = tiempoDisponible;
		this.atraccionPreferida = atraccionPreferida;
	}

	public int getId() {
		return id;
	}

	public void setPresupuesto(int presupuesto) {
		this.presupuesto = presupuesto;
	}

	public void setTiempoDisponible(int tiempoDisponible) {
		this.tiempoDisponible = tiempoDisponible;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPresupuesto() {
		return presupuesto;
	}

	public int getTiempoDisponible() {
		return tiempoDisponible;
	}

	public String getAtraccionPreferida() {
		return atraccionPreferida;
	}

	@Override
	public String toString() {
		return " Id " + id + " | Usuario: " + nombre + " | Presupuesto: " + presupuesto
				+ " monedas | Tiempo Disponible = " + tiempoDisponible + " minutos | Preferencia: " + atraccionPreferida
				+ "\n";
	}

}