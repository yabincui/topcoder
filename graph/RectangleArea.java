// This problem can be solved easier by using union set.
import java.util.*;

public class RectangleArea {
	
	class State {
		ArrayList<ArrayList<Boolean>> board;
		
		public int hashCode() {
			return board.hashCode();
		}
		
		public boolean equals(Object o) {
			if (o == null) return false;
			if (!(o instanceof State)) return false;
			return board.equals(((State)o).board);
		}
	}
	
	int m = -1;
	int n = -1;
	
	public int minimumQueries(String[] known) {
		m = known.length;
		n = known[0].length();
		HashSet<State> set = new HashSet<State>();
		Queue<State> queue = new ArrayDeque<State>();
		State initState = new State();
		initState.board = new ArrayList<ArrayList<Boolean>>();
		int yesCount = 0;
		for (int i = 0; i < m; ++i) {
			long value = 0;
			ArrayList<Boolean> s = new ArrayList<Boolean>();
			for (int j = 0; j < n; ++j) {
				if (known[i].charAt(j) == 'Y') {
					s.add(true);
					yesCount++;
				} else {
					s.add(false);
				}
			}
			initState.board.add(s);
		}
		if (m == 1 || n == 1) {
			return m + n - 1 - yesCount;
		}
		if (reasonBoard(initState.board, -1, -1)) {
			return 0;
		}
    System.out.printf("m = %d, n = %d\n", m, n);
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        System.out.printf("%c", initState.board.get(i).get(j) ? 'Y' : 'N');
      }
      System.out.printf("\n");
    }
		set.add(initState);
		queue.add(initState);
		for (int step = 1; ; ++step) {
			int size = queue.size();
			while (size-- > 0) {
				State state = queue.poll();
				boolean used = false;
				for (int i = 0; i < m && !used; ++i) {
					for (int j = 0; j < n; ++j) {
						if (!state.board.get(i).get(j) && isNearTwo(state, i, j)) {
							State next = buildNext(state, i, j);
							if (reasonBoard(next.board, i, j)) {
								return step;
							}
							if (!set.contains(next)) {
								set.add(next);
								queue.add(next);
							}
							used = true;
							break;
						}
					}
				}
				
				for (int i = 0; i < m && !used; ++i) {
					for (int j = 0; j < n; ++j) {
						if (!state.board.get(i).get(j) && !isNearTwo(state, i, j)) {
							State next = buildNext(state, i, j);
							if (reasonBoard(next.board, i, j)) {
								return step;
							}
							if (!set.contains(next)) {
								set.add(next);
								queue.add(next);
							}
							used = true;
							break;
						}
					}
				}
			}
		}
	}
	
	boolean isNearTwo(State state, int i, int j) {
		for (int sr = i - 1; sr <= i; ++sr) {
			for (int sc = j - 1; sc <= j; ++sc) {
				if (sr < 0 || sr + 1 >= m || sc < 0 || sc + 1 >= n) {
					continue;
				}
				int count = 0;
				for (int tr = sr; tr <= sr + 1; ++tr) {
					for (int tc = sc; tc <= sc + 1; ++tc) {
						if (state.board.get(tr).get(tc)) {
							count++;
						}
					}
				}
				if (count == 2) {
					return true;
				}
			}
		}
		return false;
	}
	
	State buildNext(State state, int i, int j) {
		State next = new State();
		next.board = new ArrayList<ArrayList<Boolean>>();
		int m = state.board.size();
		int n = state.board.get(0).size();
		for (int r = 0; r < m; ++r) {
			ArrayList<Boolean> s = new ArrayList<Boolean>();
			for (int c = 0; c < n; ++c) {
				s.add(state.board.get(r).get(c));
			}
			next.board.add(s);
		}
		next.board.get(i).set(j, true);
		return next;
	}
	
	boolean reasonBoard(ArrayList<ArrayList<Boolean>> board,
						int startR, int startC) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		if (startR == -1) {
			for (int i = 0; i < m; ++i) {
				for (int j = 0; j < n; ++j) {
					if (board.get(i).get(j)) {
						queue.add((i << 8) | j);
					}
				}
			}
		} else {
			queue.add((startR << 8) | startC);
		}
		while (!queue.isEmpty()) {
			Integer value = queue.poll();
			int sr = value >> 8;
			int sc = value & 0xff;

      for (int r = 0; r < m; ++r) {
        if (r == sr || !board.get(r).get(sc)) continue;
        for (int c = 0; c < n; ++c) {
          if (board.get(r).get(c) && !board.get(sr).get(c)) {
            board.get(sr).set(c, true);
            queue.add((sr << 8) | c);
          } else if (!board.get(r).get(c) && board.get(sr).get(c)) {
            board.get(r).set(c, true);
            queue.add((r << 8) | c);
          }
        }
      }
      for (int c = 0; c < n; ++c) {
        if (c == sc || !board.get(sr).get(c)) continue;
        for (int r = 0; r < m; ++r) {
          if (board.get(r).get(c) && !board.get(r).get(sc)) {
            board.get(r).set(sc, true);
            queue.add((r << 8) | sc);
          } else if (!board.get(r).get(c) && board.get(r).get(sc)) {
            board.get(r).set(c, true);
            queue.add((r << 8) | c);
          }
        }
      }
		}
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (!board.get(i).get(j)) {
					return false;
				}
			}
		}
		return true;
	}

  public static void main(String[] args) {
    RectangleArea obj = new RectangleArea();
    String[] known = new String[]
{"NNYNNYNNNNYNNYNNYNNNNNYNNNNNNNYNNNYNNNNNYNNNN", "NYNYNNNYYNNYYNYNNYYNNYNYNNYNNNNNNNNYYNNYNNNNY", "NNNNNNNNNNNYYNYNNYNNNYNNNNYYNNNNNNNYYNYYNNNNY", "NYNNNNNNYNNNNNNNNYYYNYNYNNYYNYNNNNNYYNYNNYNNY", "NYNYNNNYYNNYYNYNNYNYNYNNNNYYNYNNNNNYNNYYNYNNN", "YNNNYNNNNNNNNNNNNNNNYNNNNYNNYNNYNNNNNYNNNNYNN", "NNYNNYYNNNYNNYNNYNNNNNYNNNNNNNNNNYYNNNNNYNNNN", "NYNNNNNYYNNYYNYNNYYYNYNYNNNNNYNNNNNYNNYYNYNNY", "NNNYNNNYYNNYYNNNNYYYNYNYNNYYNYNNNNNYYNYYNYNNY", "NYNYNNNNNNNYNNYNNYYYNNNYNNYNNNNNNNNYNNYYNYNNY", "NYNYNNNNYNNNYNNNNNYYNYNYNNNYNYNNNNNNYNYYNYNNY"};

    int ret = obj.minimumQueries(known);
    System.out.printf("ret = %d\n", ret);
  }
}
