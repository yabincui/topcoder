import java.util.*;

public class Hexagons {
	public int[] centers(String[] pieces) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		char[][] s = new char[7][6];
		for (int i = 0; i < pieces.length; ++i) {
			boolean[] used = new boolean[7];
			used[i] = true;
			for (int j = 0; j < 6; ++j) {
				s[0][j] = pieces[i].charAt(j);
			}
			if (findValid(1, used, pieces, s, ' ', "" + (i+1))) {
				System.out.printf("%d\n", i+1);
				result.add(i+1);
			}
		}
		int[] ret = new int[result.size()];
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = result.get(i);
		}
		return ret;
	}
	
	boolean findValid(int count, boolean[] used, String[] pieces, char[][] s, char charFromPrev, String trylist) {
		//System.out.printf("try %s\n", trylist);
		if (count == 7) {
			if (charFromPrev != s[1][4]) {
				return false;
			}
			return true;
		}
		int check_place = (count + 2) % 6;
		char middle_need = s[0][count-1];
		for (int i = 0; i < used.length; ++i) {
			if (!used[i]) {
				used[i] = true;
				int shift = getShift(check_place, middle_need, pieces[i]);
				for (int j = 0; j < 6; ++j) {
					s[count][(j + shift) % 6] = pieces[i].charAt(j);
				}
				char nextChar = s[count][(check_place + 5) % 6];
				char prevChar = s[count][(check_place + 1) % 6];
				if (count == 1 || prevChar == charFromPrev) {
					if (findValid(count + 1, used, pieces, s, nextChar, trylist + (i+1))) {
						return true;
					}
				}
				used[i] = false;
			}
		}
		return false;
	}
	
	private int getShift(int check_place, char middle_need, String s) {
		for (int i = 0; i < s.length(); ++i) {
			if (s.charAt(i) == middle_need) {
				int shift;
				if (i <= check_place) {
					shift = check_place - i;
				} else {
					shift = 6 + check_place - i;
				}
				return shift;
			}
		}
		return -1;
	}
}
