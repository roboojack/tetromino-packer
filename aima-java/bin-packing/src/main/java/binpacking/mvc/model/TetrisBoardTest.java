package binpacking.mvc.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import aima.core.util.datastructure.XYLocation;

/**
 *
 * @author Robert Jackson
 */
public class TetrisBoardTest {

    /**
     *
     */
    @Test
	public void testMinHeight() {
		// will hold the series of metrics from each addition of a piece


		TetrisBoard board = new TetrisBoard(17, 16);
		System.out.println("test1:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 0);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 0);

		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		System.out.println("test2:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 1);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 0);
		assertFalse(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == -999);


		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		System.out.println("test3:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 2);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 0);

		
		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		System.out.println("test4:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 4);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 0);

		piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 9)
		);
		System.out.println("test5:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 8);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 0);


		
		piece = new TetrisPiece(TetrisPiece.I_PIECE);
		board.addPieceAt(
				piece, 
				new XYLocation(14, 9)
		);
		board.addPieceAt(
				piece, 
				new XYLocation(11, 9)
		);
		board.addPieceAt(
				piece, 
				new XYLocation(8, 9)
		);
		board.addPieceAt(
				piece, 
				new XYLocation(5, 9)
		);
		board.addPieceAt(
				piece, 
				new XYLocation(2, 9)
		);
		board.addPieceAt(
				piece, 
				new XYLocation(1, 9)
		);
		System.out.println("test6:\n" + board);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 8);
		assertTrue(
				"board.getMinHeight() ==" + board.getMinHeight(), 
				board.getMinHeight() == 7);

		
	}
        /**
         *
         */
        @Test
	public void testMaxHeight() {
		// will hold the series of metrics from each addition of a piece


		TetrisBoard board = new TetrisBoard(17, 16);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 0);

		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 15)
		);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 1);


		
		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 14)
		);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 2);

		
		
		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 13)
		);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 4);

		piece = new TetrisPiece(TetrisPiece.I_PIECE);
		piece.rotateClockwise();
		board.addPieceAt(
				piece, 
				new XYLocation(6, 9)
		);
		assertTrue(
				"board.getMaxHeight() ==" + board.getMaxHeight(), 
				board.getMaxHeight() == 8);

	
	}

        /**
         *
         */
