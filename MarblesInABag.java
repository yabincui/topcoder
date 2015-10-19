import java.util.*;

public class MarblesInABag {
	// Out of memory.
	public double getProbability2(int redCount, int blueCount) {
		double[][] dp = new double[redCount + 1][blueCount + 1];
		dp[redCount][blueCount] = 1.0;
		for (int i = redCount; i >= 0; i--) {
			for (int j = blueCount; j >= 0; j--) {
				if (i == 0 && j == 0) {
					continue;
				}
				boolean myTurn = (i + j) % 2 == 1;
				if (myTurn) {
					if (i > 0) {
						dp[i-1][j] += dp[i][j] * i / (i + j);
					}
					if (j > 0) {
						dp[i][j-1] += dp[i][j] * j / (i + j);
					}
				} else {
					if (j != 0) {
						dp[i][j-1] += dp[i][j];
					}
				}
			}
		}
		return (blueCount == 0) ? 0.0 : dp[0][1];
	}
	
	public double getProbability(int redCount, int blueCount) {
		double[] dp = new double[blueCount + 1];
		dp[blueCount] = 1.0;
		for (int i = redCount; i >= 0; i--) {
			double[] nextDp = new double[blueCount + 1];
			for (int j = blueCount; j >= 0; j--) {
				if (i == 0 && j == 0) {
					continue;
				}
				boolean myTurn = (i + j) % 2 == 1;
				if (myTurn) {
					if (i > 0) {
						nextDp[j] += dp[j] * i / (i + j);
					}
					if (j > 0) {
						dp[j-1] += dp[j] * j / (i + j);
					}
				} else {
					if (j != 0) {
						dp[j-1] += dp[j];
					}
				}
			}
			if (i != 0) {
				dp = nextDp;
			}
		}
		return (blueCount == 0) ? 0.0 : dp[1];
	}
}