import java.util.*;

public class ContestSchedule {
	class Node {
		int start;
		int end;
		int probability;
		Node(int start, int end, int probability) {
			this.start = start;
			this.end = end;
			this.probability = probability;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.end - n2.end;
		}
	}
	
	public double expectedWinnings(String[] contests) {
		int n = contests.length;
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = contests[i].split(" ");
			nodes[i] = new Node(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]),
								Integer.parseInt(strs[2]));
		}
		Arrays.sort(nodes, new NodeComparator());
		int[] dp = new int[n];
		for (int i = 0; i < n; ++i) {
			int curPro = nodes[i].probability;
			int prevEndLimit = nodes[i].start;
			dp[i] = curPro;
			for (int j = i-1; j >= 0; --j) {
				if (nodes[j].end <= prevEndLimit && dp[j] + curPro > dp[i]) {
					dp[i] = dp[j] + curPro;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			result = Math.max(result, dp[i]);
		}
		return result / 100.0;
	}
}
