package binpacking.mvc.controller.main;

import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;

/**
 * @author Robert Jackson
 * 
 */

public interface BinPackingSearch extends Runnable {


    /**
     *
     * @throws Exception
     */
    public void doSearch() throws Exception;

    /**
     *
     * @param properties
     */
    public void printInstrumentation(Properties properties);
	
        /**
         *
         * @param actions
         */
        public void printActions(List<Action> actions);

	@Override
	public void run();


}