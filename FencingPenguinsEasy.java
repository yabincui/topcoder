public class FencingPenguinsEasy {
  class Post {
    double x;
    double y;
  }
  public double calculateMinArea(int numPosts, int radius, int[] x, int[] y) {
    double perDegree = 2 * Math.PI / numPosts;
    Post[] posts = new Post[numPosts];
    for (int i = 0; i < numPosts; ++i) {
      double degree = i * perDegree;
      posts[i] = new Post();
      posts[i].x = radius * Math.cos(degree);
      posts[i].y = radius * Math.sin(degree);
    }

    int[] possibleLen = new int[numPosts];
    for (int i = 0; i < numPosts; ++i) {
      int len = 1;
      for (; len < numPosts; ++len) {
        int j = (i + len) % numPosts;
        boolean possible = true;
        for (int k = 0; k < x.length; ++k) {
          if (DotMul(posts[i].x, posts[i].y, posts[j].x, posts[j].y,
                     x[k], y[k]) <= 1e-9) {
            possible = false;
            break;
          }
        }
        if (!possible) {
          break;
        }
      }
      possibleLen[i] = len - 1;
    }

    double result = -1.0;
    for (int start = 0; start < numPosts; ++start) {
      double[] area = new double[numPosts];
      for (int i = 0; i < numPosts; ++i) {
        area[i] = -1;
      }
      for (int i = 1; i < numPosts; ++i) {
        int targetPos = (start + i) % numPosts;
        if (possibleLen[start] >= dist(start, targetPos, numPosts)) {
          area[i] = 0.0;
        } else {
          for (int j = 1; j < i; ++j) {
            int midPos = (start + j) % numPosts;
            if (area[j] >= 0.0 &&
                possibleLen[midPos] >= dist(midPos, targetPos, numPosts)) {
              double temp = triangleArea(posts[start].x, posts[start].y, posts[midPos].x, posts[midPos].y,
                                         posts[targetPos].x, posts[targetPos].y);
              temp += area[j];
              if (area[i] == -1 || area[i] > temp) {
                area[i] = temp;
              }
            }
          }
        }
      }
      double minArea = -1;
      for (int i = 1; i < numPosts; ++i) {
        int pos = (start + i) % numPosts;
        if (area[i] >= 0 && possibleLen[pos] >= dist(pos, start, numPosts)) {
          if (minArea < 0 || minArea > area[i]) {
            minArea = area[i];
          }
        }
      }
      if (minArea >= 0) {
        if (result < 0 || result > minArea) {
          result = minArea;
        }
      }
    }
    return result;
  }

  int dist(int start, int end, int n) {
    if (end < start) {
      end += n;
    }
    return end - start;
  }

  double DotMul(double x1, double y1, double x2, double y2, double x3, double y3) {
    double dx1 = x2 - x1;
    double dy1 = y2 - y1;
    double dx2 = x3 - x1;
    double dy2 = y3 - y1;

    double result = dx1 * dy2 - dx2 * dy1;
    return result;
  }

  double triangleArea(double x1, double y1, double x2, double y2, double x3, double y3) {
    double a = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    double b = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
    double c = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));

    double p = (a + b + c) / 2;
    double area = Math.sqrt(p * (p - a) * (p - b) * (p - c));
    return area;
  }
}
