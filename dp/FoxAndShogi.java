import java.util.*;

public class FoxAndShogi {
  final long MOD = 1000000007;
  public int differentOutcomes(String[] board) {
    int n = board.length;
    long result = 1;
    for (int col = 0; col < n; ++col) {
      ArrayList<Character> charList = new ArrayList<Character>();
      ArrayList<Integer> originList = new ArrayList<Integer>();
      for (int row = 0; row < n; ++row) {
        if (board[row].charAt(col) != '.') {
          charList.add(board[row].charAt(col));
          originList.add(row);
        }
      }
      int m = charList.size();
      // dp[i][j] means how many ways to put charList(0..j - 1) at row 0..i-1.
      long[][] dp = new long[n + 1][m + 1];
      dp[0][0] = 1;
      for (int i = 1; i <= n; ++i) {
        dp[i][0] = 1;
        for (int j = 1; j <= m; ++j) {
          dp[i][j] = dp[i - 1][j];
          // can we put charList[j - 1] at row i-1 ?
          if ((charList.get(j - 1) == 'D' && i - 1 >= originList.get(j - 1)) ||
              (charList.get(j - 1) == 'U' && i - 1 <= originList.get(j - 1))) {
            dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % MOD;
          }
        }
      }
      result = (result * dp[n][m]) % MOD;
    }
    return (int)result;
  }
}
