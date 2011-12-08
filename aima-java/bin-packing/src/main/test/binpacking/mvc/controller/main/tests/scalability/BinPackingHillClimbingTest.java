package binpacking.mvc.controller.main.tests.scalability;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingHillClimbing;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingHillClimbingTest extends BinPackingScalabilityTestCases{

    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingHillClimbing();
    	System.gc();
	}
	
	
}
