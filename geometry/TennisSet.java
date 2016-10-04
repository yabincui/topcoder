public class TennisSet {
	public String firstSet(String[] points) {
		String s = String.join("", points);
		int aGame = 0;
		int bGame = 0;
		int aPoint = 0;
		int bPoint = 0;
		boolean aTurn = true;
		for (int i = 0; i < s.length(); ++i) {
			char c = s.charAt(i);
			if (aTurn) {
				if (c == 'S') {
					aPoint++;
				} else {
					bPoint++;
				}
			} else {
				if (c == 'S') {
					bPoint++;
				} else {
					aPoint++;
				}
			}
			// Test who won this game.
			if (aPoint >= 4 && aPoint >= bPoint + 2) {
				aGame++;
				aPoint = bPoint = 0;
				aTurn = !aTurn;
			}
			if (bPoint >= 4 && bPoint >= aPoint + 2) {
				bGame++;
				aPoint = bPoint = 0;
				aTurn = !aTurn;
			}
			if (aGame >= 6 && aGame >= bGame + 2) {
				break;
			}
			if (bGame >= 6 && bGame >= aGame + 2) {
				break;
			}
		}
		return "" + aGame + "-" + bGame;
	}
}
