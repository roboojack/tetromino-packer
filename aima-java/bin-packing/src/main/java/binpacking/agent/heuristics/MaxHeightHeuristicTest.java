package binpacking.agent.heuristics;

import static org.junit.Assert.assertTrue;

import java.util.Vector;

import org.junit.Test;

import aima.core.util.datastructure.XYLocation;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.TetrisBoard;
import binpacking.mvc.model.TetrisPiece;

/**
 *
 * @author Robert Jackson
 */
public class MaxHeightHeuristicTest {

    /**
     *
     */
    @Test
	public void test1() {
		// will hold the series of metrics from each addition of a piece
		Vector<Double> doubleVector = new Vector<Double>();

		MaxHeightHeuristic mhh = new MaxHeightHeuristic();

		
		TetrisBoard board = new TetrisBoard(16, 16);
		doubleVector.add(mhh.h(board));
		assertTrue(
				"doubleVector.get(0) ==" + doubleVector.get(0), 
				doubleVector.get(0) == 0);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		doubleVector.add(mhh.h(board));
		assertTrue(
				"doubleVector.get(1) ==" + doubleVector.get(1), 
				doubleVector.get(1) > doubleVector.get(0));


		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		doubleVector.add(mhh.h(board));
		assertTrue(
				"doubleVector.get(2) ==" + doubleVector.get(2), 
				doubleVector.get(2) > doubleVector.get(1));

		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		doubleVector.add(mhh.h(board));
		assertTrue(
				"doubleVector.get(3) ==" + doubleVector.get(3), 
				doubleVector.get(3) > doubleVector.get(2));

		DebugOutput.stdOut(doubleVector);
	
	}

        /**
         *
         */
        @Test
	public void testH() {
		TetrisBoard board = new TetrisBoard(16, 16);
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		
		MaxHeightHeuristic mhh = new MaxHeightHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 1);

		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		
		mhh = new MaxHeightHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 2);

		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		
		mhh = new MaxHeightHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 4);
	}

}
