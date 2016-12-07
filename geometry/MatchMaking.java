import java.util.*;

public class MatchMaking {

	public String makeMatch(String[] namesWomen, String[] answersWomen, String[] namesMen,
						    String[] answersMen, String queryWomen) {
		int n = namesWomen.length;
		boolean[] validWomen = new boolean[n];
		boolean[] validMen = new boolean[n];
		Arrays.fill(validWomen, true);
		Arrays.fill(validMen, true);
		while (true) {
			int selWomen = -1;
			for (int i = 0; i < n; ++i) {
				if (validWomen[i] && (selWomen == -1
					|| namesWomen[i].compareTo(namesWomen[selWomen]) < 0)) {
					selWomen = i;
				}
			}
			int selMen = -1;
			int bestMatch = -1;
			for (int i = 0; i < n; ++i) {
				if (!validMen[i]) {
					continue;
				}
				int match = getMatch(answersWomen[selWomen], answersMen[i]);
				if (match > bestMatch || (match == bestMatch &&
					namesMen[i].compareTo(namesMen[selMen]) < 0)) {
					selMen = i;
					bestMatch = match;
				}
			}
			if (namesWomen[selWomen].equals(queryWomen)) {
				return namesMen[selMen];
			}
			validWomen[selWomen] = false;
			validMen[selMen] = false;
		}
	}
	
	int getMatch(String a, String b) {
		int result = 0;
		for (int i = 0; i < a.length(); ++i) {
			if (a.charAt(i) == b.charAt(i)) {
				result++;
			}
		}
		return result;
	}
}