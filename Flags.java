public class Flags {
	public long numStripes(String numFlags, String[] forbidden) {
		int n = forbidden.length;
		int[] forbiddenBits = new int[n];
		for (int i = 0; i < n; ++i) {
			String[] strs = forbidden[i].split(" ");
			forbiddenBits[i] |= 1 << i;
			for (int j = 0; j < strs.length; ++j) {
				forbiddenBits[i] |= 1 << Integer.parseInt(strs[j]);
			}
		}
		long[] dp = new long[n];
		for (int i = 0; i < n; ++i) {
			dp[i] = 1;
		}
		long flagRequirement = Long.parseLong(numFlags);
		long flagSum = n;
		long length = 1;
		if (flagSum < flagRequirement) {
			// Test for the special pair situations.
			int pairCount = 0;
			boolean allPairs = true;
			for (int i = 0; i < n; ++i) {
				if (forbiddenBits[i] == (1 << n) - 1) {
					continue;
				}
				int otherCount = 0;
				int other = -1;
				for (int j = 0; j < n; ++j) {
					if ((forbiddenBits[i] & (1 << j)) == 0) {
						otherCount++;
						other = j;
					}
				}
				if (otherCount != 1) {
					allPairs = false;
					break;
				}
				if (other > i) {
					pairCount++;
				}
			}
			if (allPairs) {
				length = 1 + (flagRequirement - n) / (pairCount * 2);
				if ((flagRequirement - n) % (pairCount * 2) != 0) {
					length++;
				}
				return length;
			}
			
			while (true) {
				length++;
				boolean fulfilled = false;
				long[] nextDp = new long[n];
				for (int i = 0; i < n; ++i) {
					for (int j = 0; j < n; ++j) {
						if ((forbiddenBits[i] & (1 << j)) != 0) {
							continue;
						}
						nextDp[j] += dp[i];
						if (nextDp[j] >= flagRequirement) {
							fulfilled = true;
						}
					}
				}
				for (int i = 0; i < n; ++i) {
					flagSum += nextDp[i];
					if (flagSum >= flagRequirement || nextDp[i] >= flagRequirement) {
						fulfilled = true;
						break;
					}
				}
				if (fulfilled) {
					break;
				}
				dp = nextDp;
			}
		}
		return length;
	}
}
