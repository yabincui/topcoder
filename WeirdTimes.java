import java.util.*;

public class WeirdTimes {
	public int[] hourValues(int[] minuteValues, int K) {
		int n = minuteValues.length;
		int hours = 24;
		// ways[i][j] means how many ways to assign i,... when assigning i'th item to hour j.
		long[][] ways = new long[n][hours];
		for (int i = 0; i < hours; ++i) {
			ways[n - 1][i] = 1;
		}
		for (int i = n - 2; i >= 0; --i) {
			for (int j = 0; j < hours; ++j) {
				if (minuteValues[i] < minuteValues[i + 1]) {
					ways[i][j] += ways[i + 1][j];
				}
				for (int k = j + 1; k < hours; ++k) {
					ways[i][j] += ways[i + 1][k];
				}
			}
		}
		
		int totalCount = 0;
		for (int i = 0; i < hours; ++i) {
			totalCount += ways[0][i];
		}
		
		if (totalCount < K) {
			return new int[]{-1};
		}
		int[] result = new int[n];
		int last = K;
		int startHour = 0;
		for (int i = 0; i < n; ++i) {
			for (int j = startHour; j < hours; ++j) {
				if (last > ways[i][j]) {
					last -= ways[i][j];
				} else {
					result[i] = j;
					startHour = j;
					if (i < n - 1 && minuteValues[i] >= minuteValues[i + 1]) {
						startHour++;
					}
					break;
				}
			}
		}
		return result;
	}
}