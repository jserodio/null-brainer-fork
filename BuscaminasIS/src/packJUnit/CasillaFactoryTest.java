package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.Casilla;
import packCodigo.CasillaFactory;

public class CasillaFactoryTest {

	private Casilla cV;
	private Casilla cM;
	private Casilla cN;
	private CasillaFactory f;
	
	@Before
	public void setUp() throws Exception {
		cV = f.getMiFactoria().generarCasilla("Vacia");
		cM = f.getMiFactoria().generarCasilla("Mina");
		cN = f.getMiFactoria().generarCasilla("Numero");
	}

	@After
	public void tearDown() throws Exception {
		cV = null;
		cM = null;
		cN = null;
	}

	@Test
	public void testGenerarCasilla() {
		assertNotNull(cV);
		assertNotNull(cM);
		assertNotNull(cN);
	}

}
