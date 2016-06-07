public class FourStrings {
	public int shortestLength(String a, String b, String c, String d) {
		String[] strs = new String[4];
		strs[0] = a;
		strs[1] = b;
		strs[2] = c;
		strs[3] = d;
		boolean[] used = new boolean[4];
		int result = shortestCombine(0, used, strs, "");
		return result;
	}
	
	int shortestCombine(int count, boolean[] used, String[] strs, String combined) {
		if (count == used.length) {
			return combined.length();
		}
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < used.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				int same = sameCharacters(combined, strs[i]);
				String newCombined = combined + strs[i].substring(same);
				int len = shortestCombine(count + 1, used, strs, newCombined);
				result = Math.min(result, len);
				used[i] = false;
			} 
		}
		return result;
	}
	
	int sameCharacters(String back, String front) {
		if (back.length() >= front.length()) {
			for (int start = 0; start <= back.length() - front.length(); ++start) {
				int i = start;
				int j = 0;
				for(; j < front.length(); ++j, ++i) {
					if (back.charAt(i) != front.charAt(j)) {
						break;
					}
				}
				if (j == front.length()) {
					return front.length();
				}
			}
		}
		int len = Math.min(back.length(), front.length());
		for (; len > 0; --len) {
			int i = back.length() - len;
			int j = 0;
			for (; j < len; ++j, ++i) {
				if (back.charAt(i) != front.charAt(j)) {
					break;
				}
			}
			if (j == len) {
				return len;
			}
		}
		return len;
	}
}
