import org.junit.*;
import static org.junit.Assert.*;

public class FencingPenguinsEasyTest {
  FencingPenguinsEasy fence = new FencingPenguinsEasy();

  @Test
  public void test() {
    assertEquals(32.47595264191645, fence.calculateMinArea(3, 5, new int[]{-1}, new int[]{0}), 1e-9);
    assertEquals(-1.0, fence.calculateMinArea(30, 5, new int[]{6}, new int[]{0}), 1e-9);
    assertEquals(25.0, fence.calculateMinArea(4, 5, new int[]{2}, new int[]{1}), 1e-9);
    assertEquals(50.0, fence.calculateMinArea(4, 5, new int[]{2, -2}, new int[]{1, -1}), 1e-9);
    assertEquals(-1.0, fence.calculateMinArea(8, 5, new int[]{8}, new int[]{8}), 1e-9);
    assertEquals(29.436752637711805, fence.calculateMinArea(7, 10, new int[]{9}, new int[]{1}), 1e-9);
    assertEquals(384778.74757953023, fence.calculateMinArea(30, 800,
    new int[]{8,2,100,120,52,67,19,-36}, new int[]
{-19,12,88,-22,53,66,-140,99}), 1e-9);
  }
}
