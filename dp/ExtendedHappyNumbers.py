import java.util.*;

public class ExtendedHappyNumbers {
	int[] pow;
	int[] dp;
	public long calcTheSum(int A, int B, int K) {
		pow = new int[10];
		for (int i = 1; i < 10; ++i) {
			int t = 1;
			for (int j = 0; j < K; ++j) {
				t *= i;
			}
			pow[i] = t;
		}
		dp = new int[6000000];
		long sum = 0;
		for (int i = A; i <= B; ++i) {
			sum += getMin(i);
		}
		return sum;
	}
	private int getMin(int x) {
		if (dp[x] != 0) {
			return dp[x];
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		HashMap<Integer, Integer> posMap = new HashMap<Integer, Integer>();
		values.add(x);
		posMap.put(x, 0);
		int cur = x;
		while (true) {
			int next = 0;
			while (cur != 0) {
				next += pow[cur % 10];
				cur /= 10;
			}
			cur = next;
			if (posMap.containsKey(cur)) {
				break;
			}
			posMap.put(cur, values.size());
			values.add(cur);
			if (dp[cur] != 0) {
				break;
			}
		}
		int cur_min = values.get(values.size() - 1);
		if (dp[cur] != 0) {
			cur_min = Math.min(cur_min, dp[cur]);
		}
		for (int i = posMap.get(cur); i < values.size(); ++i) {
			cur_min = Math.min(cur_min, values.get(i));
		}
		for (int i = values.size() - 1; i >= 0; --i) {
			cur_min = Math.min(cur_min, values.get(i));
			dp[values.get(i)] = cur_min;
		}
		return dp[x];
	}
	
}
