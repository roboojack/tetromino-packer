package binpacking.mvc.view;

import java.util.List;

import aima.core.agent.Action;
import binpacking.agent.BinPackingFunctionFactory;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;

/**
 *
 * @author Robert Jackson
 */
public class BoardDrawer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

        /**
         *
         * @param list
         */
        public static void drawBoardFromActions(List<Action> list){
		TetrisBoard board = new TetrisBoard(StartStateProperties.BOARD_WIDTH, StartStateProperties.BOARD_HEIGHT);
		for (Action action : list) {
			board=(TetrisBoard) BinPackingFunctionFactory.getResultFunction().result(board, action);
			BinPackingView.setTetrisBoardView(new TetrisBoardView(board));
		}
		

	}
}
