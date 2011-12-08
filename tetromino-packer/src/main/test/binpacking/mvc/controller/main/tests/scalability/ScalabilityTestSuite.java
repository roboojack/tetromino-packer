package binpacking.mvc.controller.main.tests.scalability;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author Robert Jackson
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			BinPackingDFSTest.class,
			BinPackingGreedyBestFirstSearchTest.class,
			BinPackingHillClimbingTest.class,
			BinPackingSimulatedAnnealingTest.class,
			BinPackingAStarTest.class
		})
public class ScalabilityTestSuite {
}
