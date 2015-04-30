import org.junit.*;
import static org.junit.Assert.*;

public class ZigZagTest {
  ZigZag zig = new ZigZag();

  void matchZigZag(int[] s, int len) {
    if (len <= 1) {
      return;
    }
    int dir = (s[1] > s[0]) ? 1 : -1;
    boolean result = true;
    for (int i = 2; i < len; ++i) {
      if ((dir == 1 && s[i] > s[i - 1]) || (dir == -1 && s[i] < s[i - 1])) {
        result = false;
        break;
      }
      dir = -dir;
    }
    assertTrue(result);
  }

  @Test
  public void test1() {
    int[] s = {1, 7, 4, 9, 2, 5};
    assertEquals(6, zig.longestZigZag(s));
    matchZigZag(s, 6);
  }
  
  @Test
  public void test2() {
    int[] s = {1, 17, 5, 10, 13, 15, 10, 5, 16, 8};
    assertEquals(7, zig.longestZigZag(s));
    matchZigZag(s, 7);
  }
  
  @Test
  public void test3() {
    int[] s = {44};
    assertEquals(1, zig.longestZigZag(s));
    matchZigZag(s, 1);
  }

  @Test
  public void test4() {
    int[] s = {70, 55, 13, 2, 99, 2, 80, 80, 80, 80, 100, 19, 7, 5, 5, 5, 1000, 32, 32 };
    assertEquals(8, zig.longestZigZag(s));
    matchZigZag(s, 8);
  }

  @Test
  public void test5() {
    int[] s = {374, 40, 854, 203, 203, 156, 362, 279, 812, 955, 
600, 947, 978, 46, 100, 953, 670, 862, 568, 188, 
67, 669, 810, 704, 52, 861, 49, 640, 370, 908, 
477, 245, 413, 109, 659, 401, 483, 308, 609, 120, 
249, 22, 176, 279, 23, 22, 617, 462, 459, 244};

    assertEquals(36, zig.longestZigZag(s));
    matchZigZag(s, 36);
  }
}
