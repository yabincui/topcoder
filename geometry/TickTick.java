import java.util.*;

public class TickTick {
	class Number {
		int a; // before decimal point
		int b; // after decimal point
		Number(int a, int b) {
			this.a = a;
			this.b = b;
		}
	}
	
	public int count(String[] events) {
		int n = events.length;
		Number[] nums = new Number[n];
		int[] barray = new int[n+1];
		for (int i = 0; i < n; ++i) {
			String[] strs = events[i].split("\\.");
			int a = 0;
			//System.out.printf("events[%d] = %s, strs.length = %d\n", i, events[i], strs.length);
			if (strs[0].length() != 0) {
				a = Integer.parseInt(strs[0]);
			}
			int digits = strs[1].length();
			int b = Integer.parseInt(strs[1]);
			for (int j = digits; j < 8; ++j) {
				b *= 10;
			}
			nums[i] = new Number(a, b);
			barray[i] = b;
		}
		Arrays.sort(barray, 0, n);
		barray[n] = barray[n-1] + 1;
		HashSet<String> result = new HashSet<String>();
		for (int i = 0; i <= n; ++i) {
			StringBuilder sb = new StringBuilder();
			buildString(0, 0, barray[i], nums, sb);
			String s = sb.toString();
			//System.out.printf("b = %d, s = %s\n", barray[i], s);
			if (!result.contains(s)) {
				result.add(s);
			}
		}
		return result.size();
	}
	
	void buildString(int numIndex, int a, int b, Number[] nums, StringBuilder sb) {
		if (numIndex == nums.length) {
			return;
		}
		if (nums[numIndex].a < a || (nums[numIndex].a == a && nums[numIndex].b < b)) {
			sb.append('S');
			buildString(numIndex + 1, a, b, nums, sb);
		} else {
			sb.append('D');
			a = nums[numIndex].a;
			if (b <= nums[numIndex].b) {
				a++;
			}
			buildString(numIndex + 1, a, b, nums, sb);
		}
	}
}

