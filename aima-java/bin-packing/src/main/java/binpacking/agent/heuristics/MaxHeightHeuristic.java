package binpacking.agent.heuristics;

import aima.core.search.framework.HeuristicFunction;
import binpacking.mvc.model.TetrisBoard;

/**
 * Estimates the distance to goal by the number of attacking pairs of queens on
 * the board.
 * 
 * @author R. Lunde
 */
public class MaxHeightHeuristic implements HeuristicFunction {

    /**
     *
     * @param state
     * @return
     */
    @Override
	public double h(Object state) {
		TetrisBoard board = (TetrisBoard) state;
		return Math.pow(board.getMaxHeight(), 2.0);
	}
}