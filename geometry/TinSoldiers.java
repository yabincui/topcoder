import java.util.*;

public class TinSoldiers {
	
	private int toState(int[] s) {
		int n = s.length;
		boolean[] used = new boolean[n];
		int state = 0;
		for (int i = 0; i < n; ++i) {
			int minId = -1;
			for (int j = 0; j < n; ++j) {
				if (!used[j] && (minId == -1 || s[minId] > s[j])) {
					minId = j;
				}
			}
			used[minId] = true;
			state = (state << 3) | s[minId];
		}
		return state;
	}
	
	private HashMap<Integer, Long> symmetryStateMap;
	private HashMap<Integer, Long> unSymmetryStateMap;
	
	public int lineUps(int[] rankCounts) {
		symmetryStateMap = new HashMap<Integer, Long>();
		unSymmetryStateMap = new HashMap<Integer, Long>();
		long count1 = getUnSymmetryCount(rankCounts);
		long count2 = getSymmetryCount(rankCounts);
		long result = count1 / 2 + count2;
		return (int)result;
	}
	
	private long getUnSymmetryCount(int[] s) {
		int state = toState(s);
		Long value = unSymmetryStateMap.get(state);
		if (value != null) {
			return value;
		}
		long result = 0;
		int typeCount = 0;
		for (int i = 0; i < s.length; ++i) {
			if (s[i] != 0) {
				typeCount++;
			}
		}
		if (typeCount <= 1) {
			result = 0;
		} else {
			for (int i = 0; i < s.length; ++i) {
				for (int j = 0; j < s.length; ++j) {
					if (s[i] == 0 || s[j] == 0 || (i == j && s[j] == 1)) {
						continue;
					}
					s[i]--;
					s[j]--;
					result += getUnSymmetryCount(s);
					if (i != j) {
						result += getSymmetryCount(s);
					}
					s[i]++;
					s[j]++;
				}
			}
		}
		unSymmetryStateMap.put(state, result);
		System.out.printf("unSymmetryStateMap [%d, %d, %d] = > %d\n",
				s[0], s[1], s[2], result);
		return result;
	}
	
	private long getSymmetryCount(int[] s) {
		int state = toState(s);
		Long value = symmetryStateMap.get(state);
		if (value != null) {
			return value;
		}
		long result = 0;
		int sum = 0;
		int oddCount = 0;
		for (int i = 0; i < s.length; ++i) {
			sum += s[i];
			if (s[i] % 2 == 1) {
				oddCount++;
			}
		}
		if (oddCount > 1) {
			result = 0;
		} else if (sum == 1 || sum == 0) {
			result = 1;
		} else {
			for (int i = 0; i < s.length; ++i) {
				if (s[i] >= 2) {
					s[i] -= 2;
					result += getSymmetryCount(s);
					s[i] += 2;
				}
			}
		}
		symmetryStateMap.put(state, result);
		return result;
	}
	
	public static void main(String[] args) {
		TinSoldiers obj = new TinSoldiers();
		int result = obj.lineUps(new int[]{2, 2, 1});
		System.out.printf("result = %d\n", result);
	}
}