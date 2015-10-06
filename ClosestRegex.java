public class ClosestRegex {
	public String closestString(String text, String regex) {
		int minLength = 0;
		int starCount = 0;
		for (int i = 0; i < regex.length(); ++i) {
			if (regex.charAt(i) != '*') {
				if (i == regex.length() - 1 || regex.charAt(i + 1) != '*') {
					minLength++;
				}
			} else {
				starCount++;
			}
		}
		if (starCount == 0 && text.length() != regex.length()) {
			return "";
		}
		if (minLength > text.length()) {
			return "";
		}
		
		int n = regex.length() - starCount;
		char[] pattern = new char[n];
		boolean[] star = new boolean[n];
		for (int i = 0, j = 0; i < regex.length(); ++i) {
			if (regex.charAt(i) != '*') {
				pattern[j] = regex.charAt(i);
				if (i + 1 < regex.length() && regex.charAt(i + 1) == '*') {
					star[j] = true;
				}
				++j;
			}
		}
		
		int m = text.length();
		// change[i][j] is the minimum change to let text[0..i] match pattern[0..j].
		int[][] change = new int[m + 1][n + 1];
		int[][] prev = new int[m + 1][n + 1];
		String[][] str = new String[m + 1][n + 1];
		
		for (int i = 0; i <= m; ++i) {
			for (int j = 0; j <= n; ++j) {
				change[i][j] = -1;
			}
		}
		
		change[0][0] = 0;
		str[0][0] = "";
		for (int j = 1; j <= n; ++j) {
			if (star[j] == true) {
				change[0][j] = 0;
				prev[0][j] = 0 * (n + 1) + j - 1;
				str[0][j] = "";
			} else {
				break;
			}
		}
		
		for (int i = 1; i <= m; ++i) {
			for (int j = 1; j <= n; ++j) {
				// Use a star match more, From [i-1][j].
				if (change[i-1][j] != -1 && star[j-1]) {
					int value = change[i-1][j] + (pattern[j-1] == text.charAt(i-1) ? 0 : 1);
					if (change[i][j] == -1 || change[i][j] > value || (change[i][j] == value && str[i][j].compareTo(str[i-1][j] + pattern[j-1]) > 0)) {
						change[i][j] = value;
						prev[i][j] = (i-1) * (m + 1) + j;
						str[i][j] = str[i-1][j] + pattern[j-1];
					}
				}
				// Use a star match nothing, From [i][j-1].
				if (change[i][j-1] != -1 && star[j-1]) {
					int value = change[i][j-1];
					if (change[i][j] == -1 || change[i][j] > value || (change[i][j] == value && str[i][j].compareTo(str[i][j-1]) > 0)) {
						change[i][j] = value;
						prev[i][j] = i * (m + 1) + (j - 1);
						str[i][j] = str[i][j-1];
					}
				}
				// Match current, From [i-1][j-1].
				if (change[i-1][j-1] != -1) {
					int value = change[i-1][j-1] + (pattern[j-1] == text.charAt(i - 1) ? 0 : 1);
					if (change[i][j] == -1 || change[i][j] > value || (change[i][j] == value && str[i][j].compareTo(str[i-1][j-1] + pattern[j-1]) > 0)) {
						change[i][j] = value;
						prev[i][j] = (i - 1) * (m + 1) + (j - 1);
						str[i][j] = str[i-1][j-1] + pattern[j-1];
					}
				}
			}
		}
		return str[m][n];
	}
}