import java.util.*;

public class RGBTree {
	public String exist(String[] G) {
		int n = G.length;
		if ((n - 1) % 3 != 0) {
			return "Does not exist";
		}
		int mask = (1 << n) - 1;
		int max_color = (n - 1) / 3;
		
		HashSet<Integer> hitSet = new HashSet<Integer>();
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		// (2^13 * 4^3 * 13 * 13)
		int start = getValue(1, 0, 0, 0);
		hitSet.add(start);
		queue.add(start);
		
		while (!queue.isEmpty()) {
			int value = queue.poll();
			int bits = value >> 16;
			int r = (value >> 8) & 0xf;
			int g = (value >> 4) & 0xf;
			int b = value & 0xf;
			for (int i = 0; i < n; ++i) {
				if ((bits & (1 << i)) == 0) {
					continue;
				}
				for (int j = 0; j < n; ++j) {
					char c = G[i].charAt(j);
					if ((bits & (1 << j)) != 0 || c == '.') {
						continue;
					}
					int ni = bits | (1 << j);
					int nr = r;
					int ng = g;
					int nb = b;
					if (c == 'R') {
						nr++;
					} else if (c == 'G') {
						ng++;
					} else {
						nb++;
					}
					if (nr > max_color || ng > max_color || nb > max_color) {
						continue;
					}
					if (nr == max_color && ng == max_color && nb == max_color) {
						return "Exist";
					}
					int nvalue = getValue(ni, nr, ng, nb);
					if (!hitSet.contains(nvalue)) {
						hitSet.add(nvalue);
						queue.add(nvalue);
					}
				}
			}
		}
		return "Does not exist";
	}
	
	int getValue(int pos, int r, int g, int b) {
		return (pos << 16) | (r << 8) | (g << 4) | b;
	}
}
