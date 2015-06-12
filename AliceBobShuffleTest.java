import org.junit.*;
import static org.junit.Assert.*;

public class AliceBobShuffleTest {
  AliceBobShuffle shuffle = new AliceBobShuffle();

  @Test
  public void test() {
    assertEquals(4, shuffle.countWays(new int[]{1, 3}, new int[]{2, 4}, new int[]{2, 3}, new int[]{1, 4}));
    assertEquals(4, shuffle.countWays(new int[]{1, 2, 1}, new int[]{1, 2},
        new int[]{1, 2, 1}, new int[]{2, 1}));
    assertEquals(0, shuffle.countWays(new int[]{1}, new int[]{2}, new int[]{3}, new int[]{4}));
    assertEquals(0, shuffle.countWays(new int[]{1, 2}, new int[]{3}, new int[]{3}, new int[]{2, 1}));
    assertEquals(314159265, shuffle.countWays(
    new int[]{3, 3, 2, 4, 1, 3, 1, 2, 1, 1, 5, 5, 1, 2, 3, 1, 2, 1, 2, 1, 1, 1, 2},
    new int[]{4, 1, 4, 3, 4, 4, 4, 3, 2, 1, 6, 2, 2, 3, 2, 2, 1, 3, 2, 3},
    new int[]{4, 1, 4, 3, 4, 4, 4, 1, 3, 1, 2, 2, 2, 3, 2, 1, 2, 1, 2, 2, 2},
    new int[]{3, 3, 2, 3, 4, 2, 1, 2, 1, 1, 5, 6, 5, 1, 3, 1, 2, 3, 1, 1, 1, 3}));
  }
}
