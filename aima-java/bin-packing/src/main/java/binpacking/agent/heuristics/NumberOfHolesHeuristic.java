package binpacking.agent.heuristics;

import java.util.Collections;

import aima.core.search.framework.HeuristicFunction;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 * Estimates the distance to goal by:
 * 
 *  #Tiles to put on board - #number of tiles on the board 
 * 
 * @author Robert Jackson
 */
public class NumberOfHolesHeuristic implements HeuristicFunction {

    /**
     *
     * @param state - the state coerced to a tetrisboard
     * @return      - how much more there is left to go on this path of the tree 
     */
    @Override
	public double h(Object state) {
		double result = 0;
		
		TetrisBoard board = (TetrisBoard) state;
	
		
		if(StartStateProperties.getNeededArea() == StartStateProperties.boardArea){
			
			result = StartStateProperties.getNeededArea()
			+
			(4 * board.getNumberOfHoles())
			- 
			board.getNumberOfTetrominosOnBoard() * 4;

		} else { // needed area < board area 
			result = StartStateProperties.getNeededArea() 
			+
			(1 * board.getNumberOfHoles()) 
			-
			board.getNumberOfTetrominosOnBoard() * 4;
		
		}
//		System.out.println(
//				"numHoles    : " + board.getNumberOfHoles() + "\t" +
//				"tilesOnBoard: " + board.getNumberOfTetrominosOnBoard() * 4 + "\t" + 
//				"distanceLeft: " + result);
		return result;
	}//h

}//class