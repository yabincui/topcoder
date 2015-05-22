import java.util.*;

public class MovingRooksDiv2 {
  public String move(int[] Y1, int[] Y2) {
    int state1 = arrayToState(Y1);
    int state2 = arrayToState(Y2);
    HashSet<Integer> set = new HashSet<Integer>();
    ArrayList<Integer> queue = new ArrayList<Integer>();
    set.add(state1);
    queue.add(state1);
    for (int i = 0; i < queue.size(); ++i) {
      int state = queue.get(i);
      if (state == state2) {
        return "Possible";
      }
      int[] s = stateToArray(state, Y1.length);
      for (int j = 0; j < s.length; ++j) {
        for (int k = j + 1; k < s.length; ++k) {
          if (s[j] > s[k]) {
            int temp = s[j];
            s[j] = s[k];
            s[k] = temp;
            int next_state = arrayToState(s);
            if (set.contains(next_state) == false) {
              queue.add(next_state);
              set.add(next_state);
            }
            temp = s[j];
            s[j] = s[k];
            s[k] = temp;
          }
        }
      }
    }
    return "Impossible";
  }

  int arrayToState(int[] Y) {
    int state = 0;
    for (int i = 0; i < Y.length; ++i) {
      state |= (Y[i] << (i * 3));
    }
    return state;
  }

  int[] stateToArray(int state, int n) {
    int[] Y = new int[n];
    for (int i = 0; i < n; ++i) {
      Y[i] = (state & 7);
      state >>= 3;
    }
    return Y;
  }
}
