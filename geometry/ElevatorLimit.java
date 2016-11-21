public class ElevatorLimit {
	public int[] getRange(int[] enter, int[] exit, int physicalLimit) {
		int minStart = 0;
		int cur = 0;
		int n = enter.length;
		for (int i = 0; i < n; ++i) {
			if (cur < exit[i]) {
				minStart += exit[i] - cur;
				cur = exit[i];
				if (cur > physicalLimit) {
					return new int[]{};
				}
			}
			cur -= exit[i];
			cur += enter[i];
			if (cur > physicalLimit) {
				return new int[]{};
			}
		}
		// check if minStart is valid
		cur = minStart;
		if (cur > physicalLimit) {
			return new int[]{};
		}
		for (int i = 0; i < n; ++i) {
			cur -= exit[i];
			cur += enter[i];
			if (cur > physicalLimit) {
				return new int[]{};
			}
		}
		
		int maxStart = physicalLimit;
		cur = physicalLimit;
		for (int i = 0; i < n; ++i) {
			cur -= exit[i];
			cur += enter[i];
			if (cur > physicalLimit) {
				maxStart -= cur - physicalLimit;
				cur = physicalLimit;
			}
		}
		System.out.printf("minStart = %d, maxStart = %d\n", minStart, maxStart);
		return new int[]{minStart, maxStart};
	}
}
