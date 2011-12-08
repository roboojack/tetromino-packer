package binpacking.mvc.controller.main.tests.scalability.width;


import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import binpacking.mvc.controller.main.BinPackingDFS;
import binpacking.mvc.controller.main.BinPackingSearch;
import binpacking.mvc.controller.main.BinPackingSimulatedAnnealing;
import binpacking.mvc.model.StartStateProperties;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingSimulatedAnnealingTest {//extends BinPackingScalabilityTestCases{

    /**
     *
     */
//    @Before
//	public void beforeTest(){
//    	System.gc();
//		s = new BinPackingSimulatedAnnealing();
//    	System.gc();
//	}

	private static final long MAX_TIME_FOR_SEARCH_ms = 30000;
	private static final int TEST_ITERATIONS = 5;
	/**
	 * the search algorithm that will be tested, note this is a supertype
	 */
	public static BinPackingSearch s;
//	int TEST_BIN_WIDTH = 400;
//	int TEST_BIN_HEIGHT = 2;



//	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
//	public void testWidth4() throws Exception {
//		setStartState(600, (int) Math.pow(2, 1), 4, 4, 4, 4, 4, 4, 4);
//		doSearchMultipleTimes();
//		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
//	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth4() throws Exception {
		setStartState((int) Math.pow(2, 2), 300,  4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth8() throws Exception {
		setStartState((int) Math.pow(2, 3),150, 4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth16() throws Exception {
		setStartState( (int) Math.pow(2, 4),75, 4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth32() throws Exception {
		setStartState( (int) Math.pow(2, 5),38, 4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth64() throws Exception {
		setStartState((int) Math.pow(2, 6),19,  4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth128() throws Exception {
		setStartState((int) Math.pow(2, 7),10,  4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth256() throws Exception {
		setStartState((int) Math.pow(2, 8),5,  4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void testWidth512() throws Exception {
		setStartState((int) Math.pow(2, 9), 3,  4, 4, 4, 4, 4, 4, 4);
		s = new BinPackingSimulatedAnnealing();
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

//	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
//	public void testWidth1024() throws Exception {
//		setStartState(2, (int) Math.pow(2, 10), 4, 4, 4, 4, 4, 4, 4);
//		doSearchMultipleTimes();
//		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
//	}
	
	
	
	// @Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	// public void test252Pieces() throws Exception {
	// setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 36, 36, 36, 36, 36, 36,
	// 36);
	// s.doSearch();
	// assertTrue(BinPackingDFS.getAgent().getActions().size() == 252);
	// }

	// @Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	// public void test312Pieces() throws Exception {
	// setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 44, 44, 44, 44, 44, 44,
	// 44);
	// s.doSearch();
	// assertTrue(BinPackingDFS.getAgent().getActions().size() == 252);
	// }


	private void doSearchMultipleTimes() throws Exception {
		for (int i = 0; i < TEST_ITERATIONS; i++) {
			s.doSearch();
			
		}
		s = null;
	}

	
	private void setStartState(int width, int height, int o, int z, int j,
			int t, int l, int s, int i) {
		System.out.println(width + ", " + height);
		StartStateProperties.BOARD_WIDTH = (short) width;
		StartStateProperties.BOARD_HEIGHT = (short) height;

		StartStateProperties.numOs = (short) o;
		StartStateProperties.numZs = (short) z;
		StartStateProperties.numJs = (short) j;
		StartStateProperties.numTs = (short) t;
		StartStateProperties.numLs = (short) l;
		StartStateProperties.numSs = (short) s;
		StartStateProperties.numIs = (short) i;

	}


	
}
