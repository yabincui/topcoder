public class SquareCode {
	public String[] completeIt(String[] grille) {
		int n = grille.length;
		char[][] matrix = new char[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				matrix[i][j] = grille[i].charAt(j);
			}
		}
		// check if valid.
		boolean valid = true;
		for (int i = 0; i < n && valid; ++i) {
			for (int j = 0; j < n && valid; ++j) {
				if (matrix[i][j] == '.') {
					int ti = i;
					int tj = j;
					for (int t = 1; t < 4; ++t) {
						int ni = tj;
						int nj = n - 1 - ti;
						if (matrix[ni][nj] == '.') {
							valid = false;
							break;
						}
						ti = ni;
						tj = nj;
					}
				}
			}
		}
		if (!valid) {
			//System.out.printf("empty string\n");
			return new String[0];
		}
		// add new mark.
		for (int j = 0; j < n/2; ++j) {
			for (int i = 0; i < n/2; ++i) {
				if (!hasMark(matrix, n, i, j)) {
					matrix[i][j] = '.';
				}
			}
		}
		String[] result = new String[n];
		for (int i = 0; i < n; ++i) {
			result[i] = String.valueOf(matrix[i]);
			//System.out.printf("%s\n", result[i]);
		}
		return result;
	}
	
	boolean hasMark(char[][] matrix, int n, int i, int j) {
		for (int t = 0; t < 4; ++t) {
			if (matrix[i][j] == '.') {
				return true;
			}
			int ni = j;
			int nj = n - 1 - i;
			i = ni;
			j = nj;
		}
		return false;
	}
}
