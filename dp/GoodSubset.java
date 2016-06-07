import java.util.*;

public class GoodSubset {
  int numberOfSubsets(int goodValue, int[] d) {
    final int MOD = 1000000007;
    TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
    for (int t = 0; t < d.length; ++t) {
      Integer[] keys = map.keySet().toArray(new Integer[0]);
      for (int i = keys.length - 1; i >= 0; --i) {
        if (keys[i] <= goodValue / d[t] && goodValue / d[t] % keys[i] == 0) {
          int addValue = map.get(keys[i]);
          int new_key = keys[i] * d[t];
          Integer value = map.get(new_key);
          if (value == null) {
            value = 0; 
          }
          map.put(new_key, (value + addValue) % MOD);
        }
      }
      
      Integer value = map.get(d[t]);
      if (value == null) {
        value = 0;
      }
      map.put(d[t], (value + 1) % MOD);
    }
    Integer value = map.get(goodValue);
    if (value == null) {
      value = 0;
    }
    return value;
  }
}
