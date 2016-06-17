import java.util.*;

public class Fragile2 {

	public int countPairs(String[] graph) {
		int n = graph.length;
		boolean[] valid = new boolean[n];
		for (int i = 0; i < n; ++i) {
			valid[i] = true;
		}
		int origin_components = getComponents(graph, valid);
		int count = 0;
		for (int a = 0; a < n; ++a) {
			for (int b = a + 1; b < n; ++b) {
				for (int i = 0; i < n; ++i) {
					if (i != a && i != b) {
						valid[i] = true;
					} else {
						valid[i] = false;
					}
				}
				int components = getComponents(graph, valid);
				if (components > origin_components) {
					count++;
				}
			}
		}
		return count;
	}
	
	private int getComponents(String[] graph, boolean[] valid) {
		int n = graph.length;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		int result = 0;
		while (true) {
			int start;
			for (start = 0; start < n; ++start) {
				if (valid[start]) {
					break;
				}
			}
			if (start == n) {
				break;
			}
			queue.add(start);
			valid[start] = false;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int next = 0; next < n; ++next) {
					if (valid[next] && graph[cur].charAt(next) == 'Y') {
						queue.add(next);
						valid[next] = false;
					}
				}
			}
			result++;
		}
		return result;
	}
}
