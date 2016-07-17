import java.util.*;

public class SlimeXResidentSlime {

	class Slime {
		int r;
		int c;
		int power;
		Slime(int r, int c, int power) {
			this.r = r;
			this.c = c;
			this.power = power;
		}
	}
	
	int[] dr = new int[]{-1, 1, 0, 0};
	int[] dc = new int[]{0, 0, -1, 1};
	
	class State {
		int curPos;
		ArrayList<Integer> slimeStates = new ArrayList<Integer>();	
		
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof State)) return false;
			State other = (State) o;
			if (curPos != other.curPos) return false;
			return slimeStates.equals(other.slimeStates);
		}
		
		public int hashCode() {
			return curPos * slimeStates.hashCode();
		}
	}

	public int exterminate(String[] field) {
		int m = field.length;
		int n = field[0].length();
		int[][] slimeIndex = new int[m][n];
		ArrayList<Slime> slimes = new ArrayList<Slime>();
		int startR = -1;
		int startC = -1;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (field[i].charAt(j) >= '1' && field[i].charAt(j) <= '9') {
					slimeIndex[i][j] = slimes.size();
					slimes.add(new Slime(i, j, field[i].charAt(j) - '0'));
				} else {
					slimeIndex[i][j] = -1;
				}
				if (field[i].charAt(j) == '$') {
					startR = i;
					startC = j;
				}
			}
		}
		if (slimes.size() > 9) {
			return -1;
		}
		HashSet<State> set = new HashSet<State>();
		State initState = new State();
		initState.curPos = startR * n + startC;
		initState.slimeStates = new ArrayList<Integer>();
		for (int i = 0; i < slimes.size(); ++i) {
			initState.slimeStates.add(0);
		}
		set.add(initState);
		Queue<State> queue = new ArrayDeque<State>();
		queue.add(initState);
		for (int step = 1; !queue.isEmpty(); ++step) {
			int size = queue.size();
			while (size-- > 0) {
				State state = queue.poll();
				int r = state.curPos / n;
				int c = state.curPos % n;
				for (int d = 0; d < 4; ++d) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr < 0 || nr >= m || nc < 0 || nc >= n || field[nr].charAt(nc) == '#') {
						continue;
					}
					State next = new State();
					next.curPos = nr * n + nc;
					next.slimeStates = new ArrayList<Integer>();
					for (int i = 0; i < slimes.size(); ++i) {
						next.slimeStates.add(state.slimeStates.get(i) > 0 ?
											 state.slimeStates.get(i) - 1 : 0);
					}
					int index = slimeIndex[nr][nc];
					if (index != -1) {
						next.slimeStates.set(index, slimes.get(index).power);
					}
					boolean success = true;
					for (int i = 0; i < slimes.size(); ++i) {
						if (next.slimeStates.get(i) == 0) {
							success = false;
							break;
						}
					}
					if (success) return step;
					if (!set.contains(next)) {
						set.add(next);
						queue.add(next);
					}
				}
			}
		}
		return -1;
	}
}
