public class CardCosts {
	public long mincost(int n, int k) {
		if (n == 0) {
			return 0;
		}
		if (k == 1) {
			return n;
		}
		int maxRoundCount = 64;
		long[] rounds = new long[maxRoundCount + 1];
		long[] mul_k = new long[maxRoundCount + 1];
		mul_k[0] = 1;
		for (int i = 1; i <= maxRoundCount; ++i) {
			mul_k[i] = mul_k[i-1] * k;
			if (mul_k[i] >= (long)n * n) {
				maxRoundCount = i - 1;
				break;
			}
		}
		for (int i = 0; i < n; ++i) {
			int minPos = 0;
			long minCost = mul_k[0] * (2 * rounds[0] + 1);
			for (int pos = 1; pos <= maxRoundCount; ++pos) {
				long cost = mul_k[pos] * (2 * rounds[pos] + 1);
				if (cost < minCost) {
					minPos = pos;
					minCost = cost;
				}
			}
			rounds[minPos]++;
		}
		long result = 0;
		for (int i = 0; i <= maxRoundCount; ++i) {
			result += mul_k[i] * (rounds[i] * rounds[i]);
		}
		return result;
	}
}
