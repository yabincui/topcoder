import java.util.*;

public class XorTravelingSalesman {
	public int maxProfit(int[] cityValues, String[] roads) {
		int n = cityValues.length;
		int value_mask = 1023;
		boolean[][] hit = new boolean[n][value_mask + 1];
		hit[0][cityValues[0]] = true;
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add((0 << 16) | cityValues[0]);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			int pos = cur >> 16;
			int value = cur & 0xffff;
			for (int i = 0; i < n; ++i) {
				if (roads[i].charAt(pos) == 'Y') {
					int nvalue = value ^cityValues[i];
					if (!hit[i][nvalue]) {
						hit[i][nvalue] = true;
						queue.add((i << 16) | nvalue);
					}
				}
			}
		}
		for (int value = value_mask; value >= 0; --value) {
			for (int i = 0; i < n; ++i) {
				if (hit[i][value]) {
					return value;
				}
			}
		}
		return 0;
	}
}
