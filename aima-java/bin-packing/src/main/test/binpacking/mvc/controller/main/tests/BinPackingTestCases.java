package binpacking.mvc.controller.main.tests;


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
public class BinPackingTestCases {

	private static final long MAX_TIME_FOR_SEARCH_ms = 10000;
        /**
         *
         */
        public static BinPackingSearch s;


        
        
	
	/**
	 * run a test with:
	 * 6 O's
	 * 2 L's
	 * 2 I's
	 * 
	 * this is possible by example:
	 * 
	 * OOOO
	 * OOOO
	 * OOOO
	 * OOOO
	 * OOOO
	 * OOOO
	 * LLLL
	 * LLLL 
	 * IIII 
	 * IIII 
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test1() throws Exception {
		StartStateProperties.numIs=2;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=2;
		StartStateProperties.numOs=6;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;

		
		s.doSearch();
		System.out.println(BinPackingSearchImp.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}

	/**
	 * run a test with:
	 * 12 O's
	 * 4 L's
	 * 4 I's
	 * 
	 * on
	 * 
	 * this is possible by example:
	 * 
	 * OOOOOOOO
	 * OOOOOOOO
	 * OOOOOOOO
	 * OOOOOOOO
	 * OOOOOOOO
	 * OOOOOOOO
	 * LLLLLLLL
	 * LLLLLLLL
	 * IIIIIIII 
	 * IIIIIIII
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms*2)
	public void test1x2() throws Exception {
		StartStateProperties.numIs=4;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=4;
		StartStateProperties.numOs=12;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		StartStateProperties.BOARD_WIDTH =8;
		StartStateProperties.BOARD_HEIGHT=10;

		
		s.doSearch();
		System.out.println(BinPackingSearchImp.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 20);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}

	/**
	 * this is a transpose of test1 with: 
	 *  height=4
	 *  width =10

	 * this is possible by example:
	 * 
	 * IILLOOOOOO
	 * IILLOOOOOO
	 * IILLOOOOOO
	 * IILLOOOOOO
	 * 
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test2() throws Exception {
		StartStateProperties.numIs=2;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=2;
		StartStateProperties.numOs=6;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;

		StartStateProperties.BOARD_WIDTH =10;
		StartStateProperties.BOARD_HEIGHT=4;

		
		s.doSearch();
		System.out.println(BinPackingDFS.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}
	
	/**
	 * run a test with:
	 * 4 I's
	 * width =4
	 * height=4
	 * 
	 * this is possible by example:
	 * 
	 * IIII
	 * IIII 
	 * IIII 
	 * IIII 
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test3() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=4;

		
		StartStateProperties.numIs=4;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		System.out.println(BinPackingDFS.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 4);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}

	
	
	/**
	 * run a test with:
	 * 1 I's
	 * width =5
	 * height=10
	 * 
	 * minimum must be 0
	 * 
	 * this is possible by example:
	 * 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * IIIIX 
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test4() throws Exception {
		StartStateProperties.BOARD_WIDTH =5;
		StartStateProperties.BOARD_HEIGHT=10;

		
		StartStateProperties.numIs=1;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		System.out.println(BinPackingDFS.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 1);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}

	/**
	 * run a test with:
	 * 1 I's
	 * width =5
	 * height=10
	 * 
	 * minimum must be 0
	 * 
	 * this is possible by example:
	 * 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * XXXXX 
	 * IIIIX 
	 * @throws Exception 
	 *  
	 */
	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test5() throws Exception {
		StartStateProperties.BOARD_WIDTH =5;
		StartStateProperties.BOARD_HEIGHT=10;

		
		StartStateProperties.numIs=12;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		System.out.println(BinPackingDFS.getAgent().getActions());
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 12);
		assertFalse(BinPackingDFS.getAgent().getActions().size() == 100);
	}

	/**
	 * run a test with:
	 * 10 I's
	 * width =5
	 * height=10
	 * 
	 * minimum must be 0
	 * 
	 * this is possible by example:
	 * 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * IIII 
	 * @throws Exception 
	 *  
	 */

	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test10Is() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=10;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
	}

	/**
	 * run a test with:
	 * 1 I's
	 * width =10
	 * height=4
	 * 
	 *
	 * 
	 * 
	 * this is possible by example:
	 * 
	 * XXXXXXXXXX
	 * XXXXXXXXXX
	 * XXXXXXXXXX
	 * IIIIXXXXXX
	 * @throws Exception 
	 *  
	 */

	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test1IOn10By4() throws Exception {
		StartStateProperties.BOARD_WIDTH =10;
		StartStateProperties.BOARD_HEIGHT=4;
		
		StartStateProperties.numIs=1;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(
				"BinPackingDFS.agent.getActions().size() ==" + BinPackingDFS.getAgent().getActions().size(), 
				BinPackingDFS.getAgent().getActions().size() == 1
		);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test10Js() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=10;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		BinPackingDFS s = new BinPackingDFS();
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test10Ls() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=10;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test10Os() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=10;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 10);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test8Ss() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=8;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 8);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test9Ts() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=9;
		StartStateProperties.numZs=0;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 9);
	}

        /**
         *
         * @throws Exception
         */
        @Test(timeout=MAX_TIME_FOR_SEARCH_ms)
	public void test8Zs() throws Exception {
		StartStateProperties.BOARD_WIDTH =4;
		StartStateProperties.BOARD_HEIGHT=10;
		
		StartStateProperties.numIs=0;
		StartStateProperties.numJs=0;
		StartStateProperties.numLs=0;
		StartStateProperties.numOs=0;
		StartStateProperties.numSs=0;
		StartStateProperties.numTs=0;
		StartStateProperties.numZs=8;
		
		s.doSearch();
		assertTrue(BinPackingDFS.getAgent().getActions().size() == 8);
	}

    	/**
    	 * 
    	 * 
    	 * @throws Exception
    	 */
    	@Test(timeout=MAX_TIME_FOR_SEARCH_ms)
    	public void test1O4Z4S2I_5By10() throws Exception {
    		StartStateProperties.BOARD_WIDTH =5;
    		StartStateProperties.BOARD_HEIGHT=10;
    		
    		StartStateProperties.numIs=2;
    		StartStateProperties.numJs=0;
    		StartStateProperties.numLs=0;
    		StartStateProperties.numOs=1;
    		StartStateProperties.numSs=4;
    		StartStateProperties.numTs=0;
    		StartStateProperties.numZs=4;
    		
    		s.doSearch();
    		assertTrue(BinPackingDFS.getAgent().getActions().size() == 11);
    	}


	
	
	
	
	
	
}
