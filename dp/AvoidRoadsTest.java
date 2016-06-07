import org.junit.*;
import static org.junit.Assert.*;

public class AvoidRoadsTest {
  AvoidRoads roads = new AvoidRoads();

  @Test
  public void test1() {
    assertEquals(252L, roads.numWays(6, 6, new String[]{"0 0 0 1", "6 6 5 6"}));
  }

  @Test
  public void test2() {
    assertEquals(2L, roads.numWays(1, 1, new String[]{}));
  }

  @Test
  public void test3() {
    assertEquals(6406484391866534976L, roads.numWays(35, 31, new String[]{}));
  }

  @Test
  public void test4() {
    assertEquals(0L, roads.numWays(2, 2, new String[]{"0 0 1 0", "1 2 2 2", "1 1 2 1"}));
  }
}
