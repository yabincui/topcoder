public class MazeWanderingEasy {
	static final int[] dr = new int[]{-1, 1, 0, 0};
	static final int[] dc = new int[]{0, 0, -1, 1};

	public int decisions(String[] maze) {
		int n = maze.length;
		int m = maze[0].length();
		int start_r = -1;
		int start_c = -1;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				if (maze[i].charAt(j) == 'M') {
					start_r = i;
					start_c = j;
					break;
				}
			}
			if (start_r != -1) {
				break;
			}
		}
		int result = dfs(maze, start_r, start_c, -1, -1);
		return result;
	}
	
	int dfs(String[] maze, int r, int c, int prev_r, int prev_c) {
		if (maze[r].charAt(c) == '*') {
			return 0;
		}
		int choice_count = 0;
		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < maze.length && nc >= 0 && nc < maze[0].length() &&
				(maze[nr].charAt(nc) == '.' || maze[nr].charAt(nc) == '*') &&
				!(nr == prev_r && nc == prev_c)) {
				choice_count++;
			}
		}
		if (choice_count == 0) {
			return -1;
		}
		for (int i = 0; i < 4; ++i) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (nr >= 0 && nr < maze.length && nc >= 0 && nc < maze[0].length() &&
				(maze[nr].charAt(nc) == '.' || maze[nr].charAt(nc) == '*') &&
				!(nr == prev_r && nc == prev_c)) {
				int ret = dfs(maze, nr, nc, r, c);
				if (ret != -1) {
					if (choice_count > 1) {
						ret++;
					}
					return ret;
				}
			}
		}
		return -1;
	}
}
