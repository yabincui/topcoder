public class VampireTree {
	public int maxDistance(int[] num) {
		int sum = 0;
		for (int i = 0; i < num.length; ++i) {
			sum += num[i];
		}
		if (sum - num.length + 1 != num.length - 1) {
			return -1;
		}
		int nonOneCount = 0;
		for (int i = 0; i < num.length; ++i) {
			if (num[i] != 1) {
				nonOneCount++;
			}
		}
		return nonOneCount + 1;
	}
}
