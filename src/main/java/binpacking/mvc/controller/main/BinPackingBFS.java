package binpacking.mvc.controller.main;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingBFS extends BinPackingSearchImp {



	
    /**
     *
     */
    @Override
	public void doSearch() {
		try {
			DebugOutput.infoPanelOut("\nBFS -->");
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);
			Search search = new BreadthFirstSearch(new TreeSearch());
			agent = new SearchAgent(problem, search);
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}

}