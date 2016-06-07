import java.util.*;

public class RelativelyPrimeSubset {
  HashMap<Long, Integer> map;
  long[] masks;  
  int[] S;
  int n;
  public int findSize(int[] S) {
    n = S.length;
    this.S = S;
    masks = new long[n];
    for (int i = 0; i < n; ++i) {
      long mask = 0;
      for (int j = 0; j < n; ++j) {
        if (j != i && gcd(S[i], S[j]) == 1) {
          mask |= (1L << j);
        }
      }
      masks[i] = mask;
    }
    map = new HashMap<Long, Integer>();
    map.put(0L, 0);
    return findSize((1L << n) - 1);
  }

  int findSize(long bits) {
    Integer size = map.get(bits);
    if (size != null) {
      return size;
    }
    long level = 1;
    size = 0;
    for (int i = 0; level <= bits; ++i, level <<= 1) {
      if ((bits & level) != 0) {
        long newBits = bits & masks[i];
        int count = findSize(newBits) + 1;
        size = Math.max(size, count);
      }
    }
    map.put(bits, size);
    return size;
  }


  int gcd(int a, int b) {
    if (a < b) {
      int temp = a;
      a = b;
      b = temp;
    }
    int c = a % b;
    while (c != 0) {
      a = b;
      b = c;
      c = a % b;
    }
    return b;
  }
}
