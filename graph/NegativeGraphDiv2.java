// This problem is interesting that you need to limit the size of PriorityQueue.
// Control this by adjusting QueueNodeComparator to calculate charges == 0, then
// charges == 1, ... If not, the queue will become very big and cause out of time
// error.

import java.util.*;

public class NegativeGraphDiv2 {
	class Edge {
		int w;
		int to;
		
		Edge(int w, int to) {
			this.w = w;
			this.to = to;
		}
	}
	
	class QueueNode {
		int cost;
		int node;
		int charges;
		QueueNode(int cost, int node, int charges) {
			this.cost = cost;
			this.node = node;
			this.charges = charges;
		}
	}
	
	class QueueNodeComparator implements Comparator<QueueNode> {
		public int compare(QueueNode n1, QueueNode n2) {
			if (n1.charges != n2.charges) {
				return n1.charges - n2.charges;
			}
			return n1.cost - n2.cost;
		}
	}

	public long findMin(int N, int[] s, int[] t, int[] weight, int charges) {
		// 1. build the neighbor matrix.
		ArrayList<ArrayList<Edge>> neighbors = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < N; ++i) {
			neighbors.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < s.length; ++i) {
			neighbors.get(s[i] - 1).add(new Edge(weight[i], t[i] - 1));
		}
		// 2. build dp matrix.
		// dp[i][j] is the min cost reaching node i with j charges used.
		int[][] dp = new int[N][charges + 1];
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j <= charges; ++j) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;
		PriorityQueue<QueueNode> queue = new PriorityQueue<QueueNode>(new QueueNodeComparator());
		queue.add(new QueueNode(0, 0, 0));
		while (!queue.isEmpty()) {
			QueueNode node = queue.poll();
			if (dp[node.node][node.charges] < node.cost) {
				continue;
			}
			for (Edge edge : neighbors.get(node.node)) {
				int next = edge.to;
				int cost = node.cost + edge.w;
				if (dp[next][node.charges] > cost) {
					dp[next][node.charges] = cost;
					queue.add(new QueueNode(cost, next, node.charges));
				}
				if (node.charges < charges) {
					cost = node.cost - edge.w;
					if (dp[next][node.charges + 1] > cost) {
						dp[next][node.charges + 1] = cost;
						queue.add(new QueueNode(cost, next, node.charges + 1));
					}
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i <= charges; ++i) {
			result = Math.min(result, dp[N - 1][i]);
		}
		return result;
	}
}
