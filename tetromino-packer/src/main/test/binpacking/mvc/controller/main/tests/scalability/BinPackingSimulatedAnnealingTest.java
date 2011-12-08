package binpacking.mvc.controller.main.tests.scalability;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingSimulatedAnnealing;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingSimulatedAnnealingTest extends BinPackingScalabilityTestCases{

    /**
     *
     */
    @Before
	public void beforeTest(){
    	System.gc();
		s = new BinPackingSimulatedAnnealing();
    	System.gc();
	}
	
	
}
