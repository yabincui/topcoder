import org.junit.*;
import static org.junit.Assert.*;

public class MergersDivTwoTest {
  MergersDivTwo merge = new MergersDivTwo();

  @Test
  public void test() {
    assertEquals(1.5, merge.findMaximum(new int[]{5, -7, 3}, 2), 1e-9);
    assertEquals(0.3333333333333333, merge.findMaximum(new int[]{5, -7, 3}, 3), 1e-9);
    assertEquals(2.9166666666666665, merge.findMaximum(new int[]{1, 2, 2, 3, -10, 7}, 3), 1e-9);
    assertEquals(-66.66666666666667, merge.findMaximum(new int[]
    {-100, -100, -100, -100, -100, 100}, 4), 1e-9);
    assertEquals(706.0369290573373, merge.findMaximum(new int[]
    {869, 857, -938, -290, 79, -901, 32, -907, 256, -167, 510, -965, -826, 808, 890,
 -233, -881, 255, -709, 506, 334, -184, 726, -406, 204, -912, 325, -445, 440, -368},
    7), 1e-9);
  }
}
