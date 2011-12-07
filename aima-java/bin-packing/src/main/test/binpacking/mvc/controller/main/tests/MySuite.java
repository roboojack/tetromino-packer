package binpacking.mvc.controller.main.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author Robert Jackson
 */
@RunWith(ConcurrentSuite.class)
@Suite.SuiteClasses(
		{
			BinPackingAStarTest.class,
			BinPackingDFSTest.class,
			BinPackingGreedyBestFirstSearchTest.class,
			BinPackingHillClimbingTest.class,
			BinPackingSimulatedAnnealingTest.class
		})

@Concurrent
public class MySuite {
}
