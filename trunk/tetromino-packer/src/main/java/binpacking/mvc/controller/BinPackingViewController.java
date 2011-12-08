package binpacking.mvc.controller;

import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

import binpacking.mvc.controller.main.BinPackingAStar;
import binpacking.mvc.controller.main.BinPackingBFS;
import binpacking.mvc.controller.main.BinPackingDFS;
import binpacking.mvc.controller.main.BinPackingGreedyBestFirstSearch;
import binpacking.mvc.controller.main.BinPackingHillClimbing;
import binpacking.mvc.controller.main.BinPackingIterativeDeepeningSearch;
import binpacking.mvc.controller.main.BinPackingRecursiveDLS;
import binpacking.mvc.controller.main.BinPackingSimulatedAnnealing;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;
import binpacking.mvc.view.BinPackingView;

/**
 *
 * @author Robert Jackson
 */
public class BinPackingViewController {
	static final Logger LOG = Logger.getLogger(TetrisBoard.class);


	
        /**
         *
         * @param string
         */
        public static void addText(final String string) {
		if(BinPackingView.output != null)
			BinPackingView.output.setText(BinPackingView.output.getText() + "\n" + string);
		else
			LOG.info(string);
	}

	/**
	 * while(!isSelectedOptionsSane()){
	 *  numIs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numJs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numLs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numOs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numSs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numTs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 *  numZs = random(0, BOARD_HEIGHT * BOARD_WIDTH / 4)
	 * }
	 */
	public static void generateRandomTetrominoSet() {
	    BinPackingViewController.addText("generateRandomTetrominoSet() called");
	
	    
	    StartStateProperties.numIs = 0;
	    StartStateProperties.numJs = 0;
	    StartStateProperties.numLs = 0;
	    StartStateProperties.numOs = 0;
	    StartStateProperties.numSs = 0;
	    StartStateProperties.numTs = 0;
	    StartStateProperties.numZs = 0;
	
	
	
	    while (!isSelectedOptionsSane()) {
	        StartStateProperties.numIs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numJs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numLs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numOs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numSs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numTs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	        StartStateProperties.numZs = (short) Math.floor(Math.random() * Math.floor(StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH / 4));
	    }
	    BinPackingViewController.addText("Total area in this configuration is: " + StartStateProperties.numTetrominos * 4 + " and total area is: " + StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH);
	
	    BinPackingView.numIsTextField.setText(String.valueOf(StartStateProperties.numIs));
	    BinPackingView.numJsTextField.setText(String.valueOf(StartStateProperties.numJs));
	    BinPackingView.numLsTextField.setText(String.valueOf(StartStateProperties.numLs));
	    BinPackingView.numOsTextField.setText(String.valueOf(StartStateProperties.numOs));
	    BinPackingView.numSsTextField.setText(String.valueOf(StartStateProperties.numSs));
	    BinPackingView.numTsTextField.setText(String.valueOf(StartStateProperties.numTs));
	    BinPackingView.numZsTextField.setText(String.valueOf(StartStateProperties.numZs));
		StartStateProperties.neededArea=
			StartStateProperties.numIs +
			StartStateProperties.numJs +
			StartStateProperties.numLs +
			StartStateProperties.numOs +
			StartStateProperties.numSs +
			StartStateProperties.numTs +
			StartStateProperties.numZs;
	}

	/**
	 * each tetromino has 4 units of area, so:
	 *      4 * numTetrominos <= BOARD_HEIGHT * BOARD_WIDTH &&
	 *      numTetrominos > 0
         *
         * @return
         */
	public static boolean isSelectedOptionsSane() {
	    //addText("isSelectedOptionsSane() called");
	
	    StartStateProperties.numTetrominos = 
	    	StartStateProperties.numIs + StartStateProperties.numJs + 
	    	StartStateProperties.numLs + StartStateProperties.numOs + 
	    	StartStateProperties.numSs + StartStateProperties.numTs + StartStateProperties.numZs;
	    if (4 * StartStateProperties.numTetrominos 
	    		<= 
	    		StartStateProperties.BOARD_HEIGHT * StartStateProperties.BOARD_WIDTH && StartStateProperties.numTetrominos > 0) {
	        return true;
	    }
	    // else return false
	    return false;
	}

