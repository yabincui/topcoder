import java.util.*;

public class CuttingBitString {
  class Value {
    final int BASE = (int)Math.pow(5, 10);
    ArrayList<Integer> digits = new ArrayList<Integer>();  // digits.get(i) is < 5^10.

    void addBitValue(int level) {  // Add 1 << level.
      ArrayList<Integer> value = new ArrayList<Integer>();
      value.add(1);
      for (int i = 1; i <= level; ++i) {
        int carry = 0;
        for (int j = 0; j < value.size(); ++j) {
          int temp = value.get(j);
          temp = temp * 2 + carry;
          carry = 0;
          if (temp >= BASE) {
            temp -= BASE;
            carry = 1;
          }
          value.set(j, temp);
        }
        if (carry != 0) {
          value.add(carry);
        }
      }
      int carry = 0;
      int i;
      for (i = 0; i < digits.size() && i < value.size(); ++i) {
        int temp = digits.get(i) + value.get(i) + carry;
        digits.set(i, temp % BASE);
        carry = temp / BASE;
      }
      for (; i < digits.size(); ++i) {
        int temp = digits.get(i) + carry;
        digits.set(i, temp % BASE);
        carry = temp / BASE;
      }
      for (; i < value.size(); ++i) {
        int temp = value.get(i) + carry;
        digits.add(temp % BASE);
        carry = temp / BASE;
      }
      while (carry > 0) {
        digits.add(carry % BASE);
        carry /= BASE;
      }
    }

    boolean isPowerOfFive() {
      int digit = 0;
      for (int i = 0; i < digits.size(); ++i) {
        if (digits.get(i) != 0) {
          if (digit != 0) {
            return false;
          }
          digit = digits.get(i);
        }
      }
      while (digit != 1) {
        if (digit % 5 != 0) {
          return false;
        }
        digit /= 5;
      }
      return true;
    }
  }

  public int getmin(String s) {
    int n = s.length();
    int[] dp = new int[n + 1];
    dp[0] = 0;
    for (int i = 1; i <= n; ++i) {
      dp[i] = -1;
      Value value = new Value();
      for (int j = i; j > 0; --j) {
        if (s.charAt(j - 1) == '1') {
          value.addBitValue(i - j);
          if (value.isPowerOfFive()) {
            if (dp[j - 1] != -1 && (dp[i] == -1 || (dp[j - 1] + 1 < dp[i]))) {
              dp[i] = dp[j - 1] + 1;
            }
          }
        }
      }
    }
    return dp[n];
  }
}
