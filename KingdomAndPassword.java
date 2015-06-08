import java.util.*;

public class KingdomAndPassword {
  HashMap<Long, Long> hitMaxMap;
  HashMap<Long, Long> hitMinMap;
  int[] restrictedDigits;
  
  long getMaxPermute(long state, int pos) {
    if (state == 0) {
      return 0;
    }
    Long target = hitMaxMap.get(state);
    if (target != null) {
      return target;
    }
    target = -1L;
    long prevDigit = -1;
    for (long level = 1; level <= state; level *= 10) {
      long digit = state / level % 10;
      if (digit == prevDigit) {
        continue;
      }
      prevDigit = digit;
      if (restrictedDigits[pos] == digit) {
        continue;
      }
      long newState = state / level / 10 * level + state % level;
      long temp = getMaxPermute(newState, pos + 1);
      if (temp == -1) {
        continue;
      }
      long add = 1;
      for (; add <= temp; add *= 10) {
      }
      target = digit * add + temp;
      break;
    }

    hitMaxMap.put(state, target);
    return target;
  }

  long getMinPermute(long state, int pos) {
    if (state == 0) {
      return 0;
    }
    Long target = hitMinMap.get(state);
    if (target != null) {
      return target;
    }
    target = -1L;
    long prevDigit = -1;
    long level = 1;
    for (; level <= state; level *= 10) {
    }

    for (level /= 10; level >= 1; level /= 10) {
      long digit = state / level % 10;
      if (digit == prevDigit) {
        continue;
      }
      prevDigit = digit;
      if (restrictedDigits[pos] == digit) {
        continue;
      }
      long newState = state / level / 10 * level + state % level;
      long temp = getMinPermute(newState, pos + 1);
      if (temp == -1) {
        continue;
      }
      long add = 1;
      for (; add <= temp; add *= 10) {
      }
      target = digit * add + temp;
      break;
    }
    hitMinMap.put(state, target);
    return target;
  }

  public long newPassword(long oldPassword, int[] restrictedDigits) {
    hitMaxMap = new HashMap<Long, Long>();
    hitMinMap = new HashMap<Long, Long>();
    this.restrictedDigits = restrictedDigits;
    int[] digits = new int[10];
    int n = 0;
    long temp;
    for (temp = oldPassword; temp > 0; temp /= 10) {
      digits[(int)(temp % 10)]++;
      n++;
    }
    // level[i] is the i-th most significant digit.
    int[] level = new int[n];
    temp = oldPassword;
    for (int i = 0; i < n; ++i, temp /= 10) {
      level[n - 1 - i] = (int)(temp % 10);
    }
    long remLevel = 1;
    for (int i = 0; i < n; ++i) {
      remLevel *= 10;
    }
    long result = -1;
    int match = 0;
    for (; match < n; ++match) {
      remLevel /= 10;
      long prevValue = oldPassword / remLevel;
      int curDigit = level[match];
      long biggerValue = -1;
      for (int i = curDigit + 1; i <= 9; ++i) {
        if (digits[i] == 0 || i == restrictedDigits[match]) {
          continue;
        }
        digits[i]--;
        long state = 0;
        for (int k = 1; k <= 9; ++k) {
          for (int j = 0; j < digits[k]; ++j) {
            state = state * 10 + k;
          }
        }
        biggerValue = getMinPermute(state, match + 1);
        digits[i]++;
        if (biggerValue != -1) {
          biggerValue += (prevValue - curDigit + i) * remLevel;
          break;
        }
      }
      long smallerValue = -1;
      for (int i = curDigit - 1; i >= 1; --i) {
        if (digits[i] == 0 || i == restrictedDigits[match]) {
          continue;
        }
        digits[i]--;
        long state = 0;
        for (int k = 1; k <= 9; ++k) {
          for (int j = 0; j < digits[k]; ++j) {
            state = state * 10 + k;
          }
        }
        smallerValue = getMaxPermute(state, match + 1);
        digits[i]++;
        if (smallerValue != -1) {
          smallerValue += (prevValue - curDigit + i)  * remLevel;
          break;
        }
      }

      long selectedValue = -1;
      if (biggerValue == -1) {
        selectedValue = smallerValue;
      } else if (smallerValue == -1) {
        selectedValue = biggerValue;
      } else {
        if (biggerValue - oldPassword < oldPassword - smallerValue) {
          selectedValue = biggerValue;
        } else {
          selectedValue = smallerValue;
        }
      }

      if (selectedValue != -1) {
        if (result == -1) {
          result = selectedValue;
        } else {
          long oldDiff = Math.abs(result - oldPassword);
          long newDiff = Math.abs(selectedValue - oldPassword);
          if (newDiff < oldDiff || (newDiff == oldDiff && selectedValue < result)) {
            result = selectedValue;
          }
        }
      }
      
      if (level[match] == restrictedDigits[match]) {
        break;
      }
      digits[level[match]]--;
    }
    if (match == n) {
      return oldPassword;
    }
    return result;
  }

}
