public class BitStrings {
	public int maxStrings(String[] list, int numZeroes, int numOnes) {
		int n = list.length;
		int[] zeroCost = new int[n];
		int[] oneCost = new int[n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < list[i].length(); ++j) {
				if (list[i].charAt(j) == '0') {
					zeroCost[i]++;
				} else {
					oneCost[i]++;
				}
			}
		}
		int mask = (1 << n) - 1;
		int[] zeroDp = new int[mask + 1];
		int[] oneDp = new int[mask + 1];
		for (int i = 1; i <= mask; ++i) {
			int bit = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					bit = j;
					break;
				}
			}
			int z = zeroDp[i & ~(1 << bit)];
			int o = oneDp[i & ~(1 << bit)];
			if (z == -1 || o == -1) {
				zeroDp[i] = -1;
				oneDp[i] = -1;
				continue;
			}
			z += zeroCost[bit];
			o += oneCost[bit];
			if (z > numZeroes || o > numOnes) {
				z = -1;
				o = -1;
			}
			zeroDp[i] = z;
			oneDp[i] = o;
		}
		int result = 0;
		for (int i = 1; i <= mask; ++i) {
			if (zeroDp[i] == -1 || oneDp[i] == -1) {
				continue;
			}
			int bits = 0;
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					bits++;
				}
			}
			result = Math.max(result, bits);
		}
		return result;
	}
}
