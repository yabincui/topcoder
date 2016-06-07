import java.util.*;

public class IslandFerries {
  public int[] costs(String[] legs, String[] prices) {
    int serviceCount = legs.length;
    int islandCount = prices.length;
    int serviceMask = (1 << serviceCount) - 1;

    boolean[][][] neighbor = new boolean[serviceCount][islandCount][islandCount];
    for (int i = 0; i < legs.length; ++i) {
      String[] strs = legs[i].split(" ");
      for (String s : strs) {
        String[] ids = s.split("-");
        int from = Integer.parseInt(ids[0]);
        int to = Integer.parseInt(ids[1]);
        neighbor[i][from][to] = true;
      }
    }

    int[][] price = new int[islandCount][serviceCount];
    for (int i = 0; i < islandCount; ++i) {
      String[] strs = prices[i].split(" ");
      for (int j = 0; j < serviceCount; ++j) {
        price[i][j] = Integer.parseInt(strs[j]);
      }
    }

    int[][] cost = new int[islandCount][serviceMask + 1];
    Integer[] ticketCombination = enumerateTicketCombination(serviceCount, 3);
    for (int i = 0; i < islandCount; ++i) {
      for (int j = 0; j <= serviceMask; ++j) {
        cost[i][j] = -1;
      }
    }
    cost[0][0] = 0;
    boolean[][] visited = new boolean[islandCount][serviceMask + 1];
    while (true) {
      int selectedIsland = -1;
      int selectedServiceMask = -1;
      for (int i = 0; i < islandCount; ++i) {
        for (int j : ticketCombination) {
          if (!visited[i][j] && cost[i][j] != -1 &&
            (selectedIsland == -1 || cost[i][j] < cost[selectedIsland][selectedServiceMask])) {
            selectedIsland = i;
            selectedServiceMask = j;
          }
        }
      }
      if (selectedIsland == -1) {
        break;
      }
      visited[selectedIsland][selectedServiceMask] = true;
      // Move to other island.
      for (int i = 0; i < serviceCount; ++i) {
        if ((selectedServiceMask & (1 << i)) != 0) {
          int nextServiceMask = selectedServiceMask & ~(1 << i);
          for (int nextIsland = 0; nextIsland < islandCount; ++nextIsland) {
            if (neighbor[i][selectedIsland][nextIsland] && !visited[nextIsland][nextServiceMask]) {
              cost[nextIsland][nextServiceMask] = cost[selectedIsland][selectedServiceMask];
            }
          }
        }
      }
      // Buy more ticket.
      int bits = getBits(selectedServiceMask);
      if (bits < 3) {
        for (int i = 0; i < serviceCount; ++i) {
          if ((selectedServiceMask & (1 << i)) == 0) {
            int nextServiceMask = selectedServiceMask | (1 << i);
            int nextCost = cost[selectedIsland][selectedServiceMask] +
                           price[selectedIsland][i];
            if (!visited[selectedIsland][nextServiceMask] &&
                (cost[selectedIsland][nextServiceMask] == -1 ||
                cost[selectedIsland][nextServiceMask] > nextCost)) {
              cost[selectedIsland][nextServiceMask] = nextCost;
            }
          }
        }
      }
    }
    int[] result = new int[islandCount - 1];
    for (int i = 0; i < islandCount - 1; ++i) {
      result[i] = cost[i + 1][0];
    }
    return result;
  }

  Integer[] enumerateTicketCombination(int serviceCount, int maxTicketCount) {
    ArrayList<Integer> combination = new ArrayList<Integer>();
    int mask = (1 << serviceCount) - 1;
    for (int i = 0; i <= mask; ++i) {
      int bits = getBits(i);
      if (bits <= maxTicketCount) {
        combination.add(i);
      }
    }
    return combination.toArray(new Integer[]{});
  }

  int getBits(int mask) {
    int bits = 0;
    while ((mask & (mask - 1)) != 0) {
      mask &= mask - 1;
      ++bits;
    }
    return bits;
  }
}
