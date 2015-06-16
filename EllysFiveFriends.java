import java.util.*;
import java.math.BigInteger;

public class EllysFiveFriends {
  final long MOD = 1000000007L;

  HashMap<BigInteger, Long> value_map;
  int n;

  public int getZero(int[] numbers) {
    this.n = numbers.length;
    numbers = sortNumbers(numbers);
    BigInteger value = numbersToBigInteger(numbers);
    value_map = new HashMap<BigInteger, Long>();
    value_map.put(BigInteger.valueOf(0), 1L);
    return (int)findCount(value);
  }

  long findCount(BigInteger value) {
    Long result = value_map.get(value);
    if (result != null) {
      return result;
    }
    int[] numbers = bigIntegerToNumbers(value);
    result = 0L;
    for (int i = 0; i < n; ++i) {
      int j = (i + 1) % n;
      if (numbers[i] == 0 || numbers[j] == 0) {
        continue;
      }
      int saved1 = numbers[i];
      int saved2 = numbers[j];
      // Drink apple.
      if (numbers[i] % 2 == 1 && numbers[j] % 2 == 1) {
        numbers[i]--;
        numbers[j]--;
        int[] newNumbers = sortNumbers(numbers);
        result += findCount(numbersToBigInteger(newNumbers));
        result %= MOD;
        numbers[i] = saved1;
        numbers[j] = saved2;
      }
      // Drink orange.
      numbers[i] /= 2;
      numbers[j] /= 2;
      int[] newNumbers = sortNumbers(numbers);
      result += findCount(numbersToBigInteger(newNumbers));
      result %= MOD;
      numbers[i] = saved1;
      numbers[j] = saved2;
    }
    value_map.put(value, result);
    return result;
  }

  // Make the smallest numbers by shifting.
  int[] sortNumbers(int[] numbers) {
    int minStart = 0;
    for (int start = 1; start < n; ++start) {
      for (int i = 0; i < n; ++i) {
        int value1 = numbers[(minStart + i) % n];
        int value2 = numbers[(start + i) % n];
        if (value1 != value2) {
          if (value2 < value1) {
            minStart = start;
          }
          break;
        }
      }
    }
    int[] s = new int[n];
    for (int i = 0; i < n; ++i) {
      s[i] = numbers[(minStart + i) % n];
    }
    return s;
  }

  final int SHIFT_BITS = 14;
  final int SHIFT_MASK = (1 << SHIFT_BITS) - 1;
  BigInteger numbersToBigInteger(int[] numbers) {
    BigInteger value = BigInteger.valueOf(numbers[0]);
    for (int i = 1; i < numbers.length; ++i) {
      value = value.shiftLeft(SHIFT_BITS).add(BigInteger.valueOf(numbers[i]));
    }
    return value;
  }

  int[] bigIntegerToNumbers(BigInteger value) {
    int[] numbers = new int[n];
    for (int i = n - 1; i >= 0; --i) {
      numbers[i] = value.and(BigInteger.valueOf(SHIFT_MASK)).intValue();
      value = value.shiftRight(SHIFT_BITS);
    }
    return numbers;
  }
}
