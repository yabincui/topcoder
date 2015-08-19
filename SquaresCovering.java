import java.util.*;

public class SquaresCovering {
	public int minCost(int[] x, int[] y, int[] cost, int[] sides) {
		int n = x.length;
		int mask = (1 << n) - 1;
		long[] costs = new long[mask + 1];
		costs[0] = 0;
		for (int i = 1; i <= mask; ++i) {
			costs[i] = -1;
		}
		for (int i = 0; i < cost.length; ++i) {
			int side = sides[i];
			for (int j = 0; j < n; ++j) {
				int left = x[j];
				int right = left + side;
				for (int k = 0; k < n; ++k) {
					int up = y[k];
					int bottom = up + side;
					int bits = 0;
					for (int t = 0; t < n; ++t) {
						int tx = x[t];
						int ty = y[t];
						if ((tx >= left && tx <= right) &&
								(ty >= up && ty <= bottom)) {
							bits |= 1 << t;
						}
					}
					if (costs[bits] == -1 || costs[bits] > cost[i]) {
						costs[bits] = cost[i];
					}
				}
			}
		}
		ArrayList<Integer> squares = new ArrayList<Integer>();
		for (int i = 1; i <= mask; ++i) {
			if ((i & 1) != 0 || costs[i] == -1) {
				continue;
			}
			squares.add(i);
		}
		
		for (int i = 2; i <= mask; i += 2) {
			for (int j = 0; j < squares.size(); ++j) {
				int k = squares.get(j);
				if (k > i) {
					break;
				}
				if ((k & ~i) != 0) {
					continue;
				}
				int t = i | 1;
				long newCost = costs[k] + costs[t & ~k];
				if (costs[t] == -1 || costs[t] > newCost) {
					costs[t] = newCost;
				}
			}
		}
		return (int)costs[mask];
	}
}