public class BoardEscapeDiv2 {

	public String findWinner(String[] s, int k) {
		int startR = -1;
		int startC = -1;
		int rows = s.length;
		int cols = s[0].length();
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (s[i].charAt(j) == 'T') {
					startR = i;
					startC = j;
				}
			}
		}
		if (!canMove(s, startR, startC)) {
			return "Bob";
		}
		if (nextToExit(s, startR, startC)) {
			return "Alice";
		}
		if (k % 2 == 0) {
			// Bob wants to keep the same place.
			return "Bob";
		}
		// Alice wants to keep the same place.
		boolean hasMoveNotNearExit = false;
		
		for (int dir = 0; dir < 4; ++dir) {
			int[] pos = move(s, startR, startC, dir);
			if (pos == null) {
				continue;
			}
			if (!nextToExit(s, pos[0], pos[1])) {
				hasMoveNotNearExit = true;
			}
		}
		if (k == 1 || hasMoveNotNearExit) {
			return "Alice";
		}
		return "Bob";
	}
	
	boolean canMove(String[] s, int r, int c) {
		for (int dir = 0; dir < 4; ++dir) {
			int[] pos = move(s, r, c, dir);
			if (pos != null) {
				return true;
			}
		}
		return false;
	}
	
	boolean nextToExit(String[] s, int r, int c) {
		for (int dir = 0; dir < 4; ++dir) {
			int[] pos = move(s, r, c, dir);
			if (pos != null && s[pos[0]].charAt(pos[1]) == 'E') {
				return true;
			}
		}
		return false;
	}
	
	int[] move(String[] s, int r, int c, int direction) {
		final int[] dr = new int[]{0, 0, -1, 1};
		final int[] dc = new int[]{-1, 1, 0, 0};
		r += dr[direction];
		c += dc[direction];
		if (r < 0 || r >= s.length || c < 0 || c >= s[0].length() || s[r].charAt(c) == '#') {
			return null;
		}
		return new int[]{r, c};
	}
}
