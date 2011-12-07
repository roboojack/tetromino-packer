package binpacking.agent;

import aima.core.search.framework.GoalTest;
import binpacking.mvc.model.TetrisBoard;

/**
 * @author R. Lunde
 */
public class BinPackingGoalTest implements GoalTest {

	@Override
	public boolean isGoalState(Object state) {
		//DebugOutput.stdOut("isGoalState()");
		boolean result;
		TetrisBoard board = (TetrisBoard) state;
		
		if(!board.existMoreIsToPlace() && 
		   !board.existMoreOsToPlace() &&
		   !board.existMoreLsToPlace() &&
		   !board.existMoreJsToPlace() &&
		   !board.existMoreZsToPlace() &&
		   !board.existMoreSsToPlace() && 
		   !board.existMoreTsToPlace() 
		   ) {
			result=true;
			//DebugOutput.stdOut("SUCCESS! SOLUTION FOUND");
		}
		else
			result=false;
		
		
		return result;
	}
}