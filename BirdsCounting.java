import java.util.*;

public class BirdsCounting {
	public double computeProbability(int birdsNumber, int caughtPerDay, int daysNumber, int birdsMarked) {
		double[][] C = buildC(birdsNumber);
		// dp[i][j] means the probability of having j marked birds on day i.
		double[][] dp = new double[daysNumber + 1][birdsNumber + 1];
		dp[0][0] = 1;
		for (int i = 0; i < daysNumber; ++i) {
			for (int j = 0; j <= birdsNumber; ++j) {
				if (dp[i][j] == 0.0) {
					continue;
				}
				for (int newMark = 0; newMark <= caughtPerDay; ++newMark) {
					int caughtOldMark = caughtPerDay - newMark;
					if (caughtOldMark > j) {
						continue;
					}
					if (newMark > birdsNumber - j) {
						continue;
					}
					double probability = C[j][caughtOldMark] * C[birdsNumber - j][newMark] / C[birdsNumber][caughtPerDay];
					dp[i+1][j + newMark] += probability * dp[i][j];
				}
			}
		}
		return dp[daysNumber][birdsMarked];
	}
	
	double[][] buildC(int n) {
		double[][] C = new double[n + 1][n + 1];
		C[0][0] = 1;
		for (int i = 1; i <= n; ++i) {
			C[i][0] = C[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				C[i][j] = C[i-1][j-1] + C[i-1][j];
			}
		}
		return C;
	}
}