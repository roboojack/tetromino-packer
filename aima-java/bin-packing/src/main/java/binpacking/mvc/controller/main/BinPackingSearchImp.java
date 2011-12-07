package binpacking.mvc.controller.main;

import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import aima.core.agent.Action;
import aima.core.search.framework.SearchAgent;
import binpacking.debug.DebugOutput;
import binpacking.mvc.view.BoardDrawer;

/**
 * @author Robert Jackson
 * 
 */

public class BinPackingSearchImp implements BinPackingSearch {
    /**
     *
     */
    public static SearchAgent agent;
	
    /**
     *
     * @throws Exception
     */
    @Override
	public void doSearch() throws Exception {
		// TODO Auto-generated method stub
		
	}


        /**
         *
         * @param properties
         */
        @Override
	public void printInstrumentation(Properties properties) {
		Iterator<Object> keys = properties.keySet().iterator();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			String property = properties.getProperty(key);
			DebugOutput.infoPanelOut(key + " : " + property);
		}

	}

	
        /**
         *
         * @param actions
         */
        @Override
	public void printActions(List<Action> actions) {
		for (int i = 0; i < actions.size(); i++) {
			String action = actions.get(i).toString();
			DebugOutput.infoPanelOut(action);
		}
	}

	@Override
	public void run() {
		try {
			doSearch();
			printActions(agent.getActions());
			printInstrumentation(agent.getInstrumentation());
			if(agent.getActions().isEmpty())
				DebugOutput.infoPanelOut("FAILURE: DONE SEARCHING AND NO SOLUTION FOUND...");

			// draw final config on graphic board
			BoardDrawer.drawBoardFromActions(getAgent().getActions());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println("I'm interrupted!");
			e.printStackTrace();
		}

		
	}


        /**
         *
         * @param agent
         */
        public void setAgent(SearchAgent agent) {
		BinPackingSearchImp.agent = agent;
	}


        /**
         *
         * @return
         */
        public static SearchAgent getAgent() {
		return agent;
	}


}