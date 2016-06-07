import java.util.*;

public class InfiniteSoup {
	public int[] countRays(String[] g, String[] words, int k) {
		int[][] F = buildTransitionArrays(words);
		ArrayList<Integer> dxList = new ArrayList<Integer>();
		ArrayList<Integer> dyList = new ArrayList<Integer>();
		for (int x = 0; x <= k; ++x) {
			for (int y = 0; y <= k; ++y) {
				if (x == 0 || y == 0) {
					if ((x == 0 && y != 1) || (y == 0 && x != 1)) {
						continue;
					}
				} else if (gcd(x, y) != 1) {
					continue;
				}
				dxList.add(x);
				dyList.add(y);
			}
		}
		int[] result = new int[words.length];
		int testLen = F[0].length + k;
		for (int i = 0; i < dxList.size(); ++i) {
			StringBuilder builder = new StringBuilder();
			int dx = dxList.get(i);
			int dy = dyList.get(i);
			int x = 0;
			int y = 0;
			for (int j = 0; j < testLen; ++j) {
				char c = g[y % g.length].charAt(x % g[0].length());
				builder.append(c);
				x += dx;
				y += dy;
			}
			String text = builder.toString();
			int[] matchLen = new int[words.length];
			for (int j = 0; j < text.length(); ++j) {
				for (k = 0; k < words.length; ++k) {
					int match = matchLen[k];
					if (match == words[k].length()) {
						continue;
					}
					while (match > 0 && text.charAt(j) != words[k].charAt(match)) {
						match = F[k][match];
					}
					if (text.charAt(j) == words[k].charAt(match)) {
						match++;
					}
					matchLen[k] = match;
				}
			}
			for (int j = 0; j < words.length; ++j) {
				if (matchLen[j] == words[j].length()) {
					result[j]++;
				}
			}
		}
		return result;
	}
	
	int gcd(int a, int b) {
		if (a < b) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		int c = a % b;
		while (c != 0) {
			a = b;
			b = c;
			c = a % b;
		}
		return b;
	}
	
	int[][] buildTransitionArrays(String[] words) {
		int wordCount = words.length;
		int maxLength = 0;
		for (String word : words) {
			maxLength = Integer.max(maxLength, word.length());
		}
		int[][] F = new int[wordCount][maxLength + 1];
		for (int i = 0; i < wordCount; ++i) {
			F[i][0] = 0;
			String word = words[i];
			if (word.length() > 0) {
				F[i][1] = 0;
			}
			int matchLen = 0;
			for (int j = 2; j <= word.length(); ++j) {
				while (matchLen > 0 && word.charAt(j - 1) != word.charAt(matchLen)) {
					matchLen = F[i][matchLen];
				}
				if (word.charAt(j - 1) == word.charAt(matchLen)) {
					matchLen++;
				}
				F[i][j] = matchLen;
			}
		}
		return F;
	}
}