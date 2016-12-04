public class LongestRun {
	public int runLength(String[] collection) {
		int n = collection.length;
		boolean[] used = new boolean[n];
		int[] frontLength = new int[n];
		int[] backLength = new int[n];
		int[] middleLength = new int[n];
		int result = 0;
		for (char c = 'A'; c <= 'Z'; ++c) {
			int pureLength = 0;
			for (int i = 0; i < n; ++i) {
				boolean stopFront = false;
				int frontSize = 0;
				int backSize = 0;
				int maxMiddleSize = 0;
				int middleSize = 0;
				for (int j = 0; j < collection[i].length(); ++j) {
					if (collection[i].charAt(j) == c) {
						if (!stopFront) {
							frontSize++;
						}
						backSize++;
						middleSize++;
						maxMiddleSize = Math.max(maxMiddleSize, middleSize);
					} else {
						backSize = 0;
						stopFront = true;
						middleSize = 0;
					}
				}
				if (frontSize == collection[i].length()) {
					pureLength += frontSize;
					used[i] = true;
				} else {
					used[i] = false;
					frontLength[i] = frontSize;
					backLength[i] = backSize;
					middleLength[i] = maxMiddleSize;
				}
			}
			int maxSum = 0;
			for (int i = 0; i < n; ++i) {
				if (used[i]) continue;
				maxSum = Math.max(maxSum, frontLength[i]);
				maxSum = Math.max(maxSum, backLength[i]);
				for (int j = 0; j < n; ++j) {
					if (used[j] || j == i) continue;
					maxSum = Math.max(maxSum, backLength[i] + frontLength[j]);
				}
			}
			maxSum += pureLength;
			for (int i = 0; i < n; ++i) {
				if (!used[i]) {
					maxSum = Math.max(middleLength[i], maxSum);
				}
			}
			/*
			if (c == 'C') {
				System.out.printf("backLength[0] = %d, frontLength[1] = %d\n",
					backLength[0], frontLength[1]);
				System.out.printf("c = %c, maxSum = %d, pureLength = %d\n", c, maxSum, pureLength);
			}
			*/
			result = Math.max(result, maxSum);
		}
		return result;
	}
	
	public static void main(String[] args) {
		LongestRun obj = new LongestRun();
		int result = obj.runLength(new String[]
		{"ABAA", "BAB", "CAAA", "A", "CAAAAAAAAD", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A", "A"});
		System.out.printf("result = %d\n", result);

	}
}
