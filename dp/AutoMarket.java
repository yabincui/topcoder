import java.util.*;

public class AutoMarket {
	class Node {
		int cost;
		int feature;
		int fixTime;
		Node(int cost, int feature, int fixTime) {
			this.cost = cost;
			this.feature = feature;
			this.fixTime = fixTime;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.cost - n2.cost;
		}
	}
	
	public int maxSet(int[] cost, int[] features, int[] fixTimes) {
		int n = cost.length;
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			nodes[i] = new Node(cost[i], features[i], fixTimes[i]);
		}
		Arrays.sort(nodes, new NodeComparator());
		// dp[i] is the max length can acheive if the maxSet ended at pos i.
		int[] dp = new int[n];
		for (int i = 0; i < n; ++i) {
			dp[i] = 1;
		}
		for (int i = 1; i < n; ++i) {
			for (int j = 0; j < i; ++j) {
				if (nodes[j].cost < nodes[i].cost &&
					nodes[j].feature > nodes[i].feature &&
					nodes[j].fixTime < nodes[i].fixTime) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
