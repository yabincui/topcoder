import java.util.*;

public class GetToTheTop {
	
	class Node {
		int y;
		ArrayList<Node> from;
		int selfSweet;
		boolean reachable;
		int sweetSum;
		
		Node(int y, int selfSweet) {
			this.y = y;
			this.selfSweet = selfSweet;
			this.reachable = false;
			this.sweetSum = 0;
			this.from = new ArrayList<Node>();
		}
		
		void addSweet(int sweet) {
			this.selfSweet += sweet;
		}
		
		void addFrom(Node fromNode) {
			this.from.add(fromNode);
		}
		
		void sumSweet() {
			boolean reachable = false;
			int maxPrev = 0;
			for (Node node : from) {
				if (!node.reachable) {
					continue;
				}
				reachable = true;
				maxPrev = Math.max(maxPrev, node.sweetSum);
			}
			if (reachable) {
				this.reachable = true;
				this.sweetSum = maxPrev + selfSweet;
			}
		}
	}
	
	class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			return n1.y - n2.y;
		}
	}
	
	
	public int collectSweets(int K, int[] sweets, int[] x, int[] y, int[] stairLength) {
		int n = sweets.length;
		// jumpMap[i][j] = true if John can jump from place i to place j.
		boolean[][] jumpMap = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int diffy = Math.abs(y[i] - y[j]);
				int diffx = 0;
				if (x[j] > x[i] + stairLength[i]) {
					diffx = x[j] - (x[i] + stairLength[i]);
				} else if (x[j] >= x[i] && x[j] <= x[i] + stairLength[i]) {
					diffx = 0;
				} else if (x[j] + stairLength[j] >= x[i]) {
					diffx = 0;
				} else {
					diffx = x[i] - (x[j] + stairLength[j]);
				}
				double dist = Math.sqrt(diffx * diffx + diffy * diffy);
				if (dist <= K && y[i] <= y[j]) {
					jumpMap[i][j] = true;
				}
			}
		}
		// if i, j belongs to one superId, it can jump between i <-> j.
		int[] superId = new int[n];
		ArrayList<Node> nodes = new ArrayList<Node>();

		superId[0] = 0;
		nodes.add(new Node(y[0], sweets[0]));
		
		for (int i = 1; i < n; ++i) {
			boolean found = false;
			for (int j = 0; j < i; ++j) {
				if (jumpMap[i][j] && jumpMap[j][i]) {
					superId[i] = superId[j];
					nodes.get(superId[i]).addSweet(sweets[i]);
					found = true;
					break;
				}
			}
			if (!found) {
				superId[i] = nodes.size();
				nodes.add(new Node(y[i], sweets[i]));
			}
		}
		int nodeCount = nodes.size();
		boolean[][] nodeJumpMap = new boolean[nodeCount][nodeCount];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (jumpMap[i][j]) {
					nodeJumpMap[superId[i]][superId[j]] = true;
				}
			}
		}
		for (int i = 0; i < nodeCount; ++i) {
			for (int j = 0; j < nodeCount; ++j) {
				if (i == j || !nodeJumpMap[i][j]) {
					continue;
				}
				nodes.get(j).addFrom(nodes.get(i));
			}
		}
		Collections.sort(nodes, new NodeComparator());
		
		Node startNode = new Node(0, 0);
		startNode.reachable = true;
		for (int i = 0; i < nodeCount; ++i) {
			if (nodes.get(i).y <= K) {
				nodes.get(i).addFrom(startNode);
			}
		}
		for (int i = 0; i < nodeCount; ++i) {
			nodes.get(i).sumSweet();
		}
		int result = 0;
		for (int i = 0; i < nodeCount; ++i) {
			result = Math.max(result, nodes.get(i).sweetSum);
		}
		return result;
	}
}