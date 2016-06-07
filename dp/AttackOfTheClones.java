public class AttackOfTheClones {
	public int count(int[] firstWeek, int[] lastWeek) {
		int maxPrev = 0;
		int n = firstWeek.length;
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j <= i; ++j) {
				if (lastWeek[j] == firstWeek[i]) {
					int prev = i - j;
					maxPrev = Math.max(maxPrev, prev);
				}
			}
		}
		return 1 + maxPrev;
	}
}
