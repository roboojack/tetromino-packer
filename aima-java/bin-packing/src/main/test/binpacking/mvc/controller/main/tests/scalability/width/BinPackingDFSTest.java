package binpacking.mvc.controller.main.tests.scalability.width;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingDFS;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingDFSTest extends BinPackingScalabilityTestCases{


    /**
     * 
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingDFS();
    	System.gc();
	}	
}
