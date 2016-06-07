import org.junit.*;
import static org.junit.Assert.*;

public class FoxAndTouristFamiliesTest {
  FoxAndTouristFamilies fox = new FoxAndTouristFamilies();

  @Test
  public void test() {
    assertEquals(1.5, fox.expectedLength(new int[]{0, 1}, new int[]{1, 2}, new int[]{0}), 1e-9);
    assertEquals(1.25, fox.expectedLength(new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 0}), 1e-9);
    assertEquals(0.75, fox.expectedLength(new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 1}), 1e-9);
    assertEquals(1.0, fox.expectedLength(new int[]{0, 1}, new int[]{1, 2}, new int[]{0, 2}), 1e-9);
    assertEquals(1.0, fox.expectedLength(new int[]{0, 0, 0}, new int[]{1, 2, 3}, new int[]{0}), 1e-9);
    assertEquals(0.7777777777777777, fox.expectedLength(new int[]{0, 0, 0},
        new int[]{1, 2, 3}, new int[]{1, 2}), 1e-9);
    assertEquals(0.4537037037037037, fox.expectedLength(new int[]
    {0,1,1,3,5,6}, new int[]
{1,2,3,4,4,4}, new int[]
{5,6,1}), 1e-9);
    assertEquals(1.4914341925000003, fox.expectedLength(new int[]
    {0,1,2,3,4,5,6,7,8,9}, new int[]
{1,2,3,4,5,6,7,8,9,10}, new int[]
{0,0,0,0,0,0,0,0,0,0}), 1e-9);
  }
}
