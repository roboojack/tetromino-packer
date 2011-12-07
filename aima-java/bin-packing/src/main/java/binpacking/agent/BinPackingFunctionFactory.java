package binpacking.agent;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import aima.core.agent.Action;
import aima.core.search.framework.ActionsFunction;
import aima.core.search.framework.ResultFunction;
import aima.core.util.datastructure.XYLocation;
import binpacking.agent.heuristics.BadMoveTest;
import binpacking.debug.DebugOutput;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;
import binpacking.mvc.model.TetrisPiece;

/**
 * 
 * @author Ciaran O'Reilly
 * @author R. Lunde
 * @author Robert Jackson
 * 
 */
public class BinPackingFunctionFactory {
	private static ActionsFunction _iActionsFunction = null;
	private static ResultFunction _resultFunction = null;

	/**
	 * Returns an ACTIONS function for the incremental formulation of the
	 * n-queens problem.
	 * 
	 * @return
	 */
	public static ActionsFunction getActionsFunction() {
		if (null == _iActionsFunction) {
			_iActionsFunction = new TetrisPieceActionsFunction();
		}
		return _iActionsFunction;
	}

	/**
	 * Returns a RESULT function for the n-queens problem.
	 * 
	 * @return
	 */
	public static ResultFunction getResultFunction() {
		if (null == _resultFunction) {
			_resultFunction = new TetrisPieceActionResultFunction();
		}
		return _resultFunction;
	}

	/**
	 * Assumes exactly one queen in each column and provides all possible queen
	 * movements in vertical direction as actions.
	 * 
	 */
	private static class TetrisPieceActionsFunction implements ActionsFunction {
		@Override
		public Set<Action> actions(Object state) {
			int numThreads = (int) (Runtime.getRuntime().availableProcessors() * 1.5);
			int numColumnsPerThead;
			// set columns to evaluate per thread to something reasonable...
			if (StartStateProperties.getBoardWidth() / numThreads < numThreads)
				numColumnsPerThead = 10; // 10 minimum
			else
				numColumnsPerThead = 100;
			// set initial size to height * 2, as this seems to be a good
			// heuristic based
			// on experimentation.
			int actionsListMaxSize = StartStateProperties.getBoardWidth();

			TetrisBoard board = (TetrisBoard) state;
			Set<Action> actions = Collections
					.newSetFromMap(new ConcurrentHashMap<Action, Boolean>(board
							.getColumns(), (float) 1.5, numThreads));

			// Set<Action> actions = new LinkedHashSet<Action>((int)
			// (board.getColumns() * 10));
			// Set<Action> actions = Collections.synchronizedSet(new
			// LinkedHashSet<Action>((int) (board.getColumns() * 10)));

			/**
			 * start from the minimum height then work your way up to the
			 * maximum height + 4
			 */
			// int numThreads = (int)
			// (Runtime.getRuntime().availableProcessors() * 1.5);
			for (int j = board.getRows() - board.getMaxHeight() - 4; j < board.getRows() ; j++) {
				ExecutorService pool = Executors.newCachedThreadPool();//
																		// newFixedThreadPool(numThreads);
				for (int i = 0; i < board.getColumns(); i = i
						+ (numColumnsPerThead)) {
					addActionsToSet(pool, numColumnsPerThead,
							actionsListMaxSize, i, j, board, actions);
					if (actions.size() > actionsListMaxSize) {
						// System.out.println("actions.size() == " +
						// actions.size() + " > " + actionsListMaxSize);
						break;
					}

				}
				try {
					pool.shutdown();
					pool.awaitTermination(10000, TimeUnit.MILLISECONDS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// System.out.println(actions);
			// System.out.println(actions.size());
			return actions;
		}// for loop

		private void addActionsToSet(ExecutorService pool,
				final int numColumnsPerThread, final int actionsListMaxSize,
				final int i, final int j, final TetrisBoard board,
				final Set<Action> actions) {

			pool.execute(new Runnable() {

				@Override
				public void run() {
					TetrisBoard boardCopy = new TetrisBoard(board);
					for (int k = 0; k < numColumnsPerThread; k++) {
						// if this x,y isn't empty -> skip it
						if (i + k < boardCopy.getColumns()
								&& boardCopy.getPieceAt(i + k, j) != TetrisBoard.EMPTY_BLOCK)
							continue;
						else if (boardCopy.getRows() - j > boardCopy
								.getMaxHeight() + 4) {
							// System.out.println("skipping this j: " + j);
							continue;
						}

						XYLocation loc = new XYLocation(i + k, j);
						addTActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addOActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addIActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addLActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addJActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addSActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;
						addZActionsToSet(boardCopy, loc, actions);
						if (actions.size() > actionsListMaxSize)
							break;

					}

				}
			});
		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addZActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreZsToPlace())
				return;

			// Z PIECE ACTIONS
			// try add Z piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.Z_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_Z,
						loc));
			}
			// try rotate and place Z piece
			piece.rotateClockwise();
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE_AND_PLACE_Z, loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addSActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreSsToPlace())
				return;

