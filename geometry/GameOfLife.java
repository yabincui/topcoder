public class GameOfLife {
	public int alive(String[] start, String rules, int generations) {
		int rows = start.length;
		int cols = start[0].length();
		boolean[][] curGen = new boolean[rows][cols];
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				if (start[i].charAt(j) == 'X') {
					curGen[i][j] = true;
				}
			}
		}
		boolean[][] nextGen = new boolean[rows][cols];
		for (int gen = 1; gen <= generations; ++gen) {
			for (int i = 0; i < rows; ++i) {
				for (int j = 0; j < cols; ++j) {
					int neighbor = 0;
					for (int ni = i - 1; ni <= i + 1; ++ni) {
						for (int nj = j - 1; nj <= j + 1; ++nj) {
							if (i == ni && j == nj) {
								continue;
							}
							int ti = (ni + rows) % rows;
							int tj = (nj + cols) % cols;
							if (!curGen[ti][tj]) {
								continue;
							}
							neighbor++;
						}
					}
					char action = rules.charAt(neighbor);
					if (action == 'D') {
						nextGen[i][j] = false;
					} else if (action == 'S') {
						nextGen[i][j] = curGen[i][j];
					} else {
						nextGen[i][j] = true;
					}
				}
			}
			boolean[][] tmp = curGen;
			curGen = nextGen;
			nextGen = tmp;
		}
		int result = 0;
		for (int i = 0; i < rows; ++i) {
			for (int j = 0; j < cols; ++j) {
				result += curGen[i][j] ? 1 : 0;
			}
		}
		return result;
	}
}
