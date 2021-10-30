package paquete;

import java.util.Comparator;

import model.Atraccion;

public class ComparaAtracciones implements Comparator<Atraccion> {

	@Override
	public int compare(Atraccion a1, Atraccion a2) {

		if (a1.getCostoVisita() > a2.getCostoVisita()) {
			return -1;
		} else if (a1.getCostoVisita() < a2.getCostoVisita()) {

			return 1;
		}

		if (a1.getTiempoPromedio() > a2.getTiempoPromedio()) {
			return -1;
		} else if (a1.getTiempoPromedio() < a2.getTiempoPromedio()) {

			return 1;
		}
		return 0;
	}

}