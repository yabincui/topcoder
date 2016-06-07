import java.util.*;

public class EllysCoprimesDiv2 {
  public int getCount(int[] numbers) {
    Arrays.sort(numbers);
    int result = 0;
    for (int i = 1; i < numbers.length; ++i) {
      int start = numbers[i - 1];
      int end = numbers[i];
      if (gcd(start, end) == 1) {
        continue;
      }
      boolean hasOne = false;
      for (int j = start + 1; j < end; ++j) {
        if (gcd(start, j) == 1 && gcd(j, end) == 1) {
          hasOne = true;
          break;
        }
      }
      if (hasOne) {
        result++;
      } else {
        result += 2;
      }
    }
    return result;
  }

  int gcd(int a, int b) {
    if (a < b) {
      int tmp = a;
      a = b;
      b = tmp;
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
