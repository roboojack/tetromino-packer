package binpacking.agent.heuristics;

import aima.core.agent.Action;
import aima.core.search.framework.StepCostFunction;
import binpacking.mvc.model.TetrisBoard;

/**
 * return the "cost," here cost means the number of holes that are open
 * and shadowed by the action.
 * 
 * @author Robert Jackson
 */
public class NumberOfHolesStepCostFunction implements StepCostFunction {
	@Override
	public double c(Object s, Action a, Object prime) {
		double result=0;
		
		TetrisBoard board = (TetrisBoard) s;
		TetrisBoard boardPrime = (TetrisBoard) prime;

		// how many more holes new state has
		result=boardPrime.getNumberOfHoles() - board.getNumberOfHoles(); 
		// plus 4 for the tetromino
		result+=4;

		return result;
	}
}
