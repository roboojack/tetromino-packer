package binpacking.mvc.model;

/**
 *
 * @author Robert Jackson
 */
public class StartStateProperties {

    /**
     *
     */
    public static short BOARD_HEIGHT =  20;
    /**
     *
     */
    public static short BOARD_WIDTH  =  8;
        /**
         *
         */
        public static short numIs        =  2;
        /**
         *
         */
        public static short numJs        =  0;
        /**
         *
         */
        public static short numLs        =  2;
        /**
         *
         */
        public static short numOs        =  2;
        /**
         *
         */
        public static short numSs        =  0;
        /**
         *
         */
        public static short numTs        =  0;
        /**
         *
         */
        public static short numZs        =  0;
        /**
         *
         */
        public static int numTetrominos = numIs + numJs + numLs + numOs + numSs + numTs + numZs;
        /**
         *
         */
        public static int neededArea = numTetrominos * 4;
        /**
         *
         */
        public static int boardArea = BOARD_HEIGHT * BOARD_WIDTH;
	


        /**
         *
         * @return
         */
        public static short getBoardWidth() {
		return BOARD_WIDTH;
	}

        /**
         *
         * @return
         */
        public static short getBoardHeight() {
		return BOARD_HEIGHT;
	}

        /**
         *
         * @return
         */
        public static int getNeededArea() {
		return (numIs + numJs + numLs + numOs + numSs + numTs + numZs) * 4;
	}
	


}
