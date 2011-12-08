package binpacking.mvc.model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;
import org.apache.log4j.Logger;

import cern.colt.matrix.tobject.impl.SparseObjectMatrix2D;

import aima.core.util.datastructure.XYLocation;
import binpacking.mvc.view.BinPackingView;
import binpacking.mvc.view.TetrisBoardView;

/**
 * A generic Tetris board with no GUI.
 * 
 * @author Scott Clee
 * @author Robert Jackson
 */
public class TetrisBoard {
	static final Logger LOG = Logger.getLogger(TetrisBoard.class);
	// static {
	// BasicConfigurator.configure();
	// }
	/**
	 * The value of an empty block.
	 */
	public static final byte EMPTY_BLOCK = -1;

	private SparseObjectMatrix2D fMatrix;
	
	//private Map<String, Byte> fMatrix;
	private short fColumns;
	private short fRows;
	private short numberOfTetrominosOnBoard;
	/**
	 * variables for the number of each tetromino on the board
	 */
	public short numIsOnBoard = 0;
	public short numOsOnBoard = 0;
	public short numLsOnBoard = 0;
	public short numJsOnBoard = 0;
	public short numZsOnBoard = 0;
	public short numSsOnBoard = 0;
	public short numTsOnBoard = 0;
	public int maxHeight;
	public int minHeight;

	/**
	 * Copy Constructor
	 * 
	 * 
	 * @param board
	 */
	public TetrisBoard(TetrisBoard board) {

		fColumns = board.fColumns;
		fRows = board.fRows;
		this.numIsOnBoard = board.numIsOnBoard;
		this.numOsOnBoard = board.numOsOnBoard;
		this.numLsOnBoard = board.numLsOnBoard;
		this.numJsOnBoard = board.numJsOnBoard;
		this.numZsOnBoard = board.numZsOnBoard;
		this.numSsOnBoard = board.numSsOnBoard;
		this.numTsOnBoard = board.numTsOnBoard;
		this.maxHeight = board.maxHeight;

		//final int initSize =board.getfMatrix().size() + 4; 
		final float loadFactor=(float) .999;
		
		fMatrix = new SparseObjectMatrix2D(board.getfMatrix().toArray());
//		fMatrix = new ConcurrentHashMap<String, Byte>(
//				initSize,
//				loadFactor,
//				6
//		); 
													// plus four because adding a
													// tetromino will add 4
													// points
		//fMatrix.putAll(board.getfMatrix());
		this.numberOfTetrominosOnBoard = board.numberOfTetrominosOnBoard;
	}

	/**
	 * Create a TetrisBoard with the desired number of columns and rows.
	 * 
	 * @param (short) i The number of columns.
	 * @param (short) j The number of rows.
	 */
	public TetrisBoard(int i, int j) {

		fColumns = (short) i;
		fRows = (short) j;

		numberOfTetrominosOnBoard = 0;
		//fMatrix = new ConcurrentHashMap<String, Byte>();
		fMatrix = new SparseObjectMatrix2D(fColumns, fRows);
		maxHeight = 0;

	}

	/**
	 * Returns the number of columns in the board.
	 * 
	 * @return The number of columns in the board.
	 */
	public short getColumns() {
		return fColumns;
	}

	/**
	 * Returns the number of rows in the board.
	 * 
	 * @return The number of rows in the board.
	 */
	public short getRows() {
		return fRows;
	}

