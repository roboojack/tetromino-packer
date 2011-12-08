package binpacking.mvc.controller.main.tests.scalability.width;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingAStar;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingAStarTest extends BinPackingScalabilityTestCases{


    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingAStar();
    	System.gc();
	}
	
	
}
