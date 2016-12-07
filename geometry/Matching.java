public class Matching {
	static final String[] shapes = new String[]{"CIRCLE", "SQUIGGLE", "DIAMOND"};
	static final String[] colors = new String[]{"RED", "BLUE", "GREEN"};
	static final String[] shadings = new String[]{"SOLID", "STRIPED", "EMPTY"};
	static final String[] nums = new String[]{"ONE", "TWO", "THREE"};
	
	public String[] findMatch(String[] first, String[] second) {
		String[] result = new String[4];
		String[][] s = new String[4][3];
		s[0] = shapes;
		s[1] = colors;
		s[2] = shadings;
		s[3] = nums;
		for (int i = 0; i < 4; ++i) {
			if (first[i].equals(second[i])) {
				result[i] = first[i];
			} else {
				for (int j = 0; j < 3; ++j) {
					if (!s[i][j].equals(first[i]) && !s[i][j].equals(second[i])) {
						result[i] = s[i][j];
						break;
					}
				}
			}
		}
		return result;
	}
}