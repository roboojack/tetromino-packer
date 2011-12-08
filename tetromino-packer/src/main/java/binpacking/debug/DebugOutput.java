package binpacking.debug;

import javax.swing.SwingUtilities;

import binpacking.mvc.controller.BinPackingViewController;
import binpacking.mvc.view.BinPackingView;

/**
 *
 * @author Robert Jackson
 */
public class DebugOutput {

    /**
     *
     * @param string
     */
    public static void stdOut(final String string){
//	    SwingUtilities.invokeLater(new Runnable() {
//	        @Override
//	        public void run() {
//	    		BinPackingView.addText(string);
//	        }
//	    });


		//System.out.println(string);
	}
        /**
         *
         * @param object
         */
        public static void stdOut(Object object){
		//System.out.println(object.toString());
	}

        /**
         *
         */
        public static void stdOut(){
		//System.out.println();
	}

        /**
         *
         * @param string
         */
        public static void infoPanelOut(final String string){
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	if(BinPackingView.getVisualizationPanel() != null)
	        		BinPackingViewController.addText(string);
	        	else
	        		System.out.println(string);
	        }
	    });

	}

        /**
         *
         */
        public static void infoPanelOut(){
	    SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	    		BinPackingViewController.addText("\n");
	        }
	    });

	}
}
