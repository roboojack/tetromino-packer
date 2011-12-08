package binpacking.mvc.controller.main.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import binpacking.mvc.controller.BinPackingViewController;
import binpacking.mvc.controller.main.BinPackingApp;
import binpacking.mvc.controller.main.BinPackingDFS;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.view.BinPackingView;

/**
 * 
 * @author Robert Jackson
 */
public class BinPackingAppTest {

	private static final long MAX_TIME_FOR_SEARCH_ms = 5* 60000;
	static BinPackingApp app;
	static Thread t;
	int TEST_BIN_WIDTH = 25;
	int TEST_BIN_HEIGHT = 50;

	/**
	 * 
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		t = new Thread(new Runnable() {

			@Override
			public void run() {
				app = new BinPackingApp();
				BinPackingApp.main(null);
			}
		});
		t.start();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void setStartState(int algorithm, int width, int height, int o, int z, int j,
			int t, int l, int s, int i) {

		
		BinPackingView.searchAlgorithmCombo.setSelectedIndex(algorithm);
		
		BinPackingView.boardHeightTextField.setText(String.valueOf(height));
		BinPackingView.boardWidthTextField.setText(String.valueOf(width));
		BinPackingView.numIsTextField.setText(String.valueOf(i));
		BinPackingView.numJsTextField.setText(String.valueOf(j));
		BinPackingView.numLsTextField.setText(String.valueOf(l));
		BinPackingView.numOsTextField.setText(String.valueOf(o));
		BinPackingView.numSsTextField.setText(String.valueOf(s));
		BinPackingView.numTsTextField.setText(String.valueOf(t));
		BinPackingView.numZsTextField.setText(String.valueOf(z));
		

	}
	
	
	
	private void performSearch() throws Exception {
		BinPackingViewController.stopSearchButtonActionPerformed(null);
		BinPackingViewController.startSearchButtonActionPerformed(null);
		while (BinPackingViewController.t.isAlive()){
			Thread.sleep(1000);
		}
	}
	

//	/**
//         *
//         */
//	@Test
//	public void test1() {
//		BinPackingViewController.startSearchButtonActionPerformed(null);
//		while (BinPackingViewController.t.isAlive());
//
//	}
//
//	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
//	public void test2() throws Exception {
//
//		BinPackingView.boardHeightTextField.setText("100");
//		BinPackingView.boardWidthTextField.setText("100");
//		BinPackingView.numIsTextField.setText("200");
//		BinPackingView.numJsTextField.setText("0");
//		BinPackingView.numLsTextField.setText("0");
//		BinPackingView.numOsTextField.setText("0");
//		BinPackingView.numSsTextField.setText("0");
//		BinPackingView.numTsTextField.setText("0");
//		BinPackingView.numZsTextField.setText("0");
//		
//		BinPackingView.searchAlgorithmCombo.setSelectedIndex(0);
//
//
//		BinPackingViewController.startSearchButtonActionPerformed(null);
//		while (BinPackingViewController.t.isAlive()){
//			Thread.sleep(1000);
//		}
//
//		assertTrue(BinPackingDFS.getAgent().getActions().size() == 200);
//
//	}
	
	
	
	
	
	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126PiecesDFS() throws Exception {
		setStartState(0, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

	/*
	 * Simulated Annealing Start
	 */
	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126PiecesSimAnnealing() throws Exception {
		setStartState(4, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

	/*
	 * Simulated Annealing End
	 */
	
	/*
	 * HC Start
	 */
	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126PiecesHC() throws Exception {
		setStartState(5, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

	/*
	 * HC End
	 */
	
	
	
	/*
	 * A* Start
	 */
	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126PiecesAStar() throws Exception {
		setStartState(6, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

	/*
	 * A* End
	 */
	/*
	 * GBFS Start
	 */
	
	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test2PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 0, 0, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 2);
	}


	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test4PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 0, 0, 0);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test7PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 1, 1, 1, 1, 1, 1, 1);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 7);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test14PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 2, 2, 2, 2, 2, 2, 2);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 14);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test28PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 4, 4, 4, 4, 4, 4, 4);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 28);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test63PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 9, 9, 9, 9, 9, 9, 9);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 63);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test126PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 18, 18, 18, 18, 18, 18,
				18);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 126);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test252PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 36, 36, 36, 36, 36, 36,
				36);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 252);
	}

	@Test(timeout = MAX_TIME_FOR_SEARCH_ms)
	public void test308PiecesGBFS() throws Exception {
		setStartState(7, TEST_BIN_WIDTH, TEST_BIN_HEIGHT, 44, 44, 44, 44, 44, 44,
				44);
		performSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 308);
	}
	
	/*
	 * GBFS End
	 */

	
	
	
	
	
	
	
	
	
	
	

}
