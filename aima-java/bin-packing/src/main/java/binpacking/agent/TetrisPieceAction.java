package binpacking.agent;

import aima.core.agent.impl.DynamicAction;
import aima.core.util.datastructure.XYLocation;

/**
 * 
 * @author Ravi Mohan
 * @author R. Lunde
 * @author Robert Jackson
 */
public class TetrisPieceAction extends DynamicAction implements Comparable<Object> {
	// O operations
	/**
     *
     */
	public static final String PLACE_O = "O";
	// I operations
	/**
     *
     */
	public static final String PLACE_I = "I";
	/**
     *
     */
	public static final String ROTATE_AND_PLACE_I = "rI";
	// L operations
	/**
         *
         */
	public static final String PLACE_L = "L";
	/**
         *
         */
	public static final String ROTATE1_AND_PLACE_L = "r1L";
	/**
         *
         */
	public static final String ROTATE2_AND_PLACE_L = "r2L";
	/**
         *
         */
	public static final String ROTATE3_AND_PLACE_L = "r3L";
	// J operations
	/**
         *
         */
	public static final String PLACE_J = "J";
	/**
         *
         */
	public static final String ROTATE1_AND_PLACE_J = "r1J";
	/**
         *
         */
	public static final String ROTATE2_AND_PLACE_J = "r2J";
	/**
         *
         */
	public static final String ROTATE3_AND_PLACE_J = "r3J";
	// Z operations
	/**
         *
         */
	public static final String PLACE_Z = "Z";
	/**
         *
         */
	public static final String ROTATE_AND_PLACE_Z = "rZ";
	// S operations
	/**
         *
         */
	public static final String PLACE_S = "S";
	/**
         *
         */
	public static final String ROTATE_AND_PLACE_S = "rS";
	// T operations
	/**
         *
         */
	public static final String PLACE_T = "T";
	/**
         *
         */
	public static final String ROTATE1_AND_PLACE_T = "r1T";
	/**
         *
         */
	public static final String ROTATE2_AND_PLACE_T = "r2T";
	/**
         *
         */
	public static final String ROTATE3_AND_PLACE_T = "r3T";

	/**
         *
         */
	public static final String ATTRIBUTE_QUEEN_LOC = "@";

	/**
	 * Creates a queen action. Supported values of type are {@link #PLACE_O} ,
	 * {@link #REMOVE_O}, or {@link #TRANSLATE_O}.
	 * 
	 * @param type
	 * @param loc
	 */
	public TetrisPieceAction(String type, XYLocation loc) {
		super(type);
		setAttribute(ATTRIBUTE_QUEEN_LOC, loc);
	}

	/**
	 * 
	 * @return
	 */
	public XYLocation getLocation() {
		return (XYLocation) getAttribute(ATTRIBUTE_QUEEN_LOC);
	}

	/**
	 * 
	 * @return
	 */
	public int getX() {
		return getLocation().getXCoOrdinate();
	}

	/**
	 * 
	 * @return
	 */
	public int getY() {
		return getLocation().getYCoOrdinate();
	}

	@Override
	public int compareTo(Object o) {
		TetrisPieceAction a = (TetrisPieceAction) o;
		return describeAttributes().compareTo(a.describeAttributes());
		
	}
	
	

}
