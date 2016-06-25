public class PriorityQueue {
	public int findAnnoyance(String S, int[] a) {
		int n = S.length();
		int prev_sum = 0;
		int result = 0;
		for (int i = 0; i < n; ++i) {
			if (S.charAt(i) == 'b') {
				result += prev_sum;
			}
			prev_sum += a[i];
		}
		return result;
	}
}
