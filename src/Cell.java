import javax.swing.JButton;

public class Cell extends JButton{

	private boolean revealed;
	private boolean bombable;
	private boolean marked;
	private boolean bombed;
	private int value;
	private boolean checked;
	
	public Cell(String x){
		super(x);
		revealed = false;
		bombable = false;
		bombed = false;
		marked = false;
		checked = false;
		value = 0;
	}
	
	public void setRevealed() {
		revealed = true;
	}
	
	
	public void setMarked() {
		marked = true;
	}
	
	public void setMarkedFalse() {
		marked = false;
	}
	
	public void setBombable() {
		bombable = true;
	}
	
	public void setNotABomb() {
		bombable = false;
	}
	
	public void setDeath() {
		bombed = true;
	}
	
	//getters n' setters
	public int getValue() {
		return value;
	}
	
	public void setValue(int x) {
		value = x;
	}
	
	public void setZeroChecked() {
		checked = true;
	}
	
	public boolean getRevealed() {
		return revealed;
	}
	
	public boolean getBombable() {
		return bombable;
	}
	
	public boolean getLoss() {
		return bombed;
	}
	
	public boolean getMarked() {
		return marked;
	}
	
	public boolean getZeroChecked() {
		return checked;
	}
	
	public void reset() {
		revealed = false;
		bombable = false;
		bombed = false;
		marked = false;
		checked = false;
		value = 0;
	}

	
}
