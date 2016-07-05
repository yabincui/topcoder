import java.util.*;

public class KingdomXCitiesandVillagesAnother {
	
	class Node {
		boolean isCityDist;
		int cv1;
		int v2;
		double d;
		
		Node(boolean isCityDist, int cv1, int v2, double d) {
			this.isCityDist = isCityDist;
			this.cv1 = cv1;
			this.v2 = v2;
			this.d = d;
		}
	}
	
	public class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			if (n1.d == n2.d) {
				return 0;
			}
			return (n1.d > n2.d ? 1 : -1);
		}
	}

	public double determineLength(int[] cityX, int[] cityY, int[] villageX, int[] villageY) {
		int n = cityX.length;
		int m = villageX.length;
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < m; ++j) {
				queue.add(new Node(true, i, j, getDist(cityX[i], cityY[i], villageX[j], villageY[j])));
			}
		}
		for (int i = 0; i < m; ++i) {
			for (int j = i + 1; j < m; ++j) {
				queue.add(new Node(false, i, j, getDist(villageX[i], villageY[i], villageX[j], villageY[j])));
			}
		}
		boolean[] connect = new boolean[m];
		int[] unionSet = new int[m];
		for (int i = 0; i < m; ++i) {
			unionSet[i] = -1;
		}
		double result = 0;
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			//System.out.printf("node.isCity = %b, cv1 = %d, v2 = %d, d = %f\n", node.isCityDist, node.cv1, node.v2, node.d);
			if (node.isCityDist) {
				int root = getRoot(unionSet, node.v2);
				if (connect[root]) {
					continue;
				}
				connect[root] = true;
				//System.out.printf("add %f\n", node.d);
				result += node.d;
			} else {
				int root1 = getRoot(unionSet, node.cv1);
				int root2 = getRoot(unionSet, node.v2);
				if (root1 != root2) {
					if (connect[root1] && connect[root2]) {
						continue;
					} else if (connect[root1] && !connect[root2]) {
						unionSet[root2] = root1;
					} else {
						unionSet[root1] = root2;
					}
					//System.out.printf("add %f\n", node.d);
					result += node.d;
				}
			}
		}
		return result;
	}
	
	private int getRoot(int[] unionSet, int pos) {
		int root = pos;
		while (unionSet[root] != -1) {
			root = unionSet[root];
		}
		while (pos != root) {
			int np = unionSet[pos];
			unionSet[pos] = root;
			pos = np;
		}
		return root;
	}
	
	private double getDist(int x1, int y1, int x2, int y2) {
		double dx = x1 - x2;
		double dy = y1 - y2;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
