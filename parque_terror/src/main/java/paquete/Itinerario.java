package paquete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ItinerarioDAO;
import dao.UsuarioDAO;
import model.Atraccion;
import model.Promocion;
import model.Usuario;

public class Itinerario {

	public static void creadorItinerario(String datosUsuarioInicial, Usuario visitante,
			ArrayList<Atraccion> devolucionAtracciones, ArrayList<Promocion> promocionesCompradas)
			throws IOException, SQLException {

		System.out.println("-------------------------------- ITINERARIO --------------------------------\n");
		System.out.println("Estado inicial\n" + datosUsuarioInicial);
		System.out.println("¡Muchas gracias por su compra " + visitante.getNombre() + "!\n");

		int costoTotal = 0;
		int tiempoTotal = 0;

		if (devolucionAtracciones.size() != 0) {

			System.out.println("Adquirió las siguientes atracciones: \n");

			for (int i = 0; i < devolucionAtracciones.size(); i++) {
				System.out.println("\t" + devolucionAtracciones.get(i));
				costoTotal += devolucionAtracciones.get(i).getCostoVisita();
				tiempoTotal += devolucionAtracciones.get(i).getTiempoPromedio();

				ItinerarioDAO.insertAtracciones(visitante, devolucionAtracciones.get(i));

			}
		}

		if (promocionesCompradas.size() != 0) {

			System.out.println("Adquirió las siguientes promociones: \n");

			for (int i = 0; i < promocionesCompradas.size(); i++) {
				System.out.println("\t" + promocionesCompradas.get(i));
				costoTotal += promocionesCompradas.get(i).calculoPromocion();
				tiempoTotal += promocionesCompradas.get(i).getTiempoPromedio();

				ItinerarioDAO.insertPromociones(visitante, promocionesCompradas.get(i));
			}
		}

		int horas = tiempoTotal / 60;
		int minutos = tiempoTotal % 60;

		System.out.println("El costo total es de " + costoTotal + " monedas.\n");
		System.out
				.println("El tiempo necesario para su itinerario es " + horas + " horas y " + minutos + " minutos.\n");
		System.out.println("Estado final\n" + visitante.toString() + "\n");
		System.out.println("Disfrute de su paseo.");
		System.out.println("----------------------------------------------------------------------------\n");

		UsuarioDAO.updatePresupuestoYtiempo(visitante);

	}

}