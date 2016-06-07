// http://community.topcoder.com/stat?c=problem_statement&pm=1166&rd=4705
import java.util.Arrays;
import java.util.ArrayList;

public class Jewelry {
  long[][] cnK = new long[31][31];

  void initCNK() {
    cnK[0][0] = 1;
    for (int i = 1; i <= 30; ++i) {
      cnK[i][0] = 1;
      cnK[i][i] = 1;
      for (int j = 1; j < i; ++j) {
        cnK[i][j] = cnK[i - 1][j] + cnK[i - 1][j - 1];
      }
    }
  }

  class Elem {
    int value;
    int startPos;
    int endPos;
  };

  ArrayList<Elem> arrayToElems(int[] values) {
    ArrayList<Elem> result = new ArrayList<Elem>();
    int curValue = values[0];
    int startPos = 0;
    int endPos = 0;
    for (int i = 1; i < values.length; ++i) {
      if (curValue == values[i]) {
        endPos++;
      } else {
        Elem elem = new Elem();
        elem.value = curValue;
        elem.startPos = startPos;
        elem.endPos = endPos;
        result.add(elem);
        curValue = values[i];
        startPos = endPos = i;
      }
    }
    Elem elem = new Elem();
    elem.value = curValue;
    elem.startPos = startPos;
    elem.endPos = endPos;
    result.add(elem);
    return result;
  }

  public long howMany1(int[] values) {
    initCNK();
    Arrays.sort(values);
    ArrayList<Elem> elems = arrayToElems(values);
    int n = values.length;
    int m = elems.size();
    int sum = 0;
    for (int value : values) {
      sum += value;
    }
    int maxValue = sum / 2;  // max possible split value.

    // dpFront[i][j] is the count of methods using jewelry value <= values[i - 1] to achieve
    // total value j.
    long[][] dpFront = new long[n + 1][maxValue + 1];
    dpFront[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      dpFront[i][0] = 1;
      for (int j = 1; j <= maxValue; ++j) {
        dpFront[i][j] = dpFront[i - 1][j];
        if (j >= values[i - 1]) {
          dpFront[i][j] += dpFront[i - 1][j - values[i - 1]];
        }
      }
    }

    // dpEnd[i][j] is the count of methods using jewelry value >= values[i] to achieve
    // total value j.
    long[][] dpEnd = new long[n + 1][maxValue + 1];
    dpEnd[n][0] = 1;
    for (int i = n - 1; i >= 0; --i) {
      dpEnd[i][0] = 1;
      for (int j = 1; j <= maxValue; ++j) {
        dpEnd[i][j] = dpEnd[i + 1][j];
        if (j >= values[i]) {
          dpEnd[i][j] += dpEnd[i + 1][j - values[i]];
        }
      }
    }

    long result = 0;
    for (int i = 0; i < m; ++i) {
      Elem elem = elems.get(i);
      int value = elem.value;
      int startPos = elem.startPos;
      int endPos = elem.endPos;
      int totalCount = endPos - startPos + 1;
      for (int countLeft = 1; countLeft <= totalCount; ++countLeft) {
        for (int countRight = 0; countRight <= totalCount - countLeft; ++countRight) {
          int valueLeft = countLeft * value;
          int valueRight = countRight * value;
          int splitStart = Math.max(valueLeft, valueRight);
          for (int split = splitStart; split <= maxValue; ++split) {
            int valueLeft2 = split - valueLeft;
            int valueRight2 = split - valueRight;
            long temp = dpFront[startPos][valueLeft2] * dpEnd[endPos + 1][valueRight2] * cnK[totalCount][countLeft] * cnK[totalCount - countLeft][countRight];
            if (temp == 0) {
              continue;
            }
            //System.out.printf("value = %d, split = %d, countLeft = %d, countRight = %d\n", value, split, countLeft, countRight);
            //System.out.printf("dpFront[%d][%d] = %d, dpEnd[%d][%d] = %d, cnK[%d][%d] = %d, cnK[%d][%d] = %d, temp = %d\n", startPos, valueLeft2, dpFront[startPos][valueLeft2], endPos + 1, valueRight2, dpEnd[endPos + 1][valueRight2], totalCount, countLeft, cnK[totalCount][countLeft], totalCount - countLeft, countRight, cnK[totalCount - countLeft][countRight], temp);
            result += temp;
          }
        }
      }
    }
    return result;
  }
  
  // http://community.topcoder.com/tc?module=Static&d1=match_editorials&d2=tco03_online_rd_4
  public long howMany(int[] values) {
    initCNK();
    Arrays.sort(values);
    ArrayList<Elem> elems = arrayToElems(values);
    int n = values.length;
    int m = elems.size();
    int sum = 0;
    for (int value : values) {
      sum += value;
    }
    int maxValue = sum / 2;  // max possible split value.

    // dpFront[i][j] is the count of methods using jewelry value <= values[i - 1] to achieve
    // total value j.
    long[][] dpFront = new long[n + 1][maxValue + 1];
    dpFront[0][0] = 1;
    for (int i = 1; i <= n; ++i) {
      dpFront[i][0] = 1;
      for (int j = 1; j <= maxValue; ++j) {
        dpFront[i][j] = dpFront[i - 1][j];
        if (j >= values[i - 1]) {
          dpFront[i][j] += dpFront[i - 1][j - values[i - 1]];
        }
      }
    }

    // dpEnd[i][j] is the count of methods using jewelry value >= values[i] to achieve
    // total value j.
    long[][] dpEnd = new long[n + 1][maxValue + 1];
    dpEnd[n][0] = 1;
    for (int i = n - 1; i >= 0; --i) {
      dpEnd[i][0] = 1;
      for (int j = 1; j <= maxValue; ++j) {
        dpEnd[i][j] = dpEnd[i + 1][j];
        if (j >= values[i]) {
          dpEnd[i][j] += dpEnd[i + 1][j - values[i]];
        }
      }
    }

    long result = 0;
    for (int i = 0; i < m; ++i) {
      Elem elem = elems.get(i);
      int value = elem.value;
      int startPos = elem.startPos;
      int endPos = elem.endPos;
      int totalCount = endPos - startPos + 1;
      for (int countLeft = 1; countLeft <= totalCount; ++countLeft) {
        int valueLeft = countLeft * value;
        int splitStart = Math.max(1, valueLeft);
        for (int split = splitStart; split <= maxValue; ++split) {
          int valueLeft2 = split - valueLeft;
          long temp = dpFront[startPos][valueLeft2] * dpEnd[startPos + countLeft][split] * cnK[totalCount][countLeft];
          if (temp == 0) {
            continue;
          }
          result += temp;
        }
      }
    }
    return result;
  }
}
