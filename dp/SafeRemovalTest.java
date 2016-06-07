import org.junit.*;
import static org.junit.Assert.*;

public class SafeRemovalTest {
  SafeRemoval safe = new SafeRemoval();

  @Test
  public void test() {
    assertEquals(11, safe.removeThem(new int[]{3, 8, 4}, 1));
    assertEquals(-1, safe.removeThem(new int[]{1, 1, 1, 1, 1, 1}, 3));
    assertEquals(21, safe.removeThem(new int[]{1,6,1,10,1,2,7,11}, 6));
    assertEquals(6, safe.removeThem(new int[]{1,1,1,1,1,1,1,1,7}, 3));
  }
}
