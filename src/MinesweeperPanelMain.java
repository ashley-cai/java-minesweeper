import javax.swing.*;
import java.awt.*;

public class MinesweeperPanelMain{
		
	public static void main(String[] args) {
		JFrame window = new JFrame("Minesweeper");
		MinesweeperPanelMain content = new MinesweeperPanelMain();
		MinesweeperPanel panel = new MinesweeperPanel(content);
		//content.setLayout(new BorderLayout());
				
		window.add(panel);
		window.setVisible(true);
		window.setSize(500,510); 
		window.setLocation(500,300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(panel);

	}
}