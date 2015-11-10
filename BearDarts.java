import java.util.*;

public class BearDarts {
	public long count(int[] w) {
		int n = w.length;
		HashMap<Integer, Integer> valueToPosMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < n; ++i) {
			valueToPosMap.put(w[i], i);
		}
		int[][] afterEqual = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = n-2; j >= 0; --j) {
				afterEqual[i][j] = afterEqual[i][j+1] + (w[i] == w[j+1] ? 1 : 0);
			}
		}
		int[][] beforeEqual = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 1; j < n; ++j) {
				beforeEqual[i][j] = beforeEqual[i][j-1] + (w[i] == w[j-1] ? 1 : 0);
			}
		}
		HashMap<Integer, Integer> beforeValueMap = new HashMap<Integer, Integer>();
		long result = 0;
		for (int i = 1; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				beforeValueMap.put(w[i-1], i-1);
				for (Integer pos : beforeValueMap.values()) {
					int count1 = beforeEqual[pos][i];
					long mul = (long)w[pos] * w[j];
					if (mul % w[i] != 0) {
						continue;
					}
					long d = mul / w[i];
					if (d > 1000000000) {
						continue;
					}
					Integer dPos = valueToPosMap.get((int)d);
					if (dPos == null) {
						continue;
					}
					result += afterEqual[dPos][j] * count1;
				}
			}
		}
		return result;
	}
}
