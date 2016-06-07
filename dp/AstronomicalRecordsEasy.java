public class AstronomicalRecordsEasy {
  public int minimalPlanets(int[] A, int[] B) {
    int minCount = Integer.MAX_VALUE;
    for (int i = 0; i < A.length; ++i) {
      for (int j = 0; j < B.length; ++j) {
        int k1 = B[j];
        int k2 = A[i];
        int count = 0;
        for (int i1 = 0, i2 = 0; i1 < A.length || i2 < B.length;) {
          if (i1 == A.length) {
            ++i2;
          } else if (i2 == B.length) {
            ++i1;
          } else {
            if (A[i1] * k1 < B[i2] * k2) {
              ++i1;
            } else if (A[i1] * k1 == B[i2] * k2) {
              ++i1;
              ++i2;
            } else {
              ++i2;
            }
          }
          ++count;
        }
        minCount = Math.min(minCount, count);
      }
    }
    return minCount;
  }
}
