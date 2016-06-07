// https://community.topcoder.com/stat?c=problem_statement&pm=1259&rd=4493
public class ZigZag {
  int longestZigZag(int[] sequence) {
    // dp_up[i] is the length of the longest zigzag sequence ended in i, and in up state.
    // dp_down[i] is the length of the longest zigzag sequence ended in i, and in down state.
    // prev_up[i] records the prev pos for dp_up[i].
    // prev_down[i] records the prev pos for dp_down[i].
    int n = sequence.length;
    int[] dp_up = new int[n];
    int[] dp_down = new int[n];
    int[] prev_up = new int[n];
    int[] prev_down = new int[n];

    dp_up[0] = 1;
    prev_up[0] = -1;
    dp_down[0] = 1;
    prev_down[0] = -1;
    for (int i = 1; i < n; ++i) {
      dp_up[i] = 1;
      prev_up[i] = -1;
      dp_down[i] = 1;
      prev_down[i] = -1;

      for (int j = i - 1; j >= 0; --j) {
        if (sequence[i] > sequence[j]) {
          if (dp_up[i] < dp_down[j] + 1) {
            dp_up[i] = dp_down[j] + 1;
            prev_up[i] = j;
          }
        } else if (sequence[i] < sequence[j]) {
          if (dp_down[i] < dp_up[j] + 1) {
            dp_down[i] = dp_up[j] + 1;
            prev_down[i] = j;
          }
        }
      }
    }

    int max_dp = 0;
    int max_pos = 0;
    int dir = 1;
    for (int i = 0; i < n; ++i) {
      if (dp_up[i] > max_dp) {
        max_dp = dp_up[i];
        max_pos = i;
        dir = 1;
      }
      if (dp_down[i] > max_dp) {
        max_dp = dp_down[i];
        max_pos = i;
        dir = -1;
      }
    }

    int[] result = new int[max_dp];
    int pos = max_pos;
    for (int j = max_dp - 1; j >= 0; --j) {
      result[j] = sequence[pos];
      if (dir == 1) {
        pos = prev_up[pos];
      } else {
        pos = prev_down[pos];
      }
      dir = -dir;
    }
    for (int i = 0; i < max_dp; ++i) {
      sequence[i] = result[i];
    }
    return max_dp;
  }
}
