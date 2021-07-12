
public class Checker {

	public void set(Cell[][] board) {
		for (int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++) {

				int value = 0;

				if (y + 1 < board.length) {
					if(board[x][y+1].getBombable() == true) {
						value++;
					}
				}
				if (y > 0) {
					if(board[x][y-1].getBombable() == true) {
						value++;
					}
				}
				if (x + 1 < board.length) {
					if(board[x+1][y].getBombable() == true) {
						value++;
					}
				}	
				if (x > 0) {
					if(board[x-1][y].getBombable() == true) {
						value++;
					}
				}
				if (x + 1 < board.length && y + 1 < board.length){
					if(board[x+1][y+1].getBombable() == true) {
						value++;
					}
				}
				if(x + 1 < board.length && y > 0) {
					if(board[x+1][y-1].getBombable() == true) {
						value++;
					}
				}
				if (x > 0 && y + 1 < board.length) {
					if(board[x-1][y+1].getBombable() == true) {
						value++;
					}
				}
				if (x > 0 && y > 0) {
					if(board[x-1][y-1].getBombable() == true) {
						value++;
					}
				}

				board[x][y].setValue(value);
			}
		}

	}

	public int bombCounter(Cell[][] board) {
		int counter = 0;
		for (int x = 0; x < board.length; x++) {
			for(int y = 0; y < board.length; y++) {
				if (board[x][y].getBombable() == true && board[x][y].getRevealed() == false) {
					counter ++;
				}

			}
		}
		return counter;
	}
}
