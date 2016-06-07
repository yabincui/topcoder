import java.util.*;

public class CrazyComponents {
	public double expectedProfit(int k, String[] components, int[] income, int[] expense) {
		int n = components.length;
		int mask = (1 << n) - 1;
		int[] need = new int[n];
		for (int i = 0; i < components.length; ++i) {
			if (!components[i].isEmpty()) {
				String[] strs = components[i].split(" ");
				for (int j = 0; j < strs.length; ++j) {
					need[i] |= (1 << Integer.parseInt(strs[j]));
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			System.out.printf("need[%d] = %x\n", i, need[i]);
		}
		double[][] dp = new double[k+1][mask + 1];
		for (int i = k - 1; i >= 0; --i) {
			for (int j = 0; j <= mask; ++j) {
				double newValue = 0.0;
				for (int t = 0; t < n; ++t) {
					if ((need[t] & ~j) != 0) {
						newValue += dp[i+1][j];
					} else {
						newValue += Math.max(dp[i+1][j|(1<<t)] + income[t] - expense[t], dp[i+1][j]);
					}
				}
				newValue /= n;
				dp[i][j] = newValue;
				System.out.printf("dp[%d][%x] = %f\n", i, j, dp[i][j]);
			}
		}
		return dp[0][0];
	}
}