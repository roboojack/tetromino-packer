package binpacking.mvc.controller.main.tests.scalability;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingBFS;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingBFSTest extends BinPackingScalabilityTestCases{


    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingBFS();
    	System.gc();
	}
	
	
}
