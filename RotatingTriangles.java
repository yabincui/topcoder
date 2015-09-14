public class RotatingTriangles {
	// Brute-force.
	public int count(String[] grid) {
		int rows = grid.length;
		int cols = grid[0].length();
		int[][] map = new int[rows + 2][cols + 2];
		for (int i = 1; i <= rows; ++i) {
			for (int j = 1; j <= cols; ++j) {
				if (grid[i - 1].charAt(j - 1) == '.') {
					map[i][j] = 0;
				} else if (grid[i - 1].charAt(j - 1) == '#') {
					map[i][j] = 2;
				} else if (grid[i - 1].charAt(j - 1) == '/') {
					map[i][j] = 1;
				}
			}
		}
		
		// Eight types of triangles.
		int count1 = countType1(map, rows, cols);
		int count2 = countType2(map, rows, cols);
		int result = count1 + count2;
		return result;
	}
	
	/*               ---      ---
	 *    /|  |\     \ |      | /
	 *   /_|  |_\     \|      |/
	 *   
	 */
	int countType1(int[][] map, int rows, int cols) {
		int count = 0;
		int[] dR = new int[]{1, 1, -1, -1};
		int[] dLeftC = new int[]{-1, 0, -1, 0};
		int[] dRightC = new int[]{0, 1, 0, 1};
		boolean[] leftCValid = new boolean[]{true, false, true, false};
		for (int startR = 1; startR <= rows; ++startR) {
			for (int startC = 1; startC <= cols; ++startC) {
				for (int k = 0; k < 4; ++k) {
					int curR = startR;
					int leftC = 0;
					int rightC = 0;
					if (leftCValid[k]) {
						leftC = startC;
						rightC = startC + 1;
					} else {
						leftC = startC - 1;
						rightC = startC;
					}
					while (true) {
						if (leftCValid[k]) {
							if (map[curR][leftC] != 1 || map[curR][rightC] == 2) {
								break;
							}
						} else {
							if (map[curR][leftC] == 2 || map[curR][rightC] != 1) {
								break;
							}
						}
						if (!isBlackLine(map, curR, leftC + 1, curR, rightC - 1)) {
							break;
						}
						if (leftCValid[k]) {
							if (isWhiteLine(map, curR + dR[k], leftC, curR + dR[k], rightC - 1)) {
								count++;
							}
						} else {
							if (isWhiteLine(map, curR + dR[k], leftC + 1, curR + dR[k], rightC)) {
								count++;
							}
						}
						curR += dR[k];
						leftC += dLeftC[k];
						rightC += dRightC[k];
						if (curR <= 0 || curR > rows || (leftCValid[k] && leftC == 0) ||
								(!leftCValid[k] && rightC > cols)) {
							break;
						}
					}
				}
			}
		}
		return count;
	}
	
	int countType2(int[][] map, int rows, int cols) {
		int count1 = countBottomInRow(map, rows, cols);
		int count2 = countBottomInColumn(map, rows, cols);
		return count1 + count2;
	}
	
	/*
	 *    ---     /\
	 *    \ /    /__\
	 */
	int countBottomInRow(int[][] map, int rows, int cols) {
		int count = 0;
		int[] dR = new int[]{1, -1};
		for (int startR = 1; startR <= rows; ++startR) {
			for (int startC = 1; startC <= cols; ++startC) {
				for (int k = 0; k < 2; ++k) {
					int curR = startR;
					int leftC = startC;
					int rightC = startC + 1;
					while (true) {
						if (map[curR][leftC] != 1 || map[curR][rightC] != 1) {
							break;
						}
						if (!isBlackLine(map, curR, leftC + 1, curR, rightC - 1)) {
							break;
						}
						if (isWhiteLine(map, curR + dR[k], leftC, curR + dR[k], rightC)) {
							count++;
						}
						curR += dR[k];
						leftC--;
						rightC++;
						if (curR <= 0 || curR > rows || leftC <= 0 || rightC > cols) {
							break;
						}
					}
				}
			}
		}
		return count;
	}
	
	/*
	 *    /|  |\
	 *    \|  |/
	 */
	int countBottomInColumn(int[][] map, int rows, int cols) {
		int count = 0;
		int[] dC = new int[]{1, -1};
		for (int startR = 1; startR <= rows; ++startR) {
			for (int startC = 1; startC <= cols; ++startC) {
				for (int k = 0; k < 2; ++k) {
					int curC = startC;
					int upR = startR;
					int downR = startR + 1;
					while (true) {
						if (map[upR][curC] != 1 || map[downR][curC] != 1) {
							break;
						}
						if (!isBlackLine(map, upR + 1, curC, downR - 1, curC)) {
							break;
						}
						if (isWhiteLine(map, upR, curC + dC[k], downR, curC + dC[k])) {
							count++;
						}
						upR--;
						downR++;
						curC += dC[k];
						if (curC <= 0 || curC > cols || upR <= 0 || downR > rows) {
							break;
						}
					}
				}
			}
		}
		return count;
	}
	
	// The time complexity can be lowered by pre-calculating isWhiteLine and isBlackLine.
	boolean isWhiteLine(int[][] map, int r1, int c1, int r2, int c2) {
		for (int r = r1; r <= r2; ++r) {
			for (int c = c1; c <= c2; ++c) {
				if (map[r][c] == 2) {
					return false;
				}
			}
		}
		return true;
	}
	
	boolean isBlackLine(int[][] map, int r1, int c1, int r2, int c2) {
		for (int r = r1; r <= r2; ++r) {
			for (int c = c1; c <= c2; ++c) {
				if (map[r][c] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}