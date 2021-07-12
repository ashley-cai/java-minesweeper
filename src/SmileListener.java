import java.awt.Color;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class SmileListener implements ActionListener, MouseListener{

	// Save a reference to the event-generating component that I am listening for
	private MinesweeperPanel panel;
	
	public SmileListener(MinesweeperPanel p) {
		// store the reference, so we can call its methods, like panel.repaint();
		panel = p;
		
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
		panel.reset();
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		panel.reset();

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