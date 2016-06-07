import java.util.*;

public class LexStringWriter {
	class Node {
		char c;
		int pos;
		Node(char c, int pos) {
			this.c = c;
			this.pos = pos;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			if (n1.c != n2.c) {
				return n1.c - n2.c;
			}
			return n1.pos - n2.pos;
		}
	}
	
	class State {
		int pos;
		int buttons;
		State(int pos, int buttons) {
			this.pos = pos;
			this.buttons = buttons;
		}
	}
	
	public int minMoves(String s) {
		int n = s.length();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			nodes[i] = new Node(s.charAt(i), i);
		}
		Arrays.sort(nodes, new NodeComparator());
		State[] dp = new State[1];
		dp[0] = new State(0, 0);
		for (int start = 0; start < n; ) {
			int end = start;
			while (end + 1 < n && nodes[end+1].c == nodes[start].c) {
				end++;
			}
			int left = nodes[start].pos;
			int right = nodes[end].pos;
			int count = end - start + 1;
			State[] nextDp = new State[count];
			for (int i = 0; i < count; ++i) {
				int to = nodes[start + i].pos;
				int minCost = Integer.MAX_VALUE;
				for (int j = 0; j < dp.length; ++j) {
					int from = dp[j].pos;
					int buttons = dp[j].buttons;
					int cost = buttons + count;
					if (from < to) {
						if (left < from) {
							cost += (from - left) * 2 + (right - from) + (right - to);
						} else {
							cost += (right - from) + (right - to);
						}
					} else {
						if (right < from) {
							cost += (from - left) + (to - left);
						} else {
							cost += (right - from) * 2 + (from - left) + (to - left);
						}
					}
					minCost = Math.min(minCost, cost);
				}
				nextDp[i] = new State(to, minCost);
			}
			dp = nextDp;
			start = end + 1;
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < dp.length; ++i) {
			result = Math.min(result, dp[i].buttons);
		}
		return result;
	}
}
