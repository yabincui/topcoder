import java.util.*;

public class CollectingRiders {
	public int minimalMoves(String[] board) {
		int rows = board.length;
		int cols = board[0].length();
		int n = rows * cols;
		boolean[][] connect = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				int r1 = i / cols;
				int c1 = i % cols;
				int r2 = j / cols;
				int c2 = j % cols;
				int d1 = Math.abs(r1 - r2);
				int d2 = Math.abs(c1 - c2);
				if ((d1 == 1 && d2 == 2) || (d1 == 2 && d2 == 1)) {
					connect[i][j] = connect[j][i] = true;
				}
			}
		}
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				dist[i][j] = dist[j][i] = (connect[i][j] ? 1 : -1);
			}
		}
		for (int step = 0; step < n; ++step) {
			for (int k = 0; k < n; ++k) {
				for (int i = 0; i < n; ++i) {
					if (i == k || dist[i][k] == -1) {
						continue;
					}
					for (int j = i + 1; j < n; ++j) {
						if (j == k || dist[k][j] == -1) {
							continue;
						}
						if (dist[i][j] == -1 || dist[i][j] > dist[i][k] + dist[k][j]) {
							dist[i][j] = dist[j][i] = dist[i][k] + dist[k][j];
						}
					}
				}
			}
		}
		HashMap<Integer, Integer> rider_map = new HashMap<Integer, Integer>();
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				if (board[r].charAt(c) != '.') {
					rider_map.put(r * cols + c, board[r].charAt(c) - '0');
				}
			}
		}
		// loop all targets from 0 - n - 1
		int result = -1;
		for (int target = 0; target < n; ++target) {
			int cost = 0;
			for (int rider : rider_map.keySet()) {
				int d = dist[rider][target];
				if (d == -1) {
					cost = -1;
					break;
				}
				int jump = rider_map.get(rider);
				cost += (d + jump - 1) / jump;
			}
			if (cost != -1) {
				if (result == -1 || result > cost) {
					result = cost;
				}
			}
		}
		return result;
	}
}
