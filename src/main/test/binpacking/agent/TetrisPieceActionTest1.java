package binpacking.agent;

import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import aima.core.agent.Action;
import aima.core.util.datastructure.XYLocation;

public class TetrisPieceActionTest1 {

	@Test
	public void test1() {
		Set<Action> actions = 
			Collections.newSetFromMap(new ConcurrentHashMap<Action, Boolean>());//Collections.synchronizedSet(new LinkedHashSet<Action>());

		assertTrue(actions.size() == 0);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 0))
		);
		assertTrue(actions.size() == 1);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 0))
		);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 0))
		);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 0))
		);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 0))
		);
		System.out.println(actions);
		assertTrue(actions.size() == 1);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(0, 1))
		);
		System.out.println(actions);
		assertTrue(actions.size() == 2);
		actions.add(
				new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
				new XYLocation(1, 0))
		);
		System.out.println(actions);
		assertTrue(actions.size() == 3);
		


	}

}
