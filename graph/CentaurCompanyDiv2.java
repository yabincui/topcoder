import java.util.*;

public class CentaurCompanyDiv2 {
	class Node {
		ArrayList<Node> children = new ArrayList<Node>();		
	}
	
	long subTreeCount;
	
	public long count(int[] a, int[] b) {
		int n = a.length + 1;
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; ++i) {
			nodes[i] = new Node();
		}
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(0);
		visited[0] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int i = 0; i < n - 1; ++i) {
				int na = a[i] - 1;
				int nb = b[i] - 1;
				if (na == cur && !visited[nb]) {
					nodes[na].children.add(nodes[nb]);
					visited[nb] = true;
					queue.add(nb);
				} else if (nb == cur && !visited[na]) {
					nodes[nb].children.add(nodes[na]);
					visited[na] = true;
					queue.add(na);
				}
			}
		}
		subTreeCount = 1;
		getSubTreeCount(nodes[0]);
		return subTreeCount;
	}
	
	long getSubTreeCount(Node node) {
		long result = 1;
		for (Node child : node.children) {
			result *= getSubTreeCount(child) + 1;
		}
		subTreeCount += result;
		return result;
	}
}
