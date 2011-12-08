package binpacking.agent.heuristics;

import static org.junit.Assert.assertFalse;
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
public class NumberOfHolesHeuristicTest {

    /**
     *
     */
    @Test
	public void test1() {
		// will hold the series of metrics from each addition of a piece
		Vector<Double> doubleVector = new Vector<Double>();

		NumberOfHolesHeuristic nhh = new NumberOfHolesHeuristic();

		TetrisBoard board = new TetrisBoard(16, 16);
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 0);
		System.out.println("1: 0==" + nhh.h(board));
		System.out.println(board.toString());

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		System.out.println("1.1: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 0);


		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		System.out.println("2: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 0);

		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		System.out.println("3: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 0);


		piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(9, 5)
		);
		System.out.println("4: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board) == 8);

		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(9, 14)
		);
		System.out.println("5: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 10);
		assertFalse(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 100);

		
		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.L_PIECE), 
				new XYLocation(2, 0)
		);
		System.out.println("6: 0==" + nhh.h(board));
		System.out.println(board.toString());
		doubleVector.add(nhh.h(board));
		assertTrue(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 48);
		assertFalse(
				"nhh.h(board) ==" + nhh.h(board), 
				nhh.h(board)  == 100);

		
		
		DebugOutput.stdOut(doubleVector);
	
	}

/*	
	@Test
	public void testH() {
		TetrisBoard board = new TetrisBoard(16, 16);
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		
		NumberOfHolesHeuristic mhh = new NumberOfHolesHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 1);

		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		
		mhh = new NumberOfHolesHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 2);

		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		
		mhh = new NumberOfHolesHeuristic();
		assertTrue("mhh.h(board)==" + mhh.h(board), mhh.h(board) == 4);
	}
*/
}
