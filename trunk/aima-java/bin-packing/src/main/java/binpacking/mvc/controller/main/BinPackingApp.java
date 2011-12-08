/*
 * BinPackingApp.java
 */

package binpacking.mvc.controller.main;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import binpacking.mvc.view.BinPackingView;

/**
 * The main class of the application.
 */
public class BinPackingApp extends SingleFrameApplication {

    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new BinPackingView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     * @param root
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of BinPackingApp
     */
    public static BinPackingApp getApplication() {
        return Application.getInstance(BinPackingApp.class);
    }

    /**
     * Main method launching the application.
     * @param args
     */
    public static void main(String[] args) {
    	go();
    }
    
    /**
     * start up the application
     */
    public static void go(){
//    	BasicConfigurator.configure();
        launch(BinPackingApp.class, null);

    }

    /**
     * start up the application
     */
    public static void kill(){
    	getApplication().quit(null);
    }
}
