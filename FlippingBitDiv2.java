public class FlippingBitDiv2 {
  public int getmin(String[] S, int M) {
    int n = 0;
    for (int i = 0; i < S.length; ++i) {
      n += S[i].length();
    }
    boolean[] s = new boolean[n];
    for (int i = 0, j = 0; i < S.length; ++i) {
      for (int t = 0; t < S[i].length(); ++t) {
        if (S[i].charAt(t) == '0') {
          s[j++] = false;
        } else {
          s[j++] = true;
        }
      }
    }
    int zeroSum = 0;
    for (int i = 0; i < n; ++i) {
      if (!s[i]) {
        zeroSum++;
      }
    }
    int maxStep = Math.min(zeroSum, 1 + n - zeroSum);
    int areaNum = n / M;
    int[] areaZeroes = new int[areaNum];
    for (int i = 0; i < areaNum; ++i) {
      for (int j = i * M; j < (i + 1) * M; ++j) {
        if (!s[j]) {
          areaZeroes[i]++;
        }
      }
    }

    // dpFrontZero[i][j] means for area 0..i-1, using j front flips, the minimum left zero count.
    int[][] dpFrontZero = new int[areaNum + 1][maxStep + 1];
    // dpFrontOne[i][j] means for area 0..i-1, using j front flips, the minimum left one count.
    int[][] dpFrontOne = new int[areaNum + 1][maxStep + 1];
    
    for (int i = 1; i <= areaNum; ++i) {
      dpFrontZero[i][0] = dpFrontZero[i - 1][0] + areaZeroes[i - 1];
      dpFrontOne[i][0] = dpFrontOne[i - 1][0] + M - areaZeroes[i - 1];
    }
    for (int i = 1; i <= areaNum; ++i) {
      for (int j = 1; j <= maxStep; ++j) {
        // Don't flip front i * M.
        dpFrontZero[i][j] = dpFrontZero[i - 1][j] + areaZeroes[i - 1];
        // Flip front i * M.
        int temp = dpFrontOne[i - 1][j - 1] + M - areaZeroes[i - 1];
        dpFrontZero[i][j] = Math.min(dpFrontZero[i][j], temp);
        
        // Don't flip front i * M.
        dpFrontOne[i][j] = dpFrontOne[i - 1][j] + M - areaZeroes[i - 1];
        // Flip front i * M.
        temp = dpFrontZero[i - 1][j - 1] + areaZeroes[i - 1];
        dpFrontOne[i][j] = Math.min(dpFrontOne[i][j], temp);
      }
    }

    // dpBackZero[i][j] means for area i..areaNum-1, using j back flips, the minimum left zero count.
    int[][] dpBackZero = new int[areaNum + 1][maxStep + 1];
    // dpBackOne[i][j] means for area i..areaNum-1, using j back flips, the minimum left one count.
    int[][] dpBackOne = new int[areaNum + 1][maxStep + 1];

    for (int i = areaNum - 1; i >= 0; --i) {
      dpBackZero[i][0] = dpBackZero[i + 1][0] + areaZeroes[i];
      dpBackOne[i][0] = dpBackOne[i + 1][0] + M - areaZeroes[i];
    }
    for (int i = areaNum - 1; i >= 0; --i) {
      for (int j = 1; j <= maxStep; ++j) {
        // Don't flip back (areaNum - i) * M.
        dpBackZero[i][j] = dpBackZero[i + 1][j] + areaZeroes[i];
        dpBackOne[i][j] = dpBackOne[i + 1][j] + M - areaZeroes[i];
        // Flip back (areaNum - i) * M.
        dpBackZero[i][j] = Math.min(dpBackZero[i][j], dpBackOne[i + 1][j - 1] + M - areaZeroes[i]);
        dpBackOne[i][j] = Math.min(dpBackOne[i][j], dpBackZero[i + 1][j - 1] + areaZeroes[i]);
      }
    }

    int result = maxStep;
    for (int split = 0; split <= areaNum; ++split) {
      // 0..split - 1 use front flip, split..areaNum-1 use back flip.
      int frontCount = 0;
      if (split >= 1) {
        frontCount = Integer.MAX_VALUE;
        for (int i = 0; i <= maxStep; ++i) {
          int temp = dpFrontZero[split][i] + i;
          frontCount = Math.min(frontCount, temp);
        }
      }
      int backCount = 0;
      if (split < areaNum) {
        backCount = Integer.MAX_VALUE;
        for (int i = 0; i <= maxStep; ++i) {
          int temp = dpBackZero[split][i] + i;
          backCount = Math.min(backCount, temp);
        }
      }
      result = Math.min(result, frontCount + backCount);
    }
    return result;
  }
}
