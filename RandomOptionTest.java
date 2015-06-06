import org.junit.*;
import static org.junit.Assert.*;

public class RandomOptionTest {
  RandomOption option = new RandomOption();

  @Test
  public void test() {
    assertEquals(1.0, option.getProbability(2, new int[]{}, new int[]{}), 1e-9);
    assertEquals(0.6, option.getProbability(5, new int[]{0}, new int[]{3}), 1e-9);
    assertEquals(0.1, option.getProbability(5, new int[]{0, 1, 2}, new int[]{1, 2, 0}), 1e-9);
    assertEquals(0.0, option.getProbability(5, new int[]{2, 2, 2, 2}, new int[]{0, 1, 3, 4}), 1e-9);
    assertEquals(0.3904761904761904, option.getProbability(7, new int[]{0, 1, 2},
        new int[]{6, 5, 4}), 1e-9);
    assertEquals(0.09166666666666667, option.getProbability(7, new int[]
    {3, 5, 1, 0, 2, 6, 4}, new int[]
{0, 2, 4, 6, 1, 5, 3}), 1e-9);
  }
}
