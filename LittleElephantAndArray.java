import java.util.*;

public class LittleElephantAndArray {
  final long REM = 1000000007L; 

  public int getNumber(long A, int N) {
    TreeMap<Long, Long> map1 = new TreeMap<Long, Long>();
    TreeMap<Long, Long> map2;
    
    map1.put(0L, 1L);
    for (int i = 0; i <= N; ++i) {
      map2 = new TreeMap<Long, Long>();
      long value = A + i;
      int mask = getMask(value);
      addValue(value, mask, map1, map2);
      enumerateValue(value, mask, 0, 1, map1, map2);

      sumMap(map2);
      map1 = map2;
    }
    long result = map1.lastEntry().getValue();
    return (int)result;
  }

  void enumerateValue(long value, int mask, int bit, long level,
      TreeMap<Long, Long> map1, TreeMap<Long, Long> map2) {
    if ((mask & (1 << bit)) == 0) {
      return;
    }

    // remove bit at current level.
    long newValue = value / (level * 10) * level + value % level;
    int newMask = mask & ~(1 << bit);

    addValue(newValue, newMask, map1, map2);

    enumerateValue(newValue, newMask, bit + 1, level, map1, map2);
    enumerateValue(value, mask, bit + 1, level * 10, map1, map2);
  }

  void addValue(long value, int mask, TreeMap<Long, Long> map1, TreeMap<Long, Long> map2) {
    if (mask == 0) {
      return;
    }
    Long prevKey = map1.floorKey(value);
    Map.Entry<Long, Long> prevEntry = map1.floorEntry(value);
    if (prevEntry == null) {
      return;
    }
    Long count = map2.get(value);
    if (count == null) {
      count = prevEntry.getValue();
    } else {
      count = (count + prevEntry.getValue()) % REM;
    }
    map2.put(value, count);
  }

  int getMask(long value) {
    int mask = 0;
    for (int bit = 0; value != 0; ++bit) {
      mask |= (1 << bit);
      value /= 10;
    }
    return mask;
  }

  void sumMap(TreeMap<Long, Long> map) {
    Long sum = 0L;
    Long key = map.firstKey();
    while (key != null) {
      Long count = map.get(key);
      sum = (sum + count) % REM;
      map.put(key, sum);
      key = map.higherKey(key);
    }
  }
}
