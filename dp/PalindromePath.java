import java.util.*;

public class PalindromePath {
	public int shortestLength(int n, int[] a, int[] b, String c) {
		char[][] map = new char[n][n];
		for (int i = 0; i < a.length; ++i) {
			map[a[i]][b[i]] = map[b[i]][a[i]] = c.charAt(i);
		}
		HashSet<Integer> existSet = new HashSet<Integer>();
		ArrayList<Integer> curValues = new ArrayList<Integer>();
		curValues.add(pairToValue(0, 1));
		existSet.add(pairToValue(0, 1));
		for (int len = 0; !curValues.isEmpty(); len += 2) {
			for (int i = 0; i < curValues.size(); ++i) {
				int[] p = valueToPair(curValues.get(i));
				if (map[p[0]][p[1]] != '\0') {
					return len + 1;
				}
			}
			ArrayList<Integer> nextValues = new ArrayList<Integer>();
			for (int i = 0; i < curValues.size(); ++i) {
				int[] p = valueToPair(curValues.get(i));
				for (int t1 = 0; t1 < n; ++t1) {
					if (map[p[0]][t1] == '\0') {
						continue;
					}
					for (int t2 = 0; t2 < n; ++t2) {
						if (map[p[1]][t2] != map[p[0]][t1]) {
							continue;
						}
						if (existSet.contains(pairToValue(t1, t2))) {
							continue;
						}
						if (t1 == t2) {
							return len + 2;
						}
						existSet.add(pairToValue(t1, t2));
						nextValues.add(pairToValue(t1, t2));
					}
				}
			}
			curValues = nextValues;
		}
		return -1;
	}
	
	int pairToValue(int p1, int p2) {
		if (p1 > p2) {
			int t = p1;
			p1 = p2;
			p2 = t;
		}
		return (p1 << 16) | p2;
	}
	
	int[] valueToPair(int value) {
		int[] p = new int[2];
		p[0] = value >> 16;
		p[1] = value & 0xffff;
		return p;
	}
}
