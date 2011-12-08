package binpacking.mvc.controller.main;

import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthLimitedSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingRecursiveDLS extends BinPackingSearchImp {


    /**
     *
     */
    @Override
	public void doSearch() {
		DebugOutput.infoPanelOut("\nRecursive DLS -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);

			Search search = new DepthLimitedSearch(8);
			agent = new SearchAgent(problem, search);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



}