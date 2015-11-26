public class Flush {
	public double size(int[] suites, int number) {
		if (number == 0) {
			return 0.0;
		}
		int total = 0;
		for (int suite : suites) {
			total += suite;
		}
		long[][] C = buildC(total);
		long div = C[total][number];
		long sum = 0;
		for (int flush = 1; flush <= number; ++flush) {
			int fulfillMask = 0;
			for (int i = 0; i < suites.length; ++i) {
				if (suites[i] >= flush) {
					fulfillMask |= 1 << i;
				}
			}
			for (int matchMask = 1; matchMask < 16; ++matchMask) {
				if ((matchMask & ~fulfillMask) != 0) {
					continue;
				}
				long count = recursiveFind(0, number, flush, matchMask, suites, C);
				sum += count * flush;
			}
		}
		return sum * 1.0 / div;
	}
	
	private long recursiveFind(int pos, int lastNumber, int flush, int matchMask, int[] suites, long[][] C) {
		if (pos >= suites.length) {
			return (lastNumber == 0) ? 1 : 0;
		}
		if ((matchMask & (1 << pos)) != 0) {
			if (lastNumber < flush) {
				return 0;
			}
			long next = recursiveFind(pos + 1, lastNumber - flush, flush, matchMask, suites, C);
			long cur = C[suites[pos]][flush];
			return cur * next;
		}
		long sum = 0;
		for (int cur = 0; cur < flush; ++cur) {
			if (lastNumber < cur) {
				break;
			}
			long next = recursiveFind(pos + 1, lastNumber - cur, flush, matchMask, suites, C);
			long curWays = C[suites[pos]][cur];
			sum += curWays * next;
		}
		return sum;
	}
	
	private long[][] buildC(int n) {
		long[][] C = new long[n+1][n+1];
		C[0][0] = 1;
		for (int i = 1; i <= n; ++i) {
			C[i][0] = C[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				C[i][j] = C[i-1][j] + C[i-1][j-1];
			}
		}
		return C;
	}
}
