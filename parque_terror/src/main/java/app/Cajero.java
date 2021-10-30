package app;

import java.io.IOException;
import java.sql.SQLException;
import dao.AtraccionDAO;
import dao.PromocionDAO;
import dao.UsuarioDAO;
import paquete.Oferta;
import model.Usuario;

public class Cajero {

	private static void recorredorUsuarios() throws SQLException, IOException {

		int c = UsuarioDAO.contadorTotalUsuarios();

		for (int i = 1; i <= c; i++) {
			Usuario visitante = UsuarioDAO.buscaUsuarioPorID(i);
			Oferta.creadorDeOfertas(visitante);

		}

	}

	public static void main(String[] args) throws SQLException, IOException {

		Oferta.creadorPaseos(AtraccionDAO.findAll());

		Oferta.creadorPromociones(PromocionDAO.recuperaPromociones());

		recorredorUsuarios();

	}

}