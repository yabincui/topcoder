import java.util.*;

public class BoardFoldingDiv2 {
  int m;
  int n;
  boolean[][][][] matchLeftRight;
  boolean[][][][] matchUpDown;
  HashSet<Integer> hitSet;
  int sum;

  public int howMany(String[] paper) {
    m = paper.length;
    n = paper[0].length();
    matchLeftRight = new boolean[m][m][n][n];
    matchUpDown = new boolean[m][m][n][n];
    for (int r1 = 0; r1 < m; ++r1) {
      for (int c1 = 0; c1 <= n - 2; ++c1) {
        if (paper[r1].charAt(c1) == paper[r1].charAt(c1 + 1)) {
          matchLeftRight[r1][r1][c1][c1 + 1] = true;
        }
      }
      for (int clen = 4; clen <= n; clen += 2) {
        for (int c1 = 0; c1 <= n - clen; ++c1) {
          int c2 = c1 + clen - 1;
          if (matchLeftRight[r1][r1][c1 + 1][c2 - 1] && paper[r1].charAt(c1) == paper[r1].charAt(c2)) {
            matchLeftRight[r1][r1][c1][c2] = true;
          }
        }
      }
    }
    for (int rlen = 2; rlen <= m; ++rlen) {
      for (int r1 = 0; r1 <= m - rlen; ++r1) {
        int r2 = r1 + rlen - 1;
        for (int c1 = 0; c1 < n; ++c1) {
          for (int c2 = c1 + 1; c2 < n; ++c2) {
            matchLeftRight[r1][r2][c1][c2] = matchLeftRight[r1][r2 - 1][c1][c2] && matchLeftRight[r2][r2][c1][c2];
          }
        }
      }
    }

    for (int c1 = 0; c1 < n; ++c1) {
      for (int r1 = 0; r1 <= m - 2; ++r1) {
        if (paper[r1].charAt(c1) == paper[r1 + 1].charAt(c1)) {
          matchUpDown[r1][r1 + 1][c1][c1] = true;
        }
      }
      for (int rlen = 4; rlen <= m; rlen += 2) {
        for (int r1 = 0; r1 <= m - rlen; ++r1) {
          int r2 = r1 + rlen - 1;
          if (matchUpDown[r1 + 1][r2 - 1][c1][c1] && paper[r1].charAt(c1) == paper[r2].charAt(c1)) {
            matchUpDown[r1][r2][c1][c1] = true;
          }
        }
      }
    }
    for (int clen = 2; clen <= n; ++clen) {
      for (int c1 = 0; c1 <= n - clen; ++c1) {
        int c2 = c1 + clen - 1;
        for (int r1 = 0; r1 < m; ++r1) {
          for (int r2 = r1 + 1; r2 < m; ++r2) {
            matchUpDown[r1][r2][c1][c2] = matchUpDown[r1][r2][c1][c2 - 1] && matchUpDown[r1][r2][c2][c2]; 
          }
        }
      }
    }

    hitSet = new HashSet<Integer>();
    sum = 0;
    enumerate(0, m - 1, 0, n - 1);
    return sum;
  }

  int rangeToState(int startRow, int endRow, int startCol, int endCol) {
    return startRow | (endRow << 8) | (startCol << 16) | (endCol << 24);
  }

  int[] stateToRange(int state) {
    int[] range = new int[4];
    range[0] = state & 0xff;
    range[1] = (state >> 8) & 0xff;
    range[2] = (state >> 16) & 0xff;
    range[3] = (state >> 24) & 0xff;
    return range;
  }

  void enumerate(int startRow, int endRow, int startCol, int endCol) {
    int state = rangeToState(startRow, endRow, startCol, endCol);
    if (hitSet.contains(state)) {
      return;
    }
    hitSet.add(state);
    sum++;

    for (int leftEnd = startCol + 1, remove = 1; leftEnd <= endCol; ++remove, leftEnd += 2) {
      if (matchLeftRight[startRow][endRow][startCol][leftEnd]) {
        enumerate(startRow, endRow, startCol + remove, endCol);
      }
    }
    for (int rightEnd = endCol - 1, remove = 1; rightEnd >= startCol; ++remove, rightEnd -= 2) {
      if (matchLeftRight[startRow][endRow][rightEnd][endCol]) {
        enumerate(startRow, endRow, startCol, endCol - remove);
      }
    }
    for (int upEnd = startRow + 1, remove = 1; upEnd <= endRow; ++remove, upEnd += 2) {
      if (matchUpDown[startRow][upEnd][startCol][endCol]) {
        enumerate(startRow + remove, endRow, startCol, endCol);
      }
    }
    for (int downEnd = endRow - 1, remove = 1; downEnd >= startRow; ++remove, downEnd -= 2) {
      if (matchUpDown[downEnd][endRow][startCol][endCol]) {
        enumerate(startRow, endRow - remove, startCol, endCol);
      }
    }
  }
}
