import java.util.*;

public class NiceOrUgly {
	public String describe(String s) {
		boolean strictNice = true;
		boolean strictUgly = false;
		HashSet<Integer> cur = new HashSet<Integer>();
		cur.add(0);
		for (int i = 0; i < s.length(); ++i) {
			HashSet<Integer> next = new HashSet<Integer>();
			Iterator iterator = cur.iterator();
			while (iterator.hasNext()) {
				Integer curState = (Integer)iterator.next();
				if (s.charAt(i) == '?' || isVowel(s.charAt(i))) {
					int state = curState;
					if (state < 0) {
						state = 0;
					}
					state++;
					if (state < 3) {
						next.add(state);
					} else {
						strictNice = false;
					}
				}
				if (s.charAt(i) == '?' || !isVowel(s.charAt(i))) {
					int state = curState;
					if (state > 0) {
						state = 0;
					}
					state--;
					if (state > -5) {
						next.add(state);
					} else {
						strictNice = false;
					}
				}
			}
			if (next.isEmpty()) {
				strictUgly = true;
				break;
			}
			cur = next;
		}
		if (strictNice) {
			return "NICE";
		}
		return strictUgly ? "UGLY" : "42";
	}
	
	boolean isVowel(char c) {
		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}
