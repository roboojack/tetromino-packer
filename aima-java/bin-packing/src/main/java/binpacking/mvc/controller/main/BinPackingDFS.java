package binpacking.mvc.controller.main;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.uninformed.DepthFirstSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingDFS extends BinPackingSearchImp {

	

    /**
     *
     * @throws Exception
     */
    @Override
	public void doSearch() throws Exception {
		DebugOutput.infoPanelOut("\nDFS -->");

			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);
			Search search = new DepthFirstSearch(new GraphSearch());
			agent= new SearchAgent(problem, search);
				
 
	}


}