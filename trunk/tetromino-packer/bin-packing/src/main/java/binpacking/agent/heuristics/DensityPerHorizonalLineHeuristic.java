package binpacking.agent.heuristics;

import aima.core.search.framework.HeuristicFunction;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * Estimates the distance to goal by the number of pieces left to place
 * 
 * @author Robert Jackson
 */
public class DensityPerHorizonalLineHeuristic implements HeuristicFunction {

    /**
     *
     * @param state
     * @return
     */
    @Override
	public double h(Object state) {
		TetrisBoard board = (TetrisBoard) state;
		return
			(StartStateProperties.numIs +	
			StartStateProperties.numJs +	
			StartStateProperties.numLs +	
			StartStateProperties.numOs +	
			StartStateProperties.numSs +	
			StartStateProperties.numTs +	
			StartStateProperties.numZs)	
			-
			(board.numIsOnBoard +
			board.numJsOnBoard +
			board.numLsOnBoard +
			board.numOsOnBoard +
			board.numSsOnBoard +
			board.numTsOnBoard +
			board.numZsOnBoard)
			;
	}
}