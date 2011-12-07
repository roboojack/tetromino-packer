package binpacking.mvc.controller.main.tests;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.GraphSearch;
import aima.core.search.framework.Problem;
import aima.core.search.framework.Search;
import aima.core.search.framework.SearchAgent;
import aima.core.search.framework.TreeSearch;
import aima.core.search.local.SimulatedAnnealingSearch;
import aima.core.search.uninformed.BreadthFirstSearch;
import aima.core.search.uninformed.DepthFirstSearch;
import aima.core.search.uninformed.DepthLimitedSearch;
import aima.core.search.uninformed.IterativeDeepeningSearch;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.agent.BinPackingGoalTest;
import binpacking.agent.heuristics.MaxHeightHeuristic;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author Ravi Mohan, edited by Robert Jackson
 * 
 */

public class BinPackingDemo implements Runnable {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
		if(StartStateProperties.boardArea >= StartStateProperties.neededArea)
			newBinPackingDemo();
		else
			DebugOutput.infoPanelOut("Sorry there is no solution... \n" +
					"needed area=" + StartStateProperties.neededArea + "\n"	+				
					"board area =" + StartStateProperties.boardArea 
					);
	}
	
        /**
         *
         */
        public BinPackingDemo(){
		
	}

        /**
         *
         */
        public static void newBinPackingDemo() {
		binPackingSimulatedAnnealingSearch();
		//binPackingWithDepthFirstSearch();
		//binPackingWithBreadthFirstSearch();
		//binPackingWithRecursiveDLS();
		//binPackingWithIterativeDeepeningSearch();

	}

	
        /**
         *
         */
        public static void binPackingSimulatedAnnealingSearch() {
		DebugOutput.infoPanelOut("\nBinPackingDemo Simulated Annealing  -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);
			
			SimulatedAnnealingSearch search = new SimulatedAnnealingSearch(
					new MaxHeightHeuristic());

			SearchAgent agent = new SearchAgent(problem, search);

			DebugOutput.infoPanelOut();
			printActions(agent.getActions());
			DebugOutput.infoPanelOut("Search Outcome=" + search.getOutcome());
			DebugOutput.infoPanelOut("Final State=\n" + search.getLastSearchState());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
        /**
         *
         */
        public static void binPackingWithRecursiveDLS() {
		DebugOutput.infoPanelOut("\nBinPackingDemo recursive DLS -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);

			Search search = new DepthLimitedSearch(8);
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

        /**
         *
         */
        public static void binPackingWithIterativeDeepeningSearch() {
		DebugOutput.infoPanelOut("\nBinPackingDemo Iterative DS  -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);

			Search search = new IterativeDeepeningSearch();
			SearchAgent agent = new SearchAgent(problem, search);

			DebugOutput.infoPanelOut();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
        /**
         *
         */
        public static void binPackingWithBreadthFirstSearch() {
		try {
			DebugOutput.infoPanelOut("\nbinPackingDemo BFS -->");
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);
			Search search = new BreadthFirstSearch(new TreeSearch());
			SearchAgent agent2 = new SearchAgent(problem, search);
			printActions(agent2.getActions());
			printInstrumentation(agent2.getInstrumentation());
		} catch (Exception e1) {

			e1.printStackTrace();
		}
	}


        /**
         *
         */
        public static void binPackingWithDepthFirstSearch() {
		DebugOutput.infoPanelOut("\nBinPackingDemo DFS -->");
		try {
			Problem problem = new Problem(
					new TetrisBoard(StartStateProperties.getBoardWidth(), StartStateProperties.getBoardHeight()),
					BinPackingFunctionFactory.getActionsFunction(),
					BinPackingFunctionFactory.getResultFunction(),
					new BinPackingGoalTest()
					);
			Search search = new DepthFirstSearch(new GraphSearch());
			SearchAgent agent = new SearchAgent(problem, search);
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
			if(agent.getActions().isEmpty())
				DebugOutput.infoPanelOut("FAILURE: DON'T SEARCHING AND NO SOLUTION FOUND...");
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




        /**
         *
         * @param properties
         */
        public static void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			DebugOutput.infoPanelOut(key + " : " + property);
		}

	}

        /**
         *
         * @param actions
         */
        public static void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			DebugOutput.infoPanelOut(action);
		}
	}

	@Override
	public void run() {
		binPackingWithDepthFirstSearch();
		
	}

}