import java.util.*;

public class ChooseTheBestOne {
  public int countNumber(int N) {
    ArrayList<Integer> s = new ArrayList<Integer>();
    for (int i = 0; i < N; ++i) {
      s.add(i + 1);
    }
    int curPos = 0;
    for (int turn = 1; turn < N; ++turn) {
      int n = s.size();
      int threshold = (turn * turn) % n * turn % n;
      curPos = (curPos + threshold - 1 + n) % n;
      s.remove(curPos);
      if (curPos == s.size()) {
        curPos = 0;
      }
    }
    return s.get(0);
  }
}
