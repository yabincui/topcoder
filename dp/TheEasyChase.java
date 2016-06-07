public class TheEasyChase {
	
	public String winner(int n, int rowWhite, int colWhite, int rowBlack, int colBlack) {
		// dp[i][j][k] is the result when white is in pos i, black is in pos j, and it is
		// (k == 0 ? white : black)'s turn to go.
		int[][][] dp = new int[n * n][n * n][2];
		
		int[] aMoveX = new int[]{0, 0, 1, -1};
		int[] aMoveY = new int[]{1, -1, 0, 0};
		int[] bMoveX = new int[]{0, 0, 0, 0, 1, -1, 2, -2};
		int[] bMoveY = new int[]{1, -1, 2, -2, 0, 0, 0, 0};
		
		// One step situation.
		for (int i = 0; i < n * n; ++i) {
			int ax = i / n;
			int ay = i % n;
			for (int t = 0; t < aMoveX.length; ++t) {
				int bx = ax + aMoveX[t];
				int by = ay + aMoveY[t];
				if (!isValid(bx, by, n)) {
					continue;
				}
				int j = bx * n + by;
				dp[i][j][0] = 1;
			}
		}
		for (int i = 0; i < n * n; ++i) {
			int bx = i / n;
			int by = i % n;
			for (int t = 0; t < bMoveX.length; ++t) {
				int ax = bx + bMoveX[t];
				int ay = by + bMoveY[t];
				if (!isValid(ax, ay, n)) {
					continue;
				}
				int j = ax * n + ay;
				dp[j][i][1] = -1;
			}
		}
		
		int targetA = (rowWhite - 1) * n + colWhite - 1;
		int targetB = (rowBlack - 1) * n + colBlack - 1;
		while (true) {
			for (int aPos = 0; aPos < n * n; ++aPos) {
				for (int bPos = 0; bPos < n * n; ++bPos) {
					// turn = 0.
					if (dp[aPos][bPos][0] == 0) {
						// See if it can reach a Ba.
						int ax = aPos / n;
						int ay = aPos % n;
						for (int t = 0; t < aMoveX.length; ++t) {
							int nextAx = ax + aMoveX[t];
							int nextAy = ay + aMoveY[t];
							if (!isValid(nextAx, nextAy, n)) {
								continue;
							}
							int nextAPos = nextAx * n + nextAy;
							if (dp[nextAPos][bPos][1] > 0) {
								dp[aPos][bPos][0] = dp[nextAPos][bPos][1] + 1;
								break;
							}
						}
						if (dp[aPos][bPos][0] == 0) {
							// See if can only reach Bb.
							boolean onlyBb = true;
							int minBb = 0;
							for (int t = 0; t < aMoveX.length; ++t) {
								int nextAx = ax + aMoveX[t];
								int nextAy = ay + aMoveY[t];
								if (!isValid(nextAx, nextAy, n)) {
									continue;
								}
								int nextAPos = nextAx * n + nextAy;
								if (dp[nextAPos][bPos][1] >= 0) {
									onlyBb = false;
									break;
								}
								minBb = Math.min(minBb, dp[nextAPos][bPos][1]);
							}
							if (onlyBb) {
								dp[aPos][bPos][0] = minBb - 1;
							}
						}
					}
					
					// turn = 1.
					if (dp[aPos][bPos][1] == 0) {
						// See if it can reach a Ab.
						int bx = bPos / n;
						int by = bPos % n;
						for (int t = 0; t < bMoveX.length; ++t) {
							int nextBx = bx + bMoveX[t];
							int nextBy = by + bMoveY[t];
							if (!isValid(nextBx, nextBy, n)) {
								continue;
							}
							int nextBPos = nextBx * n + nextBy;
							if (dp[aPos][nextBPos][0] < 0) {
								dp[aPos][bPos][1] = dp[aPos][nextBPos][0] - 1;
								break;
							}
						}
						if (dp[aPos][bPos][1] == 0) {
							// See if it can only reach Aa.
							boolean onlyAa = true;
							int maxAa = 0;
							for (int t = 0; t < bMoveX.length; ++t) {
								int nextBx = bx + bMoveX[t];
								int nextBy = by + bMoveY[t];
								if (!isValid(nextBx, nextBy, n)) {
									continue;
								}
								int nextBPos = nextBx * n + nextBy;
								if (dp[aPos][nextBPos][0] <= 0) {
									onlyAa = false;
									break;
								}
								maxAa = Math.max(maxAa, dp[aPos][nextBPos][0]);
							}
							if (onlyAa) {
								dp[aPos][bPos][1] = maxAa + 1;
							}
						}
					}
				}
			}
			if (dp[targetA][targetB][0] != 0) {
				break;
			}
		}
		
		int result = dp[targetA][targetB][0];
		StringBuilder builder = new StringBuilder();
		if (result > 0) {
			builder.append("WHITE ");
			builder.append(result);
		} else {
			builder.append("BLACK ");
			builder.append(-result);
		}
		return builder.toString();
	}
	
	boolean isValid(int x, int y, int n) {
		if (x < 0 || x >= n || y < 0 || y >= n) {
			return false;
		}
		return true;
	}
}