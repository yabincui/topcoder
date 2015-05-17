public class BuildingTowers {
  public long maxHeight(int N, int K, int[] x, int[] t) {
    int m = x.length + 2;
    if (x.length > 0 && x[0] == 1) {
      --m;
    }
    if (x.length > 0 && x[x.length - 1] == N) {
      --m;
    }
    long[] limitPos = new long[m];
    long[] limitValue = new long[m];
    limitPos[0] = 0;
    limitValue[0] = 0;
    limitPos[m - 1] = N - 1;
    limitValue[m - 1] = Long.MAX_VALUE;

    for (int i = 0; i < x.length; ++i) {
      int j = i + 1;
      if (x[0] == 1) {
        --j;
      }
      limitPos[j] = x[i] - 1;
      limitValue[j] = t[i];
    }
    limitValue[0] = 0;

    long[] dpMax = new long[m];

    for (int i = 1; i < m; ++i) {
      long dist = limitPos[i] - limitPos[i - 1];
      long diff = (long)K * dist;
      dpMax[i] = rangeModify(dpMax[i - 1] + diff, 0, limitValue[i]);
    }

    long possibleMax = 0;
    for (int i = m - 1; i > 0; --i) {
      long dist = limitPos[i] - limitPos[i - 1];
      long diff = (long)K * dist;
      dpMax[i - 1] = rangeModify(dpMax[i] + diff, 0, dpMax[i - 1]);
      long temp = findMaxInBetween(dist, K, dpMax[i - 1], dpMax[i]);
      possibleMax = Math.max(possibleMax, temp);
    }
    return possibleMax;
  }

  long rangeModify(long value, long min, long max) {
    if (value < min) {
      value = min;
    }
    if (value > max) {
      value = max;
    }
    return value;
  }

  long findMaxInBetween(long dist, long K, long value1, long value2) {
    if (value1 < value2) {
      long temp = value1;
      value1 = value2;
      value2 = temp;
    }
    long t = (value1 - value2) / K;
    value2 += t * K;
    dist -= t;
    long d1 = dist / 2;
    long d2 = dist - d1;
    return Math.min(value1 + d1 * K, value2 + d2 * K);
  }
}
