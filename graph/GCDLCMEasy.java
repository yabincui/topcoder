import java.util.*;

public class GCDLCMEasy {

	class Node {
		int to;
		int gcd;
		int lcm;
		
		Node(int to, int gcd, int lcm) {
			this.to = to;
			this.gcd = gcd;
			this.lcm = lcm;
		}
	}

	public String possible(int n, int[] A, int[] B, int[] G, int[] L) {
		ArrayList<ArrayList<Node>> neighbors = new ArrayList<ArrayList<Node>>();
		for (int i = 0; i < n; ++i) {
			neighbors.add(new ArrayList<Node>());
		}
		for (int i = 0; i < A.length; ++i) {
			int a = A[i];
			int b = B[i];
			neighbors.get(a).add(new Node(b, G[i], L[i]));
			neighbors.get(b).add(new Node(a, G[i], L[i]));
		}
		int[] values = new int[n];
		int[] tmp_value = new int[n];
		for (int i = 0; i < n; ++i) {
			if (values[i] == 0) {
				for (int j = 1; j <= 10000; ++j) {
					System.arraycopy(values, 0, tmp_value, 0, n);
					tmp_value[i] = j;
					if (genValues(i, n, tmp_value, neighbors)) {
						System.arraycopy(tmp_value, 0, values, 0, n);
					}
				}
				if (values[i] == 0) {
					return "Solution does not exist";
				}
			}
		}
		return "Solution exists";
	}
	
	boolean genValues(int start, int n, int[] values, ArrayList<ArrayList<Node>> neighbors) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(start);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (Node node : neighbors.get(cur)) {
				int mul = node.gcd * node.lcm;
				int cur_value = values[cur];
				if (mul % cur_value != 0) {
					return false;
				}
				int next_value = mul / cur_value;
				if (getGcd(cur_value, next_value) != node.gcd) {
					return false;
				}
				if (values[node.to] != 0 && values[node.to] != next_value) {
					return false;
				}
				if (values[node.to] == 0) {
					values[node.to] = next_value;
					queue.add(node.to);
				}
			}
		}
		return true;
	}
	
	int getGcd(int a, int b) {
		int tmp;
		if (a < b) {
			tmp = a; a = b; b = tmp; 
		}
		while (a % b != 0) {
			tmp = a % b;
			a = b;
			b = tmp;
		}
		return b;
	}
}
