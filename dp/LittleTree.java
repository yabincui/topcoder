import java.util.*;

public class LittleTree {
	class Node {
		int depth;
		// costs[i] is the min move needed for the subtree if current node is at depth i.
		int[] costs;
		ArrayList<Node> children;
		
		Node(int maxHeight) {
			depth = -1;
			costs = new int[maxHeight];
			children = new ArrayList<Node>();
		}
		
		void addChild(Node child) {
			children.add(child);
		}
		
		void calculateDepth(int depth) {
			this.depth = depth;
			for (Node child : children) {
				child.calculateDepth(depth + 1);
			}
		}
		
		void calculateCost(int maxDepth) {
			for (Node child : children) {
				child.calculateCost(maxDepth);
			}
			for (int i = 0; i <= depth; ++i) {
				int minCost = Integer.MAX_VALUE;
				for (int j = 0; j <= i && j <= maxDepth; ++j) {
					// Current node decides to move from depth i to depth j.
					if (j == 0 && i != 0) {
						// No way to move to root.
						continue;
					}
					int curCost = i - j;
					for (Node child : children) {
						curCost += child.costs[j+1];
					}
					minCost = Math.min(curCost, minCost);
				}
				costs[i] = minCost;
			}
		}
	}
	
	public int minCost(int N, String[] edges, int height) {
		Node[] nodes = new Node[N];
		for (int i = 0; i < N; ++i) {
			nodes[i] = new Node(N);
		}
		StringBuilder builder = new StringBuilder();
		for (String edge : edges) {
			builder.append(edge);
		}
		String[] strs = builder.toString().split(" ");
		for (String s : strs) {
			String[] strs2 = s.split(",");
			int parent = Integer.valueOf(strs2[0]);
			int child = Integer.valueOf(strs2[1]);
			nodes[parent].addChild(nodes[child]);
		}
		nodes[0].calculateDepth(0);
		nodes[0].calculateCost(height);
		return nodes[0].costs[0];
	}
}