import org.junit.*;
import static org.junit.Assert.*;

public class Excavations2Test {
  Excavations2 ex = new Excavations2();

  @Test
  public void test() {
    assertEquals(1, ex.count(new int[]{1,2,2,1}, new int[]{1}, 2));
    assertEquals(4, ex.count(new int[]{1,2,2,1}, new int[]{1,2}, 2));
    assertEquals(6, ex.count(new int[]{1, 2, 1, 1, 2, 3, 4, 3, 2, 2}, new int[]{4, 2}, 3));
    assertEquals(5567902560L, ex.count(new int[]
    {50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, new int[]
{50}, 21));
  }
}
