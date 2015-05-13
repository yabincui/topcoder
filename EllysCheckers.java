import java.util.*;

public class EllysCheckers {
  public String getWinner(String board) {
    HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
    int value = 0;
    for (int i = board.length() - 1; i >= 0; --i) {
      if (board.charAt(i) == 'o') {
        value += (1 << (board.length() - 1 - i));
      }
    }
    map.put(0, false);
    boolean result = getResult(value, map);
    return result ? "YES" : "NO";
  }

  boolean getResult(int value, HashMap<Integer, Boolean> map) {
    value &= ~1;
    Boolean result = map.get(value);
    if (result != null) {
      return result;
    }
    result = false;
    for (int i = 1; value >= (1 << i); ++i) {
      if ((value & (1 << i)) == 0) {
        continue;
      }
      if ((value & (1 << (i - 1))) == 0) {
        int nextValue = value - (1 << (i - 1));
        if (getResult(nextValue, map) == false) {
          result = true;
          break;
        }
      }
      if (i >= 3 && ((value & (7 << (i - 3))) == 6)) {
        int nextValue = value - (7 << (i - 3));
        if (getResult(nextValue, map) == false) {
          result = true;
          break;
        }
      }
    }
    map.put(value, result);
    return result;
  }
}
