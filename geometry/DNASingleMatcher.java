public class DNASingleMatcher {
	public int longestMatch(String sequence1, String sequence2) {
		int m = sequence1.length();
		int n = sequence2.length();
		int[][] matrix = new int[m+1][n+1];
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				if (sequence1.charAt(i-1) == sequence2.charAt(j-1)) {
					matrix[i][j] = matrix[i-1][j-1]+1;
				} else {
					matrix[i][j] = 0;
				}
			}
		}
		int result = 0;
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				result = Math.max(result, matrix[i][j]);
			}
		}
		return result;
	}
}