	/**
	 * Returns the value of the block at the given coordinates.
	 * 
	 * @param x
	 *            The x coordinate.
	 * @param y
	 *            The y coordinate.
	 * @return The value at the given coordinates.
	 */
	public byte getPieceAt(int x, int y) {
		//if(fRows - y < maxHeight)
			//System.out.println(x +" " +y);
			
		//Byte result = fMatrix.get(x + "," + y); // just get this once because
												// lookup on hashtable takes a
												// while
		Byte result = (Byte) fMatrix.getQuick(x, y); // just get this once because

		if (result == null)
			return EMPTY_BLOCK;
		else
			return result.byteValue();
		
	}

	
	/**
	 * Remove the piece from the board.
	 * 
	 * @param piece
	 *            The piece to remove.
	 */
	public void removePiece(TetrisPiece piece) {
		if (piece != null) {
			numberOfTetrominosOnBoard--;
			if (piece.getType() == TetrisPiece.O_PIECE)
				numOsOnBoard--;
			else if (piece.getType() == TetrisPiece.I_PIECE)
				numIsOnBoard--;
			else if (piece.getType() == TetrisPiece.L_PIECE)
				numLsOnBoard--;
			else if (piece.getType() == TetrisPiece.J_PIECE)
				numJsOnBoard--;
			else if (piece.getType() == TetrisPiece.Z_PIECE)
				numZsOnBoard--;
			else if (piece.getType() == TetrisPiece.S_PIECE)
				numSsOnBoard--;
			else if (piece.getType() == TetrisPiece.T_PIECE)
				numTsOnBoard--;			
			
			Point centre = piece.getCentrePoint();
			Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

//				fMatrix.remove(x + "," + y); 
//				fMatrix.put(x + "," + y, EMPTY_BLOCK);
				fMatrix.setQuick(x,y, EMPTY_BLOCK);
			}
			recalculateMaxHeight();
		}
	}
	/**
	 * Add a piece to the board.
	 * 
	 * The notify parameter is there to supress events in such cases as when
	 * performing a fall which will involve multiple add/removes.
	 * 
	 * @param piece
	 *            The piece to add.
	 * @param notify
	 *            If true then fire a BoardEvent once the piece is added.
	 * @throws ArrayIndexOutOfBoundsException
	 * 
	 */
	public void addPieceAt(TetrisPiece piece, XYLocation location)
			throws ArrayIndexOutOfBoundsException {
		piece.setCentrePoint(new Point(location.getXCoOrdinate(), location
				.getYCoOrdinate()));

		boolean needRecalculateMaxHeight = false;

		if (piece != null) {
			int numBlocksOnSolidGround = 0;

			Point centre = piece.getCentrePoint();
			Point[] blocks = piece.getRelativePoints();

			// first check to see if all four blocks are in bounds of board
			for (int count = 0; count < 4; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				if (x < 0 || x >= fColumns)
					throw new ArrayIndexOutOfBoundsException();
				if (y < 0 || y >= fRows)
					throw new ArrayIndexOutOfBoundsException();
			}

			for (int count = 0; count < 4; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				// try to check if space under this block is empty
				// if so -> count++
				try {
					if (y + 1 >= fRows || getPieceAt(x, y + 1) != EMPTY_BLOCK) {
						numBlocksOnSolidGround++;
					}
				} catch (Exception e) {
					// e.printStackTrace();
				}

				fMatrix.setQuick(x,y, piece.getType());

				// fMatrix[x][y] = piece.getType();
				if (fRows - y > maxHeight)
					needRecalculateMaxHeight = true;
			}

			numberOfTetrominosOnBoard++;
			if (piece.getType() == TetrisPiece.O_PIECE)
				numOsOnBoard++;
			else if (piece.getType() == TetrisPiece.I_PIECE)
				numIsOnBoard++;
			else if (piece.getType() == TetrisPiece.L_PIECE)
				numLsOnBoard++;
			else if (piece.getType() == TetrisPiece.J_PIECE)
				numJsOnBoard++;
			else if (piece.getType() == TetrisPiece.Z_PIECE)
				numZsOnBoard++;
			else if (piece.getType() == TetrisPiece.S_PIECE)
				numSsOnBoard++;
			else if (piece.getType() == TetrisPiece.T_PIECE)
				numTsOnBoard++;

			if (needRecalculateMaxHeight == true)
				recalculateMaxHeight();

			printBoardAsString();
		}

	}

	/**
	 * Tests to see if the given piece will fit in this board.
	 * 
	 * @param piece
	 *            The piece to test against.
	 * @return true if the piece fits else false.
	 */
	public boolean willFit(TetrisPiece piece) {
		boolean result = true;

		if (piece != null) {
			Point centre = piece.getCentrePoint();
			Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4 && result == true; count++) {
				int x = centre.x + blocks[count].x;
				int y = centre.y + blocks[count].y;

				// Ensure it's within the boundaries
				if (x < 0 || x >= fColumns || y < 0 || y >= fRows)
					result = false;

				if (result && getPieceAt(x, y) != EMPTY_BLOCK)
					result = false;
			}
		}

		return result;
	}

	/**
	 * Tests to see if the given piece will fit in this board.
	 * 
	 * @param piece
	 *            The piece to test against.
	 * @param loc
	 * @return true if the piece fits else false.
	 */
	public boolean willFit(TetrisPiece piece, XYLocation loc) {
		boolean result = true;

		if (piece != null) {
			Point[] blocks = piece.getRelativePoints();

			for (int count = 0; count < 4 && result == true; count++) {
				int x = loc.getXCoOrdinate() + blocks[count].x;
				int y = loc.getYCoOrdinate() + blocks[count].y;

				// Ensure it's within the boundaries
				if (x < 0 || x >= fColumns || y < 0 || y >= fRows)
					result = false;

				if (result && getPieceAt(x, y) != EMPTY_BLOCK)
					result = false;
			}
		}

		return result;
	}

	/**
	 * Print a neat little ASCII verison of the board to std::out
	 */
	public void printBoardAsString() {
		BinPackingView.setTetrisBoardView(new TetrisBoardView(this));
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Integer> getCurveSequence() {

		ArrayList<Integer> sequence = new ArrayList<Integer>();
		for (int cols = 0; cols < fColumns; cols++) {
			int height = maxHeight;
			for (int rows = fRows - maxHeight; rows < fRows; rows++) { // start at 4th so last
														// inserted piece at top
														// is not counted
				//System.out.println(rows);
				if (getPieceAt(cols, rows) == EMPTY_BLOCK) {
					height--;
					if (rows == fRows - 1) {
						sequence.add(height);
						break;
					}
				} else {
					sequence.add(height);
					break;
				}
			}
		}
		//System.out.println(sequence);
		return sequence;
	}

	/**
	 * 
	 * @return 
	 * @return
	 */
	public ArrayList<Integer> getDerivitiveOfCurve() {

		ArrayList<Integer> seq = getCurveSequence();
		ArrayList<Integer> diffSequence = new ArrayList<Integer>();
		for (int i = 0; i < seq.size() - 1; i++) {
			diffSequence.add(-1 * (seq.get(i) - seq.get(i + 1)));
		}
		//System.out.println(diffSequence);
		return diffSequence;
	}

	/**
	 * 
	 * @param numberOfTetrominosOnBoard
	 */
	public void setNumberOfTetrominosOnBoard(short numberOfTetrominosOnBoard) {
		this.numberOfTetrominosOnBoard = numberOfTetrominosOnBoard;
	}

	/**
	 * 
	 * @return
	 */
	public int getNumberOfTetrominosOnBoard() {
		return numberOfTetrominosOnBoard;
	}

	/**
	 * 
	 * @return the hashmap
	 */
	public SparseObjectMatrix2D getfMatrix() {
		return fMatrix;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreOsToPlace() {
		if (numOsOnBoard != StartStateProperties.numOs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreIsToPlace() {
		if (numIsOnBoard != StartStateProperties.numIs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreLsToPlace() {
		if (numLsOnBoard != StartStateProperties.numLs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreJsToPlace() {
		if (numJsOnBoard != StartStateProperties.numJs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreZsToPlace() {
		if (numZsOnBoard != StartStateProperties.numZs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreSsToPlace() {
		if (numSsOnBoard != StartStateProperties.numSs)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public boolean existMoreTsToPlace() {
		if (numTsOnBoard != StartStateProperties.numTs)
			return true;
		return false;
	}

	/**
	 * NOTE: distance is from TOP of bin!
	 * 
	 * @return returns the height of the highest column on the board
	 */
	public int getMaxHeight() {
		return maxHeight;
	}


	/**
	 * go from 0-lastMaxHeight to see if maxHeight has changed
	 */
	public void recalculateMaxHeight() {
		//System.out.println("recalculateMaxHeight");
		short[] intVector = new short[fColumns];

		for (int col = 0; col < fColumns; col++) {
			int startRow;
			if (maxHeight == 0) // at bottom
				startRow = fRows-1;
			else if (fRows-1 - maxHeight - 4 < 0) // at top
				startRow = 0;
			else // at middle
				startRow = fRows-1 - maxHeight - 5;
			for (int row = startRow; row < fRows; row++) {
				// the first nonempty block that you run into,
				// stop and add the height to the array
				//System.out.println(col + ", " + row);
				if (getPieceAt(col, row) != EMPTY_BLOCK) {
					intVector[col] = (short) (fRows - row);
					break;
				}
			}

		}

		// now intVector contains all the maxs for each column,
		// find the global max in inVector
		Arrays.sort(intVector); // sort in ascending order then return last
								// element
		maxHeight = intVector[intVector.length - 1];
	}

	@Override
	public String toString() {
		String board = "";

		for (int i = 0; i < fRows; i++) {
			for (int j = 0; j < fColumns; j++) {
				if (getPieceAt(j, i) == EMPTY_BLOCK)
					board += "-";
				else
					board += getPieceAt(j, i);
			}
			board += "\n";
		}

		return board;
	}

	/**
	 * returns the number of holes (empty space that lays below blocks)
	 * 
	 * @return
	 */
	public double getNumberOfHoles() {
		/*
		 * foreach column foreach row read until encounter a block count number
		 * of empty blocks after first
		 */
		int numHoles = 0; // init to 0
		boolean foundFirstBlock = false;

		for (int cols = 0; cols < fColumns; cols++) {
			int columnCount = 0;
			foundFirstBlock = false;
			int startRow;
			if (maxHeight == 0) // at bottom
				startRow = fRows-1;
			else if (fRows-1 - maxHeight - 4 < 0) // at top
				startRow = 0;
			else // at middle
				startRow = fRows-1 - maxHeight - 5;
			for (int rows = startRow; rows < fRows && !foundFirstBlock; rows++) {
				if (getPieceAt(cols, rows) != EMPTY_BLOCK) { // first block that
																// isn't empty
					foundFirstBlock = true;
					for (int i = rows; i < fRows; i++) {
						if (getPieceAt(cols, i) == EMPTY_BLOCK) {
							columnCount++;
						}
					}
				}
			}
			numHoles += columnCount;
		}

		return numHoles;
	}

	/**
	 * 
	 * @return returns the height of the lowest stack on the board
	 */
	public int getMinHeight() {
		Vector<Integer> intVector = new Vector<Integer>();
		for (int cols = fColumns - 1; cols >= 0; cols--) {
			for (int rows = 0; rows < fRows; rows++) {
				if (getPieceAt(cols, rows) != EMPTY_BLOCK) {
					intVector.add(fRows - rows);
					break;
				}
				// if it's the last row, then the whole column is empty add 0 to
				// vector
				if (rows == fRows - 1) {
					intVector.add(0);
					break;
				}
			}
		}
		// now intVector contains all the maxs for each column,
		// find the global max in inVector
		int min;

		if (intVector.size() == 0)
			min = 0;
		else
			min = Collections.min(intVector);

		// System.out.println(min);
		return min;
	}

}
