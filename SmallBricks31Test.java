import org.junit.*;
import static org.junit.Assert.*;

public class SmallBricks31Test {
  SmallBricks31 brick = new SmallBricks31();

  @Test
  public void test() {
    assertEquals(4, brick.countWays(1, 3));
    assertEquals(13, brick.countWays(3, 1));
    assertEquals(84, brick.countWays(3, 2));
    assertEquals(132976888, brick.countWays(4, 9));
    assertEquals(951846687, brick.countWays(10, 10));
  }
}
