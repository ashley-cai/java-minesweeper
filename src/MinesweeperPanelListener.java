import java.awt.Color;
import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MinesweeperPanelListener implements ActionListener, MouseListener{

	// Save a reference to the event-generating component that I am listening for
	private MinesweeperPanel panel;
	private Cell cell;
	int row;
	int col;

	public MinesweeperPanelListener(MinesweeperPanel p, Cell c, int rows, int cols) {
		// store the reference, so we can call its methods, like panel.repaint();
		panel = p; 
		cell = c;
		row = rows;
		col = cols;
		panel.addMouseListener(this);
		// register listener with the panel
	}


	public void mouseClicked(MouseEvent e) {

		// TODO Auto-generated method stub
	}

	// NO DRAWING!  Just set the state of Panel to include a new Rectangle
	// Only work with graphics inside paintComponent() in the Panel class
	// Set the state of instance variables to include a new Rectangle



	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		panel.setShock();

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		if(panel.getFirst() == false) {
			panel.setBoardAgain(row,col);
			panel.setFirst(true);
		}

		if (SwingUtilities.isRightMouseButton(e)) {
			if(cell.getMarked() == false) {
				panel.markCell(row, col);
			}
			else {
				panel.unmarkCell(row, col);
			}
		}
		else{
			if(cell.getMarked() == false) {
				if (cell.getBombable() == true) {
					if(cell.getMarked() == false) {
						panel.setLoss(true);
						cell.setDeath();
						panel.setLoser();
					}
				}
				else {
					panel.revealCell(row,col);
				}
			}
		}
		panel.setNormal();
		panel.repaint();

		if(panel.checkWin()) {
			panel.setWinner();
			JOptionPane.showMessageDialog(panel, "Congratulations, you won!");
			panel.reset();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}






}