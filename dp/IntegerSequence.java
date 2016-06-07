public class IntegerSequence {
	public int maxSubsequence(int[] numbers) {
		int n = numbers.length;
		int[] increaseDp = new int[n];
		for (int i = 0; i < n; ++i) {
			increaseDp[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (numbers[j] < numbers[i]) {
					increaseDp[i] = Math.max(increaseDp[i], increaseDp[j] + 1);
				}
			}
		}
		int[] decreaseDp = new int[n];
		for (int i = n - 1; i >= 0; --i) {
			decreaseDp[i] = 1;
			for (int j = n - 1; j > i; --j) {
				if (numbers[j] < numbers[i]) {
					decreaseDp[i] = Math.max(decreaseDp[i], decreaseDp[j] + 1);
				}
			}
		}
		int result = n;
		for (int i = 0; i < n; ++i) {
			int reserve = increaseDp[i] + decreaseDp[i];
			int removed = n + 1 - reserve;
			result = Math.min(result, removed);
		}
		return result;
	}
}
