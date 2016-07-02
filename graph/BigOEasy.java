import java.util.*;

public class BigOEasy {
	// Solution out of time, recursive takes exponential time.
	public String isBoundedTimeout(String[] graph) {
		// If there are two cycles intersect on some nodes, it is unbounded.
		// Namely, go from one node, if there are more than one ways going back to the node, it is unbounded.
		int n = graph.length;
		for (int start = 0; start < n; ++start) {
			// go from start, find two loop paths.
			long loopCount = findPath(start, start, graph, 0);
			if (loopCount > 1) {
				return "Unbounded";
			}
		}
		return "Bounded";
	}
	
	private long findPath(int cur, int target, String[] graph, long nodeMaskInPath) {
		long loopCount = 0;
		for (int i = 0; i < graph.length; ++i) {
			if (graph[cur].charAt(i) == 'Y') {
				if (i == target) {
					loopCount++;
					continue;
				}
				if ((nodeMaskInPath & (1L << i)) != 0) {
					continue;
				}
				loopCount += findPath(i, target, graph, nodeMaskInPath | (1L << i));
			}
		}
		return loopCount;
	}
	
	public String isBounded(String[] graph) {
		int n = graph.length;
		for (int start = 0; start < n; ++start) {
			// go from start, find a path back to start.
			boolean[] visited = new boolean[n];
			ArrayList<Integer> path = new ArrayList<Integer>();
			if (!findPath(start, start, graph, -1, visited, path)) {
				continue;
			}
			// disable an edge in the path, see if we can still find a path.
			// It is not correct to disable a node, become some node can be critical. For example:
			//  0 -> 1  1-> 2  1-> 0  2-> 0.   then node 1 is critical.
			for (Integer disable : path) {
				ArrayList<Integer> path2 = new ArrayList<Integer>();
				for (int i = 0; i < n; ++i) {
					visited[i] = false;
				}
				if (findPath(start, start, graph, disable, visited, path2)) {
					return "Unbounded";
				}
			}
		}
		return "Bounded";
	}
	
	private boolean findPath(int cur, int target, String[] graph, int disabledPath, boolean[] visited, ArrayList<Integer> path) {
		for (int i = 0; i < graph.length; ++i) {
			if (graph[cur].charAt(i) == 'Y') {
				if (i == target) {
					return true;
				}
				if (visited[i]) {
					continue;
				}
				int pathElem = (cur << 16) | i;
				if (pathElem == disabledPath) {
					continue;
				}
				visited[i] = true;
				path.add(pathElem);
				if (findPath(i, target, graph, disabledPath, visited, path)) {
					return true;
				}
				path.remove(path.size() - 1);
			}
		}
		return false;
	}
}
