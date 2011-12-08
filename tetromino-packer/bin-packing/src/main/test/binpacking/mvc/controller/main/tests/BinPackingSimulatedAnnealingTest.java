package binpacking.mvc.controller.main.tests;


import org.junit.Before;

import binpacking.mvc.controller.main.BinPackingSimulatedAnnealing;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingSimulatedAnnealingTest extends BinPackingTestCases{

    /**
     *
     */
    @Before
	public void beforeTest(){
		s = new BinPackingSimulatedAnnealing();
	}
	
	
}
