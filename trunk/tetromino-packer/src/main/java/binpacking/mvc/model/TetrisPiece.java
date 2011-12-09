package binpacking.mvc.model;

import java.awt.Point;
import java.util.Arrays;

//@formatter:off
/**
 * A generic Tetris piece with no GUI.
 *
 * OX 
 * OO 
 * ??
 * 
 * 
 * IIXI   <-- default case
 * ???? 
 * 
 *  I
 *  X
 *  I
 *  I
 *  ?
 *
 *  X        L     LL    LLX
 *  L      XLL     ?L    L??
 *  LL     ???      X    ?
 *  ??              ?
 *  (3)    (2)     (1)   (0)
 *                       default
 *  
 *  JJ     J        J    JJJ
 *  J?     JJJ      J    ??J
 *  J      ???     JJ      ?
 *  ?              ??
 *  (3)    (2)     (1)   (0)
 *                       default
 * ZZ
 * ?ZZ    <-- default case (0)
 *  ??  
 * 
 *  Z
 * ZZ      (1)
 * Z?
 * ? 

 *  SS
 * SS?   <-- default case (0)
 * ??  
 * 
 * S 
 * SS                     (1)
 * ?S
 *  ?
 *           T      T    T  
 *  TTT     TT     TTT   TT
 *  ?T?     ?T     ???   T?
 *   ?       ?           ?
 *  (0)    (1)     (2)   (3)
 * default

 * 
 * @author Scott Clee
 * @author Robert Jackson
 * 
 */
//@formatter:on
public class TetrisPiece {
    /**
     *
     */
    public static final byte L_PIECE = 0;
        public static final byte J_PIECE = 1;
        public static final byte I_PIECE = 2;
        public static final byte Z_PIECE = 3;
        public static final byte S_PIECE = 4;
        public static final byte O_PIECE = 5;
        public static final byte T_PIECE = 6;


	private byte fType;
	private byte fRotation;
	private byte fMaxRotate;
	private Point fCentrePoint;
	private Point[] fBlocks;


	/**
	 * Create a TetrisPiece object.
	 * 
	 * @param type
	 *            The type/shape of the piece.
	 */
	public TetrisPiece(byte type) {
		fBlocks = new Point[4];
		fCentrePoint = new Point();
		fType = type;
		initializeBlocks();
	}

	/**
	 * Create a TetrisPiece object.
	 * 
	 * @param type
	 *            The type/shape of the piece.
	 * @param board
	 *            The board the piece is going to move around in.
	 */
	public TetrisPiece(byte type, TetrisBoard board) {
		fBlocks = new Point[4];
		fCentrePoint = new Point();
		fType = type;
		initializeBlocks();
	}

	/**
	 * Returns the centre coordinate of this piece.
	 * 
	 * @return The centre point.
	 */
	public Point getCentrePoint() {
		return fCentrePoint;
	}

	/**
	 * Set the Centre point of this piece.
	 * 
         *
         * @param point
         */
	public void setCentrePoint(Point point) {
		fCentrePoint = point;
	}

	/**
	 * Returns an array containing the relative point positions around the
	 * centre piece. i.e. (0, -1) for 1 block above.
	 * 
	 * @return A Point array of relative points.
	 */
	public Point[] getRelativePoints() {
		return fBlocks;
	}

	/**
	 * Set the relative centre points.
	 * 
	 * @param blocks
	 *            The relative centre points.
	 */
	public void setRelativePoints(Point[] blocks) {
		if (blocks != null)
			fBlocks = blocks;
	}

	/**
	 * Returns the type of this piece.
	 * 
	 * @return The type of this piece.
	 */
	public byte getType() {
		return fType;
	}

	/**
	 * Set the type of this piece.
	 * 
	 * @param type
	 *            The type of this piece.
	 */
	public void setType(byte type) {
		fType = type;
		initializeBlocks();
	}

	private void initializeBlocks() {
		switch (fType) {
		case I_PIECE:
			fBlocks[0] = new Point(-1, 0);
			fBlocks[1] = new Point(0, 0);
			fBlocks[2] = new Point(1, 0);
			fBlocks[3] = new Point(2, 0);
			fMaxRotate = 2;
			break;

		case L_PIECE:
			fBlocks[0] = new Point(-1, 0);
			fBlocks[1] = new Point(-1, 1);
			fBlocks[2] = new Point(0, 0);
			fBlocks[3] = new Point(1, 0);
			fMaxRotate = 4;
			break;

		case J_PIECE:
			fBlocks[0] = new Point(-1, 0);
			fBlocks[1] = new Point(0, 0);
			fBlocks[2] = new Point(1, 0);
			fBlocks[3] = new Point(1, 1);
			fMaxRotate = 4;
			break;

		case Z_PIECE:
			fBlocks[0] = new Point(-1, 0);
			fBlocks[1] = new Point(0, 0);
			fBlocks[2] = new Point(0, 1);
			fBlocks[3] = new Point(1, 1);
			fMaxRotate = 2;
			break;

		case S_PIECE:
			fBlocks[0] = new Point(-1, 1);
			fBlocks[1] = new Point(0, 0);
			fBlocks[2] = new Point(0, 1);
			fBlocks[3] = new Point(1, 0);
			fMaxRotate = 2;
			break;

		case O_PIECE:
			fBlocks[0] = new Point(-1, 0);
			fBlocks[1] = new Point(-1, 1);
			fBlocks[2] = new Point(0, 0);
			fBlocks[3] = new Point(0, 1);
			fMaxRotate = 1;
			break;

		case T_PIECE:
			fBlocks[0] = new Point(0, 1);
			fBlocks[1] = new Point(-1, 0);
			fBlocks[2] = new Point(0, 0);
			fBlocks[3] = new Point(1, 0);
			fMaxRotate = 4;
			break;
		}
	}

        /**
         *
         */
        public void rotateClockwise() {
		if (fMaxRotate > 1) // If the piece is allowed to rotate
		{
			fRotation++;

			if (fMaxRotate == 2 && fRotation == 2) {
				// Rotate Anti-Clockwise
				rotateClockwiseNow();
				rotateClockwiseNow();
				rotateClockwiseNow();
			} else
				rotateClockwiseNow();
		}

		fRotation = (byte) (fRotation % fMaxRotate);
	}


	private void rotateClockwiseNow() {
		for (int count = 0; count < 4; count++) {
			final int temp = fBlocks[count].x;

			fBlocks[count].x = -fBlocks[count].y;
			fBlocks[count].y = temp;
		}
	}

	/**
	 * Returns a random piece to use in the given board.
	 * 
	 * @param board
	 *            The board the piece will be in.
	 * @return A random piece.
	 */
	public static TetrisPiece getRandomPiece(TetrisBoard board) {
		return new TetrisPiece((byte) (Math.random() * 7), board);
	}

        /**
         *
         * @return
         */
        public int getfRotation() {
		return fRotation;
	}

	@Override
	public String toString() {
		return "TetrisPiece " + "\n\t[fType=" + fType + "\n\t fRotation="
				+ fRotation + "\n\tfMaxRotate=" + fMaxRotate
				+ "\n\tfCentrePoint=" + fCentrePoint + "\n\tfBlocks="
				+ Arrays.toString(fBlocks) +
				// "\n\tfBoard=" + fBoard +
				"]";
	}

}
