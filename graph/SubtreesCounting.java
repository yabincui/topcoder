import java.util.*;
import static org.junit.Assert.*;

public class SubtreesCounting {

  long MOD = 1000000007;

  class SegTree {
    SegTree(int size) {
      up_bound = 1;
      while (up_bound < size) {
        up_bound <<= 1;
      }
      s = new long[up_bound * 2];
      this.size = size;
      for (int i = 0; i < s.length; ++i) {
        s[i] = 1;
      }
    }
 
    void set(int pos, long value) {
      int left = 0;
      int right = up_bound - 1;
      int root = 0;
      while (true) {
        //System.out.printf("left = %d, right = %d, root = %d\n", left, right, root);
        s[root] = (s[root] * value) % MOD;
        if (left == right) {
          break;
        }
        int mid = (left + right) / 2;
        if (mid >= pos) {
          root = 2 * root + 1;
          right = mid;
        } else {
          root = 2 * root + 2;
          left = mid + 1;
        }
      }
    }
 
    long getRange(int start, int end) {
      return getRangeRecursive(0, 0, up_bound - 1, start, end);
    }

    private long getRangeRecursive(int root, int left, int right, int start, int end) {
      if (left >= start && right <= end) {
        return s[root];
      }
      if (left > end || right < start) {
        return 1;
      }
      int mid = (left + right) / 2;
      long a = getRangeRecursive(root * 2 + 1, left, mid, start, end);
      long b = getRangeRecursive(root * 2 + 2, mid + 1, right, start, end);
      return (a * b) % MOD;
    }

    long[] s;
    int size;
    int up_bound;
  }

	public int sumOfSizes(int n, int a0, int b, int c, int m) {
		int[] a = new int[n];
		a[0] = a0;
		for (int i = 1; i <= n - 2; ++i) {
			a[i] = (int)(((long) b * a[i - 1] + c) % m);
		}
		int[] parent = new int[n];
		parent[0] = -1;
		for (int i = 1; i <= n - 1; ++i) {
			int j = a[i - 1] % i;
			parent[i] = j;
		}
		ArrayList<ArrayList<Integer>> children = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < n; ++i) {
			children.add(new ArrayList<Integer>());
		}
		for (int i = 1; i < n; ++i) {
			children.get(parent[i]).add(i);
		}
		/*
		for (int i = 0; i < n; ++i) {
			System.out.printf("children[%d] = ", i);
			for (int j = 0; j < children.get(i).size(); ++j) {
				System.out.printf("%d ", children.get(i).get(j));
			}
			System.out.println();
		}
		*/
		long MOD = 1000000007;
		long[] size_with_root = new long[n];
		long[] count_with_root = new long[n];
		long result = 0;
		for (int i = n - 1; i >= 0; --i) {
			if (children.get(i).isEmpty()) {
				size_with_root[i] = 1;
				count_with_root[i] = 1;
				continue;
			}
			ArrayList<Integer> child = children.get(i);
			int size = child.size();
      System.out.printf("size = %d\n", size);
			for (int j = 0; j < size; ++j) {
				result = (result + size_with_root[child.get(j)]) % MOD;
			}
 
      SegTree segTree = new SegTree(size);
      for (int j = 0; j < size; ++j) {
        segTree.set(j, count_with_root[child.get(j)] + 1);
      }
      count_with_root[i] = segTree.getRange(0, size - 1);
      size_with_root[i] = count_with_root[i];

			for (int j = 0; j < size; ++j) {
        long tmp = 1;
        if (j > 0) {
          tmp = (tmp * segTree.getRange(0, j - 1)) % MOD;
        }
        if (j < size - 1) {
          tmp = (tmp * segTree.getRange(j + 1, size - 1)) % MOD;
        }
				tmp = (tmp * size_with_root[child.get(j)]) % MOD;
				size_with_root[i] = (size_with_root[i] + tmp) % MOD;
			}
		}
		result = (result + size_with_root[0]) % MOD;
		return (int)result;
	}

  public static void main(String[] args) {
    SubtreesCounting obj = new SubtreesCounting();
    assertEquals(10, obj.sumOfSizes(3, 1, 1, 1, 1));
    int result = obj.sumOfSizes(100000, 1, 1, 1, 1000000000);
    System.out.printf("result = %d\n", result);
  }
}
