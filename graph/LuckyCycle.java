public class LuckyCycle {
	class Connect {
		boolean connected;
		int four_count;
		int seven_count;
		Connect() {
			connected = false;
			four_count = 0;
			seven_count = 0;
		}
	}
	
	public int[] getEdge(int[] edge1, int[] edge2, int[] weight) {
		int n = edge1.length + 1;
		Connect[][] dp = new Connect[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				dp[i][j] = dp[j][i] = new Connect();
			}
		}
		for (int i = 0; i < n - 1; ++i) {
			int a = edge1[i] - 1;
			int b = edge2[i] - 1;
			dp[a][b].connected = true;
			if (weight[i] == 4) {
				dp[a][b].four_count++;
			} else {
				dp[a][b].seven_count++;
			}
		}
		for (int step = 0; step < n; ++step) {
			for (int i = 0; i < n - 1; ++i) {
				int a = edge1[i] - 1;
				int b = edge2[i] - 1;
				for (int c = 0; c < n; ++c) {
					if (c == a || c == b || dp[a][c].connected == dp[b][c].connected) {
						continue;
					}
					if (dp[a][c].connected && !dp[b][c].connected) {
						int tmp = a;
						a = b;
						b = tmp;
					}
					dp[a][c].connected = true;
					dp[a][c].four_count = dp[b][c].four_count;
					dp[a][c].seven_count = dp[b][c].seven_count;
					if (weight[i] == 4) {
						dp[a][c].four_count++;
					} else {
						dp[a][c].seven_count++;
					}
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				//System.out.printf("dp[%d][%d], connected = %b, four = %d, seven = %d\n",
				//	i, j, dp[i][j].connected, dp[i][j].four_count, dp[i][j].seven_count);
				if (dp[i][j].connected && dp[i][j].four_count != 0 && dp[i][j].seven_count != 0) {
					if (dp[i][j].four_count + 1 == dp[i][j].seven_count) {
						return new int[]{i + 1, j + 1, 4};
					} else if (dp[i][j].seven_count + 1 == dp[i][j].four_count) {
						return new int[]{i + 1, j + 1, 7};
					}
				}
			}
		}
		return new int[]{};
	}

  public static void main(String[] args) {
    LuckyCycle cycle = new LuckyCycle();
    int[] edge1 = new int[]{2, 1, 5, 1, 1, 2, 1, 3, 5, 4, 4, 3};
    int[] edge2 = new int[]{13, 2, 7, 5, 3, 12, 4, 11, 6, 9, 8, 10};
    int[] weight = new int[] {4, 7, 7, 4, 7, 4, 4, 4, 7, 7, 7, 4};
    int[] result = cycle.getEdge(edge1, edge2, weight);
    System.out.printf("result = [");
    for (int i = 0; i < result.length; ++i) {
      System.out.printf("%d ", result[i]);
    }
    System.out.printf("]\n");
	}
}
