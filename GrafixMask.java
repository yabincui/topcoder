import java.util.*;

public class GrafixMask {
  public int[] sortedAreas(String[] rectangles) {
    final int ROW = 400;
    final int COL = 600;
    boolean[][] map = new boolean[ROW][COL];
    for (int i = 0; i < ROW; ++i) {
      for (int j = 0; j < COL; ++j) {
        map[i][j] = true;
      }
    }
    for (String rec : rectangles) {
      String[] strs = rec.split(" ");
      try {
        int top = Integer.parseInt(strs[0]);
        int left = Integer.parseInt(strs[1]);
        int bottom = Integer.parseInt(strs[2]);
        int right = Integer.parseInt(strs[3]);
        for (int i = top; i <= bottom; ++i) {
          for (int j = left; j <= right; ++j) {
            map[i][j] = false;
          }
        }
      } catch (NumberFormatException e) {
      }
    }
    ArrayList<Integer> areas = new ArrayList<Integer>();
    for (int i = 0; i < map.length; ++i) {
      for (int j = 0; j < map[0].length; ++j) {
        if (map[i][j]) {
          areas.add(fillArea(map, i, j));
        }
      }
    }
    Collections.sort(areas);
    int[] result = new int[areas.size()];
    for (int i = 0; i < areas.size(); ++i) {
      result[i] = areas.get(i);
    }
    return result;
  }

  private int fillArea(boolean[][] map, int startR, int startC) {
    Stack<Integer> stack = new Stack<Integer>();
    stack.push((startR << 16) | startC);
    
    int[] dr = new int[]{-1, 1, 0, 0};
    int[] dc = new int[]{0, 0, 1, -1};
    
    int result = 0;
    while (!stack.empty()) {
      Integer position = stack.pop();
      int r = position >> 16;
      int c = position & 0xffff;
      if (r < 0 || r >= map.length || c < 0 || c >= map[0].length || map[r][c] == false) {
        continue;
      }
      map[r][c] = false;
      ++result;
      for (int i = 0; i < 4; ++i) {
        int nr = r + dr[i];
        int nc = c + dc[i];
        stack.push((nr << 16) | nc);
      }
    }

    return result;
  }
}
