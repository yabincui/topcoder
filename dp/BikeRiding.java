import java.util.*;

public class BikeRiding {
	public int countRoutes(String[] paths, int[] startPoints, int endPoint, int countLimit) {
		int n = paths.length;
		boolean[][] connect = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				connect[i][j] = paths[i].charAt(j) == '1';
			}
		}
		for (int k = 0; k < n; ++k) {
			for (int i = 0; i < n; ++i) {
				for (int j = 0; j < n; ++j) {
					if (connect[i][k] && connect[k][j]) {
						connect[i][j] = true;
					}
				}
			}
		}
		// Test if can arrive end.
		boolean canReachEnd = false;
		for (int i = 0; i < startPoints.length; ++i) {
			if (connect[startPoints[i]][endPoint]) {
				canReachEnd = true;
				break;
			}
		}
		if (!canReachEnd) {
			return 0;
		}
		// Test loop for infinite.
		for (int i = 0; i < startPoints.length; ++i) {
			if (connect[startPoints[i]][endPoint] && connect[startPoints[i]][startPoints[i]]) {
				return -1;
			}
		}
		if (connect[endPoint][endPoint]) {
			return -1;
		}
		boolean[] canReachFromStart = new boolean[n];
		for (int i = 0; i < startPoints.length; ++i) {
			canReachFromStart[startPoints[i]] = true;
		}
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < startPoints.length; ++j) {
				if (connect[startPoints[j]][i]) {
					canReachFromStart[i] = true;
					break;
				}
			}
		}
		for (int i = 0; i < n; ++i) {
			if (canReachFromStart[i] && connect[i][i] && connect[i][endPoint]) {
				return -1;
			}
		}
		boolean[] inPath = new boolean[n];
		for (int i = 0; i < n; ++i) {
			if (canReachFromStart[i] && connect[i][endPoint]) {
				inPath[i] = true;
			}
		}
		inPath[endPoint] = true;
		// No loop in any path. Calculate path count using dp.
		long[] initValue = new long[n];
		for (int i = 0; i < startPoints.length; ++i) {
			initValue[startPoints[i]] = 1;
		}
		long[] dp = new long[n];
		for (int time = 0; time < n; ++time) {
			for (int i = 0; i < n; ++i) {
				if (!inPath[i]) {
					continue;
				}
				long sum = initValue[i];
				for (int j = 0; j < n; ++j) {
					if (inPath[j] && paths[j].charAt(i) == '1') {
						sum += dp[j];
					}
				}
				if (sum > countLimit) {
					return -1;
				}
				dp[i] = sum;
			}
		}
		return (int)dp[endPoint];
	}
}