import java.util.*;

public class CheeseRolling {
  public long[] waysToWin(String[] wins) {
    int n = wins.length;
    int mask = (1 << n) - 1;
    long[][] dp = new long[n][mask + 1];
    for (int i = 0; i < n; ++i) {
      dp[i][1 << i] = 1;
    }
    for (int size = 2; size <= n; size *= 2) {
      for (int i = 0; i < n; ++i) {
        for (int j = 0; j <= mask; ++j) {
          if (((j >> i) & 1) == 0 || getBits(j) != size) {
            continue;
          }
          ArrayList<Integer> subsets = enumSubsets((j & ~(1 << i)), size - 1, size / 2 - 1);
          for (Integer subset : subsets) {
            int subA = subset | (1 << i);
            int subB = (j & ~subA);
            for (int bit = 0; bit < n; ++bit) {
              if (((subB >> bit) & 1) != 0 && wins[i].charAt(bit) == 'Y') {
                dp[i][j] += dp[i][subA] * dp[bit][subB] * 2;
              }
            }
          }
        }
      }
    }
    long[] result = new long[n];
    for (int i = 0; i < n; ++i) {
      result[i] = dp[i][mask];
    }
    return result;
  }
  
  int getBits(int set) {
    int result = 0;
    for (int i = 0; i < 32; ++i) {
      if (((set >> i) & 1) != 0) {
        result++;
      }
    }
    return result;
  }
  
  ArrayList<Integer> enumSubsets(int set, int lastSize, int needSize) {
    ArrayList<Integer> result = new ArrayList<Integer>();
    enumSubsets(set, lastSize, needSize, 0, 0, result);
    return result;
  }
  
  void enumSubsets(int set, int lastSize, int needSize, int pos, int subset, ArrayList<Integer> subsets) {
    if (needSize == 0) {
      subsets.add(subset);
      return;
    }
    for (int i = pos; i < 32 && lastSize >= needSize; ++i) {
      if ((set & (1 << i)) == 0) {
        continue;
      }
      enumSubsets(set, lastSize - 1, needSize - 1, i + 1, subset | (1 << i), subsets);
      lastSize--;
    }
  }
}