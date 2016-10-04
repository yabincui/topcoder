import java.util.*;
public class Workshop {
	public int pictureFrames(int[] pieces) {
		Arrays.sort(pieces);
		int count = 0;
		for (int i = 0; i < pieces.length; ++i) {
			for (int j = i + 1; j < pieces.length; ++j) {
				int k = bsearch(pieces, j, pieces.length - 1, pieces[i] + pieces[j]);
				count += k - j;
			}
		}
		return count;
	}
	
	private int bsearch(int[] pieces, int low, int high, int target) {
		while (low + 1 < high) {
			int mid = (low + high) / 2;
			if (pieces[mid] < target) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}
		if (high > low && pieces[high] < target) {
			return high;
		}
		return low;
	}
}
