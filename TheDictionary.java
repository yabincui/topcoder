public class TheDictionary {
	public String find(int n, int m, int k) {
		// ways[i][j] is how many ways to permute i occurrences of 'a' and j occurrences of 'z'.
		long[][] ways = new long[n + 1][m + 1];
		for (int i = 0; i <= m; ++i) {
			ways[0][i] = 1;
		}
		for (int i = 1; i <= n; ++i) {
			ways[i][0] = 1;
			for (int j = 1; j <= m; ++j) {
				ways[i][j] = ways[i-1][j] + ways[i][j-1];
			}
		}
		if (k > ways[n][m]) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		
		int aCount = n;
		int zCount = m;
		for (int count = 0; count < n + m; ++count) {
			if (aCount == 0) {
				builder.append('z');
				zCount--;
			} else if (zCount == 0) {
				builder.append('a');
				aCount--;
			} else if (k > ways[aCount-1][zCount]) {
				builder.append('z');
				k -= ways[aCount-1][zCount];
				zCount--;
			} else {
				builder.append('a');
				aCount--;
			}
		}
		return builder.toString();
	}
}