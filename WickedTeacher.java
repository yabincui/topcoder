import java.math.BigInteger;

public class WickedTeacher {
	public String cheatProbability(String[] numbers, int K) {
		int n = numbers.length;
		int mask = (1 << n) - 1;
		
		int[] rem = new int[n];
		for (int i = 0; i < n; ++i) {
			String s = numbers[i];
			int last = 0;
			for (int j = 0; j < s.length(); ++j) {
				last = last * 10 + s.charAt(j) - '0';
				last %= K;
			}
			rem[i] = last;
		}
		
		int[] tenRem = new int[1000];
		int tenLast = 1 % K;
		for (int i = 0; i < tenRem.length; ++i) {
			tenRem[i] = tenLast;
			tenLast = tenLast * 10 % K;
		}
		
		int[] numberMaskLength = new int[mask + 1];
		for (int i = 0; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) != 0) {
					numberMaskLength[i] = numbers[j].length() + numberMaskLength[i & ~(1 << j)];
					break;
				}
			}
		}
		
		BigInteger[][] ways = new BigInteger[mask + 1][K];
		for (int i = 0; i <= mask; ++i) {
			for (int j = 0; j < K; ++j) {
				ways[i][j] = BigInteger.ZERO;
			}
		}
		for (int i = 0; i < n; ++ i) {
			ways[(1 << i)][rem[i]] = BigInteger.ONE;
		}
		for (int i = 0; i <= mask; ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i & (1 << j)) == 0) {
					continue;
				}
				int lastMask = i & ~(1 << j);
				int addRem = (rem[j] * tenRem[numberMaskLength[lastMask]]) % K;
				for (int k = 0; k < K; ++k) {
					int newRem = (addRem + k) % K;
					ways[i][newRem] = ways[i][newRem].add(ways[lastMask][k]);
				}
			}
		}
		
		BigInteger fulfillCount = ways[mask][0];
		BigInteger totalCount = BigInteger.ZERO;
		for (int i = 0; i < K; ++i) {
			totalCount = totalCount.add(ways[mask][i]);
		}
		
		// Gcd
		BigInteger a = totalCount;
		BigInteger b = fulfillCount;
		while (b.compareTo(BigInteger.ZERO) != 0) {
			BigInteger c = a.remainder(b);
			if (c.compareTo(BigInteger.ZERO) == 0) {
				if (b.compareTo(BigInteger.ZERO) != 0) {
					totalCount = totalCount.divide(b);
					fulfillCount = fulfillCount.divide(b);
				}
				break;
			}
			a = b;
			b = c;
		}
		if (fulfillCount.compareTo(BigInteger.ZERO) == 0) {
			totalCount = BigInteger.ONE;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(fulfillCount.toString());
		builder.append("/");
		builder.append(totalCount.toString());
		return builder.toString();
	}
}