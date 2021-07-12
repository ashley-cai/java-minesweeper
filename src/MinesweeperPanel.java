import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class MinesweeperPanel extends JPanel{

	private Cell[][] cells;
	private static int ROWS = 24;
	private static int COLS = 24;
	private int bombNumber;
	private Checker checker;
	private boolean loss;
	private boolean win;
	private boolean first;
	ImageIcon bombLost = new ImageIcon("resources/bomblost.jpg");
	ImageIcon bomb = new ImageIcon("resources/bomb.png");
	ImageIcon flag = new ImageIcon("resources/flag.png");

	ImageIcon normal = new ImageIcon("resources/normal.png");
	ImageIcon shock = new ImageIcon("resources/shock.png");
	ImageIcon winner = new ImageIcon("resources/winner.png");
	ImageIcon loser = new ImageIcon("resources/loser.png");

	JButton smile;

	MinesweeperPanel(MinesweeperPanelMain content){

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(ROWS,COLS));

		Image img = bombLost.getImage();
		Image resizedImage = img.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		bombLost.setImage(resizedImage);

		Image img2 = bomb.getImage();
		Image resizedImage2 = img2.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		bomb.setImage(resizedImage2);

		Image img3 = flag.getImage();
		Image resizedImage3 = img3.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		flag.setImage(resizedImage3);

		cells = new Cell[24][24];
		first = false;

		for(int rows = 0; rows < ROWS; rows++) {
			for(int cols = 0; cols < COLS; cols++) {
				cells[rows][cols] = new Cell("");
				cells[rows][cols].setPreferredSize(new Dimension(20, 20));
				MinesweeperPanelListener listener = new MinesweeperPanelListener(this, cells[rows][cols], rows, cols);
				cells[rows][cols].addMouseListener(listener);
				buttonPanel.add(cells[rows][cols]);
			}
		}

		smile = new JButton(normal);
		SmileListener listener = new SmileListener(this);
		smile.addMouseListener(listener);

		//smileys
		Image img4 = normal.getImage();
		Image resizedImage4 = img4.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		normal.setImage(resizedImage4);

		Image img5 = shock.getImage();
		Image resizedImage5 = img5.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		shock.setImage(resizedImage5);

		Image img6 = winner.getImage();
		Image resizedImage6 = img6.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		winner.setImage(resizedImage6);

		Image img7 = loser.getImage();
		Image resizedImage7 = img7.getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH);  
		loser.setImage(resizedImage7);

		//JMenu instructions = new JMenu("Instructions");	
		
		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(smile, BorderLayout.NORTH);
		//this.add(instructions, BorderLayout.SOUTH);

		//smile.setPreferredSize(new Dimension(20, 20)); // It's not working :(

		//bombNumber = 10;
		bombNumber = (int)((ROWS * COLS)* .2);
		checker = new Checker();
		loss = false;
		win = false;
		randomizeBombs();
		setValue();


	}

	public void paintComponent(Graphics g) {
		//super.paintComponent(g);

		for (int x = 0; x < ROWS; x++) {
			for(int y = 0; y < COLS; y++) {
				if (loss == false) { // hasn't lost yet
					if (cells[x][y].getRevealed() == true) {
						if (cells[x][y].getValue() == 0) { // revealed cells that equal 0
							cells[x][y].setSelected(true);
						}
						else {//revealed cells
							cells[x][y].setText(Integer.toString(cells[x][y].getValue()));
							cells[x][y].setSelected(true);
						}
					}
					else {
						if (cells[x][y].getMarked() == true) { //marked cells
							cells[x][y].setIcon(flag);
						}
						else {
							cells[x][y].setIcon(null);
						}
					}

				}
				else { //they lost
					if (cells[x][y].getLoss() == false) { // if the cell was not the one that caused the loss
						if (cells[x][y].getBombable() == true) {
							//cells[x][y].setMargin(new Insets(1, 1, 1, 1));
							cells[x][y].setIcon(bomb);


						}
						else {
							if (cells[x][y].getValue() == 0) { //printing zeros all nice and pretty								
								cells[x][y].setSelected(true);


							}
							else { // printing everything else

								cells[x][y].setSelected(true);

								cells[x][y].setText(Integer.toString(cells[x][y].getValue()));
							}
						}
					}
					else { // the bomb that caused the loss
						cells[x][y].setIcon(bombLost);
					}
				}

			}
		}

	}
	public void revealZeroes(int x, int y) { // checks if the ones around the cell that == 0 are also zero and reveals them

		if (y + 1 < cells.length) {
			cells[x][y+1].setRevealed();
			if(cells[x][y+1].getValue() == 0 && cells[x][y+1].getZeroChecked() == false) {
				cells[x][y+1].setZeroChecked();
				revealZeroes(x, y + 1);
			}
		}
		if (y > 0) {
			cells[x][y-1].setRevealed();
			if(cells[x][y-1].getValue() == 0 && cells[x][y-1].getZeroChecked() == false) {
				cells[x][y-1].setZeroChecked();
				revealZeroes(x, y - 1);
			}
		}
		if (x + 1 < cells.length) {
			cells[x + 1][y].setRevealed();
			if(cells[x+1][y].getValue() == 0 && cells[x + 1][y].getZeroChecked() == false) {
				cells[x+1][y].setZeroChecked();
				revealZeroes(x + 1, y);
			}
		}	
		if (x > 0) {
			cells[x - 1][y].setRevealed();
			if(cells[x-1][y].getValue() == 0 && cells[x - 1][y].getZeroChecked() == false) {
				cells[x-1][y].setZeroChecked();
				revealZeroes(x - 1, y);
			}
		}
		if (x + 1 < cells.length && y + 1 < cells.length){
			cells[x + 1][y + 1].setRevealed();
			if(cells[x+1][y+1].getValue() == 0 && cells[x + 1][y + 1].getZeroChecked() == false) {
				cells[x+1][y + 1].setZeroChecked();
				revealZeroes(x + 1, y + 1);
			}
		}
		if(x + 1 < cells.length && y > 0) {
			cells[x + 1][y - 1].setRevealed();
			if(cells[x+1][y-1].getValue() == 0 && cells[x + 1][y - 1].getZeroChecked() == false) {
				cells[x+1][y - 1].setZeroChecked();
				revealZeroes(x + 1, y - 1);
			}
		}
		if (x > 0 && y + 1 < cells.length) {
			cells[x - 1][y + 1].setRevealed();
			if(cells[x-1][y+1].getValue() == 0 && cells[x - 1][y + 1].getZeroChecked() == false) {
				cells[x-1][y + 1].setZeroChecked();
				revealZeroes(x - 1, y + 1);
			}
		}
		if (x > 0 && y > 0) {
			cells[x - 1][y - 1].setRevealed();
			if(cells[x-1][y-1].getValue() == 0 && cells[x - 1][y - 1].getZeroChecked() == false) {
				cells[x-1][y-1].setZeroChecked();
				revealZeroes(x - 1, y - 1);
			}
		}
		this.repaint();

	}


	public void setValue() { //sets the numbers in the board --> different class
		checker.set(cells);
	}

	public void setBoardAgain(int x, int y) { // makes sure that the first cell clicked is a 0
		while(cells[x][y].getValue() != 0 || cells[x][y].getBombable() == true) {
			randomizeBombs();
			setValue();
		}
	}

	public void randomizeBombs() { // randomizes where the bombs are placed
		for (int x = 0; x < ROWS; x++) { //resets the cells(just in case)
			for(int y = 0; y < COLS; y++) {
				cells[x][y].setNotABomb();
			}
		}

		for(int i =  0; i < bombNumber; i++) { //randomizes the bombs
			cells[(int)(Math.random()*ROWS)][(int)(Math.random()*COLS)].setBombable();
		}
	}


	public void revealCell(int x, int y) { //reveals the cells
		cells[x][y].setRevealed();
		if (cells[x][y].getValue() == 0) { 
			revealZeroes(x,y);
		}
	}

	public void markCell(int x, int y) {
		cells[x][y].setMarked();
	}
	public void unmarkCell(int x, int y) { 
		cells[x][y].setMarkedFalse();
	}
	public boolean getFirst() {
		return first;
	}

	public void setFirst(boolean first) {
		this.first = first;
	}

	public boolean checkWin() { // checks for a win and returns
		if (checkUnrevealed() == checker.bombCounter(cells)) {
			win = true;
		}
		return win;
	}

	public void setLoss(boolean loss) {
		this.loss = loss;
	}
	public boolean checkLoss() {
		return loss;
	}

	public int checkUnrevealed() { // gets the number of not revealed cells (used to check for a win)
		int value = 0;
		for (int x = 0; x < ROWS; x++) {
			for(int y = 0; y < COLS; y++) {
				if (cells[x][y].getRevealed() == false) {
					value ++;
				}
			}
		}

		return value;
	}

	public int getBombCount() {
		int a = checker.bombCounter(cells);
		for (int x = 0; x < ROWS; x++) {
			for(int y = 0; y < COLS; y++) {
				if (cells[x][y].getMarked() == true) {
					a--;
				}
			}
		}
		return a;
	}


	//Reset EVERYTHING
	public void reset() {
		
		for (int x = 0; x < ROWS; x++) {
			for(int y = 0; y < COLS; y++) {
				cells[x][y].reset();
				cells[x][y].setText("");
				cells[x][y].setSelected(false);
			}
		}

		first = false;
		loss = false;
		win = false;
		
		randomizeBombs();
		setValue();
	}
	//SMILEY
	public void setNormal() {
		smile.setIcon(normal);
	}

	public void setShock() {
		smile.setIcon(shock);
	}

	public void setWinner() {
		smile.setIcon(winner);

	}

	public void setLoser() {
		smile.setIcon(loser);

	}


}
