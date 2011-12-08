package binpacking.mvc.controller.main.tests.scalability;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import binpacking.mvc.controller.main.BinPackingDFS;
import binpacking.mvc.controller.main.BinPackingSearch;
import binpacking.mvc.controller.main.BinPackingSearchImp;
import binpacking.mvc.model.StartStateProperties;

/**
 * 
 * @author Robert Jackson
 */
public class BinPackingScalabilityTestCases {

	private static final long MAX_TIME_FOR_SEARCH_ms = 100000;
	private static final int TEST_ITERATIONS = 5;
	/**
	 * the search algorithm that will be tested, note this is a supertype
	 */
	public static BinPackingSearch s;
	int TEST_BIN_WIDTH = 25;
	int TEST_BIN_HEIGHT = 50;

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126Pieces() throws Exception {
		setStartState(TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		doSearchMultipleTimes();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

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
	}

	private void setStartState(int width, int height, int o, int z, int j,
			int t, int l, int s, int i) {
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
