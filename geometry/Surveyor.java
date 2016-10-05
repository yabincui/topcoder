public class DiamondLogo {
	public String[] logo(int size, char background) {
		int n = 2 * size - 1;
		String[] result = new String[n];
		int leftPlace = size - 1;
		int rightPlace = size - 1;
		for (int r = 0; r < n; ++r) {
			StringBuilder b = new StringBuilder();
			for (int j = 0; j < leftPlace; ++j) {
				b.append(background);
			}
			b.append('X');
			for (int j = leftPlace + 1; j < rightPlace; ++j) {
				b.append(' ');
			}
			if (leftPlace != rightPlace) {
				b.append('X');
			}
			for (int j = rightPlace + 1; j < n; ++j) {
				b.append(background);
			}
			if (r < size - 1) {
				--leftPlace;
				++rightPlace;
			} else {
				++leftPlace;
				--rightPlace;
			}
			result[r] = b.toString();
		}
		/*
		for (int r = 0; r < n; ++r) {
			System.out.printf("result[%d] = %s\n", r, result[r]);
		}
		*/
		return result;
	}
}
