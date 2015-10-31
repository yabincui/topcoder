import java.util.Arrays;
import java.util.Comparator;

public class FastPostman {
	class Node {
		int addr;
		int maxTime;
		
		Node(int addr, int maxTime) {
			this.addr = addr;
			this.maxTime = maxTime;
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.addr - n2.addr;
		}
	}

	public int minTime(int[] address, int[] maxTime, int initialPos) {
		int n = address.length;
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			nodes[i] = new Node(address[i], maxTime[i]);
		}
		Arrays.sort(nodes, new NodeComparator());
		if (initialPos <= nodes[0].addr) {
			for (int i = 0; i < n; ++i) {
				if (nodes[i].addr - initialPos > nodes[i].maxTime) {
					return -1;
				}
			}
			return nodes[n-1].addr - initialPos;
		}
		if (initialPos >= nodes[n-1].addr) {
			for (int i = n-1; i >= 0; --i) {
				if (initialPos - nodes[i].addr > nodes[i].maxTime) {
					return -1;
				}
			}
			return initialPos - nodes[0].addr;
		}
		int midPos = -1;
		for (int i = 0; i < n - 1; ++i) {
			if (nodes[i].addr <= initialPos && nodes[i+1].addr >= initialPos) {
				midPos = i;
				break;
			}
		}
		int leftCount = midPos + 1;
		int rightCount = n - midPos - 1;
		
		// dp[i][j][0] is the min time to send over mails in left[i]..right[j], ended at left[i].
		// dp[i][j][1] is the min time to send over mails in left[i]..right[j], ended at right[j].
		int[][][] dp = new int[leftCount + 1][rightCount + 1][2];
		for (int left = 1; left <= leftCount; ++left) {
			dp[left][0][0] = initialPos - nodes[midPos + 1 - left].addr;
			dp[left][0][1] = dp[left][0][0] * 2;
			if (dp[left][0][0] > nodes[midPos + 1 - left].maxTime) {
				return -1;
			}
		}
		for (int right = 1; right <= rightCount; ++right) {
			dp[0][right][1] = nodes[midPos + right].addr - initialPos;
			dp[0][right][0] = dp[0][right][1] * 2;
			if (dp[0][right][1] > nodes[midPos + right].maxTime) {
				return -1;
			}
		}
		for (int left = 1; left <= leftCount; ++left) {
			for (int right = 1; right <= rightCount; ++right) {
				// dp[left][right][0].
				int value = Integer.MAX_VALUE;
				if (dp[left-1][right][0] != Integer.MAX_VALUE) {
					value = Math.min(value, dp[left-1][right][0] + nodes[midPos+1-left+1].addr -
					                        nodes[midPos+1-left].addr);
				}
				if (dp[left-1][right][1] != Integer.MAX_VALUE) {
					value = Math.min(value, dp[left-1][right][1] + nodes[midPos+right].addr -
					                        nodes[midPos+1-left].addr);
				}
				dp[left][right][0] = (value > nodes[midPos+1-left].maxTime) ? Integer.MAX_VALUE : value;
				// dp[left][right][1].
				value = Integer.MAX_VALUE;
				if (dp[left][right-1][1] != Integer.MAX_VALUE) {
					value = Math.min(value, dp[left][right-1][1] + nodes[midPos+right].addr -
					                        nodes[midPos+right-1].addr);
				}
				if (dp[left][right-1][0] != Integer.MAX_VALUE) {
					value = Math.min(value, dp[left][right-1][0] + nodes[midPos+right].addr -
					                        nodes[midPos+1-left].addr);
				}
				dp[left][right][1] = (value > nodes[midPos+right].maxTime) ? Integer.MAX_VALUE : value;
			}
		}
		int result = Math.min(dp[leftCount][rightCount][0], dp[leftCount][rightCount][1]);
		return (result == Integer.MAX_VALUE) ? -1 : result;
	}
}