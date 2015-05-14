import java.util.*;

public class RouteIntersection {
  public String isValid(int N, int[] coords, String moves) {
    boolean result = true;
    ArrayList<HashMap<Integer, Integer>> positions = new ArrayList<HashMap<Integer, Integer>>();
    positions.add(new HashMap<Integer, Integer>());
    for (int i = 1; i <= coords.length; ++i) {
      int dimen = coords[i - 1];
      Integer value = positions.get(i - 1).get(dimen);
      if (value == null) {
        value = 0;
      }
      if (moves.charAt(i - 1) == '+') {
        value++;
      } else {
        value--;
      }
      positions.add((HashMap<Integer, Integer>)positions.get(i - 1).clone());
      if (value != 0) {
        positions.get(i).put(dimen, value);
      } else {
        positions.get(i).remove(dimen);
      }

      for (int j = i - 1; j >= 0; --j) {
        Set<Integer> keys1 = positions.get(i).keySet();
        Set<Integer> keys2 = positions.get(j).keySet();
        if (keys1.equals(keys2) == false) {
          continue;
        }
        boolean theSame = true;
        for (Integer key : keys1) {
          Integer value1 = positions.get(i).get(key);
          Integer value2 = positions.get(j).get(key);
          if (value1 != value2) {
            theSame = false;
            break;
          }
        }
        if (theSame) {
          result = false;
          break;
        }
      }
      if (!result) {
        break;
      }
    }
    return result ? "VALID" : "NOT VALID";
  }
}
