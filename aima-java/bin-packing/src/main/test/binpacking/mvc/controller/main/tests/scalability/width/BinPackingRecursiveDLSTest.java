package binpacking.mvc.controller.main.tests.scalability.width;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingRecursiveDLS;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingRecursiveDLSTest extends BinPackingScalabilityTestCases{

    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingRecursiveDLS();
    	System.gc();
	}
	
	
}
