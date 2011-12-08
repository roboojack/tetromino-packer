package binpacking.mvc.controller.main.tests;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingBFS;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingBFSTest extends BinPackingTestCases{


    /**
     *
     */
    @Before
	public void beforeTest(){
		s = new BinPackingBFS();
	}
	
	
}
