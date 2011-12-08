/**
 * 
 */
package binpacking.agent.heuristics;

import java.awt.Point;
import java.util.Collections;

import binpacking.mvc.model.TetrisBoard;
import binpacking.mvc.model.TetrisPiece;

import aima.core.util.datastructure.XYLocation;

/**
 * @author Robert Jackson
 *
 */
public class BadMoveTest {
	/**
	 * Tests to see if the given piece is "floating"
	 * 
	 * @param piece
	 *            The piece to test against.
	 * @param loc
	 *            The x,y location to test against.
	 * @return true if the piece fits else false.
	 */
	public static boolean isBadMove(TetrisBoard board, TetrisPiece piece, XYLocation loc) {
		boolean result=true;
		int numBlocksOnSolidGround = 0;
		piece.setCentrePoint(new Point(loc.getXCoOrdinate(), loc
				.getYCoOrdinate()));

		Point centre = piece.getCentrePoint();// new Point(loc.getXCoOrdinate(),
												// loc.getYCoOrdinate());
		Point[] blocks = piece.getRelativePoints();

		for (int count = 0; count < 4; count++) {
			int x = centre.x + blocks[count].x;
			int y = centre.y + blocks[count].y;

			// Ensure it's within the boundaries
			if (x < 0 || x >= board.getColumns() || y < 0 || y >= board.getRows())
				return true;

			// try to check if space under this block is empty
			// if so -> count++
			try {
				// DebugOutput.stdOut(x + "," + y);
				// order matters in this evaluation, else an exception will be
				// thrown
				if (y + 1 >= board.getRows() || board.getPieceAt(x,y + 1) != TetrisBoard.EMPTY_BLOCK) {
					numBlocksOnSolidGround++;
				}
			} catch (Exception e) {
				//LOG.info("Out out bounds: " + (x) + "," + (y + 1));
				return true;
			}

		}


	
		/*
		 * here we'll try doing some optimization to try to trim down the search
		 * tree.
		 * 
		 * For example, if the piece is an O and there is only 1 block on the
		 * ground it's not a good move. There are a lot of other case which we
		 * could trim down. I'll try to put them here.
		 */
		switch (numBlocksOnSolidGround) {
		case 0:
			// if there isn't AT LEAST 1 block on
			// solid ground, ERROR!

			//LOG.info("isn't AT LEAST 1 block on solid ground, ERROR!");
			return true;

		case 1:
			if (piece.getType() == TetrisPiece.I_PIECE
					&& piece.getfRotation() == 1)
				result=false;
			// z piece with 1 tile on ground is only good if it's on the bottom of bin
			else if (piece.getType() == TetrisPiece.Z_PIECE
					&& (piece.getfRotation() == 1 && piece.getCentrePoint().y+1 >= board.getRows()-1))
				result=false;
			// same with s piece as z piece
			else if (piece.getType() == TetrisPiece.S_PIECE
					&& (piece.getfRotation() == 1 && piece.getCentrePoint().y+1 >= board.getRows()-1))
				result=false;

			break;
		case 2:
			if (piece.getType() == TetrisPiece.O_PIECE)
				result=false;

			else if (piece.getType() == TetrisPiece.L_PIECE
					&& (piece.getfRotation() == 3 || piece.getfRotation() == 1))
				result=false;
			else if (piece.getType() == TetrisPiece.J_PIECE
					&& (piece.getfRotation() == 1 || piece.getfRotation() == 3))
				result=false;
			else if (piece.getType() == TetrisPiece.Z_PIECE
					&& (piece.getfRotation() == 1 /*|| piece.getfRotation() == 0*/))
				result=false;
			else if (piece.getType() == TetrisPiece.S_PIECE
					&& (piece.getfRotation() == 1 /*|| piece.getfRotation() == 0*/))
				result=false;
			else if (piece.getType() == TetrisPiece.T_PIECE
					&& (piece.getfRotation() == 1 || piece.getfRotation() == 3))
				result=false;

			break;
		case 3:
			if (piece.getType() == TetrisPiece.L_PIECE
					&& (piece.getfRotation() == 2 || piece.getfRotation() == 0))
				result=false;
			else if (piece.getType() == TetrisPiece.J_PIECE
					&& (piece.getfRotation() == 2 || piece.getfRotation() == 0))
				result=false;
			else if (piece.getType() == TetrisPiece.Z_PIECE
					&& piece.getfRotation() == 0)
				result=false;
			else if (piece.getType() == TetrisPiece.S_PIECE
					&& piece.getfRotation() == 0)
				result=false;
			else if (piece.getType() == TetrisPiece.T_PIECE
					&& (piece.getfRotation() == 0 || piece.getfRotation() == 2))
				result=false;

			break;
		case 4:
			if (piece.getType() == TetrisPiece.I_PIECE
					&& piece.getfRotation() == 0)
				result=false;

			break;
		}

		if(result == true)
			return true;		
		if(result==false && !causesCanyon(board, piece, loc))
			return false;
		
		
		// System.out.println("default case:\n" + piece);
		return true;

	}

	private static boolean causesCanyon(TetrisBoard board, TetrisPiece piece, XYLocation loc) {
		/*
		 * checking here to see if adding this piece will cause a "canyon effect"
		 * that is, leaving a positive of negative "cliff."
		 * It not a good move in my opinion because is creates valleys where only a 
		 * few tetrominos can fit sometime, like I pieces in the up vertical possition.
		 */

		
		TetrisBoard boardPrime = board;
		boardPrime.addPieceAt(piece, loc);
		if(
				Collections.max(boardPrime.getDerivitiveOfCurve()) >=  3 || 
				Collections.min(boardPrime.getDerivitiveOfCurve()) <= -3 
					){
			//System.out.println("Bad Move: " + piece);
			boardPrime.removePiece(piece);
			return true;
		}
		boardPrime.removePiece(piece);
		return false;
	}


}
