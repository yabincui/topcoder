public class DigitMultiples {
	public int getLongest(String single, String multiple) {
		int maxLength = 0;
		for (int i = 0; i < single.length(); ++i) {
			for (int j = 0; j < multiple.length(); ++j) {
				int factor = -1; // -1 means any.
				int k = 0;
				for (; i + k < single.length() && j + k < multiple.length(); ++k) {
					int a = single.charAt(i + k) - '0';
					int b = multiple.charAt(j + k) - '0';
					if (a == 0 && b != 0) {
						break;
					}
					if (a == 0 && b == 0) {
						continue;
					}
					if (b % a != 0) {
						break;
					}
					int needFactor = b / a;
					if (factor == -1) {
						factor = needFactor;
					} else if (factor != needFactor) {
						break;
					}
				}
				maxLength = Math.max(maxLength, k);
				if (maxLength == k) {
					//System.out.printf("i = %d, j = %d, match k = %d max = %d\n", i, j, k, maxLength);
				}
			}
		}
		return maxLength;
	}
}
