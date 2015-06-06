import java.util.*;

public class RandomOption {
  public double getProbability(int keyCount, int[] badLane1, int[] badLane2) {
    HashSet<Integer> adjacentSet = new HashSet<Integer>();
    for (int i = 0; i < badLane1.length; ++i) {
      int value1 = badLane1[i] * keyCount + badLane2[i];
      int value2 = badLane2[i] * keyCount + badLane1[i];
      adjacentSet.add(value1);
      adjacentSet.add(value2);
    }
    int keyBitmask = (1 << keyCount) - 1;
    // dp[i][j] means the remainKey bitmask is i, and prevKey is j, the possibility.
    double[][] dp = new double[keyBitmask + 1][keyCount + 1];
    for (int i = 0; i < keyCount; ++i) {
      dp[0][i] = 1.0;
    }
    for (int bitcount = 1; bitcount <= keyCount; ++bitcount) {
      for (int mask = 0; mask <= keyBitmask; ++mask) {
        if (getBitCount(mask, keyCount) != bitcount) {
          continue;
        }
        for (int bit = 0; bit < keyCount; ++bit) {
          if ((mask & (1 << bit)) != 0) {
            int nextMask = mask & ~(1 << bit);
            for (int key = 0; key <= keyCount; ++key) {
              if ((mask & (1 << key)) == 0) {
                int value = key * keyCount + bit;
                if (adjacentSet.contains(value) == false) {
                  dp[mask][key] += 1.0 / bitcount * dp[nextMask][bit];
                }
              }
            }
          }
        }
      }
    }
    return dp[keyBitmask][keyCount];
  }

  int getBitCount(int mask, int keyCount) {
    int bitcount = 0;
    for (int i = 0; i < keyCount; ++i) {
      if ((mask & (1 << i)) != 0) {
        ++bitcount;
      }
    }
    return bitcount;
  }
}
