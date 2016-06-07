public class PrimeSoccer {
  public double getProbability(int skillOfTeamA, int skillOfTeamB) {
    double pA = skillOfTeamA / 100.0;
    double pB = skillOfTeamB / 100.0;
    int n = 90 / 5;
    double[] dpA = new double[n + 1];
    double[] dpB = new double[n + 1];

    dpA[0] = 1.0;
    dpB[0] = 1.0;
    for (int i = 1; i <= n; ++i) {
      dpA[i] = dpA[i - 1] * pA;
      for (int j = i - 1; j >= 1; --j) {
        dpA[j] = dpA[j] * (1 - pA) + dpA[j - 1] * pA;
      }
      dpA[0] = dpA[0] * (1 - pA);

      dpB[i] = dpB[i - 1] * pB;
      for (int j = i - 1; j >= 1; --j) {
        dpB[j] = dpB[j] * (1 - pB) + dpB[j - 1] * pB;
      }
      dpB[0] = dpB[0] * (1 - pB);
    }
    double result = 0.0;
    for (int i = 0; i <= n; ++i) {
      if (isPrime(i)) {
        result += dpA[i];
        continue;
      }
      for (int j = 0; j <= n; ++j) {
        if (isPrime(j)) {
          result += dpA[i] * dpB[j];
        }
      }
    }
    return result;
  }

  boolean isPrime(int n) {
    if (n == 0 || n == 1) {
      return false;
    }
    if (n == 2) {
      return true;
    }
    for (int i = 2; i <= (int)Math.sqrt(n); ++i) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
