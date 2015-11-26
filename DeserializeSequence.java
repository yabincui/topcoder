import java.util.*;

public class DeserializeSequence {

	public int howMany(String str) {
		int n = str.length();
		// dp[i] is the number of ways forming str[0..i-1] with specified maxValue.
		ArrayList<HashMap<Integer, Integer>> dp = new ArrayList<HashMap<Integer, Integer>>();		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(0, 1);
		dp.add(map);
		for (int i = 1; i <= n; ++i) {
			int value = 0;
			int p = 1;
			int level = 0;
			HashMap<Integer, Integer> curMap = new HashMap<Integer, Integer>();
			for (int start = i - 1; start >= 0; --start) {
				level += 1;
				if (level > 7 && str.charAt(start) != '0') {
					break;
				}
				value += (str.charAt(start) - '0') * p;
				p *= 10;
				if (value == 0) {
					continue;
				}
				if (value > 1000000) {
					break;
				}
				map = dp.get(start);
				Integer count = curMap.get(value);
				if (count == null) {
					count = 0;
				}
				for (Integer key : map.keySet()) {
					if (key <= value) {
						count += map.get(key);
					}
				}
				curMap.put(value, count);
			}
			dp.add(curMap);
		}
		int result = 0;
		map = dp.get(n);
		for (Integer key : map.keySet()) {
			result += map.get(key);
		}
		return result;
	}
}
