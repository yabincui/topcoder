import java.util.*;

public class GameOnABoard {
	public int optimalChoiceTimeout(String[] cost) {
		int rows = cost.length;
		int cols = cost[0].length();
		int n2 = rows * cols;
		// dp[i][j] is the min cost of path from i to j.
		int[][] dp = new int[n2][n2];
		for (int i = 0; i < n2; ++i) {
			for (int j = 0; j < n2; ++j) {
				if (i == j) {
					int r = i / cols;
					int c = i % cols;
					dp[i][j] = (cost[r].charAt(c) - '0');
				} else {
					dp[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		for (int i = 0; i < n2; ++i) {
			int r = i / cols;
			int c = i % cols;
			if (r + 1 < rows) {
				int j = (r + 1) * cols + c;
				dp[i][j] = dp[j][i] = Math.min(dp[i][j], dp[i][i] + dp[j][j]);
			}
			if (c + 1 < cols) {
				int j = r * cols + (c + 1);
				dp[i][j] = dp[j][i] = Math.min(dp[i][j], dp[i][i] + dp[j][j]);
			}
		}
		// timeout, in this graph, O(nodes) ~= O(edges), so we should use
		// single shortest path instead of flood.
		for (int k = 0; k < n2; ++k) {
			for (int i = 0; i < n2; ++i) {
				if (i == k) continue;
				for (int j = i + 1; j < n2; ++j) {
					if (j == k) continue;
					if (dp[i][k] != Integer.MAX_VALUE && dp[k][j] != Integer.MAX_VALUE) {
						dp[i][j] = dp[j][i] = Math.min(dp[i][j], dp[i][k] + dp[k][j] - dp[k][k]);
					}
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < n2; ++i) {
			int maxValue = 0;
			for (int j = 0; j < n2; ++j) {
				maxValue = Math.max(maxValue, dp[i][j]);
			}
			result = Math.min(result, maxValue);
		}
		return result;
	}
	
	class QueueNode {
		int d;
		int node;
		QueueNode(int d, int node) {
			this.d = d;
			this.node = node;
		}
	}
	
	class QueueNodeComparator implements Comparator<QueueNode> {
		public int compare(QueueNode n1, QueueNode n2) {
			return n1.d - n2.d;
		}
	}
	
	public int optimalChoice(String[] cost) {
		int rows = cost.length;
		int cols = cost[0].length();
		int n2 = rows * cols;
		int[] dist = new int[n2];
		int[] dr = new int[]{-1, 1, 0, 0};
		int[] dc = new int[]{0, 0, -1, 1};
		int result = Integer.MAX_VALUE;
		for (int start = 0; start < n2; ++start) {
			for (int i = 0; i < n2; ++i) {
				dist[i] = Integer.MAX_VALUE;
			}
			dist[start] = cost[start / cols].charAt(start % cols) - '0';
			PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(new QueueNodeComparator());
			queue.add(new QueueNode(dist[start], start));
			while (!queue.isEmpty()) {
				QueueNode cur = queue.poll();
				if (dist[cur.node] < cur.d) continue;
				int r = cur.node / cols;
				int c = cur.node % cols;
				for (int i = 0; i < dr.length; ++i) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr < 0 || nr >= rows || nc < 0 || nc >= cols) {
						continue;
					}
					int next = nr * cols + nc;
					int nextCost = cur.d + cost[nr].charAt(nc) - '0';
					if (dist[next] > nextCost) {
						dist[next] = nextCost;
						queue.add(new QueueNode(nextCost, next));
					}
				}
			}
			int maxValue = 0;
			for (int i = 0; i < n2; ++i) {
				//System.out.printf("dist[%d][%d] = %d\n", start, i, dist[i]);
				maxValue = Math.max(maxValue, dist[i]);
			}
			result = Math.min(result, maxValue);
		}
		return result;
	}
}
