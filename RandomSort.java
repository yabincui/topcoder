import java.util.*;

public class RandomSort {

	HashMap<Integer, Double> dp;
	int last;
	int n;
	
	public double getExpected(int[] permutation) {
		n = permutation.length;
		dp = new HashMap<Integer, Double>();
		int first = 0;
		for (int i = 0; i < n; ++i) {
			first = (first << 3) + (permutation[i] - 1);
		}
		last = 0;
		for (int i = 0; i < n; ++i) {
			last = (last << 3) + i;
		}
		return getExpectedNumber(first);
	}
	
	private double getExpectedNumber(int state) {
		if (state == last) {
			return 0;
		}
		if (dp.containsKey(state)) {
			return dp.get(state);
		}
		int reverseCount = 0;
		double sum = 0;
		for (int i = 0; i < n; ++i) {
			int frontValue = (state >> ((n - 1 - i) * 3)) & 7;
			for (int j = i + 1; j < n; ++j) {
				int backValue = (state >> ((n - 1 - j) * 3)) & 7;
				if (frontValue > backValue) {
					int nextState = (state & ~(7 << ((n - 1 - i) * 3)) & ~(7 << ((n - 1 - j) * 3))
							| (backValue << ((n - 1 - i) * 3)) | (frontValue << ((n - 1 - j) * 3)));
					sum += getExpectedNumber(nextState) + 1;
					reverseCount++;
				}
			}
		}
		double result = sum / reverseCount;
		dp.put(state, result);
		return result;
	}
	
	/*
	double[] dp;
	int last;
	int n;
	
	public double getExpected(int[] permutation) {
		n = permutation.length;
		dp = new double[(1 << (n * 3))];
		int first = 0;
		for (int i = 0; i < n; ++i) {
			first = (first << 3) + (permutation[i] - 1);
		}
		last = 0;
		for (int i = 0; i < n; ++i) {
			last = (last << 3) + i;
		}
		return getExpectedNumber(first);
	}
	
	private double getExpectedNumber(int state) {
		if (state == last) {
			return 0;
		}
		if (dp[state] != 0) {
			return dp[state];
		}
		int reverseCount = 0;
		double sum = 0;
		for (int i = 0; i < n; ++i) {
			int frontValue = (state >> ((n - 1 - i) * 3)) & 7;
			for (int j = i + 1; j < n; ++j) {
				int backValue = (state >> ((n - 1 - j) * 3)) & 7;
				if (frontValue > backValue) {
					int nextState = (state & ~(7 << ((n - 1 - i) * 3)) & ~(7 << ((n - 1 - j) * 3))
							| (backValue << ((n - 1 - i) * 3)) | (frontValue << ((n - 1 - j) * 3)));
					sum += getExpectedNumber(nextState) + 1;
					reverseCount++;
				}
			}
		}
		dp[state] = sum / reverseCount;
		return dp[state];
	}
	*/
}