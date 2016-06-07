import java.util.*;

public class VampireTreeDiv2 {
  final int INF = 0x1000000;
	int minSamples;
	int wayCountInMinSamples;
	int MOD = 1000000007;
	ArrayList<ArrayList<Integer>> servants;
	ArrayList<ArrayList<Integer>> children;
	int n;
	boolean[] isKeyPoint;
	ArrayList<Integer> keyPoints;
	
	public int countMinSamples(int[] A, int[] B) {
		n = A.length + 1;
		servants = new ArrayList<ArrayList<Integer>>();
		children = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; ++i) {
			servants.add(new ArrayList<Integer>());
			children.add(new ArrayList<Integer>());
		}
		keyPoints = new ArrayList<Integer>();
		keyPoints.add(0);
		isKeyPoint = new boolean[n];
		isKeyPoint[0] = true;
		for (int i = 0; i < A.length; ++i) {
			if (B[i] == -1) {
				servants.get(A[i]).add(i+1);
			} else {
				children.get(A[i]).add(i+1);
				children.get(B[i]).add(i+1);
				keyPoints.add(i+1);
				isKeyPoint[i+1] = true;
			}
		}
		minSamples = INF;
    int mask = (1 << keyPoints.size()) - 1;
    for (int i = 0; i <= mask; ++i) {
      int t = (i == 0 ? mask : i-1);
		  boolean[] select = new boolean[n];
      for (int j = 0; j < keyPoints.size(); ++j) {
        if ((t & (1 << j)) != 0) {
          select[keyPoints.get(j)] = true;
        }
      }
      findMinWays(select);
    }
		System.out.printf("minSamples = %d, wayCountInMinSamples = %d\n", minSamples,
				wayCountInMinSamples);
		return wayCountInMinSamples;
	}
	
	void findMinWays(boolean[] select) {
		// dp[i][0] is the min samples for servant tree rooted at i, i is not sampled.
		// dp[i][1] is the min samples for servant tree rooted at i, i is sampled.
		// If dp[i][j] == INF, that value is not valid.
		int[][] dp = new int[n][2];
		int[][] ways = new int[n][2];
    int curDp = 0;
		for (int i = n - 1; i >= 0; --i) {
			for (int j = 0; j < 2; ++j) {
				if (j == 0) {
					dp[i][0] = INF;
          ways[i][0] = 0;
					// Compute dp[i][0]. If any dp[servant][1] is false or dp[child][1] is false,
					// or it is a selected keyPoint, set INF.
					if (isKeyPoint[i] && select[i]) {
						continue;
					}
          boolean ok = true;
          if (children.get(i).size() > 0) {
            for (int child : children.get(i)) {
              if (dp[child][1] == INF) {
                ok = false;
                break;
              }
            }
          }
          if (ok) {
            int dpValue = 0;
            long wayValue = 1;
            for (int s : servants.get(i)) {
              dpValue += dp[s][1];
              wayValue *= ways[s][1];
              if (wayValue >= MOD) {
                wayValue %= MOD;
              }
            }
            dp[i][0] = dpValue;
            ways[i][0] = (int)wayValue;
          }
				} else {
					dp[i][1] = INF;
          ways[i][0] = 0;
					// Compute dp[i][1]. If it is a non-selected keyPoint, set INF.
					if (isKeyPoint[i] && !select[i]) {
						continue;
					}
					int dpValue = 1;
					long wayValue = 1;
					for (int s : servants.get(i)) {
						if (dp[s][0] < dp[s][1]) {
							dpValue += dp[s][0];
							wayValue *= ways[s][0];
						} else if (dp[s][0] == dp[s][1]) {
							dpValue += dp[s][0];
							wayValue *= (ways[s][0] + ways[s][1]);
						} else {
							dpValue += dp[s][1];
							wayValue *= ways[s][1];
						}
            if (wayValue >= MOD) {
              wayValue %= MOD;
            }
					}
          dp[i][1] = dpValue;
          ways[i][1] = (int)wayValue;
				}
			}
      if (isKeyPoint[i]) {
        int t = (select[i] ? 1 : 0);
        curDp += dp[i][t];
        if (curDp > minSamples) {
          return;
        }
      }
		}
		long curWayCount = 1;
		for (Integer key : keyPoints) {
			int t = select[key] ? 1 : 0;
      curWayCount *= ways[key][t];
      if (curWayCount >= MOD) {
        curWayCount %= MOD;
      }
		}
		if (minSamples > curDp) {
			minSamples = curDp;
			wayCountInMinSamples = (int)curWayCount;
		} else if (minSamples == curDp) {
      wayCountInMinSamples += (int)curWayCount;
      if (wayCountInMinSamples >= MOD) {
        wayCountInMinSamples -= MOD;
      }
		}
	}
}
