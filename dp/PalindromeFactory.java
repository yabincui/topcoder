import java.util.*;

public class PalindromeFactory {
	public int getEditDistance(String initial) {
		char[] s = initial.toCharArray();
		int minDist = getDist(s);
		for (int i = 0; i < s.length; ++i) {
			for (int j = i + 1; j < s.length; ++j) {
				char c = s[i];
				s[i] = s[j];
				s[j] = c;
				int dist = getDist(s) + 1;
				minDist = Math.min(dist, minDist);
				c = s[i];
				s[i] = s[j];
				s[j] = c;
			}
		}
		return minDist;
	}
	
	int getDist(char[] s) {
		int n = s.length;
		int[][] dist = new int[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				dist[i][j] = -1;
			}
		}
		for (int i = 0; i < n; ++i) {
			dist[i][i] = 0;
		}
		for (int len = 2; len <= n; ++len) {
			for (int i = 0; i <= n - len; ++i) {
				int j = i + len - 1;
				int min = Math.min(dist[i][j-1], dist[i+1][j]) + 1;
				if (s[i] == s[j]) {
					min = Math.min(min, (len == 2 ? 0 : dist[i+1][j-1]));
				} else {
					min = Math.min(min,  (len == 2 ? 0 : dist[i+1][j-1]) + 1);
				}
				dist[i][j] = min;
			}
		}
		return dist[0][n - 1];
	}
}