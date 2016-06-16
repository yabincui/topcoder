public class Sunnygraphs2 {
	public long count(int[] a) {
		int n = a.length;
		boolean[] valid = new boolean[n];
		for (int i = 0; i < n; ++i) {
			valid[i] = true;
		}
		long result = 1;
		while (true) {
			int start = -1;
			for (int i = 0; i < n; ++i) {
				if (valid[i]) {
					start = i;
					break;
				}
			}
			if (start == -1) {
				break;
			}
			boolean[] hit = new boolean[n];
			int cur = start;
			while (!hit[cur]) {
				hit[cur] = true;
				cur = a[cur];
			}
			hit = new boolean[n];
			// how many nodes in the loop ?
			int loop_start = cur;
			int loop_size = 1;
			while (true) {
				hit[cur] = true;
				cur = a[cur];
				if (cur == loop_start) {
					break;
				}
				loop_size++;
			}
			// how many nodes outside the loop?
			int outside_loop_size = 0;
			while (true) {
				boolean has_outside_node = false;
				for (int i = 0; i < n; ++i) {
					if (!hit[i] && hit[a[i]]) {
						hit[i] = true;
						has_outside_node = true;
						break;
					}
				}
				if (!has_outside_node) {
					break;
				}
				outside_loop_size++;
			}
			// the calculate formula is:
			// If only one connected component:
			// 2^(outside_loop_size) * (2^loop_size - 1) + 1
			// If many connect components:
			// multiply all 2^(outside_loop_size) * (2^loop_size - 1)
			if (loop_size + outside_loop_size == n) {
				result = (1L << outside_loop_size) * ((1L << loop_size) - 1) + 1;
			} else {
				result *= (1L << outside_loop_size) * ((1L << loop_size) - 1);
			}
			for (int i = 0; i < n; ++i) {
				if (hit[i]) {
					valid[i] = false;
				}
			}
		}
		return result;
	}
}
