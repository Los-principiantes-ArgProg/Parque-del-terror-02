package dao;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import model.Usuario;

public class UsuarioDAOtest {
	
	@Test
	public void contadorDeUsuarioTest() throws SQLException {

		int cantidadUsuariosEsperada = 12;
		int cantidadUsuarios = UsuarioDAO.contadorTotalUsuarios();

		assertEquals(cantidadUsuariosEsperada, cantidadUsuarios);

	}

	@Test
	public void BuscaUsuarioPorIDtest() throws SQLException {

		Usuario usuario1 = UsuarioDAO.buscaUsuarioPorID(1);

		String nombre = usuario1.getNombre();
		assertEquals("Sergio", nombre);

	}

}
