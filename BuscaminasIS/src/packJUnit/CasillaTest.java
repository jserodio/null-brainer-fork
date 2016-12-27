package packJUnit;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.Casilla;
import packCodigo.CasillaFactory;
import packCodigo.CasillaVacia;
import packCodigo.Tablero;

public class CasillaTest {

	private Casilla cV;
	private Casilla cM;
	private Casilla cN;
	private CasillaFactory f;
	
	@Before
	public void setUp() throws Exception {
		cV = f.getMiFactoria().generarCasilla("Vacia");
		cM = f.getMiFactoria().generarCasilla("Mina");
		cN = f.getMiFactoria().generarCasilla("Numero");
		cV.inicializar("0,1");
		cM.inicializar("0,0");
		cN.inicializar("1,1");
	}

	@After
	public void tearDown() throws Exception {
		cV = null;
		cM = null;
		cN = null;
	}

	@Test
	public void testCasilla() {
		assertNotNull(cV);
		assertNotNull(cM);
		assertNotNull(cN);
	}

	@Test
	public void testObtenerCoordenadas() {
		assertTrue(cV.obtenerCoordenadas()=="0,1");
		assertTrue(cM.obtenerCoordenadas()=="0,0");
		assertTrue(cN.obtenerCoordenadas()=="1,1");
	}

	@Test
	public void testCambioBandera() {
		cV.cambioBandera();
		assertTrue(cV.tieneBandera());
		cV.cambioBandera();
		assertFalse(cV.tieneBandera());
		cV.descubrir();
		assertFalse(cV.tieneBandera());
		cM.cambioBandera();
		assertTrue(cM.tieneBandera());
		cM.cambioBandera();
		assertFalse(cM.tieneBandera());
		cM.descubrir();
		assertFalse(cM.tieneBandera());
		cN.cambioBandera();
		assertTrue(cN.tieneBandera());
		cN.cambioBandera();
		assertFalse(cN.tieneBandera());
		cN.descubrir();
		assertFalse(cN.tieneBandera());
		
	}

	@Test
	/*
	public void testEstaDesvelada() {
		Tablero t = new Tablero(1,7,10);
		t.generarMatriz();
		CasillaVacia c = new CasillaVacia();
		int i = 0;
		int j = 0;
		boolean enc = false;
		while (i<7&& enc==false) {
			while (j<10&& enc==false){
				if (t.buscarCasilla(i, j) instanceof CasillaVacia){
					c = (CasillaVacia)t.buscarCasilla(i, j);
					System.out.println(c.obtenerCoordenadas());
					enc =  true;
				}
				j++;
			}
			i++;
		}
		//c.obtenerVecinos();
		ArrayList<String> vecinos = c.devolverVecinos();
		i = 0;
		c.descubrir();
		while (i<vecinos.size()){
			Casilla c1;
			String[] co = separarCoordenadas(vecinos.get(i));
			c1 = t.buscarCasilla(separarCoordenadasFil(co), separarCoordenadasCol(co));
			assertTrue(c1.estaDesvelada());
		}
		
	}
	*/
	/****************************************************
	 * Coge las coordenadas de la casilla y lo separa	*
	 * metiendolo en un array de Strings -> String[]	*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(" ")	*
	 ****************************************************/
	private String[] separarCoordenadas(String pCasilla){
		return pCasilla.split(",");
	}
	
	/************************************************
	 * coge la coordenada de la col y lo pasa a int	*
	 * @param pCasilla								*
	 * @return Integer.parseInt(pCasilla[1])		*
	 ************************************************/
	private int separarCoordenadasCol(String[] pCasilla){
		return Integer.parseInt(pCasilla[1]);
	}
	
	/****************************************************
	 * coge la coordenada de la fila y lo pasa a int 	*
	 * @param pCasilla									*
	 * @return Integer.parseInt(pCasilla[0])			*
	 ****************************************************/
	private int separarCoordenadasFil(String[] pCasilla){
		return Integer.parseInt(pCasilla[0]);
	}
	
	/****************************************************
	 * separa un string									*
	 * @param pCasilla									*
	 * @return pCasilla.obtenerCoordenadas().split(" ")	*
	 ****************************************************/
	private String[] separarCoordenadasString(String pCoord){
		return pCoord.split(",");
	}
	
}