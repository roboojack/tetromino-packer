package binpacking.mvc.controller.main.tests.scalability;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingGreedyBestFirstSearch;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingGreedyBestFirstSearchTest extends BinPackingScalabilityTestCases{


    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingGreedyBestFirstSearch();
    	System.gc();
	}
	
	
}
