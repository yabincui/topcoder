import java.util.*;

public class PlaneGame {
	public int bestShot(int[] x, int[] y) {
		int n = x.length;
		if (n <= 2) {
			return n;
		}
		int result = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = i + 1; j < n; ++j) {
				if (x[i] == x[j]) {
					int common = 0;
					int maxHorizen = 0;
					HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
					for (int k = 0; k < n; ++k) {
						if (k == i || k == j) {
							continue;
						}
						if (x[k] == x[i]) {
							common++;
							continue;
						}
						int t = 0;
						if (map.get(y[k]) == null) {
							map.put(y[k], 1);
							t = 1;
						} else {
							t = map.get(y[k]) + 1;
							map.put(y[k], t);
						}
						maxHorizen = Math.max(maxHorizen, t);
					}
					result = Math.max(result, 2 + common + maxHorizen);	
				} else if (y[i] == y[j]) {
					int common = 0;
					int maxVertical = 0;
					HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
					for (int k = 0; k < n; ++k) {
						if (k == i || k == j) {
							continue;
						}
						if (y[k] == y[i]) {
							common++;
							continue;
						}
						int t = 0;
						if (map.get(x[k]) == null) {
							t = 1;
							map.put(x[k], 1);
						} else {
							t = map.get(x[k]) + 1;
							map.put(x[k], t);
						}
						maxVertical = Math.max(maxVertical, t);
					}
					result = Math.max(result, 2 + common + maxVertical);
				} else {
					double k1 = (y[i] - y[j]) / (double)(x[i] - x[j]);
					double b = y[i] - k1 * x[i];
					double inv_k = -1.0 / k1;
					int common = 0;
					int maxHorizen = 0;
					HashMap<String, Integer> map = new HashMap<String, Integer>();
					for (int k = 0; k < n; ++k) {
						if (k == i || k == j) {
							continue;
						}
						double yt = k1 * x[k] + b;
						if (Math.abs(yt - y[k]) < 1e-6) {
							common++;
							continue;
						}
						double b2 = y[k] - inv_k * x[k];
						String key = String.format("%.6f", b2);
						int t = 0;
						if (map.get(key) == null) {
							t = 1;
							map.put(key, 1);
						} else {
							t = map.get(key) + 1;
							map.put(key, t);
						}
						maxHorizen = Math.max(maxHorizen, t);
					}
					result = Math.max(result, 2 + common + maxHorizen);
				}
			}
		}
		return result;
	}
}
