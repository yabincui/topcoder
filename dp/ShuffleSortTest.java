import org.junit.*;
import static org.junit.Assert.*;

public class ShuffleSortTest {
  ShuffleSort sort = new ShuffleSort();

  @Test
  public void test() {
    assertEquals(1.0, sort.shuffle(new int[]{1}), 1e-9);
    assertEquals(2.0, sort.shuffle(new int[]{1,2}), 1e-9);
    assertEquals(4.0, sort.shuffle(new int[]{2,3,1}), 1e-9);
    assertEquals(16.0, sort.shuffle(new int[]{15,16,4,8,42,23}), 1e-9);
    assertEquals(1.0, sort.shuffle(new int[]{1,1,1,1,1,1,1,1}), 1e-9);
  }
}
