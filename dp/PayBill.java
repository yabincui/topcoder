import java.util.*;

public class PayBill {
	public int[] whoPaid(int[] meals, int totalMoney) {
		// dp[i] is the paid bit mask if paid money is i.
		long[] dp = new long[totalMoney + 1];
		for (int i = 1; i <= totalMoney; ++i) {
			dp[i] = -1;
		}
		for (int i = 0; i < meals.length; ++i) {
			for (int j = totalMoney; j >= meals[i]; --j) {
				if (dp[j - meals[i]] == -1) {
					continue;
				}
				dp[j] = dp[j - meals[i]] | (1L << i);
			}
		}
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < meals.length; ++i) {
			if ((dp[totalMoney] & (1L << i)) != 0) {
				result.add(i);
			}
		}
		int[] ret = new int[result.size()];
		for (int i = 0; i < result.size(); ++i) {
			ret[i] = result.get(i);
		}
		return ret;
	}
}
