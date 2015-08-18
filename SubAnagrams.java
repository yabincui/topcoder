public class SubAnagrams {
	public int maximumParts(String[] suppliedWord) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < suppliedWord.length; ++i) {
			builder.append(suppliedWord[i]);
		}
		String s = builder.toString();
		int n = s.length();
		int[][] parts = new int[n][n];
		for (int i = 0; i < n; ++i) {
			parts[i][n-1] = 1;
		}
		for (int start = n - 2; start >= 0; --start) {
			int[] match = new int[26];
			int matchCount = 26;
			match[s.charAt(start) - 'A']++;
			int nextEnd = start;
			for (int end = start; end < n - 1; ++end) {
				int c = s.charAt(end) - 'A';
				match[c] -= 2;
				if (match[c] < 0) {
					matchCount--;
					while (matchCount < 26 && nextEnd < n - 1) {
						nextEnd++;
						c = s.charAt(nextEnd) - 'A';
						match[c]++;
						if (match[c] == 0) {
							matchCount++;
						}
					}
					if (matchCount < 26) {
						break;
					}
				}
				parts[start][end] = parts[end + 1][nextEnd] + 1;
			}
			for (int end = n - 2; end >= start; --end) {
				parts[start][end] = Math.max(parts[start][end], parts[start][end + 1]);
			}
		}
		return parts[0][0];
	}
}