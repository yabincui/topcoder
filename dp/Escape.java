import java.util.*;

public class Escape {
  public int lowest(String[] harmful, String[] deadly) {
    final int maxRow = 500;
    final int maxCol = 500;
    // 0 normal, -1 death, 1 harmful.
    int[][] map = new int[maxRow + 1][maxCol + 1];
    for (String s : harmful) {
      int[] range = parseRegion(s);
      if (range != null) {
        for (int x = range[0]; x <= range[2]; ++x) {
          for (int y = range[1]; y <= range[3]; ++y) {
            map[y][x] = 1;
          }
        }
      }
    }
    for (String s : deadly) {
      int[] range = parseRegion(s);
      for (int x = range[0]; x <= range[2]; ++x) {
        for (int y = range[1]; y <= range[3]; ++y) {
          map[y][x] = -1;
        }
      }
    }
    Queue<Integer> queue = new ArrayDeque<Integer>();
    boolean[][] marked = new boolean[maxRow + 1][maxCol + 1];
    queue.add(0);
    marked[0][0] = true;
    boolean achieved = false;
    int step = 0;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    while (!achieved && !queue.isEmpty()) {
      ArrayList<Integer> array = new ArrayList<Integer>();
      while (!queue.isEmpty()) {
        array.add(queue.remove());
      }
      for (int i = 0; i < array.size(); ++i) {
        int pos = array.get(i);
        int x = pos >> 16;
        int y = pos & 0xffff;
        for (int j = 0; j < dx.length; ++j) {
          int nx = x + dx[j];
          int ny = y + dy[j];
          if (nx >= 0 && nx <= maxCol && ny >= 0 && ny <= maxRow && map[ny][nx] == 0 &&
              marked[ny][nx] == false) {
            marked[ny][nx] = true;
            array.add((nx << 16) | ny);
            if (ny == maxRow && nx == maxCol) {
              achieved = true;
              break;
            }
          }
        }
        if (achieved) {
          break;
        }
      }

      if (achieved) {
        break;
      }
      ++step;

      for (Integer pos : array) {
        int x = pos >> 16;
        int y = pos & 0xffff;
        for (int i = 0; i < dx.length; ++i) {
          int nx = x + dx[i];
          int ny = y + dy[i];
          if (nx >= 0 && nx <= maxCol && ny >= 0 && ny <= maxRow && map[ny][nx] == 1 &&
            marked[ny][nx] == false) {
            marked[ny][nx] = true;
            queue.add((nx << 16) | ny);
            if (ny == maxRow && nx == maxCol) {
              achieved = true;
              break;
            }
          }
        }
        if (achieved) {
          break;
        }
      }
    }
    return achieved ? step : -1;
  }

  int[] parseRegion(String s) {
    String[] strs = s.split(" ");
    if (strs.length != 4) {
      return null;
    }
    int x1 = Integer.parseInt(strs[0]);
    int y1 = Integer.parseInt(strs[1]);
    int x2 = Integer.parseInt(strs[2]);
    int y2 = Integer.parseInt(strs[3]);
    int[] result = new int[4];
    result[0] = Math.min(x1, x2);
    result[1] = Math.min(y1, y2);
    result[2] = Math.max(x1, x2);
    result[3] = Math.max(y1, y2);
    return result;
  }
}
