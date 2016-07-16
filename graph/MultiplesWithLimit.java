import java.util.*;

public class MultiplesWithLimit {

	public String minMultiples(int N, int[] forbiddenDigits) {
		// N = a, a = a1 * 10 + a2.
		// b = b1 * 10 + b2.
		// c = c1 * 10 + c2.
		boolean[] forbidden = new boolean[10];
		for (int d : forbiddenDigits) {
			forbidden[d] = true;
		}
		int a = N;
		int digitZeroInA = 0;
		while (a % 10 == 0) {
			a /= 10;
			if (forbidden[0]) {
				return "IMPOSSIBLE";
			}
			digitZeroInA++;
		}
		int a2 = a % 10;
		int MAX_C = 10000;
		int[] dpCurDigit = new int[MAX_C + 1];
		int[] dpPrev = new int[MAX_C + 1];
		int[] dpLevel = new int[MAX_C + 1];
		for (int i = 0; i <= MAX_C; ++i) {
			dpLevel[i] = Integer.MAX_VALUE;
			dpPrev[i] = -1;
		}
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(0);
		for (int level = 0; !queue.isEmpty(); ++level) {
			int size = queue.size();
			while (--size >= 0) {
				int c = queue.poll();
				int c2 = c % 10;
				int startB = (level == 0 ? 1 : 0);
				for (int b2 = startB; b2 <= 9; ++b2) {
					int add = b2 * a2 + c2;
					int digitZero = add % 10;
					if (forbidden[digitZero]) {
						continue;
					}
					int nc = (a * b2 + c) / 10;
					if (dpLevel[nc] == level && dpCurDigit[nc] > digitZero) {
						dpCurDigit[nc] = digitZero;
						dpPrev[nc] = c;
						//System.out.printf("update pair c = %d, digitZero = %d, prev_c = %d\n", nc, digitZero, c);
					} else if (dpLevel[nc] > level) {
						dpCurDigit[nc] = digitZero;
						dpPrev[nc] = c;
						dpLevel[nc] = level;
						queue.add(nc);
						//System.out.printf("add pair c = %d, digitZero = %d, prev_c = %d\n", nc, digitZero, c);
					}
				}
			}
			if (dpLevel[0] == level) {
				break;
			}
		}
		if (dpLevel[0] == Integer.MAX_VALUE) {
			return "IMPOSSIBLE";
		}
		int[] result = new int[dpLevel[0] + 1 + digitZeroInA];
		int cur = 0;
		for (int i = 0; i <= dpLevel[0]; ++i) {
			result[i] = dpCurDigit[cur];
			cur = dpPrev[cur];
		}
		for (int i = dpLevel[0] + 1; i < result.length; ++i) {
			result[i] = 0;
		}
		if (result.length < 9) {
			int ret = 0;
			for (int i = 0; i < result.length; ++i) {
				ret = ret * 10 + result[i];
			}
			return "" + ret;
		}
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 3; ++i) {
			s.append((char)('0' + result[i]));
		}
		s.append("...");
		for (int i = result.length - 3; i < result.length; ++i) {
			s.append((char)('0' + result[i]));
		}
		s.append("(" + result.length + " digits)");
		return s.toString();
	}
}
