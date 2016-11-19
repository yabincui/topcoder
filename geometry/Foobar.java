public class Foobar {
	String[] dic = {
		"heck", "gosh", "dang", "shucks", "fooey", "snafu", "fubar",
	};

	// len(text) * len(dic) * len(text) * len(code)
	// 50 * 7 * 50 * 50	
	public String censor(String plain, String code, String text) {
		int n = text.length();
		boolean[] forbidden = new boolean[n];
		for (int start = 0; start < n; ++start) {
			if (text.charAt(start) == ' ') {
				continue;
			}
			for (String word : dic) {
				int match = 0;
				int textIndex = start;
				for (; textIndex < n && match < word.length(); ++textIndex) {
					if (text.charAt(textIndex) == ' ') {
						continue;
					}
					boolean found = (text.charAt(textIndex) == word.charAt(match));
					for (int i = 0; i < code.length() && !found; ++i) {
						if (code.charAt(i) == text.charAt(textIndex) &&
							plain.charAt(i) == word.charAt(match)) {
							found = true;
						}
					}
					if (!found) {
						break;
					}
					match++;
				}
				if (match == word.length()) {
					for (int i = start; i < textIndex; ++i) {
						forbidden[i] = true;
					}
				}
			}
		}
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < n; ++i) {
			if (forbidden[i]) {
				b.append('*');
			} else {
				b.append(text.charAt(i));
			}
		}
		return b.toString();
	}
}
