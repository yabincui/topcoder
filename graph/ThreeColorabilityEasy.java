public class ThreeColorabilityEasy {
	public String isColorable(String[] cells) {
		int H = cells.length;
		int W = cells[0].length();
		char[][] board = new char[H + 1][W + 1];
		board[0][0] = 'A';
		board[0][1] = 'B';
		for (int r = 0; r < H; ++r) {
			for (int c = 0; c < W; ++c) {
				// check cell[r][c].
				if (cells[r].charAt(c) == 'N') {
					if (board[r][c] != 0 && board[r + 1][c] != 0) {
						if (!markBoard(board, r, c, r + 1, c, r + 1, c + 1) ||
							!markBoard(board, r, c, r + 1, c + 1, r, c + 1)) {
							return "No";	
						}
					} else if (board[r][c] != 0 && board[r][c+1] != 0) {
						if (!markBoard(board, r, c, r, c + 1, r + 1, c + 1) ||
							!markBoard(board, r, c, r + 1, c + 1, r + 1, c)) {
							return "No";
						}
					} else {
						throw new RuntimeException();
					}
				} else {
					if (board[r][c] != 0 && board[r + 1][c] != 0) {
						if (!markBoard(board, r, c, r + 1, c, r, c + 1) ||
							!markBoard(board, r, c + 1, r + 1, c, r + 1, c + 1)) {
							return "No";
						}
					} else if (board[r][c] != 0 && board[r][c + 1] != 0) {
						if (!markBoard(board, r, c, r, c + 1, r + 1, c) ||
							!markBoard(board, r, c + 1, r + 1, c, r + 1, c + 1)) {
							return "No";
						}
					} else {
						throw new RuntimeException();
					}
				}
			}
		}
		return "Yes";
	}
	
	private boolean markBoard(char[][] board, int r1, int c1, int r2, int c2, int r3, int c3) {
		char a = board[r1][c1];
		char b = board[r2][c2];
		char c = '0';
		if (a == b) {
			//System.out.printf("board[%d][%d] = %c, board[%d][%d] = %c\n", r1, c1, a, r2, c2, b);
			return false;
		}
		if ((a == 'A' && b == 'B') || (a == 'B' && b == 'A')) {
			c = 'C';
		} else if ((a == 'A' && b == 'C') || (a == 'C' && b == 'A')) {
			c = 'B';
		} else if ((a == 'B' && b == 'C') || (a == 'C' && b == 'B')) {
			c = 'A';
		}
		if (board[r3][c3] != 0 && board[r3][c3] != c) {
			//System.out.printf("board[%d][%d] = %c\n", r3, c3, board[r3][c3]);
			return false;
		}
		board[r3][c3] = c;
		//System.out.printf("board[%d][%d] = %c\n", r3, c3, c);
		return true;
	}
}
