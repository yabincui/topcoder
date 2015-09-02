public class HexagonalBattlefieldEasy {
	public int countArrangements(int[] X, int[] Y, int N) {
		// Move (0, 0) to (N-1, N-1).
		boolean[][] map = new boolean[2 * N][2 * N];
		for (int i = N; i < 2 * N; ++i) {
			int y = -(N - 1) + N + i - N;
			int x = - (N - 1) + N;
			for (int j = 0; j < i; ++j) {
				map[x + j][y] = true;
			}
		}
		for (int i = 2 * N - 1; i >= N; --i) {
			int y = N + 2 * N - 1 - i;
			int x = -(N - 1) + N + 2 * N - 1 - i;
			for (int j = 0; j < i; ++j) {
				map[x + j][y] = true;
			}
		}
		
		for (int i = 0; i < X.length; ++i) {
			map[X[i] + N][Y[i] + N] = false;
		}
		
		int mask = (1 << (2 * N)) - 1;
		// bit 1 means occupied, bit 0 means empty. states[i] means the number of ways in state i.
		int[] states = new int[mask + 1];
		states[mask] = 1;
		for (int x = 0; x < 2 * N; ++x) {
			int[] nextStates = new int[mask + 1];
			for (int i = 0; i <= mask; ++i) {
				if (states[i] != 0) {
					buildNextState(i, states[i], map[x], 0, 0, nextStates);
				}
			}
			states = nextStates;
		}
		return states[mask];
	}
	
	void buildNextState(int state, int count, boolean[] s, int k, int nextState, int[] nextStates) {
		if (k == s.length) {
			if ((state & (1 << (k - 1))) != 0) {
				nextStates[nextState] += count;
			}
			return;
		}
		if (s[k] == false) {
			if (k == 0 || (state & (1 << (k - 1))) != 0) {
				buildNextState(state, count, s, k + 1, nextState | (1 << k), nextStates);
			}
			return;
		}
		if ((state & (1 << (k - 1))) == 0) {
			buildNextState((state | ( 1 << (k - 1))), count, s, k + 1,
					nextState | (1 << k), nextStates);
			return;
		}
		if ((state & (1 << k)) == 0) {
			buildNextState((state | (1 << k)), count, s, k + 1,
					nextState | (1 << k), nextStates);
		} else {
			if (k + 1 < s.length && s[k + 1] == true) {
				buildNextState(state, count, s, k + 2, nextState | (3 << k), nextStates);
			}
		}
		
		buildNextState(state, count, s, k + 1, nextState, nextStates);
	}
}