        /**
         *
         * @param evt
         */
        public static void randomStateButtonActionPerformed(final java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomStateButtonActionPerformed
	    BinPackingViewController.addText("randomStateButtonActionPerformed() called");
	    setBoardDimentions();
	    generateRandomTetrominoSet();
	}//GEN-LAST:event_randomStateButtonActionPerformed

        /**
         *
         */
        public static void setBoardDimentions() {
		StartStateProperties.BOARD_HEIGHT=(short) Integer.parseInt(BinPackingView.boardHeightTextField.getText());
		StartStateProperties.BOARD_WIDTH=(short) Integer.parseInt(BinPackingView.boardWidthTextField.getText());
		StartStateProperties.boardArea=StartStateProperties.BOARD_HEIGHT*StartStateProperties.BOARD_WIDTH;

		
		
	}

        /**
         *
         */
        public static void setEachTetrominoFromTextField() {
	
	    StartStateProperties.numIs=(short) Integer.parseInt(BinPackingView.numIsTextField.getText());
	    StartStateProperties.numJs=(short) Integer.parseInt(BinPackingView.numJsTextField.getText());
	    StartStateProperties.numLs=(short) Integer.parseInt(BinPackingView.numLsTextField.getText());
	    StartStateProperties.numOs=(short) Integer.parseInt(BinPackingView.numOsTextField.getText());
	    StartStateProperties.numSs=(short) Integer.parseInt(BinPackingView.numSsTextField.getText());
	    StartStateProperties.numTs=(short) Integer.parseInt(BinPackingView.numTsTextField.getText());
	    StartStateProperties.numZs=(short) Integer.parseInt(BinPackingView.numZsTextField.getText());
	
	}

        /**
         *
         * @param evt
         */
        public static void startSearchButtonActionPerformed(final java.awt.event.ActionEvent evt) {
	        BinPackingViewController.addText("startSearchButtonActionPerformed() called");
	        setBoardDimentions();
	        setEachTetrominoFromTextField();
	
	        if (isSelectedOptionsSane()) {
	
	            BinPackingViewController.addText("Your selected options are valid..." +
	            		"\n\tNeeded area=" + StartStateProperties.getNeededArea() +				
						"\n\tBoard area =" + StartStateProperties.boardArea );
	
	            //now do the appropriate search given their selection in a new thread
	            switch (BinPackingView.searchAlgorithmCombo.getSelectedIndex()) {
				case 0: // DFS
					t = new Thread(new BinPackingDFS());
					t.start();
				break;
	
				case 1: // BFS
					t = new Thread(new BinPackingBFS());
					t.start();
					break;
	
				case 2: // Recursive DLS
					t = new Thread(new BinPackingRecursiveDLS());
					t.start();
					break;
	
				case 3: // Iterative Deepening
					t = new Thread(new BinPackingIterativeDeepeningSearch());
					t.start();
					break;
	
				case 4: // Simulated Annealing
					t = new Thread(new BinPackingSimulatedAnnealing());
					t.start();
					break;
	
				case 5: // Hill Climbing
					t = new Thread(new BinPackingHillClimbing());
					t.start();
					break;
	
				case 6: // A*
					t = new Thread(new BinPackingAStar());
					t.start();
					break;
	
				case 7: // Greedy Best First Search
					t = new Thread(new BinPackingGreedyBestFirstSearch());
					t.start();
					break;
	
				default:
		            BinPackingViewController.addText("Invalid search method selected or Search not implemented yet.");
					break;
				}//switch
	            
	        } //isSelectedOptionsSane()
	        else {
	            BinPackingViewController.addText("Your selected options are not valid, try a different configuration.");
	        }//else
	    }//startSearchButtonActionPerformed

        /**
         *
         * @param evt
         */
        public static void stopSearchButtonActionPerformed(final ActionEvent evt) {
	    BinPackingViewController.addText("stopSearchButtonActionPerformed() called." +
	    		"\n\t...trying to shutdown running threads... ");
	    System.out.println(Thread.getAllStackTraces());
	    if(t != null){
	    	t.interrupt();
	    	t.stop();
	    }
	    System.gc();
	}

        /**
         *
         */
        public static Thread t = null;

}
