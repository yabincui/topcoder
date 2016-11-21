import java.util.*;

public class Removal {
	
	public int finalPos(int n, int k, String[] remove) {
		long cur = k;
		for (int i = remove.length - 1; i >= 0; --i) {
			String[] strs = remove[i].split("-");
			int start = Integer.parseInt(strs[0]);
			int end = Integer.parseInt(strs[1]);
			// add start-end to the range, see what is the change of current position.
			if (cur < start) {
				// nothing happens
			} else {
				cur += end - start + 1;
			}
		}
		if (cur > n) {
			return -1;
		}
		return (int)cur;
	}
}
