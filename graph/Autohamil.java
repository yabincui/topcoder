import java.util.*;

public class Autohamil {

	public String check(int[] z0, int[] z1) {
		int n = z0.length;
		boolean[][] connect = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			connect[i][i] = true;
		}
		for (int i = 0; i < n; ++i) {
			connect[i][z0[i]] = true;
			connect[i][z1[i]] = true;
		}
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (k == i || k == j || i == j) {
						continue;
					}
					if (connect[i][k] && connect[k][j]) {
						connect[i][j] = true;
					}
				}
			}
		}
		boolean[] visited = new boolean[n];
		visited[0] = true;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(0);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int n0 = z0[cur];
			int n1 = z1[cur];
			if (!connect[n0][n1] && !connect[n1][n0]) {
				//System.out.printf("!connect cur = %d\n", cur);
				return "Does not exist";
			}
			boolean all_connect = false;
			if (connect[n0][cur] || connect[n1][cur]) {
				all_connect = true;
			}
			if (all_connect || connect[n0][n1]) {
				if (!visited[n0]) {
					visited[n0] = true;
					queue.add(n0);
				}
			}
			if (all_connect || connect[n1][n0]) {
				if (!visited[n1]) {
					visited[n1] = true;
					queue.add(n1);
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			if (!visited[i]) {
				return "Does not exist";
			}
		}
		return "Exists";
	}
}
