import java.util.*;

public class ArcadeManao {

	public int shortestLadder(String[] level, int coinRow, int coinColumn) {
		int row = level.length;
		int col = level[0].length();
		int[][] pos_to_node_map = new int[row][col];
		int node_count = 0;
		// on the groud are all node 0.
		node_count++;
		for (int r = row - 2; r >= 0; --r) {
			for (int c = 0; c < col; ++c) {
				if (level[r].charAt(c) == '.') {
					pos_to_node_map[r][c] = -1;
				} else {
					pos_to_node_map[r][c] = node_count;
					if (c + 1 == col || level[r].charAt(c + 1) == '.') {
						node_count++;
					}
				}
			}
		}
		boolean[][] adjMatrix = new boolean[node_count][node_count];
		int target_node = pos_to_node_map[coinRow - 1][coinColumn - 1];
		boolean[] connect = new boolean[node_count];
		connect[0] = true;
		
		// Test connection and increase ladder length gradually.
		int ladder = 0;
		while (true) {
			updateConnect(adjMatrix, connect);
			if (connect[target_node]) {
				return ladder;
			}
			ladder++;
			for (int r = row - 1; r >= ladder; --r) {
				for (int c = 0; c < col; ++c) {
					int a = pos_to_node_map[r][c];
					int b = pos_to_node_map[r - ladder][c];
					if (a != -1 && b != -1) {
						adjMatrix[a][b] = adjMatrix[b][a] = true;
					}
				}
			}
		}
	}
	
	void updateConnect(boolean[][] adjMatrix, boolean[] reachable) {
		int n = adjMatrix.length;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		for (int i = 0; i < n; ++i) {
			if (reachable[i]) {
				queue.add(i);
			}
		}
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < n; ++i) {
				if (!reachable[i] && adjMatrix[cur][i]) {
					reachable[i] = true;
					queue.add(i);
				}
			}
		}
	}
}
