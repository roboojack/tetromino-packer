package binpacking.mvc.controller.main;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.GreedyBestFirstSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.agent.heuristics.NumberOfHolesHeuristic;
import binpacking.agent.heuristics.NumberOfHolesStepCostFunction;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingGreedyBestFirstSearch extends BinPackingSearchImp {




    /**
     *
     */
    @Override
	public void doSearch() {
		DebugOutput.infoPanelOut("\nGreedy Best First Search -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest(),
					new NumberOfHolesStepCostFunction()
					);
			Search search = new GreedyBestFirstSearch(
					new GraphSearch(),
					new NumberOfHolesHeuristic()
//					new NumberOfPiecesLeftHeuristic()					
			);
			agent = new SearchAgent(problem, search);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}