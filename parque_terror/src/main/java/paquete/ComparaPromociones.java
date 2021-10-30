package paquete;

import java.util.Comparator;

import model.Promocion;

public class ComparaPromociones implements Comparator<Promocion> {

	@Override
	public int compare(Promocion a1, Promocion a2) {

		if (a1.calculoPromocion() > a2.calculoPromocion()) {
			return -1;
		} else if (a1.calculoPromocion() < a2.calculoPromocion()) {

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