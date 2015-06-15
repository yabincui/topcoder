public class FoxAndSorting {
  // Wrong answer 1.
  public long get0(long idx) {
    int n = 18;
    int maxSum = 9 * n;
    // dp[i][j] means how many numbers are constructed with 0-i levels, sum is j.
    long[][] dp = new long[n][maxSum+1];
    for (int i = 0; i < n; ++i) {
      dp[i][0] = 1;
    }
    for (int j = 1; j <= 9; ++j) {
      dp[0][j] = 1;
    }
    for (int i = 1; i < n; ++i) {
      int maxJ = 9 * (i + 1);
      for (int j = 1; j <= maxJ; ++j) {
        for (int k = 0; k <= 9 && k <= j; ++k) {
          dp[i][j] += dp[i-1][j-k];
        }
      }
    }
    long result = 0;
    long level = 1;
    for (int i = 1; i < n; ++i) {
      level *= 10;
    }

    int selectedSum = 0;
    for (int j = 0; j <= maxSum; ++j) {
      if (idx <= dp[n-1][j]) {
        selectedSum = j;
        break;
      } else {
        idx -= dp[n-1][j];
      }
    }

    for (int i = n - 2; i >= 0; --i) {
      for (int j = 0; j <= 9 && j <= selectedSum; ++j) {
        int lastSum = selectedSum - j;
        if (idx <= dp[i][lastSum]) {
          selectedSum = lastSum;
          result += j * level;
          break;
        } else {
          idx -= dp[i][lastSum];
        }
      }
      level /= 10;
    }
    result += selectedSum;
    return result;
  }

  // Wrong answer 2.
  public long get1(long idx) {
    int n = 18;
    int maxSum = n * 9;
    // dp[i][j][k] means how many numbers are sum to i, first non-zero digit is j, and the digit
    // is at level k.
    long[][][] dp = new long[maxSum+1][10][n];

    for (int i = 0; i <= 9; ++i) {
      dp[i][i][0] = 1;
    }
    for (int k = 1; k < n; ++k) {
      int maxI = (k + 1) * 9;
      for (int i = 1; i <= maxI; ++i) {
        for (int j = 1; j <= 9 && j <= i; ++j) {
          int prevI = i - j;
          for (int prevJ = 0; prevJ <= 9 && prevJ <= prevI; ++prevJ) {
            for (int prevK = 0; prevK < k; ++prevK) {
              dp[i][j][k] += dp[prevI][prevJ][prevK];
            }
          }
        }
      }
    }
    long result = 0;
    int selectSum = 0;
    int maxK = 0;
    boolean found = false;
    for (int i = 0; i <= maxSum && !found; ++i) {
      int j = 0;
      for (; j <= 9 && j < i && !found; ++j) {
        for (int k = n - 1; k >= 0 && !found; --k) {
          if (idx <= dp[i][j][k]) {
            result += j * pow(10, k);
            selectSum = i - j;
            maxK = k - 1;
            found = true;
          } else {
            idx -= dp[i][j][k];
          }
        }
      }
      if (!found && j <= 9 && j == i) {
        for (int k = 0; k <= n - 1 && !found; ++k) {
          if (idx <= dp[i][j][k]) {
            result += j * pow(10, k);
            selectSum = i - j;
            maxK = k - 1;
            found = true;
          } else {
            idx -= dp[i][j][k];
          }
        }
      }
    }

    while (maxK >= 0) {
      found = false;
      int j = 0;
      for (; j <= 9 && j <= selectSum && !found; ++j) {
        for (int k = 0; k <= maxK && !found; ++k) {
          if (idx <= dp[selectSum][j][k]) {
            result += j * pow(10, k);
            selectSum -= j;
            maxK = k - 1;
            found = true;
          } else {
            idx -= dp[selectSum][j][k];
          }
        }
      }
    }
    return result;
  }

  long pow(int a, int b) {
    long result = 1;
    while (b-- > 0) {
      result *= a;
    }
    return result;
  }

  // Wrong answer 3.
  public long get2(long idx) {
    int n = 18;
    int maxSum = n * 9;

    // dp[i][j][k][t] means how many numbers are sum to i, first non-zero digit is j,
    // the dist to next non-zero bit is k, and the digit is at level t.
    long[][][][] dp = new long[maxSum+1][10][n][n];

    if (idx == 1) {
      return 0;
    }
    idx--;
    
    for (int i = 1; i <= 9; ++i) {
      for (int t = 0; t < n; ++t) {
        dp[i][i][0][t] = 1;
      }
    }
    for (int t = 1; t < n; ++t) {
      int maxI = (t + 1) * 9;
      for (int i = 1; i <= maxI; ++i) {
        for (int j = 1; j <= 9 && j < i; ++j) {
          int prevI = i - j;
          for (int k = 1; k <= t; ++k) {
            int prevT = t - k;
            if (prevI > (prevT + 1) * 9) {
              continue;
            }
            for (int prevJ = 0; prevJ <= 9 && prevJ <= prevI; ++prevJ) {
              for (int prevK = 0; prevK <= prevT; ++prevK) {
                dp[i][j][k][t] += dp[prevI][prevJ][prevK][prevT];
              }
            }
          }
        }
      }
    }
    long result = 0;
    int selectSum = 0;
    int targetT = 0;
    boolean found = false;

    for (int i = 0; i <= maxSum && !found; ++i) {
      for (int j = 0; j <= 9 && j <= i && !found; ++j) {
        for (int k = n - 1; k >= 0 && !found; --k) {
          for (int t = k; t < n && !found; ++t) {
            if (idx <= dp[i][j][k][t]) {
              result += j * pow(10, t);
              selectSum = i - j;
              targetT = t - k;
              found = true;
            } else {
              idx -= dp[i][j][k][t];
            }
          }
        }
      }
    }


    while (selectSum != 0) {
      found = false;
      for (int j = 0; j <= 9 && j <= selectSum && !found; ++j) {
        for (int k = targetT; k >= 0; --k) {
          if (idx <= dp[selectSum][j][k][targetT]) {
            result += j * pow(10, targetT);
            selectSum -= j;
            targetT = targetT - k;
            found = true;
          } else {
            idx -= dp[selectSum][j][k][targetT];
          }
        }
      }
    }
    return result;
  }

  // Right answer.
  public long get(long idx) {
    int n = 18;
    int maxSum = n * 9;

    if (idx == 1) {
      return 0;
    }
    idx--;

    // dp[i][j][k] means how many numbers are sum to i, first non-zero digit is j,
    // and it is at level k.
    long[][][] dp = new long[maxSum+1][10][n];

    for (int i = 1; i <= 9; ++i) {
      for (int k = 0; k < n; ++k) {
        dp[i][i][k] = 1;
      }
    }

    for (int k = 1; k < n; ++k) {
      for (int i = 1; i <= maxSum; ++i) {
        for (int j = 1; j <= 9 && j < i; ++j) {
          int prevI = i - j;
          for (int prevJ = 1; prevJ <= 9 && prevJ <= prevI; ++prevJ) {
            for (int prevK = 0; prevK < k; ++prevK) {
              dp[i][j][k] += dp[prevI][prevJ][prevK];
            }
          }
        }
      }
    }

    int selectSum = 0;
    int lastDigit = 0;
    long result = 0;
    boolean found = false;
    for (int i = 1; i <= maxSum && !found; ++i) {
      for (int j = 1; j <= 9 && j <= i && !found; ++j) {
        long count = 0;
        for (int k = 0; k < n; ++k) {
          count += dp[i][j][k];
        }
        if (idx <= count) {
          selectSum = i - j;
          lastDigit = j;
          result = j;  // first Digit is j.
          found = true;
        } else {
          idx -= count;
        }
      }
    }

    int maxLevel = 17;
    while (selectSum != 0) {
      found = false;
      int maxDist = maxLevel;
      for (int dist = maxDist; dist > 0 && !found; --dist) {
        for (int digit = 1; digit <= 9 && digit <= selectSum && !found; ++digit) {
          long count = 0;
          for (int level = maxLevel - dist; level >= 0; level--) {
            count += dp[selectSum][digit][level];
          }
          if (idx <= count) {
            selectSum -= digit;
            lastDigit = digit;
            result *= pow(10, dist);
            result += digit;
            maxLevel -= dist;
            found = true;
          } else {
            idx -= count;
          }
        }
      }
    }
    for (; idx != 1; --idx) {
      result = result * 10;
    }
    return result;
  }

}
