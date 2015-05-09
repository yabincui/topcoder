public class EmoticonsDiv2 {
  public int printSmiles(int smiles) {
    // dp[i][j] means having i smiles in chat, j smiles in clipboard, needs at least dp[i][j] seconds.
    int[][] dp = new int[smiles + 1][smiles + 1];
    for (int i = 0; i <= smiles; ++i) {
      for (int j = 0; j <= smiles; ++j) {
        dp[i][j] = -1;
      }
    }
    dp[1][0] = 0;
    dp[1][1] = 1;
    for (int i = 2; i <= smiles; ++i) {
      // Paste.
      for (int j = 1; j <= i; ++j) {
        if (dp[i - j][j] != -1) {
          dp[i][j] = dp[i - j][j] + 1;
        }
      }
      
      // Copy.
      dp[i][i] = -1;
      for (int j = 1; j < i; ++j) {
        if (dp[i][j] != -1 && (dp[i][i] == -1 || (dp[i][i] > dp[i][j] + 1))) {
          dp[i][i] = dp[i][j] + 1;
        }
      }
    }
    int minValue = Integer.MAX_VALUE;
    for (int i = 0; i <= smiles; ++i) {
      if (dp[smiles][i] != -1 && (dp[smiles][i] < minValue)) {
        minValue = dp[smiles][i];
      }
    }
    return minValue;
  }
}
