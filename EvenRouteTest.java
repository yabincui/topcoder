import org.junit.*;
import static org.junit.Assert.*;

public class EvenRouteTest {
  EvenRoute route = new EvenRoute();

  @Test
  public void test1() {
    assertEquals("CAN", route.isItPossible(new int[]{-1, -1, 1, 1}, new int[]{-1, 1, 1, -1}, 0));
    assertEquals("CAN", route.isItPossible(new int[]{-5, -3, 2}, new int[]{2, 0, 3}, 1));
    assertEquals("CAN", route.isItPossible(new int[]{1001, -4000}, new int[]{0, 0}, 1));
    assertEquals("CANNOT", route.isItPossible(new int[]{11, 21, 0}, new int[]{-20, 42, 7}, 0));
    assertEquals("CANNOT", route.isItPossible(new int[]{0, 6}, new int[]{10, -20}, 1));
  }
}
