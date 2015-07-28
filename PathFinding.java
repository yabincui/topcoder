import java.util.*;

public class PathFinding {
  public int minTurns(String[] board) {
    int m = board.length;
    int n = board[0].length();
    int aPos = 0;
    int bPos = 0;
    for (int i = 0; i < m; ++i) {
      for (int j = 0; j < n; ++j) {
        if (board[i].charAt(j) == 'A') {
          aPos = (i << 16) | j;
        } else if (board[i].charAt(j) == 'B') {
          bPos = (i << 16) | j;
        }
      }
    }
    long initState = ((long)aPos << 32) | bPos;
    long targetState = ((long)bPos << 32) | aPos;
    //System.out.printf("initState = 0x%x, targetState = 0x%x\n", initState, targetState);
    Queue<Long> queue = new ArrayDeque<Long>();
    HashSet<Long> hitSet = new HashSet<Long>();
    int turn = 0;
    queue.add(initState);
    hitSet.add(initState);
    boolean found = false;
    while (!queue.isEmpty()) {
      int size = queue.size();
      while (size-- > 0) {
        long state = queue.remove();
        if (state == targetState) {
          found = true;
          break;
        }
        aPos = (int)(state >> 32);
        bPos = (int)(state & 0xffffffff);
        int aRow = aPos >> 16;
        int aCol = aPos & 0xffff;
        int bRow = bPos >> 16;
        int bCol = bPos & 0xffff;
        int[] dr = new int[]{0, 0, 0, -1, 1, -1, 1, -1, 1};
        int[] dc = new int[]{0, -1, 1, 0, 0, -1, 1, 1, -1};
        for (int i = 0; i < dr.length; i++) {
          for (int j = 0; j < dr.length; j++) {
            int aNextRow = aRow + dr[i];
            int aNextCol = aCol + dc[i];
            int bNextRow = bRow + dr[j];
            int bNextCol = bCol + dc[j];
            if (aNextRow >= 0 && aNextRow < m && aNextCol >= 0 && aNextCol < n &&
                bNextRow >= 0 && bNextRow < m && bNextCol >= 0 && bNextCol < n &&
                board[aNextRow].charAt(aNextCol) != 'X' && board[bNextRow].charAt(bNextCol) != 'X' &&
                !(aNextRow == bRow && aNextCol == bCol && bNextRow == aRow && bNextCol == aCol) &&
                !(aNextRow == bNextRow && aNextCol == bNextCol)) {
              long nextState = ((long)((aNextRow << 16) | aNextCol) << 32) | ((bNextRow << 16) | bNextCol);
              if (!hitSet.contains(nextState)) {
                //System.out.printf("state = 0x%x\n", nextState);
                queue.add(nextState);
                hitSet.add(nextState);
              }
            }
          }
        }
      }
      if (found) {
        break;
      }
      turn++;
    }
    return (found ? turn : -1);
  }
}
