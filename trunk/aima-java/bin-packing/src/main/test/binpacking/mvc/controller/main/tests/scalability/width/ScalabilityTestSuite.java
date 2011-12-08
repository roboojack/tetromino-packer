package binpacking.mvc.controller.main.tests.scalability.width;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * @author Robert Jackson
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
		{
			BinPackingAStarTest.class,
			BinPackingDFSTest.class,
			BinPackingGreedyBestFirstSearchTest.class,
			BinPackingHillClimbingTest.class,
			BinPackingSimulatedAnnealingTest.class
		})
public class ScalabilityTestSuite {
}
