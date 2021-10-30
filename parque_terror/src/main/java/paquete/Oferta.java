package paquete;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import model.Usuario;
import model.Atraccion;
import model.Promocion;
import dao.AtraccionDAO;
import dao.ItinerarioDAO;
import dao.PromocionDAO;
import dao.Atraccion_promocionDAO;

public class Oferta {

	private static Atraccion[] paseos;

	private static Promocion[] promocionesOrdenadas;

	private static ArrayList<Promocion> listaPromociones = new ArrayList<Promocion>();
	private static ArrayList<Atraccion> listaAtraccion = new ArrayList<Atraccion>();

	private static ArrayList<Integer> idAtraccionesCompradaPorUsuario = new ArrayList<Integer>();

	public static void creadorPaseos(Atraccion[] atracciones) {
		paseos = Arrays.copyOfRange(atracciones, 0, atracciones.length);

	}

	public static void creadorPromociones(ArrayList<Promocion> arrayList) {
		listaPromociones.addAll(arrayList);

	}

	public static Atraccion obtenerAtraccionPorNombre(String nombre) {
		for (Atraccion atraccion : paseos) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	public static void creadorDeOfertas(Usuario visitante) throws IOException, SQLException {

		// declaro lo necesario
		String datosUsuarioInicial = visitante.toString();

		int contador, x;
		boolean condicion = true;

		ArrayList<Atraccion> devolucionAtracciones = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaAtraccionesPreferidas = new ArrayList<Atraccion>();
		ArrayList<Atraccion> listaAtraccionesNOPreferidas = new ArrayList<Atraccion>();
		ArrayList<Promocion> promocionesCompradas = new ArrayList<Promocion>();

		contador = 0;
		x = 0;

		// BIENVENIDA
		bienvenidaUsuario(datosUsuarioInicial, visitante.getNombre());

		// limpio las listas
		listaAtraccion.clear();

		idAtraccionesCompradaPorUsuario.clear();

		// cargo Lista Atracciones compradas por usuario anteriormente

		int[] idAtraccionesIti = ItinerarioDAO.recuperaIDAtraccionesCompradasPorUsuario(visitante.getId());

		for (int i = 0; i < idAtraccionesIti.length; i++) {
			idAtraccionesCompradaPorUsuario.add(idAtraccionesIti[i]);
		}

		int[] IdPromosIti = ItinerarioDAO.recuperaIDPromocionesCompradasPorUsuario(visitante.getId());

		for (int i = 0; i < IdPromosIti.length; i++) {

			int tipoAtraccion = PromocionDAO.recuperaTipoAtraccionPorIDPromocion(IdPromosIti[i]);
			int[] IdAtraccionesPromo = Atraccion_promocionDAO.IDAtraccionesPorPromocion(tipoAtraccion);

			for (int j = 0; j < IdAtraccionesPromo.length; j++) {

				if (!idAtraccionesCompradaPorUsuario.contains(IdAtraccionesPromo[j])) {
					idAtraccionesCompradaPorUsuario.add(IdAtraccionesPromo[j]);
				}
			}
		}

		for (int i = 0; i < idAtraccionesCompradaPorUsuario.size(); i++) {

		}

		// cargo lista atraccion
		for (int i = 0; i < paseos.length; i++) {
			listaAtraccion.add(paseos[i]);
		}

		// ordeno la lista promocion

		Collections.sort(listaPromociones, new ComparaPromociones());

		// creo el array promocionesOrdenadas

		promocionesOrdenadas = new Promocion[listaPromociones.size()];
		listaPromociones.toArray(promocionesOrdenadas);

		// Separo la lista atracciones en lista preferida y lista no preferida
		for (int i = 0; i < listaAtraccion.size(); i++) {
			if (listaAtraccion.get(i).getTipoAtraccion().equalsIgnoreCase(visitante.getAtraccionPreferida())) {
				listaAtraccionesPreferidas.add(listaAtraccion.get(i));

			} else {
				listaAtraccionesNOPreferidas.add(listaAtraccion.get(i));

			}
		}

		// ordeno las listas de atracciones prefe y no prefe

		Collections.sort(listaAtraccionesPreferidas, new ComparaAtracciones());
		Collections.sort(listaAtraccionesNOPreferidas, new ComparaAtracciones());

		// -----------------------------------------------------

		// ACA SE COMIENZA A OFRECER PROMOCIONES PREFERIDAS

		while (contador < promocionesOrdenadas.length && condicion) {

			// El siguiente bucle controla que haya cupo en todas las atracciones de la
			// promo
			x = 0;
			while (x < promocionesOrdenadas[contador].getAtraccion().length && condicion) {

				if (promocionesOrdenadas[contador].getAtraccionElemento(x).getCupoMaximoDiario() < 0) {
					condicion = false;

				}

				x++;
			}

			if (condicion && controlaPromocionPrefe(promocionesOrdenadas[contador], visitante)) {

				if (ofrecePromocion(promocionesOrdenadas[contador])) {

					promocionesCompradas.add(promocionesOrdenadas[contador]);

					for (int a = 0; a < promocionesOrdenadas[contador].getAtraccion().length; a++) {
						// aca restamos las atracciones de la promocion del arraylist
						// promocionesPreferidas
						int c = listaAtraccionesPreferidas.size();
						int z = 0;
						boolean prueba = true;
						while (z <= c && prueba) {
							if (listaAtraccionesPreferidas.get(z).getId() == promocionesOrdenadas[contador]
									.getAtraccionElemento(a).getId()) {
								listaAtraccionesPreferidas.remove(z);
								prueba = false;
							}
							z++;
						}

						for (int q = 0; q < paseos.length; q++) {
							if (paseos[q].getNombre().equalsIgnoreCase(
									promocionesOrdenadas[contador].getAtraccionElemento(a).getNombre())) {
								paseos[q].setCupoMaximoDiario(paseos[q].getCupoMaximoDiario() - 1);
							}
						}
					}

					restaPresupuestoYTiempoVisitantePromo(visitante, promocionesOrdenadas[contador]);

				}
			}

			contador++;
		}

		// ACA COMIENZA A OFRECER ATRACCIONES PREFERIDAS

		if (listaAtraccionesPreferidas.size() != 0) {
			for (int i = 0; i < listaAtraccionesPreferidas.size(); i++) {

				if (tieneDineroYTiempoAtracciones(visitante, listaAtraccionesPreferidas, i)
						&& listaAtraccionesPreferidas.get(i).getCupoMaximoDiario() > 0) {

					if (ofertaAtraccion(visitante, listaAtraccionesPreferidas.get(i))) {

						devolucionAtracciones.add(listaAtraccionesPreferidas.get(i));

						restaPresupuestoYTiempoVisitanteAtraccion(visitante, listaAtraccionesPreferidas.get(i));

					}
				}
			}

		}

		/* aca se debe ofrecer promociones que no le gustan */

		contador = 0;
		x = 0;
		while (contador < promocionesOrdenadas.length && condicion) {

			while (x < promocionesOrdenadas[contador].getAtraccion().length && condicion) {
				if (promocionesOrdenadas[contador].getAtraccionElemento(x).getCupoMaximoDiario() < 0) {
					condicion = false;

				}

				x++;
			}

			if (condicion && controlaPromocionNoPrefe(promocionesOrdenadas[contador], visitante)) {

				if (ofrecePromocion(promocionesOrdenadas[contador])) {

					promocionesCompradas.add(promocionesOrdenadas[contador]);
					for (int a = 0; a < promocionesOrdenadas[contador].getAtraccion().length; a++) {

						int c = listaAtraccionesNOPreferidas.size();
						int z = 0;
						boolean prueba = true;

						while (z <= c && prueba) {

							if (listaAtraccionesNOPreferidas.get(z).getId() == promocionesOrdenadas[contador]
									.getAtraccionElemento(a).getId()) {
								listaAtraccionesNOPreferidas.remove(z);

								prueba = false;
							}
							z++;
						}
						for (int q = 0; q < paseos.length; q++) {
							if (paseos[q].getNombre().equalsIgnoreCase(
									promocionesOrdenadas[contador].getAtraccionElemento(a).getNombre())) {
								paseos[q].setCupoMaximoDiario(paseos[q].getCupoMaximoDiario() - 1);
							}
						}
					}

					restaPresupuestoYTiempoVisitantePromo(visitante, promocionesOrdenadas[contador]);

				}
			}
			contador++;
		}
		// OFRECE ATRACCIONES NO PREFERIDAS

		if (listaAtraccionesNOPreferidas.size() != 0) {
			for (int w = 0; w < listaAtraccionesNOPreferidas.size(); w++) {

				if (tieneDineroYTiempoAtracciones(visitante, listaAtraccionesNOPreferidas, w)
						&& listaAtraccionesNOPreferidas.get(w).getCupoMaximoDiario() > 0) {

					if (ofertaAtraccion(visitante, listaAtraccionesNOPreferidas.get(w))) {

						devolucionAtracciones.add(listaAtraccionesNOPreferidas.get(w));

						restaPresupuestoYTiempoVisitanteAtraccion(visitante, listaAtraccionesNOPreferidas.get(w));

					}

				}
			}
		}

		Itinerario.creadorItinerario(datosUsuarioInicial, visitante, devolucionAtracciones, promocionesCompradas);

		actualizarCupoAtracciones(paseos);

	}

	private static void actualizarCupoAtracciones(Atraccion[] paseos) throws SQLException {
		for (int a = 0; a < paseos.length; a++) {
			AtraccionDAO.updateCupo(paseos[a]);
		}
	}

	public static void restaPresupuestoYTiempoVisitantePromo(Usuario visitante, Promocion promocionComprada) {
		visitante.setPresupuesto(visitante.getPresupuesto() - promocionComprada.calculoPromocion());
		visitante.setTiempoDisponible(visitante.getTiempoDisponible() - promocionComprada.getTiempoPromedio());

		System.out.println("<Monedas disponibles: " + visitante.getPresupuesto() + "\tTiempo disponible: "
				+ visitante.getTiempoDisponible() + ">\n");

	}

	public static void restaPresupuestoYTiempoVisitanteAtraccion(Usuario visitante, Atraccion AtraccionComprada) {

		AtraccionComprada.setCupoMaximoDiario(AtraccionComprada.getCupoMaximoDiario() - 1);
		visitante.setPresupuesto(visitante.getPresupuesto() - AtraccionComprada.getCostoVisita());
		visitante.setTiempoDisponible(visitante.getTiempoDisponible() - AtraccionComprada.getTiempoPromedio());

		System.out.println("<Monedas disponibles: " + visitante.getPresupuesto() + "\tTiempo disponible: "
				+ visitante.getTiempoDisponible() + ">\n");

	}

	public static boolean controlaPromocionPrefe(Promocion promocionOfertada, Usuario visitante) throws SQLException {

		/*
		 * Verifica que la atraccion preferida del visitante sea igual al tipo de
		 * atraccion de la promocion ofertada. luego verifica que el visitante tenga
		 * tiempo y presupuesto sufieciente para comprar la promocion
		 */
		return visitante.getAtraccionPreferida().equalsIgnoreCase(promocionOfertada.getTipoAtraccion())
				&& controlPromocionesYaOfrecidas(promocionOfertada, visitante)
				&& tieneDineroYTiempoAtraccionesPromos(promocionOfertada, visitante);
	}

	public static boolean tieneDineroYTiempoAtraccionesPromos(Promocion promocionOfertada, Usuario visitante)
			throws SQLException {
		return visitante.getTiempoDisponible() >= promocionOfertada.getTiempoPromedio()
				&& visitante.getPresupuesto() >= promocionOfertada.calculoPromocion();
	}

	private static boolean controlPromocionesYaOfrecidas(Promocion promocionOfertada, Usuario visitante)
			throws SQLException {
		boolean condicion = true;
		int i = 0;
		int[] promocionesCompradas = ItinerarioDAO.recuperaIDPromocionesCompradasPorUsuario(visitante.getId());

		while (i < promocionesCompradas.length && condicion) {

			condicion = !(promocionOfertada.getId() == promocionesCompradas[i]);
			i++;

		}
		condicion = controlAtraccionesEnPromocion(promocionOfertada);

		return condicion;
	}

	private static boolean controlAtraccionesEnPromocion(Promocion promocionOfertada) throws SQLException {
		// Si hay una atraccion comprada dentro de la promocion ofrecida, este metodo
		// devuelve false.
		int tipoAtraccion = PromocionDAO.recuperaTipoAtraccionPorIDPromocion(promocionOfertada.getId());
		int[] IdAtraccionesPromo = Atraccion_promocionDAO.IDAtraccionesPorPromocion(tipoAtraccion);
		int c = 0, i = 0;
		boolean condicion = true;

		while (i < idAtraccionesCompradaPorUsuario.size() && condicion) {
			c = 0;
			while (c < IdAtraccionesPromo.length) {
				condicion = !(IdAtraccionesPromo[c] == idAtraccionesCompradaPorUsuario.get(i));
				
				c++;
			}
			i++;
		}

		return condicion;
	}

	public static boolean controlaPromocionNoPrefe(Promocion promocionOfertada, Usuario visitante) throws SQLException {
		return !(visitante.getAtraccionPreferida().equalsIgnoreCase(promocionOfertada.getTipoAtraccion()))
				&& tieneDineroYTiempoAtraccionesPromos(promocionOfertada, visitante)
				&& controlPromocionesYaOfrecidas(promocionOfertada, visitante);

	}

	private static boolean tieneDineroYTiempoAtracciones(Usuario visitante, ArrayList<Atraccion> lista, int w)
			throws SQLException {
		return (visitante.getPresupuesto() >= lista.get(w).getCostoVisita()
				&& visitante.getTiempoDisponible() >= lista.get(w).getTiempoPromedio())
				&& controlAtraccionesYaOfrecidas(lista.get(w).getId(), visitante);
	}

	private static boolean controlAtraccionesYaOfrecidas(int IdAtraccionOfertada, Usuario visitante)
			throws SQLException {

		for (int i = 0; i < idAtraccionesCompradaPorUsuario.size(); i++) {

			if (IdAtraccionOfertada == idAtraccionesCompradaPorUsuario.get(i)) {

				return false;
			}

		}

		return true;
	}

	private static boolean ofertaAtraccion(Usuario visitante, Atraccion paseo) {

		char entradaUs = ' ';

		System.out.println("¿Quiere comprar el pase a la atracción " + paseo.getNombre() + "? ");
		System.out.println("Costo: " + paseo.getCostoVisita() + " monedas.\tDuración: " + paseo.getTiempoPromedio()
				+ " minutos.\n");

		while (!(entradaUs == 's' || entradaUs == 'n')) {
			System.out.println(
					"Ingrese la letra 's' si quiere comprar la atracción, de lo contrario ingrese la letra 'n'");

			entradaUs = entradaCar();

		}

		if (entradaUs == 's') {
			return true;
		} else {
			return false;
		}
	}

	private static char entradaCar() {
		Scanner in = new Scanner(System.in);
		char entradaUs = ' ';

		entradaUs = in.next().charAt(0);

		in.nextLine();
		entradaUs = Character.toLowerCase(entradaUs);

		return entradaUs;

	}

	private static boolean ofrecePromocion(Promocion promociones) {

		char entradaUsuario = ' ';

		System.out.println("¿Quiere comprar el pase a la promoción: " + promociones.getNombre() + "? ");
		System.out.println("Incluye las siguientes atracciones:");
		for (int i = 0; i < promociones.getAtraccion().length; i++) {
			System.out.println("\tAtraccion N°" + (i + 1) + ": " + promociones.getAtraccion()[i].getNombre());
		}
		System.out.println("Costo: " + promociones.calculoPromocion() + " monedas.\tDuración "
				+ promociones.getTiempoPromedio() + " minutos.");

		System.out.println("");
		while (!(entradaUsuario == 's' || entradaUsuario == 'n')) {
			System.out.println(
					"Ingrese la letra 's' si quiere comprar la promoción, de lo contrario ingrese la letra 'n'");
			entradaUsuario = entradaCar();

		}
		return (entradaUsuario == 's');

	}

	private static void bienvenidaUsuario(String datos, String nombre) {
		System.out.println("---------------------- BIENVENIDO AL PARQUE DEL TERROR ---------------------\n");
		System.out.println(datos);
		System.out.println("Hola " + nombre);
		System.out.println("¿Comenzamos?\nEstas son tus sugerencias:\n");
	}

}