package binpacking.mvc.controller.main;

import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.Scheduler;
import aima.core.search.local.SimulatedAnnealingSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.agent.heuristics.NumberOfHolesHeuristic;
import binpacking.agent.heuristics.NumberOfPiecesLeftHeuristic;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingSimulatedAnnealing extends BinPackingSearchImp {

//	public static void main(String[] args) {
//		if(StartStateProperties.boardArea >= StartStateProperties.neededArea)
//			newBinPackingDemo();
//		else
//			DebugOutput.infoPanelOut("Sorry there is no solution... \n" +
//					"needed area=" + StartStateProperties.neededArea + "\n"	+				
//					"board area =" + StartStateProperties.boardArea 
//					);
//	}
	
    /**
     *
     */
    @Override
	public void doSearch() {
		DebugOutput.infoPanelOut("\nSimulated Annealing  -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);

			// UNCOMMENT TO SELECT DESIRED HEURISITC

			double lambda = 0.45;
			int limit=1 * StartStateProperties.getNeededArea() / StartStateProperties.getBoardHeight() * StartStateProperties.getBoardWidth();
			System.out.println("StartStateProperties.neededArea=" + StartStateProperties.neededArea);
			System.out.println("StartStateProperties.boardArea=" + StartStateProperties.boardArea);
			System.out.println(limit + "\n" + lambda);
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
//					new NumberOfHolesHeuristic(),
					new NumberOfPiecesLeftHeuristic(),
					new Scheduler(
							200, 
							lambda, 
							limit
					)
			);

			agent = new SearchAgent(problem, search);
//
			DebugOutput.infoPanelOut("Final State=\n" + search.getLastSearchState());
			DebugOutput.infoPanelOut("Search Outcome=" + search.getOutcome());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}