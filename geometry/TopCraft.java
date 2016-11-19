import java.util.*;

public class TopCraft {
	public int select(String[] select) {
		int n = select.length;
		int[][] number = new int[n][n];
		int one_count = 0;
		ArrayList<Integer> oneX = new ArrayList<Integer>();
		ArrayList<Integer> oneY = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				number[i][j] = -1;
				if (select[i].charAt(j) == '1') {
					number[i][j] = one_count++;
					oneY.add(i);
					oneX.add(j);
				}
			}
		}
		// erase_mask[i] is using i as the up side, the erase_masks can be got by
		// using one ranctangle.
		ArrayList<TreeSet<Integer>> erase_mask = new ArrayList<>();
		for (int i = 0; i < one_count; ++i) {
			TreeSet<Integer> s = new TreeSet<Integer>();
			int curC = oneX.get(i);
			for (int left_c = 0; left_c <= curC; ++left_c) {
				for (int right_c = curC; right_c < n; ++right_c) {
					int m = 0;
					boolean hasUnit2 = false;
					for (int r = oneY.get(i); r < n && !hasUnit2; ++r) {
						for (int c = left_c; c <= right_c; ++c) {
							if (select[r].charAt(c) == '2') {
								hasUnit2 = true;
								break;
							} else if (select[r].charAt(c) == '1') {
								m |= 1 << number[r][c];
							}
						}
						if (!hasUnit2) {
							s.add(m);
						}
					}
				}
			}
			erase_mask.add(s);
		}
		
		int mask = (1 << one_count) - 1;
		int[] min_cost = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			int a = 0;
			min_cost[i] = Integer.MAX_VALUE;
			for (int j = 0; j < one_count; ++j) {
				if ((i & (1<< j)) != 0) {
					a = j;
					break;
				}
			}
			for (Integer m : erase_mask.get(a)) {
				int prev = i & ~m;
				min_cost[i] = Math.min(min_cost[i], min_cost[prev] + 1);
			}
		}
		return min_cost[mask];
	}
}


