public class WordParts {
	public int partCount(String original, String compound) {
		int n = compound.length();
		// dp[i] is the min parts to partition compounds[0..i-1].
		int[] dp = new int[n+1];
		for (int i = 1; i <= n; ++i) {
			dp[i] = -1;
		}
		for (int i = 1; i <= n; ++i) {
			int end = i-1;
			for (int start = end; start >= 0; --start) {
				if (dp[start] != -1 &&
					isPrefixOrSubfix(compound.substring(start, end+1), original)) {
					if (dp[i] == -1 || dp[i] > dp[start] + 1) {
						dp[i] = dp[start] + 1;
					}
				}
			}
		}
		return dp[n];
	}
	
	private boolean isPrefixOrSubfix(String part, String total) {
		if (part.length() > total.length()) {
			return false;
		}
		boolean isPrefix = true;
		for (int i = 0; i < part.length(); ++i) {
			if (part.charAt(i) != total.charAt(i)) {
				isPrefix = false;
				break;
			}
		}
		if (isPrefix) {
			return true;
		}
		boolean isSubfix = true;
		int add = total.length() - part.length();
		for (int i = 0; i < part.length(); ++i) {
			if (part.charAt(i) != total.charAt(add + i)) {
				isSubfix = false;
				break;
			}
		}
		if (isSubfix) {
			return true;
		}
		return false;
	}
}
