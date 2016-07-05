public class GogoXReimuHakurai {
	public int solve(String[] choices) {
		int n = choices.length;
		boolean[] used = new boolean[n];
    boolean[] connectedToZero = new boolean[n];
		used[0] = true;
    connectedToZero[0] = true;
		int result = 0;
		if (choices[0].charAt(n - 1) == 'Y') {
			result++;
		}
		for (int i = 1; i < n; ++i) {
			if (choices[i].charAt(n - 1) == 'Y') {
				int ways = collectWays(i, used, connectedToZero, choices);
        System.out.printf("i = %d, ways = %d\n", i, ways);
				result += ways;
			}
		}
		return result;
	}
	
	int collectWays(int cur, boolean[] used, boolean[] connectedToZero, String[] choices) {
		if (used[cur]) {
			return connectedToZero[cur] ? 1 : 0;
		}
		int result = 0;
		for (int i = cur - 1; i >= 0; --i) {
			if (choices[i].charAt(cur) == 'Y') {
				int ways = collectWays(i, used, connectedToZero, choices);
				result += ways;
			}
		}
		used[cur] = true;
    if (result != 0) {
      connectedToZero[cur] = true;
    }
		return result;
	}

  public static void main(String[] args) {
    GogoXReimuHakurai obj = new GogoXReimuHakurai();
    String[] choices = new String[]
{"NNNNN", "NNYNY", "NNNYY", "NNNNN", "NNNNN"}
    ;
    int ret = obj.solve(choices);
    System.out.printf("ret = %d\n", ret);
  }
}
