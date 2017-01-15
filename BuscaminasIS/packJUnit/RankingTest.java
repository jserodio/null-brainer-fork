package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.Jugador;
import packCodigo.Ranking;

public class RankingTest {

	Jugador p1;
	Jugador p2;
	Jugador p3;
	@Before
	public void setUp() throws Exception {
		p1 = new Jugador("AA");
		p2 = new Jugador("BB");
		p3 = new Jugador("CC");
	}

	@After
	public void tearDown() throws Exception {
		Ranking.getRanking().reset();
	}

	@Test
	public void testAnadirLista() {
		Ranking.getRanking().anadirLista(p1);
		assertEquals(Ranking.getRanking().longLista(), 1);
	}

	@Test
	public void testEstaEnRanking() {
		Ranking.getRanking().anadirLista(p1);
		assertTrue(Ranking.getRanking().estaEnRanking("AA"));
		assertFalse(Ranking.getRanking().estaEnRanking("BB"));
	}

	@Test
	public void testObtJugador() {
		Ranking.getRanking().anadirLista(p2);
		assertEquals(Ranking.getRanking().obtJugador("BB"), p2);
	}

	@Test
	public void testConseguirJugadorPos() {
		Ranking.getRanking().anadirLista(p1);
		assertEquals(Ranking.getRanking().conseguirJugadorPos(0), p1);
	}

}
