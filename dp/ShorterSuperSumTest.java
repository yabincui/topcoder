import org.junit.*;
import static org.junit.Assert.*;

public class ShorterSuperSumTest {
  ShorterSuperSum sum = new ShorterSuperSum();

  @Test
  public void test1() {
    assertEquals(6, sum.calculate(1, 3));
    assertEquals(10, sum.calculate(2, 3));
    assertEquals(2002, sum.calculate(4, 10));
    assertEquals(167960, sum.calculate(10, 10));
  }
}
