public class TreeAndPathLength2 {
	public String possible(int n, int s) {
		boolean[][] dp_with_parent = new boolean[n + 1][s + 1];
		dp_with_parent[0][0] = true;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j <= s; ++j) {
				// try a node with each subtree count.
				for (int subtree_count = 1; subtree_count <= i; ++subtree_count) {
					int num_in_cur_node = (subtree_count + 1) * subtree_count / 2;
					if (j >= num_in_cur_node && dp_with_parent[i - subtree_count][j - num_in_cur_node]) {
						dp_with_parent[i][j] = true;
						//System.out.printf("dp[%d][%d] = true\n", i, j);
						break;
					}
				}
			}
		}
		// Let the root has only one subtree, this is always possible.
		if (dp_with_parent[n - 2][s]) {
			return "Possible";
		}
		return "Impossible";
	}

	public String possible_slow(int n, int s) {
		// hit[i][j] is if we can get j number of simple path 2 in a i nodes tree.
		boolean[][] hit = new boolean[n + 1][s + 1];
		
		// hit_with_parent[i][j] is if we can get j number of simple path 2 in a i nodes subtree having parent.
		boolean[][] hit_with_parent = new boolean[n + 1][s + 1];
		// count_hit_with_parent[i][j][k] is if we can get a sum k by using i subtrees having parent and total node count is j.
		boolean[][][] count_hit_with_parent = new boolean[n + 1][n + 1][s + 1];
		
		hit[0][0] = true;
		hit_with_parent[0][0] = true;
		count_hit_with_parent[0][0][0] = true;
		for (int i = 1; i <= n; ++i) {
			for (int j = 0; j <= s; ++j) {
				// try different subtree count for hit[i][j].
				for (int subtree_count = 0; subtree_count <= i - 1; ++subtree_count) {
					int num_in_cur_level = subtree_count * (subtree_count - 1) / 2;
					int prev_num = j - num_in_cur_level;
					if (prev_num < 0) {
						continue;
					}
					if (count_hit_with_parent[subtree_count][i-1][prev_num]) {
						hit[i][j] = true;
						break;
					}
				}
				// try different subtree count for hit_with_parent[i][j].
				for (int subtree_count = 0; subtree_count <= i - 1; ++subtree_count) {
					int num_in_cur_level = subtree_count * (subtree_count - 1) / 2 + subtree_count;
					int prev_num = j - num_in_cur_level;
					if (prev_num < 0) {
						continue;
					}
					if (count_hit_with_parent[subtree_count][i-1][prev_num]) {
						hit_with_parent[i][j] = true;
						break;
					}
				}
				if (hit_with_parent[i][j]) {
					for (int a = 0; a < n; ++a) {
						for (int b = 0; b <= n - i; ++b) {
							for (int c = 0; c <= s - j; ++c) {
								if (count_hit_with_parent[a][b][c]) {
									count_hit_with_parent[a + 1][b + i][c + j] = true;
								}
							}
						}
					}
				}
			}
		}
		if (hit[n][s]) {
			return "Possible";
		}
		return "Impossible";
	}
}
