import java.util.*;

public class FindingKids {
  class Node {
    long pos;
    int dir;  // dir == -1 is left, 1 is right.
    Node(long pos, int dir) {
      this.pos = pos;
      this.dir = dir;
    }
  }

  int n;
  int q;
  Node[] nodes;
  ArrayList<Long> carsToLeft;
  ArrayList<Long> carsToRight;

  class Query {
    int kid;
    long time;
    Query(int kid, long time) {
      this.kid = kid;
      this.time = time;
    }
  }

  Query[] queries;

  public long getSum(int n, int q, int A, int B, int C) {
    this.n = n;
    this.q = q;
    generateData(A, B, C);
    long sum = 0;
    for (int i = 0; i < q; ++i) {
      long tmp = handleQuery(queries[i].kid, queries[i].time);
      sum += Math.abs(tmp);
    }
    return sum;
  }

  long handleQuery(int kid, long time) {
    int dir = nodes[kid].dir;
    long pos = nodes[kid].pos;
    int leftCount = 0;
    int tmp = Collections.binarySearch(carsToRight, pos);
    if (tmp >= 0) {
      leftCount = tmp;
    } else {
      leftCount = -tmp - 1;
    }
    int rightCount = 0;
    tmp = Collections.binarySearch(carsToLeft, pos);
    if (tmp >= 0) {
      rightCount = carsToLeft.size() - tmp - 1;
    } else {
      rightCount = carsToLeft.size() - (-tmp - 1);
    }
    int leftEnd = 0;
    int leftBeg = leftCount - 1;
    int rightEnd = carsToLeft.size() - 1;
    int rightBeg = rightEnd - rightCount + 1;
    //System.out.printf("dir = %d, tmp = %d, leftEnd = %d, leftBeg = %d, rightEnd = %d, rightBeg = %d, rightCarsToLeft = %d\n",
    //	dir, tmp, leftEnd, leftBeg, rightEnd, rightBeg, rightCarsToLeft);

    if (dir == -1) {
      if (leftCount == 0) {
      	//System.out.printf("pos = %d, time = %d, ret = %d\n", pos, time, pos - time);
        return pos - time;
      }
      long p1 = carsToRight.get(leftBeg);
      if (pos - p1 >= 2 * time) {
        return pos - time;
      }
      if (leftCount <= rightCount) {
        // To left at last.
        rightCount = leftCount;
        rightEnd = rightBeg + rightCount - 1;
        return binarySearchPosition(-1, leftBeg, leftEnd, rightBeg, rightEnd, time);
      } else {
        // To right at last.
        leftCount = rightCount + 1;
        leftEnd = leftBeg - leftCount + 1;
        return binarySearchPosition(-1, leftBeg, leftEnd, rightBeg, rightEnd, time);
      }
    } else {
      if (rightCount == 0) {
        return pos + time;
      }
      long p1 = carsToLeft.get(rightBeg);
      if (p1 - pos >= 2 * time) {
        return pos + time;
      }
      if (rightCount <= leftCount) {
        // To right at last.
        leftCount = rightCount;
        leftEnd = leftBeg - leftCount + 1;
        return binarySearchPosition(1, leftBeg, leftEnd, rightBeg, rightEnd, time);
      } else {
        // To left at last.
        rightCount = leftCount + 1;
        rightEnd = rightBeg + rightCount - 1;
        return binarySearchPosition(1, leftBeg, leftEnd, rightBeg, rightEnd, time);
      }
    }
  }

  long binarySearchPosition(int dir, int leftBeg, int leftEnd, int rightBeg, int rightEnd, long time) {
    int leftCount = leftBeg - leftEnd + 1;
    int rightCount = rightEnd - rightBeg + 1;
    if (carsToLeft.get(rightEnd) - carsToRight.get(leftEnd) <= 2 * time) {
      if (dir == -1) {
        if (leftCount <= rightCount) {
          return carsToLeft.get(rightEnd) - time;
        }
        return carsToRight.get(leftEnd) + time;
      } else {
        if (leftCount >= rightCount) {
          return carsToRight.get(leftEnd) + time;
        }
        return carsToLeft.get(rightEnd) - time;
      }
    }

    int steps = leftCount + rightCount;
    int low = 0;
    int high = steps - 2;
    while (low != high) {
      int mid = (low + high) / 2;
      int leftPos = leftBeg - (mid / 2);
      int rightPos = rightBeg + (mid / 2);
      if (mid % 2 == 1) {
        if (dir == -1) {
          leftPos--;
        } else {
          rightPos++;
        }
      }
      if (carsToLeft.get(rightPos) - carsToRight.get(leftPos) >= 2 * time) {
        high = mid;
      } else {
        low = mid + 1;
      }
    }
    int leftPos = leftBeg - (low / 2);
    int rightPos = rightBeg + (low / 2);
    boolean onLeft = (dir == -1 ? true : false);
    if (low % 2 == 1) {
      onLeft = !onLeft;
      if (dir == -1) {
        leftPos--;
      } else {
        rightPos++;
      }
    }
    if (onLeft) {
      return carsToRight.get(leftPos) + time;
    }
    return carsToLeft.get(rightPos) - time;
  }

  void generateData(long A, long B, long C) {
    final long M = 1000000007;
    nodes = new Node[n];
    HashSet<Long> posSet = new HashSet<Long>();
    for (int i = 0; i < n; ++i) {
      A = (A * B + C) % M;
      long p = A % (M - n + i + 1);
      if (posSet.contains(p)) {
        p = M - n + i;
      }
      posSet.add(p);
      int dir = (p % 2 == 0) ? 1 : -1;
      nodes[i] = new Node(p, dir);
    }
    queries = new Query[q];
    for (int i = 0; i < q; ++i) {
      A = (A * B + C) % M;
      int kid = (int)(A % n);
      A = (A * B + C) % M;
      long time = A;
      queries[i] = new Query(kid, time);
    }
    /*
    for (int i = 0; i < n; ++i) {
    	System.out.printf("nodes[%d] pos = %d, dir = %d\n", i, nodes[i].pos, nodes[i].dir);
    }
    for (int i = 0; i < q; ++i) {
    	System.out.printf("queries[%d] kid = %d, time = %d\n", i, queries[i].kid, queries[i].time);
    }
    */
    carsToLeft = new ArrayList<Long>();
    carsToRight = new ArrayList<Long>();
    for (int i = 0; i < n; ++i) {
      if (nodes[i].dir == -1) {
        carsToLeft.add(nodes[i].pos);
      } else {
        carsToRight.add(nodes[i].pos);
      }
    }
    /*
    System.out.printf("carsToLeft = ");
    for (Long p : carsToLeft) {
    	System.out.printf("%d ", p);
    }
    System.out.printf("\n");
    System.out.printf("carsToRight = ");
    for (Long p : carsToRight) {
    	System.out.printf("%d ", p);
    }
    System.out.printf("\n");
    */
    Collections.sort(carsToLeft);
    Collections.sort(carsToRight);
  }
}

