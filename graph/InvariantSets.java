import java.util.*;

public class InvariantSets {
	public long countSets(int[] f) {
		int n = f.length;
		ArrayList<ArrayList<Integer>> rev_f = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; ++i) {
			rev_f.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; ++i) {
			rev_f.get(f[i]).add(i);
		}
		
		boolean[] visited = new boolean[n];
		long result = 1;
		for (int i = 0; i < n; ++i) {
			if (visited[i]) {
				continue;
			}
			int[] visit_count = new int[n];
			int t = i;
			while (visit_count[t] < 2) {
				visit_count[t]++;
				t = f[t];
			}
			// all nodes with visit_count[i] == 2 is in the loop.
			long mul = 1;
			for (int j = 0; j < n; ++j) {
				if (visit_count[j] == 2) {
					// It is a tree, we need to count the count of its subtree containing root or nothing.
					long count = getSubTreeCount(j, rev_f, visit_count, visited);
					mul *= (count - 1);
					//System.out.printf("j = %d, count = %d, mul = %d\n", j, count, mul);
				}
			}
			result = result * (1 + mul);
			//System.out.printf("i = %d, mul = %d, result = %d\n", i, mul, result);
		}
		return result;
	}
	
	long getSubTreeCount(int root, ArrayList<ArrayList<Integer>> children, int[] visit_count, boolean[] visited) {
		visited[root] = true;
		long result = 1;
		for (Integer child : children.get(root)) {
			if (visit_count[child] != 2) {
				long a = getSubTreeCount(child, children, visit_count, visited);
				result *= a;
			}
		}
		result++;
		return result;
	}
}
