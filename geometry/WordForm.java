public class WordForm {
	public String getSequence(String word) {
		StringBuilder sb = new StringBuilder();
		final int None = 0;
		final int Vow = 1;
		final int Cons = 2;
		int state = None;
		word = word.toUpperCase();
		for (int i = 0; i < word.length(); ++i) {
			char c = word.charAt(i);
			if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U') {
				if (state == None || state == Cons) {
					sb.append('V');
					state = Vow;
				}
			} else if (c == 'Y') {
				if (i == 0) {
					sb.append('C');
					state = Cons;
				}
				else if (state == None || state == Cons) {
					sb.append('V');
					state = Vow;
				} else {
					sb.append('C');
					state = Cons;
				}
			} else {
				if (state == None || state == Vow) {
					sb.append('C');
					state = Cons;
				}
			}
		}
		return sb.toString();
	}
}