package binpacking.mvc.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import binpacking.mvc.controller.BoardEvent;
import binpacking.mvc.controller.BoardListener;
import binpacking.mvc.model.StartStateProperties;
import binpacking.mvc.model.TetrisBoard;
import binpacking.mvc.model.TetrisPiece;


/**
 * This class is a GUI for the Tetris Bean.
 * 
 * @author Scott Clee
 * @author Robert Jackson
 */
public class TetrisBoardView
{
	public static final AtomicInteger count = new AtomicInteger(0);
	// weather or not extra cycles should be used to paint the board after every change
	static final boolean SHOW_EVERY_FRAME = true; 
	
	static TetrisBoardGUI fBoardGUI = new TetrisBoardGUI();
	static JPanel fFrame = BinPackingView.getVisualizationPanel() == null ? null : BinPackingView.getVisualizationPanel();
	static Container pane = null;

	
	/**
	 * Create in instance of Scottris.
         *
         * @param board
         */
	public TetrisBoardView(final TetrisBoard board) {
		if(!SHOW_EVERY_FRAME){
			int cycle =count.getAndIncrement();
			//System.out.println(cycle);
			// only update the board every 10th view
			if(cycle < StartStateProperties.getBoardWidth() * 2 && cycle != 0){
				return;
			}
			else if (cycle != 0){
				count.set(0);
			}
		
		}
		
		// check to see if we're running stand-alone app or as panel inside BinPackingApp
		if(fFrame == null){
			//DebugOutput.stdOut("Showing board in stand alone mode...");
			//pane=new JFrame();
			//pane.setPreferredSize(new Dimension(500, 1000));
		}
		else{ // it's not stand-alone, go update the view
			
			// update the gui in another thread
			// or else things will look very poor when
			// doing the update
		    SwingUtilities.invokeLater(new Runnable() {
		        @Override
		        public void run() {
					pane = fFrame;// .getContentPane();
					pane.setLayout(new BorderLayout());
					pane.add(fBoardGUI, BorderLayout.CENTER);

		        	fBoardGUI.boardChange(new BoardEvent(board));

		        }
		    });

		}


	}


        /**
         *
         * @param args
         */
        public static void main(String[] args) {
		TetrisBoard board = new TetrisBoard(StartStateProperties.BOARD_WIDTH,
				StartStateProperties.BOARD_HEIGHT);
		new TetrisBoardView(board);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This class gives a graphical representation of a TetrisBoard GUI
	 */
	public static class TetrisBoardGUI extends JPanel implements BoardListener {
		/**
		 * 
		 */
		private static final long serialVersionUID = -5048944900439555693L;
		private TetrisBoard fBoard;

		@Override
		public void boardChange(final BoardEvent e) {
			fBoard = (TetrisBoard) e.getSource();
			repaint();
		}

		@Override
		public void paintComponent(final Graphics graphics) {
			
			final int width = getBounds().width;
			final int height = getBounds().height;

			// Set up double buffering.
			final Image image = createImage(width, height);
			image.setAccelerationPriority((float) 0.8);
			final Graphics g = image.getGraphics();

			// Draw the board pieces if board not null.
			if (fBoard != null) {
				final int numCols = fBoard.getColumns();
				final int numRows = fBoard.getRows();

				for (int cols = 0; cols < numCols; cols++) {
					for (int rows = numRows - 1 - fBoard.getMaxHeight() - 4; rows < numRows; rows++) {
						final int piece = fBoard.getPieceAt(cols, rows);

						if (piece != TetrisBoard.EMPTY_BLOCK) {
							g.setColor(getPieceColor(piece));
							drawBlock(
									g, 
									(cols * width / numCols) + 1, 
									(rows * height / numRows) + 1,
									(width / numCols) - 1,
									(height / numRows) - 1);
							
						}
					}
				}
			}

			g.setColor(Color.blue);
			g.drawRect(0, 0, width - 1, height - 1);

			graphics.drawImage(image, 0, 0, width, height, null);
			
			graphics.dispose();
			g.dispose();
			image.flush();
		}

		private void drawBlock(final Graphics g, final int x, final int y, final int width, final int height) {
			g.fillRect(x, y, width, height);
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(500, 1000);
		}

		private Color getPieceColor(final int color) {
			Color result = null;

			switch (color) {
			case TetrisPiece.L_PIECE:
				result = new Color(255, 128, 0); // turquoise
				break;
			case TetrisPiece.J_PIECE:
				result = new Color(0, 0, 255); // purple
				break;
			case TetrisPiece.I_PIECE:
				result = new Color(0, 253, 255); // blue
				break;
			case TetrisPiece.Z_PIECE:
				result = new Color(255, 0, 0); // red
				break;
			case TetrisPiece.S_PIECE:
				result = new Color(0, 255, 0); // green
				break;
			case TetrisPiece.O_PIECE:
				result = new Color(255, 255, 0); // gray
				break;
			case TetrisPiece.T_PIECE:
				result = new Color(255, 0, 255); // yellow
				break;
			}

			return result;
		}
	}
}
