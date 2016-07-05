public class TopView {
	public String findOrder(String[] grid) {
		// valid[i] is true if i appears in the grid.
		boolean[] valid = new boolean[128];
		int[] left = new int[128];
		int[] right = new int[128];
		int[] top = new int[128];
		int[] bottom = new int[128];
		// depend[i][j] is true if j depends on i.
		boolean[][] depend = new boolean[128][128];
		
		for (int i = 0; i < 128; ++i) {
			left[i] = Integer.MAX_VALUE;
			right[i] = 0;
			top[i] = Integer.MAX_VALUE;
			bottom[i] = 0;
		}
		
		for (int i = 0; i < grid.length; ++i) {
			for (int j = 0; j < grid[0].length(); ++j) {
				char c = grid[i].charAt(j);
				if (c == '.') {
					continue;
				}
				valid[c] = true;
				left[c] = Math.min(left[c], j);
				right[c] = Math.max(right[c], j);
				top[c] = Math.min(top[c], i);
				bottom[c] = Math.max(bottom[c], i);
			}
		}
		for (int i = 0; i < 128; ++i) {
			if (valid[i]) {
				for (int r = top[i]; r <= bottom[i]; ++r) {
					for (int c = left[i]; c <= right[i]; ++c) {
						char ch = grid[r].charAt(c);
						if (ch == '.') {
							return "ERROR!";
						} else if (ch != i) {
							depend[i][ch] = true;
						}
					}
				}
			}
		}
		for (int k = 0; k < 128; ++k) {
			if (!valid[k]) continue;
			for (int i = 0; i < 128; ++i) {
				if (!valid[i] || i == k) continue;
				for (int j = 0; j < 128; ++j) {
					if (!valid[j] || j == k) continue;
					if (depend[i][k] && depend[k][j]) {
						depend[i][j] = true;
						if (i == j) {
							return "ERROR!";
						}
					}
				}
			}
		}
		int[] dependCount = new int[128];
		for (int i = 0; i < 128; ++i) {
			for (int j = 0; j < 128; ++j) {
				if (!valid[i] || !valid[j]) continue;
				if (depend[i][j]) {
					dependCount[j]++;
				}
			}
		}
		StringBuilder result = new StringBuilder();
		while (true) {
			int sel = -1;
			for (int i = 0; i < 128; ++i) {
				if (valid[i] && dependCount[i] == 0) {
					sel = i;
					break;
				}
			}
			if (sel == -1) {
				break;
			}
			result.append((char)sel);
			valid[sel] = false;
			for (int i = 0; i < 128; ++i) {
				if (depend[sel][i]) {
					dependCount[i]--;
				}
			}
		}
		return result.toString();
	}
}