			// S PIECE ACTIONS
			// try add S piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.S_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_S,
						loc));
			}
			// try rotate and place S piece
			piece.rotateClockwise();
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE_AND_PLACE_S, loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addJActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {

			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreJsToPlace())
				return;

			// J PIECE ACTIONS
			// try place J piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.J_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_J,
						loc));
			}
			// try place J piece rotated 1 times
			// piece = new TetrisPiece(TetrisPiece.J_PIECE);
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE1_AND_PLACE_J, loc));
			}
			// try place J piece rotated 2 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE2_AND_PLACE_J, loc));
			}
			// try place J piece rotated 3 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE3_AND_PLACE_J, loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addLActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreLsToPlace())
				return;

			// L PIECE ACTIONS
			// try place L piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.L_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_L,
						loc));
			}
			// try place L piece rotated 1 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE1_AND_PLACE_L, loc));
			}
			// try place L piece rotated 2 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE2_AND_PLACE_L, loc));
			}
			// try place L piece rotated 3 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE3_AND_PLACE_L, loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addIActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreIsToPlace())
				return;

			// I PIECE ACTIONS
			// try add I piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_I,
						loc));
			}
			// try rotate and place I piece
			piece.rotateClockwise();
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE_AND_PLACE_I, loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addOActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreOsToPlace())
				return;

			// try add O piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.O_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_O,
						loc));
			}

		}

		/**
		 * Adds possible actions for a for Z piece to actions set
		 * 
		 * @param board
		 *            - the state of the board that we would like to add a piece
		 *            to
		 * @param loc
		 *            - the location of which we'd like to add the piece to
		 * @param actions
		 *            - the set of the possible actions, we can add possible
		 *            actions to the actions set
		 */
		private void addTActionsToSet(TetrisBoard board, XYLocation loc,
				Set<Action> actions) {
			// if there are no more piece of this type to place, don't go any
			// further
			if (!board.existMoreTsToPlace())
				return;

			// T PIECE ACTIONS
			// try place T piece
			TetrisPiece piece = new TetrisPiece(TetrisPiece.T_PIECE);
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(TetrisPieceAction.PLACE_T,
						loc));
			}
			// try place T piece rotated 1 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE1_AND_PLACE_T, loc));
			}
			// try place T piece rotated 2 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE2_AND_PLACE_T, loc));
			}
			// try place T piece rotated 3 times
			piece.rotateClockwise(); // rotate!
			if (board.willFit(piece, loc)
					&& !BadMoveTest.isBadMove(board, piece, loc)) {
				actions.add(new TetrisPieceAction(
						TetrisPieceAction.ROTATE3_AND_PLACE_T, loc));
			}

		}
	}// class

	/** Supports queen placing, queen removal, and queen movement actions. */
	private static class TetrisPieceActionResultFunction implements
			ResultFunction {
		@Override
		public Object result(Object s, Action a) {
			DebugOutput.stdOut("TetrisPieceActionResultFunction :: result()");
			if (a instanceof TetrisPieceAction) {
				TetrisPieceAction qa = (TetrisPieceAction) a;
				TetrisBoard board = (TetrisBoard) s;

				TetrisBoard newBoard = new TetrisBoard(board);

				// O ACTION RESULTS
				if (qa.getName().equals(TetrisPieceAction.PLACE_O))
					newBoard.addPieceAt(new TetrisPiece(TetrisPiece.O_PIECE),
							qa.getLocation());

				// I ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_I))
					newBoard.addPieceAt(new TetrisPiece(TetrisPiece.I_PIECE),
							qa.getLocation());
				else if (qa.getName().equals(
						TetrisPieceAction.ROTATE_AND_PLACE_I)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.I_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate piece
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				// L ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_L)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.L_PIECE); // create
																				// piece
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE1_AND_PLACE_L)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.L_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE2_AND_PLACE_L)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.L_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE3_AND_PLACE_L)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.L_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				// J ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_J)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.J_PIECE); // create
																				// piece
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE1_AND_PLACE_J)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.J_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE2_AND_PLACE_J)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.J_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE3_AND_PLACE_J)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.J_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				// Z ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_Z))
					newBoard.addPieceAt(new TetrisPiece(TetrisPiece.Z_PIECE),
							qa.getLocation());
				else if (qa.getName().equals(
						TetrisPieceAction.ROTATE_AND_PLACE_Z)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.Z_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate piece
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				// S ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_S))
					newBoard.addPieceAt(new TetrisPiece(TetrisPiece.S_PIECE),
							qa.getLocation());
				else if (qa.getName().equals(
						TetrisPieceAction.ROTATE_AND_PLACE_S)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.S_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate piece
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				// T ACTION RESULTS
				else if (qa.getName().equals(TetrisPieceAction.PLACE_T)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.T_PIECE); // create
																				// piece
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE1_AND_PLACE_T)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.T_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE2_AND_PLACE_T)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.T_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				} else if (qa.getName().equals(
						TetrisPieceAction.ROTATE3_AND_PLACE_T)) {
					TetrisPiece piece = new TetrisPiece(TetrisPiece.T_PIECE); // create
																				// piece
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					piece.rotateClockwise(); // rotate!
					newBoard.addPieceAt(piece, qa.getLocation());
				}

				s = newBoard;
			}
			// if(System.currentTimeMillis() % 100 == 0){
			// System.out.println("Running GC");
			// System.gc();
			// }
			// if action is not understood or is a NoOp
			// the result will be the current state.
			return s;
		}
	}
}