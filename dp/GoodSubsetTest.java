import org.junit.*;
import static org.junit.Assert.*;

public class GoodSubsetTest {
  GoodSubset subset = new GoodSubset();

  @Test
  public void test() {
    assertEquals(1, subset.numberOfSubsets(10, new int[]{2, 3, 4, 5}));
    assertEquals(2, subset.numberOfSubsets(6, new int[]{2, 3, 4, 5, 6}));
    assertEquals(7, subset.numberOfSubsets(1, new int[]{1, 1, 1}));
    assertEquals(6, subset.numberOfSubsets(12, new int[]{1,2,3,4,5,6,7,8,9,10,11,12}));
    assertEquals(0, subset.numberOfSubsets(5, new int[]{1, 2, 3, 4}));
  }
}
