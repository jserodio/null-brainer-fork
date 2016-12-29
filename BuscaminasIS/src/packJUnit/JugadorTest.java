package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.GestorBuscaminas;
import packCodigo.Jugador;

public class JugadorTest {

	Jugador j1;
	Jugador j2;
	Jugador j3;
	Jugador j4;
	Jugador j5;
	
	@Before
	public void setUp() throws Exception {
		j1 = new Jugador("AA");
		j2 = new Jugador("BB");
		j3 = new Jugador("CC");
		j4 = new Jugador("DD");
		j5 = new Jugador("Desconocido");
		
		j1.establecerPuntuacion(10);
		j2.establecerPuntuacion(20);
		j3.establecerPuntuacion(15);
		j4.establecerPuntuacion(10);
		j5.establecerPuntuacion(30);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareTo() {
		assertEquals(j1.compareTo(j2),1);
		assertEquals(j2.compareTo(j1),-1);
		assertEquals(j1.compareTo(j4),0);
	}

/*	@Test
	public void testMismoJugador() {
		Buscaminas.getBuscaminas().establecerNombreJugador("");
		assertTrue(j5.mismoJugador());
		assertFalse(j4.mismoJugador());
	}

	@Test
	public void testAsignarPuntuacionR() {
		Buscaminas.getBuscaminas().establecerNombreJugador("");
		Buscaminas.getBuscaminas().calcularPuntos();
		j5.asignarPuntuacionR();
		assertTrue(j5.obtenerPunt()==30);
	}*/

}