//        @Test
//	public void testMaxWidth() {
//		// will hold the series of metrics from each addition of a piece
//
//
//		TetrisBoard board = new TetrisBoard(16, 16);
//		assertTrue(
//				"board.getMaxWidth() ==" + board.getMaxWidth(), 
//				board.getMaxWidth() == 15);
//
//		
//		board.addPieceAt(
//				new TetrisPiece(TetrisPiece.I_PIECE), 
//				new XYLocation(11, 14)
//		);
//		assertTrue(
//				"board.getMaxWidth() ==" + board.getMaxWidth(), 
//				board.getMaxWidth() == 10);
//
//
//		
//		board.addPieceAt(
//				new TetrisPiece(TetrisPiece.I_PIECE), 
//				new XYLocation(6, 14)
//		);
//		assertTrue(
//				"board.getMaxWidth() ==" + board.getMaxWidth(), 
//				board.getMaxWidth() == 5);
//
//		
//		
//		TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
//		piece.rotateClockwise();
//		board.addPieceAt(
//				piece, 
//				new XYLocation(4, 13)
//		);
//		assertTrue(
//				"board.getMaxWidth() ==" + board.getMaxWidth(), 
//				board.getMaxWidth() == 4);
//
//		piece = new TetrisPiece(TetrisPiece.I_PIECE);
//		piece.rotateClockwise();
//		board.addPieceAt(
//				piece, 
//				new XYLocation(3, 9)
//		);
//		assertTrue(
//				"board.getMaxWidth() ==" + board.getMaxWidth(), 
//				board.getMaxWidth() == 3);
//
//	
//	}

	/**
	 * OX 
	 * OO 
	 * ??
	 */
	@Test
	public void testAddOPiece() {

		TetrisBoard board = new TetrisBoard(8, 8);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.O_PIECE), 
				new XYLocation(1, 0))
		;
		System.out.println("test1:\n" + board);
		assertTrue(
				board.getPieceAt(0,0) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 1 ,0) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 0 ,1) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 1 ,1) == TetrisPiece.O_PIECE 
				);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.O_PIECE), 
				new XYLocation(7, 0))
		;
		System.out.println("test2:\n" + board);
		assertTrue(
				board.getPieceAt( 7 ,0) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 6 ,0) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 7 ,1) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 6 ,1) == TetrisPiece.O_PIECE 
				);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.O_PIECE), 
				new XYLocation(1, 6))
		;
		System.out.println("test3:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,6) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 0 ,7) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 1 ,6) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 1 ,7) == TetrisPiece.O_PIECE 
				);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.O_PIECE), 
				new XYLocation(7, 6))
		;
		System.out.println("test4:\n" + board);
		assertTrue(
				board.getPieceAt( 6 ,6) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 6 ,7) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 7 ,6) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 7 ,7) == TetrisPiece.O_PIECE 
				);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.O_PIECE), 
				new XYLocation(4, 3))
		;
		System.out.println("test4:\n" + board);
		assertTrue(
				board.getPieceAt( 3 ,3) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 3 ,4) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 4 ,3) == TetrisPiece.O_PIECE && 
				board.getPieceAt( 4 ,4) == TetrisPiece.O_PIECE 
				);
	
	}

	
	
	
	
	
	
	
	
	
	/**
	 * IXII   <-- default case
	 * ???? 
	 * 
	 *  I
	 *  X
	 *  I
	 *  I
	 *  ?
	 */
	@Test
	public void testAddIPiece() {

		TetrisBoard board = new TetrisBoard(8, 8);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(2, 0))
		;
		System.out.println("test1:\n" + board);
		assertTrue(
				board.getPieceAt( 1 ,0) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 2 ,0) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 3 ,0) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 4 ,0) == TetrisPiece.I_PIECE 
				);

		try {
			board.addPieceAt(
					new TetrisPiece(TetrisPiece.I_PIECE), 
					new XYLocation(0, 1))
			;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test2:\n" + board);
		assertFalse(
				board.getPieceAt( 0 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 1 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 2 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 3 ,1) == TetrisPiece.I_PIECE 
				);

		try {
			board.addPieceAt(
					new TetrisPiece(TetrisPiece.I_PIECE), 
					new XYLocation(6, 1))
			;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test3:\n" + board);
		assertFalse(
				board.getPieceAt( 4 ,1) == TetrisPiece.I_PIECE || 
				board.getPieceAt( 5 ,1) == TetrisPiece.I_PIECE || 
				board.getPieceAt( 6 ,1) == TetrisPiece.I_PIECE || 
				board.getPieceAt( 7 ,1) == TetrisPiece.I_PIECE 
				);


		board.addPieceAt(
				new TetrisPiece(TetrisPiece.I_PIECE), 
				new XYLocation(5, 1))
		;
		System.out.println("test4:\n" + board);
		assertTrue(
				board.getPieceAt( 7 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 6 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 5 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 4 ,1) == TetrisPiece.I_PIECE 
				);

		TetrisPiece p = new TetrisPiece(TetrisPiece.I_PIECE);
		p.rotateClockwise();
		board.addPieceAt(
				p, 
				new XYLocation(0, 5)
		);
		System.out.println("test5:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,4) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,5) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,6) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,7) == TetrisPiece.I_PIECE 
		);

		try {
			board.addPieceAt(
					p, 
					new XYLocation(1, 6)
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test6:\n" + board);
		assertTrue(
				board.getPieceAt( 1 ,4) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 1 ,5) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 1 ,6) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 1 ,7) != TetrisPiece.I_PIECE 
		);

		try {
			board.addPieceAt(
					p, 
					new XYLocation(0, 0)
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test7:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,0) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,1) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,2) != TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,3) != TetrisPiece.I_PIECE 
		);

		board.addPieceAt(
				p, 
				new XYLocation(0, 1)
		);
		System.out.println("test8:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,0) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,1) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,2) == TetrisPiece.I_PIECE && 
				board.getPieceAt( 0 ,3) == TetrisPiece.I_PIECE 
		);
		
	}

	/** XS
	 * SS?   <-- default case (0)
	 * ??  
	 * 
	 * S 
	 * SX                     (1)
	 * ?S
	 *  ?
	 *   
	 */
	@Test
	public void testAddSPiece() {

		TetrisBoard board = new TetrisBoard(8, 8);

		board.addPieceAt(
				new TetrisPiece(TetrisPiece.S_PIECE), 
				new XYLocation(2, 0))
		;
		System.out.println("test1:\n" + board);
		assertTrue(
				board.getPieceAt( 2 ,0) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 3 ,0) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 1 ,1) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 2 ,1) == TetrisPiece.S_PIECE 
				);

		try {
			board.addPieceAt(
					new TetrisPiece(TetrisPiece.S_PIECE), 
					new XYLocation(0, 1))
			;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test2:\n" + board);
		assertFalse(
				board.getPieceAt( 0 ,1) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 1 ,1) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 2 ,1) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 3 ,1) == TetrisPiece.S_PIECE 
				);

		try {
			board.addPieceAt(
					new TetrisPiece(TetrisPiece.S_PIECE), 
					new XYLocation(7, 1))
			;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test3:\n" + board);
		assertFalse(
				board.getPieceAt( 6 ,2) == TetrisPiece.S_PIECE || 
				board.getPieceAt( 7 ,1) == TetrisPiece.S_PIECE || 
				board.getPieceAt( 7 ,2) == TetrisPiece.S_PIECE 
				);



		TetrisPiece p = new TetrisPiece(TetrisPiece.S_PIECE);
		p.rotateClockwise();
		board.addPieceAt(
				p, 
				new XYLocation(1, 5)
		);
		System.out.println("test5:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,4) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 0 ,5) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 1 ,5) == TetrisPiece.S_PIECE && 
				board.getPieceAt( 1 ,6) == TetrisPiece.S_PIECE 
		);

		try {
			board.addPieceAt(
					p, 
					new XYLocation(0, 6)
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test6:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,6) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 0 ,7) != TetrisPiece.S_PIECE 
		);

		try {
			board.addPieceAt(
					p, 
					new XYLocation(0, 0)
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test7:\n" + board);
		assertTrue(
				board.getPieceAt( 0 ,0) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 0 ,1) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 0 ,2) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 0 ,3) != TetrisPiece.S_PIECE 
		);

		try {
			board.addPieceAt(
					p, 
					new XYLocation(7, 7)
			);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("test8:\n" + board);
		assertTrue(
				board.getPieceAt( 7 ,7) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 6 ,7) != TetrisPiece.S_PIECE && 
				board.getPieceAt( 6 ,6) != TetrisPiece.S_PIECE

		);
		
	}

	
}
