public class LittleElephantAndXor {
  final boolean debug = false;

  public long getNumber(int A, int B, int C) {
    long result = 0;
    long a = A;
    long b = B;
    long c = C;

    // dp[i] means the count fulfill requirements using (A & ((1 << i) - 1)), (B & ((1 << i) - 1)),
    // (C & ((1 << i) - 1)).
    long[] dp = new long[32];

    // dpA[i] means the count fulfill requirements using (A & ((1 << i) - 1)), ((1 << i) - 1),
    // (C & ((1 << i) - 1)).
    long[] dpA = new long[32];

    // dpB[i] means the count fulfill requirements using ((1 << i) - 1), (B & ((1 << i) - 1)),
    // (C & ((1 << i) - 1)).
    long[] dpB = new long[32];

    // dpNoLimit[i] means the count fulfill requirement using ((1 << i) - 1), ((1 << i) - 1),
    // (C & ((1 << i) - 1)).
    long[] dpNoLimit = new long[32];

    int cbit = (int)(c & 1);
    int abit = (int)(a & 1);
    int bbit = (int)(b & 1);

    if (cbit == 0) {
      if (abit == 0) {
        dp[0] = 1;  // 0-0
      } else {
        dp[0] = (bbit == 0) ? 1 : 2;  // 0-0 : 0-0, 1-1
      }
    } else {
      if (abit == 0) {
        dp[0] = (bbit == 0) ? 1 : 2;
      } else {
        dp[0] = (bbit == 0) ? 2 : 4;
      }
    }

    if (cbit == 0) {
      if (abit == 0) {
        dpA[0] = 1;
      } else {
        dpA[0] = 2;
      }
    } else {
      if (abit == 0) {
        dpA[0] = 2;
      } else {
        dpA[0] = 4;
      }
    }

    if (cbit == 0) {
      if (bbit == 0) {
        dpB[0] = 1;
      } else {
        dpB[0] = 2;
      }
    } else {
      if (bbit == 0) {
        dpB[0] = 2;
      } else {
        dpB[0] = 4;
      }
    }

    if (cbit == 0) {
      dpNoLimit[0] = 2;
    } else {
      dpNoLimit[0] = 4;
    }
    
    if (debug) {
      long[] verify = getNumberVerifyBit(A, B, C, 0);
      System.out.printf("dp[%d] = %d, verify[0] = %d\n", 0, dp[0], verify[0]);
      System.out.printf("dpA[%d] = %d, verify[1] = %d\n", 0, dpA[0], verify[1]);
      System.out.printf("dpB[%d] = %d, verify[2] = %d\n", 0, dpB[0], verify[2]);
      System.out.printf("dpNoLimit[%d] = %d, verify[3] = %d\n", 0, dpNoLimit[0], verify[3]);
    }

    int maxBit = 0;
    while ((A >= (1L << (maxBit + 1))) || (B >= (1L << (maxBit + 1)))) {
      ++maxBit;
    }
    
    for (int i = 1; i <= maxBit; ++i) {
      abit = (int)(a >> i) & 1;
      bbit = (int)(b >> i) & 1;
      cbit = (int)(c >> i) & 1;
      long limit = ((1L << i) - 1);
      long arem = a & limit;
      long brem = b & limit;

      // dp[i].
      if (cbit == 0) {
        if (abit == 0) {
          if (bbit == 0) {
            dp[i] = dp[i - 1];
          } else {
            // b should use zero, so it can use whatever value below.
            dp[i] = dpA[i - 1];
          }
        } else {
          // cbit = 0, abit = 1
          if (bbit == 0) {
            dp[i] = dpB[i - 1];
          } else {
            dp[i] = dp[i - 1] + dpNoLimit[i - 1];
          }
        }
      } else {
        if (abit == 0) {
          // cbit = 1, abit = 0.
          if (bbit == 0) {
            dp[i] = (arem + 1) * (brem + 1);
          } else {
            dp[i] = dp[i - 1] + (arem + 1) * (limit + 1);
          }
        } else {
          // cbit = 1, abit = 1.
          if (bbit == 0) {
            dp[i] = dp[i - 1] + (brem + 1) * (limit + 1);
          } else {
            dp[i] = (arem + 1) * (brem + 1) + dpA[i - 1] + dpB[i - 1] + (limit + 1) * (limit + 1);
          }
        }
      }
      
      // dpA[i].
      if (cbit == 0) {
        if (abit == 0) {
          dpA[i] = dpA[i - 1];
        } else {
          dpA[i] = dpA[i - 1] + dpNoLimit[i - 1];
        }
      } else {
        if (abit == 0) {
          dpA[i] = dpA[i - 1] + (arem + 1) * (limit + 1);
        } else {
          dpA[i] = (arem + 1) * (limit + 1) + dpA[i - 1] + dpNoLimit[i - 1] + (limit + 1) * (limit + 1);
        }
      }

      // dpB[i].
      if (cbit == 0) {
        if (bbit == 0) {
          dpB[i] = dpB[i - 1];
        } else {
          dpB[i] = dpB[i - 1] + dpNoLimit[i - 1];
        }
      } else {
        if (bbit == 0) {
          dpB[i] = dpB[i - 1] + (brem + 1) * (limit + 1);
        } else {
          dpB[i] = (brem + 1) * (limit + 1) + dpB[i - 1] + dpNoLimit[i - 1] + (limit + 1) * (limit + 1);
        }
      }

      // dpNoLimit[i].
      if (cbit == 0) {
        dpNoLimit[i] = dpNoLimit[i - 1] + dpNoLimit[i - 1];
      } else {
        dpNoLimit[i] = (limit + 1) * (limit + 1) + dpNoLimit[i - 1] + dpNoLimit[i - 1] + (limit + 1) * (limit + 1);
      }

      if (debug) {
        long[] verify = getNumberVerifyBit(A, B, C, i);
        System.out.printf("dp[%d] = %d, verify[0] = %d\n", i, dp[i], verify[0]);
        System.out.printf("dpA[%d] = %d, verify[1] = %d\n", i, dpA[i], verify[1]);
        System.out.printf("dpB[%d] = %d, verify[2] = %d\n", i, dpB[i], verify[2]);
        System.out.printf("dpNoLimit[%d] = %d, verify[3] = %d\n", i, dpNoLimit[i], verify[3]);
      }
    }

    return dp[maxBit];
  }

  long getNumberVerify(long A, long B, long C) {
    long count = 0;
    for (int i = 0; i <= A; ++i) {
      for (int j = 0; j <= B; ++j) {
        if ((i ^ j) <= C) {
          ++count;
        }
      }
    }
    return count;
  }

  long[] getNumberVerifyBit(int A, int B, int C, int bit) {
    long[] result = new long[4];
    long limit = (1L << (bit + 1)) - 1; 
    result[0] = getNumberVerify(A & limit, B & limit, C & limit);
    result[1] = getNumberVerify(A & limit, limit, C & limit);
    result[2] = getNumberVerify(limit, B & limit, C & limit);
    result[3] = getNumberVerify(limit, limit, C & limit);
    return result;
  }
}
