import java.util.*;

public class RollingBlock {
	final int VERTICAL = 0;
	final int DOWN = 1;
	final int RIGHT = 2;
	
	int rows;
	int cols;
	
	class State {
		int r;
		int c;
		int state;
		
		State(int r, int c, int state) {
			this.r = r;
			this.c = c;
			this.state = state;
		}
		
		State[] nextStates() {
			State[] states = new State[4];
			if (state == VERTICAL) {
				states[0] = new State(r, c + 1, RIGHT);
				states[1] = new State(r+1, c, DOWN);
				states[2] = new State(r, c - 3, RIGHT);
				states[3] = new State(r - 3, c, DOWN);
			} else if (state == DOWN) {
				states[0] = new State(r - 1, c, VERTICAL);
				states[1] = new State(r + 3, c, VERTICAL);
				states[2] = new State(r, c - 1, DOWN);
				states[3] = new State(r, c + 1, DOWN);
			} else if (state == RIGHT) {
				states[0] = new State(r, c - 1, VERTICAL);
				states[1] = new State(r, c + 3, VERTICAL);
				states[2] = new State(r + 1, c, RIGHT);
				states[3] = new State(r - 1, c, RIGHT);
			}
			return states;
		}
		
		boolean isValid() {
			if (state == VERTICAL) {
				return isValidPoint(r, c);
			} else if (state == DOWN) {
				return isValidPoint(r, c) && isValidPoint(r + 2, c);
			} else if (state == RIGHT) {
				return isValidPoint(r, c) && isValidPoint(r, c + 2);
			}
			return false;
		}
		
		private boolean isValidPoint(int r, int c) {
			return (r >= 0 && r < rows && c >= 0 && c < cols);
		}
	}
	
	public int minMoves(int rows, int cols, int[] start, int[] target) {
		if (start[0] == target[0] && start[1] == target[1]) {
			return 0;
		}
		this.rows = rows;
		this.cols = cols;
		boolean[][][] visited = new boolean[rows][cols][3];
		Queue<State> queue = new ArrayDeque<State>();
		queue.add(new State(start[0], start[1], VERTICAL));
		visited[start[0]][start[1]][VERTICAL] = true;
		int step = 0;
		boolean arrived = false;
		while (!queue.isEmpty()) {
			step++;
			int size = queue.size();
			while (size-- > 0) {
				State cur = queue.poll();
				State[] nextStates = cur.nextStates();
				for (State next : nextStates) {
					if (!next.isValid() || visited[next.r][next.c][next.state]) {
						continue;
					}
					visited[next.r][next.c][next.state] = true;
					if (next.r == target[0] && next.c == target[1] && next.state == VERTICAL) {
						arrived = true;
						break;
					}
					queue.add(next);
				}
				if (arrived) {
					break;
				}
			}
			if (arrived) {
				break;
			}
		}
		if (arrived) {
			return step;
		}
		return -1;
	}
}
