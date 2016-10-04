public class RepeatString {
	public int minimalModify(String s) {
		int minValue = s.length();
		for (int i = 1; i < s.length(); ++i) {
			String a = s.substring(0, i);
			String b = s.substring(i);
			int modify = matchString(a, b);
			minValue = Math.min(minValue, modify);
		}
		return minValue;
	}
	
	private int matchString(String a, String b) {
		int m = a.length();
		int n = b.length();
		int[][] matrix = new int[m+1][n+1];
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				if (i == 0 && j == 0) {
					continue;
				}
				int cur = Integer.MAX_VALUE;
				if (i > 0) {
					cur = Math.min(matrix[i-1][j] + 1, cur);
				}
				if (j > 0) {
					cur = Math.min(matrix[i][j-1] + 1, cur);
				}
				if (i > 0 && j > 0) {
					if (a.charAt(i-1) == b.charAt(j-1)) {
						cur = Math.min(matrix[i-1][j-1], cur);
					} else {
						cur = Math.min(matrix[i-1][j-1] + 1, cur);
					}
				}
				matrix[i][j] = cur;
			}
		}
		//System.out.printf("matchString(%s, %s) = %d\n", a, b, matrix[m][n]);
		return matrix[m][n];
	}
}
