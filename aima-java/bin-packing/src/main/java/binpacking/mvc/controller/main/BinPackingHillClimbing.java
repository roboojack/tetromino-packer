package binpacking.mvc.controller.main;

import aima.core.search.framework.Problem;
import aima.core.search.framework.SearchAgent;
import aima.core.search.local.HillClimbingSearch;
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

public class BinPackingHillClimbing extends BinPackingSearchImp {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		if(StartStateProperties.boardArea >= StartStateProperties.neededArea){
			BinPackingHillClimbing search = new BinPackingHillClimbing();
			search.run();
		}
		else
			DebugOutput.infoPanelOut("Sorry there is no solution... \n" +
					"needed area=" + StartStateProperties.neededArea + "\n"	+				
					"board area =" + StartStateProperties.boardArea 
					);
	}
	

	
    /**
     *
     */
    @Override
	public void doSearch() {
		DebugOutput.infoPanelOut("\nHill Climbing  -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);

			// UNCOMMENT TO SELECT DESIRED HEURISITC
//			HillClimbingSearch search = new HillClimbingSearch(
//					new MaxHeightHeuristic());
			HillClimbingSearch search = new HillClimbingSearch(
//					new NumberOfPiecesLeftHeuristic()					
					new NumberOfHolesHeuristic()
					);

			agent = new SearchAgent(problem, search);

			DebugOutput.infoPanelOut("Search Outcome=" + search.getOutcome());
			DebugOutput.infoPanelOut("Final State=\n" + search.getLastSearchState());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



}