import java.util.*;

public class LonglongestPathTree {
	class Edge {
		boolean valid;
		int dist;
		int child;
		
		Edge(int dist, int child) {
			this.valid = true;
			this.dist = dist;
			this.child = child;
		}
	}

	public long getLength(int[] A, int[] B, int[] L) {
		int N = A.length + 1;
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; ++i) {
			neighbors.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < N - 1; ++i) {
			neighbors.get(A[i]).add(B[i]);
			neighbors.get(B[i]).add(A[i]);
		}
		boolean[] visited = new boolean[N];
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(0);
		visited[0] = true;
		int[] parent = new int[N];
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (Integer next : neighbors.get(cur)) {
				if (visited[next]) {
					continue;
				}
				parent[next] = cur;
				queue.add(next);
				visited[next] = true;
			}
		}

		ArrayList<ArrayList<Edge>> children = new ArrayList<ArrayList<Edge>>();
		
		for (int i = 0; i < N; ++i) {
			children.add(new ArrayList<Edge>());
		}
		Edge[] edges = new Edge[N - 1];
		for (int i = 0; i < N - 1; ++i) {
			int a = A[i];
			int b = B[i];
			if (parent[A[i]] == B[i]) {
				a = B[i];
				b = A[i];
			}
			edges[i] = new Edge(L[i], b);
			children.get(a).add(edges[i]);
		}
		long result = 0;
		for (int i = 0; i < N - 1; ++i) {
			edges[i].valid = false;
			long max_dist1 = getMaxDist(0, children);
			long max_dist2 = getMaxDist(edges[i].child, children);
			result = Math.max(result, max_dist1 + max_dist2 + edges[i].dist);
			edges[i].valid = true;
		}
		return result;
	}
	
	long getMaxDist(int root, ArrayList<ArrayList<Edge>> children) {
		long[] max_dist = new long[1];
		getMaxDist(root, children, max_dist);
		return max_dist[0];
	}
	
	long getMaxDist(int root, ArrayList<ArrayList<Edge>> children, long[] max_dist) {
		long[] sub_max = new long[2];
		for (Edge edge : children.get(root)) {
			if (!edge.valid) {
				continue;
			}
			long sub_max_with_root = getMaxDist(edge.child, children, max_dist);
			sub_max_with_root += edge.dist;
			if (sub_max_with_root > sub_max[0]) {
				sub_max[1] = sub_max[0];
				sub_max[0] = sub_max_with_root;
			} else if (sub_max_with_root > sub_max[1]) {
				sub_max[1] = sub_max_with_root;
			}
		}
		max_dist[0] = Math.max(max_dist[0], sub_max[0] + sub_max[1]);
		return sub_max[0];
	}
}
