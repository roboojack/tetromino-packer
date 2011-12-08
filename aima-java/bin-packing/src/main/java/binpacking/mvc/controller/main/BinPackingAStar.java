package binpacking.mvc.controller.main;

import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.informed.AStarSearch;
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

public class BinPackingAStar extends BinPackingSearchImp {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		if(StartStateProperties.boardArea >= StartStateProperties.neededArea){
			BinPackingAStar prog = new BinPackingAStar();
			prog.doSearch();
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
		DebugOutput.infoPanelOut("\nA* -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest(),
					new NumberOfHolesStepCostFunction() // <---- STEP COST FUCTION
					);
			Search search = new AStarSearch(
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