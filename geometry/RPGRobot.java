import java.util.*;

public class RPGRobot {
	String[] map;
	int[][] stateBoard;
	HashMap<Integer, Integer> unknownStateMap;
	int rows;
	int cols;
	
	final static int STATE_N = 1;
	final static int STATE_S = 2;
	final static int STATE_W = 4;
	final static int STATE_E = 8;

	void buildStateMap(String[] map) {
		this.map = map;
		stateBoard = new int[rows][cols];
		for (int r = 1; r < rows; r += 2) {
			for (int c = 1; c < cols; c += 2) {
				// collect openness in different directions.
				int state = 0;
				if (map[r-1].charAt(c) == ' ') {
					state |= STATE_N;
				}
				if (map[r+1].charAt(c) == ' ') {
					state |= STATE_S;
				}
				if (map[r].charAt(c-1) == ' ') {
					state |= STATE_W;
				}
				if (map[r].charAt(c+1) == ' ') {
					state |= STATE_E;
				}
				stateBoard[r][c] = state;
			}
		}
		unknownStateMap = new HashMap<Integer, Integer>();
	}
	
	boolean isStateMatch(int x, int y, int state) {
		if (x >= 0 && x < cols && y >= 0 && y < rows) {
			return stateBoard[y][x] == state;
		}
		int pos = (y << 16) | (x & 0xffff);
		Integer value = unknownStateMap.get(pos);
		if (value == null) {
			// test Four edges.
			if (x == -1 && (y >= 0 && y < rows)) {
				boolean canEast = (map[y].charAt(0) == ' ');
				boolean goEast = ((state & STATE_E) == STATE_E);
				if (canEast != goEast) {
					return false;
				}
			} else if (x == cols && (y >= 0 && y < rows)) {
				boolean canWest = (map[y].charAt(cols - 1) == ' ');
				boolean goWest = ((state & STATE_W) == STATE_W);
				if (canWest != goWest) {
					return false;
				}
			} else if (y == -1 && (x >= 0 && x < cols)) {
				boolean canSouth = (map[0].charAt(x) == ' ');
				boolean goSouth = ((state & STATE_S) == STATE_S);
				if (canSouth != goSouth) {
					return false;
				}	
			} else if (y == rows && (x >= 0 && x < cols)) {
				boolean canNorth = (map[rows - 1].charAt(x) ==  ' ');
				boolean goNorth = ((state & STATE_N) == STATE_N);
				if (canNorth != goNorth) {
					return false;
				}
			}
			unknownStateMap.put(pos, state);
			return true;
		} else {
			return value == state;
		}
	}
	
	class Move {
		int state;
		int move;
		Move(int state, int move) {
			this.state = state;
			this.move = move;
		}
	};
	ArrayList<Move> moves;
	
	void parseMoveMents(String movements) {
		moves = new ArrayList<Move>();
		Move cur = new Move(0, 0);
		final int WAIT_DIR = 0;
		final int WAIT_MOVE = 1;
		int curWait = WAIT_DIR;
		for (int i = 0; i < movements.length(); ++i) {
			char p = movements.charAt(i);
			int dir = 0;
			if (p == 'N') {
				dir = STATE_N;
			} else if (p == 'S') {
				dir = STATE_S;
			} else if (p == 'W') {
				dir = STATE_W;
			} else if (p == 'E') {
				dir = STATE_E;
			} else if (p == ',') {
				if (curWait == WAIT_DIR) {
					curWait = WAIT_MOVE;
				}
				continue;
			} else if (p == ' ') {
				continue;
			}
			if (curWait == WAIT_DIR) {
				cur.state |= dir;
			} else if (curWait == WAIT_MOVE) {
				cur.move = dir;
				moves.add(cur);
				cur = new Move(0, 0);
				curWait = WAIT_DIR;
			}
		}
		moves.add(cur);
	}
	
	boolean testPos(int x, int y) {
		for (int i = 0; i < moves.size(); ++i) {
			Move move = moves.get(i);
			int expectedState = move.state;
			if (!isStateMatch(x, y, expectedState)) {
				return false;
			}
			int dir = move.move;
			if (dir == STATE_N) {
				y -= 2;
			} else if (dir == STATE_S) {
				y += 2;
			} else if (dir == STATE_W) {
				x -= 2;
			} else if (dir == STATE_E) {
				x += 2;
			}
		}
		return true;
	}

	public String[] where(String[] map, String movements) {
		rows = map.length;
		cols = map[0].length();
		buildStateMap(map);
		parseMoveMents(movements);
		// try all positions.
		ArrayList<String> result = new ArrayList<String>();
		for (int c = 1; c < cols; c += 2) {
			for (int r = 1; r < rows; r += 2) {
				unknownStateMap.clear();
				if (testPos(c, r)) {
					System.out.printf("result add (x=%d, y=%d)\n", c, r);
					result.add(""+c+","+r);
				}
			}
		}
		String[] ret = new String[result.size()];
		for (int i = 0; i < result.size(); ++i) {
			ret[i] = result.get(i);
		}
		return ret;
	}
}
