import org.junit.*;
import static org.junit.Assert.*;

public class QuickSumsTest {
  QuickSums sums = new QuickSums();

  @Test
  public void test1() {
    assertEquals(4, sums.minSums("99999", 45));
  }

  @Test
  public void test2() {
    assertEquals(3, sums.minSums("1110", 3));
  }

  @Test
  public void test3() {
    assertEquals(8, sums.minSums("0123456789", 45));
  }

  @Test
  public void test4() {
    assertEquals(-1, sums.minSums("99999", 100));
  }

  @Test
  public void test5() {
    assertEquals(2, sums.minSums("382834", 100));
  }

  @Test
  public void test6() {
    assertEquals(4, sums.minSums("9230560001", 71));
  }
}
