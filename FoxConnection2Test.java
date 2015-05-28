import org.junit.*;
import static org.junit.Assert.*;

public class FoxConnection2Test {
  FoxConnection2 fox = new FoxConnection2();

  @Test
  public void test() {
    assertEquals(3, fox.ways(new int[]{1, 2, 3}, new int[]{2, 3, 4}, 2));
    assertEquals(6, fox.ways(new int[]{1, 1, 1, 1}, new int[]{2, 3, 4, 5}, 3));
    assertEquals(3, fox.ways(new int[]{1, 2, 3, 4}, new int[]{2, 3, 4, 5}, 3));
    assertEquals(6, fox.ways(new int[]{1, 2, 2, 4, 4}, new int[]{2, 3, 4, 5, 6}, 3));
    assertEquals(4, fox.ways(new int[]{1, 2, 2, 4, 4}, new int[]{2, 3, 4, 5, 6}, 5));
    assertEquals(923263934, fox.ways(new int[]
    {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
    new int[]
    {2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40},
    20));
    assertEquals(2, fox.ways(new int[]{2}, new int[]{1}, 1));
  }
}
