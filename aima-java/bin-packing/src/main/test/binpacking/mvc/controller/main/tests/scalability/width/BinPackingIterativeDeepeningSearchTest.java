package binpacking.mvc.controller.main.tests.scalability.width;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingIterativeDeepeningSearch;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingIterativeDeepeningSearchTest extends BinPackingScalabilityTestCases{

    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingIterativeDeepeningSearch();
    	System.gc();
	}
	
	
}
