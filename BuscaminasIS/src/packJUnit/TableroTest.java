package packJUnit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packCodigo.CasillaMina;
import packCodigo.CasillaNumero;
import packCodigo.CasillaVacia;
import packCodigo.Tablero;


public class TableroTest {

	int n1;
  	int c;
	int f;
/*	int n2;
	int n3;

	ArrayList<String> lM;
	ArrayList<String> lCVacias;
	Stack<String> cPV;
	ArrayList<String> lCVisitadas;
	Casilla[][] matriz;*/
	Tablero t;
	
	@Before
	public void setUp() throws Exception {
		n1=1;
		c=10;
		f=7;
/*		n2=2;
		n3=3;*/
		t=new Tablero(n1, f, c);
	}

	@After
	public void tearDown() throws Exception {
		t=new Tablero(n1, f, c);
	}

	/*
	@Test
	public void testGenerarMatriz() {
	
		for (int i=0; i<7; i++){
			for (int j=0;j<10;j++){
				if(t.buscarCasilla(i, j) instanceof CasillaVacia){
					CasillaVacia cv = (CasillaVacia) t.buscarCasilla(i, j);
					assertNotNull(cv.obtenerCoordenadas());
				}else
					if(t.buscarCasilla(i, j) instanceof CasillaMina){
						CasillaMina cM = (CasillaMina) t.buscarCasilla(i, j);
						assertNotNull(cM.obtenerCoordenadas());
					}
					else
						if(t.buscarCasilla(i, j) instanceof CasillaNumero){
							CasillaNumero cn = (CasillaNumero) t.buscarCasilla(i, j);
							assertNotNull(cn.obtenerCoordenadas());
						}
			}
		}
	}
	 
	@Test
	
	public void testRandInt() {
		int num=Tablero.randInt(10);
		assertTrue(num>0);
		assertTrue(num<10);
	}
	
	@Test
	public void testEstaVisitada() {
		assertTrue(t.getCasillasVisitadas().size()==0);
		assertTrue(t.estaVisitada("2,1")==false);
		t.anadirVisitadas("2,1");
		assertTrue(t.getCasillasVisitadas().size()==1);
		assertTrue(t.estaVisitada("2,1")==true);
	}
	
	@Test
	public void testAnadirVisitadas() {
		assertTrue(t.getCasillasVisitadas().size()==0);
		t.anadirVisitadas("1,2");
		assertTrue(t.getCasillasVisitadas().size()==1);
	}
	
	@Test
	public void testAnadirPorVisitar() {
		assertTrue(t.getCasillasPorVisitar().size()==0);
		t.anadirPorVisitar("0,0");
		assertTrue(t.getCasillasPorVisitar().size()==1);
	}
	
	@Test
	public void testAnadirVacia() {
		assertTrue(t.getCasillasVacias().size()==0);
		t.anadirVacia("1,1");
		assertTrue(t.getCasillasVacias().size()==1);
	}
	
	@Test
	public void testCogeryEliminarPorVisitar() {
		t.anadirPorVisitar("0,0");
		assertTrue(t.getCasillasPorVisitar().size()==1);
		t.cogeryEliminarPorVisitar();
		assertTrue(t.getCasillasPorVisitar().size()==0);
	}
	*/
}
