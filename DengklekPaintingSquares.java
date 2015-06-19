import java.util.*;

public class DengklekPaintingSquares {
  final int MOD = 1000000007;
  public int numSolutions(int N, int M) {
    // For the key, use 3 bits to represent each cell, 0 (no color), 1 (with color,  0 neighbor),
    // 2 (with color, 1 neighbor), 3 (with color, 2 neighbors), 4 (with color, 3 neighbors),
    // 5 (with color, 4 neighbors).
    HashMap<Integer, Integer> dp = new HashMap<Integer, Integer>();
    dp.put(0, 1);
    for (int i = 0; i < N; ++i) {
      for (int j = 0; j < M; ++j) {
        HashMap<Integer, Integer> nDp = new HashMap<Integer, Integer>();
        for (Integer key : dp.keySet()) {
          int leftCell = getCell(key, j - 1);
          int upCell = getCell(key, j);
          int count = dp.get(key);
          if (upCell != 0) {
            // This is the last chance to fulfill upCell.
            if (upCell == 1 || upCell == 3) {
              // Don't paint this cell.
              int newKey = setCell(key, j, 0);
              addValue(nDp, newKey, count);
            } else {
              // Paint this cell.
              int current = 2 + (leftCell == 0 ? 0 : 1);
              int newKey = setCell(key, j, current);
              if (leftCell != 0) {
                newKey = setCell(newKey, j - 1, leftCell + 1);
              }
              addValue(nDp, newKey, count);
            }
          } else {
            // Don't paint this cell.
            int newKey = setCell(key, j, 0);
            addValue(nDp, newKey, count);
            // Paint this cell.
            if (leftCell == 0) {
              newKey = setCell(key, j, 1);
            } else {
              newKey = setCell(key, j - 1, leftCell + 1);
              newKey = setCell(newKey, j, 2);
            }
            addValue(nDp, newKey, count);
          }
        }
        dp = nDp;
      }
    }
    int result = 0;
    for (Integer key : dp.keySet()) {
      boolean valid = true;
      for (int i = 0; i < M; ++i) {
        int current = getCell(key, i);
        if (current == 2 || current == 4) {
          valid = false;
          break;
        }
      }
      if (valid) {
        result = (result + dp.get(key)) % MOD;
      }
    }
    return result;
  }

  int getCell(int key, int pos) {
    if (pos < 0) {
      return 0;
    }
    return (key >> (pos * 3)) & 7;
  }

  int setCell(int key, int pos, int value) {
    int shift = pos * 3;
    return (key & ~(7 << shift)) | (value << shift);
  }

  void addValue(HashMap<Integer, Integer> dp, int key, int add) {
    Integer value = dp.get(key);
    if (value == null) {
      dp.put(key, add);
    } else {
      dp.put(key, (value + add) % MOD);
    }
  }
